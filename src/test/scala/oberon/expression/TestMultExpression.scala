package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestMultExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Multiplication Expression"

  it should "return value 50 in MultExpression(IntValue(5), IntValue(10))" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new MultExpression(val5, val10) 

    add.eval() should be (IntValue(50)) 
  }

  it should "lead to an exception in MultExpression(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val add = new MultExpression(val5, valf)

   // add.eval() should be (IntValue(5))
  }
}
