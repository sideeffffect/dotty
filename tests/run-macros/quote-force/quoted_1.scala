import scala.quoted._
import scala.quoted.autolift._

case class Location(owners: List[String])

object Location {

  implicit inline def location: Location = ${impl}

  def impl given QuoteContext: Expr[Location] = {
    val list = List("a", "b", "c", "d", "e", "f")
    '{new Location(${list})}
  }

  private implicit def ListIsLiftable[T : Liftable : Type]: Liftable[List[T]] = new Liftable[List[T]] {
    def toExpr(x: List[T]) given QuoteContext: Expr[List[T]] = x match {
      case x :: xs  => '{ ${x} :: ${xs} }
      case Nil => '{ List.empty[T] }
    }
  }
}
