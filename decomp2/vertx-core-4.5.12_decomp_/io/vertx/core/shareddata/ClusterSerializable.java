package io.vertx.core.shareddata;

import io.vertx.core.buffer.Buffer;

public interface ClusterSerializable extends io.vertx.core.shareddata.impl.ClusterSerializable {
   void writeToBuffer(Buffer var1);

   int readFromBuffer(int var1, Buffer var2);
}
