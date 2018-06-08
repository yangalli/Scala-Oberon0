/* import scala.annotation._
import scala.meta._

@compileTimeOnly("Should be used only in compile time.")
class Memoize extends StaticAnnotation {

  inline def apply(defn: Any): Any = meta {
    defn match {
      case q"def $name(..$paramss): $result = { ..$body }" => ???

      case _ => abort("@Memoize must annotate an function.")
    }
  }

}
 */

/* q"class $nameExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {

    val v1 = lhs.eval().asInstanceOf[$valueType].value
    val v2 = rhs.eval().asInstanceOf[$valueType].value

    return $valueReturn(v1 $operator v2)
  }
}" */