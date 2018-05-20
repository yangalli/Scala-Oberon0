package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

import oberon.Environment._
import oberon.expression._
import oberon.command._
import oberon.FuncDef
import oberon.func


class TestDefExpression extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A function"
  //def sum(x: Int, y: Int) = {
  //  return x + y;
  //}
  it should "return 3 when we call the function: sum(1,2)" in {
    // r1 <= x := y
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    // sum(x, y)
    val d1 = new FuncDef("sum", List("x", "y"), None, r1)

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            x = x + 1;                      *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(3))
  }

}
