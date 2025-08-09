package scala.util;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Left$ implements Serializable {
   public static final Left$ MODULE$ = new Left$();

   public final String toString() {
      return "Left";
   }

   public Left apply(final Object value) {
      return new Left(value);
   }

   public Option unapply(final Left x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Left$.class);
   }

   private Left$() {
   }
}
