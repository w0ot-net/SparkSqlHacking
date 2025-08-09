package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcDD$sp extends CanMapValues.OpArray implements CanMapValues$mcDD$sp {
   private final ClassTag evidence$1;

   public double[] map(final double[] from, final Function1 fn) {
      return this.map$mcDD$sp(from, fn);
   }

   public double[] map$mcDD$sp(final double[] from, final Function1 fn) {
      double[] arr = (double[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcDD$sp(from[index$macro$2]);
      }

      return arr;
   }

   public double[] mapActive(final double[] from, final Function1 fn) {
      return this.mapActive$mcDD$sp(from, fn);
   }

   public double[] mapActive$mcDD$sp(final double[] from, final Function1 fn) {
      return this.map$mcDD$sp(from, fn);
   }

   public CanMapValues$OpArray$mcDD$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
