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
  
  it should "return 3 when we call the function: sum(1,2)" in {
    // r1 <= x := y
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    // sum(x, y)
    val d1 = new FuncDef("sum", List("x", "y"), None, r1)

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(3))
  }
  
  it should "lookup 3 when we call the function: get(1,2)" in {
    //Procedimento
    
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    /* val cmd = new Print(r1) */
    val cmd = new Assignment("a", r1)
    val d1 = new FuncDef("get", List("x", "y"), Some(cmd), Undefined())

    /*********************************************
    *          def get(x := 1, y := 2) = {       *
    *             a <=(x + y);                  *               
    *          }                                 *
    **********************************************/
    
    val f1 = new func("get", List(IntValue(1), IntValue(2)))

    f1.eval()
    lookup("a") should be (Some(IntValue(3))) 
  }

}
