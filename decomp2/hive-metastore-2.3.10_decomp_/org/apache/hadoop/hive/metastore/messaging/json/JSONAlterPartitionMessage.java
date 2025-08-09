package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.messaging.AlterPartitionMessage;
import org.apache.thrift.TException;

public class JSONAlterPartitionMessage extends AlterPartitionMessage {
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
   Map keyValues;
   @JsonProperty
   String partitionObjBeforeJson;
   @JsonProperty
   String partitionObjAfterJson;

   public JSONAlterPartitionMessage() {
   }

   public JSONAlterPartitionMessage(String server, String servicePrincipal, Table tableObj, Partition partitionObjBefore, Partition partitionObjAfter, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = tableObj.getDbName();
      this.table = tableObj.getTableName();
      this.timestamp = timestamp;
      this.keyValues = JSONMessageFactory.getPartitionKeyValues(tableObj, partitionObjBefore);

      try {
         this.tableObjJson = JSONMessageFactory.createTableObjJson(tableObj);
         this.partitionObjBeforeJson = JSONMessageFactory.createPartitionObjJson(partitionObjBefore);
         this.partitionObjAfterJson = JSONMessageFactory.createPartitionObjJson(partitionObjAfter);
      } catch (TException e) {
         throw new IllegalArgumentException("Could not serialize: ", e);
      }

      this.checkValid();
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

   public String getTable() {
      return this.table;
   }

   public Map getKeyValues() {
      return this.keyValues;
   }

   public Table getTableObj() throws Exception {
      return (Table)JSONMessageFactory.getTObj(this.tableObjJson, Table.class);
   }

   public Partition getPtnObjBefore() throws Exception {
      return (Partition)JSONMessageFactory.getTObj(this.partitionObjBeforeJson, Partition.class);
   }

   public Partition getPtnObjAfter() throws Exception {
      return (Partition)JSONMessageFactory.getTObj(this.partitionObjAfterJson, Partition.class);
   }

   public String getTableObjJson() {
      return this.tableObjJson;
   }

   public String getPartitionObjBeforeJson() {
      return this.partitionObjBeforeJson;
   }

   public String getPartitionObjAfterJson() {
      return this.partitionObjAfterJson;
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception e) {
         throw new IllegalArgumentException("Could not serialize: ", e);
      }
   }
}
