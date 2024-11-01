package patternmatching

import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object PatternDateChecking {

      def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder.appName("PatternMatchingETL").master("local[*]").getOrCreate()
        import spark.implicits._
        println("Testing Pattern")
        // Sample data with different types

        import spark.implicits._

        val data = Seq(
          ("Alice", "2024-01-01", 100),
          ("Bob", 1234567890, 200), // timestamp as integer
          ("Cathy", "01/02/2024", 300), // date in a different format
          ("David", null, 150) // missing date
        )

        val df = data.toDF("name", "date", "amount")
        df.show()
        println("DF show Pattern")
        // Transformation using pattern matching
        val transformedDF = df.withColumn("parsed_date", parseDate($"date"))
        transformedDF.show()

        spark.stop()
      }

      // Parse date based on data type and format using pattern matching
      def parseDate(dateColumn: Column): Column = {
        when(dateColumn.isNull, lit(null))
          .otherwise(udf((value: Any) => value match {
            case s: String if s.matches("\\d{4}-\\d{2}-\\d{2}") => s // ISO format: YYYY-MM-DD
            case s: String if s.matches("\\d{2}/\\d{2}/\\d{4}") => // Custom format: MM/DD/YYYY
              val Array(month, day, year) = s.split("/")
              s"$year-$month-$day"
            case i: Int => new java.sql.Date(i.toLong * 1000).toString // Unix timestamp
            case _ => null
          }).apply(dateColumn))
      }
    }

