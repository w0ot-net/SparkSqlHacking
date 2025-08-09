package scala.reflect.internal.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class FakePos$ extends AbstractFunction1 implements Serializable {
   public static final FakePos$ MODULE$ = new FakePos$();

   public final String toString() {
      return "FakePos";
   }

   public FakePos apply(final String msg) {
      return new FakePos(msg);
   }

   public Option unapply(final FakePos x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FakePos$.class);
   }

   private FakePos$() {
   }
}
