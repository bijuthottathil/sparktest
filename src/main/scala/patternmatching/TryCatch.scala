package patternmatching

import scala.util.{Try, Success, Failure}


object TryCatch extends  App {


  import scala.util.{Failure, Success, Try}

  def divide(dividend: Int, divisor: Int): Try[Int] = Try {
    dividend / divisor
  }

  val result = divide(10, 2) // Try(5)
  val failResult = divide(10, 0) // Failure(java.lang.ArithmeticException: / by zero)

  result match {
    case Success(value) => println(s"Success: $value")
    case Failure(exception) => println(s"Failure: ${exception.getMessage}")
  }

  failResult match {
    case Success(value) => println(s"Success: $value")
    case Failure(exception) => println(s"Failure: ${exception.getMessage}")
  }

}
