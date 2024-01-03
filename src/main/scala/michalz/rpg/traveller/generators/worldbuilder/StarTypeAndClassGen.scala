package michalz.rpg.traveller.generators.worldbuilder

import michalz.rpg.traveller.generators.base.table.TableGen
import michalz.rpg.traveller.generators.base.table.TableRow
import michalz.rpg.traveller.generators.base.Dice
import michalz.rpg.traveller.generators.base.Dice.*
import org.scalacheck.Gen
import scala.language.implicitConversions

object StarTypeAndClassGen {

  case class StarTypeAndClass(starType: StarType, starClass: StarClass)

  implicit class StartType(self: StarType) {
    def withClass: StarTypeAndClass                        = StarTypeAndClass(self, StarClass.V)
    def withClass(startClass: StarClass): StarTypeAndClass = StarTypeAndClass(self, startClass)
  }

  val `2d6`: DiceGen = 2 x Dice.d(6)

  def hotTypeGen(diceGen: DiceGen = `2d6`): Gen[StarType] = TableGen(
    diceGen,
    TableRow(2, 9, StarType.A),
    TableRow(10, 11, StarType.B),
    TableRow(12, StarType.O),
  )

  def mainSequenceTypeGen(diceGen: DiceGen = `2d6`): Gen[StarType] = TableGen(
    diceGen,
    TableRow(3, 6, StarType.M),
    TableRow(7, 8, StarType.K),
    TableRow(9, 10, StarType.G),
    TableRow(11, StarType.F),
    TableRow(12, _ => hotTypeGen()),
  )

  def giantClassGen(diceGen: DiceGen = `2d6`): Gen[StarClass] = TableGen(
    diceGen,
    TableRow(2, 8, StarClass.III),
    TableRow(9, 10, StarClass.II),
    TableRow(11, StarClass.Ib),
    TableRow(12, StarClass.Ia),
  )

  def specialClassGen(diceGen: DiceGen = `2d6`): Gen[StarClass] = TableGen(
    diceGen,
    TableRow(2, 5, StarClass.VI),
    TableRow(6, 8, StarClass.IV),
    TableRow(9, 10, StarClass.III),
  )

  def starTypeByClass(starClass: StarClass, diceGen: DiceGen = `2d6` + 1): Gen[StarTypeAndClass] = starClass match
    case StarClass.IV =>
      diceGen
        .map(num => if (num <= 6) num + 5 else num)
        .flatMap(roll => mainSequenceTypeGen(Gen.const(roll)))
        .map {
          case StarType.O => StarType.B
          case other      => other
        }
        .map(_.withClass(starClass))

    case StarClass.VI =>
      mainSequenceTypeGen()
        .map {
          case StarType.F => StarType.G
          case StarType.A => StarType.B
          case other      => other
        }
        .map(_.withClass(starClass))

    case starClass => mainSequenceTypeGen(diceGen).map(_.withClass(starClass))

  def primaryStarGen(diceGen: DiceGen = `2d6`): Gen[StarTypeAndClass] = TableGen(
    diceGen,
    TableRow(2, _ => specialClassGen().flatMap(sc => starTypeByClass(sc))),
    TableRow(3, 12, (roll: Int) => mainSequenceTypeGen(Gen.const(roll)).map(_.withClass)),
  )

}
