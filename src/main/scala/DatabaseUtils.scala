import org.apache.spark.sql.{DataFrame, SparkSession}

import java.sql.SQLException

trait DatabaseUtils {
  def getJdbcUrl: String = {
    val server = "mydbserverforscala.database.windows.net:1433"
    val database = "mydbforscala"
    val username = "sqladmin@mydbserverforscala"
    val password = "SmithaMathew#1526"
    s"jdbc:sqlserver://$server;database=$database;user=$username;password=$password;;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30"
  }

  def writeToAzureSQL(df: DataFrame, tableName: String): Unit = {
    val password = "SmithaMathew#1526"
     //df.write
     // .format("jdbc")
    //  .option("url", getJdbcUrl)
      //.option("dbtable", tableName)
     // .option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
     // .mode("overwrite")
    //  .save()
    try {
      df.write
        .format("jdbc")
        .option("url", s"jdbc:sqlserver://mydbserverforscala.database.windows.net:1433;database=mydbforscala;user=sqladmin@mydbserverforscala;password=$password;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;;")
        .option("dbtable", "dbo.EmployeeTable") // Specify dbo schema explicitly
        .option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
        .mode("overwrite")
        .save()

      println("Data Saved")
    }
    catch {
      case e: SQLException =>
        println(s"Database error occurred: ${e.getMessage}")
      // Log the error or handle the SQLException specifically if needed
      case e: Exception =>
        println(s"An error occurred while writing data to Azure SQL: ${e.getMessage}")
      // Log the error or handle any generic exception
    }
  }
}
