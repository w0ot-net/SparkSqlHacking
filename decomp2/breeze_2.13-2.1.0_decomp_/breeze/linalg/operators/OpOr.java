package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q\u0001C\u0005\t\u0002A1QAE\u0005\t\u0002MAQaI\u0001\u0005\u0002\u0011:Q!J\u0001\t\u0004\u00192Q\u0001K\u0001\t\u0002%BQa\t\u0003\u0005\u0002IBQa\r\u0003\u0005BQBq!\u000f\u0003\u0002\u0002\u0013%!(\u0001\u0003Pa>\u0013(B\u0001\u0006\f\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002\r\u001b\u00051A.\u001b8bY\u001eT\u0011AD\u0001\u0007EJ,WM_3\u0004\u0001A\u0011\u0011#A\u0007\u0002\u0013\t!q\n](s'\u0011\tACG\u000f\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\t\t2$\u0003\u0002\u001d\u0013\t1q\n\u001d+za\u0016\u0004\"AH\u0011\u000e\u0003}Q!\u0001I\u0007\u0002\u000f\u001d,g.\u001a:jG&\u0011!e\b\u0002\u0011\u000b2,W.\u001a8uo&\u001cX-\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\t\u0002%%l\u0007\u000f\\0Pa>\u0013xLQ0C?\u0016\fxL\u0011\t\u0003O\u0011i\u0011!\u0001\u0002\u0013S6\u0004HnX(q\u001fJ|&i\u0018\"`KF|&iE\u0002\u0005))\u0002RaJ\u00160_=J!\u0001L\u0017\u0003\u000b%k\u0007\u000f\u001c\u001a\n\u00059z\"!B+Gk:\u001c\u0007CA\u000b1\u0013\t\tdCA\u0004C_>dW-\u00198\u0015\u0003\u0019\nQ!\u00199qYf$2aL\u001b8\u0011\u00151d\u00011\u00010\u0003\u00051\b\"\u0002\u001d\u0007\u0001\u0004y\u0013A\u0001<3\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005Y\u0004C\u0001\u001fB\u001b\u0005i$B\u0001 @\u0003\u0011a\u0017M\\4\u000b\u0003\u0001\u000bAA[1wC&\u0011!)\u0010\u0002\u0007\u001f\nTWm\u0019;"
)
public final class OpOr {
   public static Object withSink(final Object s) {
      return OpOr$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpOr$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpOr$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpOr$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpOr$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpOr$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpOr$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpOr$.MODULE$.apply(v, impl);
   }

   public static class impl_OpOr_B_B_eq_B$ implements UFunc.UImpl2 {
      public static final impl_OpOr_B_B_eq_B$ MODULE$ = new impl_OpOr_B_B_eq_B$();

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

      public boolean apply(final boolean v, final boolean v2) {
         return v || v2;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(impl_OpOr_B_B_eq_B$.class);
      }
   }
}
