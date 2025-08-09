package scala.util;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Success$ implements Serializable {
   public static final Success$ MODULE$ = new Success$();

   public final String toString() {
      return "Success";
   }

   public Success apply(final Object value) {
      return new Success(value);
   }

   public Option unapply(final Success x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Success$.class);
   }

   private Success$() {
   }
}
