package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.orc.DataMask;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.PhysicalWriter;
import org.apache.orc.impl.OutStream;
import org.apache.orc.impl.StreamName;

public interface WriterContext {
   OutStream createStream(StreamName var1) throws IOException;

   int getRowIndexStride();

   boolean buildIndex();

   boolean isCompressed();

   OrcFile.EncodingStrategy getEncodingStrategy();

   boolean[] getBloomFilterColumns();

   double getBloomFilterFPP();

   Configuration getConfiguration();

   OrcFile.Version getVersion();

   /** @deprecated */
   @Deprecated
   OrcFile.BloomFilterVersion getBloomFilterVersion();

   void writeIndex(StreamName var1, OrcProto.RowIndex.Builder var2) throws IOException;

   void writeBloomFilter(StreamName var1, OrcProto.BloomFilterIndex.Builder var2) throws IOException;

   DataMask getUnencryptedMask(int var1);

   WriterEncryptionVariant getEncryption(int var1);

   PhysicalWriter getPhysicalWriter();

   void setEncoding(int var1, WriterEncryptionVariant var2, OrcProto.ColumnEncoding var3);

   void writeStatistics(StreamName var1, OrcProto.ColumnStatistics.Builder var2) throws IOException;

   boolean getUseUTCTimestamp();

   double getDictionaryKeySizeThreshold(int var1);

   boolean getProlepticGregorian();
}
