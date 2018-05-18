package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.expression._

class TestIfThen extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an if then command"

  before {
    clear()
  }
  // if(x > 10)
  // then x := x + 1

  it should "lookup(x) should return 16 after if statement" in { 
    val assignment = new Assignment("x", IntValue(15))
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    val cmd = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val ift = new IfThen(cond, cmd)

    assignment.run()
    ift.run()

    lookup("x") should be (Some(IntValue(16))) 
  }

  it should "lookup(x) should return 5 after if statement" in { 
    val assignment = new Assignment("x", IntValue(5))
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    val cmd = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val ift = new IfThen(cond, cmd)

    assignment.run()
    ift.run()

    lookup("x") should be (Some(IntValue(5))) 
  }
}
