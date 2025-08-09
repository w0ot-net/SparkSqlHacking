package org.apache.spark.sql.internal.types;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StringTypeNonCSAICollation$ extends StringTypeNonCSAICollation {
   public static final StringTypeNonCSAICollation$ MODULE$ = new StringTypeNonCSAICollation$();

   public StringTypeNonCSAICollation apply(final boolean supportsTrimCollation) {
      return new StringTypeNonCSAICollation(supportsTrimCollation);
   }

   public Option unapply(final StringTypeNonCSAICollation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.supportsTrimCollation())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringTypeNonCSAICollation$.class);
   }

   private StringTypeNonCSAICollation$() {
      super(false);
   }
}
