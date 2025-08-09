package scala.reflect.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.api.Position;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class TypecheckException$ extends AbstractFunction2 implements Serializable {
   public static final TypecheckException$ MODULE$ = new TypecheckException$();

   public final String toString() {
      return "TypecheckException";
   }

   public TypecheckException apply(final Position pos, final String msg) {
      return new TypecheckException(pos, msg);
   }

   public Option unapply(final TypecheckException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.pos(), x$0.msg())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TypecheckException$.class);
   }

   private TypecheckException$() {
   }
}
