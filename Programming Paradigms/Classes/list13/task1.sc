
object Cat {
  def makeNoise() = {
    "MEOW"
  }
}
object Dog {
  def makeNoise() = {
    "WOOF"
  }
}

def noise(animal: {def makeNoise(): String}): Unit = {
  println(animal.makeNoise())
}

noise(Dog)
noise(Cat)