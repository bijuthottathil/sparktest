package classobjects

class DSource (val name: String,val format: String,val path : String  ) {

  def getDetails () : Unit =
  {
  println (s"Print share datasoruce details name $name,format $format, path $path")
    }
}

object  DataSource {
  def main(args: Array[String]): Unit = {
    val ds = new DSource("Spark", "json", "c://dd")
    ds.getDetails()
  }
}