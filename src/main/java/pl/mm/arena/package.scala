package pl.mm

import scala.util.Random
package object arena {
  type Position = (Int,Int)  // row, col
  type Direction = String

  implicit class ListOps[T](val l: List[T]) extends AnyVal {
    def shuffle : List[T] = {
      val indexedList = l map {v => (Random.nextInt,v)}
      (indexedList.sortBy {case (i,v) => i} unzip)._2  
    }
  }
}