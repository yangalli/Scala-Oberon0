package oberon.command

import oberon.Environment._
import oberon.expression._

import oberon.visitor.Visitable
import oberon.visitor.Visitor

class Declaration(val id: String) extends Visitable {
  def run() : Unit = {
    map(id, Undefined())
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}