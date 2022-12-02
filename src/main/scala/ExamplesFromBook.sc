import cats.data.Writer
import cats.syntax.applicative._
import cats.instances.function._
import cats.syntax.functor._
import cats.{Eval, Functor, Id, Monad}


val func = (x: Int) => x + 1
val liftedFunc = Functor[Option].lift(func)
liftedFunc(Option(1))

val list1 = List(1, 2, 3)
val list2 = Functor[List].map(list1)(_ * 2)

Functor[List].as(list1, "As")

val func1 = (a: Int) => a + 1
val func2 = (a: Int) => a * 2
val func3 = (a: Int) => s"${a}!"
val func4 = func1.map(func2).map(func3)

func4(123)

val opt1 = Monad[Option].pure(3)
val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
val opt3 = Monad[Option].map(opt2)(a => 100 * a)

"Dave": Id[String]

val now = Eval.now(math.random + 1000)
val always = Eval.always(math.random() + 3000)
val later = Eval.later(math.random() + 2000)

def factorial(n: BigInt): Eval[BigInt] =
  if(n == 1) {
    Eval.now(n)
  } else {
    Eval.defer(factorial(n-1).map(_ * n))//n * factorial(n - 1)
  }

//factorial(50000).value

Writer(Vector(
  "It was the best of times",
  "it was the worst of times"
), 1859)

type Logged[A] = Writer[Vector[String], A]
123.pure[Logged]