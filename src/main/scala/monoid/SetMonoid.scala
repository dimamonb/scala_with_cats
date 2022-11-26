package monoid

object SetMonoid {

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }
  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }
  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) =
      monoid
  }

  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
     def empty: Set[A] = Set.empty[A]

     def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
  }

}
