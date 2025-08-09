package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class RightModule$ implements Serializable {
   public static final RightModule$ MODULE$ = new RightModule$();

   public final RightModule apply(final RightModule V) {
      return V;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RightModule$.class);
   }

   public final RightModule apply$mDc$sp(final RightModule V) {
      return V;
   }

   public final RightModule apply$mFc$sp(final RightModule V) {
      return V;
   }

   public final RightModule apply$mIc$sp(final RightModule V) {
      return V;
   }

   public final RightModule apply$mJc$sp(final RightModule V) {
      return V;
   }

   private RightModule$() {
   }
}
