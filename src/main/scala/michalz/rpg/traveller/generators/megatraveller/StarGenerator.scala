package michalz.rpg.traveller.generators.megatraveller

import michalz.rpg.traveller.generators.base.table.TableGen
import michalz.rpg.traveller.generators.base.table.TableRow
import michalz.rpg.traveller.generators.base.Dice.*
import michalz.rpg.traveller.generators.megatraveller.starsystem.Star
import michalz.rpg.traveller.generators.megatraveller.starsystem.StarClass
import michalz.rpg.traveller.generators.megatraveller.starsystem.StarType
import michalz.rpg.traveller.generators.megatraveller.uwp.UWP
import org.scalacheck.Gen

case class StarTypeWithRoll(starType: StarType, roll: Int):
  def withType(starType: StarType): StarTypeWithRoll = StarTypeWithRoll(starType, roll)

case class StarClassWithRoll(starClass: StarClass, roll: Int):
  def withClass(starClass: StarClass): StarClassWithRoll = StarClassWithRoll(starClass, roll)

case class StarWithRolls(star: Star, typeRoll: Int, classRoll: Int)

object StarWithRolls:
  def apply(typeWithRoll: StarTypeWithRoll, decimalType: Int, classWithRoll: StarClassWithRoll): StarWithRolls = StarWithRolls(
    Star(typeWithRoll.starType, decimalType, classWithRoll.starClass),
    typeWithRoll.roll,
    classWithRoll.roll,
  )

object StarGenerator:

  private def typeWithRoll(starType: StarType)(roll: Int): Gen[StarTypeWithRoll] = Gen.const(StarTypeWithRoll(starType, roll))

  def primaryStarType(dice: DiceGen = `2d6`): Gen[StarTypeWithRoll] = TableGen(
    dice,
    TableRow(2, typeWithRoll(StarType.A)),
    TableRow(3, 7, typeWithRoll(StarType.M)),
    TableRow(8, typeWithRoll(StarType.G)),
    TableRow(9, typeWithRoll(StarType.G)),
    TableRow(10, 12, typeWithRoll(StarType.F)),
  )

  private def classWithRoll(starClass: StarClass)(roll: Int): Gen[StarClassWithRoll] = Gen.const(StarClassWithRoll(starClass, roll))

  def primaryStarClass(dice: DiceGen = `2d6`): Gen[StarClassWithRoll] = TableGen(
    dice,
    TableRow(2, classWithRoll(StarClass.ClassII)),
    TableRow(3, classWithRoll(StarClass.ClassIII)),
    TableRow(4, classWithRoll(StarClass.ClassIV)),
    TableRow(5, 10, classWithRoll(StarClass.ClassV)),
    TableRow(11, classWithRoll(StarClass.ClassVI)),
    TableRow(12, classWithRoll(StarClass.Dwarf)),
  )

  def otherStarType(diceGen: DiceGen): Gen[StarType] = TableGen(
    diceGen,
    TableRow(4, StarType.`A`),
    TableRow(5, 6, StarType.`F`),
    TableRow(7, 8, StarType.`G`),
    TableRow(9, 10, StarType.`K`),
    TableRow(11, 14, StarType.`M`),
  )

  def otherStarGenerator(mainStarWithRolls: StarWithRolls, dice: DiceGen = `2d6`): Gen[Star] = ??? // TODO implement

  def primaryStar(uwp: Option[UWP]): Gen[StarWithRolls] =
    val dice = uwp
      .map: u =>
        val atmVal = u.atmosphere.numericValue
        val popVal = u.population.numericValue
        if atmVal >= 4 && atmVal <= 8 || popVal >= 8 then
          `2d6` + 4
        else
          `2d6`
      .getOrElse(`2d6`)

    def starClassGen(dice: DiceGen, starType: StarTypeWithRoll, decimalType: Int): Gen[StarClassWithRoll] = primaryStarClass(dice).map:
      starClass =>
        (starClass.starClass, starType.starType) match
          case (StarClass.ClassIV, StarType.`K`) if decimalType >= 5 =>
            starClass.withClass(StarClass.ClassV)
          case (StarClass.ClassIV, StarType.`M`) =>
            starClass.withClass(StarClass.ClassV)
          case (StarClass.ClassVI, StarType.`A`) =>
            starClass.withClass(StarClass.ClassV)
          case (StarClass.ClassVI, StarType.`F`) if decimalType <= 4 =>
            starClass.withClass(StarClass.ClassV)
          case _ =>
            starClass

    for {
      starType    <- primaryStarType(dice)
      starDecimal <- d(10) - 1
      starClass   <- starClassGen(dice, starType, starDecimal)
    } yield StarWithRolls(starType, starDecimal, starClass)
