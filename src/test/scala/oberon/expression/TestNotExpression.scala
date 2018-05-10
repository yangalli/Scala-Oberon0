package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestNotExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an not expression"

  it should "return value true in Not(BoolValue(false))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val not   = new NotExpression(valf)

    not.eval() should be (BoolValue(true)) 
  }

  it should "return value false in Not(BoolValue(true))" in {
    val valf = BoolValue(false)
    val valt = BoolValue(true)
    val not   = new NotExpression(valt)

    not.eval() should be (BoolValue(false)) 
  }

}
