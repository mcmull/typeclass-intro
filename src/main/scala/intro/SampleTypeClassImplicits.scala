package intro

import types.Max

object SampleTypeClassImplicits {
  // make type Int satisfy SampleTypeClass
  implicit val intInstance = new SampleTypeClass[Int] {
    override def identityValue: Int = 0

    override def mash(x: Int, y: Int): Int = x + y
  }

  // make type String satisfy SampleTypeClass
  implicit val stringInstance = new SampleTypeClass[String] {
    override def identityValue: String = ""

    override def mash(x: String, y: String): String = x + y
  }

  // make type Max satisfy SampleTypeClass
  implicit val maxInstance = new SampleTypeClass[Max] {
    override def identityValue: Max = Max(Int.MinValue)

    override def mash(x: Max, y: Max): Max = Max(scala.math.max(x.n, y.n))
  }
}

object SyntaxSugar {
  implicit def zeroValue[T: SampleTypeClass]: T =
    implicitly[SampleTypeClass[T]].identityValue

  implicit class SampleTypeClassOps[T: SampleTypeClass](x: T) {
    def |+|(y: T): T =
      implicitly[SampleTypeClass[T]].mash(x, y)
  }
}

