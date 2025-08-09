package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tAA]8mY*\u0011aaB\u0001\u0007Y&t\u0017\r\\4\u000b\u0003!\taA\u0019:fKj,7\u0001\u0001\t\u0003\u0017\u0005i\u0011!\u0002\u0002\u0005e>dGnE\u0002\u0002\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0019\u001b\u00051\"BA\f\b\u0003\u001d9WM\\3sS\u000eL!!\u0007\f\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005Q\u0011\u0001B5na2,\"A\b\u0015\u0015\u0005}!\u0004#\u0002\u0011\"GE\u001aS\"A\u0001\n\u0005\tB\"!B%na2\u0014\u0004cA\u0006%M%\u0011Q%\u0002\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002(Q1\u0001A!B\u0015\u0004\u0005\u0004Q#!\u0001+\u0012\u0005-r\u0003CA\b-\u0013\ti\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=y\u0013B\u0001\u0019\u0011\u0005\r\te.\u001f\t\u0003\u001fIJ!a\r\t\u0003\u0007%sG\u000fC\u00046\u0007\u0005\u0005\t9\u0001\u001c\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u00028u\u0019j\u0011\u0001\u000f\u0006\u0003sA\tqA]3gY\u0016\u001cG/\u0003\u0002<q\tA1\t\\1tgR\u000bw\r"
)
public final class roll {
   public static UFunc.UImpl2 impl(final ClassTag evidence$1) {
      return roll$.MODULE$.impl(evidence$1);
   }

   public static Object withSink(final Object s) {
      return roll$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return roll$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return roll$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return roll$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return roll$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return roll$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return roll$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return roll$.MODULE$.apply(v, impl);
   }
}
