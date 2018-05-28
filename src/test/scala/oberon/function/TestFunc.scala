package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

import oberon.Environment._
import oberon.expression._
import oberon.command._
import oberon.FuncDef
import oberon.func


class TestFunction extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A function"
  
  it should "return 3 when we call the function: sum(1,2)" in {
    // r1 <= x + y
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    
    val d1 = new FuncDef("sum", List("x", "y"), new BlockCommand(List(Return(r1))))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(3))
  }

  it should "return 4 when we call the function: sum(1,2)" in {
    // r1 <= x + y
    val a1 = new AddExpression(new VarRef("x"), new IntValue(1))
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val a2 = new Assignment("x", a1)
    
    val d1 = new FuncDef("sum", List("x", "y"), new BlockCommand(List(a2, Return(r1))))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(4))
  }
  
}
