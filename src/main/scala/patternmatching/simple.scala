package patternmatching
import scala.util.Random
object simple  {
  def main(args: Array[String]): Unit = {
    // switch on steroids
    val random = new Random
    val x = random.nextInt(10)

    val description = x match {
      case 1 => "the ONE"
      case 2 => "double or nothing"
      case 3 => "third time is the charm"
      case _ => "something else"  // _ = WILDCARD
    }
    println(x)
    println(description)
  }
}
