import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props

class loginActor extends Actor {
  def receive:Receive = {
    case x: String =>
      if (x == "Bob") {
        context.become(isAuth)
      }
  }

  private def isAuth:Receive = {
    case x: String =>
     if (x == "Username"){
       println("Bob")
     }
     if (x == "Logout"){
       println("Logon Successful!")
       context.become(NotAuth)
     }
  }

  private def NotAuth: Receive = {
    case x: String =>
      if (x == "Bob"){
        context.become(isAuth)
      }
  }
}

object login extends App {
  val actorSystem = ActorSystem("loginSys")
  val actor = actorSystem.actorOf(Props[loginActor](), "LoginActor")

  actor!"Bob"
  actor!"Username"
  actor!"Logout"
}
