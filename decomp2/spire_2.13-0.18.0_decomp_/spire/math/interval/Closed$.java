package spire.math.interval;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Closed$ implements Serializable {
   public static final Closed$ MODULE$ = new Closed$();

   public final String toString() {
      return "Closed";
   }

   public Closed apply(final Object a) {
      return new Closed(a);
   }

   public Option unapply(final Closed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.a()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Closed$.class);
   }

   private Closed$() {
   }
}
