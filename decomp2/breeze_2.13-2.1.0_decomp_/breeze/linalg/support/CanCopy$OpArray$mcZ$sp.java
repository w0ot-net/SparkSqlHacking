package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcZ$sp extends CanCopy.OpArray {
   public boolean[] apply(final boolean[] from) {
      return this.apply$mcZ$sp(from);
   }

   public boolean[] apply$mcZ$sp(final boolean[] from) {
      return (boolean[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
