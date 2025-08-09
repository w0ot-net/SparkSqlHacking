package scala.reflect.macros;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.api.Position;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ParseException$ extends AbstractFunction2 implements Serializable {
   public static final ParseException$ MODULE$ = new ParseException$();

   public final String toString() {
      return "ParseException";
   }

   public ParseException apply(final Position pos, final String msg) {
      return new ParseException(pos, msg);
   }

   public Option unapply(final ParseException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.pos(), x$0.msg())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParseException$.class);
   }

   private ParseException$() {
   }
}
