package breeze.linalg.support;

import scala.Function1;

public class CanTransformValues$OpArray$mcD$sp extends CanTransformValues.OpArray implements CanTransformValues$mcD$sp {
   public void transform(final double[] from, final Function1 fn) {
      this.transform$mcD$sp(from, fn);
   }

   public void transform$mcD$sp(final double[] from, final Function1 fn) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         from[index$macro$2] = fn.apply$mcDD$sp(from[index$macro$2]);
      }

   }

   public void transformActive(final double[] from, final Function1 fn) {
      this.transformActive$mcD$sp(from, fn);
   }

   public void transformActive$mcD$sp(final double[] from, final Function1 fn) {
      this.transform$mcD$sp(from, fn);
   }
}
