package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._

import oberon.expression.IntValue
import oberon.expression._
import oberon.command._

class TestWhile extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A While Command"

  before {
    clear()
  }
  // soma := 0;
  //    x := 1;
  // while(x <= 10) begin
  //   soma := soma + x;
  //      x := x + 1;
  // end
  // print(soma);  
  it should "lookup(soma) must be equal to 55 after a loop summing up 1 to 10" in {
    // soma := 0;
    val a1 = new Assignment("soma", IntValue(0))
    // a2 <= x := 1;    
    val a2 = new Assignment("x", IntValue(1))
    // a3 <= soma := soma + x;
    val a3 = new Assignment("soma",new AddExpression(new VarRef("soma"), new VarRef("x")))
    // a4 <= x := x + 1;
    val a4 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    // cond <= (x <= 10)
    val cond = new LeExpression(new VarRef("x"), IntValue(10))
    /************************************************
    *       w1 = while (x <= 10) loop              *
    *          soma := soma + x;                    *
    *          x := x + 1;                          *
    *       end loop                                *
    *************************************************/
    val w1 = new While(cond, new BlockCommand(List(a3, a4)))

    a1.run()
    a2.run()
    w1.run()

    lookup("soma") should be (Some(IntValue(55))) 

    /* val res = lookup("soma")
    res match {
      case Some(v) => v.eval() should be (IntValue(55))
      case _       => 5 should be (1)  
    } */
  }
}
