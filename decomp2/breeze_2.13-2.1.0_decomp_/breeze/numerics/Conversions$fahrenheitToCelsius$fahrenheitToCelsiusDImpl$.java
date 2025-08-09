package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDD$sp;
import scala.runtime.ModuleSerializationProxy;

public class Conversions$fahrenheitToCelsius$fahrenheitToCelsiusDImpl$ implements UFunc$UImpl$mcDD$sp {
   public static final Conversions$fahrenheitToCelsius$fahrenheitToCelsiusDImpl$ MODULE$ = new Conversions$fahrenheitToCelsius$fahrenheitToCelsiusDImpl$();

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

   public double apply(final double f) {
      return this.apply$mcDD$sp(f);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Conversions$fahrenheitToCelsius$fahrenheitToCelsiusDImpl$.class);
   }

   public double apply$mcDD$sp(final double f) {
      return (f - (double)32.0F) * (double)5.0F / (double)9.0F;
   }
}
