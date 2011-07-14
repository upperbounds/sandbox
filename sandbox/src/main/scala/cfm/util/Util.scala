package cfm.util

import org.apache.http.{HttpEntity, HttpResponse}
import cfm.html.HTML5Parser

object Util {
  def ptime[A](f: () => A) = {
    val t0 = System.nanoTime
    val ans = f()
    val time = (System.nanoTime - t0) * 1e-9

    printf("Elapsed: %.3f sec\n", time)
    (time, ans)
  }

  var htmlHandler = (code: Int, response: HttpResponse, entity: Option[HttpEntity]) => {
    HTML5Parser.loadXML(entity.get.getContent)
  }
}
