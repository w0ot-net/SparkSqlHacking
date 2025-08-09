package org.xerial.snappy;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface SnappyApi {
   long rawCompress(long var1, long var3, long var5) throws IOException;

   long rawUncompress(long var1, long var3, long var5) throws IOException;

   int rawCompress(ByteBuffer var1, int var2, int var3, ByteBuffer var4, int var5) throws IOException;

   int rawCompress(Object var1, int var2, int var3, Object var4, int var5) throws IOException;

   int rawUncompress(ByteBuffer var1, int var2, int var3, ByteBuffer var4, int var5) throws IOException;

   int rawUncompress(Object var1, int var2, int var3, Object var4, int var5) throws IOException;

   int maxCompressedLength(int var1);

   int uncompressedLength(ByteBuffer var1, int var2, int var3) throws IOException;

   int uncompressedLength(Object var1, int var2, int var3) throws IOException;

   long uncompressedLength(long var1, long var3) throws IOException;

   boolean isValidCompressedBuffer(ByteBuffer var1, int var2, int var3) throws IOException;

   boolean isValidCompressedBuffer(Object var1, int var2, int var3) throws IOException;

   boolean isValidCompressedBuffer(long var1, long var3, long var5) throws IOException;

   void arrayCopy(Object var1, int var2, int var3, Object var4, int var5) throws IOException;
}
