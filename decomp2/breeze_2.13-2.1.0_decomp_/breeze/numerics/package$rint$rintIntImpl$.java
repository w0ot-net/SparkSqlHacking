package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcII$sp;
import scala.runtime.ModuleSerializationProxy;

public class package$rint$rintIntImpl$ implements UFunc$UImpl$mcII$sp {
   public static final package$rint$rintIntImpl$ MODULE$ = new package$rint$rintIntImpl$();

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

   public int apply(final int v) {
      return this.apply$mcII$sp(v);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$rint$rintIntImpl$.class);
   }

   public int apply$mcII$sp(final int v) {
      return v;
   }
}
