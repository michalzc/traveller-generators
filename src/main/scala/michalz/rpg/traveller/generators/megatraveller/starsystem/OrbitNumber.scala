package michalz.rpg.traveller.generators.megatraveller.starsystem

enum OrbitNumber:
  case Close
  case `1`
  case `2`
  case `3`
  case `4`
  case `5`
  case `6`
  case `7`
  case `8`
  case `9`
  case `10`
  case `11`
  case `12`

object OrbitNumber:
  implicit val ordering: Ordering[OrbitNumber] = Ordering.by(_.ordinal)