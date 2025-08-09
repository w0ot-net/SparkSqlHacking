package org.datanucleus.store.federation;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.NucleusContextHelper;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.flush.FlushProcess;
import org.datanucleus.identity.DatastoreId;
import org.datanucleus.identity.SingleFieldId;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.state.ReferentialStateManagerImpl;
import org.datanucleus.store.Extent;
import org.datanucleus.store.NucleusConnection;
import org.datanucleus.store.NucleusSequence;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.StorePersistenceHandler;
import org.datanucleus.store.connection.ConnectionManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.query.QueryManager;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.store.schema.naming.NamingFactory;
import org.datanucleus.store.valuegenerator.ValueGenerationManager;
import org.datanucleus.util.NucleusLogger;

public class FederatedStoreManager implements StoreManager {
   public static final String PROPERTY_DATA_FEDERATION_DATASTORE_NAME = "DATA_FEDERATION_DATASTORE_NAME";
   StoreManager primaryStoreMgr;
   Map secondaryStoreMgrMap = null;
   final NucleusContext nucleusContext;
   protected StorePersistenceHandler persistenceHandler = null;
   private QueryManager queryMgr = null;

   public FederatedStoreManager(ClassLoaderResolver clr, NucleusContext nucleusContext) {
      this.nucleusContext = nucleusContext;
      Map<String, Object> datastoreProps = nucleusContext.getConfiguration().getDatastoreProperties();
      this.primaryStoreMgr = NucleusContextHelper.createStoreManagerForProperties(nucleusContext.getConfiguration().getPersistenceProperties(), datastoreProps, clr, nucleusContext);
      String transactionIsolation = nucleusContext.getConfiguration().getStringProperty("datanucleus.transactionIsolation");
      if (transactionIsolation != null) {
         String reqdIsolation = NucleusContextHelper.getTransactionIsolationForStoreManager(this.primaryStoreMgr, transactionIsolation);
         if (!transactionIsolation.equalsIgnoreCase(reqdIsolation)) {
            nucleusContext.getConfiguration().setProperty("datanucleus.transactionIsolation", reqdIsolation);
         }
      }

      Set<String> propNamesWithDatastore = nucleusContext.getConfiguration().getPropertyNamesWithPrefix("datanucleus.datastore.");
      if (propNamesWithDatastore != null) {
         this.secondaryStoreMgrMap = new HashMap();

         for(String datastorePropName : propNamesWithDatastore) {
            String datastoreName = datastorePropName.substring("datanucleus.datastore.".length());
            String filename = nucleusContext.getConfiguration().getStringProperty(datastorePropName);
            Configuration datastoreConf = new Configuration(nucleusContext);
            datastoreConf.setPropertiesUsingFile(filename);
            datastoreConf.setProperty("DATA_FEDERATION_DATASTORE_NAME", datastoreName);
            StoreManager storeMgr = NucleusContextHelper.createStoreManagerForProperties(datastoreConf.getPersistenceProperties(), datastoreConf.getDatastoreProperties(), clr, nucleusContext);
            this.secondaryStoreMgrMap.put(datastoreName, storeMgr);
         }
      }

      this.persistenceHandler = new FederatedPersistenceHandler(this);
   }

   public NucleusContext getNucleusContext() {
      return this.nucleusContext;
   }

   public MetaDataManager getMetaDataManager() {
      return this.nucleusContext.getMetaDataManager();
   }

   public FlushProcess getFlushProcess() {
      return this.primaryStoreMgr.getFlushProcess();
   }

   public void close() {
      this.primaryStoreMgr.close();
      this.primaryStoreMgr = null;
      if (this.secondaryStoreMgrMap != null) {
         for(String name : this.secondaryStoreMgrMap.keySet()) {
            StoreManager secStoreMgr = (StoreManager)this.secondaryStoreMgrMap.get(name);
            secStoreMgr.close();
         }

         this.secondaryStoreMgrMap.clear();
         this.secondaryStoreMgrMap = null;
      }

      this.persistenceHandler.close();
      if (this.queryMgr != null) {
         this.queryMgr.close();
         this.queryMgr = null;
      }

   }

   public StoreManager getStoreManagerForClass(AbstractClassMetaData cmd) {
      if (cmd.hasExtension("datastore")) {
         String datastoreName = cmd.getValueForExtension("datastore");
         if (this.secondaryStoreMgrMap != null && this.secondaryStoreMgrMap.containsKey(datastoreName)) {
            return (StoreManager)this.secondaryStoreMgrMap.get(datastoreName);
         } else {
            throw new NucleusUserException("Class " + cmd.getFullClassName() + " specified to persist to datastore " + datastoreName + " yet not defined");
         }
      } else {
         return this.primaryStoreMgr;
      }
   }

   public StoreManager getStoreManagerForClass(String className, ClassLoaderResolver clr) {
      AbstractClassMetaData cmd = this.nucleusContext.getMetaDataManager().getMetaDataForClass(className, clr);
      return this.getStoreManagerForClass(cmd);
   }

   public void manageClasses(ClassLoaderResolver clr, String... classNames) {
      if (classNames != null) {
         for(int i = 0; i < classNames.length; ++i) {
            this.getStoreManagerForClass(classNames[i], clr).manageClasses(clr, classNames[i]);
         }
      }

   }

   public NamingFactory getNamingFactory() {
      return this.primaryStoreMgr.getNamingFactory();
   }

   public ApiAdapter getApiAdapter() {
      return this.nucleusContext.getApiAdapter();
   }

   public String getClassNameForObjectID(Object id, ClassLoaderResolver clr, ExecutionContext ec) {
      return this.primaryStoreMgr.getClassNameForObjectID(id, clr, ec);
   }

   public Date getDatastoreDate() {
      return this.primaryStoreMgr.getDatastoreDate();
   }

   public Extent getExtent(ExecutionContext ec, Class c, boolean subclasses) {
      return this.getStoreManagerForClass(c.getName(), ec.getClassLoaderResolver()).getExtent(ec, c, subclasses);
   }

   public boolean isJdbcStore() {
      return this.primaryStoreMgr.isJdbcStore();
   }

   public NucleusConnection getNucleusConnection(ExecutionContext ec) {
      return this.primaryStoreMgr.getNucleusConnection(ec);
   }

   public NucleusSequence getNucleusSequence(ExecutionContext ec, SequenceMetaData seqmd) {
      return this.primaryStoreMgr.getNucleusSequence(ec, seqmd);
   }

   public StoreSchemaHandler getSchemaHandler() {
      return this.primaryStoreMgr.getSchemaHandler();
   }

   public StoreData getStoreDataForClass(String className) {
      return this.primaryStoreMgr.getStoreDataForClass(className);
   }

   public StorePersistenceHandler getPersistenceHandler() {
      return this.persistenceHandler;
   }

   public QueryManager getQueryManager() {
      if (this.queryMgr == null) {
         this.queryMgr = new FederatedQueryManagerImpl(this.nucleusContext, this);
      }

      return this.queryMgr;
   }

   public ValueGenerationManager getValueGenerationManager() {
      return this.primaryStoreMgr.getValueGenerationManager();
   }

   public String getStoreManagerKey() {
      return this.primaryStoreMgr.getStoreManagerKey();
   }

   public String getQueryCacheKey() {
      return this.primaryStoreMgr.getQueryCacheKey();
   }

   public Object getStrategyValue(ExecutionContext ec, AbstractClassMetaData cmd, int absoluteFieldNumber) {
      return this.getStoreManagerForClass(cmd).getStrategyValue(ec, cmd, absoluteFieldNumber);
   }

   public Collection getSubClassesForClass(String className, boolean includeDescendents, ClassLoaderResolver clr) {
      return this.getStoreManagerForClass(className, clr).getSubClassesForClass(className, includeDescendents, clr);
   }

   public boolean isStrategyDatastoreAttributed(AbstractClassMetaData cmd, int absFieldNumber) {
      return this.getStoreManagerForClass(cmd).isStrategyDatastoreAttributed(cmd, absFieldNumber);
   }

   public String manageClassForIdentity(Object id, ClassLoaderResolver clr) {
      if (id instanceof SingleFieldId) {
         return this.getStoreManagerForClass(((SingleFieldId)id).getTargetClassName(), clr).manageClassForIdentity(id, clr);
      } else if (id instanceof DatastoreId) {
         return this.getStoreManagerForClass(((DatastoreId)id).getTargetClassName(), clr).manageClassForIdentity(id, clr);
      } else {
         NucleusLogger.PERSISTENCE.debug(">> TODO Need to allocate manageClassForIdentity(" + id + ") to correct store manager");
         return this.primaryStoreMgr.manageClassForIdentity(id, clr);
      }
   }

   public boolean managesClass(String className) {
      return this.primaryStoreMgr.managesClass(className);
   }

   public void printInformation(String category, PrintStream ps) throws Exception {
      this.primaryStoreMgr.printInformation(category, ps);
   }

   public void unmanageAllClasses(ClassLoaderResolver clr) {
      this.primaryStoreMgr.unmanageAllClasses(clr);
      if (this.secondaryStoreMgrMap != null) {
         for(StoreManager storeMgr : this.secondaryStoreMgrMap.values()) {
            storeMgr.unmanageAllClasses(clr);
         }
      }

   }

   public void unmanageClass(ClassLoaderResolver clr, String className, boolean removeFromDatastore) {
      this.primaryStoreMgr.unmanageClass(clr, className, removeFromDatastore);
      if (this.secondaryStoreMgrMap != null) {
         for(StoreManager storeMgr : this.secondaryStoreMgrMap.values()) {
            storeMgr.unmanageClass(clr, className, removeFromDatastore);
         }
      }

   }

   public boolean supportsQueryLanguage(String language) {
      return this.primaryStoreMgr.supportsQueryLanguage(language);
   }

   public String getNativeQueryLanguage() {
      return this.primaryStoreMgr.getNativeQueryLanguage();
   }

   public boolean supportsValueStrategy(String language) {
      return this.primaryStoreMgr.supportsValueStrategy(language);
   }

   public Collection getSupportedOptions() {
      return this.primaryStoreMgr.getSupportedOptions();
   }

   public ConnectionManager getConnectionManager() {
      return this.primaryStoreMgr.getConnectionManager();
   }

   public ManagedConnection getConnection(ExecutionContext ec) {
      return this.primaryStoreMgr.getConnection(ec);
   }

   public ManagedConnection getConnection(ExecutionContext ec, Map options) {
      return this.primaryStoreMgr.getConnection(ec, options);
   }

   public ManagedConnection getConnection(int isolation_level) {
      return this.primaryStoreMgr.getConnection(isolation_level);
   }

   public String getConnectionDriverName() {
      return this.primaryStoreMgr.getConnectionDriverName();
   }

   public String getConnectionURL() {
      return this.primaryStoreMgr.getConnectionURL();
   }

   public String getConnectionUserName() {
      return this.primaryStoreMgr.getConnectionUserName();
   }

   public String getConnectionPassword() {
      return this.primaryStoreMgr.getConnectionPassword();
   }

   public Object getConnectionFactory() {
      return this.primaryStoreMgr.getConnectionFactory();
   }

   public Object getConnectionFactory2() {
      return this.primaryStoreMgr.getConnectionFactory2();
   }

   public String getConnectionFactory2Name() {
      return this.primaryStoreMgr.getConnectionFactory2Name();
   }

   public String getConnectionFactoryName() {
      return this.primaryStoreMgr.getConnectionFactoryName();
   }

   public Object getProperty(String name) {
      return this.primaryStoreMgr.getProperty(name);
   }

   public boolean hasProperty(String name) {
      return this.primaryStoreMgr.hasProperty(name);
   }

   public int getIntProperty(String name) {
      return this.primaryStoreMgr.getIntProperty(name);
   }

   public boolean getBooleanProperty(String name) {
      return this.primaryStoreMgr.getBooleanProperty(name);
   }

   public boolean getBooleanProperty(String name, boolean resultIfNotSet) {
      return this.primaryStoreMgr.getBooleanProperty(name, resultIfNotSet);
   }

   public Boolean getBooleanObjectProperty(String name) {
      return this.primaryStoreMgr.getBooleanObjectProperty(name);
   }

   public String getStringProperty(String name) {
      return this.primaryStoreMgr.getStringProperty(name);
   }

   public void transactionStarted(ExecutionContext ec) {
      this.primaryStoreMgr.transactionStarted(ec);
   }

   public void transactionCommitted(ExecutionContext ec) {
      this.primaryStoreMgr.transactionCommitted(ec);
   }

   public void transactionRolledBack(ExecutionContext ec) {
      this.primaryStoreMgr.transactionRolledBack(ec);
   }

   public boolean useBackedSCOWrapperForMember(AbstractMemberMetaData mmd, ExecutionContext ec) {
      return this.primaryStoreMgr.useBackedSCOWrapperForMember(mmd, ec);
   }

   public boolean usesBackedSCOWrappers() {
      return this.primaryStoreMgr.usesBackedSCOWrappers();
   }

   public String getDefaultObjectProviderClassName() {
      return ReferentialStateManagerImpl.class.getName();
   }
}
