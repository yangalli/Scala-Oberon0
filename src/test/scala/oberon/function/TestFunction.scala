package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.command._
import oberon.FuncDef
import oberon.func


class TestFunction extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A function"

  before {
    clear()
  }
  
  it should "return 3 when we call the function: sum(1,2)" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    
    val d1 = new FuncDef("sum", List("x", "y"), Return(r1))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(3))
  }

  it should "return 4 when we call the function: sum(1,2)" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val a1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val d1 = new FuncDef("sum", List("x", "y"), new BlockCommand(List(a1, Return(r1))))

    /*********************************************
      *          def sum(x := 1, y := 2) = {       *
      *            x := x + 1                      *
      *            return x + y;                   *
      *          }                                 *
      **********************************************/

    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(4))
  }
}
