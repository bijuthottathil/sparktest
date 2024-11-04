package Polimorphism

trait etljob
{
  def run():Unit
}

class fileupload extends etljob
{
  def run():Unit={
    println("File upload job")
  }
}

class filedelete extends etljob
{
  def run():Unit={
    println("File delete job")
  }
}

object Polimorphism extends App{

  def executeEtljob(job :etljob) :Unit ={
  job.run()
  }
  val fup=new fileupload()
   executeEtljob(fup)
  val   fdel=new fileupload()
  executeEtljob(fdel)
}
