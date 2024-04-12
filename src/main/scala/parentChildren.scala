import akka.actor.{ActorSystem, Props, Actor}

case object CreateChild
case class Greet(msg:String)

class ChildActor extends Actor {
  def receive: Receive = {
    case Greet(msg:String) => println(s"My parent [${self.path.parent}] greeted me [${self.path}] with a message: $msg")

  }
}

class ParentActor extends Actor {
  def receive: Receive = {
    case CreateChild =>
      val child = context.actorOf(Props[ChildActor](), "child")
      child!Greet("Hello")
  }
}

object parentChildren extends App {
  val actorSystem = ActorSystem("ParentChild")
  val parent = actorSystem.actorOf(Props[ParentActor](), "Parent")
  parent!CreateChild
}


