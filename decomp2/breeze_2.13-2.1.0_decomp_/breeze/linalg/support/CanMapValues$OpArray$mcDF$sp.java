package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcDF$sp extends CanMapValues.OpArray implements CanMapValues$mcDF$sp {
   private final ClassTag evidence$1;

   public float[] map(final double[] from, final Function1 fn) {
      return this.map$mcDF$sp(from, fn);
   }

   public float[] map$mcDF$sp(final double[] from, final Function1 fn) {
      float[] arr = (float[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcFD$sp(from[index$macro$2]);
      }

      return arr;
   }

   public float[] mapActive(final double[] from, final Function1 fn) {
      return this.mapActive$mcDF$sp(from, fn);
   }

   public float[] mapActive$mcDF$sp(final double[] from, final Function1 fn) {
      return this.map$mcDF$sp(from, fn);
   }

   public CanMapValues$OpArray$mcDF$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
