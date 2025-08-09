package org.apache.parquet.hadoop.example;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.example.data.simple.convert.GroupRecordConverter;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;

public class GroupReadSupport extends ReadSupport {
   public ReadSupport.ReadContext init(Configuration configuration, Map keyValueMetaData, MessageType fileSchema) {
      return this.init((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)), keyValueMetaData, fileSchema);
   }

   public ReadSupport.ReadContext init(ParquetConfiguration configuration, Map keyValueMetaData, MessageType fileSchema) {
      String partialSchemaString = configuration.get("parquet.read.schema");
      MessageType requestedProjection = getSchemaForRead(fileSchema, partialSchemaString);
      return new ReadSupport.ReadContext(requestedProjection);
   }

   public RecordMaterializer prepareForRead(Configuration configuration, Map keyValueMetaData, MessageType fileSchema, ReadSupport.ReadContext readContext) {
      return new GroupRecordConverter(readContext.getRequestedSchema());
   }

   public RecordMaterializer prepareForRead(ParquetConfiguration configuration, Map keyValueMetaData, MessageType fileSchema, ReadSupport.ReadContext readContext) {
      return new GroupRecordConverter(readContext.getRequestedSchema());
   }
}
