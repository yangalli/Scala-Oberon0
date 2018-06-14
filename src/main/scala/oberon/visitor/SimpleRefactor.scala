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

  def visit(e: Undefined)          : Unit = ???
  def visit(e: IntValue)           : Unit = ???
  def visit(e: BoolValue)          : Unit = ???
  def visit(e: AddExpression)      : Unit = ???
  def visit(e: MultExpression)     : Unit = ???
  def visit(e: ModExpression)      : Unit = ???
  def visit(e: SubtractExpression) : Unit = ???
  def visit(e: DivideExpression)   : Unit = ???
  def visit(e: LeExpression)       : Unit = ???
  def visit(e: LtExpression)       : Unit = ???
  def visit(e: GeExpression)       : Unit = ???
  def visit(e: GtExpression)       : Unit = ???
  def visit(e: EqExpression)       : Unit = ???
  def visit(e: NotEqExpression)    : Unit = ???
  def visit(e: AndExpression)      : Unit = ???
  def visit(e: OrExpression)       : Unit = ???
  def visit(e: NotExpression)      : Unit = ???
  def visit(e: VarRef)             : Unit = ??? 
  def visit(e: Func)               : Unit = ???
  def visit(e: FuncDef)            : Unit = ???

  def visit(c: BlockCommand)  : Unit = ???
  def visit(c: Assignment)    : Unit = ???
  def visit(c: While)         : Unit = ???
  def visit(c: Print)         : Unit = ???
  def visit(c: OberonProgram) : Unit = ???
  def visit(c: Return)        : Unit = ???
  def visit(c: IfThen)        : Unit = ???
  def visit(c: IfThenElse)    : Unit = {
    c.command1 match {
      case Return(x: BoolValue) => c.command2 match {
        case Return(y: BoolValue) => if(x.value == !y.value ) str = Return(c.cond).toString
      }
    }
  }
  def visit(c: Proc)          : Unit = ???
  def visit(c: ProcDef)       : Unit = ???
}