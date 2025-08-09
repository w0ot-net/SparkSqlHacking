package scala.util;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Failure$ implements Serializable {
   public static final Failure$ MODULE$ = new Failure$();

   public final String toString() {
      return "Failure";
   }

   public Failure apply(final Throwable exception) {
      return new Failure(exception);
   }

   public Option unapply(final Failure x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.exception()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Failure$.class);
   }

   private Failure$() {
   }
}
