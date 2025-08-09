package org.apache.spark.network.client;

import org.apache.spark.network.buffer.ManagedBuffer;

public interface ChunkReceivedCallback {
   void onSuccess(int var1, ManagedBuffer var2);

   void onFailure(int var1, Throwable var2);
}
