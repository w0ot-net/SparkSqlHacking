package breeze.linalg;

import breeze.generic.UFunc;
import scala.runtime.ModuleSerializationProxy;

public class svd$reduced$reduced_Svd_DM_Impl$ implements UFunc.UImpl {
   public static final svd$reduced$reduced_Svd_DM_Impl$ MODULE$ = new svd$reduced$reduced_Svd_DM_Impl$();

   public double apply$mcDD$sp(final double v) {
      return UFunc.UImpl.apply$mcDD$sp$(this, v);
   }

   public float apply$mcDF$sp(final double v) {
      return UFunc.UImpl.apply$mcDF$sp$(this, v);
   }

   public int apply$mcDI$sp(final double v) {
      return UFunc.UImpl.apply$mcDI$sp$(this, v);
   }

   public double apply$mcFD$sp(final float v) {
      return UFunc.UImpl.apply$mcFD$sp$(this, v);
   }

   public float apply$mcFF$sp(final float v) {
      return UFunc.UImpl.apply$mcFF$sp$(this, v);
   }

   public int apply$mcFI$sp(final float v) {
      return UFunc.UImpl.apply$mcFI$sp$(this, v);
   }

   public double apply$mcID$sp(final int v) {
      return UFunc.UImpl.apply$mcID$sp$(this, v);
   }

   public float apply$mcIF$sp(final int v) {
      return UFunc.UImpl.apply$mcIF$sp$(this, v);
   }

   public int apply$mcII$sp(final int v) {
      return UFunc.UImpl.apply$mcII$sp$(this, v);
   }

   public svd.SVD apply(final DenseMatrix mat) {
      return svd$.MODULE$.breeze$linalg$svd$$doSVD_Double(mat, ReducedSVD$.MODULE$);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(svd$reduced$reduced_Svd_DM_Impl$.class);
   }
}
