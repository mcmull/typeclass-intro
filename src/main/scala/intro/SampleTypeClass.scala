package intro

import types.Max

// implements the SampleTypeClass pseudo code in the presentation slide
trait SampleTypeClass[T] {
  def identityValue: T

  def mash(x: T,  y: T): T
}

object UseSampleTypeClassNatively {
  // implements the polymorphicFunc in the presentation slide
  // T: SampleTypeClass means T should satisfy SampleTypeClass reqt.
  // such that an implicit SampleTypeClass[T] instance can be obtained
  def polymorphicFunc[T: SampleTypeClass](xs: List[T]): T = {
    val m: SampleTypeClass[T] = implicitly[SampleTypeClass[T]]
    xs.foldLeft(m.identityValue)((x, y) => m.mash(x, y))
  }
}

object CallPolymorphicFunc {
  // provides the implicit instances that satisfy the SampleTypeClass reqt.
  // to cope with the reqt. of the following polymorphicFunc() calls
  import intro.SampleTypeClassImplicits._

  // the inferred type is Int, so Int should satisfy SampleTypeClass reqt. which is available
  // from accumulator.SampleTypeClassImplicits
  // according to the implementation, it will return 6
  val intSummation = UseSampleTypeClassNatively.polymorphicFunc(List(1,2,3))

  // same mechanism, the type is String, it will return "helloworld"
  val stringConcatenation = UseSampleTypeClassNatively.polymorphicFunc(List("hello", "world"))

  // the type is Max, it will return Max(3)
  val maxInt = UseSampleTypeClassNatively.polymorphicFunc(List(1,2,3).map(Max.apply))

  // the following commented code infers the type as Min
  // compilation will fail if it's uncommented due to compilation error
  // "could not find implicit value type accumulator.SampleTypeClass[accumulator.Min]"
  // that means means Min not satisfy the SampleTypeClass reqt.
  // val minInt = polymorphicFunc(List(1,2,3).map(Min.apply))
}

// Similar to UseSampleTypeClassNatively
// But it uses syntactic sugar to simplify polymorphicFunc() implementation
object UseSampleTypeClassBySugar {
  import SyntaxSugar._

  def polymorphicFunc[T: SampleTypeClass](xs: List[T]): T =
    xs.foldLeft(zeroValue[T])((x, y) => x |+| y)
}

