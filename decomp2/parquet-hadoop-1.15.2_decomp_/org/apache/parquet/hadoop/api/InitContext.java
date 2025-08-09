package org.apache.parquet.hadoop.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.schema.MessageType;

public class InitContext {
   private final Map keyValueMetadata;
   private Map mergedKeyValueMetadata;
   private final ParquetConfiguration configuration;
   private final MessageType fileSchema;

   public InitContext(Configuration configuration, Map keyValueMetadata, MessageType fileSchema) {
      this((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)), keyValueMetadata, fileSchema);
   }

   public InitContext(ParquetConfiguration configuration, Map keyValueMetadata, MessageType fileSchema) {
      this.keyValueMetadata = keyValueMetadata;
      this.configuration = configuration;
      this.fileSchema = fileSchema;
   }

   /** @deprecated */
   @Deprecated
   public Map getMergedKeyValueMetaData() {
      if (this.mergedKeyValueMetadata == null) {
         Map<String, String> mergedKeyValues = new HashMap();

         for(Map.Entry entry : this.keyValueMetadata.entrySet()) {
            if (((Set)entry.getValue()).size() > 1) {
               throw new RuntimeException("could not merge metadata: key " + (String)entry.getKey() + " has conflicting values: " + entry.getValue());
            }

            mergedKeyValues.put(entry.getKey(), ((Set)entry.getValue()).iterator().next());
         }

         this.mergedKeyValueMetadata = mergedKeyValues;
      }

      return this.mergedKeyValueMetadata;
   }

   /** @deprecated */
   @Deprecated
   public Configuration getConfiguration() {
      return ConfigurationUtil.createHadoopConfiguration(this.configuration);
   }

   public ParquetConfiguration getParquetConfiguration() {
      return this.configuration;
   }

   public MessageType getFileSchema() {
      return this.fileSchema;
   }

   public Map getKeyValueMetadata() {
      return this.keyValueMetadata;
   }
}
