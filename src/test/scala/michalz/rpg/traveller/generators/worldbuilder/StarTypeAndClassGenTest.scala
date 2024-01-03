package michalz.rpg.traveller.generators.worldbuilder

import com.typesafe.scalalogging.LazyLogging
import org.scalacheck.Gen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class StarTypeAndClassGenTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("StarTypeAndClassGen") {
    it("should generate star and class") {
      val result = StarTypeAndClassGen.primaryStarGen().sample
      assert(result.isDefined)
      logger.info(s"Rolled star: ${result.get}")
    }

    it("should generate special star") {
      val result = StarTypeAndClassGen.primaryStarGen(Gen.const(2)).sample
      assert(result.isDefined)
      logger.info(s"Rolled star: ${result.get}")
    }

    it("should generate hot star") {
      val result = StarTypeAndClassGen.primaryStarGen(Gen.const(12)).sample
      assert(result.isDefined)
      logger.info(s"Rolled star: ${result.get}")
    }
  }
}
