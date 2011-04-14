package cfm

import java.util.{Iterator => JIter}
import javax.jcr.version.{Version, VersionIterator}
import javax.jcr.nodetype.{NodeType, NodeTypeIterator}
import javax.jcr.query.{Row, RowIterator}
import javax.jcr.observation.{Event, EventIterator, EventListener, EventListenerIterator, EventJournal}
import javax.jcr.security.{AccessControlPolicyIterator, AccessControlPolicy}
import org.apache.jackrabbit.rmi.client.{ClientRepositoryFactory}
import javax.jcr._

abstract class Repo(val url: String) {
  var repo: Repository
  var user: String
  var password: String
}

trait RMIRepository {
  val factory = new ClientRepositoryFactory()
  // repo = factory.getRepository("repoUrl")
}

object RMIRepository {
  //   def apply(url: String, user: String, password: String) = new Repo(url) with RMIRepository
}


trait JndiRepository extends Repo {
}

object Repo {

  def doWithAllNodes(node: Node, n: Node => Unit): Unit = {

    node.getNodes.foreach(f => {
      n(f);
      doWithAllNodes(f, n)
    })


  }

  def removeYearNodes() = {}

  trait RangeIter {
    def iter: RangeIterator

    def position = iter.getPosition

    def nodeSize = iter.getSize

    def skip(num: Long) = iter.skip(num)

    def remove = iter.remove

    def hasNext = iter.hasNext
  }

  // iterator implicits

  implicit def NodeItr2Iterator[A](i: NodeIterator): Iterator[Node] = new Iterator[Node] with RangeIter {
    def iter = i;

    def next: Node = iter.next.asInstanceOf[Node]
  }

  implicit def PropItr2Iterator[A](i: PropertyIterator): Iterator[Property] = new Iterator[Property] with RangeIter {
    def iter = i;

    def next: Property = iter.nextProperty.asInstanceOf[Property]
  }

  implicit def VersionItr2Iterator[A](i: VersionIterator): Iterator[Version] = new Iterator[Version] with RangeIter {
    def iter = i;

    def next: Version = iter.nextVersion.asInstanceOf[Version]
  }

  implicit def NodeTypeItr2Iterator[A](i: NodeTypeIterator): Iterator[NodeType] = new Iterator[NodeType] with RangeIter {
    def iter = i;

    def next: NodeType = iter.nextNodeType.asInstanceOf[NodeType]
  }

  implicit def RowItr2Iterator[A](i: RowIterator): Iterator[Row] = new Iterator[Row] with RangeIter {
    def iter = i;

    def next: Row = i.nextRow.asInstanceOf[Row]
  }

  implicit def EventItr2Iterator[A](i: EventJournal): Iterator[Event] = new Iterator[Event] with RangeIter {
    def iter = i;

    def next: Event = iter.nextEvent.asInstanceOf[Event];

    def skipTo(date: Long) = i.skipTo(date)
  }

  implicit def EventJournalItr2Iterator[A](i: EventIterator): Iterator[Event] = new Iterator[Event] with RangeIter {
    def iter = i;

    def next: Event = iter.nextEvent.asInstanceOf[Event]
  }

  implicit def EventListenerItr2Iterator[A](i: EventListenerIterator): Iterator[EventListener] = new Iterator[EventListener] with RangeIter {
    def iter = i;

    def next: EventListener = iter.nextEventListener.asInstanceOf[EventListener]
  }

  implicit def AccessControlPolicyItr2Iterator[A](i: AccessControlPolicyIterator): Iterator[AccessControlPolicy] = new Iterator[AccessControlPolicy] with RangeIter {
    def iter = i;

    def next: AccessControlPolicy = iter.nextAccessControlPolicy.asInstanceOf[AccessControlPolicy]
  }

  def ls(path: String)(implicit s: Session) {
    var t = s.getRootNode.getNode(path)
    for (p <- t.getNodes) {
      println(p.getName)
    }
  }

//  def collect(node: Node): List[Node] = {
//    def c(ns: List[Node], nd: Node): List[Node]={
//     var nodes= nd.getNodes.toList
//     nodes match {
//       case Nil =>
//     }
//     var ns2: List[Node] = for(n1 <- nodes) yield c(ns, n1)
//     ns2 ++ ns
//     }
//    c(List(), node)
//  }
//

    def collect(node: Node): List[Node] = {
      def c(ns: List[Node], nd: Node): List[Node] = {
        nd.getNodes.toList match {
          case Nil => nd :: ns
          case f: List[Node] =>  nd::  f.flatMap(m =>  c(ns,m))
        }
      }
      c(List(), node)
    }


  //
//  def sum(t: Tree): Int = {
//    def sumAcc(trees: List[Tree], acc: Int): Int = trees match {
//      case Nil => acc
//      case Leaf(x) :: rs => sumAcc(rs, x + acc)
//      case Node(l, r) :: rs => sumAcc(l :: r :: rs, acc)
//    }
//    sumAcc(List(t), 0)
//  }


}