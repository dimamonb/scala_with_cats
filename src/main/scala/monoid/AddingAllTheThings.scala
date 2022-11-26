package monoid

import cats._

object AddingAllTheThings extends App{

  def add(items: List[Int]): Int = items.sum

  def add1[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldRight(Monoid[A].empty)((x,y) => Monoid[A].combine(x, y))
 // def add2[A](items: List[A]): Int = items.foldRight(Monoid[A].empty)(_ |+| _)

  def addOpt[A](items: List[Option[Int]])(implicit monoid: Monoid[A]): A = ???


  println(add1(List(1,4,5,6,7)))
  println(add1[Option[Int]](List(Some(1), Some(14), Some(31), Some(68))))

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderSum = new Monoid[Order]{
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order =
      Order(
        x.totalCost + y.totalCost,
        x.quantity + y.quantity
      )
  }
  println(add1(List(Order(1, 3), Order(5, 6))))

}
