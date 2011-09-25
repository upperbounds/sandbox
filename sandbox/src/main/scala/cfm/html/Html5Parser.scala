package cfm.html

import nu.validator.htmlparser.common.XmlViolationPolicy;
import nu.validator.htmlparser.sax.HtmlParser;

import scala.xml.parsing.NoBindingFactoryAdapter;
import javax.xml.parsers.SAXParser
import org.xml.sax.InputSource
import java.io.{InputStreamReader, InputStream}
import scala.xml.Node
;

object HTML5Parser extends NoBindingFactoryAdapter {

  override def loadXML(source :InputSource, p:SAXParser) = loadXML(source)


  def loadXML(source: InputSource) = {

    val reader = new HtmlParser
    reader.setXmlPolicy(XmlViolationPolicy.ALLOW)
    reader.setCommentPolicy(XmlViolationPolicy.ALLOW)
      reader.setContentNonXmlCharPolicy(XmlViolationPolicy.ALLOW)
      reader.setContentSpacePolicy(XmlViolationPolicy.FATAL)
      reader.setNamePolicy(XmlViolationPolicy.ALLOW)
    reader.setContentHandler(this)
    reader.parse(source)
    rootElem
  }
  def loadXML(source: InputStream): Node = {
    loadXML(new InputSource(new InputStreamReader(source)))
    //todo: close that stream
  }
}