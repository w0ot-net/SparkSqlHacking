package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ParameterEntityDecl$ extends AbstractFunction2 implements Serializable {
   public static final ParameterEntityDecl$ MODULE$ = new ParameterEntityDecl$();

   public final String toString() {
      return "ParameterEntityDecl";
   }

   public ParameterEntityDecl apply(final String name, final EntityDef entdef) {
      return new ParameterEntityDecl(name, entdef);
   }

   public Option unapply(final ParameterEntityDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.entdef())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParameterEntityDecl$.class);
   }

   private ParameterEntityDecl$() {
   }
}
