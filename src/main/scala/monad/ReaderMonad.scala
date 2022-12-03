package monad

import cats.Id
import cats.data.{Kleisli, Reader}

object ReaderMonad extends App{

  final case class Cat(name: String, favoriteFood: String)
  val catName: Reader[Cat, String] =
    Reader(cat => cat.name)

  println(catName.run(Cat("Garfield", "lasagne")))

  val greetKitty = catName.map(name => s"Hello $name")

  println(greetKitty.run(Cat("Heathcliff", "junk food")))

  val feedKitty: Reader[Cat, String] = Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

  def greetAndfeed: Reader[Cat, String] = for {
    greet <- greetKitty
    feed <- feedKitty
  } yield s"$greet. $feed."

  println(greetAndfeed(Cat("Garfield", "lasagne")))
}
