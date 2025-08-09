package org.datanucleus.store;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.flush.FlushProcess;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.store.connection.ConnectionManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.query.QueryManager;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.store.schema.naming.NamingFactory;
import org.datanucleus.store.valuegenerator.ValueGenerationManager;

public interface StoreManager {
   String OPTION_APPLICATION_ID = "ApplicationId";
   String OPTION_APPLICATION_COMPOSITE_ID = "ApplicationCompositeId";
   String OPTION_DATASTORE_ID = "DatastoreId";
   String OPTION_NONDURABLE_ID = "NonDurableId";
   String OPTION_ORM = "ORM";
   String OPTION_ORM_EMBEDDED_PC = "ORM.EmbeddedPC";
   String OPTION_ORM_EMBEDDED_COLLECTION = "ORM.EmbeddedCollection";
   String OPTION_ORM_EMBEDDED_MAP = "ORM.EmbeddedMap";
   String OPTION_ORM_EMBEDDED_ARRAY = "ORM.EmbeddedArray";
   String OPTION_ORM_EMBEDDED_PC_NESTED = "ORM.EmbeddedPC.Nested";
   String OPTION_ORM_EMBEDDED_COLLECTION_NESTED = "ORM.EmbeddedCollection.Nested";
   String OPTION_ORM_EMBEDDED_MAP_NESTED = "ORM.EmbeddedMap.Nested";
   String OPTION_ORM_EMBEDDED_ARRAY_NESTED = "ORM.EmbeddedArray.Nested";
   String OPTION_ORM_SECONDARY_TABLE = "ORM.SecondaryTable";
   String OPTION_ORM_FOREIGN_KEYS = "ORM.ForeignKeys";
   String OPTION_TXN_ISOLATION_READ_COMMITTED = "TransactionIsolationLevel.read-committed";
   String OPTION_TXN_ISOLATION_READ_UNCOMMITTED = "TransactionIsolationLevel.read-uncommitted";
   String OPTION_TXN_ISOLATION_REPEATABLE_READ = "TransactionIsolationLevel.repeatable-read";
   String OPTION_TXN_ISOLATION_SERIALIZABLE = "TransactionIsolationLevel.serializable";
   String OPTION_QUERY_CANCEL = "Query.Cancel";
   String OPTION_QUERY_JDOQL_BITWISE_OPS = "Query.JDOQL.BitwiseOperations";
   String OPTION_DATASTORE_TIMEOUT = "Datastore.Timeout";

   Collection getSupportedOptions();

   void close();

   MetaDataManager getMetaDataManager();

   StorePersistenceHandler getPersistenceHandler();

   FlushProcess getFlushProcess();

   NamingFactory getNamingFactory();

   QueryManager getQueryManager();

   StoreSchemaHandler getSchemaHandler();

   StoreData getStoreDataForClass(String var1);

   NucleusSequence getNucleusSequence(ExecutionContext var1, SequenceMetaData var2);

   NucleusConnection getNucleusConnection(ExecutionContext var1);

   ConnectionManager getConnectionManager();

   ManagedConnection getConnection(ExecutionContext var1);

   ManagedConnection getConnection(ExecutionContext var1, Map var2);

   ManagedConnection getConnection(int var1);

   String getConnectionURL();

   String getConnectionUserName();

   String getConnectionPassword();

   String getConnectionDriverName();

   Object getConnectionFactory();

   String getConnectionFactoryName();

   Object getConnectionFactory2();

   String getConnectionFactory2Name();

   ValueGenerationManager getValueGenerationManager();

   ApiAdapter getApiAdapter();

   String getStoreManagerKey();

   String getQueryCacheKey();

   NucleusContext getNucleusContext();

   Date getDatastoreDate();

   boolean isJdbcStore();

   void printInformation(String var1, PrintStream var2) throws Exception;

   boolean useBackedSCOWrapperForMember(AbstractMemberMetaData var1, ExecutionContext var2);

   boolean managesClass(String var1);

   void manageClasses(ClassLoaderResolver var1, String... var2);

   void unmanageClass(ClassLoaderResolver var1, String var2, boolean var3);

   void unmanageAllClasses(ClassLoaderResolver var1);

   String manageClassForIdentity(Object var1, ClassLoaderResolver var2);

   Extent getExtent(ExecutionContext var1, Class var2, boolean var3);

   boolean supportsQueryLanguage(String var1);

   String getNativeQueryLanguage();

   boolean supportsValueStrategy(String var1);

   String getClassNameForObjectID(Object var1, ClassLoaderResolver var2, ExecutionContext var3);

   boolean isStrategyDatastoreAttributed(AbstractClassMetaData var1, int var2);

   Object getStrategyValue(ExecutionContext var1, AbstractClassMetaData var2, int var3);

   Collection getSubClassesForClass(String var1, boolean var2, ClassLoaderResolver var3);

   Object getProperty(String var1);

   boolean hasProperty(String var1);

   int getIntProperty(String var1);

   boolean getBooleanProperty(String var1);

   boolean getBooleanProperty(String var1, boolean var2);

   Boolean getBooleanObjectProperty(String var1);

   String getStringProperty(String var1);

   void transactionStarted(ExecutionContext var1);

   void transactionCommitted(ExecutionContext var1);

   void transactionRolledBack(ExecutionContext var1);

   String getDefaultObjectProviderClassName();

   boolean usesBackedSCOWrappers();
}
