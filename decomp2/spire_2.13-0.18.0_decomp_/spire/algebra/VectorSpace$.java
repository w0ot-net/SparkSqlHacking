package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class VectorSpace$ implements Serializable {
   public static final VectorSpace$ MODULE$ = new VectorSpace$();

   public final VectorSpace apply(final VectorSpace V) {
      return V;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorSpace$.class);
   }

   public final VectorSpace apply$mDc$sp(final VectorSpace V) {
      return V;
   }

   public final VectorSpace apply$mFc$sp(final VectorSpace V) {
      return V;
   }

   public final VectorSpace apply$mIc$sp(final VectorSpace V) {
      return V;
   }

   public final VectorSpace apply$mJc$sp(final VectorSpace V) {
      return V;
   }

   private VectorSpace$() {
   }
}
