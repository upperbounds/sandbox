package cfm.io

import dispatch._
import cfm.html.TagSoupFactoryAdapter
import scala.xml.Node
import java.io.InputStream

object Http {


  def get(host: String, url: String) = {
    val http = new Http()
    val ts = new TagSoupFactoryAdapter()
    val u = :/(host) / url
    http(u >> {
      (stm, charset) => ts.load(stm)
    })
  }
//
//
//  def g(host: String, url: String): Either[Exception, dispatch.http.HttpPackage[Node]] = {
//    try {
//      val http = new Http()
//      val ts = new TagSoupFactoryAdapter()
//      val u = :/(host) / url
//      Right(http(u >> {(stm: InputStream, charset:String) => ts.load(stm)}))
//
//    }
//    catch {
//      case e: Exception => Left(e)
//    }
//  }

}