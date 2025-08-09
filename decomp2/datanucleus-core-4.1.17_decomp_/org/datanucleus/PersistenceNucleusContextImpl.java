package org.datanucleus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.datanucleus.cache.Level2Cache;
import org.datanucleus.cache.NullLevel2Cache;
import org.datanucleus.enhancer.ImplementationCreatorImpl;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.DatastoreUniqueLongId;
import org.datanucleus.identity.IdentityManager;
import org.datanucleus.identity.IdentityManagerImpl;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.identity.SCOID;
import org.datanucleus.management.FactoryStatistics;
import org.datanucleus.management.jmx.ManagementManager;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.MetaDataListener;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.QueryLanguage;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.metadata.TransactionType;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.properties.CorePropertyValidator;
import org.datanucleus.properties.StringPropertyValidator;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.state.ObjectProviderFactory;
import org.datanucleus.state.ObjectProviderFactoryImpl;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.autostart.AutoStartMechanism;
import org.datanucleus.store.exceptions.DatastoreInitialisationException;
import org.datanucleus.store.federation.FederatedStoreManager;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.schema.SchemaAwareStoreManager;
import org.datanucleus.store.schema.SchemaScriptAwareStoreManager;
import org.datanucleus.store.schema.SchemaTool;
import org.datanucleus.transaction.NucleusTransactionException;
import org.datanucleus.transaction.TransactionManager;
import org.datanucleus.transaction.jta.JTASyncRegistry;
import org.datanucleus.transaction.jta.JTASyncRegistryUnavailableException;
import org.datanucleus.transaction.jta.TransactionManagerFinder;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.validation.BeanValidatorHandler;

public class PersistenceNucleusContextImpl extends AbstractNucleusContext implements Serializable, PersistenceNucleusContext {
   private static final long serialVersionUID = 7166558862250068749L;
   private transient StoreManager storeMgr;
   private boolean federated;
   private transient AutoStartMechanism starter;
   private boolean jca;
   private Level2Cache cache;
   private transient TransactionManager txManager;
   private transient javax.transaction.TransactionManager jtaTxManager;
   private transient JTASyncRegistry jtaSyncRegistry;
   private transient ManagementManager jmxManager;
   private transient FactoryStatistics statistics;
   private IdentityManager identityManager;
   private ImplementationCreator implCreator;
   private List executionContextListeners;
   private transient FetchGroupManager fetchGrpMgr;
   private transient Object validatorFactory;
   private transient boolean validatorFactoryInit;
   private ExecutionContextPool ecPool;
   private ObjectProviderFactory opFactory;

   public PersistenceNucleusContextImpl(String apiName, Map startupProps) {
      this(apiName, startupProps, (PluginManager)null);
   }

   public PersistenceNucleusContextImpl(String apiName, Map startupProps, PluginManager pluginMgr) {
      super(apiName, startupProps, pluginMgr);
      this.storeMgr = null;
      this.federated = false;
      this.starter = null;
      this.jca = false;
      this.txManager = null;
      this.jtaTxManager = null;
      this.jtaSyncRegistry = null;
      this.jmxManager = null;
      this.statistics = null;
      this.executionContextListeners = new ArrayList();
      this.validatorFactory = null;
      this.validatorFactoryInit = false;
      this.ecPool = null;
      this.opFactory = null;
   }

