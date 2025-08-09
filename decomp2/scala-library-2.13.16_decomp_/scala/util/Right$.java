package scala.util;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Right$ implements Serializable {
   public static final Right$ MODULE$ = new Right$();

   public final String toString() {
      return "Right";
   }

   public Right apply(final Object value) {
      return new Right(value);
   }

   public Option unapply(final Right x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Right$.class);
   }

   private Right$() {
   }
}
