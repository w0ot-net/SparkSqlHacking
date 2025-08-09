package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.messaging.CreateFunctionMessage;
import org.apache.thrift.TException;

public class JSONCreateFunctionMessage extends CreateFunctionMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String functionObjJson;
   @JsonProperty
   Long timestamp;

   public JSONCreateFunctionMessage() {
   }

   public JSONCreateFunctionMessage(String server, String servicePrincipal, Function fn, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = fn.getDbName();
      this.timestamp = timestamp;

      try {
         this.functionObjJson = JSONMessageFactory.createFunctionObjJson(fn);
      } catch (TException ex) {
         throw new IllegalArgumentException("Could not serialize Function object", ex);
      }

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

   public String getFunctionObjJson() {
      return this.functionObjJson;
   }

   public Function getFunctionObj() throws Exception {
      return (Function)JSONMessageFactory.getTObj(this.functionObjJson, Function.class);
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
