package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.option._

enum TechnologyLevel(val description: String) extends UWPElement:
  override def numericValue: Int = this.ordinal

  case `0` extends TechnologyLevel("Pre-industrial (primitive)")
  case `1` extends TechnologyLevel("Pre-industrial (bronze, iron)")
  case `2` extends TechnologyLevel("Pre-industrial (printing press)")
  case `3` extends TechnologyLevel("Pre-industrial (basic science)")
  case `4` extends TechnologyLevel("Industrial (internal combustion)")
  case `5` extends TechnologyLevel("Industrial (mass production)")
  case `6` extends TechnologyLevel("Pre-Stellar (nuclear power)")
  case `7` extends TechnologyLevel("Pre-Stellar (microelectronics)")
  case `8` extends TechnologyLevel("Pre-Stellar (superconductors)")
  case `9` extends TechnologyLevel("Early Stellar (fusion power)")
  case `A` extends TechnologyLevel("Early Stellar (jump drive)")
  case `B` extends TechnologyLevel("Average Stellar (large starships)")
  case `C` extends TechnologyLevel("Average Stellar (sophisticated robots)")
  case `D` extends TechnologyLevel("Average Stellar (holographic data storage)")
  case `E` extends TechnologyLevel("High Stellar (anti-grav cities)")
  case `F` extends TechnologyLevel("High Stellar (anagathics)")
  case `G` extends TechnologyLevel("High Stellar (global terraforming)")
  case `H` extends TechnologyLevel("Extreme Stellar")
  case `J` extends TechnologyLevel("Extreme Stellar")
  case `K` extends TechnologyLevel("Extreme Stellar")
  case `L` extends TechnologyLevel("Extreme Stellar")

object TechnologyLevel:
  def fromSymbol(symbol: String): Option[TechnologyLevel] =
    symbol match
      case "0" => `0`.some
      case "1" => `1`.some
      case "2" => `2`.some
      case "3" => `3`.some
      case "4" => `4`.some
      case "5" => `5`.some
      case "6" => `6`.some
      case "7" => `7`.some
      case "8" => `8`.some
      case "9" => `9`.some
      case "A" => `A`.some
      case "B" => `B`.some
      case "C" => `C`.some
      case "D" => `D`.some
      case "E" => `E`.some
      case "F" => `F`.some
      case "G" => `G`.some
      case "H" => `H`.some
      case "J" => `J`.some
      case "K" => `K`.some
      case "L" => `L`.some
      case _   => none
