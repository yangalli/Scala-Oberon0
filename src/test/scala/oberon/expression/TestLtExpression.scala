package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestLtExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Less Then expression"

  it should "return value false in Lt(IntValue(5), Add(IntValue(3), IntValue(2))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val val2 = IntValue(2)  
    val add  = new AddExpression(val3, val2) 
    val eq   = new LtExpression(val5, add)

    eq.eval() should be (BoolValue(false)) 
  }

  it should "return value true in Lt(IntValue(5), Add(IntValue(3), IntValue(3))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val add  = new AddExpression(val3, val3) 
    val eq   = new LtExpression(val5, add)

    eq.eval() should be (BoolValue(true)) 
  }

  it should "return value false in Lt(IntValue(10), Add(IntValue(3), IntValue(3))" in {
    val val10 = IntValue(10)
    val val3 = IntValue(3)
    val add  = new AddExpression(val3, val3) 
    val eq   = new LtExpression(val10, add)

    eq.eval() should be (BoolValue(false)) 
  }


}
