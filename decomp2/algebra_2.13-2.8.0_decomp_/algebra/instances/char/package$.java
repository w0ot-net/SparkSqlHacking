package algebra.instances.char;

import algebra.instances.CharInstances;
import cats.kernel.instances.CharOrder;

public final class package$ implements CharInstances {
   public static final package$ MODULE$ = new package$();
   private static CharOrder catsKernelStdOrderForChar;

   static {
      cats.kernel.instances.CharInstances.$init$(MODULE$);
   }

   public CharOrder catsKernelStdOrderForChar() {
      return catsKernelStdOrderForChar;
   }

   public void cats$kernel$instances$CharInstances$_setter_$catsKernelStdOrderForChar_$eq(final CharOrder x$1) {
      catsKernelStdOrderForChar = x$1;
   }

   private package$() {
   }
}
