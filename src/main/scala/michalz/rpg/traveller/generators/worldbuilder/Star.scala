package michalz.rpg.traveller.generators.worldbuilder

enum StarType:
  case O, B, A, F, G, K, M, Special

enum StarClass:
  case Ia, Ib, II, III, IV, V, VI, D, BD

case class Star (starType: StarType, starClass: StarClass, starSubtype: Option[Int])
