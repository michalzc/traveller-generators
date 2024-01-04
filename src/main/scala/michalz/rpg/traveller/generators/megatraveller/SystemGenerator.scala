package michalz.rpg.traveller.generators.megatraveller

import cats.syntax.option.*
import michalz.rpg.traveller.generators.base.table.TableGen
import michalz.rpg.traveller.generators.base.table.TableRow
import michalz.rpg.traveller.generators.base.Dice.`2d6`
import michalz.rpg.traveller.generators.base.Dice.DiceExt
import michalz.rpg.traveller.generators.base.Dice.DiceGen
import michalz.rpg.traveller.generators.base.GenUtils
import michalz.rpg.traveller.generators.base.GenUtils.toTuple2Gen
import michalz.rpg.traveller.generators.megatraveller.starsystem.Nature
import michalz.rpg.traveller.generators.megatraveller.starsystem.Orbit
import michalz.rpg.traveller.generators.megatraveller.starsystem.Star
import michalz.rpg.traveller.generators.megatraveller.starsystem.StarSystem
import michalz.rpg.traveller.generators.megatraveller.uwp.PBG
import michalz.rpg.traveller.generators.megatraveller.uwp.UWP
import michalz.rpg.traveller.generators.megatraveller.StarGenerator.otherStarGenerator
import org.scalacheck.Gen
import OrbitsGenerator.companionOrbitGenerator

object SystemGenerator:

  def systemNatureGenerator(dice: DiceGen = `2d6`): Gen[Nature] = TableGen(
    dice,
    TableRow(2, 7, Nature.Solo),
    TableRow(8, 11, Nature.Binary),
    TableRow(12, Nature.Trinary),
  )

  def otherStarsGenerator(
      mainStarWithRolls: StarWithRolls,
      nature: Nature,
      uwp: Option[UWP],
      dice: DiceGen = `2d6`,
  ): Gen[List[(Orbit, Star)]] =
    nature match
      case Nature.Solo =>
        handleSoloNature()
      case Nature.Binary =>
        handleBinaryNature(mainStarWithRolls, dice)
      case Nature.Trinary =>
        handleTrinaryNature(mainStarWithRolls, dice)

  private def handleSoloNature(): Gen[List[(Orbit, Star)]] = Gen.const(List.empty)

  private def handleBinaryNature(mainStarWithRolls: StarWithRolls, dice: DiceGen): Gen[List[(Orbit, Star)]] =
    toTuple2Gen(companionOrbitGenerator(), otherStarGenerator(mainStarWithRolls, dice)).map(List(_))

  private def handleTrinaryNature(mainStarWithRolls: StarWithRolls, dice: DiceGen): Gen[List[(Orbit, Star)]] = Gen
    .sequence(
      List(0, 4)
        .map(orbitAdjust => (companionOrbitGenerator(dice + orbitAdjust), otherStarGenerator(mainStarWithRolls, dice)))
        .map { case (og, sg) =>
          toTuple2Gen(og, sg)
        },
    )

  def generate(uwp: Option[UWP] = none, pbg: Option[PBG] = none, dice: DiceGen = `2d6`): Gen[StarSystem] =
    for {
      mainStar <- StarGenerator.primaryStar(uwp)
      nature   <- systemNatureGenerator()
    } yield StarSystem(mainStar.star)
