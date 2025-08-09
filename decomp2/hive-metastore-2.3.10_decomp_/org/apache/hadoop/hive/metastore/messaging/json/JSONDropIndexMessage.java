package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.messaging.DropIndexMessage;

public class JSONDropIndexMessage extends DropIndexMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String indexName;
   @JsonProperty
   String origTableName;
   @JsonProperty
   String indexTableName;
   @JsonProperty
   Long timestamp;

   public JSONDropIndexMessage() {
   }

   public JSONDropIndexMessage(String server, String servicePrincipal, Index index, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = index.getDbName();
      this.indexName = index.getIndexName();
      this.origTableName = index.getOrigTableName();
      this.indexTableName = index.getIndexTableName();
      this.timestamp = timestamp;
      this.checkValid();
   }

   public String getDB() {
      return this.db;
   }

   public String getServer() {
      return this.server;
   }

   public String getServicePrincipal() {
      return this.servicePrincipal;
   }

   public Long getTimestamp() {
      return this.timestamp;
   }

   public String getIndexName() {
      return this.indexName;
   }

   public String getOrigTableName() {
      return this.origTableName;
   }

   public String getIndexTableName() {
      return this.indexTableName;
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
