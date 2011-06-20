package cfm.sitemap

import util.parsing.combinator._

class RobotsParser extends JavaTokenParsers{
//override protected val whiteSpace = """""".r

  def robots: Parser[Any] = (dis | rep(sitemap))
  def all : Parser[Any] =  agent ~ allow
  def dis : Parser[Any] =  agent ~ rep(entry)
  def entry:Parser[Any] =  (disallow | allow)
  def sitemap: Parser[Any] = "Sitemap"~":"~"[\\S]+".r
  def agent: Parser[Any] = "User-agent"~":"~"[\\S]+".r
  def disallow: Parser[Any] = {println("disallow called"); "Disallow"~":"~"[\\S]+".r}
  def allow: Parser[Any] = {println("allow called"); "Allow"~":"~"[\\S]+".r}

}

object ParseExpr extends RobotsParser {
  def main(args: Array[String]) {
    println("input : "+ args(0))
    println(parseAll(robots, args(0)))
  }
}