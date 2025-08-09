package scala.ref;

import scala.Option;
import scala.Option$;

public final class WeakReference$ {
   public static final WeakReference$ MODULE$ = new WeakReference$();

   public WeakReference apply(final Object value) {
      return new WeakReference(value);
   }

   public Option unapply(final WeakReference wr) {
      return Option$.MODULE$.apply(wr.underlying().get());
   }

   private WeakReference$() {
   }
}
