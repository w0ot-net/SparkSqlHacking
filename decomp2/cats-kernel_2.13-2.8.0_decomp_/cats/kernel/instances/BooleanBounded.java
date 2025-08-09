package cats.kernel.instances;

import cats.kernel.LowerBounded$mcZ$sp;
import cats.kernel.UpperBounded$mcZ$sp;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\u00053\u0005C\u0003%\u0001\u0011\u00053E\u0001\bC_>dW-\u00198C_VtG-\u001a3\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u0019B\u0001A\u0007\u00145A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001F\u000b\u0018\u001b\u00059\u0011B\u0001\f\b\u00051aun^3s\u0005>,h\u000eZ3e!\tq\u0001$\u0003\u0002\u001a\u001f\t9!i\\8mK\u0006t\u0007c\u0001\u000b\u001c/%\u0011Ad\u0002\u0002\r+B\u0004XM\u001d\"pk:$W\rZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"A\u0004\u0011\n\u0005\u0005z!\u0001B+oSR\f\u0001\"\\5o\u0005>,h\u000eZ\u000b\u0002/\u0005AQ.\u0019=C_VtG\r"
)
public interface BooleanBounded extends LowerBounded$mcZ$sp, UpperBounded$mcZ$sp {
   // $FF: synthetic method
   static boolean minBound$(final BooleanBounded $this) {
      return $this.minBound();
   }

   default boolean minBound() {
      return this.minBound$mcZ$sp();
   }

   // $FF: synthetic method
   static boolean maxBound$(final BooleanBounded $this) {
      return $this.maxBound();
   }

   default boolean maxBound() {
      return this.maxBound$mcZ$sp();
   }

   // $FF: synthetic method
   static boolean minBound$mcZ$sp$(final BooleanBounded $this) {
      return $this.minBound$mcZ$sp();
   }

   default boolean minBound$mcZ$sp() {
      return false;
   }

   // $FF: synthetic method
   static boolean maxBound$mcZ$sp$(final BooleanBounded $this) {
      return $this.maxBound$mcZ$sp();
   }

   default boolean maxBound$mcZ$sp() {
      return true;
   }

   static void $init$(final BooleanBounded $this) {
   }
}
