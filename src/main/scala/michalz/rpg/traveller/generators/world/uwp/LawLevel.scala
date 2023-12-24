package michalz.rpg.traveller.generators.world.uwp

import cats.syntax.option._

enum LawLevel(val description: String) extends UWPElement:
  override def numericValue: Int = this.ordinal
  case `0` extends LawLevel("No law (no prohibitions)")
  case `1` extends LawLevel("Low law (body pistols, explosives, poison gas prohibited)")
  case `2` extends LawLevel("Low law (portable energy weapons prohibited)")
  case `3` extends LawLevel("Low law (machine guns, automatic rifles prohibited)")
  case `4` extends LawLevel("Moderate law (light assault weapons prohibited)")
  case `5` extends LawLevel("Moderate law (personal concealable weapons prohibited)")
  case `6` extends LawLevel("Moderate law (all firearms except shotguns prohibited)")
  case `7` extends LawLevel("Moderate law (shotguns prohibited)")
  case `8` extends LawLevel("High law (blade weapons controlled, no open display)")
  case `9` extends LawLevel("High law (weapon possession outside the home prohibited)")
  case `A` extends LawLevel("Extreme law (weapon possession prohibited)")
  case `B` extends LawLevel("Extreme law (rigid control of civilian movement)")
  case `C` extends LawLevel("Extreme law (unrestricted invasion of privacy)")
  case `D` extends LawLevel("Extreme law (paramilitary law enforcement)")
  case `E` extends LawLevel("Extreme law (full-fledged police state)")
  case `F` extends LawLevel("Extreme law (all facets of daily life rigidly controlled)")
  case `G` extends LawLevel("Extreme law (severe punishment for petty infractions)")
  case `H` extends LawLevel("Extreme law (legalized oppressive practices)")
  case `J` extends LawLevel("Extreme law (excessively oppressive and restrictive)")
  case `K` extends LawLevel("Extreme law (totally oppressive and restrictive)")
  case `L` extends LawLevel("Extreme law (totally oppressive and restrictive)")

object LawLevel:
  def fromSymbol(symbol: String): Option[LawLevel] = symbol match
    case "0" => LawLevel.`0`.some
    case "1" => LawLevel.`1`.some
    case "2" => LawLevel.`2`.some
    case "3" => LawLevel.`3`.some
    case "4" => LawLevel.`4`.some
    case "5" => LawLevel.`5`.some
    case "6" => LawLevel.`6`.some
    case "7" => LawLevel.`7`.some
    case "8" => LawLevel.`8`.some
    case "9" => LawLevel.`9`.some
    case "A" => LawLevel.`A`.some
    case "B" => LawLevel.`B`.some
    case "C" => LawLevel.`C`.some
    case "D" => LawLevel.`D`.some
    case "E" => LawLevel.`E`.some
    case "F" => LawLevel.`F`.some
    case "G" => LawLevel.`G`.some
    case "H" => LawLevel.`H`.some
    case "J" => LawLevel.`J`.some
    case "K" => LawLevel.`K`.some
    case "L" => LawLevel.`L`.some
    case _ => none