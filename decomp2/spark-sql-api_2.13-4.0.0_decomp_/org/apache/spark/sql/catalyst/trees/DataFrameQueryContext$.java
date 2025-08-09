package org.apache.spark.sql.catalyst.trees;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class DataFrameQueryContext$ extends AbstractFunction2 implements Serializable {
   public static final DataFrameQueryContext$ MODULE$ = new DataFrameQueryContext$();

   public final String toString() {
      return "DataFrameQueryContext";
   }

   public DataFrameQueryContext apply(final Seq stackTrace, final Option pysparkErrorContext) {
      return new DataFrameQueryContext(stackTrace, pysparkErrorContext);
   }

   public Option unapply(final DataFrameQueryContext x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.stackTrace(), x$0.pysparkErrorContext())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DataFrameQueryContext$.class);
   }

   private DataFrameQueryContext$() {
   }
}
