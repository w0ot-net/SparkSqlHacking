package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDD$sp;
import scala.runtime.ModuleSerializationProxy;

public class Conversions$celsiusToFahrenheit$celsiusToFahrenheitDImpl$ implements UFunc$UImpl$mcDD$sp {
   public static final Conversions$celsiusToFahrenheit$celsiusToFahrenheitDImpl$ MODULE$ = new Conversions$celsiusToFahrenheit$celsiusToFahrenheitDImpl$();

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

   public double apply(final double c) {
      return this.apply$mcDD$sp(c);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Conversions$celsiusToFahrenheit$celsiusToFahrenheitDImpl$.class);
   }

   public double apply$mcDD$sp(final double c) {
      return c / (double)5.0F * (double)9.0F + (double)32.0F;
   }
}
