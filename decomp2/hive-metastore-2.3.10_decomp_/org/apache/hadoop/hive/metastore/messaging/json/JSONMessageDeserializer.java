package org.apache.hadoop.hive.metastore.messaging.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class JSONMessageDeserializer extends MessageDeserializer {
   static ObjectMapper mapper = new ObjectMapper();

   public CreateDatabaseMessage getCreateDatabaseMessage(String messageBody) {
      try {
         return (CreateDatabaseMessage)mapper.readValue(messageBody, JSONCreateDatabaseMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONCreateDatabaseMessage.", exception);
      }
   }

   public DropDatabaseMessage getDropDatabaseMessage(String messageBody) {
      try {
         return (DropDatabaseMessage)mapper.readValue(messageBody, JSONDropDatabaseMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONDropDatabaseMessage.", exception);
      }
   }

   public CreateTableMessage getCreateTableMessage(String messageBody) {
      try {
         return (CreateTableMessage)mapper.readValue(messageBody, JSONCreateTableMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONCreateTableMessage.", exception);
      }
   }

   public AlterTableMessage getAlterTableMessage(String messageBody) {
      try {
         return (AlterTableMessage)mapper.readValue(messageBody, JSONAlterTableMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct appropriate alter table type.", exception);
      }
   }

   public DropTableMessage getDropTableMessage(String messageBody) {
      try {
         return (DropTableMessage)mapper.readValue(messageBody, JSONDropTableMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONDropTableMessage.", exception);
      }
   }

   public AddPartitionMessage getAddPartitionMessage(String messageBody) {
      try {
         return (AddPartitionMessage)mapper.readValue(messageBody, JSONAddPartitionMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct AddPartitionMessage.", exception);
      }
   }

   public AlterPartitionMessage getAlterPartitionMessage(String messageBody) {
      try {
         return (AlterPartitionMessage)mapper.readValue(messageBody, JSONAlterPartitionMessage.class);
      } catch (Exception e) {
         throw new IllegalArgumentException("Could not construct AlterPartitionMessage.", e);
      }
   }

   public DropPartitionMessage getDropPartitionMessage(String messageBody) {
      try {
         return (DropPartitionMessage)mapper.readValue(messageBody, JSONDropPartitionMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct DropPartitionMessage.", exception);
      }
   }

   public CreateFunctionMessage getCreateFunctionMessage(String messageBody) {
      try {
         return (CreateFunctionMessage)mapper.readValue(messageBody, JSONCreateFunctionMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONCreateFunctionMessage.", exception);
      }
   }

   public DropFunctionMessage getDropFunctionMessage(String messageBody) {
      try {
         return (DropFunctionMessage)mapper.readValue(messageBody, JSONDropFunctionMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONDropDatabaseMessage.", exception);
      }
   }

   public CreateIndexMessage getCreateIndexMessage(String messageBody) {
      try {
         return (CreateIndexMessage)mapper.readValue(messageBody, JSONCreateIndexMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONCreateIndexMessage.", exception);
      }
   }

   public DropIndexMessage getDropIndexMessage(String messageBody) {
      try {
         return (DropIndexMessage)mapper.readValue(messageBody, JSONDropIndexMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONDropIndexMessage.", exception);
      }
   }

   public AlterIndexMessage getAlterIndexMessage(String messageBody) {
      try {
         return (AlterIndexMessage)mapper.readValue(messageBody, JSONAlterIndexMessage.class);
      } catch (Exception exception) {
         throw new IllegalArgumentException("Could not construct JSONAlterIndexMessage.", exception);
      }
   }

   public InsertMessage getInsertMessage(String messageBody) {
      try {
         return (InsertMessage)mapper.readValue(messageBody, JSONInsertMessage.class);
      } catch (Exception e) {
         throw new IllegalArgumentException("Could not construct InsertMessage", e);
      }
   }

   static {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
      mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
      mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
   }
}
