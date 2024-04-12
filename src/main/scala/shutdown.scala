import akka.actor.{Props,ActorSystem,Actor,PoisonPill}

case object Stop

class ShutDownActor extends Actor {
  override def receive: Receive = {
    case msg:String => println(s"$msg")
    case Stop => context.stop(self)
  }
}

object shutdown extends App {
  val actorSystem = ActorSystem("shutdownTest")
  val shoutDownActor1 = actorSystem.actorOf(Props[ShutDownActor](), "shutdownActor")
  shoutDownActor1!"Test"
  shoutDownActor1!PoisonPill // it shuts down the actor
  shoutDownActor1!"Test2"
}
