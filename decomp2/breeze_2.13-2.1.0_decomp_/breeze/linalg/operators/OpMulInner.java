package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\t\n!b\u00149Nk2LeN\\3s\u0015\t1q!A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011\u0001\"C\u0001\u0007Y&t\u0017\r\\4\u000b\u0003)\taA\u0019:fKj,7\u0001\u0001\t\u0003\u001b\u0005i\u0011!\u0002\u0002\u000b\u001fBlU\u000f\\%o]\u0016\u00148\u0003B\u0001\u0011-e\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007CA\u0007\u0018\u0013\tARA\u0001\u0004PaRK\b/\u001a\t\u00035ui\u0011a\u0007\u0006\u00039%\tqaZ3oKJL7-\u0003\u0002\u001f7\t)QKR;oG\u00061A(\u001b8jiz\"\u0012\u0001D\u0001\u0017_BlU\u000f\\%o]\u0016\u0014hI]8n'\u0016l\u0017N]5oOV\u00111E\u000b\u000b\u0003IM\u0002R!\n\u0014)Q!r!!\u0004\u0001\n\u0005\u001dj\"!B%na2\u0014\u0004CA\u0015+\u0019\u0001!QaK\u0002C\u00021\u0012\u0011aU\t\u0003[A\u0002\"!\u0005\u0018\n\u0005=\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#EJ!A\r\n\u0003\u0007\u0005s\u0017\u0010C\u00045\u0007\u0005\u0005\t9A\u001b\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0004meBS\"A\u001c\u000b\u0005aJ\u0011\u0001B7bi\"L!AO\u001c\u0003\u0011M+W.\u001b:j]\u001e\u0004"
)
public final class OpMulInner {
   public static UFunc.UImpl2 opMulInnerFromSemiring(final Semiring evidence$12) {
      return OpMulInner$.MODULE$.opMulInnerFromSemiring(evidence$12);
   }

   public static Object withSink(final Object s) {
      return OpMulInner$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpMulInner$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpMulInner$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpMulInner$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpMulInner$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpMulInner$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpMulInner$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpMulInner$.MODULE$.apply(v, impl);
   }
}
