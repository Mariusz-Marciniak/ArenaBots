package pl.mm.arena

object Arena {

  val LEFT: Direction = "<"
  val RIGHT: Direction = ">"
  val TOP: Direction = "^"
  val BOTTOM: Direction = "v"
  val STARTING_POSITION = LEFT + TOP + RIGHT + BOTTOM

  val ME = "M"
  val ENEMY = "E"
  val WALL = "W"
  val FREE_SPACE = "o"
  val TRANSPARENT_OBJECTS = FREE_SPACE  

  val MIN_MAP_WIDTH = 5
  val MAX_MAP_WIDTH = 50
  val MIN_MAP_HEIGHT = 5
  val MAX_MAP_HEIGHT = 50

  def apply(map: String) = new Arena(map)
  def generateArena(): Arena = Arena(
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
}

class Arena(aBoard: String) {
  import Arena._
  val board = (aBoard.lines map (_.trim())).toList

  val maxPlayers = board.mkString("\n") count (c => STARTING_POSITION.contains(c))

  val startingPositions: List[Tuple2[Direction, Position]] = {
    val result = for {
      row <- (0 until board.size)
      colWithDict <- {
        val line = board(row)
        val indexedLine = line zip (0 until line.size)
        indexedLine filter {
          case (c, index) => STARTING_POSITION.contains(c)
        }
      }
    } yield (colWithDict._1.toString, (row, colWithDict._2))

    result.toList
  }

  def prepareVisibleArea(direction: Direction, pos: Position): String = VisibleAreaPreparer(direction).prepareVisibleArea(board, pos)
}
