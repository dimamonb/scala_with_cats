package meet_cats

import cats.Show

import java.util.Date

object MeetCats {

}
object DateShowInstance {
  implicit val dateShow = new Show[Date] {
    override def show(t: Date): String = s"${t.getTime}ms since the epoch."
  }
}

object Main extends App {
  val showInt: Show[Int] = Show.apply[Int]
  println(showInt.show(1234))
  import cats.syntax.show._
  val showIntSx = "456".show
  println(showIntSx)

  import  DateShowInstance._
  println(new Date().show)
}
