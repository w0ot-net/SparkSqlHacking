package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class UnparsedEntityDecl$ extends AbstractFunction3 implements Serializable {
   public static final UnparsedEntityDecl$ MODULE$ = new UnparsedEntityDecl$();

   public final String toString() {
      return "UnparsedEntityDecl";
   }

   public UnparsedEntityDecl apply(final String name, final ExternalID extID, final String notation) {
      return new UnparsedEntityDecl(name, extID, notation);
   }

   public Option unapply(final UnparsedEntityDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.name(), x$0.extID(), x$0.notation())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnparsedEntityDecl$.class);
   }

   private UnparsedEntityDecl$() {
   }
}
