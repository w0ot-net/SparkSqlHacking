package breeze.optimize.flow;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513q\u0001D\u0007\u0011\u0002\u0007\u0005A\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003\"\u0001\u0019\u0005!\u0005C\u0003/\u0001\u0019\u0005!\u0005C\u00030\u0001\u0019\u0005\u0001GB\u0004@\u0001A\u0005\u0019\u0011\u0001!\t\u000bq)A\u0011A\u000f\t\u000b\u0005+A\u0011\u0001\"\t\u000b\u0019+A\u0011\u0001\"\t\u000b\u001d+A\u0011\u0001\"\t\u000b!+a\u0011\u0001\u0012\t\u000b%+a\u0011\u0001\u0012\u0003\u0013\u0019cwn^$sCBD'B\u0001\b\u0010\u0003\u00111Gn\\<\u000b\u0005A\t\u0012\u0001C8qi&l\u0017N_3\u000b\u0003I\taA\u0019:fKj,7\u0001A\u000b\u0003+\u0015\u001a\"\u0001\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\ta\u0004\u0005\u0002\u0018?%\u0011\u0001\u0005\u0007\u0002\u0005+:LG/\u0001\u0004t_V\u00148-Z\u000b\u0002GA\u0011A%\n\u0007\u0001\t\u00151\u0003A1\u0001(\u0005\u0005q\u0015C\u0001\u0015,!\t9\u0012&\u0003\u0002+1\t9aj\u001c;iS:<\u0007CA\f-\u0013\ti\u0003DA\u0002B]f\fAa]5oW\u0006IQ\rZ4fg\u001a\u0013x.\u001c\u000b\u0003c)\u00032A\r\u001e>\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027'\u00051AH]8pizJ\u0011!G\u0005\u0003sa\tq\u0001]1dW\u0006<W-\u0003\u0002<y\tA\u0011\n^3sCR|'O\u0003\u0002:1A\u0011a(B\u0007\u0002\u0001\t!Q\tZ4f'\t)a#\u0001\u0005dCB\f7-\u001b;z+\u0005\u0019\u0005CA\fE\u0013\t)\u0005D\u0001\u0004E_V\u0014G.Z\u0001\u0005G>\u001cH/\u0001\u0003hC&t\u0017\u0001\u00025fC\u0012\fA\u0001^1jY\")1\n\u0002a\u0001G\u0005\ta\u000e"
)
public interface FlowGraph {
   Object source();

   Object sink();

   Iterator edgesFrom(final Object n);

   static void $init$(final FlowGraph $this) {
   }

   public interface Edge {
      // $FF: synthetic method
      static double capacity$(final Edge $this) {
         return $this.capacity();
      }

      default double capacity() {
         return Double.POSITIVE_INFINITY;
      }

      // $FF: synthetic method
      static double cost$(final Edge $this) {
         return $this.cost();
      }

      default double cost() {
         return (double)0.0F;
      }

      // $FF: synthetic method
      static double gain$(final Edge $this) {
         return $this.gain();
      }

      default double gain() {
         return (double)1.0F;
      }

      Object head();

      Object tail();

      // $FF: synthetic method
      FlowGraph breeze$optimize$flow$FlowGraph$Edge$$$outer();

      static void $init$(final Edge $this) {
      }
   }
}
