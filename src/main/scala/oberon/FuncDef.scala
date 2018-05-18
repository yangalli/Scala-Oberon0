package oberon

import oberon.command._
import oberon.expression._
import oberon.Environment._

// um processo recebe como retorno Undefined()
class FuncDef(val nome: String, val args: List[String], val command: Option[Command], val _return: Expression) {

  define += (nome -> this)
}