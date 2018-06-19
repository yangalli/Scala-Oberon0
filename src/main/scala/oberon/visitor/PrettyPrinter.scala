package oberon.visitor

import oberon.expression._
import oberon.command._
import oberon.OberonProgram
import oberon.Proc
import oberon.ProcDef
import oberon.Func
import oberon.FuncDef
import oberon.Environment._


class PrettyPrinter extends Visitor {
  var str = ""

  def visit(e: Undefined)     : Unit = { str = "undefined" } 

  def visit(e: IntValue)      : Unit = { str = e.value.toString }

  def visit(e: BoolValue)     : Unit = { str = e.value.toString }

  def visit(e: AddExpression) : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " + " + r + ")" 
  }

  def visit(e: SubtractExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " - " + r + ")"
  }

  def visit(e: LeExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " <= " + r + ")" 
  }

  def visit(e: EqExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " == " + r + ")" 
  }
  
  def visit(e: OrExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " || " + r + ")"
  }

  def visit(e: AndExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " && " + r + ")"
  }
  def visit(e: NotEqExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " != " + r + ")"
  }

  def visit(e: GtExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " > " + r + ")"
  }

  def visit(e: GeExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " >= " + r + ")"
  }

  def visit(e: LtExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " < " + r + ")"
  }

  def visit(e: DivideExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " / " + r + ")"
  }
  
  def visit(e: ModExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " % " + r + ")"
  }
  
  def visit(e: MultExpression): Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " * " + r + ")"
  }
  

  def visit(e: NotExpression): Unit = {
    val exp = visitExp(e.expression)
    str = "!" + exp
  }
  

  def visit(e: VarRef): Unit = { 
    str = e.id
    // colocar só o nome
  }

  def visit(c: BlockCommand)  : Unit = { 
    c.cmds.foreach(cmd => str += visitCommand(cmd) + "\n")
  }

  def visit(c: Assignment): Unit = { 
    val exp = visitExp(c.expression)
    str = "var " + c.id + " = " + exp
  } 

  def visit(c: While): Unit = { 
    val cond = visitExp(c.cond)
    val cmds = visitCommand(c.command)
    
    str = "while" + cond + "\n" + "{"  + cmds + "}" 
  }

  def visit(c: IfThen): Unit = {
    val cond = visitExp(c.cond.asInstanceOf[BinExpression])
    val command = visitCommand(c.command)

    str = "if" +  cond  + "{" + command + "}"
  }

  def visit(c: IfThenElse): Unit = {
    val cond = visitExp(c.cond.asInstanceOf[BinExpression])
    val command1 = visitCommand(c.command1)
    val command2 = visitCommand(c.command2)

    str = "if" + cond + "{" + command1 + "}" + "\n" + "else" + "{" + command2 + "}"
  }


  def visit(c: Return): Unit = {
    val exp = visitExp(c.expression)
    str = "return " + exp
  }

  def visit(d: FuncDef): Unit = {
    
    var strArgs = ""
    val command = visitCommand(d.command)

    for(i <- 0 until d.args.size-1) {
      strArgs += d.args(i)._1 + ","
    }
    strArgs += d.args(d.args.size-1)._1

    str = "function " + d.nome + "(" + strArgs + ")" + "{" + command + "}" 
  }

  def visit(e: Func): Unit = {
    val define = lookupDef(e.nome)
    var strArgs = ""
    val command = visitCommand(define.command)

    for(i <- 0 until define.args.size-1) {
      strArgs += define.args(i)._1 + ","
    }
    strArgs += define.args(define.args.size-1)._1

    str = "function" + define.nome + "(" + strArgs + ")" + "{" + command + "}"
  }

  def visit(c: Proc): Unit = {
    //igual ao procDef
    val define = lookupProc(c.nome)
    var strArgs = ""
    val command = visitCommand(define.command)

    for(i <- 0 until define.args.size-1) {
      strArgs += define.args(i)._1 + ","
    }
    strArgs += define.args(define.args.size-1)._1

    str = "function" + define.nome + "(" + strArgs + ")" + "{" + command + "}"
  }

  def visit(d: ProcDef): Unit = {
    var strArgs = ""
    val command = visitCommand(d.command)

    for(i <- 0 until d.args.size-1) {
      strArgs += d.args(i)._1 + ","
    }
    strArgs += d.args(d.args.size-1)._1

    str = "procedure " + d.nome + "(" + strArgs + ")" + "{" + command + "}"
  }


  def visit(c: Print): Unit = ???


  def visit(c: OberonProgram): Unit = {
    var strArgs = ""
    val command = visitCommand(c.cmd)

    for(i <- 0 until c.variables.size) {
      visit(c.variables(i)) 
      strArgs += str + "\n"
    }

    for(i <- 0 until c.procDef.size) {
    visit(c.procDef(i))
      strArgs += str + "\n"
    }

    for(i <- 0 until c.funcDef.size) {
      visit(c.funcDef(i))
      strArgs += str + "\n"
    }

    str = "OberonProgram\n" + strArgs + command
  }

  def visit(c: Declaration): Unit = {
    str = "var " + c.id
  }

  private def visitBinExp(e: BinExpression) : (String, String) = {
    e.lhs.accept(this)
    val l = str

    e.rhs.accept(this)
    val r = str

    return (l, r) 
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
