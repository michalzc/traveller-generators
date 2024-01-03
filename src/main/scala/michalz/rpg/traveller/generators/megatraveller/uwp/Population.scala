package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.option._

enum Population(val description: String, val min: Long, val max: Long) extends UWPElement:

  case `0` extends Population("Low (less than ten)", 0L, 9L)
  case `1` extends Population("Low (tens)", 10L, 99L)
  case `2` extends Population("Low (hundreds)", 100L, 999L)
  case `3` extends Population("Low (thousands)", 1000L, 9999L)
  case `4` extends Population("Mod (tens of thousands)", 10000L, 99999L)
  case `5` extends Population("Mod (hundreds of thousands)", 100000L, 999999L)
  case `6` extends Population("Mod (millions)", 1000000L, 9999999L)
  case `7` extends Population("Mod (tens of millions)", 10000000L, 99999999L)
  case `8` extends Population("Mod (hundreds of millions)", 100000000L, 999999999L)
  case `9` extends Population("High (billions)", 1000000000L, 9999999999L)
  case `A` extends Population("High (ten of billions)", 10000000000L, 99999999999L)

object Population:
  def fromSymbol(symbol: String): Option[Population] = symbol match
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
    case _   => none[Population]
