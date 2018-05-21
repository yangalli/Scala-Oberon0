package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestModExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Mod Expression"

  it should "return value 1 in ModExpression(IntValue(5), IntValue(2))" in {
    val val5  = IntValue(5)
    val val2  = IntValue(2)
    val mod   = new ModExpression(val5, val2) 

    mod.eval() should be (IntValue(1)) 
  }

  it should "return value 0 in ModExpression(IntValue(10), IntValue(5))" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val mod   = new ModExpression(val10, val5) 

    mod.eval() should be (IntValue(0)) 
  }
}
