package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestAndExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "An And Expression"

  it should "return value true in AndExpression(BoolValue(false), BoolValue(false))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val exp   = new AndExpression(valf, valf)

    exp.eval() should be (BoolValue(false)) 
  }

  it should "return value true in AndExpression(BoolValue(true), BoolValue(true))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val and = new AndExpression(valt, valt)

    and.eval() should be (BoolValue(true)) 
  }

  it should "return value false in AndExpression(BoolValue(true), BoolValue(false))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val and = new AndExpression(valt, valf)

    and.eval() should be (BoolValue(false)) 
  }

  it should "return value false in AndExpression(BoolValue(false), BoolValue(true))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val and = new AndExpression(valf, valt)

    and.eval() should be (BoolValue(false)) 
  }

}
