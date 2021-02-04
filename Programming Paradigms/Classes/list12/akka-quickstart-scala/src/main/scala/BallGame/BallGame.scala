package BallGame

import akka.actor.{Actor, ActorSystem, Props}

import scala.util.Random

case class Ball(count: Int)

 class Helper(){
   def randomArrElement[T](array: Array[T], element: T) = { //element - element which was chosen previously
    if(array.length == 0 && array(0) == element) {
      throw new Exception("arr is empty >.<")
    }
    var rand = new Random;
    var randEl = array(rand.nextInt(array.length))

    while(randEl == element) { //while random is not on the our side
      randEl = array(rand.nextInt(array.length))
    }
    randEl
  }

}

class BallGame extends Actor{
  def randomActor(currentActor: String) = {
    val helper = new Helper();
    val Actorsid = Array("1", "2", "3")
    context.actorSelection(String.format("../%s", helper.randomArrElement(Actorsid, currentActor)))
  }
  override def receive: Receive = {
    case Ball(count) => {
      val helper = new Helper();

      val otherActor = randomActor(self.path.name)
      println(self.path.name + " -----> "+ otherActor.pathString.split('/').last + ", COUNT: ",count);
      Thread.sleep(500);
      otherActor.tell(Ball(count + 1), self)
    }
    case _ => println("Unknown message.")
  }
}
object Main extends App {
  val system = ActorSystem("BallGameSystem")

  val actors = (1 to 3).map(i => system.actorOf(Props[BallGame], name=i.toString())).toArray

  actors(0) ! Ball(0)
}