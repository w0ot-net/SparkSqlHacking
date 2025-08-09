package cats.kernel.instances;

import cats.kernel.BoundedSemilattice;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f1\u0002!\u0019!C\u0002[\ty!)\u001b;TKRLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\t\u0001Q\u0002\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0001\"A\u0004\f\n\u0005]y!\u0001B+oSR\f1dY1ug.+'O\\3m'R$wJ\u001d3fe\u001a{'OQ5u'\u0016$X#\u0001\u000e\u0013\u0007mi\u0012F\u0002\u0003\u001d\u0001\u0001Q\"\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004c\u0001\u0010 C5\tq!\u0003\u0002!\u000f\ta\u0001+\u0019:uS\u0006dwJ\u001d3feB\u0011!eJ\u0007\u0002G)\u0011A%J\u0001\nS6lW\u000f^1cY\u0016T!AJ\b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002)G\t1!)\u001b;TKR\u00042A\b\u0016\"\u0013\tYsA\u0001\u0003ICND\u0017!I2biN\\UM\u001d8fYN#HmU3nS2\fG\u000f^5dK\u001a{'OQ5u'\u0016$X#\u0001\u0018\u0011\u0007yy\u0013%\u0003\u00021\u000f\t\u0011\"i\\;oI\u0016$7+Z7jY\u0006$H/[2f\u0001"
)
public interface BitSetInstances {
   void cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdOrderForBitSet_$eq(final PartialOrder x$1);

   void cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdSemilatticeForBitSet_$eq(final BoundedSemilattice x$1);

   PartialOrder catsKernelStdOrderForBitSet();

   BoundedSemilattice catsKernelStdSemilatticeForBitSet();

   static void $init$(final BitSetInstances $this) {
      $this.cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdOrderForBitSet_$eq(new BitSetPartialOrder());
      $this.cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdSemilatticeForBitSet_$eq(new BitSetSemilattice());
   }
}
