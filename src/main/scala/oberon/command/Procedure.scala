package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

class proc(nome: String, args: List[Expression]) extends Command {
  
  override
  def run() : Unit = {
    
    val defineProc = lookupProc(nome)

    push()
    args.foreach(a => new Assignment(defineProc.args(args.indexOf(a)), a).run()) //for
    defineProc.command.run()
    println(lookup("res"))
    pop()

  }
}