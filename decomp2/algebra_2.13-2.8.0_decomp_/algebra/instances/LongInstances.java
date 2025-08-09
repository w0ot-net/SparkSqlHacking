package algebra.instances;

import algebra.lattice.BoundedDistributiveLattice;
import algebra.lattice.BoundedDistributiveLattice$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f\u0011\u0002!\u0019!C\u0001K\tiAj\u001c8h\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"\u0001\u0005\u0002\u000f\u0005dw-\u001a2sC\u000e\u00011c\u0001\u0001\f#A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\u0004\"A\u0005\r\u000e\u0003MQ!A\u0002\u000b\u000b\u0005U1\u0012AB6fe:,GNC\u0001\u0018\u0003\u0011\u0019\u0017\r^:\n\u0005\u0011\u0019\u0012A\u0002\u0013j]&$H\u0005F\u0001\u001c!\taA$\u0003\u0002\u001e\u001b\t!QK\\5u\u0003-awN\\4BY\u001e,'M]1\u0016\u0003\u0001\u0002\"!\t\u0012\u000e\u0003\u0015I!aI\u0003\u0003\u00171{gnZ!mO\u0016\u0014'/Y\u0001\u0012\u0019>tw-T5o\u001b\u0006DH*\u0019;uS\u000e,W#\u0001\u0014\u0011\u0007\u001dRC&D\u0001)\u0015\tIs!A\u0004mCR$\u0018nY3\n\u0005-B#A\u0007\"pk:$W\r\u001a#jgR\u0014\u0018NY;uSZ,G*\u0019;uS\u000e,\u0007C\u0001\u0007.\u0013\tqSB\u0001\u0003M_:<\u0007"
)
public interface LongInstances extends cats.kernel.instances.LongInstances {
   void algebra$instances$LongInstances$_setter_$longAlgebra_$eq(final LongAlgebra x$1);

   void algebra$instances$LongInstances$_setter_$LongMinMaxLattice_$eq(final BoundedDistributiveLattice x$1);

   LongAlgebra longAlgebra();

   BoundedDistributiveLattice LongMinMaxLattice();

   static void $init$(final LongInstances $this) {
      $this.algebra$instances$LongInstances$_setter_$longAlgebra_$eq(new LongAlgebra());
      $this.algebra$instances$LongInstances$_setter_$LongMinMaxLattice_$eq(BoundedDistributiveLattice$.MODULE$.minMax$mJc$sp(Long.MIN_VALUE, Long.MAX_VALUE, $this.catsKernelStdOrderForLong()));
   }
}
