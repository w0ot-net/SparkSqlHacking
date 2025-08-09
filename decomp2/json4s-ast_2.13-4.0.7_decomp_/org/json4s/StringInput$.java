package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StringInput$ extends AbstractFunction1 implements Serializable {
   public static final StringInput$ MODULE$ = new StringInput$();

   public final String toString() {
      return "StringInput";
   }

   public StringInput apply(final String string) {
      return new StringInput(string);
   }

   public Option unapply(final StringInput x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.string()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringInput$.class);
   }

   private StringInput$() {
   }
}
