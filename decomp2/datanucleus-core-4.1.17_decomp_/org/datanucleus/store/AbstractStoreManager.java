package org.datanucleus.store;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.Transaction;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.flush.FlushNonReferential;
import org.datanucleus.flush.FlushProcess;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.identity.SCOID;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.IdentityMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.metadata.TableGeneratorMetaData;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.properties.PropertyStore;
import org.datanucleus.state.StateManagerImpl;
import org.datanucleus.store.autostart.AutoStartMechanism;
import org.datanucleus.store.connection.ConnectionFactory;
import org.datanucleus.store.connection.ConnectionManager;
import org.datanucleus.store.connection.ConnectionManagerImpl;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.encryption.ConnectionEncryptionProvider;
import org.datanucleus.store.exceptions.NoExtentException;
import org.datanucleus.store.query.QueryManager;
import org.datanucleus.store.query.QueryManagerImpl;
import org.datanucleus.store.schema.DefaultStoreSchemaHandler;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.store.schema.naming.NamingCase;
import org.datanucleus.store.schema.naming.NamingFactory;
import org.datanucleus.store.valuegenerator.AbstractDatastoreGenerator;
import org.datanucleus.store.valuegenerator.ValueGenerationConnectionProvider;
import org.datanucleus.store.valuegenerator.ValueGenerationManager;
import org.datanucleus.store.valuegenerator.ValueGenerator;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.TypeConversionHelper;

public abstract class AbstractStoreManager extends PropertyStore implements StoreManager {
   protected final String storeManagerKey;
   protected final PersistenceNucleusContext nucleusContext;
   protected ValueGenerationManager valueGenerationMgr;
   protected StoreDataManager storeDataMgr = new StoreDataManager();
   protected StorePersistenceHandler persistenceHandler = null;
   protected FlushProcess flushProcess = null;
   protected QueryManager queryMgr = null;
   protected StoreSchemaHandler schemaHandler = null;
   protected NamingFactory namingFactory = null;
   protected ConnectionManager connectionMgr;
   protected String primaryConnectionFactoryName;
   protected String secondaryConnectionFactoryName;

   protected AbstractStoreManager(String key, ClassLoaderResolver clr, PersistenceNucleusContext nucleusContext, Map props) {
      this.storeManagerKey = key;
      this.nucleusContext = nucleusContext;
      if (props != null) {
         for(Map.Entry entry : props.entrySet()) {
            this.setPropertyInternal((String)entry.getKey(), entry.getValue());
         }
      }

      this.registerConnectionMgr();
      this.registerConnectionFactory();
      nucleusContext.addExecutionContextListener(new ExecutionContext.LifecycleListener() {
         public void preClose(ExecutionContext ec) {
            ConnectionFactory connFactory = AbstractStoreManager.this.connectionMgr.lookupConnectionFactory(AbstractStoreManager.this.primaryConnectionFactoryName);
            AbstractStoreManager.this.connectionMgr.closeAllConnections(connFactory, ec);
            connFactory = AbstractStoreManager.this.connectionMgr.lookupConnectionFactory(AbstractStoreManager.this.secondaryConnectionFactoryName);
            AbstractStoreManager.this.connectionMgr.closeAllConnections(connFactory, ec);
         }
      });
   }

   protected void registerConnectionMgr() {
      this.connectionMgr = new ConnectionManagerImpl(this.nucleusContext);
   }

