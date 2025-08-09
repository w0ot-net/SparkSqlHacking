package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\t!\"Y2dk6,H.\u0019;f\u0015\t1q!\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0011\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\f\u00035\tQA\u0001\u0006bG\u000e,X.\u001e7bi\u0016\u001c2!\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0011Q\u0003G\u0007\u0002-)\u0011qcB\u0001\bO\u0016tWM]5d\u0013\tIbCA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0015\u0005aAM^!dGVlW\u000f\\1uKV\u0011a\u0004\u000b\u000b\u0004?EJ\u0004\u0003\u0002\u0011\"G\rj\u0011!A\u0005\u0003Ea\u0011A!S7qYB\u00191\u0002\n\u0014\n\u0005\u0015*!a\u0003#f]N,g+Z2u_J\u0004\"a\n\u0015\r\u0001\u0011)\u0011f\u0001b\u0001U\t\tA+\u0005\u0002,]A\u0011q\u0002L\u0005\u0003[A\u0011qAT8uQ&tw\r\u0005\u0002\u0010_%\u0011\u0001\u0007\u0005\u0002\u0004\u0003:L\b\"\u0002\u001a\u0004\u0001\b\u0019\u0014\u0001\u0002>fe>\u00042\u0001N\u001c'\u001b\u0005)$B\u0001\u001c\b\u0003\u001d\u0019Ho\u001c:bO\u0016L!\u0001O\u001b\u0003\ti+'o\u001c\u0005\u0006u\r\u0001\u001daO\u0001\u0004C\u0012$\u0007#\u0002\u001fCM\u00192cBA\u001fA\u001b\u0005q$BA \u0006\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0002B}\u0005)q\n]!eI&\u00111\t\u0007\u0002\u0006\u00136\u0004HN\r"
)
public final class accumulate {
   public static UFunc.UImpl dvAccumulate(final Zero zero, final UFunc.UImpl2 add) {
      return accumulate$.MODULE$.dvAccumulate(zero, add);
   }

   public static Object withSink(final Object s) {
      return accumulate$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return accumulate$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return accumulate$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return accumulate$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return accumulate$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return accumulate$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return accumulate$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return accumulate$.MODULE$.apply(v, impl);
   }
}
