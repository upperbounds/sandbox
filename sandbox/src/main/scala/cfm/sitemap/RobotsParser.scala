package cfm.sitemap

import util.parsing.combinator._

class RobotsParser extends JavaTokenParsers{
//override protected val whiteSpace = """""".r

  def robots: Parser[Any] = sitemap | rule
  def rule: Parser[Any] =   (all | dis)
  def all : Parser[Any] =  agent ~    allow
  def dis : Parser[Any] =  agent ~    disallow
  def sitemap: Parser[Any] = "Sitemap"~":"~"[\\S]+".r
  def agent: Parser[Any] = "User-agent"~":"~"[\\S]+".r
  def disallow: Parser[Any] = rep("Disallow"~":"~"[\\S]+".r)
  def allow: Parser[Any] = rep("Allow"~":"~"[\\S]+".r)

}

object ParseExpr extends RobotsParser {
  def main(args: Array[String]) {
    println("input : "+ args(0))
    println(parseAll(rule, args(0)))
  }
}