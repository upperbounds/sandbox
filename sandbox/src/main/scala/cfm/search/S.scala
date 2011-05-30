package cfm.search

import org.apache.lucene.store.FSDirectory
import org.apache.lucene.search.IndexSearcher
import java.io.{FileFilter, File}
import org.apache.lucene.index.IndexReader
import scala.collection.JavaConversions._
import java.util.{Set => JSet}
import org.apache.lucene.queryParser.QueryParser
import org.apache.lucene.analysis.standard.StandardAnalyzer


class S(indexDirs: List[String]) {
  val idxs = for {i <- indexDirs;
                  segs <- new File(i).listFiles(new SegFilter())
  } yield segs

  var is = idxs.map(f => new IndexSearcher(FSDirectory.getDirectory(f)))

  def allFields() = is.foldLeft(Set[String]())((f: Set[String], v: IndexSearcher) => f ++ fields(v))

  def parser(field: String) = new QueryParser(field, new StandardAnalyzer())
  private def fields(is: IndexSearcher): Set[String] = {
    val fs = is.getIndexReader.getFieldNames(IndexReader.FieldOption.ALL).asInstanceOf[JSet[String]]
    fs.foldLeft(Set[String]())((f: Set[String], v: String) => f + v)
  }

  def search(field: String, query: String) = is.map(f => {f.search(parser(field).parse(query),10)})
}

class SegFilter extends FileFilter {
  def accept(f: File) = f.isDirectory && (f.listFiles().filter(t => t.getName.startsWith("segments")).size > 0)
}