package org.apache.spark.memory;

import java.util.Map;
import org.apache.spark.SparkThrowable;
import org.apache.spark.SparkThrowableHelper;
import org.apache.spark.annotation.Private;

@Private
public final class SparkOutOfMemoryError extends OutOfMemoryError implements SparkThrowable {
   String errorClass;
   Map messageParameters;

   public SparkOutOfMemoryError(String errorClass, Map messageParameters) {
      super(SparkThrowableHelper.getMessage(errorClass, messageParameters));
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }

   public Map getMessageParameters() {
      return this.messageParameters;
   }

   public String getCondition() {
      return this.errorClass;
   }
}
