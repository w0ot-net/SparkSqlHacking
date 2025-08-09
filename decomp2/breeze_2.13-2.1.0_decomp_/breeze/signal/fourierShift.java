package breeze.signal;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tABZ8ve&,'o\u00155jMRT!AB\u0004\u0002\rMLwM\\1m\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005-\tQ\"A\u0003\u0003\u0019\u0019|WO]5feNC\u0017N\u001a;\u0014\u0007\u0005qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/\u001d\tqaZ3oKJL7-\u0003\u0002\u001a-\t)QKR;oG\u00061A(\u001b8jiz\"\u0012AC\u0001\u0011S6\u0004HNR8ve&,'o\u00155jMR,\"AH\u0016\u0015\u0007}!D\b\u0005\u0003!C\r\u001aS\"A\u0001\n\u0005\tB\"\u0001B%na2\u00042\u0001J\u0014*\u001b\u0005)#B\u0001\u0014\b\u0003\u0019a\u0017N\\1mO&\u0011\u0001&\n\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002+W1\u0001A!\u0002\u0017\u0004\u0005\u0004i#!\u0001+\u0012\u00059\n\u0004CA\b0\u0013\t\u0001\u0004CA\u0004O_RD\u0017N\\4\u0011\u0005=\u0011\u0014BA\u001a\u0011\u0005\r\te.\u001f\u0005\bk\r\t\t\u0011q\u00017\u0003))g/\u001b3f]\u000e,G%\r\t\u0004oiJS\"\u0001\u001d\u000b\u0005e:\u0011aB:u_J\fw-Z\u0005\u0003wa\u0012AAW3s_\"9QhAA\u0001\u0002\bq\u0014AC3wS\u0012,gnY3%eA\u0019qHQ\u0015\u000e\u0003\u0001S!!\u0011\t\u0002\u000fI,g\r\\3di&\u00111\t\u0011\u0002\t\u00072\f7o\u001d+bO\u0002"
)
public final class fourierShift {
   public static UFunc.UImpl implFourierShift(final Zero evidence$1, final ClassTag evidence$2) {
      return fourierShift$.MODULE$.implFourierShift(evidence$1, evidence$2);
   }

   public static Object withSink(final Object s) {
      return fourierShift$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return fourierShift$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return fourierShift$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return fourierShift$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return fourierShift$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return fourierShift$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return fourierShift$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return fourierShift$.MODULE$.apply(v, impl);
   }
}
