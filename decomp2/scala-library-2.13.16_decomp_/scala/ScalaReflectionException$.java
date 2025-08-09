package scala;

import java.io.Serializable;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaReflectionException$ extends AbstractFunction1 implements Serializable {
   public static final ScalaReflectionException$ MODULE$ = new ScalaReflectionException$();

   public final String toString() {
      return "ScalaReflectionException";
   }

   public ScalaReflectionException apply(final String msg) {
      return new ScalaReflectionException(msg);
   }

   public Option unapply(final ScalaReflectionException x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaReflectionException$.class);
   }

   private ScalaReflectionException$() {
   }
}
