package oberon

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.expression._
import oberon.command._
import oberon.Environment.clear

import oberon.visitor.PrettyPrinter

class TestPrettyPrinter extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a pretty printer"

  before {
    clear()
  }

  it should "print \"(5 + 10)\" when we call accept in such an expression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new AddExpression(val5, val10) 

    val pp = new PrettyPrinter()

    add.accept(pp)

    pp.str should be ("(5 + 10)")
  }

  it should "print \"((5 + 10) + 5)\" when we call accept in such an expression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new AddExpression(val5, val10) 
    val add2  = new AddExpression(add, val5)

    val pp = new PrettyPrinter()

    add2.accept(pp)

    pp.str should be ("((5 + 10) + 5)")
  }

  it should "print \"(5 == 5)\" when we call accept in EqExpression" in {
    val val5_1  = IntValue(5)
    val val5_2 = IntValue(5)
    val eq = new EqExpression(val5_1, val5_2)

    val pp = new PrettyPrinter()

    eq.accept(pp)

    pp.str should be ("(5 == 5)")
  }

  it should "print \"(5 == 10)\" when we call accept in NotEqExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val n_eq = new NotEqExpression(val5, val10)

    val pp = new PrettyPrinter()

    n_eq.accept(pp)

    pp.str should be ("(5 != 10)")
  }

  it should "print \"(10 > 5)\" when we call accept in GtExpression" in {
    val val10 = IntValue(10)
    val val5  = IntValue(5)
    val gt = new GtExpression(val10, val5)

    val pp = new PrettyPrinter()

    gt.accept(pp)

    pp.str should be ("(10 > 5)")
  }

  it should "print \"(5 >= 5)\" when we call accept in GeExpression" in {
    val val5_1 = IntValue(5)
    val val5_2  = IntValue(5)
    val gt_eq = new GeExpression(val5_1, val5_2)

    val pp = new PrettyPrinter()

    gt_eq.accept(pp)

    pp.str should be ("(5 >= 5)")
  }

  it should "print \"(5 % 5)\" when we call accept in ModExpression" in {
    val val5_1 = IntValue(5)
    val val5_2  = IntValue(5)
    val mod = new ModExpression(val5_1, val5_2)

    val pp = new PrettyPrinter()

    mod.accept(pp)

    pp.str should be ("(5 % 5)")
  }

  it should "print \"!true\" when we call accept in such an expression Not" in {
    val val_true  = BoolValue(true)
    val not = new NotExpression(val_true)

    val pp = new PrettyPrinter()

    not.accept(pp)

    pp.str should be ("!true")
  }

  it should "print \"x = 5\" when we call accept inVarRef expression" in {
    val val_five = IntValue(5)
    val x = new VarRef("x")
    val oto_x = new Assignment("x", val_five)
    oto_x.run()

    val pp = new PrettyPrinter()

    x.accept(pp)

    pp.str should be ("x := 5")
  }

  it should "print \"var x = 5\" when we call accept in such an expression Assign" in {
    val val_five = IntValue(5)
    val x = new VarRef("x")
    val oto_x = new Assignment("x", val_five)
    oto_x.run()

    val pp = new PrettyPrinter()

    oto_x.accept(pp)

    pp.str should be ("var x = 5")
  }

  it should "print \"if\" when we call accept in such an expression IFTHEN" in {
    // x := 15
    val a = new Assignment("x", IntValue(15))
    // x > 10
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    
    val cmd = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    // if (x > 10) 
    //   then x := x + 1 -> x = 15 + 1 = 16   
    val ift = new IfThen(cond, cmd)

    val pp = new PrettyPrinter()

    a.run()
    ift.accept(pp)

    pp.str should be ("if(x := 15 > 10){var x = (x := 15 + 1)}")
  }

  it should "print \"FuncDef\" when we call accept in a definition" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    
    val d1 = new FuncDef("sum", List(("x", TInt()), ("y", TInt())), Return(r1))

    /*********************************************
    *          def sum(x := 1, y := 2) = {       *
    *            return x + y;                   *               
    *          }                                 *
    **********************************************/
    
    //val f1 = new Func("sum", List(IntValue(1), IntValue(2)))

    val pp = new PrettyPrinter()
    d1.accept(pp)

    pp.str should be ("functionsum(x,y){return (x := undefined + y := undefined)}")

    //f1.accept(pp)
    
  }

  it should "print \"Block command\" when we call accept in a definition" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val a1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val b1 = new BlockCommand(List(a1, Return(r1)))

    val pp = new PrettyPrinter()
    b1.accept(pp)

    pp.str should be ("var x = (x := undefined + 1)\nreturn (x := undefined + y := undefined)\n")
    
  }

  
}
