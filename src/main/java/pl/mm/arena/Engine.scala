package pl.mm.arena

import scala.util.Either

object Engine {
  val INITIAL_ENERGY = 100

  def apply(arena: Arena) = new Engine(arena)
  def apply(map: String) = new Engine(Arena(map))

  class EngineException extends RuntimeException
  case class InvalidOperationException extends EngineException
  case class NotEnoughPlayersException extends EngineException
  case class TooManyPlayersException(playersAllowed: Int) extends EngineException

    class EngineBot private[Engine] (engine: Engine, state: BotState) extends BotHandler {

    override def energy: Int = state.energy
    override def look: String = engine.look(state)
    override def toString: String = "BotHandler:[state = " + state.toString() + "]"
  }
  
  class BotState private [Engine] (name: String, positionWithDirection: Tuple2[Direction,Position]) {
    val energy = INITIAL_ENERGY
    
    val sightUpgrade = SightUpgrades.default
    
    val position = positionWithDirection._2
    
    val direction = positionWithDirection._1

    override def toString: String = "BotState:[name = " + name + ", position = " + position + ", energy = " + energy + "]"

  }

}

class Engine(arena: Arena) {
  import Engine._

  private[this] var eBots: List[EngineBot] = List()

  def login(bots: List[Bot]): Either[EngineException, List[BotHandler]] =
    if (bots.size < 2)
      Left(new NotEnoughPlayersException)
    else if (bots.size > arena.maxPlayers)
      Left(new TooManyPlayersException(arena.maxPlayers))
    else {
      eBots = init(bots)
      Right(eBots)
    }

  private[this] def init(bots: List[Bot]): List[EngineBot] = {
    val possibleStartingPositions = arena.startingPositions.shuffle
    (bots zip possibleStartingPositions) map {
      case (bot, pos) => new EngineBot(this, new BotState(bot.name, pos))
    }
  }
  
  private def look(state: BotState): String = {
    SightUpgrades.look(arena.maxVisibleAreaFrom(state.direction,state.position), state.sightUpgrade)
    ""
  }

}


