package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcF$sp extends CanCopy.OpArray {
   public float[] apply(final float[] from) {
      return this.apply$mcF$sp(from);
   }

   public float[] apply$mcF$sp(final float[] from) {
      return (float[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
