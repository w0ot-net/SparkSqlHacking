package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDD$sp;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$sincpi$sincpiDoubleImpl$ implements UFunc$UImpl$mcDD$sp {
   public static final package$sincpi$sincpiDoubleImpl$ MODULE$ = new package$sincpi$sincpiDoubleImpl$();

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

   public double apply(final double v) {
      return this.apply$mcDD$sp(v);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$sincpi$sincpiDoubleImpl$.class);
   }

   public double apply$mcDD$sp(final double v) {
      double var10000;
      if (v == (double)0) {
         var10000 = (double)1.0F;
      } else {
         double temp = v * Math.PI;
         var10000 = .MODULE$.sin(temp) / temp;
      }

      return var10000;
   }
}
