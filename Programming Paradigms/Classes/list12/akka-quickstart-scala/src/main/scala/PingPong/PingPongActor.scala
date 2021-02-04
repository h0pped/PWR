package PingPong

import akka.actor.{Actor, ActorSystem, Props}

import scala.util.Random
case class Ball(player:Boolean,scoreA:Int,scoreB:Int) //true = first player, false = second
case class Win(scoreA:Int)


class PingPongActor extends Actor{
 val POINTS_TO_WIN = 10; //const
  override def receive: Receive = {
    case Ball(player,scoreA,scoreB)=>{
      println(scoreA+" - "+scoreB)
      sender ! (if (scoreA== POINTS_TO_WIN || scoreB == POINTS_TO_WIN) Win(scoreA) else move(player, scoreA, scoreB))
    }
    case Win(scoreA) => if(scoreA==POINTS_TO_WIN) println("Player 1 is a winner!") else println("Player 2 is a winner!")
    case _ => println("Error")
  }
  def move(player: Boolean, scoreA: Int, scoreB: Int): Ball = {
    val successfulHit = Random.nextDouble()>0.5; // 50/50

    val firstScoreNew = (player, scoreA, successfulHit) match {
      case (true, num, true) => num + 1
      case(false, num, false) => num + 1
      case _ => scoreA
    }

    val secondScoreNew = (player, scoreB, successfulHit) match {
      case (false, num, true) => num + 1
      case(true, num, false) => num + 1
      case _ => scoreB
    }

    Ball(!player, firstScoreNew, secondScoreNew) //second player hit
  }
}

object Main extends App {
  val system = ActorSystem("PingPong")

  val player1 = system.actorOf(Props[PingPongActor], name="Player1")
  val player2 = system.actorOf(Props[PingPongActor], name="Player2")


  player1.tell(Ball(false, 0, 0), player2);
}
