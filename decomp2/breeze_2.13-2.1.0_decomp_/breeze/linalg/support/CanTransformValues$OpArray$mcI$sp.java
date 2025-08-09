package breeze.linalg.support;

import scala.Function1;

public class CanTransformValues$OpArray$mcI$sp extends CanTransformValues.OpArray implements CanTransformValues$mcI$sp {
   public void transform(final int[] from, final Function1 fn) {
      this.transform$mcI$sp(from, fn);
   }

   public void transform$mcI$sp(final int[] from, final Function1 fn) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         from[index$macro$2] = fn.apply$mcII$sp(from[index$macro$2]);
      }

   }

   public void transformActive(final int[] from, final Function1 fn) {
      this.transformActive$mcI$sp(from, fn);
   }

   public void transformActive$mcI$sp(final int[] from, final Function1 fn) {
      this.transform$mcI$sp(from, fn);
   }
}
