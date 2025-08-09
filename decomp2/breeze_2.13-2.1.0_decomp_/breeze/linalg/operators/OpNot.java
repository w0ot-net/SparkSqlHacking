package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005;Q\u0001C\u0005\t\u0002A1QAE\u0005\t\u0002MAQaI\u0001\u0005\u0002\u0011:Q!J\u0001\t\u0004\u00192Q\u0001K\u0001\t\u0002%BQa\t\u0003\u0005\u0002IBQa\r\u0003\u0005BQBqa\u000e\u0003\u0002\u0002\u0013%\u0001(A\u0003Pa:{GO\u0003\u0002\u000b\u0017\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003\u00195\ta\u0001\\5oC2<'\"\u0001\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!E\u0001\u000e\u0003%\u0011Qa\u00149O_R\u001cB!\u0001\u000b\u001b;A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u0004\"!E\u000e\n\u0005qI!AB(q)f\u0004X\r\u0005\u0002\u001fC5\tqD\u0003\u0002!\u001b\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0012 \u00051i\u0015\r\u001d9j]\u001e,f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t\u0001#\u0001\u0007pa:{GOQ8pY\u0016\fg\u000e\u0005\u0002(\t5\t\u0011A\u0001\u0007pa:{GOQ8pY\u0016\fgnE\u0002\u0005))\u0002BaJ\u00160_%\u0011A&\f\u0002\u0005\u00136\u0004H.\u0003\u0002/?\t)QKR;oGB\u0011Q\u0003M\u0005\u0003cY\u0011qAQ8pY\u0016\fg\u000eF\u0001'\u0003\u0015\t\u0007\u000f\u001d7z)\tyS\u0007C\u00037\r\u0001\u0007q&A\u0001w\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005I\u0004C\u0001\u001e@\u001b\u0005Y$B\u0001\u001f>\u0003\u0011a\u0017M\\4\u000b\u0003y\nAA[1wC&\u0011\u0001i\u000f\u0002\u0007\u001f\nTWm\u0019;"
)
public final class OpNot {
   public static Object withSink(final Object s) {
      return OpNot$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpNot$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpNot$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpNot$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpNot$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpNot$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpNot$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpNot$.MODULE$.apply(v, impl);
   }

   public static class opNotBoolean$ implements UFunc.UImpl {
      public static final opNotBoolean$ MODULE$ = new opNotBoolean$();

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

      public boolean apply(final boolean v) {
         return !v;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(opNotBoolean$.class);
      }
   }
}
