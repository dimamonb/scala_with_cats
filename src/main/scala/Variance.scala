object Variance {

  class Box[A](var content: A)

  abstract class Animal {
    def name: String
  }
  case class Cat(name: String) extends Animal
  case class Dog(name: String) extends Animal

  def main(args: Array[String]): Unit = {

    val myAnimal: Animal = Cat("Felix")

    val myCatBox: Box[Cat] = new Box[Cat](Cat("Felix"))
    //val myAnimalBox: Box[Animal] = myCatBox - won't compile
//    val myAnimalBox: Box[Animal] = new Box[Dog](Dog("Fido"))
//    val myAnimal: Animal = myAnimalBox.content

//    myAnimalBox.content = Dog("Fido")

//    val myCat: Cat = myAnimalBox.content
  }

}
