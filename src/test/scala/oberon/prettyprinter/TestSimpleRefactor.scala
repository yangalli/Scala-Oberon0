package oberon

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.expression._
import oberon.command._
import oberon.Environment.clear

import oberon.visitor.SimpleRefactor

class TestSimpleRefactor extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Simple Refactor"

  before {
    clear()
  }

  it should "print \"if\" when we call accept in such an expression IFTHEN" in {
    // x := 15
    val a = new Assignment("x", IntValue(15))
    // x > 10
    val cond = new GtExpression(new VarRef("x"), IntValue(10))
    
    val cmd = Return(BoolValue(true))
    // if (x > 10) 
    //   then true
    // else false   
    val ift = new IfThenElse(cond, cmd, Return(BoolValue(false)))

    val pp = new SimpleRefactor()

    a.run()
    ift.accept(pp)

    pp.str should be ("return (x > 10)")
  }

  it should "print \"broco\" when we call accept in such an expression IFTHEN" in {
    // x := 15
    val a = new Assignment("x", IntValue(15))
    // x > 10
    val cond = new GtExpression(new VarRef("x"), IntValue(10))

    
    val cmd = Return(BoolValue(true))
    // if (x > 10) 
    //   then true
    // else false   
    val ift1 = new IfThenElse(cond, cmd, Return(BoolValue(true)))
    val ift2 = new IfThenElse(cond, cmd, Return(BoolValue(false)))

    val broco = new BlockCommand(List(ift1, ift2))

    val pp = new SimpleRefactor()

    a.run()
    broco.accept(pp)

    pp.str should be ("if(x > 10){return true}\nelse{return true}\nreturn (x > 10)\n")
  }

}