package oberon

import oberon.command._
import oberon.Environment._
import oberon.expression.Type

class FuncDef(val nome: String, val args: List[(String, Type)], val command: Command) {

  define += (nome -> this)
}