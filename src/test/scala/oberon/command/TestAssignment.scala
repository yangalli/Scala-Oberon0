package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.expression.IntValue
import oberon.expression.BoolValue

class TestAssignment extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "An Assignment Command"

  before {
    clear()
  }

  it should "be able to assign integer values to variables x -> 5" in { 
    // assignment := 5
    val assignment = new Assignment("x", IntValue(5))

    assignment.run()

    lookup("x") should be (Some(IntValue(5))) 
  }

  it should "be able to assign boolean values to variables x -> true" in { 
    // val_t := false
    val val_t = BoolValue(true)
    // x := val_t
    val assignment = new Assignment("x", val_t)

    assignment.run()

    lookup("x") should be (Some(val_t)) 
  }

  it should "be able to assign boolean values to variables x -> false" in { 
    // val_t := true
    val assignment = new Assignment("x", BoolValue(false))

    assignment.run()

    lookup("x") should be (Some(BoolValue(false))) 
  }
}

