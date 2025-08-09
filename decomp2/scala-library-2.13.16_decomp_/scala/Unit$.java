package scala;

import scala.runtime.BoxedUnit;

public final class Unit$ implements AnyValCompanion {
   public static final Unit$ MODULE$ = new Unit$();

   public BoxedUnit box(final BoxedUnit x) {
      return BoxedUnit.UNIT;
   }

   public void unbox(final Object x) {
      BoxedUnit var10000 = (BoxedUnit)x;
   }

   public String toString() {
      return "object scala.Unit";
   }

   private Unit$() {
   }
}
