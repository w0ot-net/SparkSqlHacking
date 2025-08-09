package breeze.linalg.support;

import breeze.math.Field;
import breeze.math.Semiring;
import scala.reflect.ClassTag;

public final class CanCreateZerosLike$ {
   public static final CanCreateZerosLike$ MODULE$ = new CanCreateZerosLike$();

   public CanCreateZerosLike opMapValues(final CanMapValues map, final Field op) {
      return new CanCreateZerosLike.OpMapValues(op, map);
   }

   public CanCreateZerosLike.OpArray OpArrayAny(final ClassTag evidence$3, final Semiring evidence$4) {
      return new CanCreateZerosLike.OpArray(evidence$3, evidence$4);
   }

   private CanCreateZerosLike$() {
   }
}
