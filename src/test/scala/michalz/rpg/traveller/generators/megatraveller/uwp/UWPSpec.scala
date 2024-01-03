package michalz.rpg.traveller.generators.megatraveller.uwp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UWPSpec extends AnyFunSpec with Matchers {

  describe("parseUWP") {
    it("should correctly parse UWP for Regina") {
      val uwpStr = "A788899-C"
      val result = UWP.parseUWP(uwpStr)

      result match {
        case Some(uwp) =>
          uwp.portType shouldBe StarPort.A
          uwp.worldSize shouldBe WorldSize.`7`
          uwp.atmosphere shouldBe Atmosphere.`8`
          uwp.hydrographic shouldBe Hydrographic.`8`
          uwp.population shouldBe Population.`8`
          uwp.government shouldBe Government.`9`
          uwp.lawLevel shouldBe LawLevel.`9`
          uwp.technologyLevel shouldBe TechnologyLevel.`C`
        case None => fail("Parsing returned None for valid UWP string")
      }
    }
  }
}
