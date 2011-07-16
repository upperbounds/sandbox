import javax.sound.midi.Sequence
import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "sandbox"
  val buildScalaVersion = "2.9.0-1"
  val buildVersion = "1.0"
  val buildSettings = Defaults.defaultSettings ++ Seq(organization :=
    buildOrganization,
    scalaVersion :=
      buildScalaVersion,
    version := buildVersion)
}

object Dependencies {
  var jackrabbitVersion = "2.2.4"
  val dispatchVersion = "0.8.3"

  val redis = "com.redis" % "redisclient" % "2.8.0.RC7-1.4" % "compile"
  val squerly = "org.squeryl" % "squeryl_2.9.0-1" % "0.9.4" % "compile"
  val jackrabbit = "org.apache.jackrabbit" % "jackrabbit-core" % jackrabbitVersion % "compile"
  val jackrabbitSpi = "org.apache.jackrabbit" % "jackrabbit-jcr2dav" % jackrabbitVersion % "compile"
  val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "compile"
  val jcrRmi = "org.apache.jackrabbit" % "jackrabbit-jcr-rmi" % jackrabbitVersion % "compile"
  val slingJson = "org.apache.sling" % "org.apache.sling.commons.json" % "2.0.4-incubator" % "compile"
  //        val crxRmi = "com.day.crx" % "crx-rmi" % "2.0.0" % "compile"
  val google = "com.google.guava" % "guava" % "r09"
  var dispatch = "net.databinder" % "dispatch-http_2.9.0" % dispatchVersion
  lazy val commonsLogging = "commons-logging" % "commons-logging" % "1.0.3"
  val html5 = "nu.validator.htmlparser" % "htmlparser" % "1.2.1"
  val jena = "com.hp.hpl.jena" % "jena" % "2.6.4" % "compile"
  val jenaTdb = "com.hp.hpl.jena" % "tdb" % "0.8.10" % "compile"
  val io = "com.github.scala-incubator.io" % "file_2.9.0" % "0.1.2"
  val specs2 = "org.specs2" %% "specs2" % "1.3" % "test"
  val configy = "net.lag" % "configgy" % "2.0.0" % "compile"
  val jenaRepo = "Jena Repo" at "http://openjena.org/repo/"
  val xalan = "xalan" % "xalan" % "2.7.1" % "compile"
  val jcr = "javax.jcr" % "jcr" % "2.0" % "compile"
  val scalazCore = "org.scalaz" %% "scalaz-core" % "6.0.1"
  // db drivers
  val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.9" % "compile"
  val h2 = "com.h2database" % "h2" % "1.2.127" % "provided"


  val gwtDev = "com.google.gwt" % "gwt-dev" % "2.3.0"
  val gwtUser = "com.google.gwt" % "gwt-user" % "2.3.0"

  //override def compileOptions = super.compileOptions ++ Seq(Unchecked)
  //
  //            override def ivyXML =
  //            <dependencies>
  //                <exclude module="jcl-over-slf4j"/>
  //            </dependencies>

  // test dependencies
  val scalaTest = "org.scalatest" % "scalatest" % "1.2" % "test"


  val compileDeps = Seq(jackrabbit, jena, dispatch, jcrRmi, io, configy, jcr, squerly, html5, xalan, redis, scalazCore, jackrabbitSpi, jenaTdb, mysqlDriver)
  val testDeps = Seq(specs2)
  val allDeps = compileDeps ++ testDeps

  val webCompileDeps = Seq(gwtDev, gwtUser)
  val webAllDeps = webCompileDeps ++ testDeps
}

object SandboxBuild extends Build {

  import Dependencies._
  import BuildSettings._

  lazy val sandbox = Project("sandbox", file("sandbox"),
    settings = buildSettings ++ Seq(libraryDependencies := allDeps,
      resolvers := Seq("Akka Repo" at "http://akka.io/repository/"))
  )
  lazy val web = Project("web", file("webapp"), settings = buildSettings ++ WebPlugin.webSettings ++ Seq(libraryDependencies := webAllDeps))

  lazy val browser = Project("repoBrowser", file("repobrowser"), settings = buildSettings)

}