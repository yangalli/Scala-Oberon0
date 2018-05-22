package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

class func(nome: String, args: List[Expression]) extends Expression {
  
  override
  def eval() : Value = {
    
    val define = lookupDef(nome)

    push()
    args.foreach(a => new Assignment(define.args(args.indexOf(a)), a).run())
    define.command.run()

    /* val res = define.command.tail().eval
    pop()
    res */

    var res: Value = Undefined()
    define.command match {
      case Return(c) => res = c.eval()
      case c => c.run()
    }

    pop
    res

  }
}