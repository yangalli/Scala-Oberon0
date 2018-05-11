package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.expression._

class TestDeclaration extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an declaration command"

  before {
    clear()
  }

  it should "the environment must have an Declaration x -> Undefined" in { 
    val assignment = new Declaration("x")

    assignment.run()

    lookup("x") should be (Some(Undefined()))
  }
}
