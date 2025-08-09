package org.apache.spark.sql.types;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class VarcharType$ extends AbstractFunction1 implements Serializable {
   public static final VarcharType$ MODULE$ = new VarcharType$();

   public final String toString() {
      return "VarcharType";
   }

   public VarcharType apply(final int length) {
      return new VarcharType(length);
   }

   public Option unapply(final VarcharType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.length())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VarcharType$.class);
   }

   private VarcharType$() {
   }
}
