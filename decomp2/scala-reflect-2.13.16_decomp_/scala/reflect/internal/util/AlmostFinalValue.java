package scala.reflect.internal.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MutableCallSite;

final class AlmostFinalValue {
   private static final MethodHandle K_FALSE;
   private static final MethodHandle K_TRUE;
   private final MutableCallSite callsite;
   final MethodHandle invoker;

   AlmostFinalValue() {
      this.callsite = new MutableCallSite(K_FALSE);
      this.invoker = this.callsite.dynamicInvoker();
   }

   void toggleOnAndDeoptimize() {
      if (this.callsite.getTarget() != K_TRUE) {
         this.callsite.setTarget(K_TRUE);
         MutableCallSite.syncAll(new MutableCallSite[]{this.callsite});
      }
   }

   static {
      K_FALSE = MethodHandles.constant(Boolean.TYPE, false);
      K_TRUE = MethodHandles.constant(Boolean.TYPE, true);
   }
}
