package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class where$ implements UFunc {
   public static final where$ MODULE$ = new where$();

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

   public UFunc.UImpl whereFromTraverseKeyValuePairs(final CanTraverseKeyValuePairs trav, final Semiring semi) {
      return new UFunc.UImpl(trav, semi) {
         private final CanTraverseKeyValuePairs trav$1;
         public final Semiring semi$1;

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

         public IndexedSeq apply(final Object v) {
            ArrayBuffer result = new ArrayBuffer();
            this.trav$1.traverse(v, new CanTraverseKeyValuePairs.KeyValuePairsVisitor(result) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final ArrayBuffer result$1;

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

               public void visitArray(final Function1 indices, final Object arr, final int offset, final int length, final int stride) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.visitArray$(this, indices, arr, offset, length, stride);
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

               public void zeros$mcJ$sp(final int numZero, final Iterator zeroKeys, final long zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcJ$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcS$sp(final int numZero, final Iterator zeroKeys, final short zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcS$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void zeros$mcV$sp(final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
                  CanTraverseKeyValuePairs.KeyValuePairsVisitor.zeros$mcV$sp$(this, numZero, zeroKeys, zeroValue);
               }

               public void visit(final Object k, final Object a) {
                  if (!BoxesRunTime.equals(a, this.$outer.semi$1.zero())) {
                     this.result$1.$plus$eq(k);
                  }

               }

               public void zeros(final int numZero, final Iterator zeroKeys, final Object zeroValue) {
                  if (!BoxesRunTime.equals(zeroValue, this.$outer.semi$1.zero())) {
                     this.result$1.$plus$plus$eq(zeroKeys);
                  }

               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.result$1 = result$1;
                     CanTraverseKeyValuePairs.KeyValuePairsVisitor.$init$(this);
                  }
               }
            });
            return result.toIndexedSeq();
         }

         public {
            this.trav$1 = trav$1;
            this.semi$1 = semi$1;
         }
      };
   }

   public UFunc.UImpl3 where3ArgFromTraverseKeyValuePairs(final .less.colon.less ev, final CanMapKeyValuePairs trav, final Semiring semi) {
      return new UFunc.UImpl3(trav, semi, ev) {
         private final CanMapKeyValuePairs trav$2;
         private final Semiring semi$2;
         private final .less.colon.less ev$1;

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

         public Object apply(final Object from, final Object v2, final Object v3) {
            return this.trav$2.map(from, (k, v) -> !BoxesRunTime.equals(v, this.semi$2.zero()) ? ((QuasiTensor)this.ev$1.apply(v2)).apply(k) : ((QuasiTensor)this.ev$1.apply(v3)).apply(k));
         }

         public {
            this.trav$2 = trav$2;
            this.semi$2 = semi$2;
            this.ev$1 = ev$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private where$() {
   }
}
