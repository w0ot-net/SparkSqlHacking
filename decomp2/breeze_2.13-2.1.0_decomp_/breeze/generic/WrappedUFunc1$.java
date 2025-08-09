package breeze.generic;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class WrappedUFunc1$ implements Serializable {
   public static final WrappedUFunc1$ MODULE$ = new WrappedUFunc1$();

   public final String toString() {
      return "WrappedUFunc1";
   }

   public WrappedUFunc1 apply(final Function1 f) {
      return new WrappedUFunc1(f);
   }

   public Option unapply(final WrappedUFunc1 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.f()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WrappedUFunc1$.class);
   }

   private WrappedUFunc1$() {
   }
}
