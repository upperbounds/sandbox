package cfm

import org.squeryl.customtypes.CustomTypesMode._
import org.squeryl.customtypes._
import org.squeryl.{Session, Schema}

trait Domain[A] {
  self: CustomType =>
  def label: String

  def validate(a: A): Unit

  def value: A
  validate(value)
}


/*
mysql> desc listing;
+------------------------------+----------------------+------+-----+---------------------+----------------+
| Field                        | Type                 | Null | Key | Default             | Extra          |
+------------------------------+----------------------+------+-----+---------------------+----------------+
| listing_id                   | int(10) unsigned     |      | PRI | NULL                | auto_increment |
| version                      | smallint(5) unsigned |      |     | 0                   |                |
| title                        | varchar(255)         |      | MUL |                     |                |
| slug                         | varchar(255)         |      |     |                     |                |
| cover_date                   | datetime             | YES  |     | NULL                |                |
| publish_date                 | datetime             | YES  |     | NULL                |                |
| published_version            | int(10) unsigned     | YES  | MUL | NULL                |                |
| preview_version              | int(10) unsigned     | YES  |     | NULL                |                |
| notes                        | text                 | YES  |     | NULL                |                |
| priority                     | tinyint(3) unsigned  |      |     | 2                   |                |
| element_id                   | int(10) unsigned     |      |     | 0                   |                |
| class                        | varchar(255)         |      | MUL |                     |                |
| hidden                       | tinyint(1)           |      |     | 0                   |                |
| checked_out                  | tinyint(1)           |      | MUL | 0                   |                |
| checked_out_by               | int(10) unsigned     |      | MUL | 0                   |                |
| desk_id                      | smallint(5) unsigned | YES  | MUL | NULL                |                |
| last_modified_date           | datetime             | YES  |     | NULL                |                |
| magazine_publish_date        | datetime             | YES  |     | NULL                |                |
| last_factcheck_date          | datetime             |      | MUL | 2005-01-01 00:00:00 |                |
| last_copyedit_date           | datetime             |      | MUL | 2006-01-01 00:00:00 |                |
| needs_data                   | tinyint(1)           | YES  |     | 0                   |                |
| needs_web_review             | tinyint(1)           | YES  |     | 0                   |                |
| needs_magazine_review        | tinyint(1)           | YES  |     | 0                   |                |
| needs_caption_or_credit      | tinyint(1)           | YES  |     | 0                   |                |
| needs_image                  | tinyint(1)           | YES  |     | 0                   |                |
| needs_image_review           | tinyint(1)           | YES  |     | 0                   |                |
| needs_photo_gallery          | tinyint(1)           | YES  |     | 0                   |                |
| needs_frequent_review        | tinyint(1)           | YES  |     | 0                   |                |
| internal_production_comments | text                 | YES  |     | NULL                |                |
| notes_user_id                | int(10) unsigned     | YES  |     | NULL                |                |
| notes_timestamp              | datetime             | YES  |     | NULL                |                |
+------------------------------+----------------------+------+-----+---------------------+----------------+
*/

class Title(s: String) extends StringField(s) with Domain[String] {
  def validate(s: String) = ()

  def label = "title"
}
class Slug(s: String) extends StringField(s) with Domain[String] {
  def validate(s: String) = ()

  def label = "slug"
}
class KrangClass(s: String) extends StringField(s) {
}

class ColId(id: Int) extends IntField(id) with Domain[Int] {
  def validate(s: Int) = ()

  def label = "ID"
}

object Listing extends Schema {
  val listings = table[Listing]
  val restaurants = from(listings)(p => where(p.clazz === "restaurants") select (p))

}

class Listing(val listingId: ColId, val slug: Slug, val title: Title, val elemId: ColId, val clazz: KrangClass) {
}

