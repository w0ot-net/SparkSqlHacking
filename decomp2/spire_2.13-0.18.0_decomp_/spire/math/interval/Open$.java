package spire.math.interval;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Open$ implements Serializable {
   public static final Open$ MODULE$ = new Open$();

   public final String toString() {
      return "Open";
   }

   public Open apply(final Object a) {
      return new Open(a);
   }

   public Option unapply(final Open x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.a()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Open$.class);
   }

   private Open$() {
   }
}
