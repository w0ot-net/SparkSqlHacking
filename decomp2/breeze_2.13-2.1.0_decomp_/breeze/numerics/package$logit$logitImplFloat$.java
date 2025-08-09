package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcFF$sp;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$logit$logitImplFloat$ implements UFunc$UImpl$mcFF$sp {
   public static final package$logit$logitImplFloat$ MODULE$ = new package$logit$logitImplFloat$();

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

   public float apply(final float x) {
      return this.apply$mcFF$sp(x);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$logit$logitImplFloat$.class);
   }

   public float apply$mcFF$sp(final float x) {
      return -((float).MODULE$.log((double)(1.0F / x - 1.0F)));
   }
}
