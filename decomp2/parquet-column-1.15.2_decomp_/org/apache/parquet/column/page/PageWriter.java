package org.apache.parquet.column.page;

import java.io.IOException;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;

public interface PageWriter extends AutoCloseable {
   /** @deprecated */
   @Deprecated
   void writePage(BytesInput var1, int var2, Statistics var3, Encoding var4, Encoding var5, Encoding var6) throws IOException;

   void writePage(BytesInput var1, int var2, int var3, Statistics var4, Encoding var5, Encoding var6, Encoding var7) throws IOException;

   default void writePage(BytesInput bytesInput, int valueCount, int rowCount, Statistics statistics, SizeStatistics sizeStatistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
      throw new UnsupportedOperationException("writePage with SizeStatistics is not implemented");
   }

   void writePageV2(int var1, int var2, int var3, BytesInput var4, BytesInput var5, Encoding var6, BytesInput var7, Statistics var8) throws IOException;

   default void writePageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, Statistics statistics, SizeStatistics sizeStatistics) throws IOException {
      throw new UnsupportedOperationException("writePageV2 with SizeStatistics is not implemented");
   }

   long getMemSize();

   long allocatedSize();

   void writeDictionaryPage(DictionaryPage var1) throws IOException;

   String memUsageString(String var1);

   default void close() {
   }
}
