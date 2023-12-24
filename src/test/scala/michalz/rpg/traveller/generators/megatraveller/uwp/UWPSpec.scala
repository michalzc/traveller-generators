package michalz.rpg.traveller.generators.megatraveller.uwp

import munit.FunSuite

class UWPSpec extends FunSuite {

  test("parseUWP should correctly parse UWP for Regina") {
    val uwpStr = "A788899-C"
    val result = UWP.parseUWP(uwpStr)

    result match {
      case Some(uwp) =>
        assertEquals(uwp.portType, StarPort.A)
        assertEquals(uwp.worldSize, WorldSize.`7`)
        assertEquals(uwp.atmosphere, Atmosphere.`8`)
        assertEquals(uwp.hydrographic, Hydrographic.`8`)
        assertEquals(uwp.population, Population.`8`)
        assertEquals(uwp.government, Government.`9`)
        assertEquals(uwp.lawLevel, LawLevel.`9`)
        assertEquals(uwp.technologyLevel, TechnologyLevel.`C`)
      case None => fail("Parsing returned None for valid UWP string")
    }
  }
}