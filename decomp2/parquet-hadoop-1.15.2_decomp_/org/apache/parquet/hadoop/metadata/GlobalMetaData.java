package org.apache.parquet.hadoop.metadata;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.parquet.schema.MessageType;

public class GlobalMetaData implements Serializable {
   private static final long serialVersionUID = 1L;
   private final MessageType schema;
   private final Map keyValueMetaData;
   private final Set createdBy;

   public GlobalMetaData(MessageType schema, Map keyValueMetaData, Set createdBy) {
      this.schema = (MessageType)Objects.requireNonNull(schema, "schema cannot be null");
      this.keyValueMetaData = Collections.unmodifiableMap((Map)Objects.requireNonNull(keyValueMetaData, "keyValueMetaData cannot be null"));
      this.createdBy = createdBy;
   }

   public MessageType getSchema() {
      return this.schema;
   }

   public String toString() {
      return "GlobalMetaData{schema: " + this.schema + ", metadata: " + this.keyValueMetaData + "}";
   }

   public Map getKeyValueMetaData() {
      return this.keyValueMetaData;
   }

   public Set getCreatedBy() {
      return this.createdBy;
   }

   public FileMetaData merge() {
      return this.merge(new StrictKeyValueMetadataMergeStrategy());
   }

   public FileMetaData merge(KeyValueMetadataMergeStrategy keyValueMetadataMergeStrategy) {
      String createdByString = this.createdBy.size() == 1 ? (String)this.createdBy.iterator().next() : this.createdBy.toString();
      Map<String, String> mergedKeyValues = keyValueMetadataMergeStrategy.merge(this.keyValueMetaData);
      return new FileMetaData(this.schema, mergedKeyValues, createdByString);
   }
}
