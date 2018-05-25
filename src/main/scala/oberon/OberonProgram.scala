package oberon

import oberon.command._

class OberonProgram(val variables: List[String], val procDef: List[ProcDef], val funcDef: List[FuncDef], val cmd: Command) extends Command {

  override
  def run() : Unit = {
    variables.foreach(v => new Declaration(v))
    //procDef.foreach(p => p.run())
    //funcDef.foreach(f => f.run())
    cmd.run()
  }

}
