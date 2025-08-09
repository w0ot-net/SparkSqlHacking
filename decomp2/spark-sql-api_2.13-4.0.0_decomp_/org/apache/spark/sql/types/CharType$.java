package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CharType$ extends AbstractFunction1 implements Serializable {
   public static final CharType$ MODULE$ = new CharType$();

   public final String toString() {
      return "CharType";
   }

   public CharType apply(final int length) {
      return new CharType(length);
   }

   public Option unapply(final CharType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.length())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CharType$.class);
   }

   private CharType$() {
   }
}
