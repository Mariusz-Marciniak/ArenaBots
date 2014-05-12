package pl.mm.collision

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import pl.mm.collision.CollisionUtil._;
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CollisionSuite extends FunSuite {

  val arena =
    """ooooWoooo
       ooooooooo
       WWWWWWWWW
       ooooooooo
       oW>oWo<Wo
       ooooooooo
       WWWWWWWWW
       ooooooooo
       ooooWoooo"""
    
  test("no position should not return collision") {
    assert(findCollision(List[List[Boolean]](), 1, 1, 1, 1) === NoCollision)
  }

}