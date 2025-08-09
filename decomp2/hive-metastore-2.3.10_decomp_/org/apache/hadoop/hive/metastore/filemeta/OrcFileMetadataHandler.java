package org.apache.hadoop.hive.metastore.filemeta;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.hadoop.hive.metastore.FileMetadataHandler;
import org.apache.hadoop.hive.metastore.Metastore;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;

public class OrcFileMetadataHandler extends FileMetadataHandler {
   protected FileMetadataExprType getType() {
      return FileMetadataExprType.ORC_SARG;
   }

   public void getFileMetadataByExpr(List fileIds, byte[] expr, ByteBuffer[] metadatas, ByteBuffer[] results, boolean[] eliminated) throws IOException {
      SearchArgument sarg = this.getExpressionProxy().createSarg(expr);
      if (metadatas == null) {
         metadatas = new ByteBuffer[results.length];
      }

      this.getStore().getFileMetadata(fileIds, metadatas);

      for(int i = 0; i < metadatas.length; ++i) {
         eliminated[i] = false;
         results[i] = null;
         if (metadatas[i] != null) {
            ByteBuffer metadata = metadatas[i].duplicate();
            Metastore.SplitInfos result = null;

            try {
               result = this.getFileFormatProxy().applySargToMetadata(sarg, metadata);
            } catch (IOException ex) {
               LOG.error("Failed to apply SARG to metadata", ex);
               metadatas[i] = null;
               continue;
            }

            eliminated[i] = result == null;
            if (!eliminated[i]) {
               results[i] = ByteBuffer.wrap(result.toByteArray());
            }
         }
      }

   }
}
