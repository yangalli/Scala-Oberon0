package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.expression._

class TestIfThen extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "An If, Then Command"

  before {
    clear()
  }

  it should "return the correct value after the use of an if, then statement x -> 16" in { 
    // x := 10
    val assignment = new Assignment("x", IntValue(15))
    // x > 10
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    // x <= x + 1
    val cmd = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    // if (x > 10) 
    //   then x <= x + 1 -> x = 15 + 1 = 16   
    val ift = new IfThen(cond, cmd)

    assignment.run()
    ift.run()

    lookup("x") should be (Some(IntValue(16))) 
  }

  it should "return the same value the if, then condition is not true x-> 5" in { 
    val assignment = new Assignment("x", IntValue(5))
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    val cmd = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val ift = new IfThen(cond, cmd)

    assignment.run()
    ift.run()

    lookup("x") should be (Some(IntValue(5))) 
  }
}
