package scala;

import java.io.Serializable;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class UninitializedFieldError$ extends AbstractFunction1 implements Serializable {
   public static final UninitializedFieldError$ MODULE$ = new UninitializedFieldError$();

   public final String toString() {
      return "UninitializedFieldError";
   }

   public UninitializedFieldError apply(final String msg) {
      return new UninitializedFieldError(msg);
   }

   public Option unapply(final UninitializedFieldError x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UninitializedFieldError$.class);
   }

   private UninitializedFieldError$() {
   }
}
