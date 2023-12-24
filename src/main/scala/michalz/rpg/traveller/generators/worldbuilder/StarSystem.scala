package michalz.rpg.traveller.generators.worldbuilder

case class StarSystem(
    primaryStar: StarLike,
    companionStar: Option[StarLike],
    closeOrbitStar: Option[StarLike],
    closeOrbitCompanion: Option[StarLike],
    nearOrbitStar: Option[StarLike],
    nearOrbitCompanion: Option[StarLike],
    farOrbitStar: Option[StarLike],
    farOrbitCompanion: Option[StarLike]
)
