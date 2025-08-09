package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114\u0001BC\u0006\u0011\u0002\u0007\u00051b\u0004\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u00011\u0019a\r\u0005\u0006\u0005\u00021\u0019a\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0015\u00021\u0019a\u0013\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006\u0005\u0002!\t\u0001\u0016\u0005\u00069\u0002!\t%\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0002\u000f\u0007>l\u0007\u000f\\3y\u0013Nt%k\\8u\u0015\taQ\"\u0001\u0003nCRD'\"\u0001\b\u0002\u000bM\u0004\u0018N]3\u0016\u0005A\u00193c\u0001\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u00042\u0001G\u000e\u001e\u001b\u0005I\"B\u0001\u000e\u000e\u0003\u001d\tGnZ3ce\u0006L!\u0001H\r\u0003\u000b9\u0013vn\u001c;\u0011\u0007yy\u0012%D\u0001\f\u0013\t\u00013BA\u0004D_6\u0004H.\u001a=\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\rA\n\u0002\u0002\u0003\u000e\u0001\u0011CA\u0014+!\t\u0011\u0002&\u0003\u0002*'\t9aj\u001c;iS:<\u0007C\u0001\n,\u0013\ta3CA\u0002B]f\fa\u0001J5oSR$C#A\u0018\u0011\u0005I\u0001\u0014BA\u0019\u0014\u0005\u0011)f.\u001b;\u0002\rM\u001c\u0017\r\\1s+\u0005!\u0004cA\u001b@C9\u0011a'\u0010\b\u0003oqr!\u0001O\u001e\u000e\u0003eR!AO\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011B\u0001\u000e\u000e\u0013\tq\u0014$A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0001\u000b%!\u0002$jK2$'B\u0001 \u001a\u0003\u0015q'o\\8u+\u0005!\u0005c\u0001\r\u001cC\u0005)qN\u001d3feV\tq\tE\u00026\u0011\u0006J!!S!\u0003\u000b=\u0013H-\u001a:\u0002\tQ\u0014\u0018nZ\u000b\u0002\u0019B\u0019\u0001$T\u0011\n\u00059K\"\u0001\u0002+sS\u001e\faa]5h]\u0016$W#A)\u0011\u0007U\u0012\u0016%\u0003\u0002T\u0003\n11+[4oK\u0012$2!H+X\u0011\u00151v\u00011\u0001\u001e\u0003\u0005\t\u0007\"\u0002-\b\u0001\u0004I\u0016!A6\u0011\u0005IQ\u0016BA.\u0014\u0005\rIe\u000e^\u0001\u0005gF\u0014H\u000f\u0006\u0002\u001e=\")a\u000b\u0003a\u0001;\u0005!a\r]8x)\ri\u0012M\u0019\u0005\u0006-&\u0001\r!\b\u0005\u0006G&\u0001\r!H\u0001\u0002E\u0002"
)
public interface ComplexIsNRoot extends NRoot {
   Field scalar();

   NRoot nroot();

   Order order();

   Trig trig();

   Signed signed();

   // $FF: synthetic method
   static Complex nroot$(final ComplexIsNRoot $this, final Complex a, final int k) {
      return $this.nroot(a, k);
   }

   default Complex nroot(final Complex a, final int k) {
      return a.nroot(k, this.scalar(), this.nroot(), this.order(), this.signed(), this.trig());
   }

   // $FF: synthetic method
   static Complex sqrt$(final ComplexIsNRoot $this, final Complex a) {
      return $this.sqrt(a);
   }

   default Complex sqrt(final Complex a) {
      return a.sqrt(this.scalar(), this.nroot(), this.order(), this.signed());
   }

   // $FF: synthetic method
   static Complex fpow$(final ComplexIsNRoot $this, final Complex a, final Complex b) {
      return $this.fpow(a, b);
   }

   default Complex fpow(final Complex a, final Complex b) {
      return a.pow(b, this.scalar(), this.nroot(), this.order(), this.signed(), this.trig());
   }

   static void $init$(final ComplexIsNRoot $this) {
   }
}
