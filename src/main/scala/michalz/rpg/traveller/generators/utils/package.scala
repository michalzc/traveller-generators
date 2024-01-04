package michalz.rpg.traveller.generators

package object utils {
  def groupResults[T: Ordering](results: List[T]): List[(T, Int)] = results
    .groupBy(i => i)
    .toList
    .map { case (v, a) =>
      v -> a.size
    }
    .sortBy(_._1)

  def histogram[T](data: List[(T, Int)], barWidth: Int = 20): List[String] =
    val maxVal        = data.map(_._2).max
    val maxLabelWidth = data.map(_._1.toString.length).max
    val maxValueWith  = data.map(_._2.toString.length).max
    val factor        = barWidth.toDouble / maxVal
    val labelFormat   = s"%1$$${maxLabelWidth}s"
    val valueFormat   = s"%1$$${maxValueWith}s"
    data.map { case (i, value) =>
      val label       = labelFormat.format(i.toString)
      val valueString = valueFormat.format(value.toString)
      val bar         = List.fill(scala.math.round(factor * value).toInt)('#').mkString
      s"$label ($valueString) - $bar"
    }
}
