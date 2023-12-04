package michalz.rpg.traveller.generators.worldbuilder

import com.typesafe.scalalogging.LazyLogging
import munit.FunSuite
import org.scalacheck.Gen

class StarTypeAndClassGenTest extends FunSuite with LazyLogging {
  test("Should generate star and class") {
    val result = StarTypeAndClassGen.primaryStarGen().sample
    assert(result.isDefined)
    logger.info(s"Rolled star: ${result.get}")
  }

  test("Should generate special star") {
    val result = StarTypeAndClassGen.primaryStarGen(Gen.const(2)).sample
    assert(result.isDefined)
    logger.info(s"Rolled star: ${result.get}")
  }

  test("Should generate hot star") {
    val result = StarTypeAndClassGen.primaryStarGen(Gen.const(12)).sample
    assert(result.isDefined)
    logger.info(s"Rolled star: ${result.get}")
  }
}
