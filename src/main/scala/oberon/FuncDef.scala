package oberon

import oberon.command._
import oberon.Environment._

class FuncDef(val nome: String, val args: List[String], val command: Command) {
  // defines the scope of the function
  define += (nome -> this)
}