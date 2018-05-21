package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

class func(nome: String, args: List[Expression]) extends Expression {
  
  override
  def eval() : Value = {
    
    val define = lookupDef(nome)

    args.foreach(a => new Assignment(define.args(args.indexOf(a)), a).run())

    define.command match {
      case Some(c) => c.run()
      case None => { }
    }

    define._return.eval()
  }
}