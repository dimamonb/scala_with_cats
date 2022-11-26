package monoid

import cats.{Monoid, Show}
import cats.syntax.semigroup._

object MonoidInCats {

}

object Main extends App {
  val s = Show[String].show(Monoid[String].combine("Hi", " There"))
  println(s)

  println(Monoid[Int].combine(4, 7))

  val stringResult = "Hi, " |+| "There"
  println(stringResult)
}
