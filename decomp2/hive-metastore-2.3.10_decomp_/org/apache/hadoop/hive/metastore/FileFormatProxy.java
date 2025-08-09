package org.apache.hadoop.hive.metastore;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;

public interface FileFormatProxy {
   Metastore.SplitInfos applySargToMetadata(SearchArgument var1, ByteBuffer var2) throws IOException;

   ByteBuffer getMetadataToCache(FileSystem var1, Path var2, ByteBuffer[] var3) throws IOException;

   ByteBuffer[] getAddedColumnsToCache();

   ByteBuffer[][] getAddedValuesToCache(List var1);
}
