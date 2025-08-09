package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class IntDef$ extends AbstractFunction1 implements Serializable {
   public static final IntDef$ MODULE$ = new IntDef$();

   public final String toString() {
      return "IntDef";
   }

   public IntDef apply(final String value) {
      return new IntDef(value);
   }

   public Option unapply(final IntDef x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IntDef$.class);
   }

   private IntDef$() {
   }
}