   public void applyDefaultProperties(Configuration conf) {
      super.applyDefaultProperties(conf);
      conf.addDefaultBooleanProperty("datanucleus.IgnoreCache", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.Optimistic", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.Multithreaded", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.RetainValues", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.RestoreValues", (String)null, false, false, true);
      conf.addDefaultProperty("datanucleus.jmxType", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.enableStatistics", (String)null, false, false, false);
      conf.addDefaultProperty("datanucleus.Name", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.PersistenceUnitName", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.persistenceXmlFilename", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.ServerTimeZoneID", (String)null, (String)null, CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.propertiesFile", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.persistenceUnitLoadClasses", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.executionContext.reaperThread", (String)null, false, false, false);
      conf.addDefaultIntegerProperty("datanucleus.executionContext.maxIdle", (String)null, 20, false, false);
      conf.addDefaultProperty("datanucleus.executionContext.closeActiveTxAction", (String)null, "exception", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultBooleanProperty("datanucleus.objectProvider.reaperThread", (String)null, false, false, false);
      conf.addDefaultIntegerProperty("datanucleus.objectProvider.maxIdle", (String)null, 0, false, false);
      conf.addDefaultProperty("datanucleus.objectProvider.className", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.datastoreIdentityType", (String)null, "datanucleus", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.identityStringTranslatorType", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.identityKeyTranslatorType", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.useImplementationCreator", (String)null, true, false, false);
      conf.addDefaultProperty("datanucleus.TransactionType", (String)null, (String)null, CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.jtaLocator", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.jtaJndiLocation", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.transactionIsolation", (String)null, "read-committed", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultBooleanProperty("datanucleus.NontransactionalRead", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.NontransactionalWrite", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.nontx.atomic", (String)null, true, false, true);
      conf.addDefaultIntegerProperty("datanucleus.datastoreTransactionFlushLimit", (String)null, 1, false, false);
      conf.addDefaultProperty("datanucleus.flush.mode", (String)null, (String)null, CorePropertyValidator.class.getName(), false, true);
      conf.addDefaultProperty("datanucleus.valuegeneration.transactionIsolation", (String)null, "read-committed", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.valuegeneration.transactionAttribute", (String)null, "NEW", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultIntegerProperty("datanucleus.valuegeneration.sequence.allocationSize", (String)null, 10, false, false);
      conf.addDefaultIntegerProperty("datanucleus.valuegeneration.increment.allocationSize", (String)null, 10, false, false);
      conf.addDefaultProperty("datanucleus.validation.mode", (String)null, "auto", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.validation.group.pre-persist", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.validation.group.pre-update", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.validation.group.pre-remove", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.validation.factory", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.storeManagerType", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.readOnlyDatastore", (String)null, false, false, true);
      conf.addDefaultProperty("datanucleus.readOnlyDatastoreAction", (String)null, "EXCEPTION", CorePropertyValidator.class.getName(), false, true);
      conf.addDefaultBooleanProperty("datanucleus.generateSchema.create-schemas", (String)null, false, false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.database.mode", (String)null, "none", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.scripts.mode", (String)null, "none", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.scripts.create.target", (String)null, "datanucleus-schema-create.ddl", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.scripts.drop.target", (String)null, "datanucleus-schema-drop.ddl", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.scripts.create.source", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.scripts.drop.source", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.generateSchema.scripts.load", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.cache.level1.type", (String)null, "soft", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.cache.level2.type", (String)null, "soft", (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.collections", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.cache.collections.lazy", (String)null, (Boolean)null, false, false);
      conf.addDefaultProperty("datanucleus.cache.level2.mode", (String)null, "UNSPECIFIED", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.cache.level2.cacheName", (String)null, "datanucleus", (String)null, false, false);
      conf.addDefaultIntegerProperty("datanucleus.cache.level2.maxSize", (String)null, -1, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.loadFields", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.clearAtClose", (String)null, true, false, false);
      conf.addDefaultIntegerProperty("datanucleus.cache.level2.timeout", (String)null, -1, false, false);
      conf.addDefaultIntegerProperty("datanucleus.cache.level2.batchSize", (String)null, 100, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.cacheEmbedded", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.readThrough", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.writeThrough", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.statisticsEnabled", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.cache.level2.storeByValue", (String)null, true, false, false);
      conf.addDefaultProperty("datanucleus.cache.level2.retrieveMode", (String)null, "use", CorePropertyValidator.class.getName(), false, true);
      conf.addDefaultProperty("datanucleus.cache.level2.storeMode", (String)null, "use", CorePropertyValidator.class.getName(), false, true);
      conf.addDefaultProperty("datanucleus.cache.level2.updateMode", (String)null, "commit-and-datastore-read", CorePropertyValidator.class.getName(), false, true);
      conf.addDefaultProperty("datanucleus.cache.queryCompilation.type", (String)null, "soft", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.cache.queryCompilationDatastore.type", (String)null, "soft", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.cache.queryResults.type", (String)null, "soft", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.cache.queryResults.cacheName", (String)null, "datanucleus-query", (String)null, false, false);
      conf.addDefaultIntegerProperty("datanucleus.cache.queryResults.maxSize", (String)null, -1, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.sql.allowAll", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.query.jdoql.allowAll", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.query.flushBeforeExecution", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.useFetchPlan", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.checkUnusedParameters", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.compileOptimised", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.loadResultsAtCommit", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.compilation.cached", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.results.cached", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.evaluateInMemory", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.resultCache.validateObjects", (String)null, true, false, false);
      conf.addDefaultProperty("datanucleus.query.resultSizeMethod", (String)null, "last", (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.query.compileNamedQueriesAtStartup", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.persistenceByReachabilityAtCommit", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.manageRelationships", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.manageRelationshipsChecks", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.SerializeRead", (String)null, false, false, true);
      conf.addDefaultProperty("datanucleus.deletionPolicy", (String)null, "JDO2", CorePropertyValidator.class.getName(), false, true);
      conf.addDefaultBooleanProperty("datanucleus.findObject.validateWhenCached", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.findObject.typeConversion", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.allowCallbacks", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.DetachAllOnCommit", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.DetachAllOnRollback", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.DetachOnClose", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.CopyOnAttach", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.attachSameDatastore", (String)null, true, false, true);
      conf.addDefaultBooleanProperty("datanucleus.allowAttachOfTransient", (String)null, false, false, true);
      conf.addDefaultBooleanProperty("datanucleus.detachAsWrapped", (String)null, false, false, true);
      conf.addDefaultProperty("datanucleus.detachmentFields", (String)null, "load-fields", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.detachedState", (String)null, "fetch-groups", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultIntegerProperty("datanucleus.maxFetchDepth", (String)null, 1, false, true);
      conf.addDefaultBooleanProperty("datanucleus.schema.autoCreateAll", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.autoCreateSchema", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.autoCreateTables", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.autoCreateColumns", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.autoCreateConstraints", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.autoCreateWarnOnError", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.validateAll", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.validateTables", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.validateColumns", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.schema.validateConstraints", (String)null, false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.autoCreateSchema", "datanucleus.schema.autoCreateAll", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.autoCreateTables", "datanucleus.schema.autoCreateTables", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.autoCreateColumns", "datanucleus.schema.autoCreateColumns", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.autoCreateConstraints", "datanucleus.schema.autoCreateConstraints", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.validateSchema", "datanucleus.schema.validateAll", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.validateTables", "datanucleus.schema.validateTables", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.validateColumns", "datanucleus.schema.validateColumns", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.validateConstraints", "datanucleus.schema.validateConstraints", false, true, false);
      conf.addDefaultBooleanProperty("datanucleus.autoCreateWarnOnError", "datanucleus.schema.autoCreateWarnOnError", false, true, false);
      conf.addDefaultProperty("datanucleus.identifier.namingFactory", (String)null, "datanucleus2", (String)null, true, false);
      conf.addDefaultProperty("datanucleus.identifier.case", (String)null, (String)null, CorePropertyValidator.class.getName(), true, false);
      conf.addDefaultProperty("datanucleus.identifier.tablePrefix", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.identifier.tableSuffix", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.identifier.wordSeparator", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.identifierFactory", (String)null, "datanucleus2", (String)null, true, false);
      conf.addDefaultIntegerProperty("datanucleus.datastoreReadTimeout", (String)null, (Integer)null, true, true);
      conf.addDefaultIntegerProperty("datanucleus.datastoreWriteTimeout", (String)null, (Integer)null, true, true);
      conf.addDefaultBooleanProperty("datanucleus.store.allowReferencesWithNoImplementations", (String)null, false, false, true);
      conf.addDefaultProperty("datanucleus.mapping", (String)null, (String)null, StringPropertyValidator.class.getName(), true, false);
      conf.addDefaultProperty("datanucleus.mapping.Catalog", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.mapping.Schema", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.TenantID", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.autoStartMechanism", (String)null, "None", (String)null, true, false);
      conf.addDefaultProperty("datanucleus.autoStartMechanismMode", (String)null, "Quiet", CorePropertyValidator.class.getName(), true, false);
      conf.addDefaultProperty("datanucleus.autoStartMechanismXmlFile", (String)null, "datanucleusAutoStart.xml", (String)null, true, false);
      conf.addDefaultProperty("datanucleus.autoStartClassNames", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.autoStartMetaDataFiles", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionURL", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionDriverName", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionUserName", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionPassword", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionPasswordDecrypter", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionFactoryName", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionFactory2Name", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionFactory", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.ConnectionFactory2", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.connection.resourceType", (String)null, (String)null, CorePropertyValidator.class.getName(), true, false);
      conf.addDefaultProperty("datanucleus.connection2.resourceType", (String)null, (String)null, CorePropertyValidator.class.getName(), true, false);
      conf.addDefaultProperty("datanucleus.connectionPoolingType", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultProperty("datanucleus.connectionPoolingType.nontx", (String)null, (String)null, (String)null, true, false);
      conf.addDefaultBooleanProperty("datanucleus.connection.nontx.releaseAfterUse", (String)null, true, true, false);
      conf.addDefaultBooleanProperty("datanucleus.connection.singleConnectionPerExecutionContext", (String)null, false, true, false);
   }

   public synchronized void initialise() {
      final ClassLoaderResolver clr = this.getClassLoaderResolver((ClassLoader)null);
      clr.registerUserClassLoader((ClassLoader)this.config.getProperty("datanucleus.primaryClassLoader"));
      boolean generateSchema = false;
      boolean generateScripts = false;
      String generateModeStr = this.config.getStringProperty("datanucleus.generateSchema.database.mode");
      if (generateModeStr == null || generateModeStr.equalsIgnoreCase("none")) {
         generateModeStr = this.config.getStringProperty("datanucleus.generateSchema.scripts.mode");
         generateScripts = true;
      }

      if (generateModeStr != null && !generateModeStr.equalsIgnoreCase("none")) {
         generateSchema = true;
         if (!this.config.getBooleanProperty("datanucleus.schema.autoCreateAll")) {
            this.config.setProperty("datanucleus.schema.autoCreateAll", "true");
         }

         if (!this.config.getBooleanProperty("datanucleus.schema.autoCreateTables")) {
            this.config.setProperty("datanucleus.schema.autoCreateTables", "true");
         }

         if (!this.config.getBooleanProperty("datanucleus.schema.autoCreateColumns")) {
            this.config.setProperty("datanucleus.schema.autoCreateColumns", "true");
         }

         if (!this.config.getBooleanProperty("datanucleus.schema.autoCreateConstraints")) {
            this.config.setProperty("datanucleus.schema.autoCreateConstraints", "true");
         }

         if (!this.config.getBooleanProperty("datanucleus.readOnlyDatastore")) {
            this.config.setProperty("datanucleus.readOnlyDatastore", "false");
         }
      }

      try {
         Set<String> propNamesWithDatastore = this.config.getPropertyNamesWithPrefix("datanucleus.datastore.");
         if (propNamesWithDatastore == null) {
            NucleusLogger.DATASTORE.debug("Creating StoreManager for datastore");
            Map<String, Object> datastoreProps = this.config.getDatastoreProperties();
            this.storeMgr = NucleusContextHelper.createStoreManagerForProperties(this.config.getPersistenceProperties(), datastoreProps, clr, this);
            String transactionIsolation = this.config.getStringProperty("datanucleus.transactionIsolation");
            if (transactionIsolation != null) {
               String reqdIsolation = NucleusContextHelper.getTransactionIsolationForStoreManager(this.storeMgr, transactionIsolation);
               if (!transactionIsolation.equalsIgnoreCase(reqdIsolation)) {
                  this.config.setProperty("datanucleus.transactionIsolation", reqdIsolation);
               }
            }
         } else {
            NucleusLogger.DATASTORE.debug("Creating FederatedStoreManager to handle federation of primary StoreManager and " + propNamesWithDatastore.size() + " secondary datastores");
            this.storeMgr = new FederatedStoreManager(clr, this);
            this.federated = true;
         }
      } catch (NucleusException ne) {
         NucleusLogger.DATASTORE.error("Exception thrown creating StoreManager. See the nested exception", ne);
         throw ne;
      }

      NucleusLogger.DATASTORE.debug("StoreManager now created");
      MetaDataManager mmgr = this.getMetaDataManager();
      final Level2Cache cache = this.getLevel2Cache();
      if (cache != null) {
         mmgr.registerListener(new MetaDataListener() {
            public void loaded(AbstractClassMetaData cmd) {
               if (cmd.hasExtension("cache-pin") && cmd.getValueForExtension("cache-pin").equalsIgnoreCase("true")) {
                  Class cls = clr.classForName(cmd.getFullClassName());
                  cache.pinAll(cls, false);
               }

            }
         });
      }

      String autoStartMechanism = this.config.getStringProperty("datanucleus.autoStartMechanism");
      if (autoStartMechanism != null && !autoStartMechanism.equals("None")) {
         this.initialiseAutoStart(clr);
      }

      if (generateSchema) {
         this.initialiseSchema(generateModeStr, generateScripts);
      }

      if (this.config.getStringProperty("datanucleus.PersistenceUnitName") != null && this.config.getBooleanProperty("datanucleus.persistenceUnitLoadClasses")) {
         Collection<String> loadedClasses = this.getMetaDataManager().getClassesWithMetaData();
         this.storeMgr.manageClasses(clr, (String[])loadedClasses.toArray(new String[loadedClasses.size()]));
      }

      if (this.config.getBooleanProperty("datanucleus.query.compileNamedQueriesAtStartup")) {
         this.initialiseNamedQueries(clr);
      }

      if (this.ecPool == null) {
         this.ecPool = new ExecutionContextPool(this);
      }

      if (this.opFactory == null) {
         this.opFactory = new ObjectProviderFactoryImpl(this);
      }

      super.initialise();
   }

   public synchronized void close() {
      if (this.opFactory != null) {
         this.opFactory.close();
         this.opFactory = null;
      }

      if (this.ecPool != null) {
         this.ecPool.cleanUp();
         this.ecPool = null;
      }

      if (this.fetchGrpMgr != null) {
         this.fetchGrpMgr.clearFetchGroups();
      }

      if (this.storeMgr != null) {
         this.storeMgr.close();
         this.storeMgr = null;
      }

      if (this.metaDataManager != null) {
         this.metaDataManager.close();
         this.metaDataManager = null;
      }

      if (this.statistics != null) {
         if (this.jmxManager != null) {
            this.jmxManager.deregisterMBean(this.statistics.getRegisteredName());
         }

         this.statistics = null;
      }

      if (this.jmxManager != null) {
         this.jmxManager.close();
         this.jmxManager = null;
      }

      if (this.cache != null) {
         this.cache.close();
         this.cache = null;
         NucleusLogger.CACHE.debug(Localiser.msg("004009"));
      }

      if (this.classLoaderResolverMap != null) {
         this.classLoaderResolverMap.clear();
         this.classLoaderResolverMap = null;
      }

      if (this.typeManager != null) {
         this.typeManager = null;
      }

      this.identityManager = null;
   }

   protected void initialiseAutoStart(ClassLoaderResolver clr) throws DatastoreInitialisationException {
      String autoStartMechanism = this.config.getStringProperty("datanucleus.autoStartMechanism");
      String autoStarterClassName = this.getPluginManager().getAttributeValueForExtension("org.datanucleus.autostart", "name", autoStartMechanism, "class-name");
      if (autoStarterClassName != null) {
         String mode = this.config.getStringProperty("datanucleus.autoStartMechanismMode");
         Class[] argsClass = new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.CLASS_LOADER_RESOLVER};
         Object[] args = new Object[]{this.storeMgr, clr};

         try {
            this.starter = (AutoStartMechanism)this.getPluginManager().createExecutableExtension("org.datanucleus.autostart", "name", autoStartMechanism, "class-name", argsClass, args);
            if (mode.equalsIgnoreCase("None")) {
               this.starter.setMode(AutoStartMechanism.Mode.NONE);
            } else if (mode.equalsIgnoreCase("Checked")) {
               this.starter.setMode(AutoStartMechanism.Mode.CHECKED);
            } else if (mode.equalsIgnoreCase("Quiet")) {
               this.starter.setMode(AutoStartMechanism.Mode.QUIET);
            } else if (mode.equalsIgnoreCase("Ignored")) {
               this.starter.setMode(AutoStartMechanism.Mode.IGNORED);
            }
         } catch (Exception e) {
            NucleusLogger.PERSISTENCE.error(StringUtils.getStringFromStackTrace(e));
         }
      }

      if (this.starter != null) {
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("034005", autoStartMechanism));
         }

         boolean illegalState = false;

         try {
            if (!this.starter.isOpen()) {
               this.starter.open();
            }

            Collection existingData = this.starter.getAllClassData();
            if (existingData != null && existingData.size() > 0) {
               List classesNeedingAdding = new ArrayList();

               for(StoreData data : existingData) {
                  if (data.isFCO()) {
                     Class classFound = null;

                     try {
                        classFound = clr.classForName(data.getName());
                     } catch (ClassNotResolvedException var21) {
                        if (data.getInterfaceName() != null) {
                           try {
                              this.getImplementationCreator().newInstance(clr.classForName(data.getInterfaceName()), clr);
                              classFound = clr.classForName(data.getName());
                           } catch (ClassNotResolvedException var19) {
                           }
                        }
                     }

                     if (classFound != null) {
                        NucleusLogger.PERSISTENCE.info(Localiser.msg("032003", data.getName()));
                        classesNeedingAdding.add(data.getName());
                        if (data.getMetaData() == null) {
                           AbstractClassMetaData acmd = this.getMetaDataManager().getMetaDataForClass(classFound, clr);
                           if (acmd != null) {
                              data.setMetaData(acmd);
                           } else {
                              String msg = Localiser.msg("034004", data.getName());
                              if (this.starter.getMode() == AutoStartMechanism.Mode.CHECKED) {
                                 NucleusLogger.PERSISTENCE.error(msg);
                                 throw new DatastoreInitialisationException(msg);
                              }

                              if (this.starter.getMode() == AutoStartMechanism.Mode.IGNORED) {
                                 NucleusLogger.PERSISTENCE.warn(msg);
                              } else if (this.starter.getMode() == AutoStartMechanism.Mode.QUIET) {
                                 NucleusLogger.PERSISTENCE.warn(msg);
                                 NucleusLogger.PERSISTENCE.warn(Localiser.msg("034001", data.getName()));
                                 this.starter.deleteClass(data.getName());
                              }
                           }
                        }
                     } else {
                        String msg = Localiser.msg("034000", data.getName());
                        if (this.starter.getMode() == AutoStartMechanism.Mode.CHECKED) {
                           NucleusLogger.PERSISTENCE.error(msg);
                           throw new DatastoreInitialisationException(msg);
                        }

                        if (this.starter.getMode() == AutoStartMechanism.Mode.IGNORED) {
                           NucleusLogger.PERSISTENCE.warn(msg);
                        } else if (this.starter.getMode() == AutoStartMechanism.Mode.QUIET) {
                           NucleusLogger.PERSISTENCE.warn(msg);
                           NucleusLogger.PERSISTENCE.warn(Localiser.msg("034001", data.getName()));
                           this.starter.deleteClass(data.getName());
                        }
                     }
                  }
               }

               String[] classesToLoad = new String[classesNeedingAdding.size()];
               Iterator classesNeedingAddingIter = classesNeedingAdding.iterator();

               for(int n = 0; classesNeedingAddingIter.hasNext(); classesToLoad[n++] = (String)classesNeedingAddingIter.next()) {
               }

               try {
                  this.storeMgr.manageClasses(clr, classesToLoad);
               } catch (Exception e) {
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("034002", e));
                  illegalState = true;
               }
            }
         } finally {
            if (this.starter.isOpen()) {
               this.starter.close();
            }

            if (illegalState) {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("034003"));
               this.starter = null;
            }

            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("034006", autoStartMechanism));
            }

         }

      }
   }

   protected void logConfigurationDetails() {
      String timeZoneID = this.config.getStringProperty("datanucleus.ServerTimeZoneID");
      if (timeZoneID == null) {
         timeZoneID = TimeZone.getDefault().getID();
      }

      NucleusLogger.PERSISTENCE.debug("Persistence : " + (this.config.getBooleanProperty("datanucleus.Multithreaded") ? "pm-multithreaded" : "pm-singlethreaded") + (this.config.getBooleanProperty("datanucleus.RetainValues") ? ", retain-values" : "") + (this.config.getBooleanProperty("datanucleus.RestoreValues") ? ", restore-values" : "") + (this.config.getBooleanProperty("datanucleus.NontransactionalRead") ? ", nontransactional-read" : "") + (this.config.getBooleanProperty("datanucleus.NontransactionalWrite") ? ", nontransactional-write" : "") + (this.config.getBooleanProperty("datanucleus.persistenceByReachabilityAtCommit") ? ", reachability-at-commit" : "") + (this.config.getBooleanProperty("datanucleus.DetachAllOnCommit") ? ", detach-all-on-commit" : "") + (this.config.getBooleanProperty("datanucleus.DetachAllOnRollback") ? ", detach-all-on-rollback" : "") + (this.config.getBooleanProperty("datanucleus.DetachOnClose") ? ", detach-on-close" : "") + (this.config.getBooleanProperty("datanucleus.CopyOnAttach") ? ", copy-on-attach" : "") + (this.config.getBooleanProperty("datanucleus.manageRelationships") ? (this.config.getBooleanProperty("datanucleus.manageRelationshipsChecks") ? ", managed-relations(checked)" : ", managed-relations(unchecked)") : "") + ", deletion-policy=" + this.config.getStringProperty("datanucleus.deletionPolicy") + (this.config.getBooleanProperty("datanucleus.IgnoreCache") ? ", ignoreCache" : "") + ", serverTimeZone=" + timeZoneID);
      String txnType = "RESOURCE_LOCAL";
      if (TransactionType.JTA.toString().equalsIgnoreCase(this.config.getStringProperty("datanucleus.TransactionType"))) {
         if (this.isJcaMode()) {
            txnType = "JTA (via JCA adapter)";
         } else {
            txnType = "JTA";
         }
      }

      String autoStartMechanism = this.config.getStringProperty("datanucleus.autoStartMechanism");
      if (autoStartMechanism != null && !autoStartMechanism.equals("None")) {
         String autoStartClassNames = this.config.getStringProperty("datanucleus.autoStartClassNames");
         NucleusLogger.PERSISTENCE.debug("AutoStart : mechanism=" + autoStartMechanism + ", mode=" + this.config.getStringProperty("datanucleus.autoStartMechanismMode") + (autoStartClassNames != null ? ", classes=" + autoStartClassNames : ""));
      }

      NucleusLogger.PERSISTENCE.debug("Transactions : type=" + txnType + ", mode=" + (this.config.getBooleanProperty("datanucleus.Optimistic") ? "optimistic" : "datastore") + ", isolation=" + this.config.getStringProperty("datanucleus.transactionIsolation"));
      NucleusLogger.PERSISTENCE.debug("ValueGeneration : txn-isolation=" + this.config.getStringProperty("datanucleus.valuegeneration.transactionIsolation") + " connection=" + (this.config.getStringProperty("datanucleus.valuegeneration.transactionAttribute").equalsIgnoreCase("New") ? "New" : "Existing"));
      NucleusLogger.PERSISTENCE.debug("Cache : Level1 (" + this.config.getStringProperty("datanucleus.cache.level1.type") + "), Level2 (" + this.config.getStringProperty("datanucleus.cache.level2.type") + ", mode=" + this.config.getStringProperty("datanucleus.cache.level2.mode") + "), QueryResults (" + this.config.getStringProperty("datanucleus.cache.queryResults.type") + ")" + (this.config.getBooleanProperty("datanucleus.cache.collections") ? ", Collections/Maps " : ""));
   }

   public boolean isFederated() {
      return this.federated;
   }

   public AutoStartMechanism getAutoStartMechanism() {
      return this.starter;
   }

   protected void initialiseNamedQueries(ClassLoaderResolver clr) {
      MetaDataManager mmgr = this.getMetaDataManager();
      Set<String> queryNames = mmgr.getNamedQueryNames();
      if (queryNames != null) {
         ExecutionContext ec = this.getExecutionContext((Object)null, (Map)null);

         for(String queryName : queryNames) {
            QueryMetaData qmd = mmgr.getMetaDataForQuery((Class)null, clr, queryName);
            if (qmd.getLanguage().equals(QueryLanguage.JPQL.toString()) || qmd.getLanguage().equals(QueryLanguage.JDOQL.toString())) {
               if (NucleusLogger.QUERY.isDebugEnabled()) {
                  NucleusLogger.QUERY.debug(Localiser.msg("008017", queryName, qmd.getQuery()));
               }

               Query q = this.storeMgr.getQueryManager().newQuery(qmd.getLanguage().toString(), ec, qmd.getQuery());
               q.compile();
               q.closeAll();
            }
         }

         ec.close();
      }

   }

   protected void initialiseSchema(String generateModeStr, boolean generateScripts) {
      SchemaTool.Mode mode = null;
      if (generateModeStr.equalsIgnoreCase("create")) {
         mode = SchemaTool.Mode.CREATE;
      } else if (generateModeStr.equalsIgnoreCase("drop")) {
         mode = SchemaTool.Mode.DELETE;
      } else if (generateModeStr.equalsIgnoreCase("drop-and-create")) {
         mode = SchemaTool.Mode.DELETE_CREATE;
      }

      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         if (mode == SchemaTool.Mode.CREATE) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("014000"));
         } else if (mode == SchemaTool.Mode.DELETE) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("014001"));
         } else if (mode == SchemaTool.Mode.DELETE_CREATE) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("014045"));
         }
      }

      Set<String> schemaClassNames = null;
      MetaDataManager metaDataMgr = this.getMetaDataManager();
      FileMetaData[] filemds = metaDataMgr.getFileMetaData();
      schemaClassNames = new TreeSet();
      if (filemds == null) {
         throw new NucleusUserException("No classes to process in generateSchema");
      } else {
         for(int i = 0; i < filemds.length; ++i) {
            for(int j = 0; j < filemds[i].getNoOfPackages(); ++j) {
               for(int k = 0; k < filemds[i].getPackage(j).getNoOfClasses(); ++k) {
                  String className = filemds[i].getPackage(j).getClass(k).getFullClassName();
                  if (!schemaClassNames.contains(className)) {
                     schemaClassNames.add(className);
                  }
               }
            }
         }

         StoreManager storeMgr = this.getStoreManager();
         if (storeMgr instanceof SchemaAwareStoreManager) {
            SchemaAwareStoreManager schemaStoreMgr = (SchemaAwareStoreManager)storeMgr;
            SchemaTool schemaTool = new SchemaTool();
            if (mode == SchemaTool.Mode.CREATE) {
               String createScript = this.config.getStringProperty("datanucleus.generateSchema.scripts.create.source");
               if (!StringUtils.isWhitespace(createScript)) {
                  String scriptContent = this.getDatastoreScriptForResourceName(createScript);
                  NucleusLogger.DATASTORE_SCHEMA.debug(">> createScript=" + scriptContent);
                  if (storeMgr instanceof SchemaScriptAwareStoreManager && !StringUtils.isWhitespace(scriptContent)) {
                     ((SchemaScriptAwareStoreManager)storeMgr).executeScript(scriptContent);
                  }
               }

               if (generateScripts) {
                  schemaTool.setDdlFile(this.config.getStringProperty("datanucleus.generateSchema.scripts.create.target"));
               }

               schemaTool.createSchemaForClasses(schemaStoreMgr, schemaClassNames);
            } else if (mode == SchemaTool.Mode.DELETE) {
               String dropScript = this.config.getStringProperty("datanucleus.generateSchema.scripts.drop.source");
               if (!StringUtils.isWhitespace(dropScript)) {
                  String scriptContent = this.getDatastoreScriptForResourceName(dropScript);
                  NucleusLogger.DATASTORE_SCHEMA.debug(">> dropScript=" + scriptContent);
                  if (storeMgr instanceof SchemaScriptAwareStoreManager && !StringUtils.isWhitespace(scriptContent)) {
                     ((SchemaScriptAwareStoreManager)storeMgr).executeScript(scriptContent);
                  }
               }

               if (generateScripts) {
                  schemaTool.setDdlFile(this.config.getStringProperty("datanucleus.generateSchema.scripts.drop.target"));
               }

               schemaTool.deleteSchemaForClasses(schemaStoreMgr, schemaClassNames);
            } else if (mode == SchemaTool.Mode.DELETE_CREATE) {
               String dropScript = this.config.getStringProperty("datanucleus.generateSchema.scripts.drop.source");
               if (!StringUtils.isWhitespace(dropScript)) {
                  String scriptContent = this.getDatastoreScriptForResourceName(dropScript);
                  NucleusLogger.DATASTORE_SCHEMA.debug(">> dropScript=" + scriptContent);
                  if (storeMgr instanceof SchemaScriptAwareStoreManager && !StringUtils.isWhitespace(scriptContent)) {
                     ((SchemaScriptAwareStoreManager)storeMgr).executeScript(scriptContent);
                  }
               }

               if (generateScripts) {
                  schemaTool.setDdlFile(this.config.getStringProperty("datanucleus.generateSchema.scripts.drop.target"));
               }

               schemaTool.deleteSchemaForClasses(schemaStoreMgr, schemaClassNames);
               String createScript = this.config.getStringProperty("datanucleus.generateSchema.scripts.create.source");
               if (!StringUtils.isWhitespace(createScript)) {
                  String scriptContent = this.getDatastoreScriptForResourceName(createScript);
                  NucleusLogger.DATASTORE_SCHEMA.debug(">> createScript=" + scriptContent);
                  if (storeMgr instanceof SchemaScriptAwareStoreManager && !StringUtils.isWhitespace(scriptContent)) {
                     ((SchemaScriptAwareStoreManager)storeMgr).executeScript(scriptContent);
                  }
               }

               if (generateScripts) {
                  schemaTool.setDdlFile(this.config.getStringProperty("datanucleus.generateSchema.scripts.create.target"));
               }

               schemaTool.createSchemaForClasses(schemaStoreMgr, schemaClassNames);
            }

            String loadScript = this.config.getStringProperty("datanucleus.generateSchema.scripts.load");
            if (!StringUtils.isWhitespace(loadScript)) {
               String scriptContent = this.getDatastoreScriptForResourceName(loadScript);
               NucleusLogger.DATASTORE_SCHEMA.debug(">> loadScript=" + scriptContent);
               if (storeMgr instanceof SchemaScriptAwareStoreManager && !StringUtils.isWhitespace(scriptContent)) {
                  ((SchemaScriptAwareStoreManager)storeMgr).executeScript(scriptContent);
               }
            }
         } else if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("008016", StringUtils.toJVMIDString(storeMgr)));
         }

         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("014043"));
         }

      }
   }

   private String getDatastoreScriptForResourceName(String scriptResourceName) {
      if (StringUtils.isWhitespace(scriptResourceName)) {
         return null;
      } else {
         File file = new File(scriptResourceName);
         if (!file.exists()) {
            try {
               file = new File(new URI(scriptResourceName));
            } catch (Exception var17) {
            }
         }

         if (file != null && file.exists()) {
            label126: {
               FileInputStream fis = null;

               String var6;
               try {
                  StringBuilder str = new StringBuilder();
                  fis = new FileInputStream(file);

                  int content;
                  while((content = fis.read()) != -1) {
                     str.append((char)content);
                  }

                  var6 = str.toString();
               } catch (Exception var18) {
                  break label126;
               } finally {
                  if (fis != null) {
                     try {
                        fis.close();
                     } catch (IOException var16) {
                     }
                  }

               }

               return var6;
            }
         }

         NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("014046", scriptResourceName));
         return null;
      }
   }

   public ExecutionContextPool getExecutionContextPool() {
      if (this.ecPool == null) {
         this.initialise();
      }

      return this.ecPool;
   }

   public ObjectProviderFactory getObjectProviderFactory() {
      if (this.opFactory == null) {
         this.initialise();
      }

      return this.opFactory;
   }

   public ExecutionContext getExecutionContext(Object owner, Map options) {
      return this.getExecutionContextPool().checkOut(owner, options);
   }

   public IdentityManager getIdentityManager() {
      if (this.identityManager == null) {
         this.identityManager = new IdentityManagerImpl(this);
      }

      return this.identityManager;
   }

   public boolean statisticsEnabled() {
      return this.config.getBooleanProperty("datanucleus.enableStatistics") || this.getJMXManager() != null;
   }

   public synchronized ManagementManager getJMXManager() {
      if (this.jmxManager == null && this.config.getStringProperty("datanucleus.jmxType") != null) {
         this.jmxManager = new ManagementManager(this);
      }

      return this.jmxManager;
   }

   public synchronized FactoryStatistics getStatistics() {
      if (this.statistics == null && this.statisticsEnabled()) {
         String name = null;
         if (this.getJMXManager() != null) {
            name = this.jmxManager.getDomainName() + ":InstanceName=" + this.jmxManager.getInstanceName() + ",Type=" + FactoryStatistics.class.getName() + ",Name=Factory" + NucleusContextHelper.random.nextInt();
         }

         this.statistics = new FactoryStatistics(name);
         if (this.jmxManager != null) {
            this.jmxManager.registerMBean(this.statistics, name);
         }
      }

      return this.statistics;
   }

   public synchronized ImplementationCreator getImplementationCreator() {
      if (this.implCreator == null) {
         boolean useImplCreator = this.config.getBooleanProperty("datanucleus.useImplementationCreator");
         if (useImplCreator) {
            this.implCreator = new ImplementationCreatorImpl(this.getMetaDataManager());
         }
      }

      return this.implCreator;
   }

   public synchronized TransactionManager getTransactionManager() {
      if (this.txManager == null) {
         this.txManager = new TransactionManager();
      }

      return this.txManager;
   }

   public synchronized javax.transaction.TransactionManager getJtaTransactionManager() {
      if (this.jtaTxManager == null) {
         this.jtaTxManager = (new TransactionManagerFinder(this)).getTransactionManager(this.getClassLoaderResolver((ClassLoader)this.config.getProperty("datanucleus.primaryClassLoader")));
         if (this.jtaTxManager == null) {
            throw new NucleusTransactionException(Localiser.msg("015030"));
         }
      }

      return this.jtaTxManager;
   }

   public JTASyncRegistry getJtaSyncRegistry() {
      if (this.jtaSyncRegistry == null) {
         try {
            this.jtaSyncRegistry = new JTASyncRegistry();
         } catch (JTASyncRegistryUnavailableException var2) {
            NucleusLogger.TRANSACTION.debug("JTA TransactionSynchronizationRegistry not found at JNDI java:comp/TransactionSynchronizationRegistry so using Transaction to register synchronisation");
            this.jtaSyncRegistry = null;
         }
      }

      return this.jtaSyncRegistry;
   }

   public StoreManager getStoreManager() {
      if (this.storeMgr == null) {
         this.initialise();
      }

      return this.storeMgr;
   }

   public boolean supportsORMMetaData() {
      return this.storeMgr != null ? this.storeMgr.getSupportedOptions().contains("ORM") : true;
   }

   public CallbackHandler getValidationHandler(ExecutionContext ec) {
      if (this.validatorFactoryInit && this.validatorFactory == null) {
         return null;
      } else if (this.config.hasPropertyNotNull("datanucleus.validation.mode") && this.config.getStringProperty("datanucleus.validation.mode").equalsIgnoreCase("none")) {
         this.validatorFactoryInit = true;
         return null;
      } else {
         try {
            ec.getClassLoaderResolver().classForName("javax.validation.Validation");
         } catch (ClassNotResolvedException var3) {
            this.validatorFactoryInit = true;
            return null;
         }

         try {
            if (this.validatorFactory == null) {
               this.validatorFactoryInit = true;
               if (this.config.hasPropertyNotNull("datanucleus.validation.factory")) {
                  this.validatorFactory = this.config.getProperty("datanucleus.validation.factory");
               } else {
                  this.validatorFactory = Validation.buildDefaultValidatorFactory();
               }
            }

            return new BeanValidatorHandler(ec, (ValidatorFactory)this.validatorFactory);
         } catch (Throwable ex) {
            if (this.config.hasPropertyNotNull("datanucleus.validation.mode") && this.config.getStringProperty("datanucleus.validation.mode").equalsIgnoreCase("callback")) {
               throw ec.getApiAdapter().getUserExceptionForException(ex.getMessage(), (Exception)ex);
            } else {
               NucleusLogger.GENERAL.warn("Unable to create validator handler", ex);
               return null;
            }
         }
      }
   }

   public boolean hasLevel2Cache() {
      this.getLevel2Cache();
      return !(this.cache instanceof NullLevel2Cache);
   }

   public Level2Cache getLevel2Cache() {
      if (this.cache == null) {
         String level2Type = this.config.getStringProperty("datanucleus.cache.level2.type");
         String level2ClassName = this.pluginManager.getAttributeValueForExtension("org.datanucleus.cache_level2", "name", level2Type, "class-name");
         if (level2ClassName == null) {
            throw (new NucleusUserException(Localiser.msg("004000", level2Type))).setFatal();
         }

         try {
            this.cache = (Level2Cache)this.pluginManager.createExecutableExtension("org.datanucleus.cache_level2", "name", level2Type, "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this});
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("004002", level2Type));
            }
         } catch (Exception e) {
            throw (new NucleusUserException(Localiser.msg("004001", level2Type, level2ClassName), e)).setFatal();
         }
      }

      return this.cache;
   }

   public ExecutionContext.LifecycleListener[] getExecutionContextListeners() {
      return (ExecutionContext.LifecycleListener[])this.executionContextListeners.toArray(new ExecutionContext.LifecycleListener[this.executionContextListeners.size()]);
   }

   public void addExecutionContextListener(ExecutionContext.LifecycleListener listener) {
      this.executionContextListeners.add(listener);
   }

   public void removeExecutionContextListener(ExecutionContext.LifecycleListener listener) {
      this.executionContextListeners.remove(listener);
   }

   public synchronized void setJcaMode(boolean jca) {
      this.jca = jca;
   }

   public boolean isJcaMode() {
      return this.jca;
   }

   public synchronized FetchGroupManager getFetchGroupManager() {
      if (this.fetchGrpMgr == null) {
         this.fetchGrpMgr = new FetchGroupManager(this);
      }

      return this.fetchGrpMgr;
   }

   public void addInternalFetchGroup(FetchGroup grp) {
      this.getFetchGroupManager().addFetchGroup(grp);
   }

   public void removeInternalFetchGroup(FetchGroup grp) {
      this.getFetchGroupManager().removeFetchGroup(grp);
   }

   public FetchGroup createInternalFetchGroup(Class cls, String name) {
      if (!cls.isInterface() && !this.getApiAdapter().isPersistable(cls)) {
         throw new NucleusUserException("Cannot create FetchGroup for " + cls + " since it is not persistable");
      } else if (cls.isInterface() && !this.getMetaDataManager().isPersistentInterface(cls.getName())) {
         throw new NucleusUserException("Cannot create FetchGroup for " + cls + " since it is not persistable");
      } else {
         return this.getFetchGroupManager().createFetchGroup(cls, name);
      }
   }

   public FetchGroup getInternalFetchGroup(Class cls, String name, boolean createIfNotPresent) {
      if (!cls.isInterface() && !this.getApiAdapter().isPersistable(cls)) {
         throw new NucleusUserException("Cannot create FetchGroup for " + cls + " since it is not persistable");
      } else {
         this.getMetaDataManager().getMetaDataForClass(cls, this.getClassLoaderResolver(cls.getClassLoader()));
         if (cls.isInterface() && !this.getMetaDataManager().isPersistentInterface(cls.getName())) {
            throw new NucleusUserException("Cannot create FetchGroup for " + cls + " since it is not persistable");
         } else {
            return this.getFetchGroupManager().getFetchGroup(cls, name, createIfNotPresent);
         }
      }
   }

   public Set getFetchGroupsWithName(String name) {
      return this.getFetchGroupManager().getFetchGroupsWithName(name);
   }

   public boolean isClassWithIdentityCacheable(Object id) {
      if (id == null) {
         return false;
      } else if (id instanceof SCOID) {
         return false;
      } else if (id instanceof DatastoreUniqueLongId) {
         return false;
      } else {
         AbstractClassMetaData cmd = null;
         if (!IdentityUtils.isDatastoreIdentity(id) && !IdentityUtils.isSingleFieldIdentity(id)) {
            Collection<AbstractClassMetaData> cmds = this.getMetaDataManager().getClassMetaDataWithApplicationId(id.getClass().getName());
            if (cmds != null && !cmds.isEmpty()) {
               cmd = (AbstractClassMetaData)cmds.iterator().next();
            }
         } else {
            cmd = this.getMetaDataManager().getMetaDataForClass(IdentityUtils.getTargetClassNameForIdentitySimple(id), this.getClassLoaderResolver(id.getClass().getClassLoader()));
         }

         return this.isClassCacheable(cmd);
      }
   }

   public boolean isClassCacheable(AbstractClassMetaData cmd) {
      if (cmd != null && cmd.getIdentityType() == IdentityType.NONDURABLE) {
         return false;
      } else {
         String cacheMode = this.config.getStringProperty("datanucleus.cache.level2.mode");
         if (cacheMode.equalsIgnoreCase("ALL")) {
            return true;
         } else if (cacheMode.equalsIgnoreCase("NONE")) {
            return false;
         } else if (cacheMode.equalsIgnoreCase("ENABLE_SELECTIVE")) {
            if (cmd == null) {
               return true;
            } else {
               return cmd.isCacheable() != null && cmd.isCacheable();
            }
         } else if (cacheMode.equalsIgnoreCase("DISABLE_SELECTIVE")) {
            if (cmd == null) {
               return true;
            } else {
               return cmd.isCacheable() == null || cmd.isCacheable();
            }
         } else if (cmd == null) {
            return true;
         } else {
            Boolean cacheableFlag = cmd.isCacheable();
            return cacheableFlag == null ? true : cacheableFlag;
         }
      }
   }
}
