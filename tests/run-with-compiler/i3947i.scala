
import scala.quoted._

object Test {

  def main(args: Array[String]): Unit = {
    implicit val toolbox: scala.quoted.Toolbox = scala.quoted.Toolbox.make(getClass.getClassLoader)
    def test[T: Type](clazz: java.lang.Class[T]): Unit = run {
      val lclazz = clazz.toExpr
      val name = '{ ($lclazz).getCanonicalName }
      println()
      println(name.show)
      '{ println($name) }
    }

    // primitive arrays
    test(classOf[Array[Int]])
    test(classOf[Array[Long]])
    test(classOf[Array[Float]])
    test(classOf[Array[Double]])
  }

}
