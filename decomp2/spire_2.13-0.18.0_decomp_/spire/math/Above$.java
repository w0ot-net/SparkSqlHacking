package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Above$ implements Serializable {
   public static final Above$ MODULE$ = new Above$();

   public final String toString() {
      return "Above";
   }

   public Above apply(final Object lower, final int flags) {
      return new Above(lower, flags);
   }

   public Option unapply(final Above x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.lower(), BoxesRunTime.boxToInteger(x$0.flags()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Above$.class);
   }

   private Above$() {
   }
}
