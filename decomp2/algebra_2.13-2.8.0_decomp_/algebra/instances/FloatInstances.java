package algebra.instances;

import algebra.lattice.DistributiveLattice;
import algebra.lattice.DistributiveLattice$;
import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f%\u0002!\u0019!C\u0001U\tqa\t\\8bi&s7\u000f^1oG\u0016\u001c(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7OC\u0001\t\u0003\u001d\tGnZ3ce\u0006\u001c\u0001aE\u0002\u0001\u0017E\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007C\u0001\n\u0019\u001b\u0005\u0019\"B\u0001\u0004\u0015\u0015\t)b#\u0001\u0004lKJtW\r\u001c\u0006\u0002/\u0005!1-\u0019;t\u0013\t!1#\u0001\u0004%S:LG\u000f\n\u000b\u00027A\u0011A\u0002H\u0005\u0003;5\u0011A!\u00168ji\u0006aa\r\\8bi\u0006cw-\u001a2sCV\t\u0001\u0005E\u0002\"I\u0019j\u0011A\t\u0006\u0003G\u001d\tAA]5oO&\u0011QE\t\u0002\u0006\r&,G\u000e\u001a\t\u0003\u0019\u001dJ!\u0001K\u0007\u0003\u000b\u0019cw.\u0019;\u0002%\u0019cw.\u0019;NS:l\u0015\r\u001f'biRL7-Z\u000b\u0002WA\u0019Af\f\u0014\u000e\u00035R!AL\u0004\u0002\u000f1\fG\u000f^5dK&\u0011\u0001'\f\u0002\u0014\t&\u001cHO]5ckRLg/\u001a'biRL7-\u001a"
)
public interface FloatInstances extends cats.kernel.instances.FloatInstances {
   void algebra$instances$FloatInstances$_setter_$floatAlgebra_$eq(final Field x$1);

   void algebra$instances$FloatInstances$_setter_$FloatMinMaxLattice_$eq(final DistributiveLattice x$1);

   Field floatAlgebra();

   DistributiveLattice FloatMinMaxLattice();

   static void $init$(final FloatInstances $this) {
      $this.algebra$instances$FloatInstances$_setter_$floatAlgebra_$eq(new FloatAlgebra());
      $this.algebra$instances$FloatInstances$_setter_$FloatMinMaxLattice_$eq(DistributiveLattice$.MODULE$.minMax$mFc$sp($this.catsKernelStdOrderForFloat()));
   }
}
