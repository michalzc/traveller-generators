package michalz.rpg.traveller.generators.worldbuilder

import michalz.rpg.traveller.generators.base.*
import michalz.rpg.traveller.generators.base.Dice.*
import michalz.rpg.traveller.generators.base.table.{TableGen, TableRow}
import org.scalacheck.Gen

import cats.syntax.option.*

object StarGenerator {

  def starSubTypeNumeric(diceGen: DiceGen = Dice.`2d6`): Gen[Int] = TableGen(
    diceGen,
    TableRow(2, 0),
    TableRow(3, 1),
    TableRow(4, 3),
    TableRow(5, 5),
    TableRow(6, 7),
    TableRow(7, 9),
    TableRow(8, 8),
    TableRow(9, 6),
    TableRow(10, 4),
    TableRow(11, 2),
    TableRow(12, 0)
  )

  def starSubTypeMPrimary(diceGen: DiceGen = Dice.`2d6`): Gen[Int] = TableGen(
    diceGen,
    TableRow(2, 8),
    TableRow(3, 6),
    TableRow(4, 5),
    TableRow(5, 4),
    TableRow(6, 0),
    TableRow(7, 2),
    TableRow(8, 1),
    TableRow(9, 3),
    TableRow(10, 5),
    TableRow(11, 7),
    TableRow(12, 9)
  )

  def starSubTypeGen(starType: StarType, isPrimary: Boolean): Gen[Int] =
    (starType, isPrimary) match {
      case (StarType.M, true) => starSubTypeMPrimary()
      case (StarType.K, _) =>
        starSubTypeNumeric().map(n => if (n >= 5) n - 5 else n)
      case _ => starSubTypeNumeric()
    }

  def generatePrimaryStar(): Gen[StarLike] = for {
    StarTypeAndClassGen.StarTypeAndClass(starType, starClass) <-
      StarTypeAndClassGen.primaryStarGen()
    starSubType <- starSubTypeGen(starType, isPrimary = true)
  } yield Star(starType, starClass, starSubType.some)

  def genStar(
      genType: StarGeneratorType,
      diceGen: DiceGen = Dice.`2d6`
  ): Gen[StarLike] = genType match
    case StarGeneratorType.Primary        => generatePrimaryStar()
    case StarGeneratorType.PrimaryUnusual => ???
    case StarGeneratorType.Additional     => ???
}
