package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcF$sp;
import breeze.math.Complex;
import breeze.math.Complex$;
import scala.Tuple2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

public final class accumulateAndCount$ implements UFunc {
   public static final accumulateAndCount$ MODULE$ = new accumulateAndCount$();

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

   public UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
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

         public Tuple2 apply(final Object v) {
            LazyRef visit$module = new LazyRef();
            this.iter$1.traverse(v, this.visit$2(visit$module));
            return new Tuple2.mcDI.sp(this.visit$2(visit$module).sum(), this.visit$2(visit$module).n());
         }

         // $FF: synthetic method
         private static final visit$1$ visit$lzycompute$1(final LazyRef visit$module$1) {
            synchronized(visit$module$1){}

            visit$1$ var2;
            try {
               class visit$1$ implements CanTraverseValues$ValuesVisitor$mcD$sp {
                  private double sum;
                  private int n;

                  public void visitArray(final double[] arr) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr);
                  }

                  public void visitArray$mcD$sp(final double[] arr) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr);
                  }

                  public void visitArray(final double[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr, offset, length, stride);
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

                  public double sum() {
                     return this.sum;
                  }

                  public void sum_$eq(final double x$1) {
                     this.sum = x$1;
                  }

                  public int n() {
                     return this.n;
                  }

                  public void n_$eq(final int x$1) {
                     this.n = x$1;
                  }

                  public void visit(final double a) {
                     this.visit$mcD$sp(a);
                  }

                  public void zeros(final int numZero, final double zeroValue) {
                     this.zeros$mcD$sp(numZero, zeroValue);
                  }

                  public void visit$mcD$sp(final double a) {
                     this.sum_$eq(this.sum() + a);
                     this.n_$eq(this.n() + 1);
                  }

                  public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                     this.sum_$eq(this.sum() + zeroValue * (double)numZero);
                     this.n_$eq(this.n() + numZero);
                  }

                  public visit$1$() {
                     CanTraverseValues.ValuesVisitor.$init$(this);
                     this.sum = (double)0.0F;
                     this.n = 0;
                  }
               }

               var2 = visit$module$1.initialized() ? (visit$1$)visit$module$1.value() : (visit$1$)visit$module$1.initialize(new visit$1$());
            } catch (Throwable var4) {
               throw var4;
            }

            return var2;
         }

         private final visit$1$ visit$2(final LazyRef visit$module$1) {
            return visit$module$1.initialized() ? (visit$1$)visit$module$1.value() : visit$lzycompute$1(visit$module$1);
         }

         public {
            this.iter$1 = iter$1;
         }
      };
   }

   public UFunc.UImpl reduce_Complex(final CanTraverseValues iter) {
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

         public Tuple2 apply(final Object v) {
            LazyRef visit$module = new LazyRef();
            this.iter$2.traverse(v, this.visit$4(visit$module));
            return new Tuple2(this.visit$4(visit$module).sum(), BoxesRunTime.boxToInteger(this.visit$4(visit$module).n()));
         }

         // $FF: synthetic method
         private static final visit$3$ visit$lzycompute$2(final LazyRef visit$module$2) {
            synchronized(visit$module$2){}

            visit$3$ var2;
            try {
               class visit$3$ implements CanTraverseValues.ValuesVisitor {
                  private Complex sum;
                  private int n;

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

                  public void visit$mcJ$sp(final long a) {
                     CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                  }

                  public void visit$mcS$sp(final short a) {
                     CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                  }

                  public void visit$mcV$sp(final BoxedUnit a) {
                     CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                  }

                  public void visitArray(final Object arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$(this, arr);
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

                  public void visitArray$mcJ$sp(final long[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                  }

                  public void visitArray$mcS$sp(final short[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                  }

                  public void visitArray(final Object arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$(this, arr, offset, length, stride);
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

                  public Complex sum() {
                     return this.sum;
                  }

                  public void sum_$eq(final Complex x$1) {
                     this.sum = x$1;
                  }

                  public int n() {
                     return this.n;
                  }

                  public void n_$eq(final int x$1) {
                     this.n = x$1;
                  }

                  public void visit(final Complex a) {
                     this.sum_$eq(this.sum().$plus(a));
                     this.n_$eq(this.n() + 1);
                  }

                  public void zeros(final int numZero, final Complex zeroValue) {
                     this.sum_$eq(this.sum().$plus(zeroValue.$times(numZero)));
                     this.n_$eq(this.n() + numZero);
                  }

                  public visit$3$() {
                     CanTraverseValues.ValuesVisitor.$init$(this);
                     this.sum = Complex$.MODULE$.zero();
                     this.n = 0;
                  }
               }

               var2 = visit$module$2.initialized() ? (visit$3$)visit$module$2.value() : (visit$3$)visit$module$2.initialize(new visit$3$());
            } catch (Throwable var4) {
               throw var4;
            }

            return var2;
         }

         private final visit$3$ visit$4(final LazyRef visit$module$2) {
            return visit$module$2.initialized() ? (visit$3$)visit$module$2.value() : visit$lzycompute$2(visit$module$2);
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

         public Tuple2 apply(final Object v) {
            LazyRef visit$module = new LazyRef();
            this.iter$3.traverse(v, this.visit$6(visit$module));
            return new Tuple2(BoxesRunTime.boxToFloat(this.visit$6(visit$module).sum()), BoxesRunTime.boxToInteger(this.visit$6(visit$module).n()));
         }

         // $FF: synthetic method
         private static final visit$5$ visit$lzycompute$3(final LazyRef visit$module$3) {
            synchronized(visit$module$3){}

            visit$5$ var2;
            try {
               class visit$5$ implements CanTraverseValues$ValuesVisitor$mcF$sp {
                  private float sum;
                  private int n;

                  public void visitArray(final float[] arr) {
                     CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr);
                  }

                  public void visitArray$mcF$sp(final float[] arr) {
                     CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr);
                  }

                  public void visitArray(final float[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr, offset, length, stride);
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

                  public float sum() {
                     return this.sum;
                  }

                  public void sum_$eq(final float x$1) {
                     this.sum = x$1;
                  }

                  public int n() {
                     return this.n;
                  }

                  public void n_$eq(final int x$1) {
                     this.n = x$1;
                  }

                  public void visit(final float a) {
                     this.visit$mcF$sp(a);
                  }

                  public void zeros(final int numZero, final float zeroValue) {
                     this.zeros$mcF$sp(numZero, zeroValue);
                  }

                  public void visit$mcF$sp(final float a) {
                     this.sum_$eq(this.sum() + a);
                     this.n_$eq(this.n() + 1);
                  }

                  public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                     this.sum_$eq(this.sum() + zeroValue * (float)numZero);
                     this.n_$eq(this.n() + numZero);
                  }

                  public visit$5$() {
                     CanTraverseValues.ValuesVisitor.$init$(this);
                     this.sum = 0.0F;
                     this.n = 0;
                  }
               }

               var2 = visit$module$3.initialized() ? (visit$5$)visit$module$3.value() : (visit$5$)visit$module$3.initialize(new visit$5$());
            } catch (Throwable var4) {
               throw var4;
            }

            return var2;
         }

         private final visit$5$ visit$6(final LazyRef visit$module$3) {
            return visit$module$3.initialized() ? (visit$5$)visit$module$3.value() : visit$lzycompute$3(visit$module$3);
         }

         public {
            this.iter$3 = iter$3;
         }
      };
   }

   private accumulateAndCount$() {
   }
}
