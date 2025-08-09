package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTranspose;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ\u0001I\u0001\u0005\u0002\u0005BqAI\u0001C\u0002\u0013\r1\u0005\u0003\u0004/\u0003\u0001\u0006I\u0001\n\u0005\b_\u0005\u0011\r\u0011b\u00011\u0011\u00191\u0014\u0001)A\u0005c\u0005!\u0001/\u001b8w\u0015\tI!\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0017\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000f\u00035\t\u0001B\u0001\u0003qS:48\u0003B\u0001\u0012/u\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u000b\u0003\u001d9WM\\3sS\u000eL!\u0001H\r\u0003\u000bU3UO\\2\u0011\u00059q\u0012BA\u0010\t\u0005-\u0001\u0018N\u001c<M_^\u0004&/[8\u0002\rqJg.\u001b;?)\u0005i\u0011!\u00059j]Z4%o\\7T-\u0012{f\t\\8biV\tA\u0005\u0005\u0003&M!BS\"A\u0001\n\u0005\u001dZ\"\u0001B%na2\u00042AD\u0015,\u0013\tQ\u0003BA\u0006EK:\u001cX-T1ue&D\bC\u0001\n-\u0013\ti3CA\u0003GY>\fG/\u0001\nqS:4hI]8n'Z#uL\u00127pCR\u0004\u0013A\u00059j]Z4%o\\7T-\u0012{Fi\\;cY\u0016,\u0012!\r\t\u0005K\u0019\u0012$\u0007E\u0002\u000fSM\u0002\"A\u0005\u001b\n\u0005U\u001a\"A\u0002#pk\ndW-A\nqS:4hI]8n'Z#u\fR8vE2,\u0007\u0005"
)
public final class pinv {
   public static UFunc.UImpl pinvFromSVD_Double() {
      return pinv$.MODULE$.pinvFromSVD_Double();
   }

   public static UFunc.UImpl pinvFromSVD_Float() {
      return pinv$.MODULE$.pinvFromSVD_Float();
   }

   public static UFunc.UImpl implFromTransposeAndSolve(final Function1 numericT, final CanTranspose trans, final Function1 numericTrans, final UFunc.UImpl2 mul, final Function1 numericMulRes, final UFunc.UImpl2 solve) {
      return pinv$.MODULE$.implFromTransposeAndSolve(numericT, trans, numericTrans, mul, numericMulRes, solve);
   }

   public static Object withSink(final Object s) {
      return pinv$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return pinv$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return pinv$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return pinv$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return pinv$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return pinv$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return pinv$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return pinv$.MODULE$.apply(v, impl);
   }
}
