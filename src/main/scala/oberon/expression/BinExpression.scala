package oberon.expression

import oberon.visitor.Visitor

abstract class BinExpression(val lhs: Expression, val rhs: Expression) extends Expression { }

class AddExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return IntValue(v1 + v2)
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

class MultExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return IntValue(v1 * v2)
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

class ModExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return IntValue(v1 % v2)
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

class GeExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 >= v2) 
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

class EqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
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

class GtExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 > v2) 
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

class LtExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return BoolValue(v1 < v2) 
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

class SubtractExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return new IntValue(v1 - v2)
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

class AndExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[BoolValue].value
    val v2 = rhs.eval().asInstanceOf[BoolValue].value

    return BoolValue(v1 && v2) 
  }

  override
  def calculateType() : Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TBool() && t2 == TBool()) TBool() else TUndefined()
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}

class OrExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[BoolValue].value
    val v2 = rhs.eval().asInstanceOf[BoolValue].value

    return BoolValue(v1 || v2) 
  }

  override
  def calculateType() : Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TBool() && t2 == TBool()) TBool() else TUndefined()
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}

class NotExpression(val expression: Expression) extends Expression {

  override
  def eval() : Value = {
    val val1 = expression.eval().asInstanceOf[BoolValue].value
    
    return BoolValue(!val1) 
  }

  override
  def calculateType() : Type = {
    val t = expression.calculateType()

    return if(t == TBool()) TBool() else TUndefined()
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}

class NotEqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval()
    val v2 = rhs.eval()

    return new NotExpression(new EqExpression(v1, v2)).eval()
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

class DivideExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval().asInstanceOf[IntValue].value
    val v2 = rhs.eval().asInstanceOf[IntValue].value

    return new IntValue(v1 / v2)
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
