package michalz.rpg.traveller.generators.worldbuilder

import com.typesafe.scalalogging.LazyLogging
import munit.FunSuite

class StarGeneratorTest extends FunSuite with LazyLogging {
  test("Should generate a primary star") {
    val star = StarGenerator.genStar(StarGeneratorType.Primary).sample
    assert(star.isDefined)
    logger.info(s"Generated star: ${star.get}")
  }
}
