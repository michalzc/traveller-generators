package michalz.rpg.traveller.generators.base.table

import michalz.rpg.traveller.generators.base.Dice.DiceGen
import org.scalacheck.Gen

object TableGen {
  def apply[T](dice: DiceGen, rows: TableRow[T]*): Gen[T] =
    val sortedRows = rows.sortBy(_.min).toVector
    require(rows.nonEmpty && validateRows(rows), "Invalid rows")
    dice.flatMap { roll =>
      sortedRows
        .collectFirst {
          case row: TableRow[T] if roll <= row.max => row.generator(roll)
        }
        .getOrElse(sortedRows.last.generator(roll))
    }

  def validateRows(rows: Seq[TableRow[_]]): Boolean = rows
    .foldLeft((true, rows.head.min - 1)) { case ((valid, lastIndex), row) =>
      (valid && row.min <= row.max && (lastIndex + 1) == row.min, row.max)
    }
    ._1
}

// min1 ---- max1 min2 --- max2 min3 ---- max3
