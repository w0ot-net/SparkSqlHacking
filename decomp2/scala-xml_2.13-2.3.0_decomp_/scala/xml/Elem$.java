package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class Elem$ implements Serializable {
   public static final Elem$ MODULE$ = new Elem$();

   public Elem apply(final String prefix, final String label, final MetaData attributes, final NamespaceBinding scope, final boolean minimizeEmpty, final Seq child) {
      return new Elem(prefix, label, attributes, scope, minimizeEmpty, child);
   }

   public Option unapplySeq(final Node n) {
      return (Option)((n instanceof SpecialNode ? true : n instanceof Group) ? .MODULE$ : new Some(new Tuple5(n.prefix(), n.label(), n.attributes(), n.scope(), n.child().toSeq())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Elem$.class);
   }

   private Elem$() {
   }
}
