package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcB$sp extends CanCopy.OpArray {
   public byte[] apply(final byte[] from) {
      return this.apply$mcB$sp(from);
   }

   public byte[] apply$mcB$sp(final byte[] from) {
      return (byte[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
