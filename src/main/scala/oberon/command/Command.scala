package oberon.command

import oberon.Environment._
import oberon.expression._

trait Command {
  def run() : Unit 
}

case class EmptyCommand() extends Command {

  override
  def run() : Unit = {
    
  }
}

class BlockCommand(val cmds: List[Command]) extends Command {

  override
  def run() : Unit = {
    cmds.foreach(c => c.run())
  }
}

class Assignment(val id: String, val expression: Expression) extends Command {

  override
  def run() : Unit = {
    map(id, expression.eval())
  }

}

class Declaration(val id: String) extends Command {

  override
  def run() : Unit = {
    map(id, Undefined())
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
}

class Print(val expression: Expression) extends Command {
  
  override
  def run() : Unit = {
    print(expression.eval())
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
}

class ReadBool() extends Command {

  override
  def run() : Unit = {
    scala.io.StdIn.readBoolean()
  }
}
