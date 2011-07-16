package cfm.taxonomy

import java.lang.Long
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.annotations.Column
import org.squeryl.{KeyedEntity, Session, Schema}
import org.squeryl.PrimitiveTypeMode._


class Fashion {

  abstract class KrangStory(@Column("story_id") val id: Long) {

  }

  class FashionGallery(@Column("story_id") val id: Long) {

  }

  class Model(@Column("story_id") val id: Long) {


  }

}

class Element(@Column("story_id") val id: Long,
              @Column("parent_id") val parentId: Long,
              @Column("root_id") val rootId: Long,
              @Column("class") val `class` : String,
              @Column("ord") val ord: Int,
              @Column("data") val data: String) extends KeyedEntity[Long] {

}

object Krang extends Schema {
  val elements = table[Element]("elements")
}

object Conn {

  import org.squeryl.SessionFactory

  Class.forName("com.mysql.jdbc.Driver");

  SessionFactory.concreteFactory = Some(() =>
    Session.create(
      java.sql.DriverManager.getConnection("jdbc:mysql://mysql.dev.nymag.biz/nymetro", "krang", ""),
      new MySQLAdapter))



  def all() = transaction {
    from(Krang.elements)(s => select(s))
  }

  def thing() = {
    transaction {
      Krang.elements.schema.printDdl
    }

  }
}