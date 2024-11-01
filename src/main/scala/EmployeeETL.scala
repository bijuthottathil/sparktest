import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._


class EmployeeETL(spark: SparkSession) extends DatabaseUtils {

  // Step 1: Create a DataFrame with hardcoded employee data
  def createEmployeeDataFrame(): DataFrame = {
    import spark.implicits._
    Seq(
      Employee(1, "Alice", 29, "Engineering"),
      Employee(2, "Bob", 35, "Marketing"),
      Employee(3, "Cathy", 27, "Sales"),
      Employee(4, "David", 30, "Support")
    ).toDF()

  }

  // Step 2: Transform the DataFrame (e.g., add a 'full_name' column)
  def transformEmployeeData(df: DataFrame): DataFrame = {
   // df.filter($"age" > 18) // Only include employees older than 18
    //  .withColumn("full_name", concat($"name", lit(" from "), $"department"))
    df.show()
    df
  }

  // Step 3: Load the DataFrame into Azure SQL
  def loadEmployeeData(df: DataFrame, tableName: String): Unit = {
    writeToAzureSQL(df, tableName)
  }
}
