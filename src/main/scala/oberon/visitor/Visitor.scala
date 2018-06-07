package oberon.visitor

import oberon.expression.Undefined
import oberon.expression.IntValue
import oberon.expression.BoolValue
import oberon.expression.AddExpression
import oberon.expression.LeExpression
import oberon.expression.EqExpression
import oberon.expression.VarRef 
import oberon.command.BlockCommand
import oberon.command.Assignment
import oberon.command.While
import oberon.command.Print
import oberon.OberonProgram


trait Visitable {
  def accept(v : Visitor) : Unit 
}

trait Visitor {
  def visit(e: Undefined)     : Unit
  def visit(e: IntValue)      : Unit
  def visit(e: BoolValue)     : Unit
  def visit(e: AddExpression) : Unit
  def visit(e: LeExpression)  : Unit
  def visit(e: EqExpression)  : Unit
  def visit(e: VarRef)        : Unit 
  def visit(c: BlockCommand)  : Unit
  def visit(c: Assignment)    : Unit
  def visit(c: While)         : Unit
  def visit(c: Print)         : Unit
  def visit(c: OberonProgram) : Unit 
}
