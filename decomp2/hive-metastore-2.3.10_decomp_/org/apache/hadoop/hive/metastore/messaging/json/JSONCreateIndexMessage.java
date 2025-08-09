package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.messaging.CreateIndexMessage;
import org.apache.thrift.TException;

public class JSONCreateIndexMessage extends CreateIndexMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String indexObjJson;
   @JsonProperty
   Long timestamp;

   public JSONCreateIndexMessage() {
   }

   public JSONCreateIndexMessage(String server, String servicePrincipal, Index index, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = index.getDbName();

      try {
         this.indexObjJson = JSONMessageFactory.createIndexObjJson(index);
      } catch (TException ex) {
         throw new IllegalArgumentException("Could not serialize Index object", ex);
      }

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

   public String getIndexObjJson() {
      return this.indexObjJson;
   }

   public Index getIndexObj() throws Exception {
      return (Index)JSONMessageFactory.getTObj(this.indexObjJson, Index.class);
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
