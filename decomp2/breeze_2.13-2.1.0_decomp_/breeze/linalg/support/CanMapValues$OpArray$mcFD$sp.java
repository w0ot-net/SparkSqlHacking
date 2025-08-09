package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcFD$sp extends CanMapValues.OpArray implements CanMapValues$mcFD$sp {
   private final ClassTag evidence$1;

   public double[] map(final float[] from, final Function1 fn) {
      return this.map$mcFD$sp(from, fn);
   }

   public double[] map$mcFD$sp(final float[] from, final Function1 fn) {
      double[] arr = (double[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcDF$sp(from[index$macro$2]);
      }

      return arr;
   }

   public double[] mapActive(final float[] from, final Function1 fn) {
      return this.mapActive$mcFD$sp(from, fn);
   }

   public double[] mapActive$mcFD$sp(final float[] from, final Function1 fn) {
      return this.map$mcFD$sp(from, fn);
   }

   public CanMapValues$OpArray$mcFD$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
