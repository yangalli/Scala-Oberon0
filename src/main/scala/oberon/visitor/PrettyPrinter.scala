package oberon.visitor

import oberon.expression._
import oberon.command._
import oberon.OberonProgram
import oberon.Proc
import oberon.Func


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
    val exp = visitExp(new VarRef(e.id).eval())
    str = e.id.toString + " = " + exp
  }

  def visit(c: BlockCommand)  : Unit = { 
    c.cmds.foreach(cmd => str += visitCommand(cmd) + " ")
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

  def visit(e: Func): Unit = ??? /* {
    val define = lookupDef(e.nome)
    str = "function" + define.nome + "(" + 
    for(i <- 0 until define.args.size-1) {
      define.args(i) + ","
    } + ")" + "{" +  + "}" 
  } */

  def visit(e: Proc): Unit = {
    
  }


  def visit(c: Print): Unit = ???


  def visit(c: OberonProgram): Unit = ???

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
