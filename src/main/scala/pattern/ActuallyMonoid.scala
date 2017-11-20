package pattern


import types.{Max, Min}

// provides scalaz.Monoid
import scalaz._
// provides Monoid[Int], Monoid[String] instances and syntax sugar of mzero[T] and |+|
import scalaz.Scalaz._

// Note: SampleTypeClass is actualy the concept provided by Monoid
// scalaz already provides the Monoid trait,
// commonly-used implicit instances (such as Int, String) and syntactic sugar


// Same as UseSampleTypeClassBySugar.polymorphicFunc
// But it doesn't need to implement syntactic sugar
// scalaz provides them, they are mzero[T] and |+|
object UseMonoidBySugar {
  def polymorphicFunc[T: Monoid](xs: List[T]): T =
    xs.foldLeft(mzero[T])((x, y) => x |+| y)
}

object CallPolymorphicFunc {
  val intSummation = UseMonoidBySugar.polymorphicFunc(List(1,2,3))
  val stringConcatenation = UseMonoidBySugar.polymorphicFunc(List("hello", "world"))

  import MonoidImplicits._  // provides Monoid[Min], Monoid[Max] instance to make the following calls compiled
  val maxInt = UseMonoidBySugar.polymorphicFunc(List(1,2,3).map(Max.apply))
  val minInt = UseMonoidBySugar.polymorphicFunc(List(1,2,3).map(Min.apply))
}

