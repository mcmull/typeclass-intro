package usecase

import pattern.UseMonoidBySugar
import types.{Max, Min}

case class Stock(ticker: String, date: String, price: Int)

case class Stats(min: Min, max: Max, total: Int, noOfRecs: Int)

// Problem: from the given stocks, find the min price, max price, summation of prices and no of stocks
object ProblemToSolve {
  val stocks = List(
    Stock("FAKE", "2012-01-01", 10000)
    , Stock("FAKE", "2012-01-02", 10020)
    , Stock("FAKE", "2012-01-03", 10022)
    , Stock("FAKE", "2012-01-04", 10005)
    , Stock("FAKE", "2012-01-05",  9911)
    , Stock("FAKE", "2012-01-06",  6023)
    , Stock("FAKE", "2012-01-07",  7019)
    , Stock("FAKE", "2012-01-08",     0)
    , Stock("FAKE", "2012-01-09",  7020)
    , Stock("FAKE", "2012-01-10",  7020)
    , Stock("CAKE", "2012-01-01",     1)
    , Stock("CAKE", "2012-01-02",     2)
    , Stock("CAKE", "2012-01-03",     3)
    , Stock("CAKE", "2012-01-04",     4)
    , Stock("CAKE", "2012-01-05",     5)
    , Stock("CAKE", "2012-01-06",     6)
    , Stock("CAKE", "2012-01-07",     7)
    , Stock("BAKE", "2012-01-01", 99999)
    , Stock("BAKE", "2012-01-02", 99999)
    , Stock("BAKE", "2012-01-03", 99999)
    , Stock("BAKE", "2012-01-04", 99999)
    , Stock("BAKE", "2012-01-05", 99999)
    , Stock("BAKE", "2012-01-06",     0)
    , Stock("BAKE", "2012-01-07", 99999)
    , Stock("LAKE", "2012-01-01", 10012)
    , Stock("LAKE", "2012-01-02",  7000)
    , Stock("LAKE", "2012-01-03",  1234)
    , Stock("LAKE", "2012-01-04",    10)
    , Stock("LAKE", "2012-01-05",  6000)
    , Stock("LAKE", "2012-01-06",  6099)
    , Stock("LAKE", "2012-01-07",  5999)
  )

  import StatsImplicits._ // provides Monoid[Stats] instance

  // solution 1 - reuse UseMonoidBySugar.polymorphicFunc
  val statsList: List[Stats] = stocks.map { case Stock(_, _, p) =>
    Stats(Min(p), Max(p), p, 1)
  }
  val answer1 = UseMonoidBySugar.polymorphicFunc(statsList)

  // solution 2
  import scalaz.Scalaz._
  val answer2 = stocks.foldLeft(mzero[Stats]){ case (stats, Stock(_, _, p)) =>
    stats |+| Stats(Min(p), Max(p), p, 1)
  }
}

