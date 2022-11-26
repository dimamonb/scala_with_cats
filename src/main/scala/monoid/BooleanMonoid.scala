package monoid

object BooleanMonoid {

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

  val booleanAndMonoid = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  val booleanOrMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  val booleanNotMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = ???
  }

}
