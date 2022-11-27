import cats.Functor
import cats.instances.function._
import cats.syntax.functor._
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