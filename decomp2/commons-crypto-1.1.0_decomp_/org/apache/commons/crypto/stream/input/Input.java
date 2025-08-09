package org.apache.commons.crypto.stream.input;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface Input extends Closeable {
   int read(ByteBuffer var1) throws IOException;

   long skip(long var1) throws IOException;

   int available() throws IOException;

   int read(long var1, byte[] var3, int var4, int var5) throws IOException;

   void seek(long var1) throws IOException;

   void close() throws IOException;
}
