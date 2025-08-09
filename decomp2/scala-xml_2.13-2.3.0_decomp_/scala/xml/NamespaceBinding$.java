package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class NamespaceBinding$ extends AbstractFunction3 implements Serializable {
   public static final NamespaceBinding$ MODULE$ = new NamespaceBinding$();

   public final String toString() {
      return "NamespaceBinding";
   }

   public NamespaceBinding apply(final String prefix, final String uri, final NamespaceBinding parent) {
      return new NamespaceBinding(prefix, uri, parent);
   }

   public Option unapply(final NamespaceBinding x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.prefix(), x$0.uri(), x$0.parent())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NamespaceBinding$.class);
   }

   private NamespaceBinding$() {
   }
}
