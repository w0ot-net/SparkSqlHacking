package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcI$sp extends CanCopy.OpArray {
   public int[] apply(final int[] from) {
      return this.apply$mcI$sp(from);
   }

   public int[] apply$mcI$sp(final int[] from) {
      return (int[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
