package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.expression._

class TestIfThenElse extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "An If, Then, Else Command"

  before {
    clear()
  }

  it should "return the value x + 1, when the condition (x > 10) is true" in { 
    // x := 10
    val assignment = new Assignment("x", IntValue(15))
    // x > 10
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    // x <= x + 1
    val cmd1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val cmd2 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(2)))
    // if (x > 10) 
    //   then x := x + 1 -> x = 15 + 1 = 16
    // else   x := x + 2 -> x = 15 + 2 = 17
    val ift = new IfThenElse(cond, cmd1, cmd2)

    assignment.run()
    ift.run()

    lookup("x") should be (Some(IntValue(16))) 
  }

  it should "return the value x + 2, when the condition (x > 10) is not true" in { 
    val assignment = new Assignment("x", IntValue(5))
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    val cmd1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val cmd2 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(2)))
    val ift = new IfThenElse(cond, cmd1, cmd2)

    assignment.run()
    ift.run()

    lookup("x") should be (Some(IntValue(7))) 
  }
}
