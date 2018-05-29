package oberon

import oberon.command._
import oberon.Environment._

class ProcDef(val nome: String, val args: List[String], val command: Command) {
  defineProc += (nome -> this)
}