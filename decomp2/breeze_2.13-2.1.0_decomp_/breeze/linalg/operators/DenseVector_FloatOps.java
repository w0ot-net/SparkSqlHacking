package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVectorDeps$;
import breeze.linalg.package$;
import breeze.linalg.scaleAdd$;
import breeze.linalg.support.CanCopy;
import dev.ludovic.netlib.blas.BLAS;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]4q\u0001D\u0007\u0011\u0002\u0007\u0005A\u0003C\u0003 \u0001\u0011\u0005\u0001eB\u0003%\u0001!\rQEB\u0003(\u0001!\u0005\u0001\u0006C\u0003G\u0007\u0011\u0005q\tC\u0003I\u0007\u0011\u0005\u0011\nC\u0003Q\u0007\u0011%\u0011\u000bC\u0004V\u0001\t\u0007I1\u0001,\t\u000fu\u0003!\u0019!C\u0002=\"9!\r\u0001b\u0001\n\u0007\u0019\u0007b\u00025\u0001\u0005\u0004%\u0019!\u001b\u0005\bW\u0002\u0011\r\u0011b\u0001m\u0005Q!UM\\:f-\u0016\u001cGo\u001c:`\r2|\u0017\r^(qg*\u0011abD\u0001\n_B,'/\u0019;peNT!\u0001E\t\u0002\r1Lg.\u00197h\u0015\u0005\u0011\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001)2\u0004\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VM\u001a\t\u00039ui\u0011!D\u0005\u0003=5\u0011A\u0003R3og\u00164Vm\u0019;pe\u0016C\b/\u00198e\u001fB\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001\"!\t1\"%\u0003\u0002$/\t!QK\\5u\u0003\u0011JW\u000e\u001d7`g\u000e\fG.\u001a3BI\u0012|\u0016J\u001c)mC\u000e,w\f\u0012,`'~#ek\u0018$m_\u0006$\bC\u0001\u0014\u0004\u001b\u0005\u0001!\u0001J5na2|6oY1mK\u0012\fE\rZ0J]Bc\u0017mY3`\tZ{6k\u0018#W?\u001acw.\u0019;\u0014\t\r)\u0012F\u000f\t\u0006U9\"t\u0007\u000e\b\u0003W1j\u0011aD\u0005\u0003[=\t\u0001b]2bY\u0016\fE\rZ\u0005\u0003_A\u0012A\"\u00138QY\u0006\u001cW-S7qYNJ!!\r\u001a\u0003\u000bU3UO\\2\u000b\u0005M\n\u0012aB4f]\u0016\u0014\u0018n\u0019\t\u0004WU:\u0014B\u0001\u001c\u0010\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005YA\u0014BA\u001d\u0018\u0005\u00151En\\1u!\tY4I\u0004\u0002=\u0003:\u0011Q\bQ\u0007\u0002})\u0011qhE\u0001\u0007yI|w\u000e\u001e \n\u0003aI!AQ\f\u0002\u000fA\f7m[1hK&\u0011A)\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0005^\ta\u0001P5oSRtD#A\u0013\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005REJ\u0014\u0005\u0006\u0017\u0016\u0001\r\u0001N\u0001\u0002s\")Q*\u0002a\u0001o\u0005\t\u0011\rC\u0003P\u000b\u0001\u0007A'A\u0001y\u0003!\u0019Hn\\<QCRDG\u0003B\u0011S'RCQa\u0013\u0004A\u0002QBQ!\u0014\u0004A\u0002]BQa\u0014\u0004A\u0002Q\na$[7qY~{\u0005/\u00113e?&s\u0007\u000b\\1dK~#ek\u0018#W?\u001acw.\u0019;\u0016\u0003]\u0003B\u0001W.5i9\u0011A$W\u0005\u000356\tQa\u00149BI\u0012L!\u0001\u0018\u0019\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\u001a\u00029%l\u0007\u000f\\0Pa\u0006#Gm\u0018#W?\u00123v,Z9`\tZ{f\t\\8biV\tq\fE\u0003YAR\"D'\u0003\u0002ba\t)\u0011*\u001c9me\u0005q\u0012.\u001c9m?>\u00038+\u001e2`\u0013:\u0004F.Y2f?\u00123v\f\u0012,`\r2|\u0017\r^\u000b\u0002IB!Qm\u0017\u001b5\u001d\tab-\u0003\u0002h\u001b\u0005)q\n]*vE\u0006a\u0012.\u001c9m?>\u00038+\u001e2`\tZ{FIV0fc~#ek\u0018$m_\u0006$X#\u00016\u0011\u000b\u0015\u0004G\u0007\u000e\u001b\u0002A%l\u0007\u000f\\0Pa6+H.\u00138oKJ|FIV0E-~+\u0017oX*`\r2|\u0017\r^\u000b\u0002[B)a\u000e\u0019\u001b5o9\u0011q.\u001e\b\u0003aRt!!]:\u000f\u0005u\u0012\u0018\"\u0001\n\n\u0005A\t\u0012B\u0001\b\u0010\u0013\t1X\"\u0001\u0006Pa6+H.\u00138oKJ\u0004"
)
public interface DenseVector_FloatOps extends DenseVectorExpandOps {
   impl_scaledAdd_InPlace_DV_S_DV_Float$ impl_scaledAdd_InPlace_DV_S_DV_Float();

   void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpAdd_InPlace_DV_DV_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpAdd_DV_DV_eq_DV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpSub_InPlace_DV_DV_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpSub_DV_DV_eq_DV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Float_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 impl_OpAdd_InPlace_DV_DV_Float();

   UFunc.UImpl2 impl_OpAdd_DV_DV_eq_DV_Float();

   UFunc.InPlaceImpl2 impl_OpSub_InPlace_DV_DV_Float();

   UFunc.UImpl2 impl_OpSub_DV_DV_eq_DV_Float();

   UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Float();

   static void $init$(final DenseVector_FloatOps $this) {
      ((TernaryUpdateRegistry).MODULE$.implicitly($this.impl_scaleAdd_InPlace_V_S_V_Float())).register($this.impl_scaledAdd_InPlace_DV_S_DV_Float(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpAdd_InPlace_DV_DV_Float_$eq(new UFunc.InPlaceImpl2() {
         // $FF: synthetic field
         private final DenseVector_FloatOps $outer;

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
            scaleAdd$.MODULE$.inPlace(a, BoxesRunTime.boxToFloat(1.0F), b, this.$outer.impl_scaledAdd_InPlace_DV_S_DV_Float());
         }

         public {
            if (DenseVector_FloatOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseVector_FloatOps.this;
               ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_FloatOps.this.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpAdd_DV_DV_eq_DV_Float_$eq($this.pureFromUpdate((UFunc.InPlaceImpl2).MODULE$.implicitly($this.impl_OpAdd_InPlace_DV_DV_Float()), (CanCopy).MODULE$.implicitly(DenseVectorDeps$.MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Float()))));
      ((BinaryRegistry).MODULE$.implicitly($this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register($this.impl_OpAdd_DV_DV_eq_DV_Float(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpSub_InPlace_DV_DV_Float_$eq(new UFunc.InPlaceImpl2() {
         // $FF: synthetic field
         private final DenseVector_FloatOps $outer;

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
            scaleAdd$.MODULE$.inPlace(a, BoxesRunTime.boxToFloat(-1.0F), b, this.$outer.impl_scaledAdd_InPlace_DV_S_DV_Float());
         }

         public {
            if (DenseVector_FloatOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseVector_FloatOps.this;
               ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_FloatOps.this.impl_Op_InPlace_V_V_Idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpSub_DV_DV_eq_DV_Float_$eq($this.pureFromUpdate((UFunc.InPlaceImpl2).MODULE$.implicitly($this.impl_OpSub_InPlace_DV_DV_Float()), (CanCopy).MODULE$.implicitly(DenseVectorDeps$.MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Float()))));
      ((BinaryRegistry).MODULE$.implicitly($this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register($this.impl_OpSub_DV_DV_eq_DV_Float(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVector_FloatOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Float_$eq(new UFunc.UImpl2() {
         private final int UNROLL_FACTOR = 6;

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

         public float apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(49)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return a.noOffsetOrStride() && b.noOffsetOrStride() && a.length() < 8 ? DenseVectorSupportMethods.smallDotProduct_Float(a.data$mcF$sp(), b.data$mcF$sp(), a.length()) : this.blasPath(a, b);
            }
         }

         private int UNROLL_FACTOR() {
            return this.UNROLL_FACTOR;
         }

         private float blasPath(final DenseVector a, final DenseVector b) {
            float var10000;
            if ((a.length() <= 300 || !package$.MODULE$.usingNatives()) && a.stride() == 1 && b.stride() == 1) {
               var10000 = DenseVectorSupportMethods.dotProduct_Float(a.data$mcF$sp(), a.offset(), b.data$mcF$sp(), b.offset(), a.length());
            } else {
               int boff = b.stride() >= 0 ? b.offset() : b.offset() + b.stride() * (b.length() - 1);
               int aoff = a.stride() >= 0 ? a.offset() : a.offset() + a.stride() * (a.length() - 1);
               var10000 = BLAS.getInstance().sdot(a.length(), b.data$mcF$sp(), boff, b.stride(), a.data$mcF$sp(), aoff, a.stride());
            }

            return var10000;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_FloatOps.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
   }

   public class impl_scaledAdd_InPlace_DV_S_DV_Float$ implements UFunc.InPlaceImpl3 {
      public void apply(final DenseVector y, final float a, final DenseVector x) {
         while(true) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(49)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            }

            if (!y.overlaps$mcF$sp(x)) {
               if (x.noOffsetOrStride() && y.noOffsetOrStride()) {
                  float[] ad = x.data$mcF$sp();
                  float[] bd = y.data$mcF$sp();
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = x.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     bd[index$macro$4] += ad[index$macro$4] * a;
                  }

                  BoxedUnit var11 = BoxedUnit.UNIT;
               } else {
                  this.slowPath(y, a, x);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               return;
            }

            x = x.copy$mcF$sp();
            a = a;
            y = y;
         }
      }

      private void slowPath(final DenseVector y, final float a, final DenseVector x) {
         int index$macro$2 = 0;

         for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            y.update$mcF$sp(index$macro$2, y.apply$mcF$sp(index$macro$2) + x.apply$mcF$sp(index$macro$2) * a);
         }

      }
   }
}
