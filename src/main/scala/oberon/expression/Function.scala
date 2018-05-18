package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

class func(nome: String, args: List[Expression]) extends Expression {
  override
  def eval() : Value = {
    //instancia: List expression da declaração -> chamada
    // executa os commandos
    //retorno
    //args = [Intvalue(2), Intvalue(3)]
    //define(nome).args = [(x, IntValue()), (y, IntValue())] tupla?

    val define = lookupDef(nome)

    args.foreach(a => new Assignment(define.args(args.indexOf(a)), a).run())

    define.command match {
      case Some(c) => c.run()
      case None => { }
    }

    define._return.eval()
  }
}