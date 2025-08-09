package spire.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaAlgebra$ implements Serializable {
   public static final ScalaAlgebra$ MODULE$ = new ScalaAlgebra$();

   public final String toString() {
      return "ScalaAlgebra";
   }

   public ScalaAlgebra apply(final Context c) {
      return new ScalaAlgebra(c);
   }

   public Option unapply(final ScalaAlgebra x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.c()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaAlgebra$.class);
   }

   private ScalaAlgebra$() {
   }
}
