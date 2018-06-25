package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.command._
import oberon.FuncDef
import oberon.Func


class TestFunction extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A function"

  before {
    clear()
  }
  
  it should "return 3 when we call the function: sum(1,2)" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    
    val d1 = new FuncDef(TInt(), "sum", List(("x", TInt()), ("y", TInt())), Return(r1))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    val f1 = new Func("sum", List(IntValue(1), IntValue(2)))

    f1.eval()
  }

  it should "return 4 when we call the function: sum(1,2)" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val a1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val d1 = new FuncDef(TInt(), "sum", List(("x", TInt()), ("y", TInt())), new BlockCommand(List(a1, Return(r1))))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            x := x + 1                      *
    *            return x + y;                   *
    *          }                                 *
    **********************************************/

    val f1 = new Func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(4))
  }

  it should "return 6 when we call the function MultExpression(2, 3)" in {

    val expression1 = new MultExpression(new VarRef("x"), new VarRef("y"))
    val deffunc1 = new FuncDef(TInt(), "multiplicacao", List(("x", TInt()), ("y", TInt())), Return(expression1))

    val func1 = new Func("multiplicacao", List(IntValue(2), IntValue(3)))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *                                            *
    *            return x * y;                   *
    *          }                                 *
    **********************************************/

    func1.eval() should be (IntValue(6))
    
  }

  it should "return the correct value with multiple commands in function declaration" in {

    val ret1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val ass1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val ass2 = new Assignment("y", new MultExpression(new VarRef("y"), new VarRef("x")))
    val fd = new FuncDef(TInt(), "summult", List(("x", TInt()), ("y", TInt())), new BlockCommand(List(ass1, ass2, Return(ret1))))
    
    val func1 = new Func("summult", List(IntValue(2), IntValue(4)))

    /*********************************************
    *          def sum(x := 2, y := 4) = {       *
    *            x := x + 1                      *
    *            y := y * x                      *
    *            return x + y;                   *
    *          }                                 *
    **********************************************/

    func1.eval() should be (IntValue(15))
    
  }

  // it should "return the correct value with a while command in function declaration" in {

  //   val ret1 = new AddExpression(new VarRef("x"), new VarRef("soma") )
  //   val a3 = new Assignment("soma", new AddExpression(new VarRef("soma"), new VarRef("x")) )
  //   // a4 <= x := x + 1;
  //   val a4 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)) )
  //   // cond <= (x <= 10)
  //   val cond = new LeExpression(new VarRef("x"), IntValue(5))

  //   val w1 = new While(cond, new BlockCommand(List(a3, a4)) )
  //   val fd = new FuncDef("while", List(("x", TInt()), ("y", TInt())), new BlockCommand(List(w1, Return(ret1))) )
    
  //   val func1 = new Func("while", List(IntValue(1), IntValue(0)))

  //   /**********************************************
  //   *          def while(x := 1, soma := 0) = {   *
  //   *            w1 = while (x <= 5) loop         *
  //   *              soma := soma + x;              *
  //   *              x := x + 1;                    *
  //   *            end loop                         *
  //   *           ret soma + x                      *
  //   *          }                                  *
  //   **********************************************/

  //   func1.eval() should be (IntValue(21))
    
  // }

}