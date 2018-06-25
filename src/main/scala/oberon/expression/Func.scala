package oberon

import oberon.visitor.Visitable
import oberon.visitor.Visitor

import oberon.command._
import oberon.expression._
import oberon.Environment._

class Func(val nome: String, val args: List[Expression]) extends Expression {

  override
  def eval() : Value = {

    if(!typeCheck()) throw Error("Bad type error")

    // defines the scope by the function name
    val define = lookupDef(nome)

    push()

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
  def calculateType() : Type = {

    push

    // defines the scope by the function name
    val define = lookupDef(nome)

    for (i <- 0 until args.size) {
      val(variable, tipo) = define.args(i)
      if (tipo != args(i).calculateType()) return TUndefined()

    }

    for (i <- 0 until args.size) {

      val(variable, tipo) = define.args(i)

      new Assignment(variable, args(i)).run()
    }
    
    var res: Type = TUndefined()
    define.command match {
      // if there is a Return in the scope, then it's expression is evaluated
      case e: Return => {
       if (e.expression.calculateType() != define.tipo) res = TUndefined()
       else res = e.expression.calculateType()
      } 
      // if there is a BlockCommmand in the scope of the function, the Return is the last command
      // in the Stack pile, and it's expression is evaluated as an instance of Return
      case c: BlockCommand => {
        if (c.cmds.last.asInstanceOf[Return].expression.calculateType() != define.tipo) res = TUndefined()
        else res = c.cmds.last.asInstanceOf[Return].expression.calculateType()
      }
    }

    pop
    res
  }

  override def accept(v : Visitor) {
    v.visit(this) 
  }
}