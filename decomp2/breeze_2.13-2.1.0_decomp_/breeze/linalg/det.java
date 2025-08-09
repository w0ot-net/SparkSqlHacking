package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\t1\u0001Z3u\u0015\t1q!\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0011\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\f\u00035\tQAA\u0002eKR\u001c2!\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0011Q\u0003G\u0007\u0002-)\u0011qcB\u0001\bO\u0016tWM]5d\u0013\tIbCA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0015\u0005i1-\u00198EKR,6/\u001b8h\u0019V+\"AH\u0013\u0015\u0005}\t\u0004\u0003\u0002\u0011\"G9j\u0011!A\u0005\u0003Ea\u0011A!S7qYB\u0011A%\n\u0007\u0001\t\u001513A1\u0001(\u0005\u0005!\u0016C\u0001\u0015,!\ty\u0011&\u0003\u0002+!\t9aj\u001c;iS:<\u0007CA\b-\u0013\ti\u0003CA\u0002B]f\u0004\"aD\u0018\n\u0005A\u0002\"A\u0002#pk\ndW\rC\u00033\u0007\u0001\u000f1'\u0001\u0004mk&k\u0007\u000f\u001c\t\u0005i\u0005\u001a3H\u0004\u00026q9\u00111BN\u0005\u0003o\u0015\t!\u0001T+\n\u0005eR\u0014!\u00039sS6LG/\u001b<f\u0015\t9T\u0001\u0005\u0003\u0010yy\n\u0015BA\u001f\u0011\u0005\u0019!V\u000f\u001d7feA\u00191b\u0010\u0018\n\u0005\u0001+!a\u0003#f]N,W*\u0019;sSb\u00042a\u0004\"E\u0013\t\u0019\u0005CA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0010\u000b&\u0011a\t\u0005\u0002\u0004\u0013:$\b"
)
public final class det {
   public static UFunc.UImpl canDetUsingLU(final UFunc.UImpl luImpl) {
      return det$.MODULE$.canDetUsingLU(luImpl);
   }

   public static Object withSink(final Object s) {
      return det$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return det$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return det$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return det$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return det$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return det$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return det$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return det$.MODULE$.apply(v, impl);
   }
}
