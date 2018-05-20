package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen


class TestOrExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "An Or expression"

  it should "return value true in Or(BoolValue(false), BoolValue(false))" in {
    val v_false = BoolValue(false)
    val v_true = BoolValue(true)
    val or = new OrExpression(v_false, v_true)

    or.eval() should be (BoolValue(true)) 
  }

  it should "return value true in Or(BoolValue(true), BoolValue(true))" in {
    val v_true1 = BoolValue(true)
    val v_true2 = BoolValue(true)
    val or = new OrExpression(v_true1, v_true2)

    or.eval() should be (BoolValue(true))
  }

  it should "return value false in Or(BoolValue(false), BoolValue(false))" in {
    val v_false1 = BoolValue(false)
    val v_false2 = BoolValue(false)
    val or = new OrExpression(v_false1, v_false2)

    or.eval() should be (BoolValue(false))
  }

}
