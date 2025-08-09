package breeze.linalg.support;

import breeze.util.ArrayUtil$;

public class CanCopy$OpArray$mcC$sp extends CanCopy.OpArray {
   public char[] apply(final char[] from) {
      return this.apply$mcC$sp(from);
   }

   public char[] apply$mcC$sp(final char[] from) {
      return (char[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
