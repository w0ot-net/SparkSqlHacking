package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class CModule$ implements Serializable {
   public static final CModule$ MODULE$ = new CModule$();

   public final CModule apply(final CModule V) {
      return V;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CModule$.class);
   }

   public final CModule apply$mDc$sp(final CModule V) {
      return V;
   }

   public final CModule apply$mFc$sp(final CModule V) {
      return V;
   }

   public final CModule apply$mIc$sp(final CModule V) {
      return V;
   }

   public final CModule apply$mJc$sp(final CModule V) {
      return V;
   }

   private CModule$() {
   }
}
