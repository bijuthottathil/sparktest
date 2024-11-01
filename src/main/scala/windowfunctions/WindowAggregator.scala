package windowfunctions

// WindowAggregator.scala
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

class WindowAggregator(spark: SparkSession) {

  // Calculate running total of sales for each customer
  def calculateRunningTotal(salesDf: DataFrame): DataFrame = {
    val windowSpec = Window.partitionBy("customerId").orderBy("saleDate")
    salesDf.withColumn("running_total", sum("amount").over(windowSpec))
  }

  // Rank each sale within each customer based on sale amount
  def rankSalesByAmount(salesDf: DataFrame): DataFrame = {
    val rankSpec = Window.partitionBy("customerId").orderBy(desc("amount"))
    salesDf.withColumn("rank", rank().over(rankSpec))
  }
}
