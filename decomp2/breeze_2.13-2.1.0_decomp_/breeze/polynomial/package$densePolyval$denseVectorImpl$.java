package breeze.polynomial;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.math.Semiring$;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class package$densePolyval$denseVectorImpl$ implements UFunc.UImpl2 {
   public static final package$densePolyval$denseVectorImpl$ MODULE$ = new package$densePolyval$denseVectorImpl$();

   public double apply$mcDDD$sp(final double v, final double v2) {
      return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
   }

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

   public DenseVector apply(final package.PolyDenseUFuncWrapper k, final DenseVector v) {
      double[] coeffs = k.p().coeffs$mcD$sp();
      int i = coeffs.length - 1;
      DenseVector result = DenseVector$.MODULE$.fill(v.size(), BoxesRunTime.boxToDouble(coeffs[i]), .MODULE$.Double(), Semiring$.MODULE$.semiringD());

      while(i > 0) {
         --i;
         double c = coeffs[i];
         int index$macro$2 = 0;

         for(int limit$macro$4 = result.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            result.update$mcD$sp(index$macro$2, result.apply$mcD$sp(index$macro$2) * v.apply$mcD$sp(index$macro$2) + c);
         }
      }

      return result;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$densePolyval$denseVectorImpl$.class);
   }
}
