package scala.reflect.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.reflect.api.Position;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class UnexpectedReificationException$ extends AbstractFunction3 implements Serializable {
   public static final UnexpectedReificationException$ MODULE$ = new UnexpectedReificationException$();

   public Throwable $lessinit$greater$default$3() {
      return null;
   }

   public final String toString() {
      return "UnexpectedReificationException";
   }

   public UnexpectedReificationException apply(final Position pos, final String msg, final Throwable cause) {
      return new UnexpectedReificationException(pos, msg, cause);
   }

   public Throwable apply$default$3() {
      return null;
   }

   public Option unapply(final UnexpectedReificationException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.pos(), x$0.msg(), x$0.cause())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnexpectedReificationException$.class);
   }

   private UnexpectedReificationException$() {
   }
}
