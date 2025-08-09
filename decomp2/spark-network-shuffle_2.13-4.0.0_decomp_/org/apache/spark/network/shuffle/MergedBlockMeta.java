package org.apache.spark.network.shuffle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.protocol.Encoders.Bitmaps;
import org.roaringbitmap.RoaringBitmap;
import org.sparkproject.guava.base.Preconditions;

public class MergedBlockMeta {
   private final int numChunks;
   private final ManagedBuffer chunksBitmapBuffer;

   public MergedBlockMeta(int numChunks, ManagedBuffer chunksBitmapBuffer) {
      this.numChunks = numChunks;
      this.chunksBitmapBuffer = (ManagedBuffer)Preconditions.checkNotNull(chunksBitmapBuffer);
   }

   public int getNumChunks() {
      return this.numChunks;
   }

   public ManagedBuffer getChunksBitmapBuffer() {
      return this.chunksBitmapBuffer;
   }

   public RoaringBitmap[] readChunkBitmaps() throws IOException {
      ByteBuf buf = Unpooled.wrappedBuffer(this.chunksBitmapBuffer.nioByteBuffer());
      List<RoaringBitmap> bitmaps = new ArrayList();

      while(buf.isReadable()) {
         bitmaps.add(Bitmaps.decode(buf));
      }

      assert bitmaps.size() == this.numChunks;

      return (RoaringBitmap[])bitmaps.toArray(new RoaringBitmap[0]);
   }
}
