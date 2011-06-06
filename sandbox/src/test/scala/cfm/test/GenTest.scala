package cfm.test

import dispatch._
import org.apache.http.{HttpEntity, HttpResponse}

object GenTest {
  val u = "http://nymag.com/fashion"
  lazy val resp = new Http() x(Handler(url(u), (i:Int, resp: HttpResponse, entity:Option[HttpEntity]) => {(resp, entity)} ))
}