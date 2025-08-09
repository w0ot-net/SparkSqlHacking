package org.apache.parquet.hadoop.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.rewrite.ParquetRewriter;
import org.apache.parquet.schema.MessageType;

/** @deprecated */
@Deprecated
public class ColumnMasker {
   public void processBlocks(CompressionConverter.TransParquetFileReader reader, ParquetFileWriter writer, ParquetMetadata meta, MessageType schema, List paths, MaskMode maskMode) throws IOException {
      ParquetRewriter rewriter = new ParquetRewriter(reader, writer, meta, schema, (String)null, (CompressionCodecName)null, paths, this.convertMaskMode(maskMode));
      rewriter.processBlocks();
   }

   org.apache.parquet.hadoop.rewrite.MaskMode convertMaskMode(MaskMode maskMode) {
      switch (maskMode) {
         case NULLIFY:
            return org.apache.parquet.hadoop.rewrite.MaskMode.NULLIFY;
         case HASH:
            return org.apache.parquet.hadoop.rewrite.MaskMode.HASH;
         case REDACT:
            return org.apache.parquet.hadoop.rewrite.MaskMode.REDACT;
         default:
            return null;
      }
   }

   public static Set convertToColumnPaths(List cols) {
      Set<ColumnPath> prunePaths = new HashSet();

      for(String col : cols) {
         prunePaths.add(ColumnPath.fromDotString(col));
      }

      return prunePaths;
   }

   public static enum MaskMode {
      NULLIFY("nullify"),
      HASH("hash"),
      REDACT("redact");

      private String mode;

      private MaskMode(String text) {
         this.mode = text;
      }

      public String getMode() {
         return this.mode;
      }

      public static MaskMode fromString(String mode) {
         for(MaskMode b : values()) {
            if (b.mode.equalsIgnoreCase(mode)) {
               return b;
            }
         }

         return null;
      }
   }
}
