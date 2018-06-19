package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.command._
import oberon.FuncDef
import oberon.Func
import oberon.ProcDef
import oberon.Proc
import oberon.OberonProgram
import oberon.visitor.PrettyPrinter

class TestOberonProgram extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "An Oberon Program"

  before {
    clear()
  }

  it should "have an oberon program working correctly" in {

    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val d1 = new FuncDef("sum", List(("x", TInt()), ("y", TInt())), Return(r1))

    val a1  = new Declaration("res")
    val a2  = new AddExpression(new VarRef("x"), new VarRef("y"))
    val cmd = new Assignment("res", a2)
    val d2  = new ProcDef("sum", List(("x", TInt()), ("y", TInt())), cmd)

    val dec1 = new Declaration("x")
    val dec2 = new Declaration("y")

    val val_t = BoolValue(true)
    val assignment = new Assignment("x", val_t)

    val o_b = new OberonProgram(List(a1, dec1, dec2), List(d2), List(d1), assignment)

    val pp = new PrettyPrinter()
    o_b.accept(pp)

    pp.str should be ("OberonProgram\nvar res\nvar x\nvar y\nprocedure sum(x,y){var res = (x + y)}\nfunction sum(x,y){return (x + y)}\nvar x = true")

  }

}  