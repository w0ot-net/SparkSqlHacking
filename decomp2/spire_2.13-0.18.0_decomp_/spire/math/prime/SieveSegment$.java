package spire.math.prime;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;
import spire.math.SafeLong;

public final class SieveSegment$ implements Serializable {
   public static final SieveSegment$ MODULE$ = new SieveSegment$();
   private static final int[] wheel30;

   static {
      long b = 0L;
      b |= 2L;
      b |= 128L;
      b |= 2048L;
      b |= 8192L;
      b |= 131072L;
      b |= 524288L;
      b |= 8388608L;
      b |= 536870912L;
      long n = b | b << (int)30L;
      int[] arr = new int[15];

      for(int index$macro$1 = 0; index$macro$1 < 15; ++index$macro$1) {
         arr[index$macro$1] = (int)(n >>> index$macro$1 * 2 & 4294967295L);
      }

      wheel30 = arr;
   }

   public int[] wheel30() {
      return wheel30;
   }

   public SieveSegment apply(final SafeLong start, final BitSet primes, final SafeLong cutoff) {
      return new SieveSegment(start, primes, cutoff);
   }

   public Option unapply(final SieveSegment x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.start(), x$0.primes(), x$0.cutoff())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SieveSegment$.class);
   }

   private SieveSegment$() {
   }
}
