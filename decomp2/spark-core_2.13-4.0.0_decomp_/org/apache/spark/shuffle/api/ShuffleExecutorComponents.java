package org.apache.spark.shuffle.api;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.apache.spark.annotation.Private;

@Private
public interface ShuffleExecutorComponents {
   void initializeExecutor(String var1, String var2, Map var3);

   ShuffleMapOutputWriter createMapOutputWriter(int var1, long var2, int var4) throws IOException;

   default Optional createSingleFileMapOutputWriter(int shuffleId, long mapId) throws IOException {
      return Optional.empty();
   }
}
