package breeze.linalg.support;

import breeze.math.Field;
import scala.reflect.ClassTag;

public final class CanCopy$ {
   public static final CanCopy$ MODULE$ = new CanCopy$();

   public CanCopy opMapValues(final CanMapValues map, final CanCopy op) {
      return new CanCopy.OpMapValues(op, map);
   }

   public CanCopy.OpArray opArrayAny(final ClassTag evidence$1, final Field evidence$2) {
      return new CanCopy.OpArray();
   }

   public CanCopy canCopyField(final Field evidence$3) {
      return new CanCopy() {
         public Object apply(final Object v1) {
            return v1;
         }
      };
   }

   private CanCopy$() {
   }
}
