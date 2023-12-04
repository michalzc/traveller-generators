package michalz.rpg.traveller.generators.base.table

import michalz.rpg.traveller.generators.base.Dice
import munit.FunSuite

class TableGenTest extends FunSuite {

  val row1 = TableRow(1, "One")
  val row2_3 = TableRow(2,3, "Two-Three")
  var row4_6 = TableRow(4,6, "Four-Six")
  val d6 = Dice.d(6)


  test("Table from Rows one") {
    val table = TableGen(d6, row1)
    val sample = table.sample
    assert(sample.isDefined)
  }

  test("Table from rows one and two") {
    val table = TableGen(d6, row1, row2_3)
    val sample = table.sample
    assert(sample.isDefined)
  }

  test("Table from row three") {
    val table = TableGen(d6, row4_6)
    val sample = table.sample
    assert(sample.isDefined)
  }

  test("Table from rows one, two and three") {
    val table = TableGen(d6, row1, row2_3, row4_6)
    val sample = table.sample
    assert(sample.isDefined)
  }

}
