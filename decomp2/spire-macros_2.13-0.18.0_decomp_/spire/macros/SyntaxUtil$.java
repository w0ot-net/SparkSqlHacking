package spire.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.ModuleSerializationProxy;

public final class SyntaxUtil$ implements Serializable {
   public static final SyntaxUtil$ MODULE$ = new SyntaxUtil$();

   public final String toString() {
      return "SyntaxUtil";
   }

   public SyntaxUtil apply(final Context c) {
      return new SyntaxUtil(c);
   }

   public Option unapply(final SyntaxUtil x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.c()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SyntaxUtil$.class);
   }

   private SyntaxUtil$() {
   }
}
