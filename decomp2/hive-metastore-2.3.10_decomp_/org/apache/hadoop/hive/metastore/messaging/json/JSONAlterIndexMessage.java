package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.messaging.AlterIndexMessage;
import org.apache.thrift.TException;

public class JSONAlterIndexMessage extends AlterIndexMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String beforeIndexObjJson;
   @JsonProperty
   String afterIndexObjJson;
   @JsonProperty
   Long timestamp;

   public JSONAlterIndexMessage() {
   }

   public JSONAlterIndexMessage(String server, String servicePrincipal, Index before, Index after, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = after.getDbName();
      this.timestamp = timestamp;

      try {
         this.beforeIndexObjJson = JSONMessageFactory.createIndexObjJson(before);
         this.afterIndexObjJson = JSONMessageFactory.createIndexObjJson(after);
      } catch (TException ex) {
         throw new IllegalArgumentException("Could not serialize Index object", ex);
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

   public String getBeforeIndexObjJson() {
      return this.beforeIndexObjJson;
   }

   public String getAfterIndexObjJson() {
      return this.afterIndexObjJson;
   }

   public Index getIndexObjBefore() throws Exception {
      return (Index)JSONMessageFactory.getTObj(this.beforeIndexObjJson, Index.class);
   }

   public Index getIndexObjAfter() throws Exception {
      return (Index)JSONMessageFactory.getTObj(this.afterIndexObjJson, Index.class);
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
