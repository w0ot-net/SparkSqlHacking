package com.github.luben.zstd;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ZstdDictTrainer {
   private final int allocatedSize;
   private final ByteBuffer trainingSamples;
   private final List sampleSizes;
   private final int dictSize;
   private long filledSize;

   public ZstdDictTrainer(int var1, int var2) {
      this.trainingSamples = ByteBuffer.allocateDirect(var1);
      this.sampleSizes = new ArrayList();
      this.allocatedSize = var1;
      this.dictSize = var2;
   }

   public synchronized boolean addSample(byte[] var1) {
      if (this.filledSize + (long)var1.length > (long)this.allocatedSize) {
         return false;
      } else {
         this.trainingSamples.put(var1);
         this.sampleSizes.add(var1.length);
         this.filledSize += (long)var1.length;
         return true;
      }
   }

   public ByteBuffer trainSamplesDirect() throws ZstdException {
      return this.trainSamplesDirect(false);
   }

   public synchronized ByteBuffer trainSamplesDirect(boolean var1) throws ZstdException {
      ByteBuffer var2 = ByteBuffer.allocateDirect(this.dictSize);
      long var3 = Zstd.trainFromBufferDirect(this.trainingSamples, this.copyToIntArray(this.sampleSizes), var2, var1);
      if (Zstd.isError(var3)) {
         var2.limit(0);
         throw new ZstdException(var3);
      } else {
         var2.limit(Long.valueOf(var3).intValue());
         return var2;
      }
   }

   public byte[] trainSamples() throws ZstdException {
      return this.trainSamples(false);
   }

   public byte[] trainSamples(boolean var1) throws ZstdException {
      ByteBuffer var2 = this.trainSamplesDirect(var1);
      byte[] var3 = new byte[var2.remaining()];
      var2.get(var3);
      return var3;
   }

   private int[] copyToIntArray(List var1) {
      int[] var2 = new int[var1.size()];
      int var3 = 0;

      for(Integer var5 : var1) {
         var2[var3] = var5;
         ++var3;
      }

      return var2;
   }
}
