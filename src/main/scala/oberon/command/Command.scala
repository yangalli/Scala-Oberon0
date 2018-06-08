package oberon.command

import oberon.visitor.Visitable
import oberon.visitor.Visitor

import oberon.Environment._
import oberon.expression._

trait Command extends Visitable {
  def run() : Unit
  def tc()  : Boolean  // a type checker for commands. 
}

case class Return(val expression: Expression) extends Command {

  override
  def run() : Unit = { }

  // o tc deve ser feito com base no tipo de saida da funcao
  override
  def tc() : Boolean = expression.typeCheck()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
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
      case BoolValue(true) => { command.run(); run(); }
      case _               => { } 
    }
  }

  def tc() : Boolean = cond.calculateType() == TBool() && command.tc()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class Print(val expression: Expression) extends Command {
  
  override
  def run() : Unit = {
    print(expression.eval())
  }

  override
  def tc() : Boolean = true

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class IfThen(val cond: Expression, val command: Command) extends Command {
  
  override
  def run() : Unit = {
    val v = cond.eval().asInstanceOf[BoolValue]

    v match {
      case BoolValue(true) => command.run()
      case BoolValue(false) => { }
    }
  }

  def tc() : Boolean = cond.calculateType() == TBool() && command.tc()

  override def accept(v : Visitor) {
    v.visit(this) 
  }  
}

class IfThenElse(val cond: Expression, val command1: Command, val command2: Command) extends Command {
  
  override
  def run() : Unit = {
    val v = cond.eval().asInstanceOf[BoolValue]

    v match {
      case BoolValue(true) => command1.run()
      case BoolValue(false) => command2.run()
    }
  }

  def tc() : Boolean = cond.calculateType() == TBool() && command1.tc() && command2.tc()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}