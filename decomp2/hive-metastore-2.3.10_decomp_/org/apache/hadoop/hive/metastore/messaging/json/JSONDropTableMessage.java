package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.messaging.DropTableMessage;

public class JSONDropTableMessage extends DropTableMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String table;
   @JsonProperty
   Long timestamp;

   public JSONDropTableMessage() {
   }

   public JSONDropTableMessage(String server, String servicePrincipal, String db, String table, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = db;
      this.table = table;
      this.timestamp = timestamp;
      this.checkValid();
   }

   public String getTable() {
      return this.table;
   }

   public String getServer() {
      return this.server;
   }

   public String getServicePrincipal() {
      return this.servicePrincipal;
   }

   public String getDB() {
      return this.db;
   }

   public Long getTimestamp() {
      return this.timestamp;
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
