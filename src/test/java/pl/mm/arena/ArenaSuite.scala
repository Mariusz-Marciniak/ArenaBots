package pl.mm.arena

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ArenaSuite extends FunSuite {

  import Arena._
  
  test("should return maximum amount of players equal to starting positions") {
    assert(Arena("WWWooo").maxPlayers == 0)
    assert(Arena("Wo<<<^^>>>vvoW").maxPlayers == 10)
    assert(Arena("""Wo<<<
              ^^>>>
              vvoW""").maxPlayers == 10)
  }

  test("should return starting positions") {
    assert(Arena("WWWooo").startingPositions == List())
    assert(Arena("""Wo<W
              ^WWooWW>
              vvWW""").startingPositions == List((LEFT, (0, 2)), (TOP, (1, 0)), (RIGHT, (1, 7)), (BOTTOM, (2, 0)), (BOTTOM, (2, 1))))

  }

  test("should prepare view for each bot facing left and right directions") {
    val arena = Arena(
    """ooooWoooo
       ooooooooo
       WWWWWWWWW
       ooooooooo
       oW>oWo<Wo
       ooooooooo
       WWWWWWWWW
       ooooooooo
       ooooWoooo""")
     val expectedView = 
      """ooWoooWoo
         ooWoWoWoo
         ooWoEoWoo
         ooWoooWoo
         WoWoWoWoW
         ooWoooWoo
         ooWoMoWoo
         ooWoWoWoo
         ooWoooWoo""".lines map (_.trim()) mkString ("\n") 
       
     arena.startingPositions.foreach{ case(direction, position) =>
       assert(arena.prepareVisibleArea(direction, position)==expectedView)
     } 
  }

  test("should prepare view for each bot facing top and bottom directions") {
    val arena = Arena(
    """oooWooooo
       ooooooooo
       WWWWWWWWW
       ooovooooo
       WWoWoWWoo
       ooo^ooooo
       WWWWWWWWW
       ooooooooo
       oooWooooo""")
     val expectedView = 
      """ooooWoooo
         ooooWoooo
         ooooooooo
         WWWWWWWWW
         ooooEoooo
         oWWoWoWWo
         ooooMoooo
         WWWWWWWWW
         ooooooooo""".lines map (_.trim()) mkString ("\n") 

     arena.startingPositions.foreach{ case(direction, position) =>
       assert(arena.prepareVisibleArea(direction, position)==expectedView)
     } 
  }

}