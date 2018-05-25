package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.command._
import oberon.ProcDef
import oberon.proc


class TestProcedure extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Procedure"

  before {
    clear()
  }
  
  it should "lookup 3 when we call the procedure: sum(1,2)" in {
    //Procedimento
    
    val a1  = new Declaration("res")
    val a2  = new AddExpression(new VarRef("x"), new VarRef("y"))
    val cmd = new Assignment("res", a2)
    val d1  = new ProcDef("sum", List("x", "y"), cmd)

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *             res := x + y                   *               
    *          }                                 *
    **********************************************/
    
    val p1 = new proc("sum", List(IntValue(1), IntValue(2)))
    a1.run()
    p1.run()

    lookup("res") should be (Some(IntValue(3)))
    new VarRef("x").eval() should be (Undefined())
    new VarRef("y").eval() should be (Undefined())
  }

}
