import org.apache.spark.sql.SparkSession

object EmployeeMain{
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("EmployeeETL")
      .master("local[*]")
      .getOrCreate()

    val employeeETL = new EmployeeETL(spark)

    // Execute ETL pipeline
    val employeeDF = employeeETL.createEmployeeDataFrame()
    val transformedDF = employeeETL.transformEmployeeData(employeeDF)
    employeeETL.loadEmployeeData(transformedDF, "dbo.Employee")

    println("ETL process completed successfully!")
    spark.stop()
  }
}