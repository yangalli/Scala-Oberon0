package oberon.command

import oberon.Environment._
import oberon.expression._

class Declaration(val id: String) {
  def run() : Unit = {
    map(id, Undefined())
  }
}