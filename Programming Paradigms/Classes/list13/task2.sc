class Foo {
  def method(input: String) = input
}

class Bar {
  def method(input: String) = input
}

val foo: {def method(input: String): String} = new Foo();
val bar: {def method(input: String): String} = new Bar();