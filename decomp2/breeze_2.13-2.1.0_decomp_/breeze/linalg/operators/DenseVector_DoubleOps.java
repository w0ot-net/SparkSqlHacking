package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVectorDeps$;
import breeze.linalg.package$;
import breeze.linalg.support.CanCopy;
import dev.ludovic.netlib.blas.BLAS;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dba\u0002\t\u0012!\u0003\r\t\u0001\u0007\u0005\u0006G\u0001!\t\u0001\n\u0005\bQ\u0001\u0011\r\u0011b\u0001*\u000f\u0015Y\u0004\u0001c\u0001=\r\u0015q\u0004\u0001#\u0001@\u0011\u0015\u0011F\u0001\"\u0001T\u0011\u0015!F\u0001\"\u0001V\u0011\u001da\u0006A1A\u0005\u0004uCq!\u0019\u0001C\u0002\u0013\r!\rC\u0004h\u0001\t\u0007I1\u00015\b\u000b)\u0004\u00012A6\u0007\u000b1\u0004\u0001\u0012A7\t\u000bI[A\u0011\u0001:\t\u000bQ[A\u0011A:\t\u000b]\\A\u0011\u0002=\t\u000bm\u0004A1\u0001?\u0003+\u0011+gn]3WK\u000e$xN]0E_V\u0014G.Z(qg*\u0011!cE\u0001\n_B,'/\u0019;peNT!\u0001F\u000b\u0002\r1Lg.\u00197h\u0015\u00051\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\u0005j\u0011!E\u0005\u0003EE\u0011A\u0003R3og\u00164Vm\u0019;pe\u0016C\b/\u00198e\u001fB\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001&!\tQb%\u0003\u0002(7\t!QK\\5u\u0003}IW\u000e\u001d7`\u001fB\fE\rZ0J]Bc\u0017mY3`\tZ{FIV0E_V\u0014G.Z\u000b\u0002UA!1F\f\u001b5\u001d\t\u0001C&\u0003\u0002.#\u0005)q\n]!eI&\u0011q\u0006\r\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HNM\u0005\u0003cI\u0012Q!\u0016$v]\u000eT!aM\u000b\u0002\u000f\u001d,g.\u001a:jGB\u0019QG\u000e\u001d\u000e\u0003MI!aN\n\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u00035eJ!AO\u000e\u0003\r\u0011{WO\u00197f\u0003\u0011JW\u000e\u001d7`g\u000e\fG.Z!eI~Ke\u000e\u00157bG\u0016|FIV0U?\u00123v\fR8vE2,\u0007CA\u001f\u0005\u001b\u0005\u0001!\u0001J5na2|6oY1mK\u0006#GmX%o!2\f7-Z0E-~#v\f\u0012,`\t>,(\r\\3\u0014\t\u0011I\u0002I\u0012\t\u0006\u0003\u0012#\u0004\b\u000e\b\u0003k\tK!aQ\n\u0002\u0011M\u001c\u0017\r\\3BI\u0012L!!\u0012\u0019\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0011\u0005\u001d{eB\u0001%N\u001d\tIE*D\u0001K\u0015\tYu#\u0001\u0004=e>|GOP\u0005\u00029%\u0011ajG\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0016K\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002O7\u00051A(\u001b8jiz\"\u0012\u0001P\u0001\u0006CB\u0004H.\u001f\u000b\u0005KYC&\fC\u0003X\r\u0001\u0007A'A\u0001z\u0011\u0015If\u00011\u00019\u0003\u0005\t\u0007\"B.\u0007\u0001\u0004!\u0014!\u0001=\u0002;%l\u0007\u000f\\0Pa\u0006#Gm\u0018#W?\u00123v,Z9`\tZ{Fi\\;cY\u0016,\u0012A\u0018\t\u0006W}#D\u0007N\u0005\u0003AB\u0012Q!S7qYJ\nq$[7qY~{\u0005oU;c?&s\u0007\u000b\\1dK~#ek\u0018#W?\u0012{WO\u00197f+\u0005\u0019\u0007\u0003\u00023/iQr!\u0001I3\n\u0005\u0019\f\u0012!B(q'V\u0014\u0017!H5na2|v\n]*vE~#ek\u0018#W?\u0016\fx\f\u0012,`\t>,(\r\\3\u0016\u0003%\u0004R\u0001Z05iQ\nqaY1o\t>$H\t\u0005\u0002>\u0017\t91-\u00198E_R$5cA\u0006\u001a]B)qn\u0018\u001b5q9\u0011\u0001\u0005]\u0005\u0003cF\t!b\u00149Nk2LeN\\3s)\u0005YGc\u0001\u001duk\")\u0011,\u0004a\u0001i!)a/\u0004a\u0001i\u0005\t!-\u0001\u0005cY\u0006\u001c\b+\u0019;i)\rA\u0014P\u001f\u0005\u00063:\u0001\r\u0001\u000e\u0005\u0006m:\u0001\r\u0001N\u0001\u0011S6\u0004Hn\u00183j[~#ekX3r?&+2!`A\b+\u0005q\bcB@\u0002\u0006\u0005%\u0011\u0011\u0005\b\u0004k\u0005\u0005\u0011bAA\u0002'\u0005\u0019A-[7\n\u0007\u0005\u001d\u0001G\u0001\u0003J[Bd\u0007\u0003B\u001b7\u0003\u0017\u0001B!!\u0004\u0002\u00101\u0001AaBA\t\u001f\t\u0007\u00111\u0003\u0002\u0002\u000bF!\u0011QCA\u000e!\rQ\u0012qC\u0005\u0004\u00033Y\"a\u0002(pi\"Lgn\u001a\t\u00045\u0005u\u0011bAA\u00107\t\u0019\u0011I\\=\u0011\u0007i\t\u0019#C\u0002\u0002&m\u00111!\u00138u\u0001"
)
public interface DenseVector_DoubleOps extends DenseVectorExpandOps {
   impl_scaleAdd_InPlace_DV_T_DV_Double$ impl_scaleAdd_InPlace_DV_T_DV_Double();

