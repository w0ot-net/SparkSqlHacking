package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ParsedEntityDecl$ extends AbstractFunction2 implements Serializable {
   public static final ParsedEntityDecl$ MODULE$ = new ParsedEntityDecl$();

   public final String toString() {
      return "ParsedEntityDecl";
   }

   public ParsedEntityDecl apply(final String name, final EntityDef entdef) {
      return new ParsedEntityDecl(name, entdef);
   }

   public Option unapply(final ParsedEntityDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.entdef())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParsedEntityDecl$.class);
   }

   private ParsedEntityDecl$() {
   }
}
