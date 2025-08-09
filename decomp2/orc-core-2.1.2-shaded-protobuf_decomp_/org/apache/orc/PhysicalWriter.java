package org.apache.orc;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.impl.StreamName;
import org.apache.orc.impl.writer.StreamOptions;
import org.apache.orc.impl.writer.WriterEncryptionVariant;

public interface PhysicalWriter {
   void writeHeader() throws IOException;

   OutputReceiver createDataStream(StreamName var1) throws IOException;

   void writeIndex(StreamName var1, OrcProto.RowIndex.Builder var2) throws IOException;

   void writeBloomFilter(StreamName var1, OrcProto.BloomFilterIndex.Builder var2) throws IOException;

   void finalizeStripe(OrcProto.StripeFooter.Builder var1, OrcProto.StripeInformation.Builder var2) throws IOException;

   void writeStatistics(StreamName var1, OrcProto.ColumnStatistics.Builder var2) throws IOException;

   void writeFileMetadata(OrcProto.Metadata.Builder var1) throws IOException;

   void writeFileFooter(OrcProto.Footer.Builder var1) throws IOException;

   long writePostScript(OrcProto.PostScript.Builder var1) throws IOException;

   void close() throws IOException;

   void flush() throws IOException;

   void appendRawStripe(ByteBuffer var1, OrcProto.StripeInformation.Builder var2) throws IOException;

   long getFileBytes(int var1, WriterEncryptionVariant var2);

   StreamOptions getStreamOptions();

   public interface OutputReceiver {
      void output(ByteBuffer var1) throws IOException;

      void suppress();
   }
}