   protected void registerConnectionFactory() {
      String datastoreName = this.getStringProperty("DATA_FEDERATION_DATASTORE_NAME");
      ConfigurationElement cfElem = this.nucleusContext.getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_connectionfactory", new String[]{"datastore", "transactional"}, new String[]{this.storeManagerKey, "true"});
      if (cfElem != null) {
         this.primaryConnectionFactoryName = cfElem.getAttribute("name");
         if (datastoreName != null) {
            this.primaryConnectionFactoryName = this.primaryConnectionFactoryName + "-" + datastoreName;
         }

         try {
            ConnectionFactory cf = (ConnectionFactory)this.nucleusContext.getPluginManager().createExecutableExtension("org.datanucleus.store_connectionfactory", new String[]{"datastore", "transactional"}, new String[]{this.storeManagerKey, "true"}, "class-name", new Class[]{StoreManager.class, String.class}, new Object[]{this, "tx"});
            this.connectionMgr.registerConnectionFactory(this.primaryConnectionFactoryName, cf);
            if (NucleusLogger.CONNECTION.isDebugEnabled()) {
               NucleusLogger.CONNECTION.debug(Localiser.msg("032018", this.primaryConnectionFactoryName));
            }
         } catch (Exception e) {
            throw (new NucleusException("Error creating transactional connection factory", e)).setFatal();
         }

         cfElem = this.nucleusContext.getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_connectionfactory", new String[]{"datastore", "transactional"}, new String[]{this.storeManagerKey, "false"});
         if (cfElem != null) {
            this.secondaryConnectionFactoryName = cfElem.getAttribute("name");
            if (datastoreName != null) {
               this.secondaryConnectionFactoryName = this.secondaryConnectionFactoryName + "-" + datastoreName;
            }

            try {
               ConnectionFactory cf = (ConnectionFactory)this.nucleusContext.getPluginManager().createExecutableExtension("org.datanucleus.store_connectionfactory", new String[]{"datastore", "transactional"}, new String[]{this.storeManagerKey, "false"}, "class-name", new Class[]{StoreManager.class, String.class}, new Object[]{this, "nontx"});
               if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                  NucleusLogger.CONNECTION.debug(Localiser.msg("032019", this.secondaryConnectionFactoryName));
               }

               this.connectionMgr.registerConnectionFactory(this.secondaryConnectionFactoryName, cf);
            } catch (Exception e) {
               throw (new NucleusException("Error creating nontransactional connection factory", e)).setFatal();
            }
         }

      } else {
         throw new NucleusException("Error creating transactional connection factory. No connection factory plugin defined");
      }
   }

   public synchronized void close() {
      if (this.primaryConnectionFactoryName != null) {
         ConnectionFactory cf = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
         if (cf != null) {
            cf.close();
         }
      }

      if (this.secondaryConnectionFactoryName != null) {
         ConnectionFactory cf = this.connectionMgr.lookupConnectionFactory(this.secondaryConnectionFactoryName);
         if (cf != null) {
            cf.close();
         }
      }

      if (this.valueGenerationMgr != null) {
         this.valueGenerationMgr.clear();
      }

      this.storeDataMgr.clear();
      if (this.persistenceHandler != null) {
         this.persistenceHandler.close();
         this.persistenceHandler = null;
      }

      if (this.queryMgr != null) {
         this.queryMgr.close();
         this.queryMgr = null;
      }

   }

   public ConnectionManager getConnectionManager() {
      return this.connectionMgr;
   }

   public ManagedConnection getConnection(ExecutionContext ec) {
      return this.getConnection(ec, (Map)null);
   }

   public ManagedConnection getConnection(ExecutionContext ec, Map options) {
      ConnectionFactory connFactory;
      if (ec.getTransaction().isActive()) {
         connFactory = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
      } else {
         Boolean singleConnection = this.getBooleanProperty("datanucleus.connection.singleConnectionPerExecutionContext");
         if (singleConnection) {
            connFactory = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
         } else if (this.secondaryConnectionFactoryName != null) {
            connFactory = this.connectionMgr.lookupConnectionFactory(this.secondaryConnectionFactoryName);
         } else {
            connFactory = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
         }
      }

      return connFactory.getConnection(ec, ec.getTransaction(), options);
   }

   public ManagedConnection getConnection(int isolation_level) {
      ConnectionFactory connFactory = null;
      if (this.secondaryConnectionFactoryName != null) {
         connFactory = this.connectionMgr.lookupConnectionFactory(this.secondaryConnectionFactoryName);
      } else {
         connFactory = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
      }

      Map options = null;
      if (isolation_level >= 0) {
         options = new HashMap();
         options.put("transaction.isolation", isolation_level);
      }

      return connFactory.getConnection((ExecutionContext)null, (Transaction)null, options);
   }

   public String getConnectionDriverName() {
      return this.getStringProperty("datanucleus.ConnectionDriverName");
   }

   public String getConnectionURL() {
      return this.getStringProperty("datanucleus.ConnectionURL");
   }

   public String getConnectionUserName() {
      return this.getStringProperty("datanucleus.ConnectionUserName");
   }

   public String getConnectionPassword() {
      String password = this.getStringProperty("datanucleus.ConnectionPassword");
      if (password != null) {
         String decrypterName = this.getStringProperty("datanucleus.ConnectionPasswordDecrypter");
         if (decrypterName != null) {
            ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);

            try {
               Class decrypterCls = clr.classForName(decrypterName);
               ConnectionEncryptionProvider decrypter = (ConnectionEncryptionProvider)decrypterCls.newInstance();
               password = decrypter.decrypt(password);
            } catch (Exception e) {
               NucleusLogger.DATASTORE.warn("Error invoking decrypter class " + decrypterName, e);
            }
         }
      }

      return password;
   }

   public Object getConnectionFactory() {
      return this.getProperty("datanucleus.ConnectionFactory");
   }

   public String getConnectionFactoryName() {
      return this.getStringProperty("datanucleus.ConnectionFactoryName");
   }

   public Object getConnectionFactory2() {
      return this.getProperty("datanucleus.ConnectionFactory2");
   }

   public String getConnectionFactory2Name() {
      return this.getStringProperty("datanucleus.ConnectionFactory2Name");
   }

   public boolean isJdbcStore() {
      return false;
   }

   public StorePersistenceHandler getPersistenceHandler() {
      return this.persistenceHandler;
   }

   public FlushProcess getFlushProcess() {
      if (this.flushProcess == null) {
         this.flushProcess = new FlushNonReferential();
      }

      return this.flushProcess;
   }

   public QueryManager getQueryManager() {
      if (this.queryMgr == null) {
         this.queryMgr = new QueryManagerImpl(this.nucleusContext, this);
      }

      return this.queryMgr;
   }

   public StoreSchemaHandler getSchemaHandler() {
      if (this.schemaHandler == null) {
         this.schemaHandler = new DefaultStoreSchemaHandler(this);
      }

      return this.schemaHandler;
   }

   public NamingFactory getNamingFactory() {
      if (this.namingFactory == null) {
         String namingFactoryName = this.getStringProperty("datanucleus.identifier.namingFactory");
         String namingFactoryClassName = this.nucleusContext.getPluginManager().getAttributeValueForExtension("org.datanucleus.identifier_namingfactory", "name", namingFactoryName, "class-name");
         if (namingFactoryClassName == null) {
            throw new NucleusUserException("Error in specified NamingFactory " + namingFactoryName + " not found");
         }

         try {
            Class[] argTypes = new Class[]{ClassConstants.NUCLEUS_CONTEXT};
            Object[] args = new Object[]{this.nucleusContext};
            this.namingFactory = (NamingFactory)this.nucleusContext.getPluginManager().createExecutableExtension("org.datanucleus.identifier_namingfactory", "name", namingFactoryName, "class-name", argTypes, args);
         } catch (Throwable thr) {
            NucleusLogger.GENERAL.debug(">> Exception creating NamingFactory", thr);
         }

         String identifierCase = this.getStringProperty("datanucleus.identifier.case");
         if (identifierCase != null) {
            if (identifierCase.equalsIgnoreCase("lowercase")) {
               this.namingFactory.setNamingCase(NamingCase.LOWER_CASE);
            } else if (identifierCase.equalsIgnoreCase("UPPERCASE")) {
               this.namingFactory.setNamingCase(NamingCase.UPPER_CASE);
            } else {
               this.namingFactory.setNamingCase(NamingCase.MIXED_CASE);
            }
         }
      }

      return this.namingFactory;
   }

   public NucleusSequence getNucleusSequence(ExecutionContext ec, SequenceMetaData seqmd) {
      return new NucleusSequenceImpl(ec, this, seqmd);
   }

   public NucleusConnection getNucleusConnection(ExecutionContext ec) {
      ConnectionFactory cf = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
      final boolean enlisted;
      if (!ec.getTransaction().isActive()) {
         enlisted = false;
      } else {
         enlisted = true;
      }

      final ManagedConnection mc = cf.getConnection(enlisted ? ec : null, enlisted ? ec.getTransaction() : null, (Map)null);
      mc.lock();
      Runnable closeRunnable = new Runnable() {
         public void run() {
            mc.unlock();
            if (!enlisted) {
               mc.close();
            }

         }
      };
      return new NucleusConnectionImpl(mc.getConnection(), closeRunnable);
   }

   public ValueGenerationManager getValueGenerationManager() {
      if (this.valueGenerationMgr == null) {
         this.valueGenerationMgr = new ValueGenerationManager();
      }

      return this.valueGenerationMgr;
   }

   public ApiAdapter getApiAdapter() {
      return this.nucleusContext.getApiAdapter();
   }

   public String getStoreManagerKey() {
      return this.storeManagerKey;
   }

   public String getQueryCacheKey() {
      return this.getStoreManagerKey();
   }

   public PersistenceNucleusContext getNucleusContext() {
      return this.nucleusContext;
   }

   public MetaDataManager getMetaDataManager() {
      return this.nucleusContext.getMetaDataManager();
   }

   public Date getDatastoreDate() {
      return new Date();
   }

   public StoreData getStoreDataForClass(String className) {
      return this.storeDataMgr.get(className);
   }

   protected void registerStoreData(StoreData data) {
      this.storeDataMgr.registerStoreData(data);
      if (this.nucleusContext.getAutoStartMechanism() != null) {
         this.nucleusContext.getAutoStartMechanism().addClass(data);
      }

   }

   protected void deregisterAllStoreData() {
      this.storeDataMgr.clear();
      AutoStartMechanism starter = this.nucleusContext.getAutoStartMechanism();
      if (starter != null) {
         try {
            if (!starter.isOpen()) {
               starter.open();
            }

            starter.deleteAllClasses();
         } finally {
            if (starter.isOpen()) {
               starter.close();
            }

         }
      }

   }

   protected void logConfiguration() {
      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         NucleusLogger.DATASTORE.debug("======================= Datastore =========================");
         NucleusLogger.DATASTORE.debug("StoreManager : \"" + this.storeManagerKey + "\" (" + this.getClass().getName() + ")");
         NucleusLogger.DATASTORE.debug("Datastore : " + (this.getBooleanProperty("datanucleus.readOnlyDatastore") ? "read-only" : "read-write") + (this.getBooleanProperty("datanucleus.SerializeRead") ? ", useLocking" : ""));
         StringBuilder autoCreateOptions = null;
         if (this.getSchemaHandler().isAutoCreateTables() || this.getSchemaHandler().isAutoCreateColumns() || this.getSchemaHandler().isAutoCreateConstraints()) {
            autoCreateOptions = new StringBuilder();
            boolean first = true;
            if (this.getSchemaHandler().isAutoCreateTables()) {
               if (!first) {
                  autoCreateOptions.append(",");
               }

               autoCreateOptions.append("Tables");
               first = false;
            }

            if (this.getSchemaHandler().isAutoCreateColumns()) {
               if (!first) {
                  autoCreateOptions.append(",");
               }

               autoCreateOptions.append("Columns");
               first = false;
            }

            if (this.getSchemaHandler().isAutoCreateConstraints()) {
               if (!first) {
                  autoCreateOptions.append(",");
               }

               autoCreateOptions.append("Constraints");
               first = false;
            }
         }

         StringBuilder validateOptions = null;
         if (this.getSchemaHandler().isValidateTables() || this.getSchemaHandler().isValidateColumns() || this.getSchemaHandler().isValidateConstraints()) {
            validateOptions = new StringBuilder();
            boolean first = true;
            if (this.getSchemaHandler().isValidateTables()) {
               validateOptions.append("Tables");
               first = false;
            }

            if (this.getSchemaHandler().isValidateColumns()) {
               if (!first) {
                  validateOptions.append(",");
               }

               validateOptions.append("Columns");
               first = false;
            }

            if (this.getSchemaHandler().isValidateConstraints()) {
               if (!first) {
                  validateOptions.append(",");
               }

               validateOptions.append("Constraints");
               first = false;
            }
         }

         NucleusLogger.DATASTORE.debug("Schema Control : AutoCreate(" + (autoCreateOptions != null ? autoCreateOptions.toString() : "None") + "), Validate(" + (validateOptions != null ? validateOptions.toString() : "None") + ")");
         String namingFactoryName = this.getStringProperty("datanucleus.identifier.namingFactory");
         String namingCase = this.getStringProperty("datanucleus.identifier.case");
         NucleusLogger.DATASTORE.debug("Schema : NamingFactory=" + namingFactoryName + " identifierCase=" + namingCase);
         String[] queryLanguages = this.nucleusContext.getPluginManager().getAttributeValuesForExtension("org.datanucleus.store_query_query", "datastore", this.storeManagerKey, "name");
         NucleusLogger.DATASTORE.debug("Query Languages : " + (queryLanguages != null ? StringUtils.objectArrayToString(queryLanguages) : "none"));
         NucleusLogger.DATASTORE.debug("Queries : Timeout=" + this.getIntProperty("datanucleus.datastoreReadTimeout"));
         NucleusLogger.DATASTORE.debug("===========================================================");
      }

   }

   public void printInformation(String category, PrintStream ps) throws Exception {
      if (category.equalsIgnoreCase("DATASTORE")) {
         ps.println(Localiser.msg("032020", this.storeManagerKey, this.getConnectionURL(), this.getBooleanProperty("datanucleus.readOnlyDatastore") ? "read-only" : "read-write"));
      }

   }

   public boolean managesClass(String className) {
      return this.storeDataMgr.managesClass(className);
   }

   public void manageClasses(ClassLoaderResolver clr, String... classNames) {
      if (classNames != null) {
         String[] filteredClassNames = this.getNucleusContext().getTypeManager().filterOutSupportedSecondClassNames(classNames);

         for(ClassMetaData cmd : this.getMetaDataManager().getReferencedClasses(filteredClassNames, clr)) {
            if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE && !this.storeDataMgr.managesClass(cmd.getFullClassName())) {
               this.registerStoreData(this.newStoreData(cmd, clr));
            }
         }

      }
   }

   protected StoreData newStoreData(ClassMetaData cmd, ClassLoaderResolver clr) {
      return new StoreData(cmd.getFullClassName(), cmd, 1, (String)null);
   }

   public void unmanageAllClasses(ClassLoaderResolver clr) {
   }

   public void unmanageClass(ClassLoaderResolver clr, String className, boolean removeFromDatastore) {
      AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, clr);
      if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         if (removeFromDatastore) {
         }

         this.storeDataMgr.deregisterClass(className);
      }

   }

   public String manageClassForIdentity(Object id, ClassLoaderResolver clr) {
      String className = null;
      if (IdentityUtils.isDatastoreIdentity(id)) {
         className = IdentityUtils.getTargetClassNameForIdentitySimple(id);
         AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, clr);
         if (cmd.getIdentityType() != IdentityType.DATASTORE) {
            throw new NucleusUserException(Localiser.msg("038001", id, cmd.getFullClassName()));
         }
      } else {
         if (!IdentityUtils.isSingleFieldIdentity(id)) {
            throw new NucleusException("StoreManager.manageClassForIdentity called for id=" + id + " yet should only be called for datastore-identity/SingleFieldIdentity");
         }

         className = IdentityUtils.getTargetClassNameForIdentitySimple(id);
         AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, clr);
         if (cmd.getIdentityType() != IdentityType.APPLICATION || !cmd.getObjectidClass().equals(id.getClass().getName())) {
            throw new NucleusUserException(Localiser.msg("038001", id, cmd.getFullClassName()));
         }
      }

      if (!this.managesClass(className)) {
         this.manageClasses(clr, className);
      }

      return className;
   }

   public Extent getExtent(ExecutionContext ec, Class c, boolean subclasses) {
      AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(c, ec.getClassLoaderResolver());
      if (!cmd.isRequiresExtent()) {
         throw new NoExtentException(c.getName());
      } else {
         if (!this.managesClass(c.getName())) {
            this.manageClasses(ec.getClassLoaderResolver(), c.getName());
         }

         return new DefaultCandidateExtent(ec, c, subclasses, cmd);
      }
   }

   public boolean supportsQueryLanguage(String language) {
      if (language == null) {
         return false;
      } else {
         String name = this.getNucleusContext().getPluginManager().getAttributeValueForExtension("org.datanucleus.store_query_query", new String[]{"name", "datastore"}, new String[]{language, this.storeManagerKey}, "name");
         return name != null;
      }
   }

   public String getNativeQueryLanguage() {
      return null;
   }

   public boolean supportsValueStrategy(String strategy) {
      ConfigurationElement elem = this.nucleusContext.getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "unique"}, new String[]{strategy, "true"});
      if (elem != null) {
         return true;
      } else {
         elem = this.nucleusContext.getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "datastore"}, new String[]{strategy, this.storeManagerKey});
         return elem != null;
      }
   }

   public String getClassNameForObjectID(Object id, ClassLoaderResolver clr, ExecutionContext ec) {
      if (id == null) {
         return null;
      } else if (id instanceof SCOID) {
         return ((SCOID)id).getSCOClass();
      } else if (!IdentityUtils.isDatastoreIdentity(id) && !IdentityUtils.isSingleFieldIdentity(id)) {
         Collection<AbstractClassMetaData> cmds = this.getMetaDataManager().getClassMetaDataWithApplicationId(id.getClass().getName());
         if (cmds != null) {
            Iterator<AbstractClassMetaData> iter = cmds.iterator();
            if (iter.hasNext()) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)iter.next();
               return cmd.getFullClassName();
            }
         }

         return null;
      } else {
         return IdentityUtils.getTargetClassNameForIdentitySimple(id);
      }
   }

   public boolean isStrategyDatastoreAttributed(AbstractClassMetaData cmd, int absFieldNumber) {
      if (absFieldNumber < 0) {
         if (cmd.isEmbeddedOnly()) {
            return false;
         }

         IdentityMetaData idmd = cmd.getBaseIdentityMetaData();
         if (idmd == null) {
            String strategy = this.getStrategyForNative(cmd, absFieldNumber);
            if (strategy.equalsIgnoreCase("identity")) {
               return true;
            }
         } else {
            IdentityStrategy idStrategy = idmd.getValueStrategy();
            if (idStrategy == IdentityStrategy.IDENTITY) {
               return true;
            }

            if (idStrategy == IdentityStrategy.NATIVE) {
               String strategy = this.getStrategyForNative(cmd, absFieldNumber);
               if (strategy.equalsIgnoreCase("identity")) {
                  return true;
               }
            }
         }
      } else {
         AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(absFieldNumber);
         if (mmd.getValueStrategy() == null) {
            return false;
         }

         if (mmd.getValueStrategy() == IdentityStrategy.IDENTITY) {
            return true;
         }

         if (mmd.getValueStrategy() == IdentityStrategy.NATIVE) {
            String strategy = this.getStrategyForNative(cmd, absFieldNumber);
            if (strategy.equalsIgnoreCase("identity")) {
               return true;
            }
         }
      }

      return false;
   }

   public Object getStrategyValue(ExecutionContext ec, AbstractClassMetaData cmd, int absoluteFieldNumber) {
      AbstractMemberMetaData mmd = null;
      String fieldName = null;
      IdentityStrategy strategy = null;
      String sequence = null;
      String valueGeneratorName = null;
      TableGeneratorMetaData tableGeneratorMetaData = null;
      SequenceMetaData sequenceMetaData = null;
      if (absoluteFieldNumber >= 0) {
         mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(absoluteFieldNumber);
         fieldName = mmd.getFullFieldName();
         strategy = mmd.getValueStrategy();
         sequence = mmd.getSequence();
         valueGeneratorName = mmd.getValueGeneratorName();
      } else {
         fieldName = cmd.getFullClassName() + " (datastore id)";
         strategy = cmd.getIdentityMetaData().getValueStrategy();
         sequence = cmd.getIdentityMetaData().getSequence();
         valueGeneratorName = cmd.getIdentityMetaData().getValueGeneratorName();
      }

      String strategyName = strategy.toString();
      if (strategy.equals(IdentityStrategy.CUSTOM)) {
         strategyName = strategy.getCustomName();
      } else if (strategy.equals(IdentityStrategy.NATIVE)) {
         strategyName = this.getStrategyForNative(cmd, absoluteFieldNumber);
         strategy = IdentityStrategy.getIdentityStrategy(strategyName);
      }

      if (valueGeneratorName != null) {
         if (strategy == IdentityStrategy.INCREMENT) {
            tableGeneratorMetaData = this.getMetaDataManager().getMetaDataForTableGenerator(ec.getClassLoaderResolver(), valueGeneratorName);
            if (tableGeneratorMetaData == null) {
               throw new NucleusUserException(Localiser.msg("038005", fieldName, valueGeneratorName));
            }
         } else if (strategy == IdentityStrategy.SEQUENCE) {
            sequenceMetaData = this.getMetaDataManager().getMetaDataForSequence(ec.getClassLoaderResolver(), valueGeneratorName);
            if (sequenceMetaData == null) {
               throw new NucleusUserException(Localiser.msg("038006", fieldName, valueGeneratorName));
            }
         }
      } else if (strategy == IdentityStrategy.SEQUENCE && sequence != null) {
         sequenceMetaData = this.getMetaDataManager().getMetaDataForSequence(ec.getClassLoaderResolver(), sequence);
         if (sequenceMetaData == null) {
            NucleusLogger.VALUEGENERATION.warn("Field " + fieldName + " has been specified to use sequence " + sequence + " but there is no <sequence> specified in the MetaData. Falling back to use a sequence in the datastore with this name directly.");
         }
      }

      String generatorName = null;
      String generatorNameKeyInManager = null;
      ConfigurationElement elem = this.nucleusContext.getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "unique"}, new String[]{strategyName, "true"});
      if (elem != null) {
         generatorName = elem.getAttribute("name");
         generatorNameKeyInManager = generatorName;
      } else {
         elem = this.nucleusContext.getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "datastore"}, new String[]{strategyName, this.storeManagerKey});
         if (elem != null) {
            generatorName = elem.getAttribute("name");
         }
      }

      if (generatorNameKeyInManager == null) {
         if (absoluteFieldNumber >= 0) {
            generatorNameKeyInManager = mmd.getFullFieldName();
         } else {
            generatorNameKeyInManager = cmd.getBaseAbstractClassMetaData().getFullClassName();
         }
      }

      ValueGenerator generator = null;
      synchronized(this) {
         generator = this.getValueGenerationManager().getValueGenerator(generatorNameKeyInManager);
         if (generator == null) {
            if (generatorName == null) {
               throw new NucleusUserException(Localiser.msg("038004", strategy));
            }

            Properties props = this.getPropertiesForGenerator(cmd, absoluteFieldNumber, ec, sequenceMetaData, tableGeneratorMetaData);
            Class cls = null;
            if (elem != null) {
               cls = this.nucleusContext.getPluginManager().loadClass(elem.getExtension().getPlugin().getSymbolicName(), elem.getAttribute("class-name"));
            }

            if (cls == null) {
               throw new NucleusException("Cannot create Value Generator for strategy " + generatorName);
            }

            generator = this.getValueGenerationManager().createValueGenerator(generatorNameKeyInManager, cls, props, this, (ValueGenerationConnectionProvider)null);
         }
      }

      Object oid = this.getStrategyValueForGenerator(generator, ec);
      if (mmd != null) {
         try {
            Object convertedValue = TypeConversionHelper.convertTo(oid, mmd.getType());
            if (convertedValue == null) {
               throw (new NucleusException(Localiser.msg("038003", mmd.getFullFieldName(), oid))).setFatal();
            }

            oid = convertedValue;
         } catch (NumberFormatException var20) {
            throw new NucleusUserException("Value strategy created value=" + oid + " type=" + oid.getClass().getName() + " but field is of type " + mmd.getTypeName() + ". Use a different strategy or change the type of the field " + mmd.getFullFieldName());
         }
      }

      if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
         NucleusLogger.VALUEGENERATION.debug(Localiser.msg("038002", fieldName, strategy, generator.getClass().getName(), oid));
      }

      return oid;
   }

   public String getStrategyForNative(AbstractClassMetaData cmd, int absFieldNumber) {
      if (absFieldNumber >= 0) {
         AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(absFieldNumber);
         Class type = mmd.getType();
         if (String.class.isAssignableFrom(type)) {
            return "uuid-hex";
         } else if (type != Long.class && type != Integer.class && type != Short.class && type != Long.TYPE && type != Integer.TYPE && type != Short.TYPE) {
            throw new NucleusUserException("This datastore provider doesn't support native strategy for field of type " + type.getName());
         } else if (this.supportsValueStrategy("identity")) {
            return "identity";
         } else if (this.supportsValueStrategy("sequence") && mmd.getSequence() != null) {
            return "sequence";
         } else if (this.supportsValueStrategy("increment")) {
            return "increment";
         } else {
            throw new NucleusUserException("This datastore provider doesn't support numeric native strategy for member " + mmd.getFullFieldName());
         }
      } else {
         IdentityMetaData idmd = cmd.getBaseIdentityMetaData();
         if (idmd != null && idmd.getColumnMetaData() != null && MetaDataUtils.isJdbcTypeString(idmd.getColumnMetaData().getJdbcType())) {
            return "uuid-hex";
         } else if (this.supportsValueStrategy("identity")) {
            return "identity";
         } else if (this.supportsValueStrategy("sequence") && idmd.getSequence() != null) {
            return "sequence";
         } else if (this.supportsValueStrategy("increment")) {
            return "increment";
         } else {
            throw new NucleusUserException("This datastore provider doesn't support numeric native strategy for class " + cmd.getFullClassName());
         }
      }
   }

   protected Object getStrategyValueForGenerator(ValueGenerator generator, final ExecutionContext ec) {
      Object oid = null;
      synchronized(generator) {
         if (generator instanceof AbstractDatastoreGenerator) {
            ValueGenerationConnectionProvider connProvider = new ValueGenerationConnectionProvider() {
               ManagedConnection mconn;

               public ManagedConnection retrieveConnection() {
                  this.mconn = AbstractStoreManager.this.getConnection(ec);
                  return this.mconn;
               }

               public void releaseConnection() {
                  this.mconn.release();
                  this.mconn = null;
               }
            };
            ((AbstractDatastoreGenerator)generator).setConnectionProvider(connProvider);
         }

         oid = generator.next();
         return oid;
      }
   }

   protected Properties getPropertiesForGenerator(AbstractClassMetaData cmd, int absoluteFieldNumber, ExecutionContext ec, SequenceMetaData seqmd, TableGeneratorMetaData tablegenmd) {
      Properties properties = new Properties();
      AbstractMemberMetaData mmd = null;
      IdentityStrategy strategy = null;
      String sequence = null;
      ExtensionMetaData[] extensions = null;
      if (absoluteFieldNumber >= 0) {
         mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(absoluteFieldNumber);
         strategy = mmd.getValueStrategy();
         sequence = mmd.getSequence();
         extensions = mmd.getExtensions();
      } else {
         IdentityMetaData idmd = cmd.getBaseIdentityMetaData();
         strategy = idmd.getValueStrategy();
         sequence = idmd.getSequence();
         extensions = idmd.getExtensions();
      }

      properties.setProperty("class-name", cmd.getFullClassName());
      properties.put("root-class-name", cmd.getBaseAbstractClassMetaData().getFullClassName());
      if (mmd != null) {
         properties.setProperty("field-name", mmd.getFullFieldName());
      }

      if (sequence != null) {
         properties.setProperty("sequence-name", sequence);
      }

      if (extensions != null) {
         for(int i = 0; i < extensions.length; ++i) {
            properties.put(extensions[i].getKey(), extensions[i].getValue());
         }
      }

      if (strategy.equals(IdentityStrategy.NATIVE)) {
         String realStrategyName = this.getStrategyForNative(cmd, absoluteFieldNumber);
         strategy = IdentityStrategy.getIdentityStrategy(realStrategyName);
      }

      if (strategy == IdentityStrategy.INCREMENT && tablegenmd != null) {
         properties.put("key-initial-value", "" + tablegenmd.getInitialValue());
         properties.put("key-cache-size", "" + tablegenmd.getAllocationSize());
         if (tablegenmd.getTableName() != null) {
            properties.put("sequence-table-name", tablegenmd.getTableName());
         }

         if (tablegenmd.getCatalogName() != null) {
            properties.put("sequence-catalog-name", tablegenmd.getCatalogName());
         }

         if (tablegenmd.getSchemaName() != null) {
            properties.put("sequence-schema-name", tablegenmd.getSchemaName());
         }

         if (tablegenmd.getPKColumnName() != null) {
            properties.put("sequence-name-column-name", tablegenmd.getPKColumnName());
         }

         if (tablegenmd.getPKColumnName() != null) {
            properties.put("sequence-nextval-column-name", tablegenmd.getValueColumnName());
         }

         if (tablegenmd.getPKColumnValue() != null) {
            properties.put("sequence-name", tablegenmd.getPKColumnValue());
         }
      } else if (strategy == IdentityStrategy.INCREMENT && tablegenmd == null) {
         if (!properties.containsKey("key-cache-size")) {
            properties.put("key-cache-size", "" + this.getIntProperty("datanucleus.valuegeneration.increment.allocationSize"));
         }
      } else if (strategy == IdentityStrategy.SEQUENCE && seqmd != null && seqmd.getDatastoreSequence() != null) {
         if (seqmd.getInitialValue() >= 0) {
            properties.put("key-initial-value", "" + seqmd.getInitialValue());
         }

         if (seqmd.getAllocationSize() > 0) {
            properties.put("key-cache-size", "" + seqmd.getAllocationSize());
         } else {
            properties.put("key-cache-size", "" + this.getIntProperty("datanucleus.valuegeneration.sequence.allocationSize"));
         }

         properties.put("sequence-name", "" + seqmd.getDatastoreSequence());
         ExtensionMetaData[] seqExtensions = seqmd.getExtensions();
         if (seqExtensions != null) {
            for(int i = 0; i < seqExtensions.length; ++i) {
               properties.put(seqExtensions[i].getKey(), seqExtensions[i].getValue());
            }
         }
      }

      return properties;
   }

   public Collection getSubClassesForClass(String className, boolean includeDescendents, ClassLoaderResolver clr) {
      Collection<String> subclasses = new HashSet();
      String[] subclassNames = this.getMetaDataManager().getSubclassesForClass(className, includeDescendents);
      if (subclassNames != null) {
         for(int i = 0; i < subclassNames.length; ++i) {
            if (!this.storeDataMgr.managesClass(subclassNames[i])) {
               this.manageClasses(clr, subclassNames[i]);
            }

            subclasses.add(subclassNames[i]);
         }
      }

      return subclasses;
   }

   public Collection getSupportedOptions() {
      return Collections.EMPTY_SET;
   }

   public boolean hasProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? true : this.nucleusContext.getConfiguration().hasProperty(name);
   }

   public Object getProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getProperty(name) : this.nucleusContext.getConfiguration().getProperty(name);
   }

   public int getIntProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getIntProperty(name) : this.nucleusContext.getConfiguration().getIntProperty(name);
   }

   public String getStringProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getStringProperty(name) : this.nucleusContext.getConfiguration().getStringProperty(name);
   }

   public boolean getBooleanProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getBooleanProperty(name) : this.nucleusContext.getConfiguration().getBooleanProperty(name);
   }

   public boolean getBooleanProperty(String name, boolean resultIfNotSet) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getBooleanProperty(name, resultIfNotSet) : this.nucleusContext.getConfiguration().getBooleanProperty(name, resultIfNotSet);
   }

   public Boolean getBooleanObjectProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getBooleanObjectProperty(name) : this.nucleusContext.getConfiguration().getBooleanObjectProperty(name);
   }

   public void transactionStarted(ExecutionContext ec) {
   }

   public void transactionCommitted(ExecutionContext ec) {
   }

   public void transactionRolledBack(ExecutionContext ec) {
   }

   public boolean usesBackedSCOWrappers() {
      return false;
   }

   public boolean useBackedSCOWrapperForMember(AbstractMemberMetaData mmd, ExecutionContext ec) {
      return this.usesBackedSCOWrappers();
   }

   public String getDefaultObjectProviderClassName() {
      return StateManagerImpl.class.getName();
   }
}
