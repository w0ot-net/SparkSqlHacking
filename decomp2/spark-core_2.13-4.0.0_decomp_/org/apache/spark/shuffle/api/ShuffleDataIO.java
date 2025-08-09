package org.apache.spark.shuffle.api;

import org.apache.spark.annotation.Private;

@Private
public interface ShuffleDataIO {
   ShuffleExecutorComponents executor();

   ShuffleDriverComponents driver();
}
