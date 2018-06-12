package oberon

import oberon.command._
import oberon.Environment._

import oberon.expression.Type

class ProcDef(val nome: String, val args: List[(String, Type)], val command: Command) {
  defineProc += (nome -> this)
}