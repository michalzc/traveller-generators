package michalz.rpg.traveller.generators.worldbuilder

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.funspec.AnyFunSpec

class StarGeneratorTest extends AnyFunSpec with LazyLogging {
  describe("Should generate a primary star") {
    it("should generate a star") {
      val star = StarGenerator.genStar(StarGeneratorType.Primary).sample
      assert(star.isDefined)
      logger.info(s"Generated star: ${star.get}")
    }
  }
}