   canDotD$ canDotD();

   void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpAdd_InPlace_DV_DV_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpAdd_DV_DV_eq_DV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpSub_InPlace_DV_DV_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpSub_DV_DV_eq_DV_Double_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Double();

   UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Double();

   UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Double();

   UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Double();

   // $FF: synthetic method
   static UFunc.UImpl impl_dim_DV_eq_I$(final DenseVector_DoubleOps $this) {
      return $this.impl_dim_DV_eq_I();
   }

   default UFunc.UImpl impl_dim_DV_eq_I() {
      return new UFunc.UImpl() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final DenseVector_DoubleOps $outer;

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

         public final int apply(final DenseVector v) {
            return v.length();
         }

         public {
            if (DenseVector_DoubleOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseVector_DoubleOps.this;
            }
         }
      };
   }

   static void $init$(final DenseVector_DoubleOps $this) {
      $this.breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpAdd_InPlace_DV_DV_Double_$eq(new UFunc.InPlaceImpl2() {
         // $FF: synthetic field
         private final DenseVector_DoubleOps $outer;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            this.$outer.impl_scaleAdd_InPlace_DV_T_DV_Double().apply(a, (double)1.0F, b);
         }

         public {
            if (DenseVector_DoubleOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseVector_DoubleOps.this;
               ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_DoubleOps.this.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            }
         }
      });
      ((TernaryUpdateRegistry).MODULE$.implicitly($this.impl_scaleAdd_InPlace_V_S_V_Double())).register($this.impl_scaleAdd_InPlace_DV_T_DV_Double(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpAdd_DV_DV_eq_DV_Double_$eq($this.pureFromUpdate((UFunc.InPlaceImpl2).MODULE$.implicitly($this.impl_OpAdd_InPlace_DV_DV_Double()), (CanCopy).MODULE$.implicitly(DenseVectorDeps$.MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double()))));
      ((BinaryRegistry).MODULE$.implicitly($this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register($this.impl_OpAdd_DV_DV_eq_DV_Double(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpSub_InPlace_DV_DV_Double_$eq(new UFunc.InPlaceImpl2() {
         // $FF: synthetic field
         private final DenseVector_DoubleOps $outer;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            this.$outer.impl_scaleAdd_InPlace_DV_T_DV_Double().apply(a, (double)-1.0F, b);
         }

         public {
            if (DenseVector_DoubleOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseVector_DoubleOps.this;
               ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_DoubleOps.this.impl_Op_InPlace_V_V_Idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$DenseVector_DoubleOps$_setter_$impl_OpSub_DV_DV_eq_DV_Double_$eq($this.pureFromUpdate((UFunc.InPlaceImpl2).MODULE$.implicitly($this.impl_OpSub_InPlace_DV_DV_Double()), (CanCopy).MODULE$.implicitly(DenseVectorDeps$.MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double()))));
      ((BinaryRegistry).MODULE$.implicitly($this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register($this.impl_OpSub_DV_DV_eq_DV_Double(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      ((BinaryRegistry).MODULE$.implicitly($this.impl_OpMulInner_V_V_eq_S_Double())).register($this.canDotD(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
   }

   public class impl_scaleAdd_InPlace_DV_T_DV_Double$ implements UFunc.InPlaceImpl3 {
      public void apply(final DenseVector y, final double a, final DenseVector x) {
         while(true) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(49)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            }

            if (!y.overlaps$mcD$sp(x)) {
               if (x.noOffsetOrStride() && y.noOffsetOrStride()) {
                  double[] ad = x.data$mcD$sp();
                  double[] bd = y.data$mcD$sp();
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = x.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     bd[index$macro$4] += ad[index$macro$4] * a;
                  }

                  BoxedUnit var14 = BoxedUnit.UNIT;
               } else {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = x.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     y.update$mcD$sp(index$macro$9, y.apply$mcD$sp(index$macro$9) + x.apply$mcD$sp(index$macro$9) * a);
                  }

                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               return;
            }

            x = x.copy$mcD$sp();
            a = a;
            y = y;
         }
      }
   }

   public class canDotD$ implements UFunc.UImpl2 {
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

      public double apply(final DenseVector a, final DenseVector b) {
         int left$macro$1 = a.length();
         int right$macro$2 = b.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(49)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            return a.noOffsetOrStride() && b.noOffsetOrStride() && a.length() < 8 ? DenseVectorSupportMethods.smallDotProduct_Double(a.data$mcD$sp(), b.data$mcD$sp(), a.length()) : this.blasPath(a, b);
         }
      }

      private double blasPath(final DenseVector a, final DenseVector b) {
         double var10000;
         if ((a.length() <= 300 || !package$.MODULE$.usingNatives()) && a.stride() == 1 && b.stride() == 1) {
            var10000 = DenseVectorSupportMethods.dotProduct_Double(a.data$mcD$sp(), a.offset(), b.data$mcD$sp(), b.offset(), a.length());
         } else {
            int boff = b.stride() >= 0 ? b.offset() : b.offset() + b.stride() * (b.length() - 1);
            int aoff = a.stride() >= 0 ? a.offset() : a.offset() + a.stride() * (a.length() - 1);
            var10000 = BLAS.getInstance().ddot(a.length(), b.data$mcD$sp(), boff, b.stride(), a.data$mcD$sp(), aoff, a.stride());
         }

         return var10000;
      }
   }
}
