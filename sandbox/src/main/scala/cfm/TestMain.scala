package cfm

import org.apache.jackrabbit.jcr2dav._
import java.util.HashMap
import javax.jcr.{Node, SimpleCredentials}
import cfm.util.Util._
import cfm.Repo._
import org.apache.jackrabbit.spi2davex.BatchReadConfig
import org.apache.jackrabbit.spi.commons.conversion.PathResolver

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/7/11
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.jcr.NamespaceException
import org.apache.jackrabbit.spi.Path
import org.apache.jackrabbit.spi.commons.conversion.PathResolver

class CFMBatchReadConfig extends BatchReadConfig{
  def getDepth(p1: Path, p2: PathResolver) = {
    println("getting depth")
    2
  }
}

object TestMain {

  def build() = {
    var props = new HashMap[String, Object]()
    props.put("org.apache.jackrabbit.repository.uri", "http://author01.dev.nymag.biz:4502/crx/server")
    props.put("org.apache.jackrabbit.spi2dav.BatchReadConfig", new CFMBatchReadConfig())

    var creds = new SimpleCredentials("admin", "admin".toCharArray)
    var s1 = new Jcr2davRepositoryFactory().getRepository(props).login(creds)

    () => collect(s1.root.getNode("content/nymag/daily"), (n: Node) => false)
  }
  def build2() = {
    var props = new HashMap[String, Object]()
    props.put("org.apache.jackrabbit.spi2dav.uri", "http://author01.dev.nymag.biz:4502/crx/server")
    props.put("org.apache.jackrabbit.spi2dav.BatchReadConfig", new CFMBatchReadConfig())
    var creds = new SimpleCredentials("admin", "admin".toCharArray)
    var s1 = new Jcr2davRepositoryFactory().getRepository(props).login(creds)

    () => collect(s1.root.getNode("content/nymag/daily"), (n: Node) => false)
  }

  def build3() = {
    var props = new HashMap[String, Object]()
    props.put("org.apache.jackrabbit.spi2davex.uri", "http://author01.dev.nymag.biz:4502/crx/server")
    props.put("org.apache.jackrabbit.spi2dav.BatchReadConfig", new CFMBatchReadConfig())


    var creds = new SimpleCredentials("admin", "admin".toCharArray)
    var s1 = new Jcr2davRepositoryFactory().getRepository(props).login(creds)

    () => collect(s1.root.getNode("content/nymag/daily"), (n: Node) => false)
  }




  def buildOld() = {
    var s1 = new RMIRepoAdaptor("author01.dev.nymag.biz", 1234, "crx", "crx.default", "admin", "admin").s

    () => collect(s1.root.getNode("content/nymag/daily"), (n: Node) => false)
  }



  def main(args: Array[String]): Unit = {
    import cfm._
    import cfm.Repo._


    var res = (1 to 5).map(_ => ptime(build()))
    res.foreach(f => println(f._1))


  }

}