package breeze.generic;

import scala.Function1;
import scala.Function2;

public final class UFunc$ {
   public static final UFunc$ MODULE$ = new UFunc$();

   public WrappedUFunc1 apply(final Function1 f) {
      return new WrappedUFunc1(f);
   }

   public WrappedUFunc2 apply(final Function2 f) {
      return new WrappedUFunc2(f);
   }

   private UFunc$() {
   }
}
