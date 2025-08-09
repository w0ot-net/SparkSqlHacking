package spire.random;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Const$ implements Serializable {
   public static final Const$ MODULE$ = new Const$();

   public final String toString() {
      return "Const";
   }

   public Const apply(final Object a) {
      return new Const(a);
   }

   public Option unapply(final Const x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.a()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Const$.class);
   }

   private Const$() {
   }
}
