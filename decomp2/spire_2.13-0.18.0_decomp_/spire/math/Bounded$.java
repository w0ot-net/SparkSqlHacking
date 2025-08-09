package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Bounded$ implements Serializable {
   public static final Bounded$ MODULE$ = new Bounded$();

   public final String toString() {
      return "Bounded";
   }

   public Bounded apply(final Object lower, final Object upper, final int flags) {
      return new Bounded(lower, upper, flags);
   }

   public Option unapply(final Bounded x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.lower(), x$0.upper(), BoxesRunTime.boxToInteger(x$0.flags()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Bounded$.class);
   }

   private Bounded$() {
   }
}
