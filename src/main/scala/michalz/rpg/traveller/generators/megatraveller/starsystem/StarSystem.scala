package michalz.rpg.traveller.generators.megatraveller.starsystem

import scala.collection.immutable.TreeMap

case class StarSystem(
    mainBody: SystemBody,
    bodies: Map[Orbit, SystemBody],
) extends SystemBody:
  def get(orbitNumber: Orbit): Option[SystemBody] =
    bodies.get(orbitNumber)

  def update(orbitNumber: Orbit, body: SystemBody): StarSystem =
    this.copy(bodies = bodies.updated(orbitNumber, body))

  def updateMainWorld(body: SystemBody): StarSystem =
    this.copy(mainBody = body)

object StarSystem:
  def apply(
      mainBody: SystemBody,
      elems: (Orbit, SystemBody)*,
  ): StarSystem =
    StarSystem(mainBody = mainBody, bodies = TreeMap(elems: _*))

  def apply(mainBody: SystemBody): StarSystem =
    StarSystem(mainBody, TreeMap.empty[Orbit, SystemBody])
