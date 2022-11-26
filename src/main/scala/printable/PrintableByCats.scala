package printable

import cats.Show
import printable.PrintableByCats.Cat

object PrintableByCats {

  final case class Cat(name: String, age: Int, color: String)


  object CatsHandmade {
    //    implicit val catPrint = new Show[Cat] {
    //      override def show(t: Cat): String =
    //        s"${t.name} is a ${t.age} year-old ${t.color} cat."
    //    }

    implicit val catPrint2 = Show.show[Cat] {
      t => s"${t.name} is a ${t.age} year-old ${t.color} cat."
    }
  }
}

object Main extends App {

  import cats.syntax.show._
  import printable.PrintableByCats.CatsHandmade._

  val cat = Cat("Vasia", 11, "white")
  println(cat.show)

}
