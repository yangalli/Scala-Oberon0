package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.expression._

class TestDeclaration extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Declaration Command"

  before {
    clear()
  }

  it should "be able to declare and Undefined variable x -> Undefined" in { 
    // x ...(Undefined)
    val assignment = new Declaration("x")

    assignment.run()

    lookup("x") should be (Some(Undefined()))
  }
}
