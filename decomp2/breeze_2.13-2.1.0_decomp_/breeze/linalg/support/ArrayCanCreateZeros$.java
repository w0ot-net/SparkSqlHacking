package breeze.linalg.support;

import breeze.math.Semiring;
import scala.reflect.ClassTag;

public final class ArrayCanCreateZeros$ {
   public static final ArrayCanCreateZeros$ MODULE$ = new ArrayCanCreateZeros$();

   public ArrayCanCreateZeros.OpArray OpArrayAny(final ClassTag evidence$3, final Semiring evidence$4) {
      return new ArrayCanCreateZeros.OpArray(evidence$3, evidence$4);
   }

   private ArrayCanCreateZeros$() {
   }
}
