package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.either.*
import cats.syntax.option.*
import scala.util.control.Exception.catching

enum Atmosphere(val description: String, val minPressure: Option[Float], val maxPressure: Option[Float])
    extends UWPElement:

  case `0` extends Atmosphere("Vacuum", none, 0f.some)
  case `1` extends Atmosphere("Vacuum (trace)", 0.001f.some, 0.09f.some)
  case `2` extends Atmosphere("Vacuum (very thin, tainted)", 0.1f.some, 0.42f.some)
  case `3` extends Atmosphere("Vacuum (very thin)", 0.1f.some, 0.42f.some)
  case `4` extends Atmosphere("Thin (tainted)", 0.43f.some, 0.7f.some)
  case `5` extends Atmosphere("Thin", 0.43f.some, 0.7f.some)
  case `6` extends Atmosphere("Standard", 0.71f.some, 1.49f.some)
  case `7` extends Atmosphere("Standard (tainted)", 0.71f.some, 1.49f.some)
  case `8` extends Atmosphere("Dense", 1.5f.some, 2.49f.some)
  case `9` extends Atmosphere("Dense (tainted)", 1.5f.some, 2.49f.some)
  case `A` extends Atmosphere("Exotic", none, none)
  case `B` extends Atmosphere("Exotic (corrosive)", none, none)
  case `C` extends Atmosphere("Exotic (insidious)", none, none)
  case `D` extends Atmosphere("Exotic (dense, high)", none, none)
  case `E` extends Atmosphere("Exotic (ellipsoid)", none, none)
  case `F` extends Atmosphere("Exotic (thin, low)", none, none)

object Atmosphere:
  def fromSymbol(symbol: String): Option[Atmosphere] =
    (
      for {
        decimal <- Either.catchNonFatal(Integer.parseInt(symbol, 16))
        value   <- Either.catchNonFatal(Atmosphere.fromOrdinal(decimal))
      } yield value
    ).toOption
