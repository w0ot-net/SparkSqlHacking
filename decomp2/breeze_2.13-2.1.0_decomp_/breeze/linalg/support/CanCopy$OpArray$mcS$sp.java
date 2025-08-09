package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcS$sp extends CanCopy.OpArray {
   public short[] apply(final short[] from) {
      return this.apply$mcS$sp(from);
   }

   public short[] apply$mcS$sp(final short[] from) {
      return (short[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
