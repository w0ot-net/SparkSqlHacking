package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0006u\t1\"U;jG.\u001cV\r\\3di*\u0011aaB\u0001\u0005[\u0006$\bNC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0001\"aC\u0001\u000e\u0003\u0015\u00111\"U;jG.\u001cV\r\\3diN!\u0011A\u0004\u000b\u0018!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u00111\"F\u0005\u0003-\u0015\u0011!bU3mK\u000e$H*[6f!\tY\u0001$\u0003\u0002\u001a\u000b\t1\u0002*[4i\u0005J\fgn\u00195j]\u001elU\rZ5b]>3W'\u0001\u0004=S:LGO\u0010\u000b\u0002\u0015\u0005a\u0011\r\u001d9s_blU\rZ5b]V\u0011aD\t\u000b\u0006?\u000535*\u0014\u000b\u0003A=\u0002\"!\t\u0012\r\u0001\u0011I1e\u0001Q\u0001\u0002\u0003\u0015\r\u0001\n\u0002\u0002\u0003F\u0011Q\u0005\u000b\t\u0003\u001f\u0019J!a\n\t\u0003\u000f9{G\u000f[5oOB\u0011q\"K\u0005\u0003UA\u00111!\u00118zQ\t\u0011C\u0006\u0005\u0002\u0010[%\u0011a\u0006\u0005\u0002\fgB,7-[1mSj,G\rC\u00041\u0007\u0005\u0005\t9A\u0019\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\bE\u00023}\u0001r!aM\u001e\u000f\u0005QJdBA\u001b9\u001b\u00051$BA\u001c\n\u0003\u0019a$o\\8u}%\t\u0001\"\u0003\u0002;\u000f\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u001f>\u0003\u001d\u0001\u0018mY6bO\u0016T!AO\u0004\n\u0005}\u0002%!B(sI\u0016\u0014(B\u0001\u001f>\u0011\u0015\u00115\u00011\u0001D\u0003\u0011!\u0017\r^1\u0011\u0007=!\u0005%\u0003\u0002F!\t)\u0011I\u001d:bs\")qi\u0001a\u0001\u0011\u0006!A.\u001a4u!\ty\u0011*\u0003\u0002K!\t\u0019\u0011J\u001c;\t\u000b1\u001b\u0001\u0019\u0001%\u0002\u000bILw\r\u001b;\t\u000b9\u001b\u0001\u0019\u0001%\u0002\rM$(/\u001b3f\u0001"
)
public final class QuickSelect {
   public static Object approxMedian(final Object data, final int left, final int right, final int stride, final Order evidence$8) {
      return QuickSelect$.MODULE$.approxMedian(data, left, right, stride, evidence$8);
   }

   public static void mo5(final Object data, final int offset, final int stride, final Order o) {
      QuickSelect$.MODULE$.mo5(data, offset, stride, o);
   }

   public static int partition(final Object data, final int left, final int right, final int stride, final Object m, final Order o) {
      return QuickSelect$.MODULE$.partition(data, left, right, stride, m, o);
   }

   public static int equalSpan(final Object data, final int offset, final int stride, final Order o) {
      return QuickSelect$.MODULE$.equalSpan(data, offset, stride, o);
   }

   public static void sort(final Object data, final int left, final int right, final int stride, final Order o) {
      QuickSelect$.MODULE$.sort(data, left, right, stride, o);
   }

   public static void select(final Object data, final int k, final Order evidence$4, final ClassTag evidence$5) {
      QuickSelect$.MODULE$.select(data, k, evidence$4, evidence$5);
   }
}
