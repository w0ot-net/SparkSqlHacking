package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.messaging.CreateDatabaseMessage;

public class JSONCreateDatabaseMessage extends CreateDatabaseMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   Long timestamp;

   public JSONCreateDatabaseMessage() {
   }

   public JSONCreateDatabaseMessage(String server, String servicePrincipal, String db, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = db;
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

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
