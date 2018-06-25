package oberon.command

import oberon.Error
import oberon.visitor.Visitable
import oberon.visitor.Visitor

import oberon.Environment._
import oberon.expression._

trait Command extends Visitable {
  def run() : Unit
  def typeCheck() : Boolean 
}

case class Return(val expression: Expression) extends Command {

  override
  def run() : Unit = { 
    if(!typeCheck()) throw Error("Bad type error")
  }

  // o typeCheck deve ser feito com base no tipo de saida da funcao
  override
  def typeCheck() : Boolean = expression.typeCheck()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class BlockCommand(val cmds: List[Command]) extends Command {

  override
  def run() : Unit = {
    if(!typeCheck()) throw Error("Bad type error")
    cmds.foreach(c => c.run())
  }

  override
  def typeCheck() : Boolean = cmds.forall(c => c.typeCheck())

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class Assignment(val id: String, val expression: Expression) extends Command {

  override
  def run() : Unit = {
    if(!typeCheck()) throw Error("Bad type error")
    map(id, expression.eval())
  }

  override
  def typeCheck() : Boolean = expression.typeCheck()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}

class While(val cond: Expression, val command: Command) extends Command {
  
  override
  def run() : Unit = {
    if(!typeCheck()) throw Error("Bad type error")
    val v = cond.eval.asInstanceOf[BoolValue]

    v match {
      case BoolValue(true) => { command.run(); run(); }
      case _               => { } 
    }
  }

  def typeCheck() : Boolean = cond.calculateType() == TBool() && command.typeCheck()

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
  def typeCheck() : Boolean = true

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

  def typeCheck() : Boolean = cond.calculateType() == TBool() && command.typeCheck()

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

  def typeCheck() : Boolean = cond.calculateType() == TBool() && command1.typeCheck() && command2.typeCheck()

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}