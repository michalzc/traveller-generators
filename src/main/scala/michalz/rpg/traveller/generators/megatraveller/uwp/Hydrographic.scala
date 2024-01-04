package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.either.*

enum Hydrographic(
    val description: String,
    val percentage: Byte,
    val minimumPercentage: Byte,
    val maximumPercentage: Byte,
) extends UWPElement:

  case `0` extends Hydrographic("Desert World", 0, 0, 4)
  case `1` extends Hydrographic("Dry World", 10, 5, 14)
  case `2` extends Hydrographic("Dry World", 20, 15, 24)
  case `3` extends Hydrographic("Wet World", 30, 25, 34)
  case `4` extends Hydrographic("Wet World", 40, 35, 44)
  case `5` extends Hydrographic("Wet World", 50, 45, 54)
  case `6` extends Hydrographic("Wet World", 60, 55, 64)
  case `7` extends Hydrographic("Wet World", 70, 65, 74)
  case `8` extends Hydrographic("Wet World", 80, 75, 84)
  case `9` extends Hydrographic("Wet World", 90, 85, 94)
  case `A` extends Hydrographic("Water World", 100, 95, 100)

object Hydrographic:
  def fromSymbol(symbol: String): Option[Hydrographic] =
    (
      for {
        decimalRepresentation <- Either.catchNonFatal(Integer.parseInt(symbol, 16))
        value                 <- Either.catchNonFatal(Hydrographic.fromOrdinal(decimalRepresentation))
      } yield value
    ).toOption
