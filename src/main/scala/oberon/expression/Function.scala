package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

class func(nome: String, args: List[Expression]) extends Expression {

  override
  def eval() : Value = {

    // defines the scope by the function name
    val define = lookupDef(nome)

    push()
    // assign all arguments in the function and run all commands
    args.foreach(a => new Assignment(define.args(args.indexOf(a)), a).run())
    // executes all commands
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
}