package algebra.instances;

import algebra.ring.TruncatedDivision;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000b\u0011\u0002A1A\u0013\u0003\u001f\tKw-\u00138u\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"\u0001\u0005\u0002\u000f\u0005dw-\u001a2sC\u000e\u00011c\u0001\u0001\f#A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\u0004\"A\u0005\r\u000e\u0003MQ!A\u0002\u000b\u000b\u0005U1\u0012AB6fe:,GNC\u0001\u0018\u0003\u0011\u0019\u0017\r^:\n\u0005\u0011\u0019\u0012A\u0002\u0013j]&$H\u0005F\u0001\u001c!\taA$\u0003\u0002\u001e\u001b\t!QK\\5u\u00035\u0011\u0017nZ%oi\u0006cw-\u001a2sCV\t\u0001\u0005\u0005\u0002\"E5\tQ!\u0003\u0002$\u000b\ti!)[4J]R\fEnZ3ce\u0006\fqCY5h\u0013:$HK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u0016\u0003\u0019\u00022a\n\u0016-\u001b\u0005A#BA\u0015\b\u0003\u0011\u0011\u0018N\\4\n\u0005-B#!\u0005+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]B\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011B\u0001\u001b\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\r\tKw-\u00138u\u0015\t!T\u0002"
)
public interface BigIntInstances extends cats.kernel.instances.BigIntInstances {
   void algebra$instances$BigIntInstances$_setter_$bigIntAlgebra_$eq(final BigIntAlgebra x$1);

   BigIntAlgebra bigIntAlgebra();

   // $FF: synthetic method
   static TruncatedDivision bigIntTruncatedDivision$(final BigIntInstances $this) {
      return $this.bigIntTruncatedDivision();
   }

   default TruncatedDivision bigIntTruncatedDivision() {
      return (BigIntTruncatedDivison)this.bigIntAlgebra();
   }

   static void $init$(final BigIntInstances $this) {
      $this.algebra$instances$BigIntInstances$_setter_$bigIntAlgebra_$eq(new BigIntTruncatedDivison());
   }
}
