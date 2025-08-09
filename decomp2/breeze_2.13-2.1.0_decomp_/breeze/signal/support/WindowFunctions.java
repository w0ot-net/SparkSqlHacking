package breeze.signal.support;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;Qa\u0003\u0007\t\u0002M1Q!\u0006\u0007\t\u0002YAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001BqaM\u0001\u0012\u0002\u0013\u0005A\u0007C\u0004@\u0003E\u0005I\u0011\u0001\u001b\t\u000b\u0001\u000bA\u0011A!\t\u000f%\u000b\u0011\u0013!C\u0001i!9!*AI\u0001\n\u0003!\u0004bB&\u0002#\u0003%\t\u0001\u000e\u0005\u0006\u0019\u0006!\t!T\u0001\u0010/&tGm\\<Gk:\u001cG/[8og*\u0011QBD\u0001\bgV\u0004\bo\u001c:u\u0015\ty\u0001#\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002#\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u0015\u00035\tABA\bXS:$wn\u001e$v]\u000e$\u0018n\u001c8t'\t\tq\u0003\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\tQ\u0002[1n[&twmV5oI><H\u0003B\u0011+_E\u00022AI\u0013(\u001b\u0005\u0019#B\u0001\u0013\u0011\u0003\u0019a\u0017N\\1mO&\u0011ae\t\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\u0019Q%\u0011\u0011&\u0007\u0002\u0007\t>,(\r\\3\t\u000b-\u001a\u0001\u0019\u0001\u0017\u0002\u00039\u0004\"\u0001G\u0017\n\u00059J\"aA%oi\"9\u0001g\u0001I\u0001\u0002\u00049\u0013!B1ma\"\f\u0007b\u0002\u001a\u0004!\u0003\u0005\raJ\u0001\u0005E\u0016$\u0018-A\fiC6l\u0017N\\4XS:$wn\u001e\u0013eK\u001a\fW\u000f\u001c;%eU\tQG\u000b\u0002(m-\nq\u0007\u0005\u00029{5\t\u0011H\u0003\u0002;w\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003ye\t!\"\u00198o_R\fG/[8o\u0013\tq\u0014HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fq\u0003[1n[&twmV5oI><H\u0005Z3gCVdG\u000fJ\u001a\u0002\u001d\td\u0017mY6nC:<\u0016N\u001c3poR)\u0011EQ\"F\u000f\")1F\u0002a\u0001Y!9AI\u0002I\u0001\u0002\u00049\u0013AA11\u0011\u001d1e\u0001%AA\u0002\u001d\n!!Y\u0019\t\u000f!3\u0001\u0013!a\u0001O\u0005\u0011\u0011MM\u0001\u0019E2\f7m[7b]^Kg\u000eZ8xI\u0011,g-Y;mi\u0012\u0012\u0014\u0001\u00072mC\u000e\\W.\u00198XS:$wn\u001e\u0013eK\u001a\fW\u000f\u001c;%g\u0005A\"\r\\1dW6\fgnV5oI><H\u0005Z3gCVdG\u000f\n\u001b\u0002\u001b!\fgN\\5oO^Kg\u000eZ8x)\t\tc\nC\u0003,\u0015\u0001\u0007A\u0006"
)
public final class WindowFunctions {
   public static DenseVector hanningWindow(final int n) {
      return WindowFunctions$.MODULE$.hanningWindow(n);
   }

   public static double blackmanWindow$default$4() {
      return WindowFunctions$.MODULE$.blackmanWindow$default$4();
   }

   public static double blackmanWindow$default$3() {
      return WindowFunctions$.MODULE$.blackmanWindow$default$3();
   }

   public static double blackmanWindow$default$2() {
      return WindowFunctions$.MODULE$.blackmanWindow$default$2();
   }

   public static DenseVector blackmanWindow(final int n, final double a0, final double a1, final double a2) {
      return WindowFunctions$.MODULE$.blackmanWindow(n, a0, a1, a2);
   }

   public static double hammingWindow$default$3() {
      return WindowFunctions$.MODULE$.hammingWindow$default$3();
   }

   public static double hammingWindow$default$2() {
      return WindowFunctions$.MODULE$.hammingWindow$default$2();
   }

   public static DenseVector hammingWindow(final int n, final double alpha, final double beta) {
      return WindowFunctions$.MODULE$.hammingWindow(n, alpha, beta);
   }
}
