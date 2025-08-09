package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcFI$sp;
import scala.runtime.ModuleSerializationProxy;

public class package$step$stepImplFloat$ implements UFunc$UImpl$mcFI$sp {
   public static final package$step$stepImplFloat$ MODULE$ = new package$step$stepImplFloat$();

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

   public double apply$mcID$sp(final int v) {
      return UFunc.UImpl.apply$mcID$sp$(this, v);
   }

   public float apply$mcIF$sp(final int v) {
      return UFunc.UImpl.apply$mcIF$sp$(this, v);
   }

   public int apply$mcII$sp(final int v) {
      return UFunc.UImpl.apply$mcII$sp$(this, v);
   }

   public int apply(final float x) {
      return this.apply$mcFI$sp(x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$step$stepImplFloat$.class);
   }

   public int apply$mcFI$sp(final float x) {
      return x > 0.0F ? 1 : 0;
   }
}
