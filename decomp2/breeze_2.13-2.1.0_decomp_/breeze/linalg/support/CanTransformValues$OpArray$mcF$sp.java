package breeze.linalg.support;

import scala.Function1;

public class CanTransformValues$OpArray$mcF$sp extends CanTransformValues.OpArray implements CanTransformValues$mcF$sp {
   public void transform(final float[] from, final Function1 fn) {
      this.transform$mcF$sp(from, fn);
   }

   public void transform$mcF$sp(final float[] from, final Function1 fn) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = from.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         from[index$macro$2] = fn.apply$mcFF$sp(from[index$macro$2]);
      }

   }

   public void transformActive(final float[] from, final Function1 fn) {
      this.transformActive$mcF$sp(from, fn);
   }

   public void transformActive$mcF$sp(final float[] from, final Function1 fn) {
      this.transform$mcF$sp(from, fn);
   }
}
