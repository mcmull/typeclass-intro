package usecase

import types.{Max, Min}

import scalaz._
import scalaz.Scalaz._

object StatsImplicits {
  implicit val statsMonoid = new Monoid[Stats] {
    import pattern.MonoidImplicits._ // provides Monoid[Min], Monoid[Max]

    override def zero: Stats =
      Stats(mzero[Min], mzero[Max], mzero[Int], mzero[Int])

    override def append(f1: Stats, f2: => Stats): Stats =
      Stats(
        f1.min |+| f2.min,
        f1.max |+| f2.max,
        f1.total |+| f2.total,
        f1.noOfRecs |+| f2.noOfRecs
      )
  }

//   alternatively, a de-sugar implementation
//  implicit val statsMonoid = new Monoid[Stats] {
//    import pattern.MonoidImplicits._
//
//    val min = implicitly[Monoid[Min]]
//    val max = implicitly[Monoid[Max]]
//    val int = implicitly[Monoid[Int]]
//
//    override def zero: Stats =
//      Stats(min.zero, max.zero, int.zero, int.zero)
//
//    override def append(f1: Stats, f2: => Stats): Stats =
//      Stats(
//        min.append(f1.min, f2.min),
//        max.append(f1.max, f2.max),
//        int.append(f1.total, f2.total),
//        int.append(f1.noOfRecs, f2.noOfRecs)
//      )
//  }
}
