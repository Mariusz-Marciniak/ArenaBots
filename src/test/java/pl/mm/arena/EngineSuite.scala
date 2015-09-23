package pl.mm
package arena

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EngineSuite extends FunSuite {
    val arena = Arena(
    """ooooooooooooooooooooo
       oooWWoooooooooooWWooo
       oooWWoooooooooooWWooo
       oooWWoooooooooooWWooo
       oooooooooWWWooooooooo
       oooooooooWWWooooooooo
       oooWWoooooooooooWWooo
       oooWWoo>ooooo<ooWWooo
       oooWWoooooooooooWWooo
       ooooooooooooooooooooo""")

    val engine = Engine(arena)

    test("should allow up to maximum number of players") {
      val expectedPositions = List((7,7),(13,7))

      assert(engine.login(List(Bot("Tom"))) == Left(new Engine.NotEnoughPlayersException))
      assert(engine.login(List(Bot("Tom"), Bot("Jack"), Bot("Ana"))) == Left(new Engine.TooManyPlayersException(2)))
      assert(engine.login(List(Bot("Tom"), Bot("Jack"))) match { 
          case Right(list) => list forall {
            handler => handler.energy == Engine.InitialEnergy
          }
          case Left(_) => false
        }
      )
    }
    
    test("list should be able to shuffle elements") {
      val l : List[Int] = (0 to 100) toList
      val shuffledOne = l.shuffle
      val shuffledTwo = l.shuffle
      assert(l.size == shuffledOne.size)
      assert(l.size == shuffledTwo.size)
      assert(l.size == shuffledOne.intersect(shuffledTwo).size)
      assert(l.indices forall { case i => l(i) == i } )
      assert(shuffledOne.indices exists { case i => shuffledOne(i) != i } )
      assert(shuffledTwo.indices exists { case i => shuffledTwo(i) != i } )
    }    

    test("should be able to store execute actions") {
      //engine.make(bot)
    }
}