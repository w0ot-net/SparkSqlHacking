package com.ning.compress.lzf.impl;

import com.ning.compress.BufferRecycler;
import com.ning.compress.lzf.ChunkEncoder;

public class VanillaChunkEncoder extends ChunkEncoder {
   public VanillaChunkEncoder(int totalLength) {
      super(totalLength);
   }

   protected VanillaChunkEncoder(int totalLength, boolean bogus) {
      super(totalLength, bogus);
   }

   public VanillaChunkEncoder(int totalLength, BufferRecycler bufferRecycler) {
      super(totalLength, bufferRecycler);
   }

   protected VanillaChunkEncoder(int totalLength, BufferRecycler bufferRecycler, boolean bogus) {
      super(totalLength, bufferRecycler, bogus);
   }

   public static VanillaChunkEncoder nonAllocatingEncoder(int totalLength) {
      return new VanillaChunkEncoder(totalLength, true);
   }

   public static VanillaChunkEncoder nonAllocatingEncoder(int totalLength, BufferRecycler bufferRecycler) {
      return new VanillaChunkEncoder(totalLength, bufferRecycler, true);
   }

   protected int tryCompress(byte[] param1, int param2, int param3, byte[] param4, int param5) {
      // $FF: Couldn't be decompiled
   }

   private final int _handleTail(byte[] in, int inPos, int inEnd, byte[] out, int outPos, int literals) {
      while(inPos < inEnd) {
         out[outPos++] = in[inPos++];
         ++literals;
         if (literals == 32) {
            out[outPos - literals - 1] = (byte)(literals - 1);
            literals = 0;
            ++outPos;
         }
      }

      out[outPos - literals - 1] = (byte)(literals - 1);
      if (literals == 0) {
         --outPos;
      }

      return outPos;
   }

   private final int first(byte[] in, int inPos) {
      return (in[inPos] << 8) + (in[inPos + 1] & 255);
   }
}
