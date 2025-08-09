package io.vertx.core.shareddata.impl;

import io.vertx.core.buffer.Buffer;

/** @deprecated */
@Deprecated
public interface ClusterSerializable {
   void writeToBuffer(Buffer var1);

   int readFromBuffer(int var1, Buffer var2);
}
