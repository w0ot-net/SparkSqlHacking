package spire.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.ModuleSerializationProxy;

public final class JavaAlgebra$ implements Serializable {
   public static final JavaAlgebra$ MODULE$ = new JavaAlgebra$();

   public final String toString() {
      return "JavaAlgebra";
   }

   public JavaAlgebra apply(final Context c) {
      return new JavaAlgebra(c);
   }

   public Option unapply(final JavaAlgebra x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.c()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaAlgebra$.class);
   }

   private JavaAlgebra$() {
   }
}
