package oberon.expression

import oberon.visitor.Visitor

abstract class BinExpression(val lhs: Expression, val rhs: Expression) extends Expression { }

class AddExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return new IntValue(v1.value + v2.value)
  }

  override
  def calculateType() : Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TInt() && t2 == TInt()) TInt() else TUndefined()
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class LeExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 <= v2) 
  }

  override
  def calculateType() : Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TInt() && t2 == TInt()) TBool() else TUndefined()
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}
class EqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 = lhs.eval()
    val v2 = rhs.eval()

    return BoolValue(v1 == v2)
  }

  override
  def calculateType() : Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}
