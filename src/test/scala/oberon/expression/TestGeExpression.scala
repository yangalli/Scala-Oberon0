package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestGeExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Greater or Equal Than Expression"

  it should "return value true in GeExpression(IntValue(5), IntValue(3))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3) 
    val ge = new GeExpression(val5, val3)

    ge.eval() should be (BoolValue(true)) 
  }

  it should "return value true in GeExpression(IntValue(5), IntValue(5))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val val2 = IntValue(2)
    val add = new AddExpression(val2, val3)
    val ge = new GeExpression(val5, add)

    ge.eval() should be (BoolValue(true)) 
  }

  it should "return value false in GeExpression(IntValue(5), IntValue(3))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val add = new AddExpression(val3, val5)
    val ge = new GeExpression(val5, add)

    ge.eval() should be (BoolValue(false)) 
  }

}