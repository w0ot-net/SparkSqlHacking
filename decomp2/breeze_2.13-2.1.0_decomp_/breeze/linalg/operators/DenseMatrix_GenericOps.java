package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u0003B\u0001\u0011\r!\tC\u0003Q\u0001\u0011\r\u0011K\u0001\fEK:\u001cX-T1ue&DxlR3oKJL7m\u00149t\u0015\t9\u0001\"A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011\u0011BC\u0001\u0007Y&t\u0017\r\\4\u000b\u0003-\taA\u0019:fKj,7\u0001A\n\u0004\u00019!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\r\u0005\u0002\u0016-5\ta!\u0003\u0002\u0018\r\tIQ*\u0019;sSb|\u0005o]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0001\"aD\u000e\n\u0005q\u0001\"\u0001B+oSR\fQ$[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018#N?R{F)T\u000b\u0003?A\"\"\u0001I\u001d\u0011\u000b\u0005*3FL\u0016\u000f\u0005\t\u001aS\"\u0001\u0005\n\u0005\u0011B\u0011\u0001C:dC2,\u0017\t\u001a3\n\u0005\u0019:#\u0001D%o!2\f7-Z%na2\u001c\u0014B\u0001\u0015*\u0005\u0015)f)\u001e8d\u0015\tQ#\"A\u0004hK:,'/[2\u0011\u0007\tbc&\u0003\u0002.\u0011\tYA)\u001a8tK6\u000bGO]5y!\ty\u0003\u0007\u0004\u0001\u0005\u000bE\u0012!\u0019\u0001\u001a\u0003\u0003Q\u000b\"a\r\u001c\u0011\u0005=!\u0014BA\u001b\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u001c\n\u0005a\u0002\"aA!os\"9!HAA\u0001\u0002\bY\u0014AC3wS\u0012,gnY3%cA\u0019Ah\u0010\u0018\u000e\u0003uR!A\u0010\u0006\u0002\t5\fG\u000f[\u0005\u0003\u0001v\u0012\u0001bU3nSJLgnZ\u0001%S6\u0004HnX(q\u001bVdW*\u0019;sSb|F)T0E\u001b~+\u0017o\u0018#N?\u001e+g.\u001a:jGV\u00111\t\u0014\u000b\u0003\t6\u0003R!\u0012%K\u0015*s!!\u0006$\n\u0005\u001d3\u0011aC(q\u001bVdW*\u0019;sSbL!!S\u0014\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u0007\tb3\n\u0005\u00020\u0019\u0012)\u0011g\u0001b\u0001e!9ajAA\u0001\u0002\by\u0015AC3wS\u0012,gnY3%eA\u0019AhP&\u0002G%l\u0007\u000f\\0Pa6+H.T1ue&Dx\fR'`-~+\u0017o\u0018#W?\u001e+g.\u001a:jGV\u0019!K\u0016-\u0015\u0005M\u000b\u0007#B#I)^s\u0006c\u0001\u0012-+B\u0011qF\u0016\u0003\u0006c\u0011\u0011\rA\r\t\u0003_a#Q!\u0017\u0003C\u0002i\u00131AV3d#\t\u00194\fE\u0002#9VK!!\u0018\u0005\u0003\rY+7\r^8s!\r\u0011s,V\u0005\u0003A\"\u00111\u0002R3og\u00164Vm\u0019;pe\")!\r\u0002a\u0002G\u0006!!/\u001b8h!\rat(\u0016"
)
public interface DenseMatrix_GenericOps extends MatrixOps {
   // $FF: synthetic method
   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DM_T_DM$(final DenseMatrix_GenericOps $this, final Semiring evidence$1) {
      return $this.impl_scaleAdd_InPlace_DM_T_DM(evidence$1);
   }

   default UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DM_T_DM(final Semiring evidence$1) {
      return new UFunc.InPlaceImpl3(evidence$1) {
         private final Semiring evidence$1$1;

         public void apply(final DenseMatrix a, final Object s, final DenseMatrix b) {
            Semiring ring = (Semiring).MODULE$.implicitly(this.evidence$1$1);
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(78)).append("requirement failed: Vector row dimensions must match!: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(78)).append("requirement failed: Vector col dimensions must match!: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = a.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = a.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> a.update(i, j, ring.$plus(a.apply(i, j), ring.$times(s, b.apply(i, j))))).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

               }
            }
         }

         public {
            this.evidence$1$1 = evidence$1$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Generic$(final DenseMatrix_GenericOps $this, final Semiring evidence$2) {
      return $this.impl_OpMulMatrix_DM_DM_eq_DM_Generic(evidence$2);
   }

   default UFunc.UImpl2 impl_OpMulMatrix_DM_DM_eq_DM_Generic(final Semiring evidence$2) {
      return new UFunc.UImpl2(evidence$2) {
         private final Semiring ring;

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

         private Semiring ring() {
            return this.ring;
         }

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
            Semiring r = this.ring();
            Zero z = Zero$.MODULE$.zeroFromSemiring(r);
            DenseMatrix res = DenseMatrix$.MODULE$.zeros(a.rows(), b.cols(), ct, z);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int colsB = b.cols();
               int colsA = a.cols();
               int rowsA = a.rows();

               for(int j = 0; j < colsB; ++j) {
                  for(int l = 0; l < colsA; ++l) {
                     Object v = b.apply(l, j);

                     for(int i = 0; i < rowsA; ++i) {
                        res.update(i, j, this.ring().$plus(res.apply(i, j), this.ring().$times(a.apply(i, l), v)));
                     }
                  }
               }

               return res;
            }
         }

         public {
            this.ring = (Semiring).MODULE$.implicitly(evidence$2$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulMatrix_DM_V_eq_DV_Generic$(final DenseMatrix_GenericOps $this, final Semiring ring) {
      return $this.impl_OpMulMatrix_DM_V_eq_DV_Generic(ring);
   }

   default UFunc.UImpl2 impl_OpMulMatrix_DM_V_eq_DV_Generic(final Semiring ring) {
      return (a, b) -> {
         ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
         int left$macro$1 = a.cols();
         int right$macro$2 = b.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(45)).append("requirement failed: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseVector res = DenseVector$.MODULE$.zeros(a.rows(), ct, Zero$.MODULE$.zeroFromSemiring(ring));

            for(int c = 0; c < a.cols(); ++c) {
               for(int r = 0; r < a.rows(); ++r) {
                  Object v = a.apply(r, c);
                  res.update(r, ring.$plus(res.apply(r), ring.$times(v, b.apply(BoxesRunTime.boxToInteger(c)))));
               }
            }

            return res;
         }
      };
   }

   static void $init$(final DenseMatrix_GenericOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
