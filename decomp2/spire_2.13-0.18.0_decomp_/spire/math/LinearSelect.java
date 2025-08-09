package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0006u\tA\u0002T5oK\u0006\u00148+\u001a7fGRT!AB\u0004\u0002\t5\fG\u000f\u001b\u0006\u0002\u0011\u0005)1\u000f]5sK\u000e\u0001\u0001CA\u0006\u0002\u001b\u0005)!\u0001\u0004'j]\u0016\f'oU3mK\u000e$8\u0003B\u0001\u000f)]\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u0006\u0016\u0013\t1RA\u0001\u0006TK2,7\r\u001e'jW\u0016\u0004\"a\u0003\r\n\u0005e)!A\u0006%jO\"\u0014%/\u00198dQ&tw-T3eS\u0006twJZ\u001b\u0002\rqJg.\u001b;?)\u0005Q\u0011\u0001D1qaJ|\u00070T3eS\u0006tWC\u0001\u0010#)\u0015y\u0012IR&N)\t\u0001s\u0006\u0005\u0002\"E1\u0001A!C\u0012\u0004A\u0003\u0005\tQ1\u0001%\u0005\u0005\t\u0015CA\u0013)!\tya%\u0003\u0002(!\t9aj\u001c;iS:<\u0007CA\b*\u0013\tQ\u0003CA\u0002B]fD#A\t\u0017\u0011\u0005=i\u0013B\u0001\u0018\u0011\u0005-\u0019\b/Z2jC2L'0\u001a3\t\u000fA\u001a\u0011\u0011!a\u0002c\u0005QQM^5eK:\u001cW\rJ\u001c\u0011\u0007Ir\u0004E\u0004\u00024w9\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003o%\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0005\n\u0005i:\u0011aB1mO\u0016\u0014'/Y\u0005\u0003yu\nq\u0001]1dW\u0006<WM\u0003\u0002;\u000f%\u0011q\b\u0011\u0002\u0006\u001fJ$WM\u001d\u0006\u0003yuBQAQ\u0002A\u0002\r\u000bA\u0001Z1uCB\u0019q\u0002\u0012\u0011\n\u0005\u0015\u0003\"!B!se\u0006L\b\"B$\u0004\u0001\u0004A\u0015\u0001\u00027fMR\u0004\"aD%\n\u0005)\u0003\"aA%oi\")Aj\u0001a\u0001\u0011\u0006)!/[4ii\")aj\u0001a\u0001\u0011\u000611\u000f\u001e:jI\u0016\u0004"
)
public final class LinearSelect {
   public static Object approxMedian(final Object data, final int left, final int right, final int stride, final Order evidence$7) {
      return LinearSelect$.MODULE$.approxMedian(data, left, right, stride, evidence$7);
   }

   public static void mo5(final Object data, final int offset, final int stride, final Order o) {
      LinearSelect$.MODULE$.mo5(data, offset, stride, o);
   }

   public static int partition(final Object data, final int left, final int right, final int stride, final Object m, final Order o) {
      return LinearSelect$.MODULE$.partition(data, left, right, stride, m, o);
   }

   public static int equalSpan(final Object data, final int offset, final int stride, final Order o) {
      return LinearSelect$.MODULE$.equalSpan(data, offset, stride, o);
   }

   public static void sort(final Object data, final int left, final int right, final int stride, final Order o) {
      LinearSelect$.MODULE$.sort(data, left, right, stride, o);
   }

   public static void select(final Object data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      LinearSelect$.MODULE$.select(data, k, evidence$4, evidence$5);
   }
}
