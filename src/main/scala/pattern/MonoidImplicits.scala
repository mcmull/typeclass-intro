package pattern

import scalaz._ // provides Monoid
import types.{Min, Max}

object MonoidImplicits {
  import scala.math._

  implicit val minInstance = new Monoid[Min] {
    override def zero: Min = Min(Int.MaxValue)

    override def append(f1: Min, f2: => Min): Min = Min(min(f1.n, f2.n))
  }

  implicit val maxInstance = new Monoid[Max] {
    override def zero: Max = Max(Int.MinValue)

    override def append(f1: Max, f2: => Max): Max = Max(max(f1.n, f2.n))
  }
}
