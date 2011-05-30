package cfm

import org.apache.jackrabbit.core.{TransientRepository}
import org.apache.jackrabbit.rmi.client.ClientRepositoryFactory
import javax.jcr.{SimpleCredentials, Credentials, Repository => JCRRepo}

trait JCRAdaptor {
  // type Repository <: JCRRepo
  val repo: JCRRepo
}

class TransientRepoAdaptor(creds: Credentials) extends JCRAdaptor {
  val repo = new TransientRepository()
  implicit lazy val s = repo.login(creds)
}
object TransientRepoAdaptor{
  def apply() = new TransientRepoAdaptor(new SimpleCredentials("admin", "admin".toCharArray))
}

class RMIRepoAdaptor(val host: String,
                     val port: Int,
                     val appPath: String,
                     val workSpace: String,
                     val userName: String,
                     val password: String) extends JCRAdaptor {
  def this() = {
    this ("localhost", 1234, "crx", "crx.default", "admin", "admin")
  }
  private[this] def getRepo = {repo}

  private def buildUrl(host: String, port: Int, workspace: String) = "//" + host + ":" + port + "/" + workspace

  lazy val repo = new ClientRepositoryFactory().getRepository(buildUrl(host, port, appPath))
  val s = repo.login(new SimpleCredentials(userName, password.toCharArray), workSpace)
}

object RMIRepoAdaptor{
    def apply() = new RMIRepoAdaptor()
  }