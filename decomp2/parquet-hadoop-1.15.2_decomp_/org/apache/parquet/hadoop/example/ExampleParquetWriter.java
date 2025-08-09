package org.apache.parquet.hadoop.example;

import java.io.IOException;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.schema.MessageType;

public class ExampleParquetWriter extends ParquetWriter {
   public static Builder builder(Path file) {
      return new Builder(file);
   }

   public static Builder builder(OutputFile file) {
      return new Builder(file);
   }

   ExampleParquetWriter(Path file, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize, boolean enableDictionary, boolean enableValidation, ParquetProperties.WriterVersion writerVersion, Configuration conf) throws IOException {
      super(file, writeSupport, compressionCodecName, blockSize, pageSize, pageSize, enableDictionary, enableValidation, writerVersion, conf);
   }

   public static class Builder extends ParquetWriter.Builder {
      private MessageType type;

      private Builder(Path file) {
         super(file);
         this.type = null;
      }

      private Builder(OutputFile file) {
         super(file);
         this.type = null;
      }

      public Builder withType(MessageType type) {
         this.type = type;
         return this;
      }

      protected Builder self() {
         return this;
      }

      protected WriteSupport getWriteSupport(Configuration conf) {
         return this.getWriteSupport((ParquetConfiguration)null);
      }

      protected WriteSupport getWriteSupport(ParquetConfiguration conf) {
         return new GroupWriteSupport(this.type);
      }

      public Builder withExtraMetaData(Map extraMetaData) {
         return (Builder)super.withExtraMetaData(extraMetaData);
      }
   }
}
