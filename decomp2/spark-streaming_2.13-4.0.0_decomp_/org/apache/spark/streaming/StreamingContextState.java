package org.apache.spark.streaming;

import org.apache.spark.annotation.DeveloperApi;

@DeveloperApi
public enum StreamingContextState {
   INITIALIZED,
   ACTIVE,
   STOPPED;

   // $FF: synthetic method
   private static StreamingContextState[] $values() {
      return new StreamingContextState[]{INITIALIZED, ACTIVE, STOPPED};
   }
}
