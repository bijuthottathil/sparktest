package datatopostgres

case object Config {
  // Database configuration
  val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
  val dbTable = "public.people"
  val inputPath = "src/main/resources/input/newemployees.csv"
}