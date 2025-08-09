package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.messaging.InsertMessage;

public class JSONInsertMessage extends InsertMessage {
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
   @JsonProperty
   List files;
   @JsonProperty
   Map partKeyVals;

   public JSONInsertMessage() {
   }

   public JSONInsertMessage(String server, String servicePrincipal, String db, String table, Map partKeyVals, Iterator fileIter, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = db;
      this.table = table;
      this.timestamp = timestamp;
      this.partKeyVals = partKeyVals;
      this.files = Lists.newArrayList(fileIter);
      this.checkValid();
   }

   public String getTable() {
      return this.table;
   }

   public String getServer() {
      return this.server;
   }

   public Map getPartitionKeyValues() {
      return this.partKeyVals;
   }

   public Iterable getFiles() {
      return this.files;
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
