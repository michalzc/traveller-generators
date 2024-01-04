package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.either.*
import cats.syntax.option.*

enum WorldSize(description: String, diameter: Option[Int], minDiameter: Option[Int], maxDiameter: Option[Int])
    extends UWPElement:

  case `0` extends WorldSize("Asteroid/Planetoid Belt", none, none, 200.some)
  case `1` extends WorldSize("Small", 1600.some, 800.some, 2399.some)
  case `2` extends WorldSize("Small (Luna)", 3200.some, 2400.some, 3999.some)
  case `3` extends WorldSize("Small (Mercury)", 4800.some, 4000.some, 5599.some)
  case `4` extends WorldSize("Small (Mars)", 6400.some, 5600.some, 7199.some)
  case `5` extends WorldSize("Medium", 8000.some, 7200.some, 8799.some)
  case `6` extends WorldSize("Medium", 9600.some, 8800.some, 10399.some)
  case `7` extends WorldSize("Medium", 11200.some, 10400.some, 11999.some)
  case `8` extends WorldSize("Large (Terra)", 12800.some, 12000.some, 13599.some)
  case `9` extends WorldSize("Large", 14400.some, 13600.some, 15199.some)
  case `A` extends WorldSize("Large", 16000.some, 15200.some, 16799.some)

  // additional sizes
  case `R` extends WorldSize("Asteroid/Planetoid Ring", none, none, 1.some)
  case `S` extends WorldSize("Very Small", 400.some, 200.some, 799.some)
  case SGG extends WorldSize("Small Gas Giant", none, none, none)
  case LGG extends WorldSize("Large Gas Giant", none, none, none)

object WorldSize:
  def fromSymbol(symbol: String): Option[WorldSize] =
    (
      for {
        decimal   <- Either.catchNonFatal(Integer.parseInt(symbol, 16))
        _         <- Either.cond(decimal <= 0xa, (), RuntimeException("Invalid value"))
        worldSize <- Either.catchNonFatal(WorldSize.fromOrdinal(decimal))
      } yield worldSize
    ).toOption
