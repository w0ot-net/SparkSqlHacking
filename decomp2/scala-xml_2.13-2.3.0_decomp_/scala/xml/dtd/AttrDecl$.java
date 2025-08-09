package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class AttrDecl$ extends AbstractFunction3 implements Serializable {
   public static final AttrDecl$ MODULE$ = new AttrDecl$();

   public final String toString() {
      return "AttrDecl";
   }

   public AttrDecl apply(final String name, final String tpe, final DefaultDecl default) {
      return new AttrDecl(name, tpe, default);
   }

   public Option unapply(final AttrDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.name(), x$0.tpe(), x$0.default())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AttrDecl$.class);
   }

   private AttrDecl$() {
   }
}
