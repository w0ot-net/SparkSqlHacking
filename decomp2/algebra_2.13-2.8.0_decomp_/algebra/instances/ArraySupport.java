package algebra.instances;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015<Qa\u0002\u0005\t\n51Qa\u0004\u0005\t\nAAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\niAQ\u0001I\u0001\u0005\u0002\u0005BQAR\u0001\u0005\u0002\u001dCQ\u0001V\u0001\u0005\u0002U\u000bA\"\u0011:sCf\u001cV\u000f\u001d9peRT!!\u0003\u0006\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"A\u0006\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001\u0001C\u0001\b\u0002\u001b\u0005A!\u0001D!se\u0006L8+\u001e9q_J$8CA\u0001\u0012!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!D\u0001\u0007g&<g.^7\u0015\u0005mq\u0002C\u0001\n\u001d\u0013\ti2CA\u0002J]RDQaH\u0002A\u0002m\t\u0011\u0001_\u0001\u0004KF4XC\u0001\u00124)\r\u0019\u0003\t\u0012\u000b\u0003I\u001d\u0002\"AE\u0013\n\u0005\u0019\u001a\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006Q\u0011\u0001\u001d!K\u0001\u0003KZ\u00042A\u000b\u00182\u001d\tYC&D\u0001\u000b\u0013\ti#\"A\u0004qC\u000e\\\u0017mZ3\n\u0005=\u0002$AA#r\u0015\ti#\u0002\u0005\u00023g1\u0001A!\u0003\u001b\u0005A\u0003\u0005\tQ1\u00016\u0005\u0005\t\u0015C\u0001\u001c:!\t\u0011r'\u0003\u00029'\t9aj\u001c;iS:<\u0007C\u0001\n;\u0013\tY4CA\u0002B]fD#aM\u001f\u0011\u0005Iq\u0014BA \u0014\u0005-\u0019\b/Z2jC2L'0\u001a3\t\u000b}!\u0001\u0019A!\u0011\u0007I\u0011\u0015'\u0003\u0002D'\t)\u0011I\u001d:bs\")Q\t\u0002a\u0001\u0003\u0006\t\u00110A\u0004d_6\u0004\u0018M]3\u0016\u0005!{EcA%R'R\u00111D\u0013\u0005\u0006Q\u0015\u0001\u001da\u0013\t\u0004U1s\u0015BA'1\u0005\u0015y%\u000fZ3s!\t\u0011t\nB\u00055\u000b\u0001\u0006\t\u0011!b\u0001k!\u0012q*\u0010\u0005\u0006?\u0015\u0001\rA\u0015\t\u0004%\ts\u0005\"B#\u0006\u0001\u0004\u0011\u0016A\u00049beRL\u0017\r\\\"p[B\f'/Z\u000b\u0003-\u0002$2a\u00162e)\tA6\f\u0005\u0002\u00133&\u0011!l\u0005\u0002\u0007\t>,(\r\\3\t\u000b!2\u00019\u0001/\u0011\u0007)jv,\u0003\u0002_a\ta\u0001+\u0019:uS\u0006dwJ\u001d3feB\u0011!\u0007\u0019\u0003\ni\u0019\u0001\u000b\u0011!AC\u0002UB#\u0001Y\u001f\t\u000b}1\u0001\u0019A2\u0011\u0007I\u0011u\fC\u0003F\r\u0001\u00071\r"
)
public final class ArraySupport {
   public static double partialCompare(final Object x, final Object y, final PartialOrder ev) {
      return ArraySupport$.MODULE$.partialCompare(x, y, ev);
   }

   public static int compare(final Object x, final Object y, final Order ev) {
      return ArraySupport$.MODULE$.compare(x, y, ev);
   }

   public static boolean eqv(final Object x, final Object y, final Eq ev) {
      return ArraySupport$.MODULE$.eqv(x, y, ev);
   }
}
