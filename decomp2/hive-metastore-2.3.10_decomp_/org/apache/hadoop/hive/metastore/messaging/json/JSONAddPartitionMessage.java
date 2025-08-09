package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.messaging.AddPartitionMessage;
import org.apache.hadoop.hive.metastore.messaging.PartitionFiles;
import org.apache.thrift.TException;

public class JSONAddPartitionMessage extends AddPartitionMessage {
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
   @JsonProperty
   List partitionListJson;
   @JsonProperty
   List partitionFiles;

   public JSONAddPartitionMessage() {
   }

   public JSONAddPartitionMessage(String server, String servicePrincipal, Table tableObj, Iterator partitionsIterator, Iterator partitionFileIter, Long timestamp) {
      this.server = server;
      this.servicePrincipal = servicePrincipal;
      this.db = tableObj.getDbName();
      this.table = tableObj.getTableName();
      this.timestamp = timestamp;
      this.partitions = new ArrayList();
      this.partitionListJson = new ArrayList();

      try {
         this.tableObjJson = JSONMessageFactory.createTableObjJson(tableObj);

         while(partitionsIterator.hasNext()) {
            Partition partitionObj = (Partition)partitionsIterator.next();
            this.partitions.add(JSONMessageFactory.getPartitionKeyValues(tableObj, partitionObj));
            this.partitionListJson.add(JSONMessageFactory.createPartitionObjJson(partitionObj));
         }
      } catch (TException e) {
         throw new IllegalArgumentException("Could not serialize: ", e);
      }

      this.partitionFiles = Lists.newArrayList(partitionFileIter);
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

   public String getTable() {
      return this.table;
   }

   public Table getTableObj() throws Exception {
      return (Table)JSONMessageFactory.getTObj(this.tableObjJson, Table.class);
   }

   public Long getTimestamp() {
      return this.timestamp;
   }

   public List getPartitions() {
      return this.partitions;
   }

   public Iterable getPartitionObjs() throws Exception {
      return Iterables.transform(JSONMessageFactory.getTObjs(this.partitionListJson, Partition.class), new Function() {
         @Nullable
         public Partition apply(@Nullable Object input) {
            return (Partition)input;
         }
      });
   }

   public String getTableObjJson() {
      return this.tableObjJson;
   }

   public List getPartitionListJson() {
      return this.partitionListJson;
   }

   public String toString() {
      try {
         return JSONMessageDeserializer.mapper.writeValueAsString(this);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not serialize: ", exception);
      }
   }

   public Iterable getPartitionFilesIter() {
      return this.partitionFiles;
   }
}
