package breeze.linalg;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl2$mcDDD$sp;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005y:Q\u0001C\u0005\t\u000291Q\u0001E\u0005\t\u0002EAQAH\u0001\u0005\u0002}9Q\u0001I\u0001\t\u0004\u00052QaI\u0001\t\u0002\u0011BQA\b\u0003\u0005\u00025BQA\f\u0003\u0005\u0002=Bq\u0001\u000e\u0003\u0002\u0002\u0013%Q'A\u0004m_\u001e$\u0015N\u001a4\u000b\u0005)Y\u0011A\u00027j]\u0006dwMC\u0001\r\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001CA\b\u0002\u001b\u0005I!a\u00027pO\u0012KgMZ\n\u0004\u0003IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a95\t!D\u0003\u0002\u001c\u0017\u00059q-\u001a8fe&\u001c\u0017BA\u000f\u001b\u00051i\u0015\r\u001d9j]\u001e,f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\ta\"\u0001\tj[BdGi\\;cY\u0016$u.\u001e2mKB\u0011!\u0005B\u0007\u0002\u0003\t\u0001\u0012.\u001c9m\t>,(\r\\3E_V\u0014G.Z\n\u0004\tI)\u0003#\u0002\u0012'U)R\u0013BA\u0014)\u0005\u0015IU\u000e\u001d73\u0013\tI#DA\u0003V\rVt7\r\u0005\u0002\u0014W%\u0011A\u0006\u0006\u0002\u0007\t>,(\r\\3\u0015\u0003\u0005\nQ!\u00199qYf$2A\u000b\u00193\u0011\u0015\td\u00011\u0001+\u0003\u0005\t\u0007\"B\u001a\u0007\u0001\u0004Q\u0013!\u00012\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003Y\u0002\"a\u000e\u001f\u000e\u0003aR!!\u000f\u001e\u0002\t1\fgn\u001a\u0006\u0002w\u0005!!.\u0019<b\u0013\ti\u0004H\u0001\u0004PE*,7\r\u001e"
)
public final class logDiff {
   public static Object withSink(final Object s) {
      return logDiff$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return logDiff$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return logDiff$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return logDiff$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return logDiff$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return logDiff$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return logDiff$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return logDiff$.MODULE$.apply(v, impl);
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
         boolean cond$macro$1 = a >= b;
         if (!cond$macro$1) {
            throw new IllegalArgumentException((new StringBuilder(29)).append("requirement failed: ").append((new StringBuilder(41)).append("a should be greater than b, but got ").append(a).append(" and ").append(b).toString()).append(": ").append("a.>=(b)").toString());
         } else {
            return a > b ? a + .MODULE$.log1p(-.MODULE$.exp(b - a)) : Double.NEGATIVE_INFINITY;
         }
      }
   }
}
