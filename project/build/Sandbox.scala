import sbt._

class SandboxProject(info: ProjectInfo) extends DefaultProject(info) with IdeaProject
{ 
    // dependencies 
  val redis = "com.redis" % "redisclient" % "2.8.0.RC3-1.4-SNAPSHOT" % "compile"       
  val akkaRepo = "Akka Repo" at "http://scalablesolutions.se/akka/repository"
  
  val jcr = "javax.jcr" % "jcr" % "2.0" % "compile"  
  val squerly = "org.squeryl" % "squeryl_2.8.0.RC6" % "0.9.4beta6" % "compile" 
  val jackrabbit = "org.apache.jackrabbit" %  "jackrabbit-core" % "2.1.0" % "compile" 
  val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.0" % "compile"
  
  val jcrRmi = "org.apache.jackrabbit" % "jackrabbit-jcr-rmi" % "2.0.0" % "compile"  
  val slingJson ="org.apache.sling" % "org.apache.sling.commons.json" % "2.0.4-incubator" % "compile"
  
  val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.9" % "compile"
  
  // test dependencies
  val scalaTest = "org.scalatest" % "scalatest" % "1.2-for-scala-2.8.0.RC6-SNAPSHOT" % "test"
  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  val h2 = "com.h2database" % "h2" % "1.2.127" % "provided"
  
  // actions
  lazy val hello = task{println("running hello"); None}     
  lazy val printDefs = task{println(redis.getClass); None}
  
}