package cats.kernel.instances;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007I1\u0001\r\u0003#\u0011+\u0017\r\u001a7j]\u0016Len\u001d;b]\u000e,7O\u0003\u0002\u0006\r\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\taa[3s]\u0016d'\"A\u0005\u0002\t\r\fGo]\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0001\"!D\u000b\n\u0005Yq!\u0001B+oSR\fQdY1ug.+'O\\3m'R$wJ\u001d3fe\u001a{'\u000fR3bI2Lg.Z\u000b\u00023I)!\u0004\b\u0015,]\u0019!1\u0004\u0001\u0001\u001a\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rib\u0004I\u0007\u0002\r%\u0011qD\u0002\u0002\u0006\u001fJ$WM\u001d\t\u0003C\u0019j\u0011A\t\u0006\u0003G\u0011\n\u0001\u0002Z;sCRLwN\u001c\u0006\u0003K9\t!bY8oGV\u0014(/\u001a8u\u0013\t9#E\u0001\u0005EK\u0006$G.\u001b8f!\ri\u0012\u0006I\u0005\u0003U\u0019\u0011A\u0001S1tQB\u0019Q\u0004\f\u0011\n\u000552!\u0001\u0004'po\u0016\u0014(i\\;oI\u0016$\u0007cA\u000f0A%\u0011\u0001G\u0002\u0002\r+B\u0004XM\u001d\"pk:$W\r\u001a"
)
public interface DeadlineInstances {
   void cats$kernel$instances$DeadlineInstances$_setter_$catsKernelStdOrderForDeadline_$eq(final Order x$1);

   Order catsKernelStdOrderForDeadline();

   static void $init$(final DeadlineInstances $this) {
      $this.cats$kernel$instances$DeadlineInstances$_setter_$catsKernelStdOrderForDeadline_$eq(new DeadlineOrder());
   }
}
