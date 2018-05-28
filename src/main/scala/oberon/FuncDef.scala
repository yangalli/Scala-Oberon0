package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

class FuncDef(val nome: String, val args: List[String], val command: BlockCommand /* val _return: Command */) {
  define += (nome -> this)
}