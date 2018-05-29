package oberon

import scala.collection.mutable.Stack
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

import oberon.expression.Value

object Environment {

  // a stack for mapping values
  var stack = new Stack[Map[String, Value]] ()
  // defines the scope of a function
  var define = new HashMap[String, FuncDef]
  // defines the scope of a procedure
  var defineProc = new HashMap[String, ProcDef]

  def push() {
    stack.push(new HashMap[String, Value]())
  }

  def pop() {
    stack.pop()
  }

  def map(id: String, value: Value) {
    if(stack.isEmpty) {
      push()
    }

    //last = global variables
    // verifies if there are any global variables in the scope, and adds the global variable to
    // the scope
    if(stack.last.contains(id)) stack.last += (id -> value)
    else stack.top += (id -> value) 
  }

  // returns the top element of the stack by it's id, if there is any element
  def lookup(id: String) : Option[Value] =
    if(stack.isEmpty) None else stack.top.lift(id)

  def lookupDef(id: String) : FuncDef = define(id)

  def lookupProc(id: String) : ProcDef = defineProc(id)

  def clear() : Unit = { stack.clear() } 
}