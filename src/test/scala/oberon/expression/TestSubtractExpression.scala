package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestSubtractExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Subtraction Expression"

  it should "return value 5 in SubtractExpression(IntValue(10), IntValue(5))" in {
    val val10  = IntValue(10)
    val val5 = IntValue(5)
    val subtract = new SubtractExpression(val10, val5) 

    subtract.eval() should be (IntValue(5)) 
  }

  it should "return -5 in SubtractExpression(IntValue(5), IntValue(10))" in {
    val val5 = IntValue(5)
    val val10 = IntValue(10)
    val subtract = new SubtractExpression(val5, val10)

    subtract.eval() should be (IntValue(-5))
  }
}
