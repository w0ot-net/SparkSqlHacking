package scala.reflect.internal.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class TextEdit$ extends AbstractFunction2 implements Serializable {
   public static final TextEdit$ MODULE$ = new TextEdit$();

   public final String toString() {
      return "TextEdit";
   }

   public TextEdit apply(final Position position, final String newText) {
      return new TextEdit(position, newText);
   }

   public Option unapply(final TextEdit x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.position(), x$0.newText())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TextEdit$.class);
   }

   private TextEdit$() {
   }
}
