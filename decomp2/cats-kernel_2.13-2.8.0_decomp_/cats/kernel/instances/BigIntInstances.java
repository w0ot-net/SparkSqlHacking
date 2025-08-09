package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000fM\u0002!\u0019!C\u0002i\ty!)[4J]RLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\t\u0001Q\u0002\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0001\"A\u0004\f\n\u0005]y!\u0001B+oSR\f1dY1ug.+'O\\3m'R$wJ\u001d3fe\u001a{'OQ5h\u0013:$X#\u0001\u000e\u0013\tmiR\u0006\r\u0004\u00059\u0001\u0001!D\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002\u001f?\u0005j\u0011aB\u0005\u0003A\u001d\u0011Qa\u0014:eKJ\u0004\"A\t\u0016\u000f\u0005\rBcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\f\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002*\u001f\u00059\u0001/Y2lC\u001e,\u0017BA\u0016-\u0005\u0019\u0011\u0015nZ%oi*\u0011\u0011f\u0004\t\u0004=9\n\u0013BA\u0018\b\u0005\u0011A\u0015m\u001d5\u0011\u0007y\t\u0014%\u0003\u00023\u000f\t\u0019RK\u001c2pk:$W\rZ#ok6,'/\u00192mK\u0006Y2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012<%o\\;q\r>\u0014()[4J]R,\u0012!\u000e\t\u0004=Y\n\u0013BA\u001c\b\u0005A\u0019u.\\7vi\u0006$\u0018N^3He>,\b\u000f"
)
public interface BigIntInstances {
   void cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdOrderForBigInt_$eq(final Order x$1);

   void cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdGroupForBigInt_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForBigInt();

   CommutativeGroup catsKernelStdGroupForBigInt();

   static void $init$(final BigIntInstances $this) {
      $this.cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdOrderForBigInt_$eq(new BigIntOrder());
      $this.cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdGroupForBigInt_$eq(new BigIntGroup());
   }
}
