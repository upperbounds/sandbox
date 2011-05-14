package cfm.io

import dispatch._
import cfm.html.TagSoupFactoryAdapter

object Http {


  def get(host: String, url: String) = {
    val http = new Http()
    val ts = new TagSoupFactoryAdapter()
    val u = :/(host) / url
    http(u >> {(stm, charset) => {println("parsing ", stm);ts.load(stm)}})
  }
}