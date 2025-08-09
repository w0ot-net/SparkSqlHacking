package breeze.optimize;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BatchSize$ extends AbstractFunction1 implements Serializable {
   public static final BatchSize$ MODULE$ = new BatchSize$();

   public final String toString() {
      return "BatchSize";
   }

   public BatchSize apply(final int size) {
      return new BatchSize(size);
   }

   public Option unapply(final BatchSize x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.size())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BatchSize$.class);
   }

   private BatchSize$() {
   }
}
