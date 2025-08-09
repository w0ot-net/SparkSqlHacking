package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class InnerProductSpace$ implements Serializable {
   public static final InnerProductSpace$ MODULE$ = new InnerProductSpace$();

   public final InnerProductSpace apply(final InnerProductSpace V) {
      return V;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InnerProductSpace$.class);
   }

   public final InnerProductSpace apply$mDc$sp(final InnerProductSpace V) {
      return V;
   }

   public final InnerProductSpace apply$mFc$sp(final InnerProductSpace V) {
      return V;
   }

   public final InnerProductSpace apply$mIc$sp(final InnerProductSpace V) {
      return V;
   }

   public final InnerProductSpace apply$mJc$sp(final InnerProductSpace V) {
      return V;
   }

   private InnerProductSpace$() {
   }
}
