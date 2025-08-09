package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Transpose;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.ScalarOf;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EaaB\u0003\u0007!\u0003\r\t!\u0004\u0005\u00061\u0001!\t!\u0007\u0005\u0006;\u0001!\u0019A\b\u0005\u0006'\u0002!\u0019\u0001\u0016\u0005\u0006m\u0002!\u0019a\u001e\u0002\u0015)J\fgn\u001d9pg\u0016|\u0005o]0M_^\u0004&/[8\u000b\u0005\u001dA\u0011!C8qKJ\fGo\u001c:t\u0015\tI!\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0017\u00051!M]3fu\u0016\u001c\u0001aE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0017\u001b\u00051\u0011BA\f\u0007\u0005U!&/\u00198ta>\u001cXm\u00149t?2{w\u000f\u0015:j_J\na\u0001J5oSR$C#\u0001\u000e\u0011\u0005=Y\u0012B\u0001\u000f\u0011\u0005\u0011)f.\u001b;\u0002?%l\u0007\u000f\\0Pa6+H.T1ue&Dx,\u0016;`)~3'o\\7`)R|V+\u0006\u0004 u!\u0003d*\u0010\u000b\u0005A}R\u0005\u000bE\u0003\"I)JDH\u0004\u0002\u0016E%\u00111EB\u0001\f\u001fBlU\u000f\\'biJL\u00070\u0003\u0002&M\t)\u0011*\u001c9me%\u0011q\u0005\u000b\u0002\u0006+\u001a+hn\u0019\u0006\u0003S)\tqaZ3oKJL7\rE\u0002,Y9j\u0011\u0001C\u0005\u0003[!\u0011\u0011\u0002\u0016:b]N\u0004xn]3\u0011\u0005=\u0002D\u0002\u0001\u0003\u0006c\t\u0011\rA\r\u0002\u0002+F\u00111G\u000e\t\u0003\u001fQJ!!\u000e\t\u0003\u000f9{G\u000f[5oOB\u0011qbN\u0005\u0003qA\u00111!\u00118z!\ty#\bB\u0003<\u0005\t\u0007!GA\u0001U!\tyS\bB\u0003?\u0005\t\u0007!G\u0001\u0002S)\")\u0001I\u0001a\u0002\u0003\u00061AO]1ogR\u0003BAQ#:\u000f6\t1I\u0003\u0002E\u0011\u000591/\u001e9q_J$\u0018B\u0001$D\u00051\u0019\u0015M\u001c+sC:\u001c\bo\\:f!\ty\u0003\nB\u0003J\u0005\t\u0007!G\u0001\u0002U)\")1J\u0001a\u0002\u0019\u0006\u0011q\u000e\u001d\t\u0006C\u0011:e&\u0014\t\u0003_9#Qa\u0014\u0002C\u0002I\u0012\u0011A\u0015\u0005\u0006#\n\u0001\u001dAU\u0001\rG\u0006tGK]1ogB|7/\u001a\t\u0005\u0005\u0016kE(A\u000ej[Bdwl\u00149`)R|6kX3r?J#vL\u001a:p[~#vlU\u000b\b+~#XmY9i)\u00111\u0016N\u001c:\u0011\r][f,\u00193h\u001d\tA\u0016,D\u0001)\u0013\tQ\u0006&A\u0003V\rVt7-\u0003\u0002];\n1Q+S7qYJR!A\u0017\u0015\u0011\u0005=zF!\u00021\u0004\u0005\u0004\u0011$AA(q!\rYCF\u0019\t\u0003_\r$QaO\u0002C\u0002I\u0002\"aL3\u0005\u000b\u0019\u001c!\u0019\u0001\u001a\u0003\u0003Y\u0003\"a\f5\u0005\u000by\u001a!\u0019\u0001\u001a\t\u000b)\u001c\u00019A6\u0002\u0005\u00154\b\u0003\u0002\"mE\u0012L!!\\\"\u0003\u0011M\u001b\u0017\r\\1s\u001f\u001aDQaS\u0002A\u0004=\u0004baV._E\u0012\u0004\bCA\u0018r\t\u0015y5A1\u00013\u0011\u0015\t6\u0001q\u0001t!\u0011\u0011U\t]4\u0005\u000bU\u001c!\u0019\u0001\u001a\u0003\u0003-\u000bQ$[7qY~{\u0005oX%o!2\f7-Z0Ui~\u001bvL\u001a:p[~#vlU\u000b\tqv\fy!!\u0002\u0002\u0002Q)\u00110a\u0002\u0002\fA1qK\u001f?\u007f\u0003\u0007I!a_/\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\u001a\u0011\u0005=jH!\u00021\u0005\u0005\u0004\u0011\u0004cA\u0016-\u007fB\u0019q&!\u0001\u0005\u000bm\"!\u0019\u0001\u001a\u0011\u0007=\n)\u0001B\u0003g\t\t\u0007!\u0007\u0003\u0004k\t\u0001\u000f\u0011\u0011\u0002\t\u0006\u00052|\u00181\u0001\u0005\u0007\u0017\u0012\u0001\u001d!!\u0004\u0011\r]SHp`A\u0002\t\u0015)HA1\u00013\u0001"
)
public interface TransposeOps_LowPrio extends TransposeOps_LowPrio2 {
   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulMatrix_Ut_T_from_Tt_U$(final TransposeOps_LowPrio $this, final CanTranspose transT, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return $this.impl_OpMulMatrix_Ut_T_from_Tt_U(transT, op, canTranspose);
   }

   default UFunc.UImpl2 impl_OpMulMatrix_Ut_T_from_Tt_U(final CanTranspose transT, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return new UFunc.UImpl2(canTranspose, op, transT) {
         private final CanTranspose canTranspose$1;
         private final UFunc.UImpl2 op$1;
         private final CanTranspose transT$1;

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

         public Object apply(final Transpose v, final Object v2) {
            return this.canTranspose$1.apply(this.op$1.apply(this.transT$1.apply(v2), v.inner()));
         }

         public {
            this.canTranspose$1 = canTranspose$1;
            this.op$1 = op$1;
            this.transT$1 = transT$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_Tt_S_eq_RT_from_T_S$(final TransposeOps_LowPrio $this, final ScalarOf ev, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return $this.impl_Op_Tt_S_eq_RT_from_T_S(ev, op, canTranspose);
   }

   default UFunc.UImpl2 impl_Op_Tt_S_eq_RT_from_T_S(final ScalarOf ev, final UFunc.UImpl2 op, final CanTranspose canTranspose) {
      return new UFunc.UImpl2(canTranspose, op) {
         private final CanTranspose canTranspose$2;
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

         public Object apply(final Transpose a, final Object b) {
            return this.canTranspose$2.apply(this.op$2.apply(a.inner(), b));
         }

         public {
            this.canTranspose$2 = canTranspose$2;
            this.op$2 = op$2;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_Tt_S_from_T_S$(final TransposeOps_LowPrio $this, final ScalarOf ev, final UFunc.InPlaceImpl2 op) {
      return $this.impl_Op_InPlace_Tt_S_from_T_S(ev, op);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_Tt_S_from_T_S(final ScalarOf ev, final UFunc.InPlaceImpl2 op) {
      return new UFunc.InPlaceImpl2(op) {
         private final UFunc.InPlaceImpl2 op$3;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Transpose a, final Object b) {
            this.op$3.apply(a.inner(), b);
         }

         public {
            this.op$3 = op$3;
         }
      };
   }

   static void $init$(final TransposeOps_LowPrio $this) {
   }
}
