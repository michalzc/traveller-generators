package michalz.rpg.traveller.generators.worldbuilder

enum StarType:
  case O, B, A, F, G, K, M, Special

enum StarClass:
  case Ia, Ib, II, III, IV, V, VI, D, BD

sealed trait StarLike

case class Star(
                 starType: StarType,
                 starClass: StarClass,
                 starSubtype: Option[Int]
               ) extends StarLike {

  override def toString: String = s"${starType}${starSubtype.getOrElse("")} ${starClass}"
}


case class BlackHole(desc: String) extends StarLike
case class Pulsar(desc: String) extends StarLike
case class NeutronStar(desc: String) extends StarLike
case class Nebula(desc: String) extends StarLike
case class ProtoStar(desc: String) extends StarLike
case class StarCluster(desc: String) extends StarLike
case class Anomaly(desc: String) extends StarLike





