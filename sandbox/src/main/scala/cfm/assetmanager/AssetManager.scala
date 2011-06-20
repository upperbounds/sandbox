package cfm.assetmanager

import net.lag.configgy.Configgy
import java.io.File
import scalax.file.Path
import scalax.file.Path._

object AssetManager {
  var homeDir = System.getProperty("user.home")
  var locs = List(".cfm")

  def getConfig() = {
    locs.map(l => homeDir + "/" + l + "/" + "cfm.conf").filter(f => new File(f).exists()).headOption
  }
  def main(args: Array[String]): Unit ={

    Configgy.configure(getConfig().get)
    val config = Configgy.config
    val roots = config.getList("media_roots").map(p => Path(p))

    roots.foreach(f => f.descendants().filter(_.isFile).foreach(println))
  }

}