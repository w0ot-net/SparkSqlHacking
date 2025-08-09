package breeze.util;

import breeze.generic.UFunc;
import breeze.generic.UFunc$InPlaceImpl2$mcI$sp;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.IndexedSeq;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class quickSelect$ implements UFunc {
   public static final quickSelect$ MODULE$ = new quickSelect$();

   static {
      UFunc.$init$(MODULE$);
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

   public UFunc.UImpl2 implFromQSInPlace(final UFunc.InPlaceImpl2 op) {
      return new UFunc.UImpl2(op) {
         private final UFunc.InPlaceImpl2 op$1;

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

         public Object apply(final Object a, final int position) {
            Object quickselected = .MODULE$.array_clone(a);
            this.op$1.apply$mcI$sp(quickselected, position);
            return .MODULE$.array_apply(quickselected, position);
         }

         public {
            this.op$1 = op$1;
         }
      };
   }

   public UFunc.InPlaceImpl2 inPlaceImpl2_Int() {
      return new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final int[] x, final int position) {
            this.apply$mcI$sp(x, position);
         }

         public void apply$mcI$sp(final int[] x, final int position) {
            implQuickSelectSort$1(x, position, x);
         }

         private static final void implQuickSelectSort$1(final int[] x, final int position, final int[] x$11) {
            int left = 0;
            int right = x.length - 1;
            scala.Predef..MODULE$.require(position >= left && position <= right, () -> (new StringBuilder(52)).append("Invalid position specification: ").append(position).append(" with array length: ").append(x.length).toString());

            while(right > left) {
               int pvt = med3$1(left, right, (int)(((long)left + (long)right) / 2L), x$11);
               Tuple2 var8 = partition3Ways$1(x, left, right, pvt, x$11);
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               int lt = var8._1$mcI$sp();
               int gt = var8._2$mcI$sp();
               Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(lt, gt);
               int lt = ((Tuple2)var3)._1$mcI$sp();
               int gt = ((Tuple2)var3)._2$mcI$sp();
               if (lt <= position && position <= gt) {
                  left = right;
               } else if (position < lt) {
                  right = lt - 1;
               } else if (position > gt) {
                  left = gt + 1;
               }
            }

         }

         private static final int med3$1(final int p1, final int p2, final int p3, final int[] x$11) {
            return x$11[p1] < x$11[p2] ? (x$11[p2] < x$11[p3] ? p2 : (x$11[p1] < x$11[p3] ? p3 : p1)) : (x$11[p2] > x$11[p3] ? p2 : (x$11[p1] > x$11[p3] ? p3 : p1));
         }

         private static final Tuple2 partition3Ways$1(final int[] x, final int left, final int right, final int pivot, final int[] x$11) {
            int pivotVal = x[pivot];
            swap$1(pivot, left, x$11);
            int i = left;
            int lt = left;
            int gt = right;

            while(i <= gt) {
               if (x[i] < pivotVal) {
                  swap$1(lt, i, x$11);
                  ++lt;
                  ++i;
               } else if (x[i] > pivotVal) {
                  swap$1(gt, i, x$11);
                  --gt;
               } else {
                  if (x[i] != pivotVal) {
                     scala.Predef..MODULE$.assert(x[i] != x[i]);
                     throw new IllegalArgumentException("Nan element detected");
                  }

                  ++i;
               }
            }

            return new Tuple2.mcII.sp(lt, gt);
         }

         private static final void swap$1(final int a, final int b, final int[] x$11) {
            int t = x$11[a];
            x$11[a] = x$11[b];
            x$11[b] = t;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public UFunc.InPlaceImpl2 inPlaceImpl2_Long() {
      return new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final long[] x, final int position) {
            this.apply$mcI$sp(x, position);
         }

         public void apply$mcI$sp(final long[] x, final int position) {
            implQuickSelectSort$2(x, position, x);
         }

         private static final void implQuickSelectSort$2(final long[] x, final int position, final long[] x$13) {
            int left = 0;
            int right = x.length - 1;
            scala.Predef..MODULE$.require(position >= left && position <= right, () -> (new StringBuilder(52)).append("Invalid position specification: ").append(position).append(" with array length: ").append(x.length).toString());

            while(right > left) {
               int pvt = med3$2(left, right, (int)(((long)left + (long)right) / 2L), x$13);
               Tuple2 var8 = partition3Ways$2(x, left, right, pvt, x$13);
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               int lt = var8._1$mcI$sp();
               int gt = var8._2$mcI$sp();
               Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(lt, gt);
               int lt = ((Tuple2)var3)._1$mcI$sp();
               int gt = ((Tuple2)var3)._2$mcI$sp();
               if (lt <= position && position <= gt) {
                  left = right;
               } else if (position < lt) {
                  right = lt - 1;
               } else if (position > gt) {
                  left = gt + 1;
               }
            }

         }

         private static final int med3$2(final int p1, final int p2, final int p3, final long[] x$13) {
            return x$13[p1] < x$13[p2] ? (x$13[p2] < x$13[p3] ? p2 : (x$13[p1] < x$13[p3] ? p3 : p1)) : (x$13[p2] > x$13[p3] ? p2 : (x$13[p1] > x$13[p3] ? p3 : p1));
         }

         private static final Tuple2 partition3Ways$2(final long[] x, final int left, final int right, final int pivot, final long[] x$13) {
            long pivotVal = x[pivot];
            swap$2(pivot, left, x$13);
            int i = left;
            int lt = left;
            int gt = right;

            while(i <= gt) {
               if (x[i] < pivotVal) {
                  swap$2(lt, i, x$13);
                  ++lt;
                  ++i;
               } else if (x[i] > pivotVal) {
                  swap$2(gt, i, x$13);
                  --gt;
               } else {
                  if (x[i] != pivotVal) {
                     scala.Predef..MODULE$.assert(x[i] != x[i]);
                     throw new IllegalArgumentException("Nan element detected");
                  }

                  ++i;
               }
            }

            return new Tuple2.mcII.sp(lt, gt);
         }

         private static final void swap$2(final int a, final int b, final long[] x$13) {
            long t = x$13[a];
            x$13[a] = x$13[b];
            x$13[b] = t;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public UFunc.InPlaceImpl2 inPlaceImpl2_Double() {
      return new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final double[] x, final int position) {
            this.apply$mcI$sp(x, position);
         }

         public void apply$mcI$sp(final double[] x, final int position) {
            implQuickSelectSort$3(x, position, x);
         }

         private static final void implQuickSelectSort$3(final double[] x, final int position, final double[] x$15) {
            int left = 0;
            int right = x.length - 1;
            scala.Predef..MODULE$.require(position >= left && position <= right, () -> (new StringBuilder(52)).append("Invalid position specification: ").append(position).append(" with array length: ").append(x.length).toString());

            while(right > left) {
               int pvt = med3$3(left, right, (int)(((long)left + (long)right) / 2L), x$15);
               Tuple2 var8 = partition3Ways$3(x, left, right, pvt, x$15);
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               int lt = var8._1$mcI$sp();
               int gt = var8._2$mcI$sp();
               Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(lt, gt);
               int lt = ((Tuple2)var3)._1$mcI$sp();
               int gt = ((Tuple2)var3)._2$mcI$sp();
               if (lt <= position && position <= gt) {
                  left = right;
               } else if (position < lt) {
                  right = lt - 1;
               } else if (position > gt) {
                  left = gt + 1;
               }
            }

         }

         private static final int med3$3(final int p1, final int p2, final int p3, final double[] x$15) {
            return x$15[p1] < x$15[p2] ? (x$15[p2] < x$15[p3] ? p2 : (x$15[p1] < x$15[p3] ? p3 : p1)) : (x$15[p2] > x$15[p3] ? p2 : (x$15[p1] > x$15[p3] ? p3 : p1));
         }

         private static final Tuple2 partition3Ways$3(final double[] x, final int left, final int right, final int pivot, final double[] x$15) {
            double pivotVal = x[pivot];
            swap$3(pivot, left, x$15);
            int i = left;
            int lt = left;
            int gt = right;

            while(i <= gt) {
               if (x[i] < pivotVal) {
                  swap$3(lt, i, x$15);
                  ++lt;
                  ++i;
               } else if (x[i] > pivotVal) {
                  swap$3(gt, i, x$15);
                  --gt;
               } else {
                  if (x[i] != pivotVal) {
                     scala.Predef..MODULE$.assert(x[i] != x[i]);
                     throw new IllegalArgumentException("Nan element detected");
                  }

                  ++i;
               }
            }

            return new Tuple2.mcII.sp(lt, gt);
         }

         private static final void swap$3(final int a, final int b, final double[] x$15) {
            double t = x$15[a];
            x$15[a] = x$15[b];
            x$15[b] = t;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public UFunc.InPlaceImpl2 inPlaceImpl2_Float() {
      return new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final float[] x, final int position) {
            this.apply$mcI$sp(x, position);
         }

         public void apply$mcI$sp(final float[] x, final int position) {
            implQuickSelectSort$4(x, position, x);
         }

         private static final void implQuickSelectSort$4(final float[] x, final int position, final float[] x$17) {
            int left = 0;
            int right = x.length - 1;
            scala.Predef..MODULE$.require(position >= left && position <= right, () -> (new StringBuilder(52)).append("Invalid position specification: ").append(position).append(" with array length: ").append(x.length).toString());

            while(right > left) {
               int pvt = med3$4(left, right, (int)(((long)left + (long)right) / 2L), x$17);
               Tuple2 var8 = partition3Ways$4(x, left, right, pvt, x$17);
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               int lt = var8._1$mcI$sp();
               int gt = var8._2$mcI$sp();
               Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(lt, gt);
               int lt = ((Tuple2)var3)._1$mcI$sp();
               int gt = ((Tuple2)var3)._2$mcI$sp();
               if (lt <= position && position <= gt) {
                  left = right;
               } else if (position < lt) {
                  right = lt - 1;
               } else if (position > gt) {
                  left = gt + 1;
               }
            }

         }

         private static final int med3$4(final int p1, final int p2, final int p3, final float[] x$17) {
            return x$17[p1] < x$17[p2] ? (x$17[p2] < x$17[p3] ? p2 : (x$17[p1] < x$17[p3] ? p3 : p1)) : (x$17[p2] > x$17[p3] ? p2 : (x$17[p1] > x$17[p3] ? p3 : p1));
         }

         private static final Tuple2 partition3Ways$4(final float[] x, final int left, final int right, final int pivot, final float[] x$17) {
            float pivotVal = x[pivot];
            swap$4(pivot, left, x$17);
            int i = left;
            int lt = left;
            int gt = right;

            while(i <= gt) {
               if (x[i] < pivotVal) {
                  swap$4(lt, i, x$17);
                  ++lt;
                  ++i;
               } else if (x[i] > pivotVal) {
                  swap$4(gt, i, x$17);
                  --gt;
               } else {
                  if (x[i] != pivotVal) {
                     scala.Predef..MODULE$.assert(x[i] != x[i]);
                     throw new IllegalArgumentException("Nan element detected");
                  }

                  ++i;
               }
            }

            return new Tuple2.mcII.sp(lt, gt);
         }

         private static final void swap$4(final int a, final int b, final float[] x$17) {
            float t = x$17[a];
            x$17[a] = x$17[b];
            x$17[b] = t;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public UFunc.UImpl2 implFromQSInPlaceColl(final scala..less.colon.less view, final Ordering ordering) {
      return new UFunc.UImpl2(view, ordering) {
         private final scala..less.colon.less view$1;
         private final Ordering ordering$1;

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

         public Object apply(final Object a, final int position) {
            ArrayBuffer copy = (ArrayBuffer)((IterableOnceOps)this.view$1.apply(a)).to(scala.collection.IterableFactory..MODULE$.toFactory(scala.collection.mutable.ArrayBuffer..MODULE$));
            quickSelect$.MODULE$.inPlace(copy, BoxesRunTime.boxToInteger(position), quickSelect$.MODULE$.implFromOrdering(scala..less.colon.less..MODULE$.refl(), this.ordering$1));
            return copy.apply(position);
         }

         public {
            this.view$1 = view$1;
            this.ordering$1 = ordering$1;
         }
      };
   }

   public UFunc.InPlaceImpl2 implFromOrdering(final scala..less.colon.less view, final Ordering ordering) {
      return new UFunc$InPlaceImpl2$mcI$sp(view, ordering) {
         private final scala..less.colon.less view$2;
         private final Ordering ordering$2;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final Object rawx, final int position) {
            this.apply$mcI$sp(rawx, position);
         }

         public void apply$mcI$sp(final Object rawx, final int position) {
            IndexedSeq coll = (IndexedSeq)this.view$2.apply(rawx);
            int pivotIndex = -1;
            this.implQuickSelectSort$5(coll, position, coll);
         }

         private final int med3$5(final int p1, final int p2, final int p3, final IndexedSeq x$18) {
            return this.ordering$2.mkOrderingOps(x$18.apply(p1)).$less(x$18.apply(p2)) ? (this.ordering$2.mkOrderingOps(x$18.apply(p2)).$less(x$18.apply(p3)) ? p2 : (this.ordering$2.mkOrderingOps(x$18.apply(p1)).$less(x$18.apply(p3)) ? p3 : p1)) : (this.ordering$2.mkOrderingOps(x$18.apply(p2)).$greater(x$18.apply(p3)) ? p2 : (this.ordering$2.mkOrderingOps(x$18.apply(p1)).$greater(x$18.apply(p3)) ? p3 : p1));
         }

         private final void implQuickSelectSort$5(final IndexedSeq x, final int position, final IndexedSeq coll$1) {
            int left = 0;
            int right = x.length() - 1;
            scala.Predef..MODULE$.require(position >= left && position <= right, () -> (new StringBuilder(51)).append("Invalid position specification: ").append(position).append(" with coll length: ").append(x.length()).toString());

            while(right > left) {
               int pvt = this.med3$5(left, right, (int)(((long)left + (long)right) / 2L), x);
               Tuple2 var9 = this.partition3Ways$5(x, left, right, pvt, coll$1);
               if (var9 == null) {
                  throw new MatchError(var9);
               }

               int lt = var9._1$mcI$sp();
               int gt = var9._2$mcI$sp();
               Tuple2.mcII.sp var4 = new Tuple2.mcII.sp(lt, gt);
               int lt = ((Tuple2)var4)._1$mcI$sp();
               int gtx = ((Tuple2)var4)._2$mcI$sp();
               if (lt <= position && position <= gtx) {
                  left = right;
               } else if (position < lt) {
                  right = lt - 1;
               } else if (position > gtx) {
                  left = gtx + 1;
               }
            }

         }

         private final Tuple2 partition3Ways$5(final IndexedSeq x, final int left, final int right, final int pivot, final IndexedSeq coll$1) {
            Object pivotVal = x.apply(pivot);
            swap$5(pivot, left, coll$1);
            int i = left;
            int lt = left;
            int gt = right;

            while(i <= gt) {
               if (this.ordering$2.lt(x.apply(i), pivotVal)) {
                  swap$5(lt, i, coll$1);
                  ++lt;
                  ++i;
               } else if (this.ordering$2.gt(x.apply(i), pivotVal)) {
                  swap$5(gt, i, coll$1);
                  --gt;
               } else {
                  if (!this.ordering$2.equiv(x.apply(i), pivotVal)) {
                     scala.Predef..MODULE$.assert(!BoxesRunTime.equals(x.apply(i), x.apply(i)));
                     throw new IllegalArgumentException("Nan element detected");
                  }

                  ++i;
               }
            }

            return new Tuple2.mcII.sp(lt, gt);
         }

         private static final void swap$5(final int a, final int b, final IndexedSeq coll$1) {
            Object t = coll$1.apply(a);
            coll$1.update(a, coll$1.apply(b));
            coll$1.update(b, t);
         }

         public {
            this.view$2 = view$2;
            this.ordering$2 = ordering$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private quickSelect$() {
   }
}
