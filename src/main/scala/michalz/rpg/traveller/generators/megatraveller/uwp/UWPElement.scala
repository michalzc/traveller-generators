package michalz.rpg.traveller.generators.megatraveller.uwp

import scala.reflect.Enum

trait UWPElement:
  self: Enum =>

  def numericValue: Int = self.ordinal
