package org.apache.spark.network.client;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface StreamCallback {
   void onData(String var1, ByteBuffer var2) throws IOException;

   void onComplete(String var1) throws IOException;

   void onFailure(String var1, Throwable var2) throws IOException;
}
