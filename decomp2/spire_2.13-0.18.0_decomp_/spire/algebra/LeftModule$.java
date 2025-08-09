package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LeftModule$ implements Serializable {
   public static final LeftModule$ MODULE$ = new LeftModule$();

   public final LeftModule apply(final LeftModule V) {
      return V;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LeftModule$.class);
   }

   public final LeftModule apply$mDc$sp(final LeftModule V) {
      return V;
   }

   public final LeftModule apply$mFc$sp(final LeftModule V) {
      return V;
   }

   public final LeftModule apply$mIc$sp(final LeftModule V) {
      return V;
   }

   public final LeftModule apply$mJc$sp(final LeftModule V) {
      return V;
   }

   private LeftModule$() {
   }
}
