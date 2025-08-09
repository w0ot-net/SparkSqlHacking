package org.apache.spark.network.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class ManagedBuffer {
   public abstract long size();

   public abstract ByteBuffer nioByteBuffer() throws IOException;

   public abstract InputStream createInputStream() throws IOException;

   public abstract ManagedBuffer retain();

   public abstract ManagedBuffer release();

   public abstract Object convertToNetty() throws IOException;

   public abstract Object convertToNettyForSsl() throws IOException;
}
