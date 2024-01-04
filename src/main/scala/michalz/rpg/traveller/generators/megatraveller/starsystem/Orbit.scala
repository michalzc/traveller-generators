package michalz.rpg.traveller.generators.megatraveller.starsystem

sealed trait Orbit

object Orbit:
  case class OrbitNumber(orbitPosition: Int) extends Orbit
  case object Close                          extends Orbit
  case object Far                            extends Orbit

  def fromNumber(orbitPosition: Int): Orbit = OrbitNumber(orbitPosition)

  implicit val orbitNumberOrdering: Ordering[Orbit] =
    new Ordering[Orbit]:
      override def compare(x: Orbit, y: Orbit): Int =
        (x, y) match
          case (x, y) if x == y =>
            0
          case (Close, _) =>
            -1
          case (_, Close) =>
            1
          case (Far, _) =>
            1
          case (_, Far) =>
            -1
          case (OrbitNumber(x), OrbitNumber(y)) =>
            x.compare(y)
