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

  def visit(e: LeExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " <= " + r + ")" 
  }

  def visit(e: EqExpression)  : Unit = {
    val (l, r) = visitBinExp(e)
    str = "(" + l + " == " + r + ")" 
  }

  def visit(e: VarRef)        : Unit = { }  
  def visit(c: BlockCommand)  : Unit = { } 
  def visit(c: Assignment)    : Unit = { } 
  def visit(c: While)         : Unit = { } 
  def visit(c: Print)         : Unit = { } 
  def visit(c: OberonProgram) : Unit = { }  

  private def visitBinExp(e: BinExpression) : (String, String) = {
    e.lhs.accept(this)
    val l = str

    e.rhs.accept(this)
    val r = str

    return (l, r) 
  }

  def visit(c: IfThenElse): Unit = ???
  def visit(c: IfThen): Unit = ???
  def visit(c: Return): Unit = ???
  def visit(e: NotExpression): Unit = ???
  def visit(e: OrExpression): Unit = ???
  def visit(e: AndExpression): Unit = ???
  def visit(e: NotEqExpression): Unit = ???
  def visit(e: GtExpression): Unit = ???
  def visit(e: GeExpression): Unit = ???
  def visit(e: LtExpression): Unit = ???
  def visit(e: DivideExpression): Unit = ???
  def visit(e: SubtractExpression): Unit = ???
  def visit(e: ModExpression): Unit = ???
  def visit(e: MultExpression): Unit = ???
  def visit(e: Func): Unit = ???
  def visit(e: Proc): Unit = ???
}
