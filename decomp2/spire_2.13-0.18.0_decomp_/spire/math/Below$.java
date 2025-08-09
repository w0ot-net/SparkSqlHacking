package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Below$ implements Serializable {
   public static final Below$ MODULE$ = new Below$();

   public final String toString() {
      return "Below";
   }

   public Below apply(final Object upper, final int flags) {
      return new Below(upper, flags);
   }

   public Option unapply(final Below x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.upper(), BoxesRunTime.boxToInteger(x$0.flags()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Below$.class);
   }

   private Below$() {
   }
}
