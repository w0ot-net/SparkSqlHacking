package scala.reflect.internal.util;

import scala.Function0;

public final class ReusableInstance$ {
   public static final ReusableInstance$ MODULE$ = new ReusableInstance$();

   private final int InitialSize() {
      return 4;
   }

   public ReusableInstance apply(final Function0 make, final int initialSize) {
      return new ReusableInstance(make, initialSize);
   }

   public ReusableInstance apply(final Function0 make) {
      int apply_initialSize = 4;
      return new ReusableInstance(make, apply_initialSize);
   }

   public ReusableInstance apply(final Function0 make, final boolean enabled) {
      if (enabled) {
         int apply_apply_initialSize = 4;
         return new ReusableInstance(make, apply_apply_initialSize);
      } else {
         int apply_initialSize = -1;
         return new ReusableInstance(make, apply_initialSize);
      }
   }

   public ReusableInstance apply(final Function0 make, final int initialSize, final boolean enabled) {
      if (enabled) {
         return new ReusableInstance(make, initialSize);
      } else {
         int apply_initialSize = -1;
         return new ReusableInstance(make, apply_initialSize);
      }
   }

   private ReusableInstance$() {
   }
}
