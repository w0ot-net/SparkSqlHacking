package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q\u0001C\u0005\t\u0002A1QAE\u0005\t\u0002MAQaI\u0001\u0005\u0002\u0011:Q!J\u0001\t\u0004\u00192Q\u0001K\u0001\t\u0002%BQa\t\u0003\u0005\u0002IBQa\r\u0003\u0005BQBq!\u000f\u0003\u0002\u0002\u0013%!(A\u0003Pa\u0006sGM\u0003\u0002\u000b\u0017\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003\u00195\ta\u0001\\5oC2<'\"\u0001\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!E\u0001\u000e\u0003%\u0011Qa\u00149B]\u0012\u001cB!\u0001\u000b\u001b;A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u0004\"!E\u000e\n\u0005qI!AB(q)f\u0004X\r\u0005\u0002\u001fC5\tqD\u0003\u0002!\u001b\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0012 \u0005A)E.Z7f]R<\u0018n]3V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002!\u0005aq\u000e]!oI\n{w\u000e\\3b]B\u0011q\u0005B\u0007\u0002\u0003\taq\u000e]!oI\n{w\u000e\\3b]N\u0019A\u0001\u0006\u0016\u0011\u000b\u001dZsfL\u0018\n\u00051j#!B%na2\u0014\u0014B\u0001\u0018 \u0005\u0015)f)\u001e8d!\t)\u0002'\u0003\u00022-\t9!i\\8mK\u0006tG#\u0001\u0014\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007=*t\u0007C\u00037\r\u0001\u0007q&A\u0001w\u0011\u0015Ad\u00011\u00010\u0003\t1('\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001<!\ta\u0014)D\u0001>\u0015\tqt(\u0001\u0003mC:<'\"\u0001!\u0002\t)\fg/Y\u0005\u0003\u0005v\u0012aa\u00142kK\u000e$\b"
)
public final class OpAnd {
   public static Object withSink(final Object s) {
      return OpAnd$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpAnd$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpAnd$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpAnd$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpAnd$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpAnd$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpAnd$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpAnd$.MODULE$.apply(v, impl);
   }

   public static class opAndBoolean$ implements UFunc.UImpl2 {
      public static final opAndBoolean$ MODULE$ = new opAndBoolean$();

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
         return v && v2;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(opAndBoolean$.class);
      }
   }
}
