case class Person(firstName:String,lastName:String) extends Ordered[Person]{
  override def compare(that: Person) = {
    if (this.firstName == that.firstName)
      this.lastName compare that.lastName
    else this.firstName compare that.firstName
  }
}


val notordered = List(Person("Illia", "Nykonchuk"), Person("Kent", "NoKent"), Person("AAA", "AAA"))

println(notordered.sorted)