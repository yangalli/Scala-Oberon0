package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen

import oberon.Environment._
import oberon.expression._
import oberon.command._
import oberon.ProcDef
import oberon.proc


class TestProcedure extends FlatSpec with Matchers with GivenWhenThen {

  behavior of "A Procedure"
  
  it should "lookup 3 when we call the function: get(1,2)" in {
    //Procedimento
    
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    /* val cmd = new Print(r1) */
    val cmd = new Assignment("a", r1)
    val d1 = new ProcDef("get", List("x", "y"), cmd)

    /*********************************************
    *          def get(x := 1, y := 2) = {       *
    *             a <=(x + y);                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new proc("get", List(IntValue(1), IntValue(2)))

    f1.run()
    lookup("a") should be (Some(IntValue(3))) 
  }

}
