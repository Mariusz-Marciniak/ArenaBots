package pl.mm.collision

object CollisionUtil {
  type AreaWithBlocks = List[List[Boolean]]
  
  trait CollisionPossibilty
  case object NoCollision extends CollisionPossibilty
  case class Collision(onX: Int, onY: Int) extends CollisionPossibilty
  
  def findCollision(area: AreaWithBlocks, fromX: Int, fromY: Int, toX: Int, toY: Int): CollisionPossibilty = {
      NoCollision
  }

}