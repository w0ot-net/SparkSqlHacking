package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcF$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcI$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcJ$sp;
import breeze.math.Semiring;
import scala.runtime.BoxedUnit;

public final class product$ implements UFunc {
   public static final product$ MODULE$ = new product$();

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
            class ProductVisitor$1 implements CanTraverseValues$ValuesVisitor$mcI$sp {
               private int product;

               public void visitArray(final int[] arr) {
                  CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr);
               }

               public void visitArray$mcI$sp(final int[] arr) {
                  CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr);
               }

               public void visitArray(final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr, offset, length, stride);
               }

               public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr, offset, length, stride);
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

               public int product() {
                  return this.product;
               }

               public void product_$eq(final int x$1) {
                  this.product = x$1;
               }

               public void visit(final int a) {
                  this.visit$mcI$sp(a);
               }

               public void zeros(final int numZero, final int zeroValue) {
                  this.zeros$mcI$sp(numZero, zeroValue);
               }

               public void visit$mcI$sp(final int a) {
                  this.product_$eq(this.product() * a);
               }

               public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                  if (numZero > 0) {
                     this.product_$eq(0);
                  }

               }

               public ProductVisitor$1() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.product = 1;
               }
            }

            ProductVisitor$1 visit = new ProductVisitor$1();
            this.iter$1.traverse(v, visit);
            return visit.product();
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
            class ProductVisitor$2 implements CanTraverseValues$ValuesVisitor$mcD$sp {
               private double product;

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

               public double product() {
                  return this.product;
               }

               public void product_$eq(final double x$1) {
                  this.product = x$1;
               }

               public void visit(final double a) {
                  this.visit$mcD$sp(a);
               }

               public void zeros(final int numZero, final double zeroValue) {
                  this.zeros$mcD$sp(numZero, zeroValue);
               }

               public void visit$mcD$sp(final double a) {
                  this.product_$eq(this.product() * a);
               }

               public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                  if (numZero > 0) {
                     this.product_$eq((double)0.0F);
                  }

               }

               public ProductVisitor$2() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.product = (double)1.0F;
               }
            }

            ProductVisitor$2 visit = new ProductVisitor$2();
            this.iter$2.traverse(v, visit);
            return visit.product();
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
            class ProductVisitor$3 implements CanTraverseValues$ValuesVisitor$mcF$sp {
               private float product;

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

               public float product() {
                  return this.product;
               }

               public void product_$eq(final float x$1) {
                  this.product = x$1;
               }

               public void visit(final float a) {
                  this.visit$mcF$sp(a);
               }

               public void zeros(final int numZero, final float zeroValue) {
                  this.zeros$mcF$sp(numZero, zeroValue);
               }

               public void visit$mcF$sp(final float a) {
                  this.product_$eq(this.product() * a);
               }

               public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                  if (numZero > 0) {
                     this.product_$eq(0.0F);
                  }

               }

               public ProductVisitor$3() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.product = 1.0F;
               }
            }

            ProductVisitor$3 visit = new ProductVisitor$3();
            this.iter$3.traverse(v, visit);
            return visit.product();
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
            class ProductVisitor$4 implements CanTraverseValues$ValuesVisitor$mcJ$sp {
               private long product;

               public void visitArray(final long[] arr) {
                  CanTraverseValues$ValuesVisitor$mcJ$sp.visitArray$(this, arr);
               }

               public void visitArray$mcJ$sp(final long[] arr) {
                  CanTraverseValues$ValuesVisitor$mcJ$sp.visitArray$mcJ$sp$(this, arr);
               }

               public void visitArray(final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues$ValuesVisitor$mcJ$sp.visitArray$(this, arr, offset, length, stride);
               }

               public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                  CanTraverseValues$ValuesVisitor$mcJ$sp.visitArray$mcJ$sp$(this, arr, offset, length, stride);
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

               public long product() {
                  return this.product;
               }

               public void product_$eq(final long x$1) {
                  this.product = x$1;
               }

               public void visit(final long a) {
                  this.visit$mcJ$sp(a);
               }

               public void zeros(final int numZero, final long zeroValue) {
                  this.zeros$mcJ$sp(numZero, zeroValue);
               }

               public void visit$mcJ$sp(final long a) {
                  this.product_$eq(this.product() * a);
               }

               public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                  if (numZero > 0) {
                     this.product_$eq(0L);
                  }

               }

               public ProductVisitor$4() {
                  CanTraverseValues.ValuesVisitor.$init$(this);
                  this.product = 1L;
               }
            }

            ProductVisitor$4 visit = new ProductVisitor$4();
            this.iter$4.traverse(v, visit);
            return visit.product();
         }

         public {
            this.iter$4 = iter$4;
         }
      };
   }

   public UFunc.UImpl reduceSemiring(final CanTraverseValues iter, final Semiring semiring) {
      return new UFunc.UImpl(semiring, iter) {
         public final Semiring semiring$1;
         private final CanTraverseValues iter$5;

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
            class ProductVisitor$5 implements CanTraverseValues.ValuesVisitor {
               private Object product;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

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

               public Object product() {
                  return this.product;
               }

               public void product_$eq(final Object x$1) {
                  this.product = x$1;
               }

               public void visit(final Object a) {
                  this.product_$eq(this.$outer.semiring$1.$times(this.product(), a));
               }

               public void zeros(final int numZero, final Object zeroValue) {
                  if (numZero > 0) {
                     this.product_$eq(this.$outer.semiring$1.zero());
                  }

               }

               public ProductVisitor$5() {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     super();
                     CanTraverseValues.ValuesVisitor.$init$(this);
                     this.product = semiring$1.one();
                  }
               }
            }

            ProductVisitor$5 visit = new ProductVisitor$5();
            this.iter$5.traverse(v, visit);
            return visit.product();
         }

         public {
            this.semiring$1 = semiring$1;
            this.iter$5 = iter$5;
         }
      };
   }

   private product$() {
   }
}
