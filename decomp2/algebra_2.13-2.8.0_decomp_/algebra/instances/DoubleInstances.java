package algebra.instances;

import algebra.lattice.DistributiveLattice;
import algebra.lattice.DistributiveLattice$;
import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f%\u0002!\u0019!C\u0001U\tyAi\\;cY\u0016Len\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0002\u0011\u00059\u0011\r\\4fEJ\f7\u0001A\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\r\u0005\u0002\u001315\t1C\u0003\u0002\u0007))\u0011QCF\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003]\tAaY1ug&\u0011AaE\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003m\u0001\"\u0001\u0004\u000f\n\u0005ui!\u0001B+oSR\fQ\u0002Z8vE2,\u0017\t\\4fEJ\fW#\u0001\u0011\u0011\u0007\u0005\"c%D\u0001#\u0015\t\u0019s!\u0001\u0003sS:<\u0017BA\u0013#\u0005\u00151\u0015.\u001a7e!\taq%\u0003\u0002)\u001b\t1Ai\\;cY\u0016\f1\u0003R8vE2,W*\u001b8NCbd\u0015\r\u001e;jG\u0016,\u0012a\u000b\t\u0004Y=2S\"A\u0017\u000b\u00059:\u0011a\u00027biRL7-Z\u0005\u0003a5\u00121\u0003R5tiJL'-\u001e;jm\u0016d\u0015\r\u001e;jG\u0016\u0004"
)
public interface DoubleInstances extends cats.kernel.instances.DoubleInstances {
   void algebra$instances$DoubleInstances$_setter_$doubleAlgebra_$eq(final Field x$1);

   void algebra$instances$DoubleInstances$_setter_$DoubleMinMaxLattice_$eq(final DistributiveLattice x$1);

   Field doubleAlgebra();

   DistributiveLattice DoubleMinMaxLattice();

   static void $init$(final DoubleInstances $this) {
      $this.algebra$instances$DoubleInstances$_setter_$doubleAlgebra_$eq(new DoubleAlgebra());
      $this.algebra$instances$DoubleInstances$_setter_$DoubleMinMaxLattice_$eq(DistributiveLattice$.MODULE$.minMax$mDc$sp($this.catsKernelStdOrderForDouble()));
   }
}
