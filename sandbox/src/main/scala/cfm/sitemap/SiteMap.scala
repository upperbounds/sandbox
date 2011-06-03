package cfm.sitemap

import cfm.io.Http
import java.io.InputStream
import scala.xml._
import java.util.zip.GZIPInputStream

import dispatch._
import java.net.URI


class SiteMap(host: String) {

  val maps: Seq[String] = Http.get(host, "sitemap.xml") \ "sitemap" map(f => (f \"loc").text)
  //maps.map(f => Http.get(host, f))


  def getFromZip(file: String): Node ={
    val http = new Http()
    val u = :/ (host) / file
    http(u >> {(stm: InputStream, charset: String) => {XML.load(new GZIPInputStream(stm))} })
  }

  def getUrls(n: Node) = (n \\ "url" \ "loc") map (_.text)

  def all() =  maps.foldLeft(List[String]())( (f, v) => if(v.endsWith(".gz")) getUrls(getFromZip(new URI(v).getPath)).toList ++ f else f )
}