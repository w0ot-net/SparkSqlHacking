package org.apache.hadoop.hive.metastore.messaging;

import java.util.Iterator;
import java.util.Map;
import org.apache.hadoop.hive.common.JavaUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.util.ReflectionUtils;

public abstract class MessageFactory {
   public static final String ADD_PARTITION_EVENT = "ADD_PARTITION";
   public static final String ALTER_PARTITION_EVENT = "ALTER_PARTITION";
   public static final String DROP_PARTITION_EVENT = "DROP_PARTITION";
   public static final String CREATE_TABLE_EVENT = "CREATE_TABLE";
   public static final String ALTER_TABLE_EVENT = "ALTER_TABLE";
   public static final String DROP_TABLE_EVENT = "DROP_TABLE";
   public static final String CREATE_DATABASE_EVENT = "CREATE_DATABASE";
   public static final String DROP_DATABASE_EVENT = "DROP_DATABASE";
   public static final String INSERT_EVENT = "INSERT";
   public static final String CREATE_FUNCTION_EVENT = "CREATE_FUNCTION";
   public static final String DROP_FUNCTION_EVENT = "DROP_FUNCTION";
   public static final String CREATE_INDEX_EVENT = "CREATE_INDEX";
   public static final String DROP_INDEX_EVENT = "DROP_INDEX";
   public static final String ALTER_INDEX_EVENT = "ALTER_INDEX";
   private static MessageFactory instance = null;
   protected static final HiveConf hiveConf = new HiveConf();
   /** @deprecated */
   @Deprecated
   private static final String CONF_LABEL_HCAT_MESSAGE_FACTORY_IMPL_PREFIX = "hcatalog.message.factory.impl.";
   protected static final String MS_SERVER_URL;
   protected static final String MS_SERVICE_PRINCIPAL;

   public static MessageFactory getInstance() {
      if (instance == null) {
         instance = getInstance(hiveConf.get(ConfVars.METASTORE_EVENT_MESSAGE_FACTORY.varname));
      }

      return instance;
   }

   private static MessageFactory getInstance(String className) {
      try {
         return (MessageFactory)ReflectionUtils.newInstance(JavaUtils.loadClass(className), hiveConf);
      } catch (ClassNotFoundException classNotFound) {
         throw new IllegalStateException("Could not construct MessageFactory implementation: ", classNotFound);
      }
   }

   public static MessageDeserializer getDeserializer(String format, String version) {
      return getInstance(hiveConf.get("hcatalog.message.factory.impl." + format, ConfVars.METASTORE_EVENT_MESSAGE_FACTORY.varname)).getDeserializer();
   }

   public abstract MessageDeserializer getDeserializer();

   public abstract String getMessageFormat();

   public abstract CreateDatabaseMessage buildCreateDatabaseMessage(Database var1);

   public abstract DropDatabaseMessage buildDropDatabaseMessage(Database var1);

   public abstract CreateTableMessage buildCreateTableMessage(Table var1, Iterator var2);

   public abstract AlterTableMessage buildAlterTableMessage(Table var1, Table var2);

   public abstract DropTableMessage buildDropTableMessage(Table var1);

   public abstract AddPartitionMessage buildAddPartitionMessage(Table var1, Iterator var2, Iterator var3);

   public abstract AlterPartitionMessage buildAlterPartitionMessage(Table var1, Partition var2, Partition var3);

   public abstract DropPartitionMessage buildDropPartitionMessage(Table var1, Iterator var2);

   public abstract CreateFunctionMessage buildCreateFunctionMessage(Function var1);

   public abstract DropFunctionMessage buildDropFunctionMessage(Function var1);

   public abstract CreateIndexMessage buildCreateIndexMessage(Index var1);

   public abstract DropIndexMessage buildDropIndexMessage(Index var1);

   public abstract AlterIndexMessage buildAlterIndexMessage(Index var1, Index var2);

   public abstract InsertMessage buildInsertMessage(String var1, String var2, Map var3, Iterator var4);

   static {
      hiveConf.addResource("hive-site.xml");
      MS_SERVER_URL = hiveConf.get(ConfVars.METASTOREURIS.name(), "");
      MS_SERVICE_PRINCIPAL = hiveConf.get(ConfVars.METASTORE_KERBEROS_PRINCIPAL.name(), "");
   }
}
