package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestAndExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an and expression"

  it should "return value true in And(BoolValue(false), BoolValue(false))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val exp   = new AndExpression(valf, valf)

    exp.eval() should be (BoolValue(false)) 
  }

  it should "return value true in And(BoolValue(true), BoolValue(true))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val exp   = new AndExpression(valt, valt)

    exp.eval() should be (BoolValue(true)) 
  }

  it should "return value false in And(BoolValue(true), BoolValue(false))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val exp   = new AndExpression(valt, valf)

    exp.eval() should be (BoolValue(false)) 
  }

  it should "return value false in And(BoolValue(false), BoolValue(true))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val exp   = new AndExpression(valf, valt)

    exp.eval() should be (BoolValue(false)) 
  }

}
