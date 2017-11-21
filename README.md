# Type class introduction

Suggest to read the source code in the order as described here

##  `SampleTypeClass.scala`
* A type class example that specifies expected functions applicable on a **Type**
* Illustrate how to use this type class 

To try it, execute `sbt console` and on the REPL, run the following commands to run the polymorphic function
```Scala
scala> import intro.CallPolymorphicFunc._
import intro.CallPolymorphicFunc._

scala> intSummation
res0: Int = 6

scala> stringConcatenation
res1: String = helloworld

scala> maxInt
res2: types.Max = Max(3)
```

To test another function `UseSampleTypeClassBySugar.polymorphicFunc` that uses syntactic sugar, 
```Scala
scala> import intro.UseSampleTypeClassBySugar._
import intro.UseSampleTypeClassBySugar._

scala> import types.{Min, Max}
import types.{Min, Max}

scala> polymorphicFunc(List(1,2,3))

scala> import types.{Min, Max}
import types.{Min, Max}

scala> import intro.UseSampleTypeClassBySugar._
import intro.UseSampleTypeClassBySugar._

scala> import intro.SampleTypeClassImplicits._
import intro.SampleTypeClassImplicits._

scala> polymorphicFunc(List(1,2,3))
res0: Int = 6

scala> polymorphicFunc(List("hello", "world"))
res1: String = helloworld

scala> polymorphicFunc(List(1,2,3).map(Max.apply))
res2: types.Max = Max(3)
```

## `ActuallyMonoid.scala`
The type class example `SampleTypeClass` is actually the concept of `Monoid` which is already provided by scalaz with a number of commonly-used `Monoid[T]` instances and syntactic sugar.

To try it, 
```Scala
scala> import pattern.CallPolymorphicFunc._
import pattern.CallPolymorphicFunc._

scala> intSummation
res0: Int = 6

scala> stringConcatenation
res1: String = helloworld

scala> maxInt
res2: types.Max = Max(3)

scala> minInt
res3: types.Min = Min(1)
```

## `ProblemToSolve.scala`
A sample problem to be solved using the Type class idea and 2 solutions are provided.

To try it,
```Scala
scala> import usecase.ProblemToSolve._
import usecase.ProblemToSolve._

scala> answer1
res0: usecase.Stats = Stats(Min(0),Max(99999),713416,31)

scala> answer2
res1: usecase.Stats = Stats(Min(0),Max(99999),713416,31)
``` 