package org.apache.hadoop.hive.metastore;

import java.util.List;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;

public interface PartitionExpressionProxy {
   String convertExprToFilter(byte[] var1) throws MetaException;

   boolean filterPartitionsByExpr(List var1, List var2, byte[] var3, String var4, List var5) throws MetaException;

   FileMetadataExprType getMetadataType(String var1);

   FileFormatProxy getFileFormatProxy(FileMetadataExprType var1);

   SearchArgument createSarg(byte[] var1);
}
