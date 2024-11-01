package windowsfunctionstest
import windowfunctions.WindowAggregator
// WindowAggregatorTest.scala
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfterAll

// Sample data for testing
case class Sale(customerId: Int, saleId: Int, amount: Double, saleDate: String)

class WindowAggregatorTest extends AnyFunSuite with BeforeAndAfterAll {

  // Initialize Spark session
  lazy val spark: SparkSession = SparkSession.builder()
    .appName("WindowAggregatorTest")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  // Sample data for tests
  val salesData: Seq[Sale] = Seq(
    Sale(1, 101, 100.0, "2024-01-01"),
    Sale(1, 102, 150.0, "2024-01-02"),
    Sale(2, 201, 200.0, "2024-01-01"),
    Sale(2, 202, 50.0, "2024-01-02"),
    Sale(1, 103, 100.0, "2024-01-03"),
    Sale(2, 203, 300.0, "2024-01-03")
  )
  val salesDf: DataFrame = salesData.toDF()

  // Instantiate the WindowAggregator class
  val aggregator = new WindowAggregator(spark)

  override def afterAll(): Unit = {
    spark.stop()
  }



  test("rankSalesByAmount should rank sales by amount within each customer group") {
    // Perform the rank operation
    val resultDf = aggregator.rankSalesByAmount(salesDf)

    // Expected data for verification
    val expectedData = Seq(
      (1, 102, 150.0, "2024-01-02", 1),
      (1, 101, 100.0, "2024-01-01", 2),
      (1, 103, 100.0, "2024-01-03", 2),
      (2, 203, 300.0, "2024-01-03", 1),
      (2, 201, 200.0, "2024-01-01", 2),
      (2, 202, 50.0, "2024-01-02", 3)
    ).toDF("customerId", "saleId", "amount", "saleDate", "rank")

    // Validate that the actual result matches the expected result
   assert(resultDf.collect() === expectedData.collect())
  }

  test("calculateRunningTotal should calculate running total of sales for each customer") {
    // Perform the running total operation
    val resultDf = aggregator.calculateRunningTotal(salesDf)

    // Expected data for verification
    val expectedData = Seq(
      (1, 101, 100.0, "2024-01-01", 100.0),
      (1, 102, 150.0, "2024-01-02", 250.0),
      (1, 103, 100.0, "2024-01-03", 350.0),
      (2, 201, 200.0, "2024-01-01", 200.0),
      (2, 202, 50.0, "2024-01-02", 250.0),
      (2, 203, 300.0, "2024-01-03", 550.0)
    ).toDF("customerId", "saleId", "amount", "saleDate", "running_total")

    // Validate that the actual result matches the expected result
    assert(resultDf.collect() === expectedData.collect())
  }

/*  test("rankSalesByAmount should rank sales by amount within each customer group") {
    // Perform the rank operation
    val resultDf = aggregator.rankSalesByAmount(salesDf)

    // Expected data for verification
    val expectedData = Seq(
      (1, 102, 150.0, "2024-01-02", 1),
      (1, 101, 100.0, "2024-01-01", 2),
      (1, 103, 100.0, "2024-01-03", 2),
      (2, 203, 300.0, "2024-01-03", 1),
      (2, 201, 200.0, "2024-01-01", 2),
      (2, 202, 50.0, "2024-01-02", 3)
    ).toDF("customerId", "saleId", "amount", "saleDate", "rank")

    // Validate that the actual result matches the expected result
    assert(resultDf.collect() === expectedData.collect())
  }*/
}
