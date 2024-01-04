package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.option.*
import michalz.rpg.traveller.generators.megatraveller.uwp.*
import michalz.rpg.traveller.generators.megatraveller.uwp.Fuel.Refined
import michalz.rpg.traveller.generators.megatraveller.uwp.Fuel.Unrefined
import michalz.rpg.traveller.generators.megatraveller.uwp.Quality.*
import michalz.rpg.traveller.generators.megatraveller.uwp.Repair.MajorDamage
import michalz.rpg.traveller.generators.megatraveller.uwp.Repair.MinorDamage
import michalz.rpg.traveller.generators.megatraveller.uwp.Repair.Overhaul
import michalz.rpg.traveller.generators.megatraveller.uwp.Repair.Superficial
import michalz.rpg.traveller.generators.megatraveller.uwp.Shipyard.Spacecrafts
import michalz.rpg.traveller.generators.megatraveller.uwp.Shipyard.Starships

enum Quality:
  case Excellent
  case Good
  case Routine
  case Poor
  case Frontier
  case Primitive
  case None

enum Fuel:
  case Refined
  case Unrefined
  case None

enum Repair(description: String):
  case Overhaul    extends Repair("Overhaul")
  case MajorDamage extends Repair("Major Damage")
  case MinorDamage extends Repair("Minor Damage")
  case Superficial extends Repair("Superficial")
  case None        extends Repair("None")

enum Shipyard:
  case Starships
  case Spacecrafts
  case None

sealed trait PortType:
  def quality: Quality
  def fuel: Fuel
  def repair: Repair
  def shipyard: Shipyard

enum StarPort(val quality: Quality, val shipyard: Shipyard, val repair: Repair, val fuel: Fuel) extends PortType:
  case `A` extends StarPort(Excellent, Starships, Overhaul, Refined)
  case `B` extends StarPort(Good, Spacecrafts, Overhaul, Refined)
  case `C` extends StarPort(Routine, Shipyard.None, MajorDamage, Unrefined)
  case `D` extends StarPort(Poor, Shipyard.None, MinorDamage, Unrefined)
  case `E` extends StarPort(Frontier, Shipyard.None, Repair.None, Fuel.None)
  case `X` extends StarPort(Quality.None, Shipyard.None, Repair.None, Fuel.None)

enum SpacePort(val quality: Quality, val shipyard: Shipyard, val repair: Repair, val fuel: Fuel) extends PortType:
  case `F` extends SpacePort(Good, Shipyard.None, MinorDamage, Unrefined)
  case `G` extends SpacePort(Poor, Shipyard.None, Superficial, Unrefined)
  case `H` extends SpacePort(Primitive, Shipyard.None, Repair.None, Fuel.None)
  case `Y` extends SpacePort(Quality.None, Shipyard.None, Repair.None, Fuel.None)

object PortType:
  def fromSymbol(symbol: String): Option[PortType] =
    symbol match {
      case "A" =>
        StarPort.`A`.some
      case "B" =>
        StarPort.`B`.some
      case "C" =>
        StarPort.`C`.some
      case "D" =>
        StarPort.`D`.some
      case "E" =>
        StarPort.`E`.some
      case "F" =>
        SpacePort.`F`.some
      case "G" =>
        SpacePort.`G`.some
      case "H" =>
        SpacePort.`H`.some
      case "Y" =>
        SpacePort.`Y`.some
      case "X" =>
        StarPort.`X`.some
      case _ =>
        none
    }
