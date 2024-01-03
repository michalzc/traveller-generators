package michalz.rpg.traveller.generators.megatraveller.starsystem

import scala.collection.immutable.TreeMap

case class StarSystem(
    mainBody: SystemBody,
    bodies: Map[OrbitNumber, SystemBody],
) extends SystemBody:
  def get(orbitNumber: OrbitNumber): Option[SystemBody] =
    bodies.get(orbitNumber)

  def update(orbitNumber: OrbitNumber, body: SystemBody): StarSystem =
    this.copy(bodies = bodies.updated(orbitNumber, body))

object StarSystem:
  def apply(
      mainBody: SystemBody,
      elems: (OrbitNumber, SystemBody)*,
  ): StarSystem =
    StarSystem(mainBody = mainBody, bodies = TreeMap(elems: _*))

  def apply(mainBody: SystemBody): StarSystem =
    StarSystem(mainBody, TreeMap.empty[OrbitNumber, SystemBody])
