package breeze.linalg;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl2$mcDDD$sp;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichDouble.;

@ScalaSignature(
   bytes = "\u0006\u0005]<Qa\u0003\u0007\t\u0002E1Qa\u0005\u0007\t\u0002QAQ!I\u0001\u0005\u0002\t:QaI\u0001\t\u0004\u00112QAJ\u0001\t\u0002\u001dBQ!\t\u0003\u0005\u00029BQa\f\u0003\u0005\u0002ABq!\u000e\u0003\u0002\u0002\u0013%a\u0007C\u0003@\u0003\u0011\u0005\u0001\tC\u0003L\u0003\u0011\rA\nC\u0003k\u0003\u0011\r1.A\u0004t_\u001a$X.\u0019=\u000b\u00055q\u0011A\u00027j]\u0006dwMC\u0001\u0010\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\n\u0002\u001b\u0005a!aB:pMRl\u0017\r_\n\u0004\u0003UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\u001d\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0011\u001e\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t\u0011#\u0001\tj[BdGi\\;cY\u0016$u.\u001e2mKB\u0011Q\u0005B\u0007\u0002\u0003\t\u0001\u0012.\u001c9m\t>,(\r\\3E_V\u0014G.Z\n\u0004\tUA\u0003#B\u0013*W-Z\u0013B\u0001\u0016 \u0005\u0015IU\u000e\u001d73!\t1B&\u0003\u0002./\t1Ai\\;cY\u0016$\u0012\u0001J\u0001\u0006CB\u0004H.\u001f\u000b\u0004WE\u001a\u0004\"\u0002\u001a\u0007\u0001\u0004Y\u0013!A1\t\u000bQ2\u0001\u0019A\u0016\u0002\u0003\t\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u000e\t\u0003quj\u0011!\u000f\u0006\u0003um\nA\u0001\\1oO*\tA(\u0001\u0003kCZ\f\u0017B\u0001 :\u0005\u0019y%M[3di\u0006)\u0011M\u001d:bsR\u00191&\u0011$\t\u000b\tC\u0001\u0019A\"\u0002\u0007\u0005\u0014(\u000fE\u0002\u0017\t.J!!R\f\u0003\u000b\u0005\u0013(/Y=\t\u000b\u001dC\u0001\u0019\u0001%\u0002\r1,gn\u001a;i!\t1\u0012*\u0003\u0002K/\t\u0019\u0011J\u001c;\u0002\u0019I,G-^2f\t>,(\r\\3\u0016\u00055\u001bFc\u0001(]IB!QeT),\u0013\t\u0001vD\u0001\u0003J[Bd\u0007C\u0001*T\u0019\u0001!Q\u0001V\u0005C\u0002U\u0013\u0011\u0001V\t\u0003-f\u0003\"AF,\n\u0005a;\"a\u0002(pi\"Lgn\u001a\t\u0003-iK!aW\f\u0003\u0007\u0005s\u0017\u0010C\u0003^\u0013\u0001\u000fa,\u0001\u0003ji\u0016\u0014\b\u0003B0c#.j\u0011\u0001\u0019\u0006\u0003C2\tqa];qa>\u0014H/\u0003\u0002dA\n\t2)\u00198Ue\u00064XM]:f-\u0006dW/Z:\t\u000b\u0015L\u00019\u00014\u0002\u000f5\f\u00070S7qYB!qmT),\u001d\t\u0011\u0002.\u0003\u0002j\u0019\u0005\u0019Q.\u0019=\u0002\u0017I,G-^2f\r2|\u0017\r^\u000b\u0003Y>$2!\\:v!\u0011)sJ\u001c9\u0011\u0005I{G!\u0002+\u000b\u0005\u0004)\u0006C\u0001\fr\u0013\t\u0011xCA\u0003GY>\fG\u000fC\u0003^\u0015\u0001\u000fA\u000f\u0005\u0003`E:\u0004\b\"B3\u000b\u0001\b1\b\u0003B4P]B\u0004"
)
public final class softmax {
   public static UFunc.UImpl reduceFloat(final CanTraverseValues iter, final UFunc.UImpl maxImpl) {
      return softmax$.MODULE$.reduceFloat(iter, maxImpl);
   }

   public static UFunc.UImpl reduceDouble(final CanTraverseValues iter, final UFunc.UImpl maxImpl) {
      return softmax$.MODULE$.reduceDouble(iter, maxImpl);
   }

   public static double array(final double[] arr, final int length) {
      return softmax$.MODULE$.array(arr, length);
   }

   public static Object withSink(final Object s) {
      return softmax$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return softmax$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return softmax$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return softmax$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return softmax$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return softmax$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return softmax$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return softmax$.MODULE$.apply(v, impl);
   }

   public static class implDoubleDouble$ implements UFunc$UImpl2$mcDDD$sp {
      public static final implDoubleDouble$ MODULE$ = new implDoubleDouble$();

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

      public double apply(final double a, final double b) {
         return this.apply$mcDDD$sp(a, b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(implDoubleDouble$.class);
      }

      public double apply$mcDDD$sp(final double a, final double b) {
         return .MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.doubleWrapper(a)) ? b : (.MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.doubleWrapper(b)) ? a : (a < b ? b + scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(a - b)) : a + scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(b - a))));
      }
   }
}
