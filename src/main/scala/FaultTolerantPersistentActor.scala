import akka.actor._


object FaultTolerantPersistentActor extends App {

  val system = ActorSystem("akka-persistence")

  val persistentActor = system.actorOf(Props(classOf[OrderProcessorActor]), "oderProcessor-persistentActor")

  persistentActor ! "Bread"
  persistentActor ! PrintOrder
  persistentActor ! Bomb // restart and recovery
  persistentActor ! PrintOrder
  persistentActor ! "Egg"
  persistentActor ! PrintOrder
  persistentActor ! "Milk"
  persistentActor ! PrintOrder

  Thread.sleep(10000)
  system.terminate()

}

// Results at runtime:

// First Run
// received orders: List(Bread)
// java.lang.Exception: Bomb!
// received orders: List(Bread)
// received orders: List(Bread, Egg)
// received orders: List(Bread, Egg, Milk)

// Second Run
// received orders: List(Bread, Egg, Milk, Bread)
// received orders: List(Bread, Egg, Milk, Bread)
// received orders: List(Bread, Egg, Milk, Bread, Egg)
// received orders: List(Bread, Egg, Milk, Bread, Egg, Milk)


