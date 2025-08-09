package scala.collection;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StringView$ extends AbstractFunction1 implements Serializable {
   public static final StringView$ MODULE$ = new StringView$();

   public final String toString() {
      return "StringView";
   }

   public StringView apply(final String s) {
      return new StringView(s);
   }

   public Option unapply(final StringView x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.s()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringView$.class);
   }

   private StringView$() {
   }
}
