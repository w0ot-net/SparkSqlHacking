package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Some$ implements Serializable {
   public static final Some$ MODULE$ = new Some$();

   public final String toString() {
      return "Some";
   }

   public Some apply(final Object value) {
      return new Some(value);
   }

   public Option unapply(final Some x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Some$.class);
   }

   private Some$() {
   }
}
