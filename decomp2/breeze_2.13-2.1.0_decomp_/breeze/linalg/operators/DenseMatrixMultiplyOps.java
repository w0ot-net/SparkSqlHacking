package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseMatrix$mcD$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.linalg.MatrixSingularException;
import breeze.linalg.MatrixSingularException$;
import breeze.linalg.NumericOps;
import breeze.linalg.TensorLike;
import breeze.linalg.Transpose;
import breeze.storage.Zero$;
import dev.ludovic.netlib.blas.BLAS;
import dev.ludovic.netlib.lapack.LAPACK;
import org.netlib.util.intW;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-da\u0002\r\u001a!\u0003\r\t\u0001\t\u0005\u0006c\u0001!\tA\r\u0005\u0006m\u0001!\u0019a\u000e\u0005\u00067\u0002!\u0019\u0001\u0018\u0005\u0006K\u0002!IAZ\u0004\u0006q\u0002A\u0019!\u001f\u0004\u0006w\u0002A\t\u0001 \u0005\u0006}\u001a!\ta \u0005\b\u0003\u00031A\u0011AA\u0002\u000f\u001d\ti\u0001\u0001E\u0002\u0003\u001f1q!!\u0005\u0001\u0011\u0003\t\u0019\u0002\u0003\u0004\u007f\u0015\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003\u0003QA\u0011AA\u000e\u0011%\t\u0019\u0003\u0001b\u0001\n\u0007\t)cB\u0004\u0002*\u0001A\u0019!a\u000b\u0007\u000f\u00055\u0002\u0001#\u0001\u00020!1ap\u0004C\u0001\u0003sAq!!\u0001\u0010\t\u0003\nY\u0004C\u0004\u0002F=!\t!a\u0012\t\u000f\u0005=s\u0002\"\u0001\u0002R\u001d9\u0011\u0011\f\u0001\t\u0004\u0005mcaBA/\u0001!\u0005\u0011q\f\u0005\u0007}V!\t!a\u0019\t\u000f\u0005\u0005Q\u0003\"\u0011\u0002f\t1B)\u001a8tK6\u000bGO]5y\u001bVdG/\u001b9ms>\u00038O\u0003\u0002\u001b7\u0005Iq\u000e]3sCR|'o\u001d\u0006\u00039u\ta\u0001\\5oC2<'\"\u0001\u0010\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019R\u0001A\u0011(W9\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u0015*\u001b\u0005I\u0012B\u0001\u0016\u001a\u0005Y!UM\\:f\u001b\u0006$(/\u001b=FqB\fg\u000eZ3e\u001fB\u001c\bC\u0001\u0015-\u0013\ti\u0013D\u0001\nEK:\u001cX-T1ue&DX*\u001e7u\u001fB\u001c\bC\u0001\u00150\u0013\t\u0001\u0014D\u0001\nEK:\u001cX-T1ue&DxlU3u\u001fB\u001c\u0018A\u0002\u0013j]&$H\u0005F\u00014!\t\u0011C'\u0003\u00026G\t!QK\\5u\u0003\u0001JW\u000e\u001d7`\u001fBlU\u000f\\'biJL\u0007p\u0018#W)R|F)\u0014+`KF|F)\u0014+\u0016\u0005abECA\u001dY!\u0015QThQ+D\u001d\tA3(\u0003\u0002=3\u0005Yq\n]'vY6\u000bGO]5y\u0013\tqtHA\u0003J[Bd''\u0003\u0002A\u0003\n)QKR;oG*\u0011!)H\u0001\bO\u0016tWM]5d!\r!UiR\u0007\u00027%\u0011ai\u0007\u0002\n)J\fgn\u001d9pg\u0016\u00042\u0001\u0012%K\u0013\tI5DA\u0006EK:\u001cXMV3di>\u0014\bCA&M\u0019\u0001!Q!\u0014\u0002C\u00029\u0013\u0011\u0001V\t\u0003\u001fJ\u0003\"A\t)\n\u0005E\u001b#a\u0002(pi\"Lgn\u001a\t\u0003EMK!\u0001V\u0012\u0003\u0007\u0005s\u0017\u0010E\u0002E-*K!aV\u000e\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\u0005\u00063\n\u0001\u001dAW\u0001\u0003_B\u0004RAO\u001fV+V\u000bq$[7qY~{\u0005/T;m\u001b\u0006$(/\u001b=`\tZ#v\fR'U?\u0016\fx\fR'U+\ti\u0016\r\u0006\u0002_GB)!(P0cEB\u0019A\t\u00131\u0011\u0005-\u000bG!B'\u0004\u0005\u0004q\u0005c\u0001#WA\")\u0011l\u0001a\u0002IB)!(\u00102cE\u0006yAO]1ogB|7/Z*ue&tw\r\u0006\u0002heB\u0011\u0001n\u001c\b\u0003S6\u0004\"A[\u0012\u000e\u0003-T!\u0001\\\u0010\u0002\rq\u0012xn\u001c;?\u0013\tq7%\u0001\u0004Qe\u0016$WMZ\u0005\u0003aF\u0014aa\u0015;sS:<'B\u00018$\u0011\u0015\u0019H\u00011\u0001u\u0003\u0005\t\u0007c\u0001#WkB\u0011!E^\u0005\u0003o\u000e\u0012a\u0001R8vE2,\u0017aH5na2|v\n]'vY6\u000bGO]5y?\u0012kEi\u0018#N\t~+\u0017o\u0018#N\tB\u0011!PB\u0007\u0002\u0001\ty\u0012.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0E\u001b\u0012{F)\u0014#`KF|F)\u0014#\u0014\u0007\u0019\tS\u0010E\u0003;{Q$H/\u0001\u0004=S:LGO\u0010\u000b\u0002s\u0006)\u0011\r\u001d9msR)A/!\u0002\u0002\n!1\u0011q\u0001\u0005A\u0002Q\f!aX1\t\r\u0005-\u0001\u00021\u0001u\u0003\ty&-A\u0010j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#U\nR0E-\u0012{V-]0E-\u0012\u0003\"A\u001f\u0006\u0003?%l\u0007\u000f\\0Pa6+H.T1ue&Dx\fR'E?\u00123FiX3r?\u00123Fi\u0005\u0003\u000bC\u0005U\u0001c\u0002\u001e>i\u0006]\u0011q\u0003\t\u0004\t\"+HCAA\b)\u0019\t9\"!\b\u0002 !)1\u000f\u0004a\u0001i\"9\u0011\u0011\u0005\u0007A\u0002\u0005]\u0011!\u00012\u0002?%l\u0007\u000f\\0Pa6+H.T1ue&Dx\f\u0012,E?\u0012kEiX3r?\u0012kE)\u0006\u0002\u0002(A1!(PA\fiR\f1%[7qY~{\u0005oU8mm\u0016l\u0015\r\u001e:jq\nKx\fR'E?\u0012kEiX3r?\u0012kE\t\u0005\u0002{\u001f\t\u0019\u0013.\u001c9m?>\u00038k\u001c7wK6\u000bGO]5y\u0005f|F)\u0014#`\t6#u,Z9`\t6#5\u0003B\b\"\u0003c\u0001b!a\r>iR$hb\u0001\u0015\u00026%\u0019\u0011qG\r\u0002\u001f=\u00038k\u001c7wK6\u000bGO]5y\u0005f$\"!a\u000b\u0015\u000bQ\fi$!\u0011\t\r\u0005}\u0012\u00031\u0001u\u0003\u0005\t\u0005BBA\"#\u0001\u0007A/A\u0001W\u0003\u001daUkU8mm\u0016$R\u0001^A%\u0003\u001bBa!a\u0013\u0013\u0001\u0004!\u0018!\u0001-\t\r\u0005}\"\u00031\u0001u\u0003\u001d\t&kU8mm\u0016$r\u0001^A*\u0003+\n9\u0006\u0003\u0004\u0002LM\u0001\r\u0001\u001e\u0005\u0007\u0003\u007f\u0019\u0002\u0019\u0001;\t\r\u0005\r3\u00031\u0001u\u0003\rJW\u000e\u001d7`\u001fB\u001cv\u000e\u001c<f\u001b\u0006$(/\u001b=Cs~#U\nR0E-\u0012{V-]0E-\u0012\u0003\"A_\u000b\u0003G%l\u0007\u000f\\0PaN{GN^3NCR\u0014\u0018\u000e\u001f\"z?\u0012kEi\u0018#W\t~+\u0017o\u0018#W\tN!Q#IA1!!\t\u0019$\u0010;\u0002\u0018\u0005]ACAA.)\u0019\t9\"a\u001a\u0002j!)1o\u0006a\u0001i\"9\u0011\u0011E\fA\u0002\u0005]\u0001"
)
public interface DenseMatrixMultiplyOps extends DenseMatrixMultOps, DenseMatrix_SetOps {
   impl_OpMulMatrix_DMD_DMD_eq_DMD$ impl_OpMulMatrix_DMD_DMD_eq_DMD();

