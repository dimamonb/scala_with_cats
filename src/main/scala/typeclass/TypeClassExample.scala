package typeclass

object TypeClassExample extends App {

  sealed trait Json
  final case class JsObject(get: Map[String, Json]) extends Json
  final case class JsString(get: String) extends Json
  final case class JsNumber(get: Double) extends Json
  final case object JsNull extends Json

  //Type class
  trait JsonWriter[A] {
    def write(value: A): Json
  }

  //Type class instances
  final case class Person(name: String, email: String)
  object JsonWriterInstances {
    implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
      override def write(value: String): Json = JsString(value)
    }

    implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
      override def write(value: Person): Json =
        JsObject(
          Map(
            "name" -> JsString(value.name),
            "email" -> JsString(value.email)
          )
        )
    }

    implicit val numberWriter: JsonWriter[Double] = new JsonWriter[Double] {
      override def write(value: Double): Json = JsNumber(value)
    }
  }

  //Use of Type class
  // 1. Interface object
  object Json {
    def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }

  import JsonWriterInstances._
  val iobjectUse = Json.toJson(Person("Dave", "dave@example.com"))
  println(iobjectUse)
  //*********************

  // 2. Interface Syntax
  object JsonSyntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
    }
  }

  import JsonWriterInstances._
  import JsonSyntax._
  val syntaxUse = Person("Max", "max@example.com").toJson
  println(syntaxUse)

  // 3. The implicitly Method
  import JsonWriterInstances._
  val implicityUse = implicitly[JsonWriter[Person]]
  val res = implicityUse.write(Person("Vera", "Vera@example.com"))
  println(res)
}
