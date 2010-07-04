package cfm

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import Repo._

class StackSpec extends FlatSpec with ShouldMatchers {

  "A Repo" should "iterate over nodes" in {
    val r = new TransientRepoAdaptor()
    val s = r.repo.login()
    for(n <- s.getRootNode.getNodes){
        println(n)
        for(p <- n.getProperties) println(p, p.getType, p.getValue.getString)
    }
    s.logout
  }
}


