package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcDJ$sp extends CanMapValues.OpArray implements CanMapValues$mcDJ$sp {
   private final ClassTag evidence$1;

   public long[] map(final double[] from, final Function1 fn) {
      return this.map$mcDJ$sp(from, fn);
   }

   public long[] map$mcDJ$sp(final double[] from, final Function1 fn) {
      long[] arr = (long[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcJD$sp(from[index$macro$2]);
      }

      return arr;
   }

   public long[] mapActive(final double[] from, final Function1 fn) {
      return this.mapActive$mcDJ$sp(from, fn);
   }

   public long[] mapActive$mcDJ$sp(final double[] from, final Function1 fn) {
      return this.map$mcDJ$sp(from, fn);
   }

   public CanMapValues$OpArray$mcDJ$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
