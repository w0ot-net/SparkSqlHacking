package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcID$sp;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$sigmoid$sigmoidImplInt$ implements UFunc$UImpl$mcID$sp {
   public static final package$sigmoid$sigmoidImplInt$ MODULE$ = new package$sigmoid$sigmoidImplInt$();

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

   public float apply$mcIF$sp(final int v) {
      return UFunc.UImpl.apply$mcIF$sp$(this, v);
   }

   public int apply$mcII$sp(final int v) {
      return UFunc.UImpl.apply$mcII$sp$(this, v);
   }

   public double apply(final int x) {
      return this.apply$mcID$sp(x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$sigmoid$sigmoidImplInt$.class);
   }

   public double apply$mcID$sp(final int x) {
      return (double)1.0F / ((double)1.0F + .MODULE$.exp(-((double)x)));
   }
}
