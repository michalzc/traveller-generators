package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.option.*

case class PBG(population: Byte, betls: Byte, gasGiants: Byte)

object PBG:
  def parsePBG(pbg: String): Option[PBG] = pbg.split("").filter(_.forall(Character.isDigit)).map(_.toByte) match
    case Array(p, g, b) => PBG(p, b, g).some
    case _              => none
