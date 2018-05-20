package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestAddExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "An Add Expression"

  it should "return value 15 in AddExpression(IntValue(5), IntValue(10))" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new AddExpression(val5, val10) 

    add.eval() should be (IntValue(15)) 
  }

  it should "return a negative number in AddExpression(IntValue(5), IntValue(-10))" in {
    val val5 = IntValue(5)
    val valneg10 = IntValue(-10)
    val add = new AddExpression(val5, valneg10)

    add.eval() should be (IntValue(-5))
  }

  // it should "lead to an exception in AddExpression(IntValue(5), BoolValue(False))" in {
  //   val val5 = IntValue(5)
  //   val valf = BoolValue(false).asInstanceOf[IntValue].value
  //   val add = new AddExpression(val5, valf)

  //   add.eval() should be (IntValue(5))
  // }
}
