import cfm.Listing
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import org.squeryl.adapters.H2Adapter
import org.squeryl.{SessionFactory, Session}

class StackSpec extends FlatSpec with ShouldMatchers {
  "An adaptor" should "initialize an Squerly SessionFactory" in {

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
   // Listing.create
    val restaurants = from(listings)(p => where(p.clazz === "restaurants") select (p))
  }
}