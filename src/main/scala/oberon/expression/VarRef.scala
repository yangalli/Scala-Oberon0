package oberon.expression

import oberon.visitor.Visitor

import oberon.Environment._

class VarRef(val id: String) extends Expression {
  
  override
  def eval() : Value = lookup(id) match {
    case Some(v) => v
    case _       => Undefined()
  }

  override
  def calculateType() : Type = lookup(id) match {
    case Some(v) => v.calculateType()
    case _       => TUndefined()  
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}
