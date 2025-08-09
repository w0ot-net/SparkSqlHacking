package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcJ$sp extends CanCopy.OpArray {
   public long[] apply(final long[] from) {
      return this.apply$mcJ$sp(from);
   }

   public long[] apply$mcJ$sp(final long[] from) {
      return (long[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
