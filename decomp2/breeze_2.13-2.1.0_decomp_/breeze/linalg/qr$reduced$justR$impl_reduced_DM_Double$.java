package breeze.linalg;

import breeze.generic.UFunc;
import scala.runtime.ModuleSerializationProxy;

public class qr$reduced$justR$impl_reduced_DM_Double$ implements UFunc.UImpl {
   public static final qr$reduced$justR$impl_reduced_DM_Double$ MODULE$ = new qr$reduced$justR$impl_reduced_DM_Double$();

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

   public DenseMatrix apply(final DenseMatrix v) {
      return (DenseMatrix)qr$.MODULE$.breeze$linalg$qr$$doQr(v, true, ReducedQR$.MODULE$)._2();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(qr$reduced$justR$impl_reduced_DM_Double$.class);
   }
}
