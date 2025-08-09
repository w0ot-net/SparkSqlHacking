package org.apache.arrow.vector.util;

import org.apache.arrow.vector.ValueVector;

public class VectorBatchAppender {
   public static void batchAppend(ValueVector targetVector, ValueVector... vectorsToAppend) {
      VectorAppender appender = new VectorAppender(targetVector);

      for(ValueVector delta : vectorsToAppend) {
         delta.accept(appender, (Object)null);
      }

   }
}
