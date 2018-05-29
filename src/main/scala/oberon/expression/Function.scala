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
    //Executa todos os comandos
    define.command.run()

    //procura o return
    var res: Value = Undefined()
    define.command match {
      case Return(e) => res = e.eval()
      case c: BlockCommand => res = c.cmds.last.asInstanceOf[Return].expression.eval()
    }

    pop
    res

  }
}