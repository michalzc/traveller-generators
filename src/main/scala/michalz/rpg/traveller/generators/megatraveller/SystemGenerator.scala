package michalz.rpg.traveller.generators.megatraveller

import cats.syntax.option.*
import michalz.rpg.traveller.generators.base.Dice.{DiceExt, DiceGen, `2d6`, d}
import michalz.rpg.traveller.generators.base.table.{TableGen, TableRow}
import michalz.rpg.traveller.generators.megatraveller.starsystem.Nature.{
  Binary,
  Solo,
  Trinary
}
import michalz.rpg.traveller.generators.megatraveller.starsystem.StarClass.*
import michalz.rpg.traveller.generators.megatraveller.starsystem.StarType.{
  `A`,
  `F`,
  `G`,
  `M`
}
import michalz.rpg.traveller.generators.megatraveller.starsystem.{
  Nature,
  Star,
  StarClass,
  StarType
}
import michalz.rpg.traveller.generators.megatraveller.uwp.UWP
import org.scalacheck.Gen

object SystemGenerator {

  def primaryStarType(dice: DiceGen = `2d6`): Gen[StarType] = TableGen(
    dice,
    TableRow(2, `A`),
    TableRow(3, 7, `M`),
    TableRow(8, `G`),
    TableRow(9, `G`),
    TableRow(10, 12, `F`)
  )

  def primaryStarClass(dice: DiceGen = `2d6`): Gen[StarClass] = TableGen(
    dice,
    TableRow(2, ClassII),
    TableRow(3, ClassIII),
    TableRow(4, ClassIV),
    TableRow(5, 10, ClassV),
    TableRow(11, ClassVI),
    TableRow(12, Dwarf)
  )

  def primaryStar(uwp: Option[UWP]): Gen[Star] =
    val dice = uwp
      .map: u =>
        val atmVal = u.atmosphere.numericValue
        val popVal = u.population.numericValue
        if atmVal >= 4 && atmVal <= 8 || popVal >= 8 then `2d6` + 4
        else `2d6`
      .getOrElse(`2d6`)

    def starClassGen(
        dice: DiceGen,
        starType: StarType,
        decimalType: Int
    ): Gen[StarClass] = primaryStarClass(dice).map: starClass =>
      (starClass, starType) match
        case (StarClass.ClassIV, StarType.`K`) if decimalType >= 5 =>
          StarClass.ClassV
        case (StarClass.ClassIV, StarType.`M`) => StarClass.ClassV
        case (StarClass.ClassVI, StarType.`A`) => StarClass.ClassV
        case (StarClass.ClassVI, StarType.`F`) if decimalType <= 4 =>
          StarClass.ClassV
        case _ => starClass


    for {
      starType <- primaryStarType(dice)
      starDecimal <- d(10) - 1
      starClass <- starClassGen(dice, starType, starDecimal)
    } yield Star(starType, starDecimal, starClass)

  def natureGenerator(dice: DiceGen = `2d6`): Gen[Nature] = TableGen(
    dice,
    TableRow(2, 7, Solo),
    TableRow(8, 11, Binary),
    TableRow(12, Trinary)
  )

  def generate(uwp: Option[String] = none): Gen[StarSystem] = ???

}
