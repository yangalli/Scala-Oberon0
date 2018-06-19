package oberon.visitor

import oberon.expression._
import oberon.command._
import oberon.OberonProgram
import oberon.Proc
import oberon.ProcDef
import oberon.Func
import oberon.FuncDef
import oberon.Environment._

class SimpleRefactor extends Visitor {

  var str = ""
  val pp = new PrettyPrinter()

  def visit(e: Undefined)          : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: IntValue)           : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: BoolValue)          : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: AddExpression)      : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: MultExpression)     : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: ModExpression)      : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: SubtractExpression) : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: DivideExpression)   : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: LeExpression)       : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: LtExpression)       : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: GeExpression)       : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: GtExpression)       : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: EqExpression)       : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: NotEqExpression)    : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: AndExpression)      : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: OrExpression)       : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: NotExpression)      : Unit = { e.accept(pp); str = pp.str;}
  def visit(e: VarRef)             : Unit = { e.accept(pp); str = pp.str;} 

  def visit(e: Func)               : Unit = { 
    val define = lookupDef(e.nome)
    var strArgs = ""
    val command = visitCommand(define.command)

    for(i <- 0 until define.args.size-1) {
      strArgs += define.args(i)._1 + ","
    }
    strArgs += define.args(define.args.size-1)._1

    str = "function" + define.nome + "(" + strArgs + ")" + "{" + command + "}"
  }

  def visit(d: FuncDef)            : Unit = { 
    var strArgs = ""
    val command = visitCommand(d.command)

    for(i <- 0 until d.args.size-1) {
      strArgs += d.args(i)._1 + ","
    }
    strArgs += d.args(d.args.size-1)._1

    str = "function" + d.nome + "(" + strArgs + ")" + "{" + command + "}"
  }

  def visit(c: BlockCommand)  : Unit = { 
    c.cmds.foreach(cmd => str += visitCommand(cmd) + "\n")
  }

  def visit(c: Assignment)    : Unit = {
    c.accept(pp)
    str = pp.str
  }

  def visit(c: While)         : Unit = {
    //c.cond.accept(pp)
    val cond = visitExp(c.cond)
    val cmds = visitCommand(c.command)
    
    str = "while" + cond + "\n" + "{"  + cmds + "}"
  }

  def visit(c: Print)         : Unit = ???

  def visit(c: OberonProgram) : Unit = ???

  def visit(c: Return)        : Unit = {
    val exp = visitExp(c.expression)
    str = "return " + exp
  }

  def visit(c: IfThen)        : Unit = {
    val cond = visitExp(c.cond.asInstanceOf[BinExpression])
    val command = visitCommand(c.command)

    str = "if" +  cond  + "{" + command + "}"
  }

  def visit(c: IfThenElse)    : Unit = {
    
    c.command1 match {
      case Return(x: BoolValue) => c.command2 match {
        case Return(y: BoolValue) => if(x.value == !y.value ){
          //str = Return(c.cond).toString
          Return(c.cond).accept(pp)
          str = pp.str
        } else {
          c.accept(pp)
          str = pp.str
        }
      }
      case _ => {
        c.accept(pp)
        str = pp.str
      }
    }
  }

  def visit(c: Proc)          : Unit = {
    val define = lookupProc(c.nome)
    var strArgs = ""
    val command = visitCommand(define.command)

    for(i <- 0 until define.args.size-1) {
      strArgs += define.args(i)._1 + ","
    }
    strArgs += define.args(define.args.size-1)._1

    str = "function" + define.nome + "(" + strArgs + ")" + "{" + command + "}"
  }

  def visit(d: ProcDef)       : Unit = {
    var strArgs = ""
    val command = visitCommand(d.command)

    for(i <- 0 until d.args.size-1) {
      strArgs += d.args(i)._1 + ","
    }
    strArgs += d.args(d.args.size-1)._1

    str = "procedure" + d.nome + "(" + strArgs + ")" + "{" + command + "}"
  }
  
  private def visitExp(e: Expression): String = {
    e.accept(this)
    val exp = str

    return exp 
  }

  private def visitCommand(c: Command): String = {
    c.accept(this)
    val cmd = str

    return cmd 
  }
}