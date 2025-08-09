package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.SparseVector;
import breeze.linalg.package$;
import breeze.storage.Zero$;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005I3qAB\u0004\u0011\u0002\u0007\u0005a\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f]\u0002!\u0019!C\u0002q!9\u0001\t\u0001b\u0001\n\u0007\t\u0005bB%\u0001\u0005\u0004%\u0019A\u0013\u0002\u001c'B\f'o]3WK\u000e$xN]0EK:\u001cX-T1ue&Dx\n]:\u000b\u0005!I\u0011!C8qKJ\fGo\u001c:t\u0015\tQ1\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0019\u00051!M]3fu\u0016\u001c\u0001aE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u00059\u0011B\u0001\r\b\u0005Y!UM\\:f\u001b\u0006$(/\u001b=Nk2$\u0018\u000e\u001d7z\u001fB\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001c!\t\u0001B$\u0003\u0002\u001e#\t!QK\\5u\u0003\u0001JW\u000e\u001d7`\u001fBlU\u000f\\'biJL\u0007p\u0018#N?N3v,Z9`\tZ{\u0016J\u001c;\u0016\u0003\u0001\u0002R!\t\u0013+cQr!A\u0006\u0012\n\u0005\r:\u0011aC(q\u001bVdW*\u0019;sSbL!!\n\u0014\u0003\u000b%k\u0007\u000f\u001c\u001a\n\u0005\u001dB#!B+Gk:\u001c'BA\u0015\f\u0003\u001d9WM\\3sS\u000e\u00042a\u000b\u0017/\u001b\u0005I\u0011BA\u0017\n\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005Ay\u0013B\u0001\u0019\u0012\u0005\rIe\u000e\u001e\t\u0004WIr\u0013BA\u001a\n\u00051\u0019\u0006/\u0019:tKZ+7\r^8s!\rYSGL\u0005\u0003m%\u00111\u0002R3og\u00164Vm\u0019;pe\u0006\u0011\u0013.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0E\u001b~\u001bfkX3r?\u00123vL\u00127pCR,\u0012!\u000f\t\u0006C\u0011Rdh\u0010\t\u0004W1Z\u0004C\u0001\t=\u0013\ti\u0014CA\u0003GY>\fG\u000fE\u0002,em\u00022aK\u001b<\u0003\u0005JW\u000e\u001d7`\u001fBlU\u000f\\'biJL\u0007p\u0018#N?N3v,Z9`\tZ{Fj\u001c8h+\u0005\u0011\u0005#B\u0011%\u0007\u001eC\u0005cA\u0016-\tB\u0011\u0001#R\u0005\u0003\rF\u0011A\u0001T8oOB\u00191F\r#\u0011\u0007-*D)A\u0012j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#UjX*W?\u0016\fx\f\u0012,`\t>,(\r\\3\u0016\u0003-\u0003R!\t\u0013M!F\u00032a\u000b\u0017N!\t\u0001b*\u0003\u0002P#\t1Ai\\;cY\u0016\u00042a\u000b\u001aN!\rYS'\u0014"
)
public interface SparseVector_DenseMatrixOps extends DenseMatrixMultiplyOps {
   void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Double_$eq(final UFunc.UImpl2 x$1);

   UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Int();

   UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Float();

   UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Long();

   UFunc.UImpl2 impl_OpMulMatrix_DM_SV_eq_DV_Double();

   static void $init$(final SparseVector_DenseMatrixOps $this) {
      $this.breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseMatrixOps $outer;

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

         public DenseVector apply(final DenseMatrix v, final SparseVector v2) {
            int left$macro$1 = v.cols();
            int right$macro$2 = v2.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("v.cols == v2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(v.rows(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = v2.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  package$.MODULE$.axpy(BoxesRunTime.boxToInteger(v2.valueAt$mcI$sp(index$macro$4)), v.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(v2.indexAt(index$macro$4)), this.$outer.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Int());
               }

               return result;
            }
         }

         public {
            if (SparseVector_DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseMatrixOps.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseMatrixOps.this.impl_OpMulMatrix_DM_V_eq_DV_Int())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(SparseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseMatrixOps $outer;

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

         public DenseVector apply(final DenseMatrix v, final SparseVector v2) {
            int left$macro$1 = v.cols();
            int right$macro$2 = v2.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("v.cols == v2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(v.rows(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = v2.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  package$.MODULE$.axpy(BoxesRunTime.boxToFloat(v2.valueAt$mcF$sp(index$macro$4)), v.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(v2.indexAt(index$macro$4)), this.$outer.canSliceCol()), result, HasOps$.MODULE$.impl_scaledAdd_InPlace_DV_S_DV_Float());
               }

               return result;
            }
         }

         public {
            if (SparseVector_DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseMatrixOps.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseMatrixOps.this.impl_OpMulMatrix_DM_V_eq_DV_Float())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(SparseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseMatrixOps $outer;

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

         public DenseVector apply(final DenseMatrix v, final SparseVector v2) {
            int left$macro$1 = v.cols();
            int right$macro$2 = v2.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("v.cols == v2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(v.rows(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = v2.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  package$.MODULE$.axpy(BoxesRunTime.boxToLong(v2.valueAt$mcJ$sp(index$macro$4)), v.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(v2.indexAt(index$macro$4)), this.$outer.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Long());
               }

               return result;
            }
         }

         public {
            if (SparseVector_DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseMatrixOps.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseMatrixOps.this.impl_OpMulMatrix_DM_V_eq_DV_Long())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(SparseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseMatrixOps$_setter_$impl_OpMulMatrix_DM_SV_eq_DV_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseMatrixOps $outer;

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

         public DenseVector apply(final DenseMatrix v, final SparseVector v2) {
            int left$macro$1 = v.cols();
            int right$macro$2 = v2.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("v.cols == v2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(v.rows(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = v2.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  package$.MODULE$.axpy(BoxesRunTime.boxToDouble(v2.valueAt$mcD$sp(index$macro$4)), v.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(v2.indexAt(index$macro$4)), this.$outer.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
               }

               return result;
            }
         }

         public {
            if (SparseVector_DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseMatrixOps.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseMatrixOps.this.impl_OpMulMatrix_DM_V_eq_DV_Double())).register(this, .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(SparseVector.class));
            }
         }
      });
   }
}
