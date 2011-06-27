package cfm.assetmanager

import net.lag.configgy.Configgy
import scalax.file.Path
import scalax.file.Path._
import org.xml.sax.{Attributes, Locator, ContentHandler}
import org.slf4j.LoggerFactory
import org.apache.tika.parser.mp3.Mp3Parser
import scalax.io.InputStreamResource
import xml.MetaData
import org.apache.tika.parser.ParseContext
import org.apache.tika.metadata.Metadata
import java.io.{ByteArrayInputStream, InputStream, File}

object AssetManager {
  var homeDir = System.getProperty("user.home")
  var locs = List(".cfm")

  def getConfig() = {
    locs.map(l => homeDir + "/" + l + "/" + "cfm.conf").filter(f => new File(f).exists()).headOption
  }

  def main(args: Array[String]): Unit = {

    Configgy.configure(getConfig().get)
    val config = Configgy.config
    val roots = config.getList("media_roots").map(p => Path(p))

    roots.foreach(f => f.descendants().filter(_.isFile).map(t => mp3Handler(new ByteArrayInputStream(t.byteArray))))
  }

  def mp3Handler(i: InputStream) = {
    try {
      val parser = new Mp3Parser()
      parser.parse(i, new Handler(), new Metadata(), new ParseContext())
    }
    finally{
      i.close()
    }

  }

}

class Handler extends ContentHandler {
  val log = LoggerFactory.getLogger(this.getClass)

  def setDocumentLocator(locator: Locator) {
    log.debug("setDocumentLocator. locator: {}", locator)
  }

  def skippedEntity(name: String){
    log.debug("skippedEntity. name: {}", name)
  }

  def processingInstruction(target: String, data: String){
    log.debug("processingInstruction. target: {}, data {}", target, data)
  }

  def ignorableWhitespace(ch: Array[Char], start: Int, length: Int) {
  }

  def characters(ch: Array[Char], start: Int, length: Int){
    log.debug("characters. ch: {}, start: {}, length: " + length, new String(ch), start)
  }

  def endElement(uri: String, localName: String, qName: String){
    log.debug("endElement. uri: {}, localName: {}, qName: {} " + qName, uri, localName)
  }

  def startElement(uri: String, localName: String, qName: String, atts: Attributes){
    log.debug("startElement. uri: {}, localName: {}, qName: {} " + qName, uri, localName)
  }

  def endPrefixMapping(prefix: String){
    log.debug("endPrefixMapping. prefix: {}", prefix)
  }

  def startPrefixMapping(prefix: String, uri: String){
    log.debug("startPrefixMapping. prefix: {}", prefix)
  }

  def endDocument(){
    log.debug("ending document")
  }

  def startDocument(){
    log.debug("starting document")
  }
}