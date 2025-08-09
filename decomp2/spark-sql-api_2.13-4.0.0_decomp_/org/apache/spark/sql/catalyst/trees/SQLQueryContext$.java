package org.apache.spark.sql.catalyst.trees;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.ModuleSerializationProxy;

public final class SQLQueryContext$ extends AbstractFunction7 implements Serializable {
   public static final SQLQueryContext$ MODULE$ = new SQLQueryContext$();

   public final String toString() {
      return "SQLQueryContext";
   }

   public SQLQueryContext apply(final Option line, final Option startPosition, final Option originStartIndex, final Option originStopIndex, final Option sqlText, final Option originObjectType, final Option originObjectName) {
      return new SQLQueryContext(line, startPosition, originStartIndex, originStopIndex, sqlText, originObjectType, originObjectName);
   }

   public Option unapply(final SQLQueryContext x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.line(), x$0.startPosition(), x$0.originStartIndex(), x$0.originStopIndex(), x$0.sqlText(), x$0.originObjectType(), x$0.originObjectName())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SQLQueryContext$.class);
   }

   private SQLQueryContext$() {
   }
}
