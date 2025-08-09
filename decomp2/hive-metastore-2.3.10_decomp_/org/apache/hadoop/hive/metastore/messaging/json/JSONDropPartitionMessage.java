package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.messaging.DropPartitionMessage;
import org.apache.thrift.TException;

public class JSONDropPartitionMessage extends DropPartitionMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String table;
   @JsonProperty
   String tableObjJson;
   @JsonProperty
   Long timestamp;
   @JsonProperty
   List partitions;

   public JSONDropPartitionMessage() {
   }

   public JSONDropPartitionMessage(String server, String servicePrincipal, String db, String table, List partitions, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = db;
      this.table = table;
      this.partitions = partitions;
      this.timestamp = timestamp;
      this.checkValid();
   }

   public JSONDropPartitionMessage(String server, String servicePrincipal, Table tableObj, List partitionKeyValues, long timestamp) {
      this(server, servicePrincipal, tableObj.getDbName(), tableObj.getTableName(), partitionKeyValues, timestamp);

      try {
         this.tableObjJson = JSONMessageFactory.createTableObjJson(tableObj);
      } catch (TException e) {
         throw new IllegalArgumentException("Could not serialize: ", e);
      }
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

   public String getTable() {
      return this.table;
   }

   public Long getTimestamp() {
      return this.timestamp;
   }

   public List getPartitions() {
      return this.partitions;
   }

   public Table getTableObj() throws Exception {
      return (Table)JSONMessageFactory.getTObj(this.tableObjJson, Table.class);
   }

   public String getTableObjJson() {
      return this.tableObjJson;
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }
}
