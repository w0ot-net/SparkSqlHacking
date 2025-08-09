package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.messaging.AddPartitionMessage;
import org.apache.hadoop.hive.metastore.messaging.AlterIndexMessage;
import org.apache.hadoop.hive.metastore.messaging.AlterPartitionMessage;
import org.apache.hadoop.hive.metastore.messaging.AlterTableMessage;
import org.apache.hadoop.hive.metastore.messaging.CreateDatabaseMessage;
import org.apache.hadoop.hive.metastore.messaging.CreateFunctionMessage;
import org.apache.hadoop.hive.metastore.messaging.CreateIndexMessage;
import org.apache.hadoop.hive.metastore.messaging.CreateTableMessage;
import org.apache.hadoop.hive.metastore.messaging.DropDatabaseMessage;
import org.apache.hadoop.hive.metastore.messaging.DropFunctionMessage;
import org.apache.hadoop.hive.metastore.messaging.DropIndexMessage;
import org.apache.hadoop.hive.metastore.messaging.DropPartitionMessage;
import org.apache.hadoop.hive.metastore.messaging.DropTableMessage;
import org.apache.hadoop.hive.metastore.messaging.InsertMessage;
import org.apache.hadoop.hive.metastore.messaging.MessageDeserializer;
import org.apache.hadoop.hive.metastore.messaging.MessageFactory;
import org.apache.hadoop.hive.metastore.messaging.PartitionFiles;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONMessageFactory extends MessageFactory {
   private static final Logger LOG = LoggerFactory.getLogger(JSONMessageFactory.class.getName());
   private static JSONMessageDeserializer deserializer = new JSONMessageDeserializer();
   private static TDeserializer thriftDeSerializer;

   public MessageDeserializer getDeserializer() {
      return deserializer;
   }

   public String getMessageFormat() {
      return "json-0.2";
   }

   public CreateDatabaseMessage buildCreateDatabaseMessage(Database db) {
      return new JSONCreateDatabaseMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, db.getName(), this.now());
   }

   public DropDatabaseMessage buildDropDatabaseMessage(Database db) {
      return new JSONDropDatabaseMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, db.getName(), this.now());
   }

   public CreateTableMessage buildCreateTableMessage(Table table, Iterator fileIter) {
      return new JSONCreateTableMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, table, fileIter, this.now());
   }

   public AlterTableMessage buildAlterTableMessage(Table before, Table after) {
      return new JSONAlterTableMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, before, after, this.now());
   }

   public DropTableMessage buildDropTableMessage(Table table) {
      return new JSONDropTableMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, table.getDbName(), table.getTableName(), this.now());
   }

   public AddPartitionMessage buildAddPartitionMessage(Table table, Iterator partitionsIterator, Iterator partitionFileIter) {
      return new JSONAddPartitionMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, table, partitionsIterator, partitionFileIter, this.now());
   }

   public AlterPartitionMessage buildAlterPartitionMessage(Table table, Partition before, Partition after) {
      return new JSONAlterPartitionMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, table, before, after, this.now());
   }

   public DropPartitionMessage buildDropPartitionMessage(Table table, Iterator partitionsIterator) {
      return new JSONDropPartitionMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, table, getPartitionKeyValues(table, partitionsIterator), this.now());
   }

   public CreateFunctionMessage buildCreateFunctionMessage(org.apache.hadoop.hive.metastore.api.Function fn) {
      return new JSONCreateFunctionMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, fn, this.now());
   }

   public DropFunctionMessage buildDropFunctionMessage(org.apache.hadoop.hive.metastore.api.Function fn) {
      return new JSONDropFunctionMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, fn, this.now());
   }

   public CreateIndexMessage buildCreateIndexMessage(Index idx) {
      return new JSONCreateIndexMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, idx, this.now());
   }

   public DropIndexMessage buildDropIndexMessage(Index idx) {
      return new JSONDropIndexMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, idx, this.now());
   }

   public AlterIndexMessage buildAlterIndexMessage(Index before, Index after) {
      return new JSONAlterIndexMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, before, after, this.now());
   }

   public InsertMessage buildInsertMessage(String db, String table, Map partKeyVals, Iterator fileIter) {
      return new JSONInsertMessage(MS_SERVER_URL, MS_SERVICE_PRINCIPAL, db, table, partKeyVals, fileIter, this.now());
   }

   private long now() {
      return System.currentTimeMillis() / 1000L;
   }

   static Map getPartitionKeyValues(Table table, Partition partition) {
      Map<String, String> partitionKeys = new LinkedHashMap();

      for(int i = 0; i < table.getPartitionKeysSize(); ++i) {
         partitionKeys.put(((FieldSchema)table.getPartitionKeys().get(i)).getName(), partition.getValues().get(i));
      }

      return partitionKeys;
   }

   static List getPartitionKeyValues(final Table table, Iterator iterator) {
      return Lists.newArrayList(Iterators.transform(iterator, new Function() {
         public Map apply(@Nullable Partition partition) {
            return JSONMessageFactory.getPartitionKeyValues(table, partition);
         }
      }));
   }

   static String createTableObjJson(Table tableObj) throws TException {
      TSerializer serializer = new TSerializer(new TJSONProtocol.Factory());
      return serializer.toString(tableObj);
   }

   static String createPartitionObjJson(Partition partitionObj) throws TException {
      TSerializer serializer = new TSerializer(new TJSONProtocol.Factory());
      return serializer.toString(partitionObj);
   }

   static String createFunctionObjJson(org.apache.hadoop.hive.metastore.api.Function functionObj) throws TException {
      TSerializer serializer = new TSerializer(new TJSONProtocol.Factory());
      return serializer.toString(functionObj);
   }

   static String createIndexObjJson(Index indexObj) throws TException {
      TSerializer serializer = new TSerializer(new TJSONProtocol.Factory());
      return serializer.toString(indexObj);
   }

   public static ObjectNode getJsonTree(NotificationEvent event) throws Exception {
      return getJsonTree(event.getMessage());
   }

   public static ObjectNode getJsonTree(String eventMessage) throws Exception {
      JsonParser jsonParser = (new JsonFactory()).createJsonParser(eventMessage);
      ObjectMapper mapper = new ObjectMapper();
      return (ObjectNode)mapper.readValue(jsonParser, ObjectNode.class);
   }

   public static Table getTableObj(ObjectNode jsonTree) throws Exception {
      TDeserializer deSerializer = new TDeserializer(new TJSONProtocol.Factory());
      Table tableObj = new Table();
      String tableJson = jsonTree.get("tableObjJson").asText();
      deSerializer.deserialize(tableObj, tableJson, "UTF-8");
      return tableObj;
   }

   public static TBase getTObj(String tSerialized, Class objClass) throws Exception {
      TBase obj = (TBase)objClass.newInstance();
      thriftDeSerializer.deserialize(obj, tSerialized, "UTF-8");
      return obj;
   }

   public static Iterable getTObjs(Iterable objRefStrs, final Class objClass) throws Exception {
      try {
         return Iterables.transform(objRefStrs, new Function() {
            public TBase apply(@Nullable String objStr) {
               try {
                  return JSONMessageFactory.getTObj(objStr, objClass);
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            }
         });
      } catch (RuntimeException re) {
         Throwable t = re.getCause();
         if (t instanceof Exception) {
            throw (Exception)t;
         } else {
            throw re;
         }
      }
   }

   public static Iterable getTObjs(ObjectNode jsonTree, String objRefListName, Class objClass) throws Exception {
      Iterable<JsonNode> jsonArrayIterator = jsonTree.get(objRefListName);
      Function<JsonNode, String> textExtractor = new Function() {
         @Nullable
         public String apply(@Nullable JsonNode input) {
            return input.asText();
         }
      };
      return getTObjs(Iterables.transform(jsonArrayIterator, textExtractor), objClass);
   }

   static {
      try {
         thriftDeSerializer = new TDeserializer(new TJSONProtocol.Factory());
      } catch (TTransportException e) {
         e.printStackTrace();
      }

   }
}
