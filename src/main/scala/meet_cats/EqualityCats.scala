package meet_cats
import cats.Eq

object EqualityCats extends App {

  import cats.instances.int._
  import cats.instances.option._

  val eqInt = Eq[Int]
  println(eqInt.eqv(3, 4))
  println(eqInt.eqv(3, 3))

  import cats.syntax.eq._
  println(3 === 5)
  println(3 =!= 5)

  import cats.syntax.option._
  println(Option(1) === None)
  println(1.some === none[Int])

}
