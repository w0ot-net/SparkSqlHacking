package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDI$sp;
import scala.runtime.ModuleSerializationProxy;

public class package$step$stepImplDouble$ implements UFunc$UImpl$mcDI$sp {
   public static final package$step$stepImplDouble$ MODULE$ = new package$step$stepImplDouble$();

   public double apply$mcDD$sp(final double v) {
      return UFunc.UImpl.apply$mcDD$sp$(this, v);
   }

   public float apply$mcDF$sp(final double v) {
      return UFunc.UImpl.apply$mcDF$sp$(this, v);
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

   public int apply(final double x) {
      return this.apply$mcDI$sp(x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$step$stepImplDouble$.class);
   }

   public int apply$mcDI$sp(final double x) {
      return x > (double)0.0F ? 1 : 0;
   }
}
