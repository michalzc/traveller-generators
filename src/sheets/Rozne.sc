import michalz.rpg.traveller.generators.base.Dice
import michalz.rpg.traveller.generators.base.Dice.DiceGen
import michalz.rpg.traveller.generators.world.uwp.{Atmosphere, StarPort}
import org.scalacheck.Gen

import scala.reflect

val atm = Atmosphere.`5`

atm.ordinal

atm.toString