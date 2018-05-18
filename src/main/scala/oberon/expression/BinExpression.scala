package oberon.expression

abstract class BinExpression(val lhs: Expression, val rhs: Expression) extends Expression {

}

class AddExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return IntValue(v1 + v2)
  }
}

class MultExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return IntValue(v1 * v2)
  }
}

class LeExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 <= v2) 
  }

}
class EqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval()
    val v2 = rhs.eval()

    return BoolValue(v1 == v2) 
  }

}

class GtExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 > v2) 
  }

}

class LtExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 < v2) 
  }
}

class SubtractExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return new IntValue(v1 - v2)
  }

}

class AndExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[BoolValue].value
    val v2 = rhs.eval().asInstanceOf[BoolValue].value

    return BoolValue(v1 && v2) 
  }

}

class NotExpression(expression: Expression) extends Expression {

  override
  def eval() : Value = {
    val val1 = expression.eval().asInstanceOf[BoolValue].value
    
    return BoolValue(!val1) 
  }

}

class NotEqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval()
    val v2 = rhs.eval()

    return new NotExpression(new EqExpression(v1, v2)).eval()
  }

}

class DivideExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return new IntValue(v1 / v2)
  }

}
