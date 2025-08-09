package org.apache.parquet.hadoop.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.rewrite.ParquetRewriter;
import org.apache.parquet.hadoop.rewrite.RewriteOptions;

/** @deprecated */
@Deprecated
public class ColumnEncryptor {
   private Configuration conf;

   public ColumnEncryptor(Configuration conf) {
      this.conf = conf;
   }

   public void encryptColumns(String inputFile, String outputFile, List paths, FileEncryptionProperties fileEncryptionProperties) throws IOException {
      Path inPath = new Path(inputFile);
      Path outPath = new Path(outputFile);
      RewriteOptions options = (new RewriteOptions.Builder(this.conf, inPath, outPath)).encrypt(paths).encryptionProperties(fileEncryptionProperties).build();
      ParquetRewriter rewriter = new ParquetRewriter(options);
      rewriter.processBlocks();
      rewriter.close();
   }

   public byte[] readBlock(int length, CompressionConverter.TransParquetFileReader reader) throws IOException {
      byte[] data = new byte[length];
      reader.blockRead(data, 0, length);
      return data;
   }

   public BytesInput readBlockAllocate(int length, CompressionConverter.TransParquetFileReader reader) throws IOException {
      byte[] data = new byte[length];
      reader.blockRead(data, 0, length);
      return BytesInput.from(data, 0, length);
   }

   public static Set convertToColumnPaths(List cols) {
      Set<ColumnPath> prunePaths = new HashSet();

      for(String col : cols) {
         prunePaths.add(ColumnPath.fromDotString(col));
      }

      return prunePaths;
   }
}
