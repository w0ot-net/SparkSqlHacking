package org.apache.orc;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public interface Writer extends Closeable {
   TypeDescription getSchema();

   void addUserMetadata(String var1, ByteBuffer var2);

   void addRowBatch(VectorizedRowBatch var1) throws IOException;

   void close() throws IOException;

   long getRawDataSize();

   long getNumberOfRows();

   long writeIntermediateFooter() throws IOException;

   void appendStripe(byte[] var1, int var2, int var3, StripeInformation var4, OrcProto.StripeStatistics var5) throws IOException;

   void appendStripe(byte[] var1, int var2, int var3, StripeInformation var4, StripeStatistics[] var5) throws IOException;

   /** @deprecated */
   void appendUserMetadata(List var1);

   ColumnStatistics[] getStatistics() throws IOException;

   List getStripes() throws IOException;

   long estimateMemory();
}
