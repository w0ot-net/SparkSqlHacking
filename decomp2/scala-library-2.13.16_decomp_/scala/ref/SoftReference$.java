package scala.ref;

import scala.Option;
import scala.Option$;

public final class SoftReference$ {
   public static final SoftReference$ MODULE$ = new SoftReference$();

   public SoftReference apply(final Object value) {
      return new SoftReference(value);
   }

   public Option unapply(final SoftReference sr) {
      return Option$.MODULE$.apply(sr.underlying().get());
   }

   private SoftReference$() {
   }
}
