package oberon

import oberon.visitor.Visitable
import oberon.visitor.Visitor

import oberon.command._
import oberon.expression._
import oberon.Environment._

class Func(nome: String, args: List[Expression]) extends Expression {

  override
  def eval() : Value = {

    // defines the scope by the function name
    val define = lookupDef(nome)

    push()
    //args.foreach(a._1 => new Assignment(define.args(args.indexOf(a)), a).run())
    for (i <- 0 until args.size) {

      val(variable, tipo) = define.args(i)

      new Assignment(variable, args(i)).run()
    }

    //Executa todos os comandos
    define.command.run()

    // search for return
    var res: Value = Undefined()
    define.command match {
      // if there is a Return in the scope, then it's expression is evaluated
      case Return(e) => res = e.eval()
      // if there is a BlockCommmand in the scope of the function, the Return is the last command
      // in the Stack pile, and it's expression is evaluated as an instance of Return
      case c: BlockCommand => res = c.cmds.last.asInstanceOf[Return].expression.eval()
    }

    pop
    res

  }
  
  override
  def calculateType() : Type = ???

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}