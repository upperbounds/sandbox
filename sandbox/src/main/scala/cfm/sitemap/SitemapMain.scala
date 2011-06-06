package cfm.sitemap


import dispatch._
import org.apache.http._
import java.util.zip.{GZIPInputStream}
import scala.xml._

object SitemapMain {


  def main(args: Array[String]): Unit = {
    val http = new Http()

    val locHandler = (m: Location) => {
        try {
          http x (Handler(url(m.location.toString), (i: Int,
                                                     resp: HttpResponse,
                                                     entity: Option[HttpEntity]) => {
            if (i > 210) println("got error for " + m.location)
          }
          ))
        }
        catch {
          case e: Exception => println("got exception for " + m.location + " : " + e.getMessage)
        }
      }


    val sm = http x (Handler(url(args(0)), (i: Int,
                                            resp: HttpResponse,
                                            entity: Option[HttpEntity]) => {
      SiteMapIndex.fromXml(XML.load(entity.get.getContent))
    }))
    val sms = sm.sitemaps.map(f => http x (Handler(url(f.url.toString), (i: Int,
                                                                         resp: HttpResponse,
                                                                         entity: Option[HttpEntity]) => {
      SiteMap.fromXml(XML.load(new GZIPInputStream(entity.get.getContent)))
    })))

    sms.foreach(f => f.loc.foreach(locHandler))
  }
}