package functors

import cats.Functor

trait Printable[A] { self =>
  def format(value: A): String
  def contramap[B](func: B => A): Printable[B] = new Printable[B] {
    override def format(value: B): String = self.format(func(value))
  }
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        s"'${value}'"
    }
  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }
  //Вместо создания здесь new Printable используем contramap в котором он уже создается
  implicit def boxPrintable[A](implicit fmt: Printable[A]): Printable[Box[A]] =
    fmt.contramap[Box[A]](_.value)

//    new Printable[Box[A]] {
//      override def format(value: Box[A]): String = s"${value.value}"
//    }
}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String =
    printable.format(value)
}

final case class Box[A](value: A)
object Contramap extends App{
  import PrintableInstances._

  println(Printable.format("hello"))

  println(Printable.format(Box("hello world")))
}
