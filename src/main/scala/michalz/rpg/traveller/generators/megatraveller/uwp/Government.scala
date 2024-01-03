package michalz.rpg.traveller.generators.megatraveller.uwp

import cats.syntax.option.*

enum Government(val description: String) extends UWPElement:

  case `0` extends Government("No Government Structure")
  case `1` extends Government("Company/Corporation")
  case `2` extends Government("Participating Democracy")
  case `3` extends Government("Self-Perpetual Oligarchy")
  case `4` extends Government("Representative Democracy")
  case `5` extends Government("Feudal Technocracy")
  case `6` extends Government("Captive Government/Colony")
  case `7` extends Government("Balkanization")
  case `8` extends Government("Civil Service Bureaucracy")
  case `9` extends Government("Impersonal Bureaucracy")
  case `A` extends Government("Charismatic Dictator")
  case `B` extends Government("Non-Charismatic Leader")
  case `C` extends Government("Charismatic Oligarchy")
  case `D` extends Government("Religious Dictatorship")
  case `E` extends Government("Religious Autocracy")
  case `F` extends Government("Totalitarian Oligarchy")

object Government {
  def fromSymbol(symbol: String): Option[Government] = symbol match {
    case "0" => Government.`0`.some
    case "1" => Government.`1`.some
    case "2" => Government.`2`.some
    case "3" => Government.`3`.some
    case "4" => Government.`4`.some
    case "5" => Government.`5`.some
    case "6" => Government.`6`.some
    case "7" => Government.`7`.some
    case "8" => Government.`8`.some
    case "9" => Government.`9`.some
    case "A" => Government.`A`.some
    case "B" => Government.`B`.some
    case "C" => Government.`C`.some
    case "D" => Government.`D`.some
    case "E" => Government.`E`.some
    case "F" => Government.`F`.some
    case _ => none[Government]
  }
}