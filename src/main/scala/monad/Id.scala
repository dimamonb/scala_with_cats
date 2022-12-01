package monad
import cats.Id

object MyId {

  def pure[A](a: A): Id[A] = a

  def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)


  def map[A, B](value: Id[A])(func: A => B): Id[B] = func(value)
}
