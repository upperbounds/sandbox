package cfm

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import cfm.Repo._
import org.apache.jackrabbit.commons.cnd.CndImporter
import java.io.{FileInputStream, FileReader}
import javax.jcr.{ValueFactory, Session, SimpleCredentials}

class StackSpec extends FlatSpec with ShouldMatchers {
  setProps
  val creds = new SimpleCredentials("username", "password".toCharArray())
  

  "A Repo" should "iterate over nodes" in {
    setUpRepo

    val r = new TransientRepoAdaptor()
    val s = r.repo.login(creds)

    for (n <- s.getRootNode.getNodes) {
      println(n)
      for (p <- n.getProperties) println(p, p.getType, p.getValue.getString)
    }
    s.logout

    tearDownRepo
  }
  def setUpRepo: Unit = {

    val r = new TransientRepoAdaptor()
    val s = r.repo.login(creds)
    try {
      val testFile = "/test.jpg"
      val vf = s.getValueFactory

      for (cnd <- List("/sling.cnd", "/tagging.cnd","/replication.cnd","/dam.cnd", "/nym.cnd")) {
        CndImporter.registerNodeTypes(new FileReader(this.getClass.getResource(cnd).getFile), s)
      }
      val assetContent = s.getRootNode.addNode("content", "nt:folder").addNode("dam", "nt:folder").addNode("test", "dam:Asset").addNode("jcr:content","dam:AssetContent")

      assetContent.addNode("metadata")
      val renditions = assetContent.addNode("renditions", "nt:folder")
      val file = renditions.addNode("original", "nt:file")
      file.addNode("jcr:content", "nt:resource").setProperty("jcr:data", vf.createBinary(new FileInputStream(this.getClass.getResource(testFile).getFile)))
      file.addMixin("nym:ImageInfo")
      file.setProperty("nym:cropInfo", List(vf.createValue(1),vf.createValue(2),vf.createValue(3),vf.createValue(4)).toArray)
      //      file.setProperty("nym:cropInfo2", "blah")

      s.save
    }
    finally {
      s.logout
    }

  }

  def tearDownRepo: Unit = {

    val r = new TransientRepoAdaptor()
    val s = r.repo.login(creds)
    for{node <- s.getRootNode.getNodes if (!node.getName.equals("jcr:system"))} node.remove

    s.save
    s.logout

  }

  def setProps(): Unit = {
    val repoHome = "src/test/repository"
    System.setProperty("org.apache.jackrabbit.repository.home", repoHome)
    System.setProperty("org.apache.jackrabbit.repository.conf", repoHome + "/repository.xml")
  }

}


