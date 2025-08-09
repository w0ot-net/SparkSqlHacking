package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0019\u0005!\u0004C\u0004)\u0001E\u0005I\u0011A\u0015\t\u000bQ\u0002A\u0011A\u001b\u0003\u0011A\u0013x\u000e_5nC2T!a\u0002\u0005\u0002\u0011A\u0014x\u000e_5nC2T!!\u0003\u0006\u0002\u0011=\u0004H/[7ju\u0016T\u0011aC\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001A\u0004\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\b\u0018\u0013\tA\u0002C\u0001\u0003V]&$\u0018\u0001\u00029s_b$2AF\u000e'\u0011\u0015a\"\u00011\u0001\u001e\u0003\u0005A\bc\u0001\u0010\"G5\tqD\u0003\u0002!\u0015\u00051A.\u001b8bY\u001eL!AI\u0010\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003\u001f\u0011J!!\n\t\u0003\r\u0011{WO\u00197f\u0011\u001d9#\u0001%AA\u0002\r\n1A\u001d5p\u00039\u0001(o\u001c=%I\u00164\u0017-\u001e7uII*\u0012A\u000b\u0016\u0003G-Z\u0013\u0001\f\t\u0003[Ij\u0011A\f\u0006\u0003_A\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005E\u0002\u0012AC1o]>$\u0018\r^5p]&\u00111G\f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017a\u0002<bYV,\u0017\t\u001e\u000b\u0003GYBQ\u0001\b\u0003A\u0002u\u0001"
)
public interface Proximal {
   void prox(final DenseVector x, final double rho);

   // $FF: synthetic method
   static double prox$default$2$(final Proximal $this) {
      return $this.prox$default$2();
   }

   default double prox$default$2() {
      return (double)1.0F;
   }

   // $FF: synthetic method
   static double valueAt$(final Proximal $this, final DenseVector x) {
      return $this.valueAt(x);
   }

   default double valueAt(final DenseVector x) {
      return (double)0.0F;
   }

   static void $init$(final Proximal $this) {
   }
}
