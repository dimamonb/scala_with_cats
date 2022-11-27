package functors
import cats.Functor
import cats.instances.function._
import cats.syntax.functor._

object FunctorExample extends App {
  ex1
  ex2

  println(doMath(Option(20)))
  println(doMath(List(1,2,3)))

  def ex1 = {
    val func1: Int => Double =
      (x: Int) => x.toDouble

    val func2: Double => Double =
      (y: Double) => y * 2

    val f1 = func1.map(func2)(0)
    println(f1)
    val f2 = func1.andThen(func2)(1)
    println(f2)
    val f3 = func2(func1(1))
    println(f3)
  }

  def ex2 = {
    val list1 = List(1, 2, 3)
    println(list1)
    val list2 = Functor[List].map(list1)(_ * 2)
    println(list2)
  }

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]) = {
    start.map(n => n +1 * 2)
  }
}


