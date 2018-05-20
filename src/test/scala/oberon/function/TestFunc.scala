package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._

import oberon.expression._
import oberon.command._
import oberon.FuncDef
import oberon.func


class TestDefExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a def expressions"
  //def sum(x: Int, y: Int) = {
  //  x = x + 1;
  //  return x + y;
  //}
  it should "return 3 when we call the function: sum(1,2)" in {

    var c1 = new Assignment("x", new AddExpression(new VarRef("x"), IntValue(1)))
    val r1 = new AddExpression(new VarRef("x"), new VarRef("y"))
    val d1 = new FuncDef("sum", List("x", "y"), None, r1)

    val f1 = new func("sum", List(IntValue(1), IntValue(2)))

    f1.eval() should be (IntValue(3))
  }

}