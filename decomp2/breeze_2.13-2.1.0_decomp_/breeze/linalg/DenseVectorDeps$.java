package breeze.linalg;

import breeze.linalg.support.CanCopy;
import scala.reflect.ClassTag;

public final class DenseVectorDeps$ {
   public static final DenseVectorDeps$ MODULE$ = new DenseVectorDeps$();

   public CanCopy canCopyDenseVector(final ClassTag evidence$19) {
      return new CanCopy() {
         public DenseVector apply(final DenseVector v1) {
            return v1.copy();
         }
      };
   }

   private DenseVectorDeps$() {
   }
}
