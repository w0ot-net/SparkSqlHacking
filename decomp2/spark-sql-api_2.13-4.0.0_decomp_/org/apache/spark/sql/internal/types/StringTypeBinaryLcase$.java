package org.apache.spark.sql.internal.types;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StringTypeBinaryLcase$ extends StringTypeBinaryLcase {
   public static final StringTypeBinaryLcase$ MODULE$ = new StringTypeBinaryLcase$();

   public StringTypeBinaryLcase apply(final boolean supportsTrimCollation) {
      return new StringTypeBinaryLcase(supportsTrimCollation);
   }

   public Option unapply(final StringTypeBinaryLcase x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.supportsTrimCollation())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringTypeBinaryLcase$.class);
   }

   private StringTypeBinaryLcase$() {
      super(false);
   }
}
