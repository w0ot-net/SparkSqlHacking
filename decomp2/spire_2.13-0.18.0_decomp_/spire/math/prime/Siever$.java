package spire.math.prime;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import spire.math.SafeLong;

public final class Siever$ extends AbstractFunction2 implements Serializable {
   public static final Siever$ MODULE$ = new Siever$();

   public final String toString() {
      return "Siever";
   }

   public Siever apply(final int chunkSize, final SafeLong cutoff) {
      return new Siever(chunkSize, cutoff);
   }

   public Option unapply(final Siever x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.chunkSize()), x$0.cutoff())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Siever$.class);
   }

   private Siever$() {
   }
}
