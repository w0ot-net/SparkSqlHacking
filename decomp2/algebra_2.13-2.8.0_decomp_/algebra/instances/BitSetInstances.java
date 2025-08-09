package algebra.instances;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0004\u001e\u0001\t\u0007I1\u0001\u0010\u0003\u001f\tKGoU3u\u0013:\u001cH/\u00198dKNT!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"A\u0004\u0002\u000f\u0005dw-\u001a2sC\u000e\u00011c\u0001\u0001\u000b!A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\u0004\"!E\f\u000e\u0003IQ!!B\n\u000b\u0005Q)\u0012AB6fe:,GNC\u0001\u0017\u0003\u0011\u0019\u0017\r^:\n\u0005\r\u0011\u0012A\u0002\u0013j]&$H\u0005F\u0001\u001b!\tY1$\u0003\u0002\u001d\u0019\t!QK\\5u\u00035\u0011\u0017\u000e^*fi\u0006cw-\u001a2sCV\tq\u0004\u0005\u0002!C5\tA!\u0003\u0002#\t\ti!)\u001b;TKR\fEnZ3ce\u0006\u0004"
)
public interface BitSetInstances extends cats.kernel.instances.BitSetInstances {
   void algebra$instances$BitSetInstances$_setter_$bitSetAlgebra_$eq(final BitSetAlgebra x$1);

   BitSetAlgebra bitSetAlgebra();

   static void $init$(final BitSetInstances $this) {
      $this.algebra$instances$BitSetInstances$_setter_$bitSetAlgebra_$eq(new BitSetAlgebra());
   }
}
