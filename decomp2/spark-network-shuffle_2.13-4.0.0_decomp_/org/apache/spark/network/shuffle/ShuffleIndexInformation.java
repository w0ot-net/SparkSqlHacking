package org.apache.spark.network.shuffle;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.file.Files;

public class ShuffleIndexInformation {
   static final int INSTANCE_MEMORY_FOOTPRINT = 176;
   private final LongBuffer offsets;

   public ShuffleIndexInformation(String indexFilePath) throws IOException {
      File indexFile = new File(indexFilePath);
      ByteBuffer buffer = ByteBuffer.allocate((int)indexFile.length());
      this.offsets = buffer.asLongBuffer();
      DataInputStream dis = new DataInputStream(Files.newInputStream(indexFile.toPath()));

      try {
         dis.readFully(buffer.array());
      } catch (Throwable var8) {
         try {
            dis.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      dis.close();
   }

   public int getRetainedMemorySize() {
      return (this.offsets.capacity() << 3) + 176;
   }

   public ShuffleIndexRecord getIndex(int reduceId) {
      return this.getIndex(reduceId, reduceId + 1);
   }

   public ShuffleIndexRecord getIndex(int startReduceId, int endReduceId) {
      long offset = this.offsets.get(startReduceId);
      long nextOffset = this.offsets.get(endReduceId);
      return new ShuffleIndexRecord(offset, nextOffset - offset);
   }
}
