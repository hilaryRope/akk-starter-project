import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.*
import scala.language.postfixOps

object ActorSetup extends App {
  implicit val timeout: Timeout = Timeout(10 seconds)

  val actorSystem = ActorSystem("SampleSystem")
  val actor = actorSystem.actorOf(Props[AddActor](), "AddActor")
  val future = (actor ? 5).mapTo[Int]
  val sum = Await.result(future, 10 seconds)
  println(sum)

  val future2 = (actor ? 5).mapTo[Int]
  val sum2 = Await.result(future2, 10 seconds)
  println(sum2)

  val future3 = (actor ? 5).mapTo[Int]
  val sum3 = Await.result(future3, 10 seconds)
  println(sum3)

}

class AddActor extends Actor {
  var sum = 0
  override def receive:Receive = {
    case x: Int => sum = sum + x
      sender() ! sum
    case _ => println("Error! Bad Data!")
  }
}

