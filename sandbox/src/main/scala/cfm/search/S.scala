package cfm.search

import org.apache.lucene.store.FSDirectory
import org.apache.lucene.search.IndexSearcher
import java.io.File


class S(indexDir: String) {
  val file: File = new File("/Users/quadvillian/cq-54/crx-quickstart/repository/workspaces/crx.default/index")
  var is  = new IndexSearcher(FSDirectory.getDirectory(indexDir))

}