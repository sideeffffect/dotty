import scala.quoted._

object Test {

  def main(args: Array[String]): Unit = {
    implicit val toolbox: scala.quoted.Toolbox = scala.quoted.Toolbox.make(getClass.getClassLoader)

    '[List]
    val list = bound('{List(1, 2, 3)})
    println(withQuoteContext(list.show))
    println(run(list))

    val opt = bound('{Option(4)})
    println(withQuoteContext(opt.show))
    println(run(opt))

    val map = bound('{Map(4 -> 1)})
    println(withQuoteContext(map.show))
    println(run(map))
  }

  def bound[T: Type, S[_]: Type](x: Expr[S[T]]): Expr[S[T]] = '{
    val y = $x
    y
  }
}
