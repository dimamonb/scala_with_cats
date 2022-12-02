package monad

import cats.Eval

object EvalModad {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def foldRightSafe[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightSafe(tail, acc)(fn)))
      case Nil =>
        acc
    }

  def foldRightNew[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    foldRightSafe(as, Eval.now((acc)))((a,b) => b.map(fn(a,_)) )
  }.value


  def main(args: Array[String]): Unit = {

  }

}
