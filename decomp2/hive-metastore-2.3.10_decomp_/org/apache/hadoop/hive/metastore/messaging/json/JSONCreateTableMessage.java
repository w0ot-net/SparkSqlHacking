package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.messaging.CreateTableMessage;
import org.apache.thrift.TException;

public class JSONCreateTableMessage extends CreateTableMessage {
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
   List files;

   public JSONCreateTableMessage() {
   }

   public JSONCreateTableMessage(String server, String servicePrincipal, String db, String table, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = db;
      this.table = table;
      this.timestamp = timestamp;
      this.checkValid();
   }

   public JSONCreateTableMessage(String server, String servicePrincipal, Table tableObj, Iterator fileIter, Long timestamp) {
      this(server, servicePrincipal, tableObj.getDbName(), tableObj.getTableName(), timestamp);

      try {
         this.tableObjJson = JSONMessageFactory.createTableObjJson(tableObj);
      } catch (TException e) {
         throw new IllegalArgumentException("Could not serialize: ", e);
      }

      this.files = Lists.newArrayList(fileIter);
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

   public Iterable getFiles() {
      return this.files;
   }
}
