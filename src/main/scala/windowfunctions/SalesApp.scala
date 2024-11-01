package windowfunctions

// SalesApp.scala
import org.apache.spark.sql.SparkSession

object SalesApp {

  def main(args: Array[String]): Unit = {
    // Initialize Spark session
    val spark = SparkSession.builder()
      .appName("SalesApp")
      .master("local[*]") // Run locally with all available cores
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    // Sample data for sales transactions
    val data = Seq(
      Sale(1, 101, 100.0, "2024-01-01"),
      Sale(1, 102, 150.0, "2024-01-02"),
      Sale(2, 201, 200.0, "2024-01-01"),
      Sale(2, 202, 50.0, "2024-01-02"),
      Sale(1, 103, 100.0, "2024-01-03"),
      Sale(2, 203, 300.0, "2024-01-03")
    )

    // Create DataFrame from sample data
    val salesDf = data.toDF()

    // Instantiate the WindowAggregator class
    val aggregator = new WindowAggregator(spark)

    // Calculate running total
    val runningTotalDf = aggregator.calculateRunningTotal(salesDf)
    println("Running Total DataFrame:")
    runningTotalDf.show()

    // Rank sales by amount within each customer
    val rankedSalesDf = aggregator.rankSalesByAmount(salesDf)
    println("Ranked Sales DataFrame:")
    rankedSalesDf.show()

    // Stop the Spark session
    spark.stop()
  }
}
