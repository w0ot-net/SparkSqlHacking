package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tA\u0002\\8h\u001d>\u0014X.\u00197ju\u0016T!AB\u0004\u0002\r1Lg.\u00197h\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005-\tQ\"A\u0003\u0003\u00191|wMT8s[\u0006d\u0017N_3\u0014\u0007\u0005qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/\u001d\tqaZ3oKJL7-\u0003\u0002\u001a-\t)QKR;oG\u00061A(\u001b8jiz\"\u0012AC\u0001\u0011Y><gj\u001c:nC2L'0Z%na2,\"AH\u0013\u0015\u0007}qs\u0007\u0005\u0003!C\r\u001aS\"A\u0001\n\u0005\tB\"\u0001B%na2\u0004\"\u0001J\u0013\r\u0001\u0011)ae\u0001b\u0001O\t\ta+\u0005\u0002)WA\u0011q\"K\u0005\u0003UA\u0011qAT8uQ&tw\r\u0005\u0002\u0010Y%\u0011Q\u0006\u0005\u0002\u0004\u0003:L\b\"B\u0018\u0004\u0001\b\u0001\u0014aC:pMRl\u0017\r_%na2\u0004B!M\u0011$i9\u00111BM\u0005\u0003g\u0015\tqa]8gi6\f\u0007\u0010\u0005\u0002\u0010k%\u0011a\u0007\u0005\u0002\u0007\t>,(\r\\3\t\u000ba\u001a\u00019A\u001d\u0002\u0005=\u0004\b#\u0002\u001eAGQ\u001acBA\u001e?\u001b\u0005a$BA\u001f\u0006\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0002@y\u0005)q\n]*vE&\u0011\u0011\t\u0007\u0002\u0006\u00136\u0004HN\r"
)
public final class logNormalize {
   public static UFunc.UImpl logNormalizeImpl(final UFunc.UImpl softmaxImpl, final UFunc.UImpl2 op) {
      return logNormalize$.MODULE$.logNormalizeImpl(softmaxImpl, op);
   }

   public static Object withSink(final Object s) {
      return logNormalize$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return logNormalize$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return logNormalize$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return logNormalize$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return logNormalize$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return logNormalize$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return logNormalize$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return logNormalize$.MODULE$.apply(v, impl);
   }
}
