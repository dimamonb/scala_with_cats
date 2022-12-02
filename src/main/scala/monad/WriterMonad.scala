package monad

import cats.data.Writer
import cats.syntax.writer._
import cats.syntax.applicative._
import cats.instances.vector._
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object WriterMonad {

  type Logged[A] = Writer[Vector[String], A]

  def slowly[A](body: => A): A =
    try body finally Thread.sleep(100)


  def factorial(n: Int): Logged[Int] = {
    for {
      ans <- if(n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }

  def main(args: Array[String]): Unit = {
    val a = Writer(Vector(
      "It was the best of times",
      "it was the worst of times"
    ), 1859)

    println(a.written)

    val pl = 123.pure[Logged]
    println(pl)
    val t: Writer[Vector[String], Unit] = Vector("msg1", "msg2", "msg3").tell
    println(t)

    factorial(5)
    println()

    Await.result(Future.sequence(
      Vector(Future(factorial(5)), Future(factorial(5)))
    ), 5.seconds)
  }
}
