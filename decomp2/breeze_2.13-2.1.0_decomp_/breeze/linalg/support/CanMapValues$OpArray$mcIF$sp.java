package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcIF$sp extends CanMapValues.OpArray implements CanMapValues$mcIF$sp {
   private final ClassTag evidence$1;

   public float[] map(final int[] from, final Function1 fn) {
      return this.map$mcIF$sp(from, fn);
   }

   public float[] map$mcIF$sp(final int[] from, final Function1 fn) {
      float[] arr = (float[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcFI$sp(from[index$macro$2]);
      }

      return arr;
   }

   public float[] mapActive(final int[] from, final Function1 fn) {
      return this.mapActive$mcIF$sp(from, fn);
   }

   public float[] mapActive$mcIF$sp(final int[] from, final Function1 fn) {
      return this.map$mcIF$sp(from, fn);
   }

   public CanMapValues$OpArray$mcIF$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
