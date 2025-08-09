package breeze.numerics;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl2$mcDDD$sp;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public class package$atan2$atan2DoubleImpl$ implements UFunc$UImpl2$mcDDD$sp {
   public static final package$atan2$atan2DoubleImpl$ MODULE$ = new package$atan2$atan2DoubleImpl$();

   public float apply$mcDDF$sp(final double v, final double v2) {
      return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
   }

   public int apply$mcDDI$sp(final double v, final double v2) {
      return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
   }

   public double apply$mcDFD$sp(final double v, final float v2) {
      return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
   }

   public float apply$mcDFF$sp(final double v, final float v2) {
      return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
   }

   public int apply$mcDFI$sp(final double v, final float v2) {
      return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
   }

   public double apply$mcDID$sp(final double v, final int v2) {
      return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
   }

   public float apply$mcDIF$sp(final double v, final int v2) {
      return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
   }

   public int apply$mcDII$sp(final double v, final int v2) {
      return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
   }

   public double apply$mcFDD$sp(final float v, final double v2) {
      return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
   }

   public float apply$mcFDF$sp(final float v, final double v2) {
      return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
   }

   public int apply$mcFDI$sp(final float v, final double v2) {
      return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
   }

   public double apply$mcFFD$sp(final float v, final float v2) {
      return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
   }

   public float apply$mcFFF$sp(final float v, final float v2) {
      return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
   }

   public int apply$mcFFI$sp(final float v, final float v2) {
      return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
   }

   public double apply$mcFID$sp(final float v, final int v2) {
      return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
   }

   public float apply$mcFIF$sp(final float v, final int v2) {
      return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
   }

   public int apply$mcFII$sp(final float v, final int v2) {
      return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
   }

   public double apply$mcIDD$sp(final int v, final double v2) {
      return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
   }

   public float apply$mcIDF$sp(final int v, final double v2) {
      return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
   }

   public int apply$mcIDI$sp(final int v, final double v2) {
      return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
   }

   public double apply$mcIFD$sp(final int v, final float v2) {
      return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
   }

   public float apply$mcIFF$sp(final int v, final float v2) {
      return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
   }

   public int apply$mcIFI$sp(final int v, final float v2) {
      return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
   }

   public double apply$mcIID$sp(final int v, final int v2) {
      return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
   }

   public float apply$mcIIF$sp(final int v, final int v2) {
      return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
   }

   public int apply$mcIII$sp(final int v, final int v2) {
      return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
   }

   public double apply(final double v, final double v2) {
      return this.apply$mcDDD$sp(v, v2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$atan2$atan2DoubleImpl$.class);
   }

   public double apply$mcDDD$sp(final double v, final double v2) {
      return .MODULE$.atan2(v, v2);
   }
}
