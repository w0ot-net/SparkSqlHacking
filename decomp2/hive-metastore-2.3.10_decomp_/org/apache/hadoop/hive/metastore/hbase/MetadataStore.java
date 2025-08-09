package org.apache.hadoop.hive.metastore.hbase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public interface MetadataStore {
   void getFileMetadata(List var1, ByteBuffer[] var2) throws IOException;

   void storeFileMetadata(List var1, List var2, ByteBuffer[] var3, ByteBuffer[][] var4) throws IOException, InterruptedException;

   void storeFileMetadata(long var1, ByteBuffer var3, ByteBuffer[] var4, ByteBuffer[] var5) throws IOException, InterruptedException;
}
