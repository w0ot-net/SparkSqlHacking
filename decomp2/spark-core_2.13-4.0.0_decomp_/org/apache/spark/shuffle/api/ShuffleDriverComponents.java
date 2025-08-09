package org.apache.spark.shuffle.api;

import java.util.Map;
import org.apache.spark.annotation.Private;

@Private
public interface ShuffleDriverComponents {
   Map initializeApplication();

   void cleanupApplication();

   default void registerShuffle(int shuffleId) {
   }

   default void removeShuffle(int shuffleId, boolean blocking) {
   }

   default boolean supportsReliableStorage() {
      return false;
   }
}
