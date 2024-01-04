package michalz.rpg.traveller.generators.base

import org.scalacheck.Gen

object GenUtils:
  def toTuple2Gen[T, U](left: Gen[T], right: Gen[U]): Gen[(T, U)] = for {
    leftVal  <- left
    rightVal <- right
  } yield (leftVal, rightVal)
