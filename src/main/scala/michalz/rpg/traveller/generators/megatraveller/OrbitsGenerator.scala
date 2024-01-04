package michalz.rpg.traveller.generators.megatraveller

import michalz.rpg.traveller.generators.base.table.TableGen
import michalz.rpg.traveller.generators.base.table.TableRow
import michalz.rpg.traveller.generators.base.Dice.`2d6`
import michalz.rpg.traveller.generators.base.Dice.d6
import michalz.rpg.traveller.generators.base.Dice.DiceGen
import michalz.rpg.traveller.generators.megatraveller.starsystem.Orbit
import org.scalacheck.Gen

object OrbitsGenerator:
  def companionOrbitGenerator(dice: DiceGen = `2d6`): Gen[Orbit] =

    val Adjust = 3

    def staticNumber(roll: Int): Gen[Orbit] = Gen.const(Orbit.fromNumber(roll - Adjust))
    def rolledNumber(roll: Int): Gen[Orbit] = d6.map(num => Orbit.fromNumber(num + roll - Adjust))

    TableGen(
      dice,
      TableRow(2, 3, Orbit.Close),
      TableRow(4, 6, staticNumber),
      TableRow(7, 11, rolledNumber),
      TableRow(12, Orbit.Far),
    )
