package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.messaging.DropFunctionMessage;

public class JSONDropFunctionMessage extends DropFunctionMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String functionName;
   @JsonProperty
   Long timestamp;

   public JSONDropFunctionMessage() {
   }

   public JSONDropFunctionMessage(String server, String servicePrincipal, Function fn, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = fn.getDbName();
      this.functionName = fn.getFunctionName();
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

   public String getFunctionName() {
      return this.functionName;
   }
}
