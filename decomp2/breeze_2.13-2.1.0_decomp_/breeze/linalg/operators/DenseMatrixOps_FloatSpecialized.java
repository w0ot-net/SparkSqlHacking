package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseMatrix$mcF$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.DenseVector$mcF$sp;
import breeze.linalg.MatrixSingularException;
import breeze.linalg.MatrixSingularException$;
import breeze.linalg.NumericOps;
import breeze.storage.Zero$;
import dev.ludovic.netlib.blas.BLAS;
import dev.ludovic.netlib.lapack.LAPACK;
import java.lang.invoke.SerializedLambda;
import org.netlib.util.intW;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=aaB\n\u0015!\u0003\r\ta\u0007\u0005\u0006Y\u0001!\t!\f\u0005\bc\u0001\u0011\r\u0011b\u00013\u0011\u0015!\u0005\u0001\"\u0003F\u000f\u0015\u0019\u0006\u0001c\u0001U\r\u00151\u0006\u0001#\u0001X\u0011\u0015aV\u0001\"\u0001^\u0011\u0015qV\u0001\"\u0001`\u0011\u001d\u0019\u0007A1A\u0005\u0004\u0011<QA\u001a\u0001\t\u0004\u001d4Q\u0001\u001b\u0001\t\u0002%DQ\u0001\u0018\u0006\u0005\u00029DQA\u0018\u0006\u0005B=DQ\u0001\u001e\u0006\u0005\u0002UDQ!\u001f\u0006\u0005\u0002i<QA \u0001\t\u0004}4q!!\u0001\u0001\u0011\u0003\t\u0019\u0001\u0003\u0004]!\u0011\u0005\u0011q\u0001\u0005\u0007=B!\t%!\u0003\u0003?\u0011+gn]3NCR\u0014\u0018\u000e_(qg~3En\\1u'B,7-[1mSj,GM\u0003\u0002\u0016-\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003/a\ta\u0001\\5oC2<'\"A\r\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019R\u0001\u0001\u000f#M%\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0007CA\u0012%\u001b\u0005!\u0012BA\u0013\u0015\u0005Y!UM\\:f\u001b\u0006$(/\u001b=FqB\fg\u000eZ3e\u001fB\u001c\bCA\u0012(\u0013\tACC\u0001\nEK:\u001cX-T1ue&DX*\u001e7u\u001fB\u001c\bCA\u0012+\u0013\tYCC\u0001\fEK:\u001cX-T1ue&Dxl\u00157jG&twm\u00149t\u0003\u0019!\u0013N\\5uIQ\ta\u0006\u0005\u0002\u001e_%\u0011\u0001G\b\u0002\u0005+:LG/\u0001\u0012j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#Uj\u0018#N?\u0016\fx\fR'`\r2|\u0017\r^\u000b\u0002gA)AgN\u001f>{9\u00111%N\u0005\u0003mQ\t1b\u00149Nk2l\u0015\r\u001e:jq&\u0011\u0001(\u000f\u0002\u0006\u00136\u0004HNM\u0005\u0003um\u0012Q!\u0016$v]\u000eT!\u0001\u0010\r\u0002\u000f\u001d,g.\u001a:jGB\u0019ahP!\u000e\u0003YI!\u0001\u0011\f\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003;\tK!a\u0011\u0010\u0003\u000b\u0019cw.\u0019;\u0002\u001fQ\u0014\u0018M\\:q_N,7\u000b\u001e:j]\u001e$\"AR)\u0011\u0005\u001dseB\u0001%M!\tIe$D\u0001K\u0015\tY%$\u0001\u0004=e>|GOP\u0005\u0003\u001bz\ta\u0001\u0015:fI\u00164\u0017BA(Q\u0005\u0019\u0019FO]5oO*\u0011QJ\b\u0005\u0006%\u000e\u0001\r!P\u0001\u0002C\u0006y\u0012.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0E\u001b\u001a{FI\u0016$`KF|FI\u0016$\u0011\u0005U+Q\"\u0001\u0001\u0003?%l\u0007\u000f\\0Pa6+H.T1ue&Dx\fR'G?\u00123fiX3r?\u00123fiE\u0002\u00069a\u0003R\u0001N\u001c>3f\u00032A\u0010.B\u0013\tYfCA\u0006EK:\u001cXMV3di>\u0014\u0018A\u0002\u001fj]&$h\bF\u0001U\u0003\u0015\t\u0007\u000f\u001d7z)\rI\u0006-\u0019\u0005\u0006%\u001e\u0001\r!\u0010\u0005\u0006E\u001e\u0001\r!W\u0001\u0002E\u0006y\u0012.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0E-\u001a{F)\u0014$`KF|F)\u0014$\u0016\u0003\u0015\u0004R\u0001N\u001cZ{u\n1%[7qY~{\u0005oU8mm\u0016l\u0015\r\u001e:jq\nKx\fR'G?\u0012keiX3r?\u0012ke\t\u0005\u0002V\u0015\t\u0019\u0013.\u001c9m?>\u00038k\u001c7wK6\u000bGO]5y\u0005f|F)\u0014$`\t63u,Z9`\t635c\u0001\u0006\u001dUB)1nN\u001f>{9\u00111\u0005\\\u0005\u0003[R\tqb\u00149T_24X-T1ue&D()\u001f\u000b\u0002OR\u0019Q\b\u001d:\t\u000bEd\u0001\u0019A\u001f\u0002\u0003\u0005CQa\u001d\u0007A\u0002u\n\u0011AV\u0001\b\u0019V\u001bv\u000e\u001c<f)\rid\u000f\u001f\u0005\u0006o6\u0001\r!P\u0001\u00021\")\u0011/\u0004a\u0001{\u00059\u0011KU*pYZ,G\u0003B\u001f|yvDQa\u001e\bA\u0002uBQ!\u001d\bA\u0002uBQa\u001d\bA\u0002u\n1%[7qY~{\u0005oU8mm\u0016l\u0015\r\u001e:jq\nKx\fR'G?\u00123fiX3r?\u00123f\t\u0005\u0002V!\t\u0019\u0013.\u001c9m?>\u00038k\u001c7wK6\u000bGO]5y\u0005f|F)\u0014$`\tZ3u,Z9`\tZ35\u0003\u0002\t\u001d\u0003\u000b\u0001Ra[\u001c>3f#\u0012a \u000b\u00063\u0006-\u0011Q\u0002\u0005\u0006%J\u0001\r!\u0010\u0005\u0006EJ\u0001\r!\u0017"
)
public interface DenseMatrixOps_FloatSpecialized extends DenseMatrixMultOps, DenseMatrix_SlicingOps {
   impl_OpMulMatrix_DMF_DVF_eq_DVF$ impl_OpMulMatrix_DMF_DVF_eq_DVF();

   impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$ impl_OpSolveMatrixBy_DMF_DMF_eq_DMF();

   impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$ impl_OpSolveMatrixBy_DMF_DVF_eq_DVF();

   void breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$_setter_$impl_OpMulMatrix_DVF_DMF_eq_DMF_$eq(final UFunc.UImpl2 x$1);

   UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Float();

   // $FF: synthetic method
   static String breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString$(final DenseMatrixOps_FloatSpecialized $this, final DenseMatrix a) {
      return $this.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString(a);
   }

   default String breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString(final DenseMatrix a) {
      return a.isTranspose() ? "T" : "N";
   }

   UFunc.UImpl2 impl_OpMulMatrix_DVF_DMF_eq_DMF();

   static void $init$(final DenseMatrixOps_FloatSpecialized $this) {
      $this.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$_setter_$impl_OpMulMatrix_DM_DM_eq_DM_Float_$eq((_a, _b) -> {
         int left$macro$1 = _a.cols();
         int right$macro$2 = _b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension mismatch!: ").append("_a.cols == _b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseMatrix rv = DenseMatrix$.MODULE$.zeros$mFc$sp(_a.rows(), _b.cols(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            if (_a.rows() != 0 && _b.rows() != 0 && _a.cols() != 0 && _b.cols() != 0) {
               DenseMatrix a = _a.majorStride() < scala.math.package..MODULE$.max(_a.isTranspose() ? _a.cols() : _a.rows(), 1) ? _a.copy$mcF$sp() : _a;
               DenseMatrix b = _b.majorStride() < scala.math.package..MODULE$.max(_b.isTranspose() ? _b.cols() : _b.rows(), 1) ? _b.copy$mcF$sp() : _b;
               BLAS.getInstance().sgemm($this.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString(a), $this.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString(b), rv.rows(), rv.cols(), a.cols(), 1.0F, a.data$mcF$sp(), a.offset(), a.majorStride(), b.data$mcF$sp(), b.offset(), b.majorStride(), 0.0F, rv.data$mcF$sp(), 0, rv.rows());
            }

            return rv;
         }
      });
      ((BinaryRegistry)scala.Predef..MODULE$.implicitly($this.op_M_M_Float())).register($this.impl_OpMulMatrix_DM_DM_eq_DM_Float(), .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(DenseMatrix.class));
      ((BinaryRegistry)scala.Predef..MODULE$.implicitly($this.impl_OpMulMatrix_DM_M_eq_DM_Float())).register($this.impl_OpMulMatrix_DM_DM_eq_DM_Float(), .MODULE$.apply(DenseMatrix.class), .MODULE$.apply(DenseMatrix.class));
      $this.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$_setter_$impl_OpMulMatrix_DVF_DMF_eq_DMF_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final DenseMatrixOps_FloatSpecialized $outer;

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

         public DenseMatrix apply(final DenseVector a, final DenseMatrix b) {
            int left$macro$1 = b.rows();
            int right$macro$2 = 1;
            if (left$macro$1 != 1) {
               throw new IllegalArgumentException((new StringBuilder(49)).append("requirement failed: ").append("b.rows == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
            } else {
               DenseMatrix rv = DenseMatrix$.MODULE$.zeros$mFc$sp(a.length(), b.cols(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               BLAS.getInstance().sgemm("T", this.$outer.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString(b), rv.rows(), rv.cols(), 1, 1.0F, a.data$mcF$sp(), a.offset(), a.stride(), b.data$mcF$sp(), b.offset(), b.majorStride(), 0.0F, rv.data$mcF$sp(), 0, rv.rows());
               return rv;
            }
         }

         public {
            if (DenseMatrixOps_FloatSpecialized.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps_FloatSpecialized.this;
            }
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class impl_OpMulMatrix_DMF_DVF_eq_DVF$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixOps_FloatSpecialized $outer;

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

      public DenseVector apply(final DenseMatrix a, final DenseVector b) {
         int left$macro$1 = a.cols();
         int right$macro$2 = b.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseVector rv = DenseVector$.MODULE$.zeros$mFc$sp(a.rows(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            BLAS.getInstance().sgemv(this.$outer.breeze$linalg$operators$DenseMatrixOps_FloatSpecialized$$transposeString(a), a.isTranspose() ? a.cols() : a.rows(), a.isTranspose() ? a.rows() : a.cols(), 1.0F, a.data$mcF$sp(), a.offset(), a.majorStride(), b.data$mcF$sp(), b.offset(), b.stride(), 0.0F, rv.data$mcF$sp(), rv.offset(), rv.stride());
            return rv;
         }
      }

      public impl_OpMulMatrix_DMF_DVF_eq_DVF$() {
         if (DenseMatrixOps_FloatSpecialized.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixOps_FloatSpecialized.this;
            super();
         }
      }
   }

   public class impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixOps_FloatSpecialized $outer;

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

      public DenseMatrix apply(final DenseMatrix A, final DenseMatrix V) {
         int left$macro$1 = A.rows();
         int right$macro$2 = V.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(72)).append("requirement failed: Non-conformant matrix sizes: ").append("A.rows == V.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseMatrix var10000;
            if (A.size() == 0) {
               var10000 = DenseMatrix$.MODULE$.zeros$mFc$sp(0, 0, .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            } else if (A.rows() == A.cols()) {
               DenseMatrix X = DenseMatrix$.MODULE$.zeros$mFc$sp(V.rows(), V.cols(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               X.$colon$eq(V, this.$outer.dm_dm_UpdateOp_Float_OpSet());
               this.LUSolve(X, A);
               var10000 = X;
            } else {
               DenseMatrix X = DenseMatrix$.MODULE$.zeros$mFc$sp(A.cols(), V.cols(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               this.QRSolve(X, A, V);
               var10000 = X;
            }

            return var10000;
         }
      }

      public DenseMatrix LUSolve(final DenseMatrix X, final DenseMatrix A) {
         int left$macro$1 = X.offset();
         int right$macro$2 = 0;
         if (left$macro$1 != 0) {
            throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("X.offset == 0 (").append(left$macro$1).append(" ").append("!=").append(" ").append(0).append(")").toString());
         } else {
            int left$macro$3 = A.offset();
            int right$macro$4 = 0;
            if (left$macro$3 != 0) {
               throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("A.offset == 0 (").append(left$macro$3).append(" ").append("!=").append(" ").append(0).append(")").toString());
            } else {
               int[] piv = new int[A.rows()];
               DenseMatrix newA = A.copy$mcF$sp();
               boolean cond$macro$5 = !newA.isTranspose();
               if (!cond$macro$5) {
                  throw new AssertionError("assertion failed: newA.isTranspose.unary_!");
               } else {
                  intW info = new intW(0);
                  LAPACK.getInstance().sgesv(A.rows(), X.cols(), newA.data$mcF$sp(), newA.majorStride(), piv, X.data$mcF$sp(), X.majorStride(), info);
                  int info = info.val;
                  if (info > 0) {
                     throw new MatrixSingularException(MatrixSingularException$.MODULE$.$lessinit$greater$default$1());
                  } else if (info < 0) {
                     throw new IllegalArgumentException();
                  } else {
                     return X;
                  }
               }
            }
         }
      }

      public DenseMatrix QRSolve(final DenseMatrix X, final DenseMatrix A, final DenseMatrix V) {
         int left$macro$1 = X.offset();
         int right$macro$2 = 0;
         if (left$macro$1 != 0) {
            throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("X.offset == 0 (").append(left$macro$1).append(" ").append("!=").append(" ").append(0).append(")").toString());
         } else {
            int left$macro$3 = A.offset();
            int right$macro$4 = 0;
            if (left$macro$3 != 0) {
               throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("A.offset == 0 (").append(left$macro$3).append(" ").append("!=").append(" ").append(0).append(")").toString());
            } else {
               int left$macro$5 = V.offset();
               int right$macro$6 = 0;
               if (left$macro$5 != 0) {
                  throw new IllegalArgumentException((new StringBuilder(51)).append("requirement failed: ").append("V.offset == 0 (").append(left$macro$5).append(" ").append("!=").append(" ").append(0).append(")").toString());
               } else {
                  int left$macro$7 = X.rows();
                  int right$macro$8 = A.cols();
                  if (left$macro$7 != right$macro$8) {
                     throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Wrong number of rows in return value: ").append("X.rows == A.cols (").append(left$macro$7).append(" ").append("!=").append(" ").append(right$macro$8).append(")").toString());
                  } else {
                     int left$macro$9 = X.cols();
                     int right$macro$10 = V.cols();
                     if (left$macro$9 != right$macro$10) {
                        throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Wrong number of rows in return value: ").append("X.cols == V.cols (").append(left$macro$9).append(" ").append("!=").append(" ").append(right$macro$10).append(")").toString());
                     } else {
                        boolean transpose = X.isTranspose();
                        int nrhs = V.cols();
                        DenseMatrix Xtmp = DenseMatrix$.MODULE$.zeros$mFc$sp(scala.math.package..MODULE$.max(A.rows(), A.cols()), nrhs, .MODULE$.Float(), Zero$.MODULE$.FloatZero());
                        int M = !transpose ? A.rows() : A.cols();
                        ((NumericOps)Xtmp.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), M), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows())).$colon$eq(V.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), M), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows()), this.$outer.dm_dm_UpdateOp_Float_OpSet());
                        float[] newData = (float[])A.data$mcF$sp().clone();
                        float[] queryWork = new float[1];
                        intW queryInfo = new intW(0);
                        LAPACK.getInstance().sgels(!transpose ? "N" : "T", A.rows(), A.cols(), nrhs, newData, A.majorStride(), Xtmp.data$mcF$sp(), scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.max(A.rows(), A.cols())), queryWork, -1, queryInfo);
                        int lwork = queryInfo.val != 0 ? scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.min(A.rows(), A.cols()) + scala.math.package..MODULE$.max(scala.math.package..MODULE$.min(A.rows(), A.cols()), nrhs)) : scala.math.package..MODULE$.max((int)queryWork[0], 1);
                        float[] work = new float[lwork];
                        intW info = new intW(0);
                        LAPACK.getInstance().sgels(!transpose ? "N" : "T", A.rows(), A.cols(), nrhs, newData, A.majorStride(), Xtmp.data$mcF$sp(), scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.max(A.rows(), A.cols())), work, work.length, info);
                        if (info.val < 0) {
                           throw new IllegalArgumentException();
                        } else {
                           int N = !transpose ? A.cols() : A.rows();
                           ((NumericOps)X.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), N), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows())).$colon$eq(Xtmp.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), N), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows()), this.$outer.dm_dm_UpdateOp_Float_OpSet());
                           return X;
                        }
                     }
                  }
               }
            }
         }
      }

      public impl_OpSolveMatrixBy_DMF_DMF_eq_DMF$() {
         if (DenseMatrixOps_FloatSpecialized.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixOps_FloatSpecialized.this;
            super();
         }
      }
   }

   public class impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixOps_FloatSpecialized $outer;

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

      public DenseVector apply(final DenseMatrix a, final DenseVector b) {
         DenseMatrix rv = (DenseMatrix)a.$bslash(new DenseMatrix$mcF$sp(b.size(), 1, b.data$mcF$sp(), b.offset(), b.stride(), true), this.$outer.impl_OpSolveMatrixBy_DMF_DMF_eq_DMF());
         return new DenseVector$mcF$sp(rv.data$mcF$sp());
      }

      public impl_OpSolveMatrixBy_DMF_DVF_eq_DVF$() {
         if (DenseMatrixOps_FloatSpecialized.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixOps_FloatSpecialized.this;
            super();
         }
      }
   }
}
