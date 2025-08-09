package org.apache.hadoop.hive.metastore;

import java.util.List;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;

public class DefaultPartitionExpressionProxy implements PartitionExpressionProxy {
   public String convertExprToFilter(byte[] expr) throws MetaException {
      throw new UnsupportedOperationException();
   }

   public boolean filterPartitionsByExpr(List partColumnNames, List partColumnTypeInfos, byte[] expr, String defaultPartitionName, List partitionNames) throws MetaException {
      throw new UnsupportedOperationException();
   }

   public FileMetadataExprType getMetadataType(String inputFormat) {
      throw new UnsupportedOperationException();
   }

   public FileFormatProxy getFileFormatProxy(FileMetadataExprType type) {
      throw new UnsupportedOperationException();
   }

   public SearchArgument createSarg(byte[] expr) {
      throw new UnsupportedOperationException();
   }
}
