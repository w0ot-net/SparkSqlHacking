package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class FatalError$ extends AbstractFunction1 implements Serializable {
   public static final FatalError$ MODULE$ = new FatalError$();

   public final String toString() {
      return "FatalError";
   }

   public FatalError apply(final String msg) {
      return new FatalError(msg);
   }

   public Option unapply(final FatalError x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FatalError$.class);
   }

   private FatalError$() {
   }
}
