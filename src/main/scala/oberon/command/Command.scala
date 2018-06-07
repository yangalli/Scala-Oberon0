package oberon.command

import oberon.visitor.Visitable
import oberon.visitor.Visitor

import oberon.Environment._

import oberon.expression.Expression
import oberon.expression.BoolValue
import oberon.expression.TBool

trait Command extends Visitable {
  def run() : Unit
  def tc()  : Boolean  // a type checker for commands. 
}


class BlockCommand(val cmds: List[Command]) extends Command {
  override
  def run() : Unit = {
    cmds.foreach(c => c.run())
  }

  override
  def tc() : Boolean = cmds.forall(c => c.tc())

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class Assignment(val id: String, val expression: Expression) extends Command {

  override
  def run() : Unit = {
    map(id, expression.eval())
  }

  override
  def tc() : Boolean = expression.typeCheck()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class While(val cond: Expression, val command: Command) extends Command {
  override
  def run() : Unit = {
    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true) => { command.run(); this.run(); }
      case _               => { } 
    }
  }

  def tc() : Boolean = cond.calculateType() == TBool() && command.tc()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class Print(val exp: Expression) extends Command {
  override
  def run() : Unit = {
    print(exp.eval())
  }

  override
  def tc() : Boolean = true

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}
