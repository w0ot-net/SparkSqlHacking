package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001\n\u0011#\\5oW><8o[5ESN$\u0018M\\2f\u0015\t1q!\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0011\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\f\u00035\tQAA\tnS:\\wn^:lS\u0012K7\u000f^1oG\u0016\u001cB!\u0001\b\u00155A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"!\u0006\r\u000e\u0003YQ!aF\u0004\u0002\u000f\u001d,g.\u001a:jG&\u0011\u0011D\u0006\u0002\u0006+\u001a+hn\u0019\t\u0003\u0017mI!\u0001H\u0003\u000315Lgn[8xg.LG)[:uC:\u001cW\rT8x!JLw.\u0001\u0004=S:LGO\u0010\u000b\u0002\u0015\u00059S.\u001b8l_^\u001c8.\u001b#jgR\fgnY3Ge>l7+\u001e2ue\u0006\u001cG/[8o\u0003:$gj\u001c:n+\u0011\t\u0003FM\"\u0015\u0007\t:T\t\u0005\u0004$I\u0019\nD\u0007N\u0007\u0002\u0003%\u0011Q\u0005\u0007\u0002\u0006\u00136\u0004Hn\r\t\u0003O!b\u0001\u0001B\u0003*\u0007\t\u0007!FA\u0001U#\tYc\u0006\u0005\u0002\u0010Y%\u0011Q\u0006\u0005\u0002\b\u001d>$\b.\u001b8h!\tyq&\u0003\u00021!\t\u0019\u0011I\\=\u0011\u0005\u001d\u0012D!B\u001a\u0004\u0005\u0004Q#!A+\u0011\u0005=)\u0014B\u0001\u001c\u0011\u0005\u0019!u.\u001e2mK\")\u0001h\u0001a\u0002s\u0005\u00191/\u001e2\u0011\u000bi\u0002e%\r\"\u000f\u0005mrT\"\u0001\u001f\u000b\u0005u*\u0011!C8qKJ\fGo\u001c:t\u0013\tyD(A\u0003PaN+(-\u0003\u0002B1\t)\u0011*\u001c9meA\u0011qe\u0011\u0003\u0006\t\u000e\u0011\rA\u000b\u0002\u0002-\")ai\u0001a\u0002\u000f\u0006Aan\u001c:n\u00136\u0004H\u000eE\u0003I\u0001\n#DG\u0004\u0002\f\u0013&\u0011!*B\u0001\u0005]>\u0014X\u000e"
)
public final class minkowskiDistance {
   public static UFunc.UImpl3 minkowskiDistanceFromSubtractionAndNorm(final UFunc.UImpl2 sub, final UFunc.UImpl2 normImpl) {
      return minkowskiDistance$.MODULE$.minkowskiDistanceFromSubtractionAndNorm(sub, normImpl);
   }

   public static UFunc.UImpl3 minkowskiDistanceFromZippedValues(final UFunc.UImpl2 zipImpl) {
      return minkowskiDistance$.MODULE$.minkowskiDistanceFromZippedValues(zipImpl);
   }

   public static Object withSink(final Object s) {
      return minkowskiDistance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return minkowskiDistance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return minkowskiDistance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return minkowskiDistance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return minkowskiDistance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return minkowskiDistance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return minkowskiDistance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return minkowskiDistance$.MODULE$.apply(v, impl);
   }
}
