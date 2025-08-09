package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SafeLongLong$ extends AbstractFunction1 implements Serializable {
   public static final SafeLongLong$ MODULE$ = new SafeLongLong$();

   public final String toString() {
      return "SafeLongLong";
   }

   public SafeLongLong apply(final long x) {
      return new SafeLongLong(x);
   }

   public Option unapply(final SafeLongLong x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.x())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SafeLongLong$.class);
   }

   private SafeLongLong$() {
   }
}
