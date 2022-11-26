package printable

import printable.PrintableExample.Printable

object PrintableExample {

  trait Printable[A]{
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val printableString: Printable[String] = new Printable[String] {
      override def format(value: String): String = value
    }

    implicit val printableInt: Printable[Int] = new Printable[Int] {
      override def format(value: Int): String = value.toString
    }
  }

  object Printable {
    def format[A](value: A)(implicit printable: Printable[A]): String =
      printable.format(value)

    def print[A](value: A)(implicit printable: Printable[A]): Unit =
      println(format(value))
  }
}

object UsePrintable extends App {
  import printable.PrintableExample.PrintableInstances._

  final case class Cat(name: String, age: Int, color: String)
  val cat = Cat("Vasia", 11, "white")

  implicit val printCat = new Printable[Cat] {
    override def format(value: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  Printable.print(cat)
}
