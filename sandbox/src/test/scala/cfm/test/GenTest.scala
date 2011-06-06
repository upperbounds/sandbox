package cfm.test

import dispatch._
import org.apache.http.{HttpEntity, HttpResponse}
import scala.xml._
import java.util.zip.GZIPInputStream
import cfm.sitemap.{SiteMap, SiteMapIndex}

object GenTest {
  val u = "http://nymag.com/fashion"
  lazy val resp = new Http() x(Handler(url(u), (i:Int, resp: HttpResponse, entity:Option[HttpEntity]) => {(resp, entity)} ))
  lazy val sm = new Http() x (Handler(url("http://nymag.com/sitemap.xml"), (i:Int, resp: HttpResponse, entity:Option[HttpEntity]) => {SiteMapIndex.fromXml(XML.load(entity.get.getContent))}))

  var http = new Http()
   var sms = sm.sitemaps.map(f => http x (Handler(url(f.url.toString),(i:Int, resp:HttpResponse, entity:Option[HttpEntity]) =>{SiteMap.fromXml(XML.load(new GZIPInputStream(entity.get.getContent)))}  ) ))
}