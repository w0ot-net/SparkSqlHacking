package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcFF$sp;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$nextPower2$nextPower2FloatImpl$ implements UFunc$UImpl$mcFF$sp {
   public static final package$nextPower2$nextPower2FloatImpl$ MODULE$ = new package$nextPower2$nextPower2FloatImpl$();

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

   public float apply(final float v) {
      return this.apply$mcFF$sp(v);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$nextPower2$nextPower2FloatImpl$.class);
   }

   public float apply$mcFF$sp(final float v) {
      return (float).MODULE$.pow((double)2.0F, package.nextExponent2$.MODULE$.apply$mDDc$sp((double)v, package$nextExponent2$nextExponent2DoubleImpl$.MODULE$));
   }
}
