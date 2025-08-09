package breeze.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Transpose$ implements Serializable {
   public static final Transpose$ MODULE$ = new Transpose$();

   public final String toString() {
      return "Transpose";
   }

   public Transpose apply(final Object inner) {
      return new Transpose(inner);
   }

   public Option unapply(final Transpose x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.inner()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Transpose$.class);
   }

   private Transpose$() {
   }
}
