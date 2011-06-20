package cfm.test

import org.specs2.mutable._
import cfm.sitemap.RobotsParser
import java.io.{InputStreamReader}

class RobotsSpec extends Specification {
  //  override def failure(msg: String):Parser[Nothing] = {}

  def getFile(f: String) = this.getClass.getClassLoader.getResource(f)

  val files = List("robots-google.txt") map (f => getFile("robots/" + f))

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      println(files)
      val stream = new InputStreamReader(files.head.openStream())
      println(RobotsSpec.parseAll(RobotsSpec.robots, stream))
      stream.close()
      "Hello world" must have size (11)
    }
  }
  "An allow string" should {
    "contain 11 characters" in {
      println(RobotsSpec.parseAll(RobotsSpec.allow, "Allow: /news/directory"))
      "Hello world" must have size (11)
    }
  }

  object RobotsSpec extends RobotsParser {

  }

}
