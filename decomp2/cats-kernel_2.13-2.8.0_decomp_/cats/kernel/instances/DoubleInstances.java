package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f\u001d\u0002!\u0019!C\u0002Q\tyAi\\;cY\u0016Len\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\t\u0001Q\u0002\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0001\"A\u0004\f\n\u0005]y!\u0001B+oSR\f1dY1ug.+'O\\3m'R$wJ\u001d3fe\u001a{'\u000fR8vE2,W#\u0001\u000e\u0013\u0007miBE\u0002\u0003\u001d\u0001\u0001Q\"\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004c\u0001\u0010 C5\tq!\u0003\u0002!\u000f\t)qJ\u001d3feB\u0011aBI\u0005\u0003G=\u0011a\u0001R8vE2,\u0007c\u0001\u0010&C%\u0011ae\u0002\u0002\u0005\u0011\u0006\u001c\b.A\u000edCR\u001c8*\u001a:oK2\u001cF\u000fZ$s_V\u0004hi\u001c:E_V\u0014G.Z\u000b\u0002SA\u0019aDK\u0011\n\u0005-:!\u0001E\"p[6,H/\u0019;jm\u0016<%o\\;q\u0001"
)
public interface DoubleInstances {
   void cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdOrderForDouble_$eq(final Order x$1);

   void cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdGroupForDouble_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForDouble();

   CommutativeGroup catsKernelStdGroupForDouble();

   static void $init$(final DoubleInstances $this) {
      $this.cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdOrderForDouble_$eq(new DoubleOrder());
      $this.cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdGroupForDouble_$eq(new DoubleGroup());
   }
}
