package cfm.images

import com.day.cq.dam.core.{ProcessorException, Context}
import java.awt.image.BufferedImage
import com.day.cq.dam.core.impl.handler.XMPProcessor
import scala.collection.JavaConversions._
import java.io.{FileInputStream, File, InputStream}
import com.adobe.xmp.{XMPException, XMPMetaFactory}
import scala.xml._

class MDExtractor(val file: File) {
  def extract = XMPProcessor.process(new FileInputStream(file))
}
object MetadataExtractor {
  def main(args: Array[String]) = {
    var imgs = Thread.currentThread.getContextClassLoader.getResources("images")
    for (img <- imgs) {
      var f = new File(img.toURI)
      f.listFiles.foreach(file => {
        try {
          val md = new MDExtractor(file).extract
          val doc = XML.load(md)
          println(doc.toString)          
        }
        catch {
          case e: XMPException => println("couldn't do anything with this " + file.getName)
          case e: ProcessorException => println("couldn't do anything with this " + file.getName)
          case _ => println("some uncaught exception")
        }
      }
        )
      //      println("File:" + f.getName + " " + f.isDirectory)
    }
  }
}