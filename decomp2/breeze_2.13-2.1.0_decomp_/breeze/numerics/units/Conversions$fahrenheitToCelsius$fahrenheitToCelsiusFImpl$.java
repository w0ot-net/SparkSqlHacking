package breeze.numerics.units;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcFF$sp;
import scala.runtime.ModuleSerializationProxy;

public class Conversions$fahrenheitToCelsius$fahrenheitToCelsiusFImpl$ implements UFunc$UImpl$mcFF$sp {
   public static final Conversions$fahrenheitToCelsius$fahrenheitToCelsiusFImpl$ MODULE$ = new Conversions$fahrenheitToCelsius$fahrenheitToCelsiusFImpl$();

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

   public float apply(final float f) {
      return this.apply$mcFF$sp(f);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Conversions$fahrenheitToCelsius$fahrenheitToCelsiusFImpl$.class);
   }

   public float apply$mcFF$sp(final float f) {
      return (f - 32.0F) * 5.0F / 9.0F;
   }
}
