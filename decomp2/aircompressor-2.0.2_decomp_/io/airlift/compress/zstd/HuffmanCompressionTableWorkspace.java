package io.airlift.compress.zstd;

import java.util.Arrays;

class HuffmanCompressionTableWorkspace {
   public final NodeTable nodeTable = new NodeTable(511);
   public final short[] entriesPerRank = new short[13];
   public final short[] valuesPerRank = new short[13];
   public final int[] rankLast = new int[14];

   public void reset() {
      Arrays.fill(this.entriesPerRank, (short)0);
      Arrays.fill(this.valuesPerRank, (short)0);
   }
}
