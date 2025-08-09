package breeze.linalg.support;

import scala.Function1;
import scala.reflect.ClassTag;

public class CanMapValues$OpArray$mcFI$sp extends CanMapValues.OpArray implements CanMapValues$mcFI$sp {
   private final ClassTag evidence$1;

   public int[] map(final float[] from, final Function1 fn) {
      return this.map$mcFI$sp(from, fn);
   }

   public int[] map$mcFI$sp(final float[] from, final Function1 fn) {
      int[] arr = (int[])this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(from.length);
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         arr[index$macro$2] = fn.apply$mcIF$sp(from[index$macro$2]);
      }

      return arr;
   }

   public int[] mapActive(final float[] from, final Function1 fn) {
      return this.mapActive$mcFI$sp(from, fn);
   }

   public int[] mapActive$mcFI$sp(final float[] from, final Function1 fn) {
      return this.map$mcFI$sp(from, fn);
   }

   public CanMapValues$OpArray$mcFI$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }
}
