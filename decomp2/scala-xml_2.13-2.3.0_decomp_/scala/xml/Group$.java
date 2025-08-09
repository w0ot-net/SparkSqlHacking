package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class Group$ extends AbstractFunction1 implements Serializable {
   public static final Group$ MODULE$ = new Group$();

   public final String toString() {
      return "Group";
   }

   public Group apply(final Seq nodes) {
      return new Group(nodes);
   }

   public Option unapply(final Group x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.nodes()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Group$.class);
   }

   private Group$() {
   }
}
