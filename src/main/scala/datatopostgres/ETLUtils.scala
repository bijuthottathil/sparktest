package datatopostgres

import org.apache.spark.sql.SparkSession

object ETLUtils {
  def getSparkSession(appName: String): SparkSession = {
    SparkSession.builder()
      .appName(appName)
      .master("local[*]") // Use local mode with all cores
      .getOrCreate()
  }

  def getDbProperties(): java.util.Properties = {
    val properties = new java.util.Properties()
    properties.setProperty("driver", "org.postgresql.Driver")
    properties.setProperty("user", "postgres")
    properties.setProperty("password", "postgres")
    properties
  }
}
