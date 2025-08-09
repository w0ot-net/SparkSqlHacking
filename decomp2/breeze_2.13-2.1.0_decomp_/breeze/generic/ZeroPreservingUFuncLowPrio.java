package breeze.generic;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.ScalarOf;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0012\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u000e[KJ|\u0007K]3tKJ4\u0018N\\4V\rVt7\rT8x!JLwN\u0003\u0002\u0006\r\u00059q-\u001a8fe&\u001c'\"A\u0004\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\u0010\u001b\u0006\u0004\b/\u001b8h+\u001a+hnY(qg\u00061A%\u001b8ji\u0012\"\u0012A\u0006\t\u0003\u0017]I!\u0001\u0007\u0007\u0003\tUs\u0017\u000e^\u0001\u0015G\u0006tW*\u00199We\u0005\u001bG/\u001b<f-\u0006dW/Z:\u0016\u000fm)cg\f$MsQ!Ad\u000f%O!\u0019i\u0002e\t\u00186q9\u0011\u0011CH\u0005\u0003?\u0011\tQ!\u0016$v]\u000eL!!\t\u0012\u0003\rUKU\u000e\u001d73\u0015\tyB\u0001\u0005\u0002%K1\u0001A!\u0002\u0014\u0003\u0005\u00049#AA(q#\tA3\u0006\u0005\u0002\fS%\u0011!\u0006\u0004\u0002\b\u001d>$\b.\u001b8h!\t\tB&\u0003\u0002.\t\t\u0019\",\u001a:p!J,7/\u001a:wS:<WKR;oGB\u0011Ae\f\u0003\u0006a\t\u0011\r!\r\u0002\u0003-F\n\"\u0001\u000b\u001a\u0011\u0005-\u0019\u0014B\u0001\u001b\r\u0005\r\te.\u001f\t\u0003IY\"Qa\u000e\u0002C\u0002E\u0012\u0011\u0001\u0016\t\u0003Ie\"QA\u000f\u0002C\u0002E\u0012\u0011!\u0016\u0005\u0006y\t\u0001\u001d!P\u0001\tQ\u0006tG\r[8mIB!ahQ\u001bF\u001b\u0005y$B\u0001!B\u0003\u001d\u0019X\u000f\u001d9peRT!A\u0011\u0004\u0002\r1Lg.\u00197h\u0013\t!uH\u0001\u0005TG\u0006d\u0017M](g!\t!c\tB\u0003H\u0005\t\u0007\u0011G\u0001\u0002We!)\u0011J\u0001a\u0002\u0015\u0006!\u0011.\u001c9m!\u0019i\u0002e\t\u0018F\u0017B\u0011A\u0005\u0014\u0003\u0006\u001b\n\u0011\r!\r\u0002\u0003-JCQa\u0014\u0002A\u0004A\u000bAbY1o\u001b\u0006\u0004h+\u00197vKN\u0004bAP)6\u000b.C\u0014B\u0001*@\u00051\u0019\u0015M\\'baZ\u000bG.^3tS\t\u0001A+\u0003\u0002V\t\t1\",\u001a:p!J,7/\u001a:wS:<WKR;oG>\u00038\u000f"
)
public interface ZeroPreservingUFuncLowPrio extends MappingUFuncOps {
   // $FF: synthetic method
   static UFunc.UImpl2 canMapV2ActiveValues$(final ZeroPreservingUFuncLowPrio $this, final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return $this.canMapV2ActiveValues(handhold, impl, canMapValues);
   }

   default UFunc.UImpl2 canMapV2ActiveValues(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return new UFunc.UImpl2(canMapValues, impl) {
         private final CanMapValues canMapValues$6;
         private final UFunc.UImpl2 impl$11;

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
            return this.canMapValues$6.mapActive(v2, (x$6) -> this.impl$11.apply(v1, x$6));
         }

         public {
            this.canMapValues$6 = canMapValues$6;
            this.impl$11 = impl$11;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final ZeroPreservingUFuncLowPrio $this) {
   }
}
