package cfm.sitemap

import cfm.io.Http
import java.io.InputStream
import scala.xml._
import java.util.zip.GZIPInputStream

import dispatch._
import java.net.{URL, URI}
import javax.xml.datatype.{DatatypeFactory, XMLGregorianCalendar}

case class SiteMapEntry(url: URL, lastModified: Option[XMLGregorianCalendar])
case class SiteMapIndex(sitemaps: Set[SiteMapEntry])
case class SiteMap(loc: Set[Location])

case class Location(location: URL, lastModified: Option[XMLGregorianCalendar], changeFrequency: Option[Frequency#Value], priority: Option[Double] ){

  override def toString = "Location {url=" + location + ", lastModified=" +
    lastModified + ", frequency=" + changeFrequency + ", priority=" + priority + "}"
}

object Frequency extends Frequency

class Frequency extends Enumeration("always", "hourly", "daily", "weekly", "monthly", "yearly", "never") {
  val Always, Hourly, Daily, Weekly, Monthly, Yearly, Never = Value
}

object SiteMap{
  def fromXml(xml: Node) ={
    new SiteMap((xml \\ "url").map(f => new Location(new URL((f \ "loc").text), None, None, None)).toSet)

  }
}

object SiteMapIndex {
  def fromXml(xml: Node) = new SiteMapIndex((xml \\ "sitemap").map(f => {
      val lastModified = (f \ "lastmod").text
      val lm = {
      try {
        Some(DatatypeFactory.newInstance.newXMLGregorianCalendar(lastModified))
      }
      catch {
        case e: Exception => None
      }
      }
    new SiteMapEntry(new URL((f \ "loc").text), lm)
  }
  ).toSet)

}


