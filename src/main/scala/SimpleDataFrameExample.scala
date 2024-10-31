import org.apache.spark.sql.{SparkSession, DataFrame}

object SimpleDataFrameExample {
  // Function to create a DataFrame with sample data
  def createSampleDataFrame(spark: SparkSession): DataFrame = {
    val data = Seq(
      ("Alice", 29),
      ("Bob", 35),
      ("sagar", 27),
      ("David", 30)
    )
    import spark.implicits._
    data.toDF("name", "age")
  }

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SimpleDataFrameExample")
      .master("local[*]")
      .getOrCreate()

    val df = createSampleDataFrame(spark)
    println("Displaying the DataFrame content:")
    df.show()

    spark.stop()
  }
}
