package org.apache.parquet.hadoop.api;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

public abstract class ReadSupport {
   public static final String PARQUET_READ_SCHEMA = "parquet.read.schema";

   public static MessageType getSchemaForRead(MessageType fileMessageType, String partialReadSchemaString) {
      if (partialReadSchemaString == null) {
         return fileMessageType;
      } else {
         MessageType requestedMessageType = MessageTypeParser.parseMessageType(partialReadSchemaString);
         return getSchemaForRead(fileMessageType, requestedMessageType);
      }
   }

   public static MessageType getSchemaForRead(MessageType fileMessageType, MessageType projectedMessageType) {
      fileMessageType.checkContains(projectedMessageType);
      return projectedMessageType;
   }

   /** @deprecated */
   @Deprecated
   public ReadContext init(Configuration configuration, Map keyValueMetaData, MessageType fileSchema) {
      throw new UnsupportedOperationException("Override ReadSupport.init(InitContext)");
   }

   /** @deprecated */
   @Deprecated
   public ReadContext init(ParquetConfiguration configuration, Map keyValueMetaData, MessageType fileSchema) {
      return this.init(ConfigurationUtil.createHadoopConfiguration(configuration), keyValueMetaData, fileSchema);
   }

   public ReadContext init(InitContext context) {
      return this.init(context.getParquetConfiguration(), context.getMergedKeyValueMetaData(), context.getFileSchema());
   }

   /** @deprecated */
   @Deprecated
   public abstract RecordMaterializer prepareForRead(Configuration var1, Map var2, MessageType var3, ReadContext var4);

   public RecordMaterializer prepareForRead(ParquetConfiguration configuration, Map keyValueMetaData, MessageType fileSchema, ReadContext readContext) {
      return this.prepareForRead(ConfigurationUtil.createHadoopConfiguration(configuration), keyValueMetaData, fileSchema, readContext);
   }

   public static final class ReadContext {
      private final MessageType requestedSchema;
      private final Map readSupportMetadata;

      public ReadContext(MessageType requestedSchema) {
         this(requestedSchema, (Map)null);
      }

      public ReadContext(MessageType requestedSchema, Map readSupportMetadata) {
         if (requestedSchema == null) {
            throw new NullPointerException("requestedSchema");
         } else {
            this.requestedSchema = requestedSchema;
            this.readSupportMetadata = readSupportMetadata;
         }
      }

      public MessageType getRequestedSchema() {
         return this.requestedSchema;
      }

      public Map getReadSupportMetadata() {
         return this.readSupportMetadata;
      }
   }
}