   impl_OpMulMatrix_DMD_DVD_eq_DVD$ impl_OpMulMatrix_DMD_DVD_eq_DVD();

   impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$ impl_OpSolveMatrixBy_DMD_DMD_eq_DMD();

   impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$ impl_OpSolveMatrixBy_DMD_DVD_eq_DVD();

   void breeze$linalg$operators$DenseMatrixMultiplyOps$_setter_$impl_OpMulMatrix_DVD_DMD_eq_DMD_$eq(final UFunc.UImpl2 x$1);

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulMatrix_DVTt_DMT_eq_DMT$(final DenseMatrixMultiplyOps $this, final UFunc.UImpl2 op) {
      return $this.impl_OpMulMatrix_DVTt_DMT_eq_DMT(op);
   }

   default UFunc.UImpl2 impl_OpMulMatrix_DVTt_DMT_eq_DMT(final UFunc.UImpl2 op) {
      return new UFunc.UImpl2(op) {
         // $FF: synthetic field
         private final DenseMatrixMultiplyOps $outer;
         private final UFunc.UImpl2 op$1;

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

         public Transpose apply(final Transpose v, final DenseMatrix v2) {
            return (Transpose)((TensorLike)((DenseVector)v.inner()).asDenseMatrix().$times(v2, this.op$1)).apply(BoxesRunTime.boxToInteger(0), .MODULE$.$colon$colon(), this.$outer.canSliceRow());
         }

         public {
            if (DenseMatrixMultiplyOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixMultiplyOps.this;
               this.op$1 = op$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulMatrix_DVT_DMT_eq_DMT$(final DenseMatrixMultiplyOps $this, final UFunc.UImpl2 op) {
      return $this.impl_OpMulMatrix_DVT_DMT_eq_DMT(op);
   }

   default UFunc.UImpl2 impl_OpMulMatrix_DVT_DMT_eq_DMT(final UFunc.UImpl2 op) {
      return new UFunc.UImpl2(op) {
         private final UFunc.UImpl2 op$2;

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

         public DenseMatrix apply(final DenseVector v, final DenseMatrix v2) {
            int left$macro$1 = v2.rows();
            int right$macro$2 = 1;
            if (left$macro$1 != 1) {
               throw new IllegalArgumentException((new StringBuilder(50)).append("requirement failed: ").append("v2.rows == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
            } else {
               return (DenseMatrix)this.op$2.apply(v.asDenseMatrix().t(HasOps$.MODULE$.canTranspose_DM()), v2);
            }
         }

         public {
            this.op$2 = op$2;
         }
      };
   }

   // $FF: synthetic method
   static String breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString$(final DenseMatrixMultiplyOps $this, final DenseMatrix a) {
      return $this.breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString(a);
   }

   default String breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString(final DenseMatrix a) {
      return a.isTranspose() ? "T" : "N";
   }

   UFunc.UImpl2 impl_OpMulMatrix_DVD_DMD_eq_DMD();

   static void $init$(final DenseMatrixMultiplyOps $this) {
      $this.breeze$linalg$operators$DenseMatrixMultiplyOps$_setter_$impl_OpMulMatrix_DVD_DMD_eq_DMD_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final DenseMatrixMultiplyOps $outer;

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
               DenseMatrix rv = DenseMatrix$.MODULE$.zeros$mDc$sp(a.length(), b.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               BLAS.getInstance().dgemm("T", this.$outer.breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString(b), rv.rows(), rv.cols(), 1, (double)1.0F, a.data$mcD$sp(), a.offset(), a.stride(), b.data$mcD$sp(), b.offset(), b.majorStride(), (double)0.0F, rv.data$mcD$sp(), 0, rv.rows());
               return rv;
            }
         }

         public {
            if (DenseMatrixMultiplyOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixMultiplyOps.this;
            }
         }
      });
   }

   public class impl_OpMulMatrix_DMD_DMD_eq_DMD$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixMultiplyOps $outer;

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

      public DenseMatrix apply(final DenseMatrix _a, final DenseMatrix _b) {
         int left$macro$1 = _a.cols();
         int right$macro$2 = _b.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension mismatch!: ").append("_a.cols == _b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseMatrix rv = DenseMatrix$.MODULE$.zeros$mDc$sp(_a.rows(), _b.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            if (_a.rows() != 0 && _b.rows() != 0 && _a.cols() != 0 && _b.cols() != 0) {
               DenseMatrix a = _a.majorStride() < scala.math.package..MODULE$.max(_a.isTranspose() ? _a.cols() : _a.rows(), 1) ? _a.copy$mcD$sp() : _a;
               DenseMatrix b = _b.majorStride() < scala.math.package..MODULE$.max(_b.isTranspose() ? _b.cols() : _b.rows(), 1) ? _b.copy$mcD$sp() : _b;
               BLAS.getInstance().dgemm(this.$outer.breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString(a), this.$outer.breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString(b), rv.rows(), rv.cols(), a.cols(), (double)1.0F, a.data$mcD$sp(), a.offset(), a.majorStride(), b.data$mcD$sp(), b.offset(), b.majorStride(), (double)0.0F, rv.data$mcD$sp(), 0, rv.rows());
               return rv;
            } else {
               return rv;
            }
         }
      }

      public impl_OpMulMatrix_DMD_DMD_eq_DMD$() {
         if (DenseMatrixMultiplyOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixMultiplyOps.this;
            super();
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultiplyOps.this.op_M_M_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseMatrixMultiplyOps.this.impl_OpMulMatrix_DM_M_eq_DM_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         }
      }
   }

   public class impl_OpMulMatrix_DMD_DVD_eq_DVD$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixMultiplyOps $outer;

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
         } else if (a.rows() != 0 && a.cols() != 0) {
            DenseVector rv = DenseVector$.MODULE$.zeros$mDc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            BLAS.getInstance().dgemv(this.$outer.breeze$linalg$operators$DenseMatrixMultiplyOps$$transposeString(a), a.isTranspose() ? a.cols() : a.rows(), a.isTranspose() ? a.rows() : a.cols(), (double)1.0F, a.data$mcD$sp(), a.offset(), a.majorStride(), b.data$mcD$sp(), b.offset(), b.stride(), (double)0.0F, rv.data$mcD$sp(), rv.offset(), rv.stride());
            return rv;
         } else {
            return DenseVector$.MODULE$.zeros$mDc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         }
      }

