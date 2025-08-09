package algebra.instances;

import algebra.lattice.BoundedDistributiveLattice;
import algebra.lattice.BoundedDistributiveLattice$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f\u0011\u0002!\u0019!C\u0001K\tq1\u000b[8si&s7\u000f^1oG\u0016\u001c(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7OC\u0001\t\u0003\u001d\tGnZ3ce\u0006\u001c\u0001aE\u0002\u0001\u0017E\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007C\u0001\n\u0019\u001b\u0005\u0019\"B\u0001\u0004\u0015\u0015\t)b#\u0001\u0004lKJtW\r\u001c\u0006\u0002/\u0005!1-\u0019;t\u0013\t!1#\u0001\u0004%S:LG\u000f\n\u000b\u00027A\u0011A\u0002H\u0005\u0003;5\u0011A!\u00168ji\u0006a1\u000f[8si\u0006cw-\u001a2sCV\t\u0001\u0005\u0005\u0002\"E5\tQ!\u0003\u0002$\u000b\ta1\u000b[8si\u0006cw-\u001a2sC\u0006\u00112\u000b[8si6Kg.T1y\u0019\u0006$H/[2f+\u00051\u0003cA\u0014+Y5\t\u0001F\u0003\u0002*\u000f\u00059A.\u0019;uS\u000e,\u0017BA\u0016)\u0005i\u0011u.\u001e8eK\u0012$\u0015n\u001d;sS\n,H/\u001b<f\u0019\u0006$H/[2f!\taQ&\u0003\u0002/\u001b\t)1\u000b[8si\u0002"
)
public interface ShortInstances extends cats.kernel.instances.ShortInstances {
   void algebra$instances$ShortInstances$_setter_$shortAlgebra_$eq(final ShortAlgebra x$1);

   void algebra$instances$ShortInstances$_setter_$ShortMinMaxLattice_$eq(final BoundedDistributiveLattice x$1);

   ShortAlgebra shortAlgebra();

   BoundedDistributiveLattice ShortMinMaxLattice();

   static void $init$(final ShortInstances $this) {
      $this.algebra$instances$ShortInstances$_setter_$shortAlgebra_$eq(new ShortAlgebra());
      $this.algebra$instances$ShortInstances$_setter_$ShortMinMaxLattice_$eq(BoundedDistributiveLattice$.MODULE$.minMax(BoxesRunTime.boxToShort((short)Short.MIN_VALUE), BoxesRunTime.boxToShort((short)32767), $this.catsKernelStdOrderForShort()));
   }
}
