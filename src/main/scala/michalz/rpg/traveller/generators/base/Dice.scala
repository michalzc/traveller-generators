package michalz.rpg.traveller.generators.base

import org.scalacheck.Gen

object Dice {

  type DiceGen = Gen[Int]

  implicit class IntDice(self: Int) {
    def x(dice: => DiceGen): NumDice = NumDice(self, () => dice)
  }

  implicit class DiceExt(self: DiceGen) {
    def +(num: Int): Gen[Int] = self.map(_ + num)
    def -(num: Int): Gen[Int] = self.map(_ - num)
  }

  case class NumDice(num: Int, dice: () => DiceGen) {
    def kh(knum: Int): DiceGen =
      require(num >= knum)
      Gen
        .listOfN(num, dice())
        .map(results => results.sorted.drop(num - knum).sum)

    def kl(knum: Int): DiceGen =
      require(num >= knum)
      Gen.listOfN(num, dice()).map(results => results.sorted.take(knum).sum)

    def +(num: Int): DiceGen = toDice + num
    def -(num: Int): DiceGen = toDice - num

    def toDice: DiceGen = Gen.listOfN(num, dice()).map(_.sum)
  }

  implicit val numDiceToDice: Conversion[NumDice, DiceGen] = (x: NumDice) => x.toDice

  def d(num: Int): DiceGen = Gen.choose(1, num)

  def `2d6`: DiceGen = (2 x d(6)).toDice

  def d6: DiceGen = d(6)

}
