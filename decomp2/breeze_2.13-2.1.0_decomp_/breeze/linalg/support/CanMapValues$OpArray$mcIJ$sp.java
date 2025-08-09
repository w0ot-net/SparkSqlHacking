package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcIJ$sp extends CanMapValues.OpArray implements CanMapValues$mcIJ$sp {
   private final ClassTag evidence$1;

   public long[] map(final int[] from, final Function1 fn) {
      return this.map$mcIJ$sp(from, fn);
   }

   public long[] map$mcIJ$sp(final int[] from, final Function1 fn) {
      long[] arr = (long[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcJI$sp(from[index$macro$2]);
      }

      return arr;
   }

   public long[] mapActive(final int[] from, final Function1 fn) {
      return this.mapActive$mcIJ$sp(from, fn);
   }

   public long[] mapActive$mcIJ$sp(final int[] from, final Function1 fn) {
      return this.map$mcIJ$sp(from, fn);
   }

   public CanMapValues$OpArray$mcIJ$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
