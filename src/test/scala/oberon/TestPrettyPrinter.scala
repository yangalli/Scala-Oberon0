package oberon

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.expression.IntValue
import oberon.expression.BoolValue
import oberon.expression.AddExpression
import oberon.expression._
import oberon.command._

import oberon.visitor.PrettyPrinter

class TestPrettyPrinter extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a pretty printer"

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

  it should "print \"!true\" when we call accept in such an expression Not" in {
    val val_true  = BoolValue(true)
    val not = new NotExpression(val_true)

    val pp = new PrettyPrinter()

    not.accept(pp)

    pp.str should be ("!true")
  }

  it should "print \"x = 5\" when we call accept in such an expression VarRef" in {
    val val_five = IntValue(5)
    val x = new VarRef("x")
    val oto_x = new Assignment("x", val_five)
    oto_x.run()

    val pp = new PrettyPrinter()

    x.accept(pp)

    pp.str should be ("x = 5")
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

    pp.str should be ("if(x = 15 > 10){var x = (x = 15 + 1)}")
  }
}
