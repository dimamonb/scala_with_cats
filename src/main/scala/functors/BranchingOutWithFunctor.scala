package functors

import cats.Functor

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    Leaf(value)
}


object BranchingOutWithFunctor extends App{

    import cats.implicits._
    implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
      override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
        case Branch(left, right) => Branch(map(left)(f), map(right)(f))
        case Leaf(value) => Leaf(f(value))
      }
    }
    //    val a = treeFunctor.map(Branch(Leaf(10), Leaf(20)))(_ * 2)
    //    println(a)
    val v: Tree[Int] = Branch(Leaf(10), Leaf(20))
    Tree.leaf(100).map(_ * 2)
    Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2)


}
