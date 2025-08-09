package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.messaging.AlterTableMessage;
import org.apache.thrift.TException;

public class JSONAlterTableMessage extends AlterTableMessage {
   @JsonProperty
   String server;
   @JsonProperty
   String servicePrincipal;
   @JsonProperty
   String db;
   @JsonProperty
   String table;
   @JsonProperty
   String tableObjBeforeJson;
   @JsonProperty
   String tableObjAfterJson;
   @JsonProperty
   Long timestamp;

   public JSONAlterTableMessage() {
   }

   public JSONAlterTableMessage(String server, String servicePrincipal, Table tableObjBefore, Table tableObjAfter, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = tableObjBefore.getDbName();
      this.table = tableObjBefore.getTableName();
      this.timestamp = timestamp;

      try {
         this.tableObjBeforeJson = JSONMessageFactory.createTableObjJson(tableObjBefore);
         this.tableObjAfterJson = JSONMessageFactory.createTableObjJson(tableObjAfter);
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

   public Table getTableObjBefore() throws Exception {
      return (Table)JSONMessageFactory.getTObj(this.tableObjBeforeJson, Table.class);
   }

   public Table getTableObjAfter() throws Exception {
      return (Table)JSONMessageFactory.getTObj(this.tableObjAfterJson, Table.class);
   }

   public String getTableObjBeforeJson() {
      return this.tableObjBeforeJson;
   }

   public String getTableObjAfterJson() {
      return this.tableObjAfterJson;
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception e) {
         throw new IllegalArgumentException("Could not serialize: ", e);
      }
   }
}
