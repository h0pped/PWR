package Welcome


import akka.actor.{Actor, ActorSystem, Props}

class WelcomeActor extends Actor{
  override def receive: Receive ={
    case "Anna"=> println("Hi Anna!");
    case "Tom"=> println("Hi Tom!");
    case "Max"=> println("Hi Max!");
    case _ => println("Hi anon!");
  }
}

object Main extends App {
  val system = ActorSystem("ActorSystem")

  val welcomeActor = system.actorOf(Props[WelcomeActor], name="Welcome.WelcomeActor")

  welcomeActor ! "Anna"
  welcomeActor ! "Tom"
  welcomeActor ! "KKKKK"
  welcomeActor ! "unknown"

  Thread.sleep(5000);

  system.terminate();
}