      public impl_OpMulMatrix_DMD_DVD_eq_DVD$() {
         if (DenseMatrixMultiplyOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixMultiplyOps.this;
            super();
         }
      }
   }

   public class impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixMultiplyOps $outer;

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
               var10000 = DenseMatrix$.MODULE$.zeros$mDc$sp(0, 0, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            } else if (A.rows() == A.cols()) {
               DenseMatrix X = DenseMatrix$.MODULE$.zeros$mDc$sp(V.rows(), V.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               X.$colon$eq(V, this.$outer.dm_dm_UpdateOp_Double_OpSet());
               this.LUSolve(X, A);
               var10000 = X;
            } else {
               DenseMatrix X = DenseMatrix$.MODULE$.zeros$mDc$sp(A.cols(), V.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               this.QRSolve(X, A, V);
               var10000 = X;
            }

            return var10000;
         }
      }

      public DenseMatrix LUSolve(final DenseMatrix X, final DenseMatrix A) {
         int[] piv = new int[A.rows()];
         DenseMatrix newA = A.copy$mcD$sp();
         boolean cond$macro$1 = !newA.isTranspose();
         if (!cond$macro$1) {
            throw new AssertionError("assertion failed: newA.isTranspose.unary_!");
         } else {
            intW info = new intW(0);
            LAPACK.getInstance().dgesv(A.rows(), X.cols(), newA.data$mcD$sp(), newA.offset(), newA.majorStride(), piv, 0, X.data$mcD$sp(), X.offset(), X.majorStride(), info);
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
                        boolean transpose = A.isTranspose();
                        int nrhs = V.cols();
                        DenseMatrix Xtmp = DenseMatrix$.MODULE$.zeros$mDc$sp(scala.math.package..MODULE$.max(A.rows(), A.cols()), nrhs, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                        ((NumericOps)Xtmp.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), A.rows()), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows())).$colon$eq(V.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), A.rows()), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows()), this.$outer.dm_dm_UpdateOp_Double_OpSet());
                        double[] newData = (double[])A.data$mcD$sp().clone();
                        double[] queryWork = new double[1];
                        intW queryInfo = new intW(0);
                        LAPACK.getInstance().dgels(!transpose ? "N" : "T", !transpose ? A.rows() : A.cols(), !transpose ? A.cols() : A.rows(), nrhs, newData, A.majorStride(), Xtmp.data$mcD$sp(), scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.max(A.rows(), A.cols())), queryWork, -1, queryInfo);
                        int lwork = queryInfo.val != 0 ? scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.min(A.rows(), A.cols()) + scala.math.package..MODULE$.max(scala.math.package..MODULE$.min(A.rows(), A.cols()), nrhs)) : scala.math.package..MODULE$.max((int)queryWork[0], 1);
                        double[] work = new double[lwork];
                        intW info = new intW(0);
                        LAPACK.getInstance().dgels(!transpose ? "N" : "T", !transpose ? A.rows() : A.cols(), !transpose ? A.cols() : A.rows(), nrhs, newData, A.majorStride(), Xtmp.data$mcD$sp(), scala.math.package..MODULE$.max(1, scala.math.package..MODULE$.max(A.rows(), A.cols())), work, work.length, info);
                        if (info.val < 0) {
                           throw new IllegalArgumentException();
                        } else {
                           X.$colon$eq(Xtmp.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), X.rows()), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nrhs), this.$outer.canSliceColsAndRows()), this.$outer.dm_dm_UpdateOp_Double_OpSet());
                           return X;
                        }
                     }
                  }
               }
            }
         }
      }

      public impl_OpSolveMatrixBy_DMD_DMD_eq_DMD$() {
         if (DenseMatrixMultiplyOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixMultiplyOps.this;
            super();
         }
      }
   }

   public class impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$ implements UFunc.UImpl2 {
      // $FF: synthetic field
      private final DenseMatrixMultiplyOps $outer;

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
         DenseMatrix rv = (DenseMatrix)a.$bslash(new DenseMatrix$mcD$sp(b.size(), 1, b.data$mcD$sp(), b.offset(), b.stride(), true), this.$outer.impl_OpSolveMatrixBy_DMD_DMD_eq_DMD());
         return new DenseVector$mcD$sp(rv.data$mcD$sp());
      }

      public impl_OpSolveMatrixBy_DMD_DVD_eq_DVD$() {
         if (DenseMatrixMultiplyOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixMultiplyOps.this;
            super();
         }
      }
   }
}
