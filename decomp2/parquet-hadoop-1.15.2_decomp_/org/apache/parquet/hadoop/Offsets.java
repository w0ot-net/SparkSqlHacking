package org.apache.parquet.hadoop;

import java.io.IOException;
import org.apache.parquet.format.PageHeader;
import org.apache.parquet.format.Util;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.io.SeekableInputStream;

class Offsets {
   public final long firstDataPageOffset;
   public final long dictionaryPageOffset;

   public static Offsets getOffsets(SeekableInputStream input, ColumnChunkMetaData chunk, long newChunkStart) throws IOException {
      long firstDataPageOffset;
      long dictionaryPageOffset;
      if (chunk.hasDictionaryPage()) {
         long dictionaryPageSize;
         if (chunk.getDictionaryPageOffset() != 0L && chunk.getFirstDataPageOffset() > chunk.getDictionaryPageOffset()) {
            dictionaryPageSize = chunk.getFirstDataPageOffset() - chunk.getDictionaryPageOffset();
         } else {
            dictionaryPageSize = readDictionaryPageSize(input, chunk);
         }

         firstDataPageOffset = newChunkStart + dictionaryPageSize;
         dictionaryPageOffset = newChunkStart;
      } else {
         firstDataPageOffset = newChunkStart;
         dictionaryPageOffset = 0L;
      }

      return new Offsets(firstDataPageOffset, dictionaryPageOffset);
   }

   private static long readDictionaryPageSize(SeekableInputStream in, ColumnChunkMetaData chunk) throws IOException {
      long origPos = -1L;

      long var9;
      try {
         origPos = in.getPos();
         in.seek(chunk.getStartingPos());
         long headerStart = in.getPos();
         PageHeader header = Util.readPageHeader(in);
         long headerSize = in.getPos() - headerStart;
         var9 = headerSize + (long)header.getCompressed_page_size();
      } finally {
         if (origPos != -1L) {
            in.seek(origPos);
         }

      }

      return var9;
   }

   private Offsets(long firstDataPageOffset, long dictionaryPageOffset) {
      this.firstDataPageOffset = firstDataPageOffset;
      this.dictionaryPageOffset = dictionaryPageOffset;
   }
}
