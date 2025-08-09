package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class AttListDecl$ extends AbstractFunction2 implements Serializable {
   public static final AttListDecl$ MODULE$ = new AttListDecl$();

   public final String toString() {
      return "AttListDecl";
   }

   public AttListDecl apply(final String name, final List attrs) {
      return new AttListDecl(name, attrs);
   }

   public Option unapply(final AttListDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.attrs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AttListDecl$.class);
   }

   private AttListDecl$() {
   }
}
