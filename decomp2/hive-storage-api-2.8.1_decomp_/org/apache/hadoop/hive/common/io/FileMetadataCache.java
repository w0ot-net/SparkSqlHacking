package org.apache.hadoop.hive.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import org.apache.hadoop.hive.common.io.encoded.MemoryBufferOrBuffers;

public interface FileMetadataCache {
   MemoryBufferOrBuffers getFileMetadata(Object var1);

   /** @deprecated */
   @Deprecated
   MemoryBufferOrBuffers putFileMetadata(Object var1, int var2, InputStream var3) throws IOException;

   /** @deprecated */
   @Deprecated
   MemoryBufferOrBuffers putFileMetadata(Object var1, ByteBuffer var2);

   /** @deprecated */
   @Deprecated
   MemoryBufferOrBuffers putFileMetadata(Object var1, int var2, InputStream var3, CacheTag var4) throws IOException;

   /** @deprecated */
   @Deprecated
   MemoryBufferOrBuffers putFileMetadata(Object var1, ByteBuffer var2, CacheTag var3);

   void decRefBuffer(MemoryBufferOrBuffers var1);

   MemoryBufferOrBuffers putFileMetadata(Object var1, ByteBuffer var2, CacheTag var3, AtomicBoolean var4);

   MemoryBufferOrBuffers putFileMetadata(Object var1, int var2, InputStream var3, CacheTag var4, AtomicBoolean var5) throws IOException;

   long markBuffersForProactiveEviction(Predicate var1, boolean var2);
}
