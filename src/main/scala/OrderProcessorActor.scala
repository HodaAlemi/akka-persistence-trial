import akka.persistence._

class OrderProcessorActor extends PersistentActor {

  override def persistenceId = "order-processor"

  var ordersState: List[String] = Nil

  def updateState(item: String) = ordersState = item :: ordersState

  override def receiveCommand: Receive = {
    case PrintOrder => println(s"received orders: ${ordersState.reverse}")
    case Bomb  => throw new Exception("Bomb!")
    case item: String =>
      persist(item) { i => updateState(i) }

  }

  override def receiveRecover: Receive = {
    case ord: String =>
      updateState(ord)
  }
}


case object Bomb
case object PrintOrder
