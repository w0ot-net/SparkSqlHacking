package org.apache.parquet.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;

public class Footer {
   private final Path file;
   private final ParquetMetadata parquetMetadata;

   public Footer(Path file, ParquetMetadata parquetMetadata) {
      this.file = file;
      this.parquetMetadata = parquetMetadata;
   }

   public Path getFile() {
      return this.file;
   }

   public ParquetMetadata getParquetMetadata() {
      return this.parquetMetadata;
   }

   public String toString() {
      return "Footer{" + this.file + ", " + this.parquetMetadata + "}";
   }
}
