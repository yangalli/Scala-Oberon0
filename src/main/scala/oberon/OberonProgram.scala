package oberon

import oberon.visitor.Visitor

import oberon.command.Command

class OberonProgram(val cmd: Command) extends Command {

  override
  def run() : Unit = {
    cmd.run()
  }

  override
  def tc() : Boolean = cmd.tc()

  override def accept(v : Visitor) {
    v.visit(this) 
  }

}
