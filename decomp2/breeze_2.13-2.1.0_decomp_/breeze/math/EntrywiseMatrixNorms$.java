package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.sum$;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcF$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcI$sp;
import breeze.numerics.package$pow$powDoubleDoubleImpl$;
import breeze.numerics.package$pow$powFloatFloatImpl$;
import breeze.numerics.package$pow$powIntIntImpl$;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class EntrywiseMatrixNorms$ {
   public static final EntrywiseMatrixNorms$ MODULE$ = new EntrywiseMatrixNorms$();

   public MatrixInnerProduct make(final Field field, final UFunc.UImpl2 hadamard, final CanTraverseValues iter) {
      return new MatrixInnerProduct(hadamard, iter, field) {
         private UFunc.UImpl2 canInnerProduct;
         private final UFunc.UImpl2 hadamard$1;
         public final CanTraverseValues iter$1;
         private final Field field$1;

         public UFunc.UImpl canInnerProductNorm_Ring(final Ring ring) {
            return MatrixInnerProduct.canInnerProductNorm_Ring$(this, ring);
         }

         public UFunc.UImpl2 canInnerProduct() {
            return this.canInnerProduct;
         }

         public void breeze$math$MatrixInnerProduct$_setter_$canInnerProduct_$eq(final UFunc.UImpl2 x$1) {
            this.canInnerProduct = x$1;
         }

         public Object innerProduct(final Object m1, final Object m2) {
            return sum$.MODULE$.apply(this.hadamard$1.apply(m1, m2), sum$.MODULE$.reduceSemiring(this.iter$1, this.field$1));
         }

         public UFunc.UImpl2 canNorm_Int(final CanTraverseValues iter) {
            return new UFunc.UImpl2(iter) {
               private final CanTraverseValues iter$2;

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

               public double apply(final Object v, final int n) {
                  class NormVisitor$1 implements CanTraverseValues$ValuesVisitor$mcI$sp {
                     private double agg;
                     // $FF: synthetic field
                     private final Tuple2 x$1;
                     private final Function1 op;
                     private final Function1 opEnd;
                     private final int n$1;

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

                     public double agg() {
                        return this.agg;
                     }

                     public void agg_$eq(final double x$1) {
                        this.agg = x$1;
                     }

                     public Function1 op() {
                        return this.op;
                     }

                     public Function1 opEnd() {
                        return this.opEnd;
                     }

                     public void visit(final int a) {
                        this.visit$mcI$sp(a);
                     }

                     public void zeros(final int numZero, final int zeroValue) {
                        this.zeros$mcI$sp(numZero, zeroValue);
                     }

                     public double norm() {
                        return this.opEnd().apply$mcDD$sp(this.agg());
                     }

                     public void visit$mcI$sp(final int a) {
                        this.op().apply$mcVI$sp(a);
                     }

                     public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                     }

                     public NormVisitor$1(final int n$1) {
                        this.n$1 = n$1;
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.agg = (double)0.0F;
                        Tuple2 var4 = n$1 == 1 ? new Tuple2((JFunction1.mcVI.sp)(v) -> this.agg_$eq(this.agg() + (double).MODULE$.abs$extension(scala.Predef..MODULE$.intWrapper(v))), (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : (n$1 == 2 ? new Tuple2((JFunction1.mcVI.sp)(v) -> {
                           double nn = (double).MODULE$.abs$extension(scala.Predef..MODULE$.intWrapper(v));
                           this.agg_$eq(this.agg() + nn * nn);
                        }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(e, package$sqrt$sqrtDoubleImpl$.MODULE$)) : (n$1 == Integer.MAX_VALUE ? new Tuple2((JFunction1.mcVI.sp)(v) -> {
                           double nn = (double).MODULE$.abs$extension(scala.Predef..MODULE$.intWrapper(v));
                           if (nn > this.agg()) {
                              this.agg_$eq(nn);
                           }

                        }, (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : new Tuple2((JFunction1.mcVI.sp)(v) -> {
                           double var10000 = (double).MODULE$.abs$extension(scala.Predef..MODULE$.intWrapper(v));
                           this.agg_$eq(this.agg() + (double)breeze.numerics.package.pow$.MODULE$.apply$mIIIc$sp(v, this.n$1, package$pow$powIntIntImpl$.MODULE$));
                        }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(e, (double)1.0F / (double)this.n$1, package$pow$powDoubleDoubleImpl$.MODULE$))));
                        if (var4 != null) {
                           Function1 op = (Function1)var4._1();
                           Function1 opEnd = (Function1)var4._2();
                           Tuple2 var3 = new Tuple2(op, opEnd);
                           this.x$1 = var3;
                           this.op = (Function1)this.x$1._1();
                           this.opEnd = (Function1)this.x$1._2();
                        } else {
                           throw new MatchError(var4);
                        }
                     }

                     // $FF: synthetic method
                     private static Object $deserializeLambda$(SerializedLambda var0) {
                        return Class.lambdaDeserialize<invokedynamic>(var0);
                     }
                  }

                  NormVisitor$1 visit = new NormVisitor$1(n);
                  this.iter$2.traverse(v, visit);
                  return visit.norm();
               }

               public {
                  this.iter$2 = iter$2;
               }
            };
         }

         public UFunc.UImpl2 canNorm_Float(final CanTraverseValues iter) {
            return new UFunc.UImpl2(iter) {
               private final CanTraverseValues iter$3;

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

               public double apply(final Object v, final float n) {
                  class NormVisitor$2 implements CanTraverseValues$ValuesVisitor$mcF$sp {
                     private double agg;
                     // $FF: synthetic field
                     private final Tuple2 x$2;
                     private final Function1 op;
                     private final Function1 opEnd;
                     private final float n$2;

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

                     public double agg() {
                        return this.agg;
                     }

                     public void agg_$eq(final double x$1) {
                        this.agg = x$1;
                     }

                     public Function1 op() {
                        return this.op;
                     }

                     public Function1 opEnd() {
                        return this.opEnd;
                     }

                     public void visit(final float a) {
                        this.visit$mcF$sp(a);
                     }

                     public void zeros(final int numZero, final float zeroValue) {
                        this.zeros$mcF$sp(numZero, zeroValue);
                     }

                     public double norm() {
                        return this.opEnd().apply$mcDD$sp(this.agg());
                     }

                     public void visit$mcF$sp(final float a) {
                        this.op().apply$mcVF$sp(a);
                     }

                     public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                     }

                     public NormVisitor$2(final float n$2) {
                        this.n$2 = n$2;
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.agg = (double)0.0F;
                        Tuple2 var4 = n$2 == (float)1 ? new Tuple2((JFunction1.mcVF.sp)(v) -> this.agg_$eq(this.agg() + (double)scala.runtime.RichFloat..MODULE$.abs$extension(scala.Predef..MODULE$.floatWrapper(v))), (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : (n$2 == (float)2 ? new Tuple2((JFunction1.mcVF.sp)(v) -> {
                           double nn = (double)scala.runtime.RichFloat..MODULE$.abs$extension(scala.Predef..MODULE$.floatWrapper(v));
                           this.agg_$eq(this.agg() + nn * nn);
                        }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(e, package$sqrt$sqrtDoubleImpl$.MODULE$)) : (n$2 == Float.POSITIVE_INFINITY ? new Tuple2((JFunction1.mcVF.sp)(v) -> {
                           double nn = (double)scala.runtime.RichFloat..MODULE$.abs$extension(scala.Predef..MODULE$.floatWrapper(v));
                           if (nn > this.agg()) {
                              this.agg_$eq(nn);
                           }

                        }, (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : new Tuple2((JFunction1.mcVF.sp)(v) -> {
                           double var10000 = (double)scala.runtime.RichFloat..MODULE$.abs$extension(scala.Predef..MODULE$.floatWrapper(v));
                           this.agg_$eq(this.agg() + (double)breeze.numerics.package.pow$.MODULE$.apply$mFFFc$sp(v, this.n$2, package$pow$powFloatFloatImpl$.MODULE$));
                        }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(e, (double)1.0F / (double)this.n$2, package$pow$powDoubleDoubleImpl$.MODULE$))));
                        if (var4 != null) {
                           Function1 op = (Function1)var4._1();
                           Function1 opEnd = (Function1)var4._2();
                           Tuple2 var3 = new Tuple2(op, opEnd);
                           this.x$2 = var3;
                           this.op = (Function1)this.x$2._1();
                           this.opEnd = (Function1)this.x$2._2();
                        } else {
                           throw new MatchError(var4);
                        }
                     }

                     // $FF: synthetic method
                     private static Object $deserializeLambda$(SerializedLambda var0) {
                        return Class.lambdaDeserialize<invokedynamic>(var0);
                     }
                  }

                  NormVisitor$2 visit = new NormVisitor$2(n);
                  this.iter$3.traverse(v, visit);
                  return visit.norm();
               }

               public {
                  this.iter$3 = iter$3;
               }
            };
         }

         public UFunc.UImpl2 canNorm_Double(final CanTraverseValues iter) {
            return new UFunc.UImpl2(iter) {
               private final CanTraverseValues iter$4;

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

               public double apply(final Object v, final double n) {
                  class NormVisitor$3 implements CanTraverseValues$ValuesVisitor$mcD$sp {
                     private double agg;
                     // $FF: synthetic field
                     private final Tuple2 x$3;
                     private final Function1 op;
                     private final Function1 opEnd;
                     private final double n$3;

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

                     public double agg() {
                        return this.agg;
                     }

                     public void agg_$eq(final double x$1) {
                        this.agg = x$1;
                     }

                     public Function1 op() {
                        return this.op;
                     }

                     public Function1 opEnd() {
                        return this.opEnd;
                     }

                     public void visit(final double a) {
                        this.visit$mcD$sp(a);
                     }

                     public void zeros(final int numZero, final double zeroValue) {
                        this.zeros$mcD$sp(numZero, zeroValue);
                     }

                     public double norm() {
                        return this.opEnd().apply$mcDD$sp(this.agg());
                     }

                     public void visit$mcD$sp(final double a) {
                        this.op().apply$mcVD$sp(a);
                     }

                     public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                     }

                     public NormVisitor$3(final double n$3) {
                        this.n$3 = n$3;
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.agg = (double)0.0F;
                        Tuple2 var5 = n$3 == (double)1 ? new Tuple2((JFunction1.mcVD.sp)(v) -> this.agg_$eq(this.agg() + scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(v))), (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : (n$3 == (double)2 ? new Tuple2((JFunction1.mcVD.sp)(v) -> {
                           double nn = scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(v));
                           this.agg_$eq(this.agg() + nn * nn);
                        }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(e, package$sqrt$sqrtDoubleImpl$.MODULE$)) : (n$3 == Double.POSITIVE_INFINITY ? new Tuple2((JFunction1.mcVD.sp)(v) -> {
                           double nn = scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(v));
                           if (nn > this.agg()) {
                              this.agg_$eq(nn);
                           }

                        }, (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : new Tuple2((JFunction1.mcVD.sp)(v) -> {
                           double nn = scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(v));
                           this.agg_$eq(this.agg() + breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(v, this.n$3, package$pow$powDoubleDoubleImpl$.MODULE$));
                        }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(e, (double)1.0F / this.n$3, package$pow$powDoubleDoubleImpl$.MODULE$))));
                        if (var5 != null) {
                           Function1 op = (Function1)var5._1();
                           Function1 opEnd = (Function1)var5._2();
                           Tuple2 var4 = new Tuple2(op, opEnd);
                           this.x$3 = var4;
                           this.op = (Function1)this.x$3._1();
                           this.opEnd = (Function1)this.x$3._2();
                        } else {
                           throw new MatchError(var5);
                        }
                     }

                     // $FF: synthetic method
                     private static Object $deserializeLambda$(SerializedLambda var0) {
                        return Class.lambdaDeserialize<invokedynamic>(var0);
                     }
                  }

                  NormVisitor$3 visit = new NormVisitor$3(n);
                  this.iter$4.traverse(v, visit);
                  return visit.norm();
               }

               public {
                  this.iter$4 = iter$4;
               }
            };
         }

         public UFunc.UImpl2 canNorm_Field(final Field field) {
            return new UFunc.UImpl2(field) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               public final Field field$2;

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

               public double apply(final Object v, final double n) {
                  class NormVisitor$4 implements CanTraverseValues.ValuesVisitor {
                     private double agg;
                     // $FF: synthetic field
                     private final Tuple2 x$4;
                     private final Function1 op;
                     private final Function1 opEnd;
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final double n$4;

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

                     public double agg() {
                        return this.agg;
                     }

                     public void agg_$eq(final double x$1) {
                        this.agg = x$1;
                     }

                     public Function1 op() {
                        return this.op;
                     }

                     public Function1 opEnd() {
                        return this.opEnd;
                     }

                     public void visit(final Object a) {
                        this.op().apply(a);
                     }

                     public void zeros(final int numZero, final Object zeroValue) {
                     }

                     public double norm() {
                        return this.opEnd().apply$mcDD$sp(this.agg());
                     }

                     // $FF: synthetic method
                     public static final void $anonfun$x$4$1(final NormVisitor$4 $this, final Object v) {
                        $this.agg_$eq($this.agg() + $this.$outer.field$2.sNorm(v));
                     }

                     // $FF: synthetic method
                     public static final void $anonfun$x$4$3(final NormVisitor$4 $this, final Object v) {
                        double nn = $this.$outer.field$2.sNorm(v);
                        $this.agg_$eq($this.agg() + nn * nn);
                     }

                     // $FF: synthetic method
                     public static final void $anonfun$x$4$5(final NormVisitor$4 $this, final Object v) {
                        double nn = $this.$outer.field$2.sNorm(v);
                        if (nn > $this.agg()) {
                           $this.agg_$eq(nn);
                        }

                     }

                     // $FF: synthetic method
                     public static final void $anonfun$x$4$7(final NormVisitor$4 $this, final Object v) {
                        double nn = $this.$outer.field$2.sNorm(v);
                        $this.agg_$eq($this.agg() + breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(nn, $this.n$4, package$pow$powDoubleDoubleImpl$.MODULE$));
                     }

                     public NormVisitor$4(final double n$4) {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.n$4 = n$4;
                           super();
                           CanTraverseValues.ValuesVisitor.$init$(this);
                           this.agg = (double)0.0F;
                           Tuple2 var5 = n$4 == (double)1 ? new Tuple2((Function1)(v) -> {
                              $anonfun$x$4$1(this, v);
                              return BoxedUnit.UNIT;
                           }, (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : (n$4 == (double)2 ? new Tuple2((Function1)(v) -> {
                              $anonfun$x$4$3(this, v);
                              return BoxedUnit.UNIT;
                           }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(e, package$sqrt$sqrtDoubleImpl$.MODULE$)) : (n$4 == Double.POSITIVE_INFINITY ? new Tuple2((Function1)(v) -> {
                              $anonfun$x$4$5(this, v);
                              return BoxedUnit.UNIT;
                           }, (JFunction1.mcDD.sp)(x) -> BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.identity(BoxesRunTime.boxToDouble(x)))) : new Tuple2((Function1)(v) -> {
                              $anonfun$x$4$7(this, v);
                              return BoxedUnit.UNIT;
                           }, (JFunction1.mcDD.sp)(e) -> breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp(e, (double)1.0F / this.n$4, package$pow$powDoubleDoubleImpl$.MODULE$))));
                           if (var5 != null) {
                              Function1 op = (Function1)var5._1();
                              Function1 opEnd = (Function1)var5._2();
                              Tuple2 var4 = new Tuple2(op, opEnd);
                              this.x$4 = var4;
                              this.op = (Function1)this.x$4._1();
                              this.opEnd = (Function1)this.x$4._2();
                           } else {
                              throw new MatchError(var5);
                           }
                        }
                     }

                     // $FF: synthetic method
                     private static Object $deserializeLambda$(SerializedLambda var0) {
                        return Class.lambdaDeserialize<invokedynamic>(var0);
                     }
                  }

                  NormVisitor$4 visit = new NormVisitor$4(n);
                  this.$outer.iter$1.traverse(v, visit);
                  return visit.norm();
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.field$2 = field$2;
                  }
               }
            };
         }

         public {
            this.hadamard$1 = hadamard$1;
            this.iter$1 = iter$1;
            this.field$1 = field$1;
            MatrixInnerProduct.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   private EntrywiseMatrixNorms$() {
   }
}
