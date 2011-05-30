package cfm.search

import org.apache.lucene.store.FSDirectory
import org.apache.lucene.search.IndexSearcher
import java.io.{FileFilter, File}
import org.apache.lucene.index.IndexReader
import scala.collection.JavaConversions._
import java.util.{Set => JSet}

class S(indexDirs: List[String]) {
  val idxs = for {i <- indexDirs;
                  //    if(new File(i).isDirectory)
                  //dir <- new File(i);
                  segs <- new File(i).listFiles(new SegFilter())
  } yield segs

  var is = idxs.map(f => new IndexSearcher(FSDirectory.getDirectory(f)))

  def allFields() = is.foldLeft(Set[String]())((f: Set[String], v: IndexSearcher) => f ++ fields(v))

  private def fields(is: IndexSearcher): Set[String] = {
    val fs = is.getIndexReader.getFieldNames(IndexReader.FieldOption.ALL).asInstanceOf[JSet[String]]
    fs.foldLeft(Set[String]())((f: Set[String], v: String) => f + v)
  }
}

class SegFilter extends FileFilter {
  def accept(f: File) = f.isDirectory && (f.listFiles().filter(t => t.getName.startsWith("segments")).size > 0)
}