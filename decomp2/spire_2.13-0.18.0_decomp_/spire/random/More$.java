package spire.random;

import java.io.Serializable;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class More$ implements Serializable {
   public static final More$ MODULE$ = new More$();

   public final String toString() {
      return "More";
   }

   public More apply(final Function0 k) {
      return new More(k);
   }

   public Option unapply(final More x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.k()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(More$.class);
   }

   private More$() {
   }
}
