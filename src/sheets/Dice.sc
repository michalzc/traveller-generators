import michalz.rpg.traveller.generators.base.*

def testGen(gen: => DiceGen, num: Int = 10000) =
  List
    .fill(num)(gen)
    .flatMap(_.sample)
    .groupBy(i => i)
    .toList
    .map(e => e._1 -> e._2.size)
    .sortBy(_._1)

def histogram[T](data: List[(T, Int)], barWidth: Int = 20) =
  val maxVal = data.map(_._2).max
  val maxLabelWidth = data.map(_._1.toString.length).max
  val maxValueWith = data.map(_._2.toString.length).max
  val factor = barWidth.toDouble / maxVal
  val labelFormat = s"%1$$${maxLabelWidth}s"
  val valueFormat = s"%1$$${maxValueWith}s"
  data.map { case (i, value) =>
    val label = labelFormat.format(i.toString)
    val valueString = valueFormat.format(value.toString)
    val bar = List.fill(scala.math.round(factor * value).toInt)('#').mkString
    s"$label ($valueString) - $bar"
  }

histogram(testGen((2 x d(6)) + 2), 50).foreach(println)

