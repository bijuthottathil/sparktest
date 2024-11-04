package Encapsulation

class DataTransformation {
  private var transformationrule: String = "default"

  def setRule(rule: String): Unit = {
    if (rule.nonEmpty) transformationrule = rule
  }

  def getRule(): String = transformationrule


  def applytransformation(data: String): Unit = {
    println(s"datatransformation applied $data using $transformationrule")
  }

}
object Encapsulation extends App{

  val dt=new DataTransformation

  dt.setRule("Read")
  dt.getRule()
  dt.applytransformation("dataexample")

}
