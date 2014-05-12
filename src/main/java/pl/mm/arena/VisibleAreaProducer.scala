package pl.mm.arena

object VisibleAreaPreparer {
  lazy val left = FacingLeftVisibleAreaPreparer
  lazy val right = FacingRightVisibleAreaPreparer
  lazy val top = FacingTopVisibleAreaPreparer
  lazy val bottom = FacingBottomVisibleAreaPreparer

  def apply(direction: Direction): VisibleAreaPreparer = direction match {
    case Arena.LEFT => left
    case Arena.RIGHT => right
    case Arena.TOP => top
    case Arena.BOTTOM => bottom
  }
}

abstract class VisibleAreaPreparer(rowOffset: Int, colOffset: Int) {
  val MAX_VISIBILITY_RANGE = 9

  protected def rotate(croppedBoard: IndexedSeq[String]): IndexedSeq[String]

  def prepareVisibleArea(entireBoard: List[String], position: Position): String = {

    def cropToMaxVisibleArea(fromRow: Int, fromCol: Int): IndexedSeq[String] = {
      val boardWidth = entireBoard(0).size
      val boardHeight = entireBoard.size
      val indexedBoard = 0 to boardHeight zip entireBoard

      val warpOnBottom = fromRow + MAX_VISIBILITY_RANGE >= boardHeight
      val warpOnTop = fromRow < 0

      val rowsInVisibilityRange = for {
        (index, line) <- indexedBoard
        if (
          (warpOnBottom && index < (fromRow + MAX_VISIBILITY_RANGE) % boardHeight)
          || (warpOnTop && index >= boardHeight + fromRow)
          || (index >= fromRow && index < fromRow + MAX_VISIBILITY_RANGE))
      } yield (index, line)

      rowsInVisibilityRange sortBy { // sort rows 
        case (index, line) => {
          if (warpOnBottom) {
            if (index < fromRow) index + boardHeight else index
          } else if (warpOnTop) {
            if (fromRow + MAX_VISIBILITY_RANGE <= index) index - boardHeight else index
          } else index
        }
      } map { // generate lines
        case (index, line) => {
          if (fromCol + MAX_VISIBILITY_RANGE >= boardWidth)
            line.substring(fromCol) + line.substring(0, MAX_VISIBILITY_RANGE - boardWidth + fromCol)
          else if (fromCol < 0)
            line.substring(boardWidth + fromCol) + line.substring(0, fromCol + MAX_VISIBILITY_RANGE)
          else
            line.substring(fromCol, fromCol + MAX_VISIBILITY_RANGE)
        }
      }
    }

    def replacePlayersMarkers(croppedBoard: IndexedSeq[String]) = {

      def indicateEnemyInLine(line: String): String = line.replaceAll("[" + Arena.STARTING_POSITION + "]", Arena.ENEMY)

      def indicateMeInLine(line: String): String = line.substring(0, 4) + Arena.ME + line.substring(5)

      croppedBoard.indices map {
        case index =>
          if (index == 6)
            indicateEnemyInLine(indicateMeInLine(croppedBoard(index)))
          else
            indicateEnemyInLine(croppedBoard(index))
      }
    }

    replacePlayersMarkers(rotate(cropToMaxVisibleArea(position._1 + rowOffset, position._2 + colOffset))) mkString ("\n")
  }

  def validate(entireMap: String): Boolean = {
    if (entireMap.lines.size >= MAX_VISIBILITY_RANGE) {
      val lineWidth = entireMap.lines.next.size
      if (lineWidth >= MAX_VISIBILITY_RANGE)
        entireMap.lines.forall(_.size == lineWidth)
      else
        false
    } else
      false
  }

}

case object FacingTopVisibleAreaPreparer extends VisibleAreaPreparer(-6, -4) {
  def rotate(croppedBoard: IndexedSeq[String]): IndexedSeq[String] = {
    croppedBoard
  }
}

case object FacingBottomVisibleAreaPreparer extends VisibleAreaPreparer(-2, -4) {
  def rotate(croppedBoard: IndexedSeq[String]): IndexedSeq[String] = {
    croppedBoard.reverse map (_.reverse)
  }
}

case object FacingLeftVisibleAreaPreparer extends VisibleAreaPreparer(-4, -6) {
  def rotate(croppedBoard: IndexedSeq[String]): IndexedSeq[String] = {
    croppedBoard.indices map { case index => croppedBoard.foldLeft("") { case (charInLine, result) => result(index) +: charInLine } }
  }
}

case object FacingRightVisibleAreaPreparer extends VisibleAreaPreparer(-4, -2) {
  def rotate(croppedBoard: IndexedSeq[String]): IndexedSeq[String] = {
    (croppedBoard.size - 1 to 0 by -1) map { case index => croppedBoard.foldLeft("")(_ + _(index)) }
  }
}

class VisibleArea {
  
}