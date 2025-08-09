package spire.math.prime;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BitSet$ implements Serializable {
   public static final BitSet$ MODULE$ = new BitSet$();

   public BitSet alloc(final int length) {
      return new BitSet(length, new int[length >>> 5]);
   }

   public BitSet apply(final int length, final int[] array) {
      return new BitSet(length, array);
   }

   public Option unapply(final BitSet x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.length()), x$0.array())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BitSet$.class);
   }

   private BitSet$() {
   }
}
