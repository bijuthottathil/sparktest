package datatopostgres

import org.apache.spark.sql.SparkSession
import Config._
object ETLApp {
  def main(args: Array[String]): Unit = {
    // Set up Spark and database properties
    val spark: SparkSession = ETLUtils.getSparkSession("ETLWithPostgres")
    val dbProperties = ETLUtils.getDbProperties()
    val inputPath = Config.inputPath // Path to your input CSV file
    // Initialize and run the ETL process
    val etl = new ETLProcess(spark, Config.dbUrl, Config.dbTable, dbProperties, inputPath)
    val extractedData = etl.extract()
    val transformedData = etl.transform(extractedData)
    etl.show(transformedData)
    etl.save(transformedData)
    etl.load(transformedData)

    // Stop the Spark session
    //  spark.stop()
  }
}
