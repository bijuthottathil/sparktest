package datatopostgres

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import java.util.Properties

class ETLProcess(spark: SparkSession, dbUrl: String, dbTable: String, dbProperties: Properties, inputPath: String) extends ETL {

  // Step 1: Extract data from CSV
  def extract(): DataFrame = {
    spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(inputPath)
  }

  // Step 2: Transform data (a simple transformation example)
  def transform(csvdf: DataFrame): DataFrame = {
    csvdf.withColumn("age_group", when(col("age") < 18, "Minor")
      .when(col("age") >= 18 && col("age") <= 65, "Adult")
      .otherwise("Senior"))

  }
  // Step 3: Show transformed dataframe in console
  def show(csvdf: DataFrame): Unit = {
    csvdf.show()

  }
  // Step 4: store transformed dataframe in a folder
  def save(df: DataFrame): Unit = {
    val outputPath = "src/main/resources/output/transformed_data"  // Path to save the transformed DataFrame
    df.write
      .mode("overwrite")  // Overwrite if the folder already exists
      .option("header", "true")  // Include header in the output file
      .csv(outputPath)
    println(s"Transformed DataFrame written to $outputPath")

  }

  // Step 5: Load data into PostgreSQL
  def load(csvdf: DataFrame): Unit = {
    // Load the existing data from PostgreSQL into a DataFrame
    val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
    val jdbcTable = "newemployees"
    val existingDF = spark.read
      .jdbc(jdbcUrl, jdbcTable, dbProperties)
    val mergedDF = csvdf.join(existingDF, Seq("id"), "leftanti")
    existingDF.show()

    // Write the merged data back to PostgreSQL
    mergedDF.write
      .mode("append")
      .jdbc(jdbcUrl, jdbcTable, dbProperties)

    println("Successfully Loaded in Postgres")
  }
}
