package oberon

import oberon.command._
import oberon.Environment._
import oberon.expression.Type

import oberon.visitor.Visitable
import oberon.visitor.Visitor

class FuncDef(val tipo: Type, val nome: String, val args: List[(String, Type)], val command: Command) extends Visitable {
  // defines the scope of the function
  define += (nome -> this)

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}