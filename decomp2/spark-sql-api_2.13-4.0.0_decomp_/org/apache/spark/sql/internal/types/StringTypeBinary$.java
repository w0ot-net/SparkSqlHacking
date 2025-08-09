package org.apache.spark.sql.internal.types;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StringTypeBinary$ extends StringTypeBinary {
   public static final StringTypeBinary$ MODULE$ = new StringTypeBinary$();

   public StringTypeBinary apply(final boolean supportsTrimCollation) {
      return new StringTypeBinary(supportsTrimCollation);
   }

   public Option unapply(final StringTypeBinary x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.supportsTrimCollation())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringTypeBinary$.class);
   }

   private StringTypeBinary$() {
      super(false);
   }
}
