package breeze.generic;

import java.io.Serializable;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class WrappedUFunc2$ implements Serializable {
   public static final WrappedUFunc2$ MODULE$ = new WrappedUFunc2$();

   public final String toString() {
      return "WrappedUFunc2";
   }

   public WrappedUFunc2 apply(final Function2 f) {
      return new WrappedUFunc2(f);
   }

   public Option unapply(final WrappedUFunc2 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.f()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WrappedUFunc2$.class);
   }

   private WrappedUFunc2$() {
   }
}
