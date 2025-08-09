package breeze.generic;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MdaB\u0004\t!\u0003\r\t!\u0004\u0005\u0006A\u0001!\t!\t\u0005\u0006K\u0001!\u0019A\n\u0005\u00065\u0002!\u0019a\u0017\u0005\u0006q\u0002!\u0019!\u001f\u0005\b\u0003/\u0001A1AA\r\u0011\u001d\t9\u0005\u0001C\u0002\u0003\u0013\u0012q\"T1qa&tw-\u0016$v]\u000e|\u0005o\u001d\u0006\u0003\u0013)\tqaZ3oKJL7MC\u0001\f\u0003\u0019\u0011'/Z3{K\u000e\u00011\u0003\u0002\u0001\u000f)a\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0017\u001b\u0005A\u0011BA\f\t\u0005Mi\u0015\r\u001d9j]\u001e,f)\u001e8d\u0019><\bK]5p!\tIb$D\u0001\u001b\u0015\tYB$A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011QDC\u0001\u0007Y&t\u0017\r\\4\n\u0005}Q\"AC$f]\u0016\u0014\u0018nY(qg\u00061A%\u001b8ji\u0012\"\u0012A\t\t\u0003\u001f\rJ!\u0001\n\t\u0003\tUs\u0017\u000e^\u0001\u0016G\u0006t',\u001b9NCB4\u0016\r\\;fg&k\u0007\u000f\\0U+\u00199\u0013gO'T\u0005R!\u0001\u0006R(V!\u0019ICf\f\u001e;\u0003:\u0011QCK\u0005\u0003W!\tQ!\u0016$v]\u000eL!!\f\u0018\u0003\rUKU\u000e\u001d73\u0015\tY\u0003\u0002\u0005\u00021c1\u0001A!\u0002\u001a\u0003\u0005\u0004\u0019$a\u0001+bOF\u0011Ag\u000e\t\u0003\u001fUJ!A\u000e\t\u0003\u000f9{G\u000f[5oOB\u0011Q\u0003O\u0005\u0003s!\u0011A\"T1qa&tw-\u0016$v]\u000e\u0004\"\u0001M\u001e\u0005\u000bq\u0012!\u0019A\u001f\u0003\u0003Q\u000b\"\u0001\u000e \u0011\u0005=y\u0014B\u0001!\u0011\u0005\r\te.\u001f\t\u0003a\t#Qa\u0011\u0002C\u0002u\u0012\u0011!\u0016\u0005\u0006\u000b\n\u0001\u001dAR\u0001\tQ\u0006tG\r[8mIB!qI\u0013\u001eM\u001b\u0005A%BA%\u001d\u0003\u001d\u0019X\u000f\u001d9peRL!a\u0013%\u0003\u0011M\u001b\u0017\r\\1s\u001f\u001a\u0004\"\u0001M'\u0005\u000b9\u0013!\u0019A\u001f\u0003\u0005Y\u000b\u0004\"\u0002)\u0003\u0001\b\t\u0016\u0001B5na2\u0004b!\u000b\u00170\u00192\u0013\u0006C\u0001\u0019T\t\u0015!&A1\u0001>\u0005\t1&\u000bC\u0003W\u0005\u0001\u000fq+A\bdC:T\u0016\u000e]'baZ\u000bG.^3t!\u00199\u0005L\u000f'S\u0003&\u0011\u0011\f\u0013\u0002\u0010\u0007\u0006t',\u001b9NCB4\u0016\r\\;fg\u0006I2-\u00198Ue\u0006t7OZ8s[Z\u000bG.^3t+\u001a+hnY0U+\u0011a\u0016n\u001b:\u0015\u0007ucG\u000f\u0005\u0003_M\"TgBA0+\u001d\t\u0001WM\u0004\u0002bI6\t!M\u0003\u0002d\u0019\u00051AH]8pizJ\u0011aC\u0005\u0003\u0013)I!a\u001a\u0018\u0003\u0017%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\t\u0003a%$QAM\u0002C\u0002M\u0002\"\u0001M6\u0005\u000bq\u001a!\u0019A\u001f\t\u000b5\u001c\u00019\u00018\u0002\u0019\r\fg\u000e\u0016:b]N4wN]7\u0011\t\u001d{'.]\u0005\u0003a\"\u0013!cQ1o)J\fgn\u001d4pe64\u0016\r\\;fgB\u0011\u0001G\u001d\u0003\u0006g\u000e\u0011\r!\u0010\u0002\u0002-\")\u0001k\u0001a\u0002kB)aL\u001e5rc&\u0011qO\f\u0002\u0006+&k\u0007\u000f\\\u0001\u001bG\u0006tGK]1og\u001a|'/\u001c,bYV,7/\u0016$v]\u000e\u0014t\fV\u000b\tu~\f\u0019!!\u0005\u0002\bQ)10a\u0003\u0002\u0014A9a\f @\u0002\u0002\u0005\u0015\u0011BA?/\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73!\t\u0001t\u0010B\u00033\t\t\u00071\u0007E\u00021\u0003\u0007!Q\u0001\u0010\u0003C\u0002u\u00022\u0001MA\u0004\t\u0019\tI\u0001\u0002b\u0001{\t\u0011aK\r\u0005\u0007[\u0012\u0001\u001d!!\u0004\u0011\r\u001d{\u0017\u0011AA\b!\r\u0001\u0014\u0011\u0003\u0003\u0006g\u0012\u0011\r!\u0010\u0005\u0007!\u0012\u0001\u001d!!\u0006\u0011\u0013ycc0a\u0004\u0002\u0006\u0005=\u0011\u0001\u00074s_6dun^(sI\u0016\u00148)\u00198NCB4\u0016\r\\;fgVa\u00111DA\u0011\u0003O\t\u0019$a\u000f\u0002,QA\u0011QDA\u0017\u0003k\ti\u0004\u0005\u0005*m\u0006}\u0011QEA\u0015!\r\u0001\u0014\u0011\u0005\u0003\u0007\u0003G)!\u0019A\u001a\u0003\u0005=\u0003\bc\u0001\u0019\u0002(\u0011)A(\u0002b\u0001{A\u0019\u0001'a\u000b\u0005\u000b\r+!\u0019A\u001f\t\r\u0015+\u00019AA\u0018!\u00199%*!\n\u00022A\u0019\u0001'a\r\u0005\u000bM,!\u0019A\u001f\t\rA+\u00019AA\u001c!!Ic/a\b\u00022\u0005e\u0002c\u0001\u0019\u0002<\u00111\u0011\u0011B\u0003C\u0002uBq!a\u0010\u0006\u0001\b\t\t%\u0001\u0007dC:l\u0015\r\u001d,bYV,7\u000fE\u0006H\u0003\u0007\n)#!\r\u0002:\u0005%\u0012bAA#\u0011\na1)\u00198NCB4\u0016\r\\;fg\u0006Q1-\u00198NCB4\u0016\u0007\u0012,\u0016\u001d\u0005-\u0013\u0011KA+\u0003K\nI&!\u001c\u0002^QA\u0011QJA0\u0003O\ny\u0007\u0005\u0006*Y\u0005=\u00131KA,\u00037\u00022\u0001MA)\t\u0019\t\u0019C\u0002b\u0001gA\u0019\u0001'!\u0016\u0005\u000bq2!\u0019A\u001f\u0011\u0007A\nI\u0006\u0002\u0004\u0002\n\u0019\u0011\r!\u0010\t\u0004a\u0005uC!B\"\u0007\u0005\u0004i\u0004BB#\u0007\u0001\b\t\t\u0007\u0005\u0004H\u0015\u0006M\u00131\r\t\u0004a\u0005\u0015D!\u0002(\u0007\u0005\u0004i\u0004B\u0002)\u0007\u0001\b\tI\u0007\u0005\u0006*Y\u0005=\u00131MA,\u0003W\u00022\u0001MA7\t\u0015!fA1\u0001>\u0011\u001d\tyD\u0002a\u0002\u0003c\u00022bRA\"\u0003'\n\u0019'a\u001b\u0002\\\u0001"
)
public interface MappingUFuncOps extends MappingUFuncLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl2 canZipMapValuesImpl_T$(final MappingUFuncOps $this, final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return $this.canZipMapValuesImpl_T(handhold, impl, canZipMapValues);
   }

   default UFunc.UImpl2 canZipMapValuesImpl_T(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return (v1, v2) -> canZipMapValues.map(v1, v2, (v, v2x) -> impl.apply(v, v2x));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl canTransformValuesUFunc_T$(final MappingUFuncOps $this, final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return $this.canTransformValuesUFunc_T(canTransform, impl);
   }

   default UFunc.InPlaceImpl canTransformValuesUFunc_T(final CanTransformValues canTransform, final UFunc.UImpl impl) {
      return new UFunc.InPlaceImpl(canTransform, impl) {
         private final CanTransformValues canTransform$1;
         private final UFunc.UImpl impl$2;

         public void apply(final Object v) {
            this.canTransform$1.transform(v, (vx) -> this.impl$2.apply(vx));
         }

         public {
            this.canTransform$1 = canTransform$1;
            this.impl$2 = impl$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canTransformValuesUFunc2_T$(final MappingUFuncOps $this, final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return $this.canTransformValuesUFunc2_T(canTransform, impl);
   }

   default UFunc.InPlaceImpl2 canTransformValuesUFunc2_T(final CanTransformValues canTransform, final UFunc.UImpl2 impl) {
      return new UFunc.InPlaceImpl2(canTransform, impl) {
         private final CanTransformValues canTransform$2;
         private final UFunc.UImpl2 impl$3;

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
            this.canTransform$2.transform(v, (x$1) -> this.impl$3.apply(x$1, v2));
         }

         public {
            this.canTransform$2 = canTransform$2;
            this.impl$3 = impl$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl fromLowOrderCanMapValues$(final MappingUFuncOps $this, final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return $this.fromLowOrderCanMapValues(handhold, impl, canMapValues);
   }

   default UFunc.UImpl fromLowOrderCanMapValues(final ScalarOf handhold, final UFunc.UImpl impl, final CanMapValues canMapValues) {
      return new UFunc.UImpl(canMapValues, impl) {
         private final CanMapValues canMapValues$1;
         private final UFunc.UImpl impl$4;

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
            return this.canMapValues$1.map(v, (vx) -> this.impl$4.apply(vx));
         }

         public {
            this.canMapValues$1 = canMapValues$1;
            this.impl$4 = impl$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMapV1DV$(final MappingUFuncOps $this, final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return $this.canMapV1DV(handhold, impl, canMapValues);
   }

   default UFunc.UImpl2 canMapV1DV(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return new UFunc.UImpl2(canMapValues, impl) {
         private final CanMapValues canMapValues$2;
         private final UFunc.UImpl2 impl$5;

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
            return this.canMapValues$2.map(v1, (x$2) -> this.impl$5.apply(x$2, v2));
         }

         public {
            this.canMapValues$2 = canMapValues$2;
            this.impl$5 = impl$5;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final MappingUFuncOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
