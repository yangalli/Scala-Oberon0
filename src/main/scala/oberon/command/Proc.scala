package oberon

import oberon.visitor.Visitable
import oberon.visitor.Visitor

import oberon.command._
import oberon.expression._
import oberon.Environment._

class Proc(val nome: String, val args: List[Expression]) extends Command {
  
  override
  def run() : Unit = {
    
    val defineProc = lookupProc(nome)
  
    push()
    for (i <- 0 until args.size) {

      val(variable, tipo) = defineProc.args(i)

      new Assignment(variable, args(i)).run()
    }
    //args.foreach(a => new Assignment(defineProc.args(args.indexOf(a)), a).run()) //for
    defineProc.command.run()
    pop()

  }
  override
    def typeCheck() : Boolean = {

    // defines the scope by the function name
    val defineProc = lookupDef(nome)

    for (i <- 0 until args.size) {
      val(variable, tipo) = defineProc.args(i)
      if (tipo != args(i).calculateType()) false
      //(variable, args(i)).run()
    }
    true
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}