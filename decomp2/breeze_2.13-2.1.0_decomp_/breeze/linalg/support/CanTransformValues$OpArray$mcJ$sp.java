package breeze.linalg.support;

import scala.Function1;

public class CanTransformValues$OpArray$mcJ$sp extends CanTransformValues.OpArray {
   public void transform(final long[] from, final Function1 fn) {
      this.transform$mcJ$sp(from, fn);
   }

   public void transform$mcJ$sp(final long[] from, final Function1 fn) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         from[index$macro$2] = fn.apply$mcJJ$sp(from[index$macro$2]);
      }

   }

   public void transformActive(final long[] from, final Function1 fn) {
      this.transformActive$mcJ$sp(from, fn);
   }

   public void transformActive$mcJ$sp(final long[] from, final Function1 fn) {
      this.transform$mcJ$sp(from, fn);
   }
}
