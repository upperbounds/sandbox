import sbt._

class SandboxProject(info: ProjectInfo) extends ParentProject(info) with IdeaProject
{
  val web = project("webapp", "Web Project", new WebappBuild(_))
  val sandbox  = project("sandbox", "Sandbox", new DefaultProject(_) with IdeaProject{
      
        var jackrabbitVersion = "2.2.4"
        val dispatchVersion = "0.8.0"
          // dependencies 
        val redis = "com.redis" % "redisclient" % "2.8.0.RC7-1.4" % "compile"       
        val akkaRepo = "Akka Repo" at "http://akka.io/repository/"

        val jcr = "javax.jcr" % "jcr" % "2.0" % "compile"  
        val squerly = "org.squeryl" % "squeryl_2.8.0" % "0.9.4beta8" % "compile" 
        val jackrabbit = "org.apache.jackrabbit" %  "jackrabbit-core" % jackrabbitVersion % "compile"

        val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "compile"

        val jcrRmi = "org.apache.jackrabbit" % "jackrabbit-jcr-rmi" % jackrabbitVersion % "compile"
        val slingJson ="org.apache.sling" % "org.apache.sling.commons.json" % "2.0.4-incubator" % "compile"
      //  val dayGFX = "com.day.commons" % "day-commons-gfx" % "2.0.13" % "compile"

        val sanselan = "org.apache.sanselan" % "sanselan" % "0.97-incubator" % "compile"

//        val damCore = "com.day.cq.dam" % "cq-dam-core" % "5.3.4" % "compile"

        val crxRmi = "com.day.crx" % "crx-rmi" % "2.0.0" % "compile"

        val nymagRepo = "NYMAG Repo" at "http://build01.nymetro.com:8080/archiva/repository/internal/"

        val google = "com.google.guava" % "guava" % "r09"

        var dispatch = "net.databinder" % "dispatch-http_2.8.1" % dispatchVersion
        lazy val commonsLogging = "commons-logging" % "commons-logging" % "1.0.3"

        val html5 = "nu.validator.htmlparser" % "htmlparser" % "1.2.1"
        
         //override def compileOptions = super.compileOptions ++ Seq(Unchecked)

            override def ivyXML =
            <dependencies>
                <exclude module="jcl-over-slf4j"/>
            </dependencies>

        //  val akka =

          //val akka_repo = "Akka Maven Repository" at "http://scalablesolutions.se/akka/repository"

          val jenaRepo = "Jena Repo" at "http://openjena.org/repo/"


          val xalan = "xalan" % "xalan" % "2.7.1" % "compile"

          // test dependencies
          val scalaTest = "org.scalatest" % "scalatest" % "1.2" % "test"  

          // db drivers
          val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.9" % "compile"
          val h2 = "com.h2database" % "h2" % "1.2.127" % "provided"

          // actions
          lazy val hello = task{println("running hello"); None}     
          lazy val printDefs = task{println(redis.getClass); None}
          val jena = "com.hp.hpl.jena" % "jena" % "2.6.4" % "compile"
  })

  class WebappBuild(info: ProjectInfo) extends DefaultWebProject(info) with IdeaProject
{
  val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "8.0.0.M2" % "test"
  val gwtDev = "com.google.gwt" % "gwt-dev" % "2.3.0"
  val gwtUser = "com.google.gwt" % "gwt-user" % "2.3.0"
}
}
