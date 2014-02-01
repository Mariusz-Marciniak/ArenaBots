package pl.mm.arena

object Bot {
  def apply(name: String) = new Bot(name)
}

class Bot(botName:String) {
  
  val name = this.botName
    
}

trait BotHandler {
  def energy: Int
  def look: String  
}