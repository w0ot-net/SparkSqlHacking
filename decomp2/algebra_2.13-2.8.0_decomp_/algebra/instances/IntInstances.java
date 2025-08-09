package algebra.instances;

import algebra.lattice.BoundedDistributiveLattice;
import algebra.lattice.BoundedDistributiveLattice$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f\u0011\u0002!\u0019!C\u0001K\ta\u0011J\u001c;J]N$\u0018M\\2fg*\u0011aaB\u0001\nS:\u001cH/\u00198dKNT\u0011\u0001C\u0001\bC2<WM\u0019:b\u0007\u0001\u00192\u0001A\u0006\u0012!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0011!\u0003G\u0007\u0002')\u0011a\u0001\u0006\u0006\u0003+Y\taa[3s]\u0016d'\"A\f\u0002\t\r\fGo]\u0005\u0003\tM\ta\u0001J5oSR$C#A\u000e\u0011\u00051a\u0012BA\u000f\u000e\u0005\u0011)f.\u001b;\u0002\u0015%tG/\u00117hK\n\u0014\u0018-F\u0001!!\t\t#%D\u0001\u0006\u0013\t\u0019SA\u0001\u0006J]R\fEnZ3ce\u0006\f\u0001#\u00138u\u001b&tW*\u0019=MCR$\u0018nY3\u0016\u0003\u0019\u00022a\n\u0016-\u001b\u0005A#BA\u0015\b\u0003\u001da\u0017\r\u001e;jG\u0016L!a\u000b\u0015\u00035\t{WO\u001c3fI\u0012K7\u000f\u001e:jEV$\u0018N^3MCR$\u0018nY3\u0011\u00051i\u0013B\u0001\u0018\u000e\u0005\rIe\u000e\u001e"
)
public interface IntInstances extends cats.kernel.instances.IntInstances {
   void algebra$instances$IntInstances$_setter_$intAlgebra_$eq(final IntAlgebra x$1);

   void algebra$instances$IntInstances$_setter_$IntMinMaxLattice_$eq(final BoundedDistributiveLattice x$1);

   IntAlgebra intAlgebra();

   BoundedDistributiveLattice IntMinMaxLattice();

   static void $init$(final IntInstances $this) {
      $this.algebra$instances$IntInstances$_setter_$intAlgebra_$eq(new IntAlgebra());
      $this.algebra$instances$IntInstances$_setter_$IntMinMaxLattice_$eq(BoundedDistributiveLattice$.MODULE$.minMax$mIc$sp(Integer.MIN_VALUE, Integer.MAX_VALUE, $this.catsKernelStdOrderForInt()));
   }
}
