package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcD$sp extends CanCopy.OpArray {
   public double[] apply(final double[] from) {
      return this.apply$mcD$sp(from);
   }

   public double[] apply$mcD$sp(final double[] from) {
      return (double[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
