package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import scala.Function1;
import scala.collection.Iterator;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class argmax$ implements UFunc {
   public static final argmax$ MODULE$ = new argmax$();

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

   public UFunc.UImpl reduce_Int(final CanTraverseKeyValuePairs iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseKeyValuePairs iter$1;

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

         public Object apply(final Object v) {
            class SumVisitor$1 implements CanTraverseKeyValuePairs.KeyValuePairsVisitor {
               private int max;
               private Object amax;
               private boolean visitedOne;

               public void visit$mcZI$sp(final int k, final boolean a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcZI$sp$(this, k, a);
               }

               public void visit$mcBI$sp(final int k, final byte a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcBI$sp$(this, k, a);
               }

               public void visit$mcCI$sp(final int k, final char a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcCI$sp$(this, k, a);
               }

               public void visit$mcDI$sp(final int k, final double a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcDI$sp$(this, k, a);
               }

               public void visit$mcFI$sp(final int k, final float a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcFI$sp$(this, k, a);
               }

               public void visit$mcII$sp(final int k, final int a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcII$sp$(this, k, a);
               }

               public void visit$mcJI$sp(final int k, final long a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcJI$sp$(this, k, a);
               }

               public void visit$mcSI$sp(final int k, final short a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcSI$sp$(this, k, a);
               }

               public void visit$mcVI$sp(final int k, final BoxedUnit a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcVI$sp$(this, k, a);
               }

               public void visitArray(final Function1 indices, final Object arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final Iterator zeroKeys, final boolean zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcZ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final Iterator zeroKeys, final byte zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcB$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final Iterator zeroKeys, final char zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcC$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcD$sp(final int numZero, final Iterator zeroKeys, final double zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcD$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcF$sp(final int numZero, final Iterator zeroKeys, final float zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcF$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcJ$sp(final int numZero, final Iterator zeroKeys, final long zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcJ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final Iterator zeroKeys, final short zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcS$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcV$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public int max() {
                  return this.max;
               }

               public void max_$eq(final int x$1) {
                  this.max = x$1;
               }

               public Object amax() {
                  return this.amax;
               }

               public void amax_$eq(final Object x$1) {
                  this.amax = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final Object k, final int a) {
                  if (a > this.max() || !this.visitedOne()) {
                     this.max_$eq(a);
                     this.amax_$eq(k);
                  }

                  this.visitedOne_$eq(true);
               }

               public void zeros(final int numZero, final Iterator zeroKeys, final int zeroValue) {
                  this.zeros$mcI$sp(numZero, zeroKeys, zeroValue);
               }

               public void visitArray(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
                  int i = 0;

                  for(int off = offset; i < length; off += stride) {
                     int a = arr[off];
                     if (a > this.max() || !this.visitedOne()) {
                        this.max_$eq(a);
                        this.amax_$eq(indices.apply(BoxesRunTime.boxToInteger(off)));
                     }

                     this.visitedOne_$eq(true);
                     ++i;
                  }

               }

               public void zeros$mcI$sp(final int numZero, final Iterator zeroKeys, final int zeroValue) {
                  if (numZero != 0) {
                     if (zeroValue > this.max() || !this.visitedOne()) {
                        this.max_$eq(zeroValue);
                        this.amax_$eq(zeroKeys.next());
                     }

                     this.visitedOne_$eq(true);
                  }

               }

               public SumVisitor$1() {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.$init$(this);
                  this.max = Integer.MIN_VALUE;
                  this.visitedOne = false;
               }
            }

            SumVisitor$1 visit = new SumVisitor$1();
            this.iter$1.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.amax();
            }
         }

         public {
            this.iter$1 = iter$1;
         }
      };
   }

   public UFunc.UImpl reduce_Double(final CanTraverseKeyValuePairs iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseKeyValuePairs iter$2;

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

         public Object apply(final Object v) {
            class SumVisitor$2 implements CanTraverseKeyValuePairs.KeyValuePairsVisitor {
               private double max;
               private Object amax;
               private boolean visitedOne;

               public void visit$mcZI$sp(final int k, final boolean a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcZI$sp$(this, k, a);
               }

               public void visit$mcBI$sp(final int k, final byte a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcBI$sp$(this, k, a);
               }

               public void visit$mcCI$sp(final int k, final char a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcCI$sp$(this, k, a);
               }

               public void visit$mcDI$sp(final int k, final double a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcDI$sp$(this, k, a);
               }

               public void visit$mcFI$sp(final int k, final float a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcFI$sp$(this, k, a);
               }

               public void visit$mcII$sp(final int k, final int a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcII$sp$(this, k, a);
               }

               public void visit$mcJI$sp(final int k, final long a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcJI$sp$(this, k, a);
               }

               public void visit$mcSI$sp(final int k, final short a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcSI$sp$(this, k, a);
               }

               public void visit$mcVI$sp(final int k, final BoxedUnit a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcVI$sp$(this, k, a);
               }

               public void visitArray(final Function1 indices, final Object arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final Iterator zeroKeys, final boolean zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcZ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final Iterator zeroKeys, final byte zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcB$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final Iterator zeroKeys, final char zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcC$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcF$sp(final int numZero, final Iterator zeroKeys, final float zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcF$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcI$sp(final int numZero, final Iterator zeroKeys, final int zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcI$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcJ$sp(final int numZero, final Iterator zeroKeys, final long zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcJ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final Iterator zeroKeys, final short zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcS$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcV$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public double max() {
                  return this.max;
               }

               public void max_$eq(final double x$1) {
                  this.max = x$1;
               }

               public Object amax() {
                  return this.amax;
               }

               public void amax_$eq(final Object x$1) {
                  this.amax = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final Object k, final double a) {
                  if (a > this.max() || !this.visitedOne()) {
                     this.max_$eq(a);
                     this.amax_$eq(k);
                  }

                  this.visitedOne_$eq(true);
               }

               public void zeros(final int numZero, final Iterator zeroKeys, final double zeroValue) {
                  this.zeros$mcD$sp(numZero, zeroKeys, zeroValue);
               }

               public void visitArray(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
                  int i = 0;

                  for(int off = offset; i < length; off += stride) {
                     double a = arr[off];
                     if (a > this.max() || !this.visitedOne()) {
                        this.max_$eq(a);
                        this.amax_$eq(indices.apply(BoxesRunTime.boxToInteger(off)));
                     }

                     this.visitedOne_$eq(true);
                     ++i;
                  }

               }

               public void zeros$mcD$sp(final int numZero, final Iterator zeroKeys, final double zeroValue) {
                  if (numZero != 0) {
                     if (zeroValue > this.max() || !this.visitedOne()) {
                        this.max_$eq(zeroValue);
                        this.amax_$eq(zeroKeys.next());
                     }

                     this.visitedOne_$eq(true);
                  }

               }

               public SumVisitor$2() {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.$init$(this);
                  this.max = Double.NEGATIVE_INFINITY;
                  this.visitedOne = false;
               }
            }

            SumVisitor$2 visit = new SumVisitor$2();
            this.iter$2.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.amax();
            }
         }

         public {
            this.iter$2 = iter$2;
         }
      };
   }

   public UFunc.UImpl reduce_Float(final CanTraverseKeyValuePairs iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseKeyValuePairs iter$3;

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

         public Object apply(final Object v) {
            class SumVisitor$3 implements CanTraverseKeyValuePairs.KeyValuePairsVisitor {
               private float max;
               private Object amax;
               private boolean visitedOne;

               public void visit$mcZI$sp(final int k, final boolean a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcZI$sp$(this, k, a);
               }

               public void visit$mcBI$sp(final int k, final byte a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcBI$sp$(this, k, a);
               }

               public void visit$mcCI$sp(final int k, final char a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcCI$sp$(this, k, a);
               }

               public void visit$mcDI$sp(final int k, final double a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcDI$sp$(this, k, a);
               }

               public void visit$mcFI$sp(final int k, final float a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcFI$sp$(this, k, a);
               }

               public void visit$mcII$sp(final int k, final int a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcII$sp$(this, k, a);
               }

               public void visit$mcJI$sp(final int k, final long a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcJI$sp$(this, k, a);
               }

               public void visit$mcSI$sp(final int k, final short a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcSI$sp$(this, k, a);
               }

               public void visit$mcVI$sp(final int k, final BoxedUnit a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcVI$sp$(this, k, a);
               }

               public void visitArray(final Function1 indices, final Object arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final Iterator zeroKeys, final boolean zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcZ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final Iterator zeroKeys, final byte zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcB$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final Iterator zeroKeys, final char zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcC$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcD$sp(final int numZero, final Iterator zeroKeys, final double zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcD$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcI$sp(final int numZero, final Iterator zeroKeys, final int zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcI$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcJ$sp(final int numZero, final Iterator zeroKeys, final long zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcJ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final Iterator zeroKeys, final short zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcS$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcV$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public float max() {
                  return this.max;
               }

               public void max_$eq(final float x$1) {
                  this.max = x$1;
               }

               public Object amax() {
                  return this.amax;
               }

               public void amax_$eq(final Object x$1) {
                  this.amax = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final Object k, final float a) {
                  if (a > this.max() || !this.visitedOne()) {
                     this.max_$eq(a);
                     this.amax_$eq(k);
                  }

                  this.visitedOne_$eq(true);
               }

               public void zeros(final int numZero, final Iterator zeroKeys, final float zeroValue) {
                  this.zeros$mcF$sp(numZero, zeroKeys, zeroValue);
               }

               public void visitArray(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
                  int i = 0;

                  for(int off = offset; i < length; off += stride) {
                     float a = arr[off];
                     if (a > this.max() || !this.visitedOne()) {
                        this.max_$eq(a);
                        this.amax_$eq(indices.apply(BoxesRunTime.boxToInteger(off)));
                     }

                     this.visitedOne_$eq(true);
                     ++i;
                  }

               }

               public void zeros$mcF$sp(final int numZero, final Iterator zeroKeys, final float zeroValue) {
                  if (numZero != 0) {
                     if (zeroValue > this.max() || !this.visitedOne()) {
                        this.max_$eq(zeroValue);
                        this.amax_$eq(zeroKeys.next());
                     }

                     this.visitedOne_$eq(true);
                  }

               }

               public SumVisitor$3() {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.$init$(this);
                  this.max = Float.NEGATIVE_INFINITY;
                  this.visitedOne = false;
               }
            }

            SumVisitor$3 visit = new SumVisitor$3();
            this.iter$3.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.amax();
            }
         }

         public {
            this.iter$3 = iter$3;
         }
      };
   }

   public UFunc.UImpl reduce_Long(final CanTraverseKeyValuePairs iter) {
      return new UFunc.UImpl(iter) {
         private final CanTraverseKeyValuePairs iter$4;

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

         public Object apply(final Object v) {
            class SumVisitor$4 implements CanTraverseKeyValuePairs.KeyValuePairsVisitor {
               private long max;
               private Object amax;
               private boolean visitedOne;

               public void visit$mcZI$sp(final int k, final boolean a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcZI$sp$(this, k, a);
               }

               public void visit$mcBI$sp(final int k, final byte a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcBI$sp$(this, k, a);
               }

               public void visit$mcCI$sp(final int k, final char a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcCI$sp$(this, k, a);
               }

               public void visit$mcDI$sp(final int k, final double a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcDI$sp$(this, k, a);
               }

               public void visit$mcFI$sp(final int k, final float a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcFI$sp$(this, k, a);
               }

               public void visit$mcII$sp(final int k, final int a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcII$sp$(this, k, a);
               }

               public void visit$mcJI$sp(final int k, final long a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcJI$sp$(this, k, a);
               }

               public void visit$mcSI$sp(final int k, final short a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcSI$sp$(this, k, a);
               }

               public void visit$mcVI$sp(final int k, final BoxedUnit a) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visit$mcVI$sp$(this, k, a);
               }

               public void visitArray(final Function1 indices, final Object arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr);
               }

               public void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcZI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcBI$sp(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcBI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcCI$sp(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcCI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcDI$sp(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcDI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcFI$sp(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcFI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcII$sp(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcII$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcJI$sp(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcJI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcSI$sp(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcSI$sp$(this, indices, arr, offset, length, stride);
               }

               public void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$mcVI$sp$(this, indices, arr, offset, length, stride);
               }

               public void zeros$mcZ$sp(final int numZero, final Iterator zeroKeys, final boolean zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcZ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcB$sp(final int numZero, final Iterator zeroKeys, final byte zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcB$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcC$sp(final int numZero, final Iterator zeroKeys, final char zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcC$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcD$sp(final int numZero, final Iterator zeroKeys, final double zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcD$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcF$sp(final int numZero, final Iterator zeroKeys, final float zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcF$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcI$sp(final int numZero, final Iterator zeroKeys, final int zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcI$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final Iterator zeroKeys, final short zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcS$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcV$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public long max() {
                  return this.max;
               }

               public void max_$eq(final long x$1) {
                  this.max = x$1;
               }

               public Object amax() {
                  return this.amax;
               }

               public void amax_$eq(final Object x$1) {
                  this.amax = x$1;
               }

               public boolean visitedOne() {
                  return this.visitedOne;
               }

               public void visitedOne_$eq(final boolean x$1) {
                  this.visitedOne = x$1;
               }

               public void visit(final Object k, final long a) {
                  if (a > this.max() || !this.visitedOne()) {
                     this.max_$eq(a);
                     this.amax_$eq(k);
                  }

                  this.visitedOne_$eq(true);
               }

               public void zeros(final int numZero, final Iterator zeroKeys, final long zeroValue) {
                  this.zeros$mcJ$sp(numZero, zeroKeys, zeroValue);
               }

               public void visitArray(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
                  int i = 0;

                  for(int off = offset; i < length; off += stride) {
                     long a = arr[off];
                     if (a > this.max() || !this.visitedOne()) {
                        this.max_$eq(a);
                        this.amax_$eq(indices.apply(BoxesRunTime.boxToInteger(off)));
                     }

                     this.visitedOne_$eq(true);
                     ++i;
                  }

               }

               public void zeros$mcJ$sp(final int numZero, final Iterator zeroKeys, final long zeroValue) {
                  if (numZero != 0) {
                     if (zeroValue > this.max() || !this.visitedOne()) {
                        this.max_$eq(zeroValue);
                        this.amax_$eq(zeroKeys.next());
                     }

                     this.visitedOne_$eq(true);
                  }

               }

               public SumVisitor$4() {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.$init$(this);
                  this.max = Long.MIN_VALUE;
                  this.visitedOne = false;
               }
            }

            SumVisitor$4 visit = new SumVisitor$4();
            this.iter$4.traverse(v, visit);
            if (!visit.visitedOne()) {
               throw new IllegalArgumentException((new StringBuilder(14)).append("No values in ").append(v).append("!").toString());
            } else {
               return visit.amax();
            }
         }

         public {
            this.iter$4 = iter$4;
         }
      };
   }

   private argmax$() {
   }
}
