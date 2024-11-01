import org.apache.spark.sql.{SparkSession, DataFrame}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfterAll
import SimpleDataFrameExample.createSampleDataFrame

class SimpleDataFrameExampleTest extends AnyFunSuite with BeforeAndAfterAll {

  // Initialize a Spark session for testing
  lazy val spark: SparkSession = SparkSession.builder()
    .appName("SimpleDataFrameExampleTest")
    .master("local[*]")
    .getOrCreate()


  // Stop the Spark session after all tests are complete
  override def afterAll(): Unit = {
    spark.stop()
  }

  /*test("createSampleDataFrame should create a DataFrame with the correct data") {
    // Call the function to create the DataFrame
    val df: DataFrame = createSampleDataFrame(spark)

    // Expected data
    val expectedData = Seq(
      ("Alice", 29),
      ("Bob", 35),
      ("sagar", 27),
      ("David", 30)
    )
    import spark.implicits._
    val expectedDf = expectedData.toDF("name", "age")

    // Verify that the content of the DataFrame matches the expected data
    assert(df.collect() === expectedDf.collect())
     assert(df.count() !=0, "DataFrame row count should >0.")
    // Verify that the schema of the DataFrame matches the expected schema
    assert(df.schema === expectedDf.schema)
   assert(df.schema("age").dataType.simpleString != "integer", "Age column should be of type integer.")
    val nullCount = df.filter($"age".isNull).count()
    //println(nullCount)
    assert(nullCount == 0, "Age column should not contain null value.")
    val distinctCount = df.select("name").distinct().count()
    assert(distinctCount == df.count(), "name column should not have unique values.")
  }*/
}
