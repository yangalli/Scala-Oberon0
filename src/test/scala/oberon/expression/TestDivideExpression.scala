package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

class TestDivideExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Division Expression"

  it should "return value 3 in Subtract(IntValue(15), IntValue(5))" in {
    val val15 = IntValue(15)
    val val5 = IntValue(5)
    val div = new DivideExpression(val15, val5) 

    div.eval() should be (IntValue(3)) 
  }

  // it should "lead to an exception in Divide(IntValue(5), IntValue(4))" in {
  //   val val5 = IntValue(5)
  //   val val4 = IntValue(4)
  //   val division = new DivideExpression(val5, val4)

  //   division.eval() should be (IntValue(5))
  // }
}
