package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tq\u0002\\8h\u0003:$gj\u001c:nC2L'0\u001a\u0006\u0003\r\u001d\ta\u0001\\5oC2<'\"\u0001\u0005\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"aC\u0001\u000e\u0003\u0015\u0011q\u0002\\8h\u0003:$gj\u001c:nC2L'0Z\n\u0004\u00039!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\r\u0005\u0002\u001615\taC\u0003\u0002\u0018\u000f\u00059q-\u001a8fe&\u001c\u0017BA\r\u0017\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t!\"\u0001\tm_\u001etuN]7bY&TX-S7qYV\u0011a$\n\u000b\u0004?9\n\u0005\u0003\u0002\u0011\"G\rj\u0011!A\u0005\u0003Ea\u0011A!S7qYB\u0011A%\n\u0007\u0001\t\u001513A1\u0001(\u0005\u00051\u0016C\u0001\u0015,!\ty\u0011&\u0003\u0002+!\t9aj\u001c;iS:<\u0007CA\b-\u0013\ti\u0003CA\u0002B]fDQaL\u0002A\u0004A\nq\u0001\\8h\u00136\u0004H\u000e\u0005\u00032C\r\u001acB\u0001\u001a?\u001d\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011q'C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!AO\u0004\u0002\u00119,X.\u001a:jGNL!\u0001P\u001f\u0002\u000fA\f7m[1hK*\u0011!hB\u0005\u0003\u007f\u0001\u000b1\u0001\\8h\u0015\taT\bC\u0003\u001d\u0007\u0001\u000f!\t\u0005\u0003DC\r\u001acBA\u0006E\u0013\t)U!\u0001\u0007m_\u001etuN]7bY&TX\r"
)
public final class logAndNormalize {
   public static UFunc.UImpl logNormalizeImpl(final UFunc.UImpl logImpl, final UFunc.UImpl logNormalizeImpl) {
      return logAndNormalize$.MODULE$.logNormalizeImpl(logImpl, logNormalizeImpl);
   }

   public static Object withSink(final Object s) {
      return logAndNormalize$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return logAndNormalize$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return logAndNormalize$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return logAndNormalize$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return logAndNormalize$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return logAndNormalize$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return logAndNormalize$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return logAndNormalize$.MODULE$.apply(v, impl);
   }
}
