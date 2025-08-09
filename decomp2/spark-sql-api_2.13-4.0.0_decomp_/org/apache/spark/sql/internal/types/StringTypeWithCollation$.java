package org.apache.spark.sql.internal.types;

import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StringTypeWithCollation$ extends StringTypeWithCollation {
   public static final StringTypeWithCollation$ MODULE$ = new StringTypeWithCollation$();

   public StringTypeWithCollation apply(final boolean supportsTrimCollation, final boolean supportsCaseSpecifier, final boolean supportsAccentSpecifier) {
      return new StringTypeWithCollation(supportsTrimCollation, supportsCaseSpecifier, supportsAccentSpecifier);
   }

   public boolean apply$default$1() {
      return false;
   }

   public boolean apply$default$2() {
      return true;
   }

   public boolean apply$default$3() {
      return true;
   }

   public Option unapply(final StringTypeWithCollation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToBoolean(x$0.supportsTrimCollation()), BoxesRunTime.boxToBoolean(x$0.supportsCaseSpecifier()), BoxesRunTime.boxToBoolean(x$0.supportsAccentSpecifier()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringTypeWithCollation$.class);
   }

   private StringTypeWithCollation$() {
      super(false, true, true);
   }
}
