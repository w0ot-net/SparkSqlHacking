package spire.random;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Next$ implements Serializable {
   public static final Next$ MODULE$ = new Next$();

   public final String toString() {
      return "Next";
   }

   public Next apply(final Function1 f) {
      return new Next(f);
   }

   public Option unapply(final Next x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.f()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Next$.class);
   }

   private Next$() {
   }
}
