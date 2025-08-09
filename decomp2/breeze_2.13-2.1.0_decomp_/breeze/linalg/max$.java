package breeze.linalg;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl2$mcDDD$sp;
import breeze.generic.UFunc$UImpl2$mcFFF$sp;
import breeze.generic.UFunc$UImpl2$mcIII$sp;
import breeze.generic.UFunc$UImpl3$mcDDDD$sp;
import breeze.generic.UFunc$UImpl3$mcFFFF$sp;
import breeze.generic.UFunc$UImpl3$mcIIII$sp;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcF$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcI$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcJ$sp;
import breeze.linalg.support.ScalarOf;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class max$ implements maxLowPrio, VectorizedReduceUFunc {
   public static final max$ MODULE$ = new max$();
   private static final UFunc.UImpl2 maxImpl2_Int;
   private static final UFunc.UImpl2 maxImpl2_Double;
   private static final UFunc.UImpl2 maxImpl2_Float;
   private static final UFunc.UImpl2 maxImpl2_Long;
   private static final UFunc.UImpl3 maxImpl3_Int;
   private static final UFunc.UImpl3 maxImpl3_Double;
   private static final UFunc.UImpl3 maxImpl3_Float;
   private static final UFunc.UImpl3 maxImpl3_Long;

   static {
      UFunc.$init$(MODULE$);
      maxLowPrio.$init$(MODULE$);
      VectorizedReduceUFunc.$init$(MODULE$);
      maxImpl2_Int = new UFunc$UImpl2$mcIII$sp() {
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

         public int apply(final int t1, final int t2) {
            return this.apply$mcIII$sp(t1, t2);
         }

         public int apply$mcIII$sp(final int t1, final int t2) {
            return .MODULE$.max(t1, t2);
         }
      };
      maxImpl2_Double = new UFunc$UImpl2$mcDDD$sp() {
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

         public double apply(final double t1, final double t2) {
            return this.apply$mcDDD$sp(t1, t2);
         }

         public double apply$mcDDD$sp(final double t1, final double t2) {
            return .MODULE$.max(t1, t2);
         }
      };
      maxImpl2_Float = new UFunc$UImpl2$mcFFF$sp() {
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

         public float apply(final float t1, final float t2) {
            return this.apply$mcFFF$sp(t1, t2);
         }

         public float apply$mcFFF$sp(final float t1, final float t2) {
            return .MODULE$.max(t1, t2);
         }
      };
      maxImpl2_Long = new UFunc.UImpl2() {
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

         public long apply(final long t1, final long t2) {
            return .MODULE$.max(t1, t2);
         }
      };
      maxImpl3_Int = new UFunc$UImpl3$mcIIII$sp() {
         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply(final int t1, final int t2, final int t3) {
            return this.apply$mcIIII$sp(t1, t2, t3);
         }

         public int apply$mcIIII$sp(final int t1, final int t2, final int t3) {
            return .MODULE$.max(.MODULE$.max(t1, t2), t3);
         }
      };
      maxImpl3_Double = new UFunc$UImpl3$mcDDDD$sp() {
         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public double apply(final double t1, final double t2, final double t3) {
            return this.apply$mcDDDD$sp(t1, t2, t3);
         }

         public double apply$mcDDDD$sp(final double t1, final double t2, final double t3) {
            return .MODULE$.max(.MODULE$.max(t1, t2), t3);
         }
      };
      maxImpl3_Float = new UFunc$UImpl3$mcFFFF$sp() {
         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public float apply(final float t1, final float t2, final float t3) {
            return this.apply$mcFFFF$sp(t1, t2, t3);
         }

         public float apply$mcFFFF$sp(final float t1, final float t2, final float t3) {
            return .MODULE$.max(.MODULE$.max(t1, t2), t3);
         }
      };
      maxImpl3_Long = new UFunc.UImpl3() {
         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public long apply(final long t1, final long t2, final long t3) {
            return .MODULE$.max(.MODULE$.max(t1, t2), t3);
         }
      };
   }

   public UFunc.UImpl vectorizeRows(final ClassTag evidence$1, final VectorizedReduceUFunc.VectorizeHelper helper, final UFunc.InPlaceImpl2 baseOp) {
      return VectorizedReduceUFunc.vectorizeRows$(this, evidence$1, helper, baseOp);
   }

   public UFunc.UImpl2 vectorizeRows2(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return VectorizedReduceUFunc.vectorizeRows2$(this, evidence$2, evidence$3, baseOp);
   }

   public UFunc.UImpl2 vectorizeRows2$mDc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return VectorizedReduceUFunc.vectorizeRows2$mDc$sp$(this, evidence$2, evidence$3, baseOp);
   }

   public UFunc.UImpl2 vectorizeRows2$mFc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return VectorizedReduceUFunc.vectorizeRows2$mFc$sp$(this, evidence$2, evidence$3, baseOp);
   }

   public UFunc.UImpl2 vectorizeRows2$mIc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return VectorizedReduceUFunc.vectorizeRows2$mIc$sp$(this, evidence$2, evidence$3, baseOp);
   }

   public UFunc.UImpl2 vectorizeRows2$mJc$sp(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return VectorizedReduceUFunc.vectorizeRows2$mJc$sp$(this, evidence$2, evidence$3, baseOp);
   }

   public UFunc.UImpl vectorizeCols_Double(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return VectorizedReduceUFunc.vectorizeCols_Double$(this, helper);
   }

   public UFunc.UImpl vectorizeCols_Float(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return VectorizedReduceUFunc.vectorizeCols_Float$(this, helper);
   }

   public UFunc.UImpl vectorizeCols_Int(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return VectorizedReduceUFunc.vectorizeCols_Int$(this, helper);
   }

   public UFunc.UImpl vectorizeCols_Long(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return VectorizedReduceUFunc.vectorizeCols_Long$(this, helper);
   }

   public UFunc.UImpl2 vectorizeCols2_Double(final UFunc.UImpl2 impl2) {
      return VectorizedReduceUFunc.vectorizeCols2_Double$(this, impl2);
   }

   public UFunc.UImpl2 vectorizeCols2_Float(final UFunc.UImpl2 impl2) {
      return VectorizedReduceUFunc.vectorizeCols2_Float$(this, impl2);
   }

   public UFunc.UImpl2 vectorizeCols2_Int(final UFunc.UImpl2 impl2) {
      return VectorizedReduceUFunc.vectorizeCols2_Int$(this, impl2);
   }

   public UFunc.UImpl2 vectorizeCols2_Long(final UFunc.UImpl2 impl2) {
      return VectorizedReduceUFunc.vectorizeCols2_Long$(this, impl2);
   }

   public UFunc.UImpl2 maxVS(final ScalarOf cmvH, final UFunc.UImpl2 maxImpl, final CanMapValues cmv) {
      return maxLowPrio.maxVS$(this, cmvH, maxImpl, cmv);
   }

   public final Object apply(final Object v, final UFunc.UImpl impl) {
      return UFunc.apply$(this, v, impl);
   }

   public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDDc$sp$(this, v, impl);
   }

   public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDFc$sp$(this, v, impl);
   }

   public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDIc$sp$(this, v, impl);
   }

   public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFDc$sp$(this, v, impl);
   }

   public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFFc$sp$(this, v, impl);
   }

   public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFIc$sp$(this, v, impl);
   }

   public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIDc$sp$(this, v, impl);
   }

   public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIFc$sp$(this, v, impl);
   }

   public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIIc$sp$(this, v, impl);
   }

   public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$(this, v1, v2, impl);
   }

   public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$(this, v1, v2, v3, impl);
   }

   public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return UFunc.apply$(this, v1, v2, v3, v4, impl);
   }

   public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return UFunc.inPlace$(this, v, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return UFunc.inPlace$(this, v, v2, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return UFunc.inPlace$(this, v, v2, v3, impl);
   }

   public final Object withSink(final Object s) {
      return UFunc.withSink$(this, s);
   }

   public UFunc.UImpl2 maxImpl2_Int() {
      return maxImpl2_Int;
   }

   public UFunc.UImpl2 maxImpl2_Double() {
      return maxImpl2_Double;
   }

   public UFunc.UImpl2 maxImpl2_Float() {
      return maxImpl2_Float;
   }

   public UFunc.UImpl2 maxImpl2_Long() {
      return maxImpl2_Long;
   }

   public UFunc.UImpl3 maxImpl3_Int() {
      return maxImpl3_Int;
   }

   public UFunc.UImpl3 maxImpl3_Double() {
      return maxImpl3_Double;
   }

   public UFunc.UImpl3 maxImpl3_Float() {
      return maxImpl3_Float;
   }

   public UFunc.UImpl3 maxImpl3_Long() {
      return maxImpl3_Long;
   }

   public UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseValues iter$1;

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

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public int apply(final Object v) {
            class SumVisitor$1 implements CanTraverseValues$ValuesVisitor$mcI$sp {
               private int max;
               private boolean visitedOne;

               public void visitArray(final int[] arr) {
                  CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr);
               }

               public void visitArray$mcI$sp(final int[] arr) {
                  CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr);
               }

               public void visit$mcZ$sp(final boolean a) {
                  CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
               }

               public void visit$mcB$sp(final byte a) {
                  CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
               }

               public void visit$mcC$sp(final char a) {
                  CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
               }

               public void visit$mcD$sp(final double a) {
                  CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
               }

               public void visit$mcF$sp(final float a) {
                  CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
               }

               public void visit$mcJ$sp(final long a) {
                  CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
               }

               public void visit$mcS$sp(final short a) {
                  CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
               }

               public void visit$mcV$sp(final BoxedUnit a) {
                  CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
               }

               public void visitArray$mcZ$sp(final boolean[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
               }

               public void visitArray$mcB$sp(final byte[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
               }

               public void visitArray$mcC$sp(final char[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
               }

               public void visitArray$mcD$sp(final double[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
               }

               public void visitArray$mcF$sp(final float[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
               }

               public void visitArray$mcJ$sp(final long[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
               }

               public void visitArray$mcS$sp(final short[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
               }

               public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
               }

               public int max() {
                  return this.max;
               }

               public void max_$eq(final int x$1) {
                  this.max = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final int a) {
                  this.visit$mcI$sp(a);
               }

               public void zeros(final int numZero, final int zeroValue) {
                  this.zeros$mcI$sp(numZero, zeroValue);
               }

               public void visitArray(final int[] arr, final int offset, final int length, final int stride) {
                  this.visitArray$mcI$sp(arr, offset, length, stride);
               }

               public void visit$mcI$sp(final int a) {
                  this.visitedOne_$eq(true);
                  this.max_$eq(.MODULE$.max(this.max(), a));
               }

               public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                  if (numZero != 0) {
                     this.visitedOne_$eq(true);
                     this.max_$eq(.MODULE$.max(zeroValue, this.max()));
                  }

               }

               public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                  if (length >= 0) {
                     this.visitedOne_$eq(true);
                  }

                  if (stride == 1) {
                     int m = this.max();
                     int index$macro$2 = offset;

                     for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        m = .MODULE$.max(m, arr[index$macro$2]);
                     }

                     this.max_$eq(m);
                  } else {
                     int off = offset;
                     int m = this.max();
                     int index$macro$7 = 0;

                     for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                        m = .MODULE$.max(m, arr[off]);
                        off += stride;
                     }

                     this.max_$eq(m);
                  }

               }

               public SumVisitor$1() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.max = Integer.MIN_VALUE;
                  this.visitedOne = false;
               }
            }

            SumVisitor$1 visit = new SumVisitor$1();
            this.iter$1.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.max();
            }
         }

         public {
            this.iter$1 = iter$1;
         }
      };
   }

   public UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseValues iter$2;

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

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public double apply(final Object v) {
            class SumVisitor$2 implements CanTraverseValues$ValuesVisitor$mcD$sp {
               private double max;
               private boolean visitedOne;

               public void visitArray(final double[] arr) {
                  CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr);
               }

               public void visitArray$mcD$sp(final double[] arr) {
                  CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr);
               }

               public void visit$mcZ$sp(final boolean a) {
                  CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
               }

               public void visit$mcB$sp(final byte a) {
                  CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
               }

               public void visit$mcC$sp(final char a) {
                  CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
               }

               public void visit$mcF$sp(final float a) {
                  CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
               }

               public void visit$mcI$sp(final int a) {
                  CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
               }

               public void visit$mcJ$sp(final long a) {
                  CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
               }

               public void visit$mcS$sp(final short a) {
                  CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
               }

               public void visit$mcV$sp(final BoxedUnit a) {
                  CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
               }

               public void visitArray$mcZ$sp(final boolean[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
               }

               public void visitArray$mcB$sp(final byte[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
               }

               public void visitArray$mcC$sp(final char[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
               }

               public void visitArray$mcF$sp(final float[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
               }

               public void visitArray$mcI$sp(final int[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
               }

               public void visitArray$mcJ$sp(final long[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
               }

               public void visitArray$mcS$sp(final short[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
               }

               public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
               }

               public double max() {
                  return this.max;
               }

               public void max_$eq(final double x$1) {
                  this.max = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final double a) {
                  this.visit$mcD$sp(a);
               }

               public void zeros(final int numZero, final double zeroValue) {
                  this.zeros$mcD$sp(numZero, zeroValue);
               }

               public void visitArray(final double[] arr, final int offset, final int length, final int stride) {
                  this.visitArray$mcD$sp(arr, offset, length, stride);
               }

               public void visit$mcD$sp(final double a) {
                  this.visitedOne_$eq(true);
                  this.max_$eq(.MODULE$.max(this.max(), a));
               }

               public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                  if (numZero != 0) {
                     this.visitedOne_$eq(true);
                     this.max_$eq(.MODULE$.max(zeroValue, this.max()));
                  }

               }

               public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                  if (length >= 0) {
                     this.visitedOne_$eq(true);
                  }

                  if (stride == 1) {
                     double m = this.max();
                     int index$macro$2 = offset;

                     for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        m = .MODULE$.max(m, arr[index$macro$2]);
                     }

                     this.max_$eq(m);
                  } else {
                     int off = offset;
                     double m = this.max();
                     int index$macro$7 = 0;

                     for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                        m = .MODULE$.max(m, arr[off]);
                        off += stride;
                     }

                     this.max_$eq(m);
                  }

               }

               public SumVisitor$2() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.max = Double.NEGATIVE_INFINITY;
                  this.visitedOne = false;
               }
            }

            SumVisitor$2 visit = new SumVisitor$2();
            this.iter$2.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.max();
            }
         }

         public {
            this.iter$2 = iter$2;
         }
      };
   }

   public UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseValues iter$3;

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

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public float apply(final Object v) {
            class SumVisitor$3 implements CanTraverseValues$ValuesVisitor$mcF$sp {
               private float max;
               private boolean visitedOne;

               public void visitArray(final float[] arr) {
                  CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr);
               }

               public void visitArray$mcF$sp(final float[] arr) {
                  CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr);
               }

               public void visit$mcZ$sp(final boolean a) {
                  CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
               }

               public void visit$mcB$sp(final byte a) {
                  CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
               }

               public void visit$mcC$sp(final char a) {
                  CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
               }

               public void visit$mcD$sp(final double a) {
                  CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
               }

               public void visit$mcI$sp(final int a) {
                  CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
               }

               public void visit$mcJ$sp(final long a) {
                  CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
               }

               public void visit$mcS$sp(final short a) {
                  CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
               }

               public void visit$mcV$sp(final BoxedUnit a) {
                  CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
               }

               public void visitArray$mcZ$sp(final boolean[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
               }

               public void visitArray$mcB$sp(final byte[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
               }

               public void visitArray$mcC$sp(final char[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
               }

               public void visitArray$mcD$sp(final double[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
               }

               public void visitArray$mcI$sp(final int[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
               }

               public void visitArray$mcJ$sp(final long[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
               }

               public void visitArray$mcS$sp(final short[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
               }

               public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
               }

               public float max() {
                  return this.max;
               }

               public void max_$eq(final float x$1) {
                  this.max = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final float a) {
                  this.visit$mcF$sp(a);
               }

               public void zeros(final int numZero, final float zeroValue) {
                  this.zeros$mcF$sp(numZero, zeroValue);
               }

               public void visitArray(final float[] arr, final int offset, final int length, final int stride) {
                  this.visitArray$mcF$sp(arr, offset, length, stride);
               }

               public void visit$mcF$sp(final float a) {
                  this.visitedOne_$eq(true);
                  this.max_$eq(.MODULE$.max(this.max(), a));
               }

               public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                  if (numZero != 0) {
                     this.visitedOne_$eq(true);
                     this.max_$eq(.MODULE$.max(zeroValue, this.max()));
                  }

               }

               public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                  if (length >= 0) {
                     this.visitedOne_$eq(true);
                  }

                  if (stride == 1) {
                     float m = this.max();
                     int index$macro$2 = offset;

                     for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        m = .MODULE$.max(m, arr[index$macro$2]);
                     }

                     this.max_$eq(m);
                  } else {
                     int off = offset;
                     float m = this.max();
                     int index$macro$7 = 0;

                     for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                        m = .MODULE$.max(m, arr[off]);
                        off += stride;
                     }

                     this.max_$eq(m);
                  }

               }

               public SumVisitor$3() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.max = Float.NEGATIVE_INFINITY;
                  this.visitedOne = false;
               }
            }

            SumVisitor$3 visit = new SumVisitor$3();
            this.iter$3.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.max();
            }
         }

         public {
            this.iter$3 = iter$3;
         }
      };
   }

   public UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseValues iter$4;

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

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public long apply(final Object v) {
            class SumVisitor$4 implements CanTraverseValues$ValuesVisitor$mcJ$sp {
               private long max;
               private boolean visitedOne;

               public void visitArray(final long[] arr) {
                  CanTraverseValues$ValuesVisitor$mcJ$sp.visitArray$(this, arr);
               }

               public void visitArray$mcJ$sp(final long[] arr) {
                  CanTraverseValues$ValuesVisitor$mcJ$sp.visitArray$mcJ$sp$(this, arr);
               }

               public void visit$mcZ$sp(final boolean a) {
                  CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
               }

               public void visit$mcB$sp(final byte a) {
                  CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
               }

               public void visit$mcC$sp(final char a) {
                  CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
               }

               public void visit$mcD$sp(final double a) {
                  CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
               }

               public void visit$mcF$sp(final float a) {
                  CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
               }

               public void visit$mcI$sp(final int a) {
                  CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
               }

               public void visit$mcS$sp(final short a) {
                  CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
               }

               public void visit$mcV$sp(final BoxedUnit a) {
                  CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
               }

               public void visitArray$mcZ$sp(final boolean[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
               }

               public void visitArray$mcB$sp(final byte[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
               }

               public void visitArray$mcC$sp(final char[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
               }

               public void visitArray$mcD$sp(final double[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
               }

               public void visitArray$mcF$sp(final float[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
               }

               public void visitArray$mcI$sp(final int[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
               }

               public void visitArray$mcS$sp(final short[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
               }

               public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
               }

               public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                  CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
               }

               public long max() {
                  return this.max;
               }

               public void max_$eq(final long x$1) {
                  this.max = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final long a) {
                  this.visit$mcJ$sp(a);
               }

               public void zeros(final int numZero, final long zeroValue) {
                  this.zeros$mcJ$sp(numZero, zeroValue);
               }

               public void visitArray(final long[] arr, final int offset, final int length, final int stride) {
                  this.visitArray$mcJ$sp(arr, offset, length, stride);
               }

               public void visit$mcJ$sp(final long a) {
                  this.visitedOne_$eq(true);
                  this.max_$eq(.MODULE$.max(this.max(), a));
               }

               public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                  if (numZero != 0) {
                     this.visitedOne_$eq(true);
                     this.max_$eq(.MODULE$.max(zeroValue, this.max()));
                  }

               }

               public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                  if (length >= 0) {
                     this.visitedOne_$eq(true);
                  }

                  if (stride == 1) {
                     long m = this.max();
                     int index$macro$2 = offset;

                     for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        m = .MODULE$.max(m, arr[index$macro$2]);
                     }

                     this.max_$eq(m);
                  } else {
                     int off = offset;
                     long m = this.max();
                     int index$macro$7 = 0;

                     for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                        m = .MODULE$.max(m, arr[off]);
                        off += stride;
                     }

                     this.max_$eq(m);
                  }

               }

               public SumVisitor$4() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.max = Long.MIN_VALUE;
                  this.visitedOne = false;
               }
            }

            SumVisitor$4 visit = new SumVisitor$4();
            this.iter$4.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.max();
            }
         }

         public {
            this.iter$4 = iter$4;
         }
      };
   }

   public VectorizedReduceUFunc.VectorizeHelper helper_Int() {
      return new VectorizedReduceUFunc$VectorizeHelper$mcI$sp() {
         public DenseVector zerosLike$mcZ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcZ$sp$(this, len);
         }

         public DenseVector zerosLike$mcB$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcB$sp$(this, len);
         }

         public DenseVector zerosLike$mcC$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcC$sp$(this, len);
         }

         public DenseVector zerosLike$mcD$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcD$sp$(this, len);
         }

         public DenseVector zerosLike$mcF$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcF$sp$(this, len);
         }

         public DenseVector zerosLike$mcJ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcJ$sp$(this, len);
         }

         public DenseVector zerosLike$mcS$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcS$sp$(this, len);
         }

         public DenseVector zerosLike$mcV$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcV$sp$(this, len);
         }

         public boolean combine$mcZ$sp(final boolean x, final boolean y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcZ$sp$(this, x, y);
         }

         public byte combine$mcB$sp(final byte x, final byte y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcB$sp$(this, x, y);
         }

         public char combine$mcC$sp(final char x, final char y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcC$sp$(this, x, y);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcF$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcJ$sp$(this, x, y);
         }

         public short combine$mcS$sp(final short x, final short y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcS$sp$(this, x, y);
         }

         public void combine$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            VectorizedReduceUFunc.VectorizeHelper.combine$mcV$sp$(this, x, y);
         }

         public DenseVector zerosLike(final int len) {
            return this.zerosLike$mcI$sp(len);
         }

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public DenseVector zerosLike$mcI$sp(final int len) {
            DenseVector r = DenseVector$.MODULE$.zeros$mIc$sp(len, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            r.$colon$eq(BoxesRunTime.boxToInteger(Integer.MIN_VALUE), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpSet());
            return r;
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Math.max(x, y);
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$mcI$sp$$$outer() {
            return max$.MODULE$;
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$$$outer() {
            return max$.MODULE$;
         }
      };
   }

   public VectorizedReduceUFunc.VectorizeHelper helper_Float() {
      return new VectorizedReduceUFunc$VectorizeHelper$mcF$sp() {
         public DenseVector zerosLike$mcZ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcZ$sp$(this, len);
         }

         public DenseVector zerosLike$mcB$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcB$sp$(this, len);
         }

         public DenseVector zerosLike$mcC$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcC$sp$(this, len);
         }

         public DenseVector zerosLike$mcD$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcD$sp$(this, len);
         }

         public DenseVector zerosLike$mcI$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcI$sp$(this, len);
         }

         public DenseVector zerosLike$mcJ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcJ$sp$(this, len);
         }

         public DenseVector zerosLike$mcS$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcS$sp$(this, len);
         }

         public DenseVector zerosLike$mcV$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcV$sp$(this, len);
         }

         public boolean combine$mcZ$sp(final boolean x, final boolean y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcZ$sp$(this, x, y);
         }

         public byte combine$mcB$sp(final byte x, final byte y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcB$sp$(this, x, y);
         }

         public char combine$mcC$sp(final char x, final char y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcC$sp$(this, x, y);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcD$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcJ$sp$(this, x, y);
         }

         public short combine$mcS$sp(final short x, final short y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcS$sp$(this, x, y);
         }

         public void combine$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            VectorizedReduceUFunc.VectorizeHelper.combine$mcV$sp$(this, x, y);
         }

         public DenseVector zerosLike(final int len) {
            return this.zerosLike$mcF$sp(len);
         }

         public float combine(final float x, final float y) {
            return this.combine$mcF$sp(x, y);
         }

         public DenseVector zerosLike$mcF$sp(final int len) {
            DenseVector r = DenseVector$.MODULE$.zeros$mFc$sp(len, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            r.$colon$eq(BoxesRunTime.boxToFloat(Float.NEGATIVE_INFINITY), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Float_OpSet());
            return r;
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Math.max(x, y);
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$mcF$sp$$$outer() {
            return max$.MODULE$;
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$$$outer() {
            return max$.MODULE$;
         }
      };
   }

   public VectorizedReduceUFunc.VectorizeHelper helper_Long() {
      return new VectorizedReduceUFunc$VectorizeHelper$mcJ$sp() {
         public DenseVector zerosLike$mcZ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcZ$sp$(this, len);
         }

         public DenseVector zerosLike$mcB$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcB$sp$(this, len);
         }

         public DenseVector zerosLike$mcC$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcC$sp$(this, len);
         }

         public DenseVector zerosLike$mcD$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcD$sp$(this, len);
         }

         public DenseVector zerosLike$mcF$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcF$sp$(this, len);
         }

         public DenseVector zerosLike$mcI$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcI$sp$(this, len);
         }

         public DenseVector zerosLike$mcS$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcS$sp$(this, len);
         }

         public DenseVector zerosLike$mcV$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcV$sp$(this, len);
         }

         public boolean combine$mcZ$sp(final boolean x, final boolean y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcZ$sp$(this, x, y);
         }

         public byte combine$mcB$sp(final byte x, final byte y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcB$sp$(this, x, y);
         }

         public char combine$mcC$sp(final char x, final char y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcC$sp$(this, x, y);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcI$sp$(this, x, y);
         }

         public short combine$mcS$sp(final short x, final short y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcS$sp$(this, x, y);
         }

         public void combine$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            VectorizedReduceUFunc.VectorizeHelper.combine$mcV$sp$(this, x, y);
         }

         public DenseVector zerosLike(final int len) {
            return this.zerosLike$mcJ$sp(len);
         }

         public long combine(final long x, final long y) {
            return this.combine$mcJ$sp(x, y);
         }

         public DenseVector zerosLike$mcJ$sp(final int len) {
            DenseVector r = DenseVector$.MODULE$.zeros$mJc$sp(len, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            r.$colon$eq(BoxesRunTime.boxToLong(Long.MIN_VALUE), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Long_OpSet());
            return r;
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Math.max(x, y);
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$mcJ$sp$$$outer() {
            return max$.MODULE$;
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$$$outer() {
            return max$.MODULE$;
         }
      };
   }

   public VectorizedReduceUFunc.VectorizeHelper helper_Double() {
      return new VectorizedReduceUFunc$VectorizeHelper$mcD$sp() {
         public DenseVector zerosLike$mcZ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcZ$sp$(this, len);
         }

         public DenseVector zerosLike$mcB$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcB$sp$(this, len);
         }

         public DenseVector zerosLike$mcC$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcC$sp$(this, len);
         }

         public DenseVector zerosLike$mcF$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcF$sp$(this, len);
         }

         public DenseVector zerosLike$mcI$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcI$sp$(this, len);
         }

         public DenseVector zerosLike$mcJ$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcJ$sp$(this, len);
         }

         public DenseVector zerosLike$mcS$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcS$sp$(this, len);
         }

         public DenseVector zerosLike$mcV$sp(final int len) {
            return VectorizedReduceUFunc.VectorizeHelper.zerosLike$mcV$sp$(this, len);
         }

         public boolean combine$mcZ$sp(final boolean x, final boolean y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcZ$sp$(this, x, y);
         }

         public byte combine$mcB$sp(final byte x, final byte y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcB$sp$(this, x, y);
         }

         public char combine$mcC$sp(final char x, final char y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcC$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcJ$sp$(this, x, y);
         }

         public short combine$mcS$sp(final short x, final short y) {
            return VectorizedReduceUFunc.VectorizeHelper.combine$mcS$sp$(this, x, y);
         }

         public void combine$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            VectorizedReduceUFunc.VectorizeHelper.combine$mcV$sp$(this, x, y);
         }

         public DenseVector zerosLike(final int len) {
            return this.zerosLike$mcD$sp(len);
         }

         public double combine(final double x, final double y) {
            return this.combine$mcD$sp(x, y);
         }

         public DenseVector zerosLike$mcD$sp(final int len) {
            DenseVector r = DenseVector$.MODULE$.zeros$mDc$sp(len, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            r.$colon$eq(BoxesRunTime.boxToDouble(Double.NEGATIVE_INFINITY), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            return r;
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Math.max(x, y);
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$mcD$sp$$$outer() {
            return max$.MODULE$;
         }

         // $FF: synthetic method
         public VectorizedReduceUFunc breeze$linalg$VectorizedReduceUFunc$VectorizeHelper$$$outer() {
            return max$.MODULE$;
         }
      };
   }

   public double array(final double[] arr, final int length) {
      double accum = Double.NEGATIVE_INFINITY;

      for(int i = 0; i < length; ++i) {
         accum = .MODULE$.max(arr[i], accum);
      }

      return accum;
   }

   private max$() {
   }
}
