import sbt._

class SandboxProject(info: ProjectInfo) extends DefaultProject(info) with IdeaProject
{ 
    // dependencies 
  val redis = "com.redis" % "redisclient" % "2.8.0.RC7-1.4" % "compile"       
  val akkaRepo = "Akka Repo" at "http://scalablesolutions.se/akka/repository"
  
  val jcr = "javax.jcr" % "jcr" % "2.0" % "compile"  
  val squerly = "org.squeryl" % "squeryl_2.8.0" % "0.9.4beta8" % "compile" 
  val jackrabbit = "org.apache.jackrabbit" %  "jackrabbit-core" % "2.1.0" % "compile"
  
  val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.0" % "compile"
  
  val jcrRmi = "org.apache.jackrabbit" % "jackrabbit-jcr-rmi" % "2.0.0" % "compile"  
  val slingJson ="org.apache.sling" % "org.apache.sling.commons.json" % "2.0.4-incubator" % "compile"
//  val dayGFX = "com.day.commons" % "day-commons-gfx" % "2.0.13" % "compile"

  val sanselan = "org.apache.sanselan" % "sanselan" % "0.97-incubator" % "compile"

  val damCore = "com.day.cq.dam" % "cq-dam-core" % "5.3.4" % "compile"
  
  val crxRmi = "com.day.crx" % "crx-rmi" % "2.0.0" % "compile"

  val nymagRepo = "NYMAG Repo" at "http://crash.nymag.biz:8080/archiva/repository/internal/"


//  val akka =

  //val akka_repo = "Akka Maven Repository" at "http://scalablesolutions.se/akka/repository"

   
  val xalan = "xalan" % "xalan" % "2.7.1" % "compile"
  
  // test dependencies
  val scalaTest = "org.scalatest" % "scalatest" % "1.2" % "test"  
  
  // db drivers
  val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.9" % "compile"
  val h2 = "com.h2database" % "h2" % "1.2.127" % "provided"
  
  // actions
  lazy val hello = task{println("running hello"); None}     
  lazy val printDefs = task{println(redis.getClass); None}
  
}