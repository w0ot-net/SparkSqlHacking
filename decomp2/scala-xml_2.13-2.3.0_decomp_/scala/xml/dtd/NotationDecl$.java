package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class NotationDecl$ extends AbstractFunction2 implements Serializable {
   public static final NotationDecl$ MODULE$ = new NotationDecl$();

   public final String toString() {
      return "NotationDecl";
   }

   public NotationDecl apply(final String name, final ExternalID extID) {
      return new NotationDecl(name, extID);
   }

   public Option unapply(final NotationDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.extID())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NotationDecl$.class);
   }

   private NotationDecl$() {
   }
}
