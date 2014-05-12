package pl.mm.arena

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UpgradeSuite extends FunSuite {
  val mapSlice =
    """123456789
       123456789
       123456789
       123456789
       123456789
       123456789
       123456789
       123456789
       123456789"""

  test("misty lens visibility") {
    val expectedSight =
      """xxxxxxxxx
         xxxxxxxxx
         xxxxxxxxx
         x2345678x
         xx34567xx
         xxx456xxx
         xxxx5xxxx
         xxxxxxxxx
         xxxxxxxxx"""
    val sight = SightUpgrades.look(mapSlice, SightUpgrade("Misty lens", 0, SightUpgrades.mistyLensVisibility))
    assert(sight.lines.size == expectedSight.lines.size)
    val zipped = sight.lines zip expectedSight.lines
    for ((line, expected) <- zipped) assert(line == expected.trim)
  }

  test("clean lens visibility") {
    val expectedSight =
      """xxxxxxxxx
         xxxxxxxxx
         xxxxxxxxx
         123456789
         x2345678x
         xx34567xx
         xxx456xxx
         xxxxxxxxx
         xxxxxxxxx"""
    val sight = SightUpgrades.look(mapSlice, SightUpgrade("Clean lens", 0, SightUpgrades.cleanLensVisibility))
    assert(sight.lines.size == expectedSight.lines.size)
    val zipped = sight.lines zip expectedSight.lines
    for ((line, expected) <- zipped) assert(line == expected.trim)
  }

  test("short radar visibility") {
    val expectedSight =
      """xxxxxxxxx
         xxxxxxxxx
         xxxxxxxxx
         xxxxxxxxx
         xx34567xx
         xx34567xx
         xx34567xx
         xx34567xx
         xxxxxxxxx"""
    val sight = SightUpgrades.look(mapSlice, SightUpgrade("Short radar", 0, SightUpgrades.shortRadarVisibility))
    assert(sight.lines.size == expectedSight.lines.size)
    val zipped = sight.lines zip expectedSight.lines
    for ((line, expected) <- zipped) assert(line == expected.trim)
  }
  
}