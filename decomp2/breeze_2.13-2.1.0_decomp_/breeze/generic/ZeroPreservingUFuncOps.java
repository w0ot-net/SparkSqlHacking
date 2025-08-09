package breeze.generic;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.ScalarOf;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uba\u0002\u0004\b!\u0003\r\t\u0001\u0004\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u0001!\u0019\u0001\t\u0005\u0006-\u0002!\u0019a\u0016\u0005\u0006a\u0002!\u0019!\u001d\u0005\b\u00033\u0001A1AA\u000e\u0005YQVM]8Qe\u0016\u001cXM\u001d<j]\u001e,f)\u001e8d\u001fB\u001c(B\u0001\u0005\n\u0003\u001d9WM\\3sS\u000eT\u0011AC\u0001\u0007EJ,WM_3\u0004\u0001M!\u0001!D\n\u0018!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0011A#F\u0007\u0002\u000f%\u0011ac\u0002\u0002\u001b5\u0016\u0014x\u000e\u0015:fg\u0016\u0014h/\u001b8h+\u001a+hn\u0019'poB\u0013\u0018n\u001c\t\u0003)aI!!G\u0004\u0003\u001f5\u000b\u0007\u000f]5oOV3UO\\2PaN\fa\u0001J5oSR$C#\u0001\u000f\u0011\u00059i\u0012B\u0001\u0010\u0010\u0005\u0011)f.\u001b;\u0002=\u0019\u0014x.\u001c'po>\u0013H-\u001a:DC:l\u0015\r]!di&4XMV1mk\u0016\u001cXCB\u0011,k%{E\b\u0006\u0003#}-\u000b\u0006#B\u0012'SQZdB\u0001\u000b%\u0013\t)s!A\u0003V\rVt7-\u0003\u0002(Q\t)Q+S7qY*\u0011Qe\u0002\t\u0003U-b\u0001\u0001B\u0003-\u0005\t\u0007QF\u0001\u0002PaF\u0011a&\r\t\u0003\u001d=J!\u0001M\b\u0003\u000f9{G\u000f[5oOB\u0011ACM\u0005\u0003g\u001d\u00111CW3s_B\u0013Xm]3sm&tw-\u0016$v]\u000e\u0004\"AK\u001b\u0005\u000bY\u0012!\u0019A\u001c\u0003\u0003Q\u000b\"A\f\u001d\u0011\u00059I\u0014B\u0001\u001e\u0010\u0005\r\te.\u001f\t\u0003Uq\"Q!\u0010\u0002C\u0002]\u0012\u0011!\u0016\u0005\u0006\u007f\t\u0001\u001d\u0001Q\u0001\tQ\u0006tG\r[8mIB!\u0011I\u0012\u001bI\u001b\u0005\u0011%BA\"E\u0003\u001d\u0019X\u000f\u001d9peRT!!R\u0005\u0002\r1Lg.\u00197h\u0013\t9%I\u0001\u0005TG\u0006d\u0017M](g!\tQ\u0013\nB\u0003K\u0005\t\u0007qGA\u0001W\u0011\u0015a%\u0001q\u0001N\u0003\u0011IW\u000e\u001d7\u0011\u000b\r2\u0013\u0006\u0013(\u0011\u0005)zE!\u0002)\u0003\u0005\u00049$A\u0001,3\u0011\u0015\u0011&\u0001q\u0001T\u00031\u0019\u0017M\\'baZ\u000bG.^3t!\u0019\tE\u000b\u000e%Ow%\u0011QK\u0011\u0002\r\u0007\u0006tW*\u00199WC2,Xm]\u0001\u0011G\u0006tW*\u00199BGRLg/\u001a,2\tZ+r\u0001W/`O\u0006d7\r\u0006\u0003ZI&t\u0007CB\u0012[9z\u0003'-\u0003\u0002\\Q\t1Q+S7qYJ\u0002\"AK/\u0005\u000b1\u001a!\u0019A\u0017\u0011\u0005)zF!\u0002\u001c\u0004\u0005\u00049\u0004C\u0001\u0016b\t\u0015\u00016A1\u00018!\tQ3\rB\u0003>\u0007\t\u0007q\u0007C\u0003@\u0007\u0001\u000fQ\r\u0005\u0003B\rz3\u0007C\u0001\u0016h\t\u0015A7A1\u00018\u0005\t1\u0016\u0007C\u0003M\u0007\u0001\u000f!\u000e\u0005\u0004$5r3\u0007m\u001b\t\u0003U1$Q!\\\u0002C\u0002]\u0012!A\u0016*\t\u000bI\u001b\u00019A8\u0011\r\u0005#fLZ6c\u0003u\u0019\u0017M\u001c+sC:\u001chm\u001c:n\u0003\u000e$\u0018N^3WC2,Xm]+Gk:\u001cWC\u0002:\u0000\u0003\u000b\t\u0019\u0002F\u0003t\u0003\u000f\t)\u0002E\u0003uyz\f\u0019A\u0004\u0002vI9\u0011ao\u001f\b\u0003ojl\u0011\u0001\u001f\u0006\u0003s.\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0006\n\u0005!I\u0011BA?)\u0005-Ie\u000e\u00157bG\u0016LU\u000e\u001d7\u0011\u0005)zHABA\u0001\t\t\u0007QFA\u0002UC\u001e\u00042AKA\u0003\t\u00151DA1\u00018\u0011\u001d\tI\u0001\u0002a\u0002\u0003\u0017\tAbY1o)J\fgn\u001d4pe6\u0004r!QA\u0007\u0003\u0007\t\t\"C\u0002\u0002\u0010\t\u0013!cQ1o)J\fgn\u001d4pe64\u0016\r\\;fgB\u0019!&a\u0005\u0005\u000b)#!\u0019A\u001c\t\r1#\u00019AA\f!\u001d!hE`A\t\u0003#\t\u0001eY1o)J\fgn\u001d4pe6\f5\r^5wKZ\u000bG.^3t+\u001a+hn\u0019\u001a`)VQ\u0011QDA\u0014\u0003W\t9$a\f\u0015\r\u0005}\u0011\u0011GA\u001d!%!\u0018\u0011EA\u0013\u0003S\ti#C\u0002\u0002$!\u0012A\"\u00138QY\u0006\u001cW-S7qYJ\u00022AKA\u0014\t\u0019\t\t!\u0002b\u0001[A\u0019!&a\u000b\u0005\u000bY*!\u0019A\u001c\u0011\u0007)\ny\u0003B\u0003Q\u000b\t\u0007q\u0007C\u0004\u0002\n\u0015\u0001\u001d!a\r\u0011\u000f\u0005\u000bi!!\u000b\u00026A\u0019!&a\u000e\u0005\u000b)+!\u0019A\u001c\t\r1+\u00019AA\u001e!)!(,!\n\u00026\u00055\u0012Q\u0007"
)
public interface ZeroPreservingUFuncOps extends ZeroPreservingUFuncLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl fromLowOrderCanMapActiveValues$(final ZeroPreservingUFuncOps $this, final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return $this.fromLowOrderCanMapActiveValues(handhold, impl, canMapValues);
   }

   default UFunc.UImpl fromLowOrderCanMapActiveValues(final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return new UFunc.UImpl(canMapValues, impl) {
         private final CanMapValues canMapValues$4;
         private final UFunc.UImpl impl$7;

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
            return this.canMapValues$4.mapActive(v, (vx) -> this.impl$7.apply(vx));
         }

         public {
            this.canMapValues$4 = canMapValues$4;
            this.impl$7 = impl$7;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMapActiveV1DV$(final ZeroPreservingUFuncOps $this, final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return $this.canMapActiveV1DV(handhold, impl, canMapValues);
   }

   default UFunc.UImpl2 canMapActiveV1DV(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return new UFunc.UImpl2(canMapValues, impl) {
         private final CanMapValues canMapValues$5;
         private final UFunc.UImpl2 impl$8;

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

         public Object apply(final Object v1, final Object v2) {
            return this.canMapValues$5.mapActive(v1, (x$4) -> this.impl$8.apply(x$4, v2));
         }

         public {
            this.canMapValues$5 = canMapValues$5;
            this.impl$8 = impl$8;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl canTransformActiveValuesUFunc$(final ZeroPreservingUFuncOps $this, final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return $this.canTransformActiveValuesUFunc(canTransform, impl);
   }

   default UFunc.InPlaceImpl canTransformActiveValuesUFunc(final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return (v) -> canTransform.transformActive(v, (vx) -> impl.apply(vx));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canTransformActiveValuesUFunc2_T$(final ZeroPreservingUFuncOps $this, final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return $this.canTransformActiveValuesUFunc2_T(canTransform, impl);
   }

   default UFunc.InPlaceImpl2 canTransformActiveValuesUFunc2_T(final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return new UFunc.InPlaceImpl2(canTransform, impl) {
         private final CanTransformValues canTransform$4;
         private final UFunc.UImpl2 impl$10;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Object v, final Object v2) {
            this.canTransform$4.transformActive(v, (x$5) -> this.impl$10.apply(x$5, v2));
         }

         public {
            this.canTransform$4 = canTransform$4;
            this.impl$10 = impl$10;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final ZeroPreservingUFuncOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
