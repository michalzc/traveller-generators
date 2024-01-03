package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.instances.option.*
import cats.syntax.apply.*

case class UWP(
    portType: PortType,
    worldSize: WorldSize,
    atmosphere: Atmosphere,
    hydrographic: Hydrographic,
    population: Population,
    government: Government,
    lawLevel: LawLevel,
    technologyLevel: TechnologyLevel,
)

object UWP:
  def parseUWP(stringUWP: String): Option[UWP] =
    val PortTypeIndex     = 0
    val WorldSizeIndex    = 1
    val AtmosphereIndex   = 2
    val HydrographicIndex = 3
    val PopulationIndex   = 4
    val GovernmentIndex   = 5
    val LawLevelIndex     = 6
    val TechLevelIndex    = 8

    (
      PortType.fromSymbol(stringUWP.charAt(PortTypeIndex).toString),
      WorldSize.fromSymbol(stringUWP.charAt(WorldSizeIndex).toString),
      Atmosphere.fromSymbol(stringUWP.charAt(AtmosphereIndex).toString),
      Hydrographic.fromSymbol(stringUWP.charAt(HydrographicIndex).toString),
      Population.fromSymbol(stringUWP.charAt(PopulationIndex).toString),
      Government.fromSymbol(stringUWP.charAt(GovernmentIndex).toString),
      LawLevel.fromSymbol(stringUWP.charAt(LawLevelIndex).toString),
      TechnologyLevel.fromSymbol(stringUWP.charAt(TechLevelIndex).toString),
    ).mapN(UWP(_, _, _, _, _, _, _, _))
