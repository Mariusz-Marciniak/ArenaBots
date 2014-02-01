package pl.mm.arena

abstract class Upgrade(upgradeName: String, upgradeAcquireTime: Int) {
  def name: String = upgradeName
  def acquireTime: Int = upgradeAcquireTime
}

case class ArmorUpgrade(upgradeName: String, upgradeAcquireTime: Int, armor: Int) extends Upgrade(upgradeName, upgradeAcquireTime)
case class GunUpgrade(upgradeName: String, upgradeAcquireTime: Int, range: Int, power: Int, reloadTime: Int) extends Upgrade(upgradeName, upgradeAcquireTime)
case class MobilityUpgrade(upgradeName: String, upgradeAcquireTime: Int, speed: Int) extends Upgrade(upgradeName, upgradeAcquireTime)
case class SightUpgrade(upgradeName: String, upgradeAcquireTime: Int, visibility: String) extends Upgrade(upgradeName, upgradeAcquireTime)

object SightUpgrades {
  val mistyLensVisibility =
    """xxxxxxxxx
       xxxxxxxxx
       xxxxxxxxx
       xooooooox
       xxoooooxx
       xxxoooxxx
       xxxxMxxxx
       xxxxxxxxx
       xxxxxxxxx"""

  val cleanLensVisibility =
    """xxxxxxxxx
       xxxxxxxxx
       xxxxxxxxx
       ooooooooo
       xooooooox
       xxoooooxx
       xxxoMoxxx
       xxxxxxxxx
       xxxxxxxxx"""

  val shortRadarVisibility =
    """xxxxxxxxx
       xxxxxxxxx
       xxxxxxxxx
       xxxxxxxxx
       xxoooooxx
       xxoooooxx
       xxooMooxx
       xxoooooxx
       xxxxxxxxx"""
    
  val default = SightUpgrade("Misty lens", 0, mistyLensVisibility)

  def look(mapSliceForBot: String, upgrade: SightUpgrade): String = {
    ((mapSliceForBot.lines zip upgrade.visibility.lines) map { 
      case (mL, vL) => (mL.trim zip vL.trim map {case (mC, vC) => if(vC =='x') 'x' else mC}).mkString("")
      
    }).mkString(String.format("%n").intern())
  }
  
  val level = Map(
    1 -> List(
      SightUpgrade("Clean lens", 2, cleanLensVisibility),
      SightUpgrade("Short radar", 2, shortRadarVisibility)))
}
