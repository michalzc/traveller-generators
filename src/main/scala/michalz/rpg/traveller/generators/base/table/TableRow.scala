package michalz.rpg.traveller.generators.base.table

import org.scalacheck.Gen

case class TableRow[T](min: Int, max: Int, generator: Int => Gen[T])

object TableRow {

  def apply[T](num: Int, value: T): TableRow[T] = TableRow(min = num, max = num, generator = _ => Gen.const(value))

  def apply[T](min: Int, max: Int, value: T): TableRow[T] = TableRow(
    min = min,
    max = max,
    generator = _ => Gen.const(value),
  )

  def apply[P, T](num: Int, generatorFun: Int => Gen[T]): TableRow[T] = TableRow(
    min = num,
    max = num,
    generator = generatorFun,
  )
}
