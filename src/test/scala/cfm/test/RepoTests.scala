package cfm

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import cfm.Repo._
import org.squeryl.adapters.H2Adapter
import org.squeryl.{SessionFactory, Session}

class StackSpec extends FlatSpec with ShouldMatchers {
  "A Repo" should "iterate over nodes" in {
    val r = new TransientRepoAdaptor()
    val s = r.repo.login()
    for (n <- s.getRootNode.getNodes) {
      println(n)
      for (p <- n.getProperties) println(p, p.getType, p.getValue.getString)
    }
    s.logout
  }
  "An adaptory" should "initialize a SessionFactory" in {
    import org.squeryl.SessionFactory
    import org.squeryl.adapters._
    /*

        Class.forName("com.mysql.jdbc.Driver");

        SessionFactory.concreteFactory = Some(() =>
          Session.create(
            java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3308/db"),
            new MySQLAdapter()))
    */

    Class.forName("org.h2.Driver");
    SessionFactory.concreteFactory = Some(() =>
      Session.create(
        java.sql.DriverManager.getConnection("jdbc:h2:~/test", "sa", ""),
        new H2Adapter()))

        SessionFactory.newSession
  }
  "A listing object" should "create a schema" in {
    import org.squeryl.customtypes.CustomTypesMode._
    import org.squeryl.customtypes._
    import cfm.Listing._
    SessionFactory.newSession.bindToCurrentThread
//    Listing.create
    val restaurants = from(listings)(p => where(p.clazz === "restaurants") select (p))
  }
}


