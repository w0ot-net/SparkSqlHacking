package org.apache.spark;

import java.util.HashMap;
import java.util.Map;
import org.apache.spark.annotation.Evolving;

@Evolving
public interface SparkThrowable {
   String getCondition();

   /** @deprecated */
   @Deprecated
   default String getErrorClass() {
      return this.getCondition();
   }

   default String getSqlState() {
      return SparkThrowableHelper.getSqlState(this.getCondition());
   }

   default boolean isInternalError() {
      return SparkThrowableHelper.isInternalError(this.getCondition());
   }

   default Map getMessageParameters() {
      return new HashMap();
   }

   default QueryContext[] getQueryContext() {
      return new QueryContext[0];
   }
}
