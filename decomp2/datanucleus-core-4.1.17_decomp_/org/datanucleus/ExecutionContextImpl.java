package org.datanucleus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.cache.CachedPC;
import org.datanucleus.cache.L2CachePopulateFieldManager;
import org.datanucleus.cache.Level1Cache;
import org.datanucleus.cache.Level2Cache;
import org.datanucleus.exceptions.ClassNotDetachableException;
import org.datanucleus.exceptions.ClassNotPersistableException;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.CommitStateTransitionException;
import org.datanucleus.exceptions.NoPersistenceInformationException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusFatalUserException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.ObjectDetachedException;
import org.datanucleus.exceptions.RollbackStateTransitionException;
import org.datanucleus.exceptions.TransactionActiveOnCloseException;
import org.datanucleus.exceptions.TransactionNotActiveException;
import org.datanucleus.flush.FlushProcess;
import org.datanucleus.flush.Operation;
import org.datanucleus.flush.OperationQueue;
import org.datanucleus.identity.DatastoreId;
import org.datanucleus.identity.DatastoreUniqueLongId;
import org.datanucleus.identity.IdentityKeyTranslator;
import org.datanucleus.identity.IdentityReference;
import org.datanucleus.identity.IdentityStringTranslator;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.identity.SCOID;
import org.datanucleus.management.ManagerStatistics;
import org.datanucleus.management.jmx.ManagementManager;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.TransactionType;
import org.datanucleus.properties.BasePropertyStore;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.state.DetachState;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.LockManager;
import org.datanucleus.state.LockManagerImpl;
import org.datanucleus.state.NullCallbackHandler;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.RelationshipManager;
import org.datanucleus.state.RelationshipManagerImpl;
import org.datanucleus.store.Extent;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.PersistenceBatchType;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.fieldmanager.NullifyRelationFieldManager;
import org.datanucleus.store.fieldmanager.ReachabilityFieldManager;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.TypeConversionHelper;
import org.datanucleus.util.WeakValueMap;

public class ExecutionContextImpl implements ExecutionContext, TransactionEventListener {
   PersistenceNucleusContext nucCtx;
   private Object owner;
   private boolean closing = false;
   private boolean closed;
   private FetchPlan fetchPlan;
   private ClassLoaderResolver clr = null;
   private CallbackHandler callbackHandler;
   protected Level1Cache cache;
   private BasePropertyStore properties = new BasePropertyStore();
   private Object objectLookingForOP = null;
   private ObjectProvider foundOP = null;
   private Transaction tx;
   private Map enlistedOPCache = new WeakValueMap();
   private List dirtyOPs = new ArrayList();
   private List indirectDirtyOPs = new ArrayList();
   private OperationQueue operationQueue = null;
   private Set nontxProcessedOPs = null;
   protected boolean l2CacheEnabled = false;
   private Set l2CacheTxIds = null;
   private Map l2CacheTxFieldsToUpdateById = null;
   private int flushing = 0;
   private boolean runningDetachAllOnTxnEnd = false;
   private FetchGroupManager fetchGrpMgr;
   private LockManager lockMgr = null;
   protected Lock lock;
   private boolean runningManageRelations = false;
   Map managedRelationDetails = null;
   Map opAttachDetachObjectReferenceMap = null;
   Map opEmbeddedInfoByOwner = null;
   Map opEmbeddedInfoByEmbedded = null;
   protected Map opAssociatedValuesMapByOP = null;
   private boolean runningPBRAtCommit = false;
   private Set reachabilityPersistedIds = null;
   private Set reachabilityDeletedIds = null;
   private Set reachabilityFlushedNewIds = null;
   private Set reachabilityEnlistedIds = null;
   ManagerStatistics statistics = null;
   private Set ecListeners = null;
   private ThreadLocal contextInfoThreadLocal;
   private List objectsToEvictUponRollback = null;
   private ObjectProvider[] detachAllOnTxnEndOPs = null;

   public ExecutionContextImpl(PersistenceNucleusContext ctx, Object owner, Map options) {
      this.nucCtx = ctx;
      if (ctx.getConfiguration().getBooleanProperty("datanucleus.Multithreaded")) {
         this.lock = new ReentrantLock();
      }

      this.initialise(owner, options);
      this.initialiseLevel1Cache();
   }

   public void initialise(Object owner, Map options) {
      this.owner = owner;
      this.closed = false;
      ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
      this.clr = this.nucCtx.getClassLoaderResolver(contextLoader);

      try {
         ImplementationCreator ic = this.nucCtx.getImplementationCreator();
         if (ic != null) {
            this.clr.setRuntimeClassLoader(ic.getClassLoader());
         }
      } catch (Exception var8) {
      }

      Configuration conf = this.nucCtx.getConfiguration();

      for(Map.Entry entry : conf.getManagerOverrideableProperties().entrySet()) {
         this.properties.setProperty(((String)entry.getKey()).toLowerCase(Locale.ENGLISH), entry.getValue());
      }

      this.properties.getFrequentProperties().setDefaults(conf.getFrequentProperties());
      this.fetchPlan = (new FetchPlan(this, this.clr)).setMaxFetchDepth(conf.getIntProperty("datanucleus.maxFetchDepth"));
      if (TransactionType.JTA.toString().equalsIgnoreCase(conf.getStringProperty("datanucleus.TransactionType"))) {
         if (this.getNucleusContext().isJcaMode()) {
            this.tx = new JTAJCATransactionImpl(this, this.properties);
         } else {
            boolean autoJoin = true;
            if (options != null && options.containsKey("jta_autojoin")) {
               autoJoin = Boolean.valueOf((String)options.get("jta_autojoin"));
            }

            this.tx = new JTATransactionImpl(this, autoJoin, this.properties);
         }
      } else {
         this.tx = new TransactionImpl(this, this.properties);
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("010000", this, this.nucCtx.getStoreManager(), this.tx));
      }

      if (this.nucCtx.statisticsEnabled()) {
         String name = null;
         if (this.nucCtx.getJMXManager() != null) {
            ManagementManager mgmtMgr = this.nucCtx.getJMXManager();
            name = mgmtMgr.getDomainName() + ":InstanceName=" + mgmtMgr.getInstanceName() + ",Type=" + ManagerStatistics.class.getName() + ",Name=Manager" + NucleusContextHelper.random.nextLong();
         }

         this.statistics = new ManagerStatistics(name, this.nucCtx.getStatistics());
         if (this.nucCtx.getJMXManager() != null) {
            this.nucCtx.getJMXManager().registerMBean(this.statistics, name);
         }
      }

      this.contextInfoThreadLocal = new ThreadLocal() {
         protected Object initialValue() {
            return new ThreadContextInfo();
         }
      };
      if (this.getReachabilityAtCommit()) {
         this.reachabilityPersistedIds = new HashSet();
         this.reachabilityDeletedIds = new HashSet();
         this.reachabilityFlushedNewIds = new HashSet();
         this.reachabilityEnlistedIds = new HashSet();
      }

      this.objectsToEvictUponRollback = null;
      this.setLevel2Cache(true);
   }

   public void close() {
      if (this.closed) {
         throw new NucleusUserException(Localiser.msg("010002"));
      } else {
         if (this.tx.getIsActive()) {
            String closeActionTxAction = this.nucCtx.getConfiguration().getStringProperty("datanucleus.executionContext.closeActiveTxAction");
            if (closeActionTxAction != null) {
               if (closeActionTxAction.equalsIgnoreCase("exception")) {
                  throw new TransactionActiveOnCloseException(this);
               }

               if (closeActionTxAction.equalsIgnoreCase("rollback")) {
                  NucleusLogger.GENERAL.warn("ExecutionContext closed with active transaction, so rolling back the active transaction");
                  this.tx.rollback();
               }
            }
         }

         if (!this.dirtyOPs.isEmpty() && this.tx.getNontransactionalWrite()) {
            if (this.isNonTxAtomic()) {
               this.processNontransactionalUpdate();
            } else {
               try {
                  this.tx.begin();
                  this.tx.commit();
               } finally {
                  if (this.tx.isActive()) {
                     this.tx.rollback();
                  }

               }
            }
         }

         if (this.properties.getFrequentProperties().getDetachOnClose() && this.cache != null && !this.cache.isEmpty()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("010011"));
            List<ObjectProvider> toDetach = new ArrayList(this.cache.values());

            try {
               if (!this.tx.getNontransactionalRead()) {
                  this.tx.begin();
               }

               for(ObjectProvider op : toDetach) {
                  if (op != null && op.getObject() != null && !op.getExecutionContext().getApiAdapter().isDeleted(op.getObject()) && op.getExternalObjectId() != null) {
                     try {
                        op.detach(new DetachState(this.getApiAdapter()));
                     } catch (NucleusObjectNotFoundException var12) {
                     }
                  }
               }

               if (!this.tx.getNontransactionalRead()) {
                  this.tx.commit();
               }
            } finally {
               if (!this.tx.getNontransactionalRead() && this.tx.isActive()) {
                  this.tx.rollback();
               }

            }

            NucleusLogger.PERSISTENCE.debug(Localiser.msg("010012"));
         }

         ExecutionContext.LifecycleListener[] listener = this.nucCtx.getExecutionContextListeners();

         for(int i = 0; i < listener.length; ++i) {
            listener[i].preClose(this);
         }

         this.closing = true;
         if (this.cache != null && !this.cache.isEmpty()) {
            for(ObjectProvider op : new HashSet(this.cache.values())) {
               if (op != null) {
                  op.disconnect();
               }
            }

            this.cache.clear();
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("003011"));
            }
         }

         this.closeCallbackHandler();
         if (this.ecListeners != null) {
            for(ExecutionContextListener lstr : new HashSet(this.ecListeners)) {
               lstr.executionContextClosing(this);
            }

            this.ecListeners.clear();
            this.ecListeners = null;
         }

         this.fetchPlan.clearGroups().addGroup("default");
         if (this.statistics != null) {
            if (this.nucCtx.getJMXManager() != null) {
               this.nucCtx.getJMXManager().deregisterMBean(this.statistics.getRegisteredName());
            }

            this.statistics = null;
         }

         this.enlistedOPCache.clear();
         this.dirtyOPs.clear();
         this.indirectDirtyOPs.clear();
         if (this.nontxProcessedOPs != null) {
            this.nontxProcessedOPs.clear();
            this.nontxProcessedOPs = null;
         }

         if (this.managedRelationDetails != null) {
            this.managedRelationDetails.clear();
            this.managedRelationDetails = null;
         }

         if (this.l2CacheTxIds != null) {
            this.l2CacheTxIds.clear();
         }

         if (this.l2CacheTxFieldsToUpdateById != null) {
            this.l2CacheTxFieldsToUpdateById.clear();
         }

         if (this.getReachabilityAtCommit()) {
            this.reachabilityPersistedIds.clear();
            this.reachabilityDeletedIds.clear();
            this.reachabilityFlushedNewIds.clear();
            this.reachabilityEnlistedIds.clear();
         }

         if (this.opEmbeddedInfoByOwner != null) {
            this.opEmbeddedInfoByOwner.clear();
            this.opEmbeddedInfoByOwner = null;
         }

         if (this.opEmbeddedInfoByEmbedded != null) {
            this.opEmbeddedInfoByEmbedded.clear();
            this.opEmbeddedInfoByEmbedded = null;
         }

         if (this.opAssociatedValuesMapByOP != null) {
            this.opAssociatedValuesMapByOP.clear();
            this.opAssociatedValuesMapByOP = null;
         }

         this.objectsToEvictUponRollback = null;
         this.closing = false;
         this.closed = true;
         this.tx.close();
         this.tx = null;
         this.owner = null;
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("010001", this));
         }

         this.nucCtx.getExecutionContextPool().checkIn(this);
      }
   }

   public void registerExecutionContextListener(ExecutionContextListener listener) {
      if (this.ecListeners == null) {
         this.ecListeners = new HashSet();
      }

      this.ecListeners.add(listener);
   }

   public void deregisterExecutionContextListener(ExecutionContextListener listener) {
      if (this.ecListeners != null) {
         this.ecListeners.remove(listener);
      }

   }

   protected void setLevel2Cache(boolean flag) {
      if (flag && this.nucCtx.hasLevel2Cache() && !this.l2CacheEnabled) {
         this.l2CacheTxIds = new HashSet();
         this.l2CacheTxFieldsToUpdateById = new HashMap();
         this.l2CacheEnabled = true;
      } else if (!flag && this.l2CacheEnabled) {
         if (NucleusLogger.CACHE.isDebugEnabled()) {
            NucleusLogger.CACHE.debug("Disabling L2 caching for " + this);
         }

         this.l2CacheTxIds.clear();
         this.l2CacheTxIds = null;
         this.l2CacheTxFieldsToUpdateById.clear();
         this.l2CacheTxFieldsToUpdateById = null;
         this.l2CacheEnabled = false;
      }

   }

   public boolean isClosed() {
      return this.closed;
   }

   protected ThreadContextInfo acquireThreadContextInfo() {
      ThreadContextInfo threadInfo = (ThreadContextInfo)this.contextInfoThreadLocal.get();
      ++threadInfo.referenceCounter;
      return threadInfo;
   }

   protected ThreadContextInfo getThreadContextInfo() {
      return (ThreadContextInfo)this.contextInfoThreadLocal.get();
   }

   protected void releaseThreadContextInfo() {
      ThreadContextInfo threadInfo = (ThreadContextInfo)this.contextInfoThreadLocal.get();
      if (--threadInfo.referenceCounter <= 0) {
         threadInfo.referenceCounter = 0;
         if (threadInfo.attachedOwnerByObject != null) {
            threadInfo.attachedOwnerByObject.clear();
         }

         threadInfo.attachedOwnerByObject = null;
         if (threadInfo.attachedPCById != null) {
            threadInfo.attachedPCById.clear();
         }

         threadInfo.attachedPCById = null;
         this.contextInfoThreadLocal.remove();
      }

   }

   public void transactionStarted() {
      this.getStoreManager().transactionStarted(this);
      this.postBegin();
   }

   public void transactionPreFlush() {
   }

   public void transactionFlushed() {
   }

   public void transactionPreCommit() {
      this.preCommit();
   }

   public void transactionCommitted() {
      this.getStoreManager().transactionCommitted(this);
      this.postCommit();
   }

   public void transactionPreRollBack() {
      this.preRollback();
   }

   public void transactionRolledBack() {
      this.getStoreManager().transactionRolledBack(this);
      this.postRollback();
   }

   public void transactionEnded() {
   }

   public void transactionSetSavepoint(String name) {
   }

   public void transactionReleaseSavepoint(String name) {
   }

   public void transactionRollbackToSavepoint(String name) {
   }

   public ManagerStatistics getStatistics() {
      return this.statistics;
   }

   protected void initialiseLevel1Cache() {
      String level1Type = this.nucCtx.getConfiguration().getStringProperty("datanucleus.cache.level1.type");
      if (!"none".equalsIgnoreCase(level1Type)) {
         String level1ClassName = this.getNucleusContext().getPluginManager().getAttributeValueForExtension("org.datanucleus.cache_level1", "name", level1Type, "class-name");
         if (level1ClassName == null) {
            throw (new NucleusUserException(Localiser.msg("003001", level1Type))).setFatal();
         } else {
            try {
               this.cache = (Level1Cache)this.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.cache_level1", (String)"name", (String)level1Type, "class-name", (Class[])null, (Object[])null);
               if (NucleusLogger.CACHE.isDebugEnabled()) {
                  NucleusLogger.CACHE.debug(Localiser.msg("003003", level1Type));
               }

            } catch (Exception e) {
               throw (new NucleusUserException(Localiser.msg("003002", level1Type, level1ClassName), e)).setFatal();
            }
         }
      }
   }

   public Level1Cache getLevel1Cache() {
      return this.cache;
   }

   public ClassLoaderResolver getClassLoaderResolver() {
      return this.clr;
   }

   public StoreManager getStoreManager() {
      return this.getNucleusContext().getStoreManager();
   }

   public ApiAdapter getApiAdapter() {
      return this.getNucleusContext().getApiAdapter();
   }

   public TypeManager getTypeManager() {
      return this.getNucleusContext().getTypeManager();
   }

   public MetaDataManager getMetaDataManager() {
      return this.getNucleusContext().getMetaDataManager();
   }

   public LockManager getLockManager() {
      if (this.lockMgr == null) {
         this.lockMgr = new LockManagerImpl();
      }

      return this.lockMgr;
   }

   public FetchPlan getFetchPlan() {
      this.assertIsOpen();
      return this.fetchPlan;
   }

   public PersistenceNucleusContext getNucleusContext() {
      return this.nucCtx;
   }

   public Object getOwner() {
      return this.owner;
   }

   public void setProperties(Map props) {
      if (props != null) {
         for(Map.Entry entry : props.entrySet()) {
            if (entry.getKey() instanceof String) {
               this.setProperty((String)entry.getKey(), entry.getValue());
            }
         }

      }
   }

   public void setProperty(String name, Object value) {
      if (this.properties.hasProperty(name.toLowerCase(Locale.ENGLISH))) {
         String intName = this.getNucleusContext().getConfiguration().getInternalNameForProperty(name);
         this.getNucleusContext().getConfiguration().validatePropertyValue(intName, value);
         this.properties.setProperty(intName.toLowerCase(Locale.ENGLISH), value);
      } else if (name.equalsIgnoreCase("datanucleus.cache.level2.type")) {
         if ("none".equalsIgnoreCase((String)value)) {
            this.setLevel2Cache(false);
         }
      } else {
         String intName = this.getNucleusContext().getConfiguration().getInternalNameForProperty(name);
         if (intName != null && !intName.equalsIgnoreCase(name)) {
            this.getNucleusContext().getConfiguration().validatePropertyValue(intName, value);
            this.properties.setProperty(intName.toLowerCase(Locale.ENGLISH), value);
         } else {
            NucleusLogger.PERSISTENCE.warn("Attempt to set property \"" + name + "\" on PM/EM yet this is not supported. Ignored");
         }
      }

      if (name.equalsIgnoreCase("datanucleus.SerializeRead")) {
         this.tx.setSerializeRead(this.getBooleanProperty("datanucleus.SerializeRead"));
      }

   }

   public Map getProperties() {
      Map<String, Object> props = new HashMap();

      for(Map.Entry entry : this.properties.getProperties().entrySet()) {
         props.put(this.nucCtx.getConfiguration().getCaseSensitiveNameForPropertyName((String)entry.getKey()), entry.getValue());
      }

      return props;
   }

   public Boolean getBooleanProperty(String name) {
      if (this.properties.hasProperty(name.toLowerCase(Locale.ENGLISH))) {
         this.assertIsOpen();
         return this.properties.getBooleanProperty(this.getNucleusContext().getConfiguration().getInternalNameForProperty(name));
      } else {
         return null;
      }
   }

   public Integer getIntProperty(String name) {
      if (this.properties.hasProperty(name.toLowerCase(Locale.ENGLISH))) {
         this.assertIsOpen();
         return this.properties.getIntProperty(this.getNucleusContext().getConfiguration().getInternalNameForProperty(name));
      } else {
         return null;
      }
   }

   public String getStringProperty(String name) {
      if (this.properties.hasProperty(name.toLowerCase(Locale.ENGLISH))) {
         this.assertIsOpen();
         return this.properties.getStringProperty(this.getNucleusContext().getConfiguration().getInternalNameForProperty(name));
      } else {
         return null;
      }
   }

   public Object getProperty(String name) {
      if (this.properties.hasProperty(name.toLowerCase(Locale.ENGLISH))) {
         this.assertIsOpen();
         return this.properties.getProperty(this.getNucleusContext().getConfiguration().getInternalNameForProperty(name).toLowerCase(Locale.ENGLISH));
      } else {
         return null;
      }
   }

   public Set getSupportedProperties() {
      return this.nucCtx.getConfiguration().getManagedOverrideablePropertyNames();
   }

   public boolean getMultithreaded() {
      return false;
   }

   protected boolean getReachabilityAtCommit() {
      return this.properties.getFrequentProperties().getReachabilityAtCommit();
   }

   public boolean isDelayDatastoreOperationsEnabled() {
      if (!this.isFlushing() && !this.tx.isCommitting()) {
         String flushModeString = (String)this.getProperty("datanucleus.flush.mode");
         if (flushModeString != null) {
            return !flushModeString.equalsIgnoreCase("AUTO");
         } else if (this.tx.isActive()) {
            return this.tx.getOptimistic();
         } else {
            return !this.isNonTxAtomic();
         }
      } else {
         return false;
      }
   }

   public boolean isInserting(Object pc) {
      ObjectProvider op = this.findObjectProvider(pc);
      return op == null ? false : op.isInserting();
   }

   public Transaction getTransaction() {
      this.assertIsOpen();
      return this.tx;
   }

   public void enlistInTransaction(ObjectProvider op) {
      this.assertActiveTransaction();
      if (this.getReachabilityAtCommit() && this.tx.isActive()) {
         if (this.getApiAdapter().isNew(op.getObject())) {
            this.reachabilityFlushedNewIds.add(op.getInternalObjectId());
         } else if (this.getApiAdapter().isPersistent(op.getObject()) && !this.getApiAdapter().isDeleted(op.getObject()) && !this.reachabilityFlushedNewIds.contains(op.getInternalObjectId())) {
            this.reachabilityPersistedIds.add(op.getInternalObjectId());
         }

         if (!this.runningPBRAtCommit) {
            this.reachabilityEnlistedIds.add(op.getInternalObjectId());
         }
      }

      if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
         NucleusLogger.TRANSACTION.debug(Localiser.msg("015017", StringUtils.toJVMIDString(op.getObject()), op.getInternalObjectId().toString()));
      }

      this.enlistedOPCache.put(op.getInternalObjectId(), op);
   }

   public void evictFromTransaction(ObjectProvider op) {
      if (this.enlistedOPCache.remove(op.getInternalObjectId()) != null && NucleusLogger.TRANSACTION.isDebugEnabled()) {
         NucleusLogger.TRANSACTION.debug(Localiser.msg("015019", StringUtils.toJVMIDString(op.getObject()), IdentityUtils.getPersistableIdentityForId(op.getInternalObjectId())));
      }

   }

   public boolean isEnlistedInTransaction(Object id) {
      if (this.getReachabilityAtCommit() && this.tx.isActive()) {
         return id == null ? false : this.reachabilityEnlistedIds.contains(id);
      } else {
         return false;
      }
   }

   public Object getAttachedObjectForId(Object id) {
      ObjectProvider op = (ObjectProvider)this.enlistedOPCache.get(id);
      if (op != null) {
         return op.getObject();
      } else {
         if (this.cache != null) {
            op = (ObjectProvider)this.cache.get(id);
            if (op != null) {
               return op.getObject();
            }
         }

         return null;
      }
   }

   public void addObjectProvider(ObjectProvider op) {
      this.putObjectIntoLevel1Cache(op);
   }

   public void removeObjectProvider(ObjectProvider op) {
      if (!this.closing) {
         this.removeObjectFromLevel1Cache(op.getInternalObjectId());
         this.enlistedOPCache.remove(op.getInternalObjectId());
         if (this.opEmbeddedInfoByEmbedded != null) {
            List<ExecutionContext.EmbeddedOwnerRelation> embRels = (List)this.opEmbeddedInfoByEmbedded.get(op);
            if (embRels != null) {
               for(ExecutionContext.EmbeddedOwnerRelation rel : embRels) {
                  this.opEmbeddedInfoByOwner.remove(rel.getOwnerOP());
               }

               this.opEmbeddedInfoByEmbedded.remove(op);
            }
         }

         if (this.opEmbeddedInfoByOwner != null) {
            List<ExecutionContext.EmbeddedOwnerRelation> embRels = (List)this.opEmbeddedInfoByOwner.get(op);
            if (embRels != null) {
               for(ExecutionContext.EmbeddedOwnerRelation rel : embRels) {
                  this.opEmbeddedInfoByEmbedded.remove(rel.getEmbeddedOP());
               }

               this.opEmbeddedInfoByOwner.remove(op);
            }
         }

         if (this.opAssociatedValuesMapByOP != null) {
            this.opAssociatedValuesMapByOP.remove(op);
         }

         this.setAttachDetachReferencedObject(op, (Object)null);
      }
   }

   public ObjectProvider findObjectProvider(Object pc) {
      ObjectProvider op = null;
      Object previousLookingFor = this.objectLookingForOP;
      ObjectProvider previousFound = this.foundOP;

      try {
         this.objectLookingForOP = pc;
         this.foundOP = null;
         ExecutionContext ec = this.getApiAdapter().getExecutionContext(pc);
         if (ec != null && this != ec) {
            throw new NucleusUserException(Localiser.msg("010007", this.getApiAdapter().getIdForObject(pc)));
         }

         op = this.foundOP;
      } finally {
         this.objectLookingForOP = previousLookingFor;
         this.foundOP = previousFound;
      }

      return op;
   }

   public ObjectProvider findObjectProvider(Object pc, boolean persist) {
      ObjectProvider op = this.findObjectProvider(pc);
      if (op == null && persist) {
         int objectType = 0;
         Object object2 = this.persistObjectInternal(pc, (FieldValues)null, (ObjectProvider)null, -1, objectType);
         op = this.findObjectProvider(object2);
      } else if (op == null) {
         return null;
      }

      return op;
   }

   public ObjectProvider findObjectProviderForEmbedded(Object value, ObjectProvider owner, AbstractMemberMetaData mmd) {
      ObjectProvider embeddedOP = this.findObjectProvider(value);
      if (embeddedOP == null) {
         embeddedOP = this.nucCtx.getObjectProviderFactory().newForEmbedded(this, value, false, owner, owner.getClassMetaData().getMetaDataForMember(mmd.getName()).getAbsoluteFieldNumber());
      }

      ObjectProvider[] embOwnerOPs = this.getOwnersForEmbeddedObjectProvider(embeddedOP);
      if (embOwnerOPs == null || embOwnerOPs.length == 0) {
         int absoluteFieldNumber = owner.getClassMetaData().getMetaDataForMember(mmd.getName()).getAbsoluteFieldNumber();
         this.registerEmbeddedRelation(owner, absoluteFieldNumber, embeddedOP);
         embeddedOP.setPcObjectType((short)1);
      }

      return embeddedOP;
   }

   public ObjectProvider findObjectProviderOfOwnerForAttachingObject(Object pc) {
      ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

      ObjectProvider var3;
      try {
         if (threadInfo.attachedOwnerByObject != null) {
            var3 = (ObjectProvider)threadInfo.attachedOwnerByObject.get(pc);
            return var3;
         }

         var3 = null;
      } finally {
         this.releaseThreadContextInfo();
      }

      return var3;
   }

   public void hereIsObjectProvider(ObjectProvider op, Object pc) {
      if (this.objectLookingForOP == pc) {
         this.foundOP = op;
      }

   }

   private boolean isNonTxAtomic() {
      return this.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.nontx.atomic");
   }

   public void processNontransactionalUpdate() {
      if (!this.tx.isActive() && this.tx.getNontransactionalWrite() && this.tx.getNontransactionalWriteAutoCommit()) {
         ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

         try {
            if (!threadInfo.nontxPersistDelete) {
               this.processNontransactionalAtomicChanges();
               return;
            }
         } finally {
            this.releaseThreadContextInfo();
         }

      }
   }

   protected void processNontransactionalAtomicChanges() {
      if (!this.tx.isActive() && this.tx.getNontransactionalWrite() && this.tx.getNontransactionalWriteAutoCommit()) {
         if (!this.dirtyOPs.isEmpty()) {
            for(ObjectProvider op : this.dirtyOPs) {
               if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
                  NucleusLogger.TRANSACTION.debug(Localiser.msg("015017", StringUtils.toJVMIDString(op.getObject()), op.getInternalObjectId().toString()));
               }

               this.enlistedOPCache.put(op.getInternalObjectId(), op);
            }

            this.flushInternal(true);
            if (this.l2CacheEnabled) {
               this.performLevel2CacheUpdateAtCommit();
            }

            if (this.properties.getFrequentProperties().getDetachAllOnCommit()) {
               this.performDetachAllOnTxnEndPreparation();
               this.performDetachAllOnTxnEnd();
            }

            List failures = null;

            try {
               ApiAdapter api = this.getApiAdapter();
               ObjectProvider[] ops = (ObjectProvider[])this.enlistedOPCache.values().toArray(new ObjectProvider[this.enlistedOPCache.size()]);

               for(int i = 0; i < ops.length; ++i) {
                  try {
                     if (ops[i] != null && ops[i].getObject() != null && api.isPersistent(ops[i].getObject()) && api.isDirty(ops[i].getObject())) {
                        ops[i].postCommit(this.getTransaction());
                     } else {
                        NucleusLogger.PERSISTENCE.debug(">> Atomic nontransactional processing : Not performing postCommit on " + ops[i]);
                     }
                  } catch (RuntimeException e) {
                     if (failures == null) {
                        failures = new ArrayList();
                     }

                     failures.add(e);
                  }
               }
            } finally {
               this.resetTransactionalVariables();
            }

            if (failures != null && !failures.isEmpty()) {
               throw new CommitStateTransitionException((Exception[])failures.toArray(new Exception[failures.size()]));
            }
         }

         if (this.nontxProcessedOPs != null && !this.nontxProcessedOPs.isEmpty()) {
            for(ObjectProvider op : this.nontxProcessedOPs) {
               if (op != null && op.getLifecycleState() != null && op.getLifecycleState().isDeleted()) {
                  this.removeObjectFromLevel1Cache(op.getInternalObjectId());
                  this.removeObjectFromLevel2Cache(op.getInternalObjectId());
               }
            }

            this.nontxProcessedOPs.clear();
         }

      }
   }

   public void evictObject(Object obj) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            this.assertNotDetached(obj);
            ObjectProvider op = this.findObjectProvider(obj);
            if (op == null) {
               throw new NucleusUserException(Localiser.msg("010048", StringUtils.toJVMIDString(obj), this.getApiAdapter().getIdForObject(obj), "evict"));
            }

            op.evict();
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public void evictObjects(Class cls, boolean subclasses) {
      if (this.cache != null) {
         try {
            if (this.getMultithreaded()) {
               this.lock.lock();
            }

            for(ObjectProvider op : new HashSet(this.cache.values())) {
               Object pc = op.getObject();
               boolean evict = false;
               if (!subclasses && pc.getClass() == cls) {
                  evict = true;
               } else if (subclasses && cls.isAssignableFrom(pc.getClass())) {
                  evict = true;
               }

               if (evict) {
                  op.evict();
                  this.removeObjectFromLevel1Cache(this.getApiAdapter().getIdForObject(pc));
               }
            }
         } finally {
            if (this.getMultithreaded()) {
               this.lock.unlock();
            }

         }
      }

   }

   public void evictAllObjects() {
      if (this.cache != null) {
         for(ObjectProvider op : new HashSet(this.cache.values())) {
            if (op != null) {
               op.evict();
            }
         }

         this.cache.clear();
         if (NucleusLogger.CACHE.isDebugEnabled()) {
            NucleusLogger.CACHE.debug(Localiser.msg("003011"));
         }
      }

   }

   public void refreshObject(Object obj) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            this.assertNotDetached(obj);
            ObjectProvider op = this.findObjectProvider(obj);
            if (op == null) {
               throw new NucleusUserException(Localiser.msg("010048", StringUtils.toJVMIDString(obj), this.getApiAdapter().getIdForObject(obj), "refresh"));
            }

            if (!this.getApiAdapter().isPersistent(obj) || !op.isWaitingToBeFlushedToDatastore()) {
               op.refresh();
               return;
            }
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public void refreshAllObjects() {
      Set<ObjectProvider> toRefresh = new HashSet();
      toRefresh.addAll(this.enlistedOPCache.values());
      toRefresh.addAll(this.dirtyOPs);
      toRefresh.addAll(this.indirectDirtyOPs);
      if (!this.tx.isActive() && this.cache != null) {
         toRefresh.addAll(this.cache.values());
      }

      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         List failures = null;

         for(ObjectProvider op : toRefresh) {
            try {
               op.refresh();
            } catch (RuntimeException e) {
               if (failures == null) {
                  failures = new ArrayList();
               }

               failures.add(e);
            }
         }

         if (failures != null && !failures.isEmpty()) {
            throw new NucleusUserException(Localiser.msg("010037"), (Exception[])failures.toArray(new Exception[failures.size()]));
         }
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

   }

   public void retrieveObject(Object obj, boolean fgOnly) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            this.assertNotDetached(obj);
            ObjectProvider op = this.findObjectProvider(obj);
            if (op == null) {
               throw new NucleusUserException(Localiser.msg("010048", StringUtils.toJVMIDString(obj), this.getApiAdapter().getIdForObject(obj), "retrieve"));
            }

            op.retrieve(fgOnly);
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public Object persistObject(Object obj, boolean merging) {
      if (obj == null) {
         return null;
      } else {
         ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

         RuntimeException re;
         try {
            boolean allowMergeOfTransient = this.nucCtx.getConfiguration().getBooleanProperty("datanucleus.allowAttachOfTransient", false);
            if (this.getBooleanProperty("datanucleus.allowAttachOfTransient") != null) {
               allowMergeOfTransient = this.getBooleanProperty("datanucleus.allowAttachOfTransient");
            }

            if (merging && allowMergeOfTransient) {
               threadInfo.merging = true;
            }

            if (threadInfo.attachedOwnerByObject == null) {
               threadInfo.attachedOwnerByObject = new HashMap();
            }

            if (threadInfo.attachedPCById == null) {
               threadInfo.attachedPCById = new HashMap();
            }

            if (this.tx.isActive()) {
               Object var21 = this.persistObjectWork(obj);
               return var21;
            }

            threadInfo.nontxPersistDelete = true;
            boolean success = true;
            Set cachedIds = this.cache != null ? new HashSet(this.cache.keySet()) : null;

            try {
               re = (RuntimeException)this.persistObjectWork(obj);
            } catch (RuntimeException var18) {
               re = var18;
               success = false;
               if (this.cache != null) {
                  Iterator cacheIter = this.cache.keySet().iterator();

                  while(cacheIter.hasNext()) {
                     Object id = cacheIter.next();
                     if (!cachedIds.contains(id)) {
                        cacheIter.remove();
                     }
                  }
               }

               throw var18;
            } finally {
               threadInfo.nontxPersistDelete = false;
               if (success) {
                  this.processNontransactionalAtomicChanges();
               }

            }
         } finally {
            this.releaseThreadContextInfo();
         }

         return re;
      }
   }

   public Object[] persistObjects(Object[] objs) {
      if (objs == null) {
         return null;
      } else {
         Object[] persistedObjs = new Object[objs.length];
         ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

         try {
            if (threadInfo.attachedOwnerByObject == null) {
               threadInfo.attachedOwnerByObject = new HashMap();
            }

            if (threadInfo.attachedPCById == null) {
               threadInfo.attachedPCById = new HashMap();
            }

            if (!this.tx.isActive()) {
               threadInfo.nontxPersistDelete = true;
            }

            try {
               this.getStoreManager().getPersistenceHandler().batchStart(this, PersistenceBatchType.PERSIST);
               List<RuntimeException> failures = null;

               for(int i = 0; i < objs.length; ++i) {
                  try {
                     if (objs[i] != null) {
                        persistedObjs[i] = this.persistObjectWork(objs[i]);
                     }
                  } catch (RuntimeException e) {
                     if (failures == null) {
                        failures = new ArrayList();
                     }

                     failures.add(e);
                  }
               }

               if (failures != null && !failures.isEmpty()) {
                  RuntimeException e = (RuntimeException)failures.get(0);
                  if (e instanceof NucleusException && ((NucleusException)e).isFatal()) {
                     throw new NucleusFatalUserException(Localiser.msg("010039"), (Throwable[])failures.toArray(new Exception[failures.size()]));
                  }

                  throw new NucleusUserException(Localiser.msg("010039"), (Throwable[])failures.toArray(new Exception[failures.size()]));
               }
            } finally {
               this.getStoreManager().getPersistenceHandler().batchEnd(this, PersistenceBatchType.PERSIST);
               if (!this.tx.isActive()) {
                  threadInfo.nontxPersistDelete = false;
                  this.processNontransactionalAtomicChanges();
               }

            }
         } finally {
            this.releaseThreadContextInfo();
         }

         return persistedObjs;
      }
   }

   private Object persistObjectWork(Object obj) {
      boolean detached = this.getApiAdapter().isDetached(obj);
      Object persistedPc = this.persistObjectInternal(obj, (FieldValues)null, (ObjectProvider)null, -1, 0);
      ObjectProvider op = this.findObjectProvider(persistedPc);
      if (op != null) {
         if (this.indirectDirtyOPs.contains(op)) {
            this.dirtyOPs.add(op);
            this.indirectDirtyOPs.remove(op);
         } else if (!this.dirtyOPs.contains(op)) {
            this.dirtyOPs.add(op);
            if (this.l2CacheTxIds != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
               this.l2CacheTxIds.add(op.getInternalObjectId());
            }
         }

         if (this.getReachabilityAtCommit() && this.tx.isActive() && (detached || this.getApiAdapter().isNew(persistedPc))) {
            this.reachabilityPersistedIds.add(op.getInternalObjectId());
         }
      }

      return persistedPc;
   }

   public Object persistObjectInternal(Object obj, FieldValues preInsertChanges, ObjectProvider ownerOP, int ownerFieldNum, int objectType) {
      if (obj == null) {
         return null;
      } else {
         ApiAdapter api = this.getApiAdapter();
         Object id = null;

         ObjectProvider op;
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            ExecutionContext ec = api.getExecutionContext(obj);
            if (ec != null && ec != this) {
               throw new NucleusUserException(Localiser.msg("010007", obj));
            }

            boolean cacheable = false;
            T persistedPc = obj;
            if (api.isDetached(obj)) {
               this.assertDetachable(obj);
               if (this.getBooleanProperty("datanucleus.CopyOnAttach")) {
                  persistedPc = (T)this.attachObjectCopy(ownerOP, obj, api.getIdForObject(obj) == null);
               } else {
                  this.attachObject(ownerOP, obj, api.getIdForObject(obj) == null);
                  persistedPc = obj;
               }
            } else if (api.isTransactional(obj) && !api.isPersistent(obj)) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("010015", StringUtils.toJVMIDString(obj)));
               }

               op = this.findObjectProvider(obj);
               if (op == null) {
                  throw new NucleusUserException(Localiser.msg("010007", this.getApiAdapter().getIdForObject(obj)));
               }

               op.makePersistentTransactionalTransient();
            } else if (api.isPersistent(obj)) {
               if (api.isPersistent(obj) && api.getIdForObject(obj) == null) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("010015", StringUtils.toJVMIDString(obj)));
                  }

                  op = this.findObjectProvider(obj);
                  op.makePersistent();
                  id = op.getInternalObjectId();
                  cacheable = this.nucCtx.isClassCacheable(op.getClassMetaData());
               } else if (api.isDeleted(obj)) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("010015", StringUtils.toJVMIDString(obj)));
                  }

                  op = this.findObjectProvider(obj);
                  op.makePersistent();
                  id = op.getInternalObjectId();
                  cacheable = this.nucCtx.isClassCacheable(op.getClassMetaData());
               } else if (api.isPersistent(obj) && api.isTransactional(obj) && api.isDirty(obj) && this.isDelayDatastoreOperationsEnabled()) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("010015", StringUtils.toJVMIDString(obj)));
                  }

                  op = this.findObjectProvider(obj);
                  op.makePersistent();
                  id = op.getInternalObjectId();
                  cacheable = this.nucCtx.isClassCacheable(op.getClassMetaData());
               }
            } else {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("010015", StringUtils.toJVMIDString(obj)));
               }

               boolean merged = false;
               ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

               try {
                  if (threadInfo.merging) {
                     AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(obj.getClass(), this.clr);
                     if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                        Object transientId = this.nucCtx.getIdentityManager().getApplicationId(obj, cmd);
                        if (transientId != null) {
                           T existingObj = (T)this.findObject(transientId, true, true, cmd.getFullClassName());
                           ObjectProvider existingOP = this.findObjectProvider(existingObj);
                           existingOP.attach(obj);
                           id = transientId;
                           merged = true;
                           persistedPc = existingObj;
                        }
                     }

                     cacheable = this.nucCtx.isClassCacheable(cmd);
                  }
               } catch (NucleusObjectNotFoundException var25) {
               } finally {
                  this.releaseThreadContextInfo();
               }

               if (!merged) {
                  ObjectProvider<T> op = this.findObjectProvider(obj);
                  if (op == null) {
                     if ((objectType == 2 || objectType == 3 || objectType == 4 || objectType == 1) && ownerOP != null) {
                        op = this.nucCtx.getObjectProviderFactory().newForEmbedded(this, obj, false, ownerOP, ownerFieldNum);
                        op.setPcObjectType((short)objectType);
                        op.makePersistent();
                        id = op.getInternalObjectId();
                     } else {
                        op = this.nucCtx.getObjectProviderFactory().newForPersistentNew(this, obj, preInsertChanges);
                        op.makePersistent();
                        id = op.getInternalObjectId();
                     }
                  } else if (op.getReferencedPC() == null) {
                     op.makePersistent();
                     id = op.getInternalObjectId();
                  } else {
                     persistedPc = (T)op.getReferencedPC();
                  }

                  if (op != null) {
                     cacheable = this.nucCtx.isClassCacheable(op.getClassMetaData());
                  }
               }
            }

            if (id != null && this.l2CacheTxIds != null && cacheable) {
               this.l2CacheTxIds.add(id);
            }

            op = persistedPc;
         } finally {
            this.clr.unsetPrimary();
         }

         return op;
      }
   }

   public Object persistObjectInternal(Object pc, ObjectProvider ownerOP, int ownerFieldNum, int objectType) {
      if (ownerOP != null) {
         ObjectProvider op = this.findObjectProvider(ownerOP.getObject());
         return this.persistObjectInternal(pc, (FieldValues)null, op, ownerFieldNum, objectType);
      } else {
         return this.persistObjectInternal(pc, (FieldValues)null, (ObjectProvider)null, ownerFieldNum, objectType);
      }
   }

   public Object persistObjectInternal(Object pc, FieldValues preInsertChanges, int objectType) {
      return this.persistObjectInternal(pc, preInsertChanges, (ObjectProvider)null, -1, objectType);
   }

   public void deleteObjects(Object[] objs) {
      if (objs != null) {
         ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

         try {
            if (!this.tx.isActive()) {
               threadInfo.nontxPersistDelete = true;
            }

            this.getStoreManager().getPersistenceHandler().batchStart(this, PersistenceBatchType.DELETE);
            List<RuntimeException> failures = null;

            for(int i = 0; i < objs.length; ++i) {
               try {
                  if (objs[i] != null) {
                     this.deleteObjectWork(objs[i]);
                  }
               } catch (RuntimeException e) {
                  if (failures == null) {
                     failures = new ArrayList();
                  }

                  failures.add(e);
               }
            }

            if (failures != null && !failures.isEmpty()) {
               RuntimeException e = (RuntimeException)failures.get(0);
               if (e instanceof NucleusException && ((NucleusException)e).isFatal()) {
                  throw new NucleusFatalUserException(Localiser.msg("010040"), (Throwable[])failures.toArray(new Exception[failures.size()]));
               }

               throw new NucleusUserException(Localiser.msg("010040"), (Throwable[])failures.toArray(new Exception[failures.size()]));
            }
         } finally {
            this.getStoreManager().getPersistenceHandler().batchEnd(this, PersistenceBatchType.DELETE);
            if (!this.tx.isActive()) {
               threadInfo.nontxPersistDelete = false;
               this.processNontransactionalAtomicChanges();
            }

            this.releaseThreadContextInfo();
         }

      }
   }

   public void deleteObject(Object obj) {
      if (obj != null) {
         ThreadContextInfo threadInfo = this.acquireThreadContextInfo();

         try {
            if (!this.tx.isActive()) {
               threadInfo.nontxPersistDelete = true;
            }

            this.deleteObjectWork(obj);
         } finally {
            if (!this.tx.isActive()) {
               threadInfo.nontxPersistDelete = false;
               this.processNontransactionalAtomicChanges();
            }

            this.releaseThreadContextInfo();
         }

      }
   }

   void deleteObjectWork(Object obj) {
      ObjectProvider op = this.findObjectProvider(obj);
      if (op == null && this.getApiAdapter().isDetached(obj)) {
         Object attachedObj = this.findObject(this.getApiAdapter().getIdForObject(obj), true, false, obj.getClass().getName());
         op = this.findObjectProvider(attachedObj);
      }

      if (op != null) {
         if (this.indirectDirtyOPs.contains(op)) {
            this.indirectDirtyOPs.remove(op);
            this.dirtyOPs.add(op);
         } else if (!this.dirtyOPs.contains(op)) {
            this.dirtyOPs.add(op);
            if (this.l2CacheTxIds != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
               this.l2CacheTxIds.add(op.getInternalObjectId());
            }
         }
      }

      this.deleteObjectInternal(obj);
      if (this.getReachabilityAtCommit() && this.tx.isActive() && op != null && this.getApiAdapter().isDeleted(obj)) {
         this.reachabilityDeletedIds.add(op.getInternalObjectId());
      }

   }

   public void deleteObjectInternal(Object obj) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            Object pc = obj;
            if (this.getApiAdapter().isDetached(obj)) {
               pc = this.findObject(this.getApiAdapter().getIdForObject(obj), true, true, (String)null);
            }

            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("010019", StringUtils.toJVMIDString(pc)));
            }

            if (this.getApiAdapter().getName().equals("JDO")) {
               if (!this.getApiAdapter().isPersistent(pc) && !this.getApiAdapter().isTransactional(pc)) {
                  throw new NucleusUserException(Localiser.msg("010020"));
               }

               if (!this.getApiAdapter().isPersistent(pc) && this.getApiAdapter().isTransactional(pc)) {
                  throw new NucleusUserException(Localiser.msg("010021", this.getApiAdapter().getIdForObject(obj)));
               }
            }

            ObjectProvider op = this.findObjectProvider(pc);
            if (op == null) {
               if (!this.getApiAdapter().allowDeleteOfNonPersistentObject()) {
                  throw new NucleusUserException(Localiser.msg("010007", this.getApiAdapter().getIdForObject(pc)));
               }

               op = this.nucCtx.getObjectProviderFactory().newForPNewToBeDeleted(this, pc);
            }

            if (this.l2CacheTxIds != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
               this.l2CacheTxIds.add(op.getInternalObjectId());
            }

            op.deletePersistent();
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public void makeObjectTransient(Object obj, FetchPlanState state) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            this.assertNotDetached(obj);
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("010022", StringUtils.toJVMIDString(obj)));
            }

            if (this.getApiAdapter().isPersistent(obj)) {
               ObjectProvider op = this.findObjectProvider(obj);
               op.makeTransient(state);
            }
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public void makeObjectTransactional(Object obj) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            this.assertNotDetached(obj);
            if (this.getApiAdapter().isPersistent(obj)) {
               this.assertActiveTransaction();
            }

            ObjectProvider op = this.findObjectProvider(obj);
            if (op == null) {
               op = this.nucCtx.getObjectProviderFactory().newForTransactionalTransient(this, obj);
            }

            op.makeTransactional();
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public void makeObjectNontransactional(Object obj) {
      if (obj != null) {
         try {
            this.clr.setPrimary(obj.getClass().getClassLoader());
            this.assertClassPersistable(obj.getClass());
            if (!this.getApiAdapter().isPersistent(obj) && this.getApiAdapter().isTransactional(obj) && this.getApiAdapter().isDirty(obj)) {
               throw new NucleusUserException(Localiser.msg("010024"));
            }

            ObjectProvider op = this.findObjectProvider(obj);
            op.makeNontransactional();
         } finally {
            this.clr.unsetPrimary();
         }

      }
   }

   public void attachObject(ObjectProvider ownerOP, Object pc, boolean sco) {
      this.assertClassPersistable(pc.getClass());
      Map attachedOwnerByObject = this.getThreadContextInfo().attachedOwnerByObject;
      if (attachedOwnerByObject != null) {
         attachedOwnerByObject.put(pc, ownerOP);
      }

      ApiAdapter api = this.getApiAdapter();
      Object id = api.getIdForObject(pc);
      if (id == null || !this.isInserting(pc)) {
         if (id == null && !sco) {
            this.persistObjectInternal(pc, (FieldValues)null, (ObjectProvider)null, -1, 0);
         } else if (api.isDetached(pc)) {
            if (this.cache != null) {
               ObjectProvider l1CachedOP = (ObjectProvider)this.cache.get(id);
               if (l1CachedOP != null && l1CachedOP.getObject() != pc) {
                  throw new NucleusUserException(Localiser.msg("010017", StringUtils.toJVMIDString(pc)));
               }
            }

            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("010016", StringUtils.toJVMIDString(pc)));
            }

            ObjectProvider op = this.nucCtx.getObjectProviderFactory().newForDetached(this, pc, id, api.getVersionForObject(pc));
            op.attach(sco);
         }
      }
   }

   public Object attachObjectCopy(ObjectProvider ownerOP, Object pc, boolean sco) {
      this.assertClassPersistable(pc.getClass());
      this.assertDetachable(pc);
      Map attachedOwnerByObject = this.getThreadContextInfo().attachedOwnerByObject;
      if (attachedOwnerByObject != null) {
         attachedOwnerByObject.put(pc, ownerOP);
      }

      ApiAdapter api = this.getApiAdapter();
      Object id = api.getIdForObject(pc);
      if (id != null && this.isInserting(pc)) {
         return pc;
      } else if (id == null && !sco) {
         return this.persistObjectInternal(pc, (FieldValues)null, (ObjectProvider)null, -1, 0);
      } else if (api.isPersistent(pc)) {
         return pc;
      } else {
         T pcTarget = (T)null;
         if (sco) {
            boolean detached = this.getApiAdapter().isDetached(pc);
            ObjectProvider<T> targetOP = this.nucCtx.getObjectProviderFactory().newForEmbedded(this, pc, true, (ObjectProvider)null, -1);
            pcTarget = (T)targetOP.getObject();
            if (detached) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("010018", StringUtils.toJVMIDString(pc), StringUtils.toJVMIDString(pcTarget)));
               }

               targetOP.attachCopy(pc, sco);
            }
         } else {
            boolean detached = this.getApiAdapter().isDetached(pc);
            pcTarget = (T)this.findObject(id, false, false, pc.getClass().getName());
            if (detached) {
               T obj = (T)null;
               Map attachedPCById = this.getThreadContextInfo().attachedPCById;
               if (attachedPCById != null) {
                  obj = (T)attachedPCById.get(this.getApiAdapter().getIdForObject(pc));
               }

               if (obj != null) {
                  pcTarget = obj;
               } else {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("010018", StringUtils.toJVMIDString(pc), StringUtils.toJVMIDString(pcTarget)));
                  }

                  pcTarget = (T)this.findObjectProvider(pcTarget).attachCopy(pc, sco);
                  if (attachedPCById != null) {
                     attachedPCById.put(this.getApiAdapter().getIdForObject(pc), pcTarget);
                  }
               }
            }
         }

         return pcTarget;
      }
   }

   public void detachObject(Object obj, FetchPlanState state) {
      if (!this.getApiAdapter().isDetached(obj)) {
         if (!this.getApiAdapter().isPersistent(obj)) {
            if (this.runningDetachAllOnTxnEnd && !this.getMetaDataManager().getMetaDataForClass(obj.getClass(), this.clr).isDetachable()) {
               return;
            }

            if (this.tx.isActive()) {
               this.persistObjectInternal(obj, (FieldValues)null, (ObjectProvider)null, -1, 0);
            }
         }

         ObjectProvider op = this.findObjectProvider(obj);
         if (op == null) {
            throw new NucleusUserException(Localiser.msg("010007", this.getApiAdapter().getIdForObject(obj)));
         } else {
            op.detach(state);
            if (this.dirtyOPs.contains(op) || this.indirectDirtyOPs.contains(op)) {
               NucleusLogger.GENERAL.info(Localiser.msg("010047", StringUtils.toJVMIDString(obj)));
               this.clearDirty(op);
            }

         }
      }
   }

   public Object detachObjectCopy(Object pc, FetchPlanState state) {
      T thePC = pc;

      Object var5;
      try {
         this.clr.setPrimary(pc.getClass().getClassLoader());
         if (!this.getApiAdapter().isPersistent(pc) && !this.getApiAdapter().isDetached(pc)) {
            if (!this.tx.isActive()) {
               throw new NucleusUserException(Localiser.msg("010014"));
            }

            thePC = (T)this.persistObjectInternal(pc, (FieldValues)null, (ObjectProvider)null, -1, 0);
         }

         if (this.getApiAdapter().isDetached(thePC)) {
            thePC = (T)this.findObject(this.getApiAdapter().getIdForObject(thePC), false, true, (String)null);
         }

         ObjectProvider<T> op = this.findObjectProvider(thePC);
         if (op == null) {
            throw new NucleusUserException(Localiser.msg("010007", this.getApiAdapter().getIdForObject(thePC)));
         }

         var5 = op.detachCopy(state);
      } finally {
         this.clr.unsetPrimary();
      }

      return var5;
   }

   public void detachAll() {
      Collection<ObjectProvider> opsToDetach = new HashSet(this.enlistedOPCache.values());
      if (this.cache != null) {
         opsToDetach.addAll(this.cache.values());
      }

      FetchPlanState fps = new FetchPlanState();
      Iterator<ObjectProvider> iter = opsToDetach.iterator();

      while(iter.hasNext()) {
         ((ObjectProvider)iter.next()).detach(fps);
      }

   }

   public Object getAttachDetachReferencedObject(ObjectProvider op) {
      return this.opAttachDetachObjectReferenceMap == null ? null : this.opAttachDetachObjectReferenceMap.get(op);
   }

   public void setAttachDetachReferencedObject(ObjectProvider op, Object obj) {
      if (obj != null) {
         if (this.opAttachDetachObjectReferenceMap == null) {
            this.opAttachDetachObjectReferenceMap = new HashMap();
         }

         this.opAttachDetachObjectReferenceMap.put(op, obj);
      } else if (this.opAttachDetachObjectReferenceMap != null) {
         this.opAttachDetachObjectReferenceMap.remove(op);
      }

   }

   public Object newInstance(Class cls) {
      if (this.getApiAdapter().isPersistable(cls) && !Modifier.isAbstract(cls.getModifiers())) {
         try {
            return cls.newInstance();
         } catch (IllegalAccessException iae) {
            throw new NucleusUserException(iae.toString(), iae);
         } catch (InstantiationException ie) {
            throw new NucleusUserException(ie.toString(), ie);
         }
      } else {
         this.assertHasImplementationCreator();
         return this.getNucleusContext().getImplementationCreator().newInstance(cls, this.clr);
      }
   }

   public boolean exists(Object obj) {
      if (obj == null) {
         return false;
      } else {
         Object id = this.getApiAdapter().getIdForObject(obj);
         if (id == null) {
            return false;
         } else {
            try {
               this.findObject(id, true, false, obj.getClass().getName());
               return true;
            } catch (NucleusObjectNotFoundException var4) {
               return false;
            }
         }
      }
   }

   public Set getManagedObjects() {
      if (!this.tx.isActive()) {
         return null;
      } else {
         Set objs = new HashSet();

         for(ObjectProvider op : this.enlistedOPCache.values()) {
            objs.add(op.getObject());
         }

         return objs;
      }
   }

   public Set getManagedObjects(Class[] classes) {
      if (!this.tx.isActive()) {
         return null;
      } else {
         Set objs = new HashSet();

         for(ObjectProvider op : this.enlistedOPCache.values()) {
            for(int i = 0; i < classes.length; ++i) {
               if (classes[i] == op.getObject().getClass()) {
                  objs.add(op.getObject());
                  break;
               }
            }
         }

         return objs;
      }
   }

   public Set getManagedObjects(String[] states) {
      if (!this.tx.isActive()) {
         return null;
      } else {
         Set objs = new HashSet();

         for(ObjectProvider op : this.enlistedOPCache.values()) {
            for(int i = 0; i < states.length; ++i) {
               if (this.getApiAdapter().getObjectState(op.getObject()).equals(states[i])) {
                  objs.add(op.getObject());
                  break;
               }
            }
         }

         return objs;
      }
   }

   public Set getManagedObjects(String[] states, Class[] classes) {
      if (!this.tx.isActive()) {
         return null;
      } else {
         Set objs = new HashSet();

         for(ObjectProvider op : this.enlistedOPCache.values()) {
            boolean matches = false;

            for(int i = 0; i < states.length; ++i) {
               if (this.getApiAdapter().getObjectState(op.getObject()).equals(states[i])) {
                  for(int j = 0; j < classes.length; ++j) {
                     if (classes[j] == op.getObject().getClass()) {
                        matches = true;
                        objs.add(op.getObject());
                        break;
                     }
                  }
               }

               if (matches) {
                  break;
               }
            }
         }

         return objs;
      }
   }

   public Object findObject(Class cls, Object key) {
      if (cls != null && key != null) {
         AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(cls, this.clr);
         if (cmd == null) {
            throw new NucleusUserException(Localiser.msg("010052", cls.getName()));
         } else {
            Object id = key;
            if (cmd.getIdentityType() == IdentityType.DATASTORE) {
               if (!IdentityUtils.isDatastoreIdentity(key)) {
                  id = this.nucCtx.getIdentityManager().getDatastoreId(cmd.getFullClassName(), key);
               }
            } else if (!cmd.getObjectidClass().equals(key.getClass().getName())) {
               try {
                  id = this.newObjectId(cls, key);
               } catch (NucleusException ne) {
                  throw new IllegalArgumentException(ne);
               }
            }

            return this.findObject(id, true, true, (String)null);
         }
      } else {
         throw new NucleusUserException(Localiser.msg("010051", cls, key));
      }
   }

   public Object findObject(Object id, boolean validate) {
      return this.findObject(id, validate, validate, (String)null);
   }

   public Object findObject(Object id, FieldValues fv, Class cls, boolean ignoreCache, boolean checkInheritance) {
      this.assertIsOpen();
      boolean createdHollow = false;
      Object pc = null;
      ObjectProvider op = null;
      if (!ignoreCache) {
         pc = this.getObjectFromCache(id);
      }

      if (pc == null) {
         pc = this.getStoreManager().getPersistenceHandler().findObject(this, id);
      }

      if (pc == null) {
         String className = cls != null ? cls.getName() : null;
         if (!(id instanceof SCOID)) {
            ClassDetailsForId details = this.getClassDetailsForId(id, className, checkInheritance);
            if (details.className != null && cls != null && !cls.getName().equals(details.className)) {
               cls = this.clr.classForName(details.className);
            }

            className = details.className;
            id = details.id;
            if (details.pc != null) {
               pc = details.pc;
               op = this.findObjectProvider(pc);
            }
         }

         if (pc == null) {
            if (cls == null) {
               try {
                  cls = this.clr.classForName(className, id.getClass().getClassLoader());
               } catch (ClassNotResolvedException e) {
                  String msg = Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id));
                  NucleusLogger.PERSISTENCE.warn(msg);
                  throw new NucleusUserException(msg, e);
               }
            }

            createdHollow = true;
            op = this.nucCtx.getObjectProviderFactory().newForHollow(this, cls, id, fv);
            pc = op.getObject();
            this.putObjectIntoLevel1Cache(op);
            this.putObjectIntoLevel2Cache(op, false);
         }
      }

      if (pc != null && fv != null && !createdHollow) {
         if (op == null) {
            op = this.findObjectProvider(pc);
         }

         if (op != null) {
            fv.fetchNonLoadedFields(op);
         }
      }

      return pc;
   }

   public Object[] findObjects(Object[] identities, boolean validate) {
      if (identities == null) {
         return null;
      } else if (identities.length == 1) {
         return new Object[]{this.findObject(identities[0], validate, validate, (String)null)};
      } else {
         for(int i = 0; i < identities.length; ++i) {
            if (identities[i] == null) {
               throw new NucleusUserException(Localiser.msg("010044"));
            }
         }

         Object[] ids = new Object[identities.length];

         for(int i = 0; i < identities.length; ++i) {
            IdentityStringTranslator idStringTranslator = this.getNucleusContext().getIdentityManager().getIdentityStringTranslator();
            if (idStringTranslator != null && identities[i] instanceof String) {
               ids[i] = idStringTranslator.getIdentity(this, (String)identities[i]);
            } else {
               ids[i] = identities[i];
            }
         }

         Map pcById = new HashMap(identities.length);
         List idsToFind = new ArrayList();
         ApiAdapter api = this.getApiAdapter();

         for(int i = 0; i < ids.length; ++i) {
            Object pc = this.getObjectFromLevel1Cache(ids[i]);
            if (pc != null) {
               if (ids[i] instanceof SCOID && api.isPersistent(pc) && !api.isNew(pc) && !api.isDeleted(pc) && !api.isTransactional(pc)) {
                  throw new NucleusUserException(Localiser.msg("010005"));
               }

               pcById.put(ids[i], pc);
            } else {
               idsToFind.add(ids[i]);
            }
         }

         if (!idsToFind.isEmpty() && this.l2CacheEnabled) {
            Map pcsById = this.getObjectsFromLevel2Cache(idsToFind);
            if (!pcsById.isEmpty()) {
               for(Map.Entry entry : pcsById.entrySet()) {
                  pcById.put(entry.getKey(), entry.getValue());
                  idsToFind.remove(entry.getKey());
               }
            }
         }

         boolean performValidationWhenCached = this.nucCtx.getConfiguration().getBooleanProperty("datanucleus.findObject.validateWhenCached");
         List<ObjectProvider> opsToValidate = new ArrayList();
         if (validate && performValidationWhenCached) {
            for(Object pc : pcById.values()) {
               if (!api.isTransactional(pc)) {
                  ObjectProvider op = this.findObjectProvider(pc);
                  opsToValidate.add(op);
               }
            }
         }

         Object[] foundPcs = null;
         if (!idsToFind.isEmpty()) {
            foundPcs = this.getStoreManager().getPersistenceHandler().findObjects(this, idsToFind.toArray());
         }

         int foundPcIdx = 0;

         for(Object id : idsToFind) {
            Object pc = foundPcs[foundPcIdx++];
            ObjectProvider op = null;
            if (pc != null) {
               op = this.findObjectProvider(pc);
               this.putObjectIntoLevel1Cache(op);
            } else {
               ClassDetailsForId details = this.getClassDetailsForId(id, (String)null, validate);
               String className = details.className;
               id = details.id;
               if (details.pc != null) {
                  pc = details.pc;
                  op = this.findObjectProvider(pc);
                  if (performValidationWhenCached && validate && !api.isTransactional(pc)) {
                     opsToValidate.add(op);
                  }
               } else {
                  try {
                     Class pcClass = this.clr.classForName(className, id instanceof DatastoreId ? null : id.getClass().getClassLoader());
                     if (Modifier.isAbstract(pcClass.getModifiers())) {
                        throw new NucleusObjectNotFoundException(Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id), className));
                     }

                     op = this.nucCtx.getObjectProviderFactory().newForHollow(this, pcClass, id);
                     pc = op.getObject();
                     if (!validate) {
                        op.markForInheritanceValidation();
                     }

                     this.putObjectIntoLevel1Cache(op);
                  } catch (ClassNotResolvedException e) {
                     NucleusLogger.PERSISTENCE.warn(Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id)));
                     throw new NucleusUserException(Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id)), e);
                  }

                  if (validate) {
                     opsToValidate.add(op);
                  }
               }
            }

            pcById.put(id, pc);
         }

         if (!opsToValidate.isEmpty()) {
            try {
               this.getStoreManager().getPersistenceHandler().locateObjects((ObjectProvider[])opsToValidate.toArray(new ObjectProvider[opsToValidate.size()]));
            } catch (NucleusObjectNotFoundException nonfe) {
               NucleusObjectNotFoundException[] nonfes = (NucleusObjectNotFoundException[])nonfe.getNestedExceptions();
               if (nonfes != null) {
                  for(int i = 0; i < nonfes.length; ++i) {
                     Object missingId = nonfes[i].getFailedObject();
                     this.removeObjectFromLevel1Cache(missingId);
                  }
               }

               throw nonfe;
            }
         }

         Object[] objs = new Object[ids.length];

         for(int i = 0; i < ids.length; ++i) {
            Object id = ids[i];
            objs[i] = pcById.get(id);
         }

         return objs;
      }
   }

   private ClassDetailsForId getClassDetailsForId(Object id, String objectClassName, boolean checkInheritance) {
      String className = null;
      String originalClassName = null;
      boolean checkedClassName = false;
      if (id instanceof SCOID) {
         throw new NucleusUserException(Localiser.msg("010006"));
      } else if (id instanceof DatastoreUniqueLongId) {
         throw new NucleusObjectNotFoundException(Localiser.msg("010026"), id);
      } else {
         if (objectClassName != null) {
            originalClassName = objectClassName;
         } else if (!IdentityUtils.isDatastoreIdentity(id) && !IdentityUtils.isSingleFieldIdentity(id)) {
            originalClassName = this.getClassNameForObjectId(id);
            checkedClassName = true;
         } else {
            originalClassName = this.getStoreManager().manageClassForIdentity(id, this.clr);
         }

         Object pc = null;
         if (checkInheritance) {
            className = checkedClassName ? originalClassName : this.getClassNameForObjectId(id);
            if (className == null) {
               throw new NucleusObjectNotFoundException(Localiser.msg("010026"), id);
            }

            if (!checkedClassName && (IdentityUtils.isDatastoreIdentity(id) || IdentityUtils.isSingleFieldIdentity(id))) {
               String[] subclasses = this.getMetaDataManager().getSubclassesForClass(className, true);
               if (subclasses != null) {
                  for(int i = 0; i < subclasses.length; ++i) {
                     Object oid = null;
                     if (IdentityUtils.isDatastoreIdentity(id)) {
                        oid = this.nucCtx.getIdentityManager().getDatastoreId(subclasses[i], IdentityUtils.getTargetKeyForDatastoreIdentity(id));
                     } else if (IdentityUtils.isSingleFieldIdentity(id)) {
                        oid = this.nucCtx.getIdentityManager().getSingleFieldId(id.getClass(), this.getClassLoaderResolver().classForName(subclasses[i]), IdentityUtils.getTargetKeyForSingleFieldIdentity(id));
                     }

                     pc = this.getObjectFromCache(oid);
                     if (pc != null) {
                        className = subclasses[i];
                        break;
                     }
                  }
               }
            }

            if (pc == null && originalClassName != null && !originalClassName.equals(className)) {
               if (IdentityUtils.isDatastoreIdentity(id)) {
                  id = this.nucCtx.getIdentityManager().getDatastoreId(className, ((DatastoreId)id).getKeyAsObject());
                  pc = this.getObjectFromCache(id);
               } else if (IdentityUtils.isSingleFieldIdentity(id)) {
                  id = this.nucCtx.getIdentityManager().getSingleFieldId(id.getClass(), this.clr.classForName(className), IdentityUtils.getTargetKeyForSingleFieldIdentity(id));
                  pc = this.getObjectFromCache(id);
               }
            }
         } else {
            className = originalClassName;
         }

         return new ClassDetailsForId(id, className, pc);
      }
   }

   protected String getClassNameForObjectId(Object id) {
      String className = null;
      if (!IdentityUtils.isDatastoreIdentity(id) && !IdentityUtils.isSingleFieldIdentity(id)) {
         Collection<AbstractClassMetaData> cmds = this.getMetaDataManager().getClassMetaDataWithApplicationId(id.getClass().getName());
         if (cmds != null && cmds.size() == 1) {
            return ((AbstractClassMetaData)cmds.iterator().next()).getFullClassName();
         }
      } else {
         className = this.getStoreManager().manageClassForIdentity(id, this.clr);
      }

      if (className != null) {
         String[] subclasses = this.getMetaDataManager().getConcreteSubclassesForClass(className);
         int numConcrete = 0;
         String concreteClassName = null;
         if (subclasses != null && subclasses.length > 0) {
            numConcrete = subclasses.length;
            concreteClassName = subclasses[0];
         }

         Class rootCls = this.clr.classForName(className);
         if (!Modifier.isAbstract(rootCls.getModifiers())) {
            concreteClassName = className;
            ++numConcrete;
         }

         if (numConcrete == 1) {
            return concreteClassName;
         }
      }

      return this.getStoreManager().getClassNameForObjectID(id, this.clr, this);
   }

   public Object findObject(Object id, boolean validate, boolean checkInheritance, String objectClassName) {
      if (id == null) {
         throw new NucleusUserException(Localiser.msg("010044"));
      } else {
         IdentityStringTranslator translator = this.getNucleusContext().getIdentityManager().getIdentityStringTranslator();
         if (translator != null && id instanceof String) {
            id = translator.getIdentity(this, (String)id);
         }

         ApiAdapter api = this.getApiAdapter();
         boolean fromCache = false;
         Object pc = this.getObjectFromCache(id);
         ObjectProvider op = null;
         if (pc != null) {
            fromCache = true;
            if (id instanceof SCOID && api.isPersistent(pc) && !api.isNew(pc) && !api.isDeleted(pc) && !api.isTransactional(pc)) {
               throw new NucleusUserException(Localiser.msg("010005"));
            }

            if (api.isTransactional(pc)) {
               return pc;
            }

            op = this.findObjectProvider(pc);
         } else {
            pc = this.getStoreManager().getPersistenceHandler().findObject(this, id);
            if (pc != null) {
               op = this.findObjectProvider(pc);
               this.putObjectIntoLevel1Cache(op);
               this.putObjectIntoLevel2Cache(op, false);
            } else {
               ClassDetailsForId details = this.getClassDetailsForId(id, objectClassName, checkInheritance);
               String className = details.className;
               id = details.id;
               if (details.pc != null) {
                  pc = details.pc;
                  op = this.findObjectProvider(pc);
                  fromCache = true;
               } else {
                  try {
                     Class pcClass = this.clr.classForName(className, id instanceof DatastoreId ? null : id.getClass().getClassLoader());
                     if (Modifier.isAbstract(pcClass.getModifiers())) {
                        throw new NucleusObjectNotFoundException(Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id), className));
                     }

                     op = this.nucCtx.getObjectProviderFactory().newForHollow(this, pcClass, id);
                     pc = op.getObject();
                     if (!checkInheritance && !validate) {
                        op.markForInheritanceValidation();
                     }

                     this.putObjectIntoLevel1Cache(op);
                  } catch (ClassNotResolvedException e) {
                     NucleusLogger.PERSISTENCE.warn(Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id)));
                     throw new NucleusUserException(Localiser.msg("010027", IdentityUtils.getPersistableIdentityForId(id)), e);
                  }
               }
            }
         }

         boolean performValidationWhenCached = this.nucCtx.getConfiguration().getBooleanProperty("datanucleus.findObject.validateWhenCached");
         if (validate && (!fromCache || performValidationWhenCached)) {
            if (!fromCache) {
               this.putObjectIntoLevel1Cache(op);
            }

            try {
               op.validate();
               if (op.getObject() != pc) {
                  fromCache = false;
                  pc = op.getObject();
                  this.putObjectIntoLevel1Cache(op);
               }
            } catch (NucleusObjectNotFoundException onfe) {
               this.removeObjectFromLevel1Cache(op.getInternalObjectId());
               throw onfe;
            }
         }

         if (!fromCache) {
            this.putObjectIntoLevel2Cache(op, false);
         }

         return pc;
      }
   }

   public Object newObjectId(Class pcClass, Object key) {
      if (pcClass == null) {
         throw new NucleusUserException(Localiser.msg("010028"));
      } else {
         this.assertClassPersistable(pcClass);
         AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(pcClass, this.clr);
         if (cmd == null) {
            throw new NoPersistenceInformationException(pcClass.getName());
         } else {
            if (!this.getStoreManager().managesClass(cmd.getFullClassName())) {
               this.getStoreManager().manageClasses(this.clr, cmd.getFullClassName());
            }

            IdentityKeyTranslator translator = this.getNucleusContext().getIdentityManager().getIdentityKeyTranslator();
            if (translator != null) {
               key = translator.getKey(this, pcClass, key);
            }

            Object id = null;
            if (cmd.usesSingleFieldIdentityClass()) {
               if (this.getBooleanProperty("datanucleus.findObject.typeConversion") && translator == null && !key.getClass().getName().equals(cmd.getObjectidClass())) {
                  AbstractMemberMetaData mmd = cmd.getMetaDataForMember(cmd.getPrimaryKeyMemberNames()[0]);
                  if (!mmd.getType().isAssignableFrom(key.getClass())) {
                     Object convKey = TypeConversionHelper.convertTo(key, mmd.getType());
                     if (convKey != null) {
                        key = convKey;
                     }
                  }
               }

               id = this.nucCtx.getIdentityManager().getSingleFieldId(this.clr.classForName(cmd.getObjectidClass()), pcClass, key);
            } else {
               if (!(key instanceof String)) {
                  throw new NucleusUserException(Localiser.msg("010029", pcClass.getName(), key.getClass().getName()));
               }

               if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                  if (Modifier.isAbstract(pcClass.getModifiers()) && cmd.getObjectidClass() != null) {
                     try {
                        Constructor c = this.clr.classForName(cmd.getObjectidClass()).getDeclaredConstructor(String.class);
                        id = c.newInstance((String)key);
                     } catch (Exception e) {
                        String msg = Localiser.msg("010030", cmd.getObjectidClass(), cmd.getFullClassName());
                        NucleusLogger.PERSISTENCE.error(msg, e);
                        throw new NucleusUserException(msg);
                     }
                  } else {
                     this.clr.classForName(pcClass.getName(), true);
                     id = this.nucCtx.getIdentityManager().getApplicationId(pcClass, key);
                  }
               } else {
                  id = this.nucCtx.getIdentityManager().getDatastoreId((String)key);
               }
            }

            return id;
         }
      }
   }

   public Object newObjectId(String className, Object pc) {
      AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, this.clr);
      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         return this.nucCtx.getIdentityManager().getDatastoreId(cmd.getFullClassName(), this.getStoreManager().getStrategyValue(this, cmd, -1));
      } else {
         return cmd.getIdentityType() == IdentityType.APPLICATION ? this.nucCtx.getIdentityManager().getApplicationId(pc, cmd) : new SCOID(className);
      }
   }

   public void clearDirty(ObjectProvider op) {
      this.dirtyOPs.remove(op);
      this.indirectDirtyOPs.remove(op);
   }

   public void clearDirty() {
      this.dirtyOPs.clear();
      this.indirectDirtyOPs.clear();
   }

   public void markDirty(ObjectProvider op, boolean directUpdate) {
      if (this.tx.isCommitting() && !this.tx.isActive()) {
         throw new NucleusException("Cannot change objects when transaction is no longer active.");
      } else {
         boolean isInDirty = this.dirtyOPs.contains(op);
         boolean isInIndirectDirty = this.indirectDirtyOPs.contains(op);
         if (!this.isDelayDatastoreOperationsEnabled() && !isInDirty && !isInIndirectDirty && this.dirtyOPs.size() >= this.getNucleusContext().getConfiguration().getIntProperty("datanucleus.datastoreTransactionFlushLimit")) {
            this.flushInternal(false);
         }

         if (directUpdate) {
            if (isInIndirectDirty) {
               this.indirectDirtyOPs.remove(op);
               this.dirtyOPs.add(op);
            } else if (!isInDirty) {
               this.dirtyOPs.add(op);
               if (this.l2CacheTxIds != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
                  this.l2CacheTxIds.add(op.getInternalObjectId());
               }
            }
         } else if (!isInDirty && !isInIndirectDirty) {
            this.indirectDirtyOPs.add(op);
            if (this.l2CacheTxIds != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
               this.l2CacheTxIds.add(op.getInternalObjectId());
            }
         }

      }
   }

   public boolean getManageRelations() {
      return this.properties.getBooleanProperty("datanucleus.manageRelationships");
   }

   public boolean getManageRelationsChecks() {
      return this.properties.getBooleanProperty("datanucleus.manageRelationshipsChecks");
   }

   public RelationshipManager getRelationshipManager(ObjectProvider op) {
      if (!this.getManageRelations()) {
         return null;
      } else {
         if (this.managedRelationDetails == null) {
            this.managedRelationDetails = new ConcurrentHashMap();
         }

         RelationshipManager relMgr = (RelationshipManager)this.managedRelationDetails.get(op);
         if (relMgr == null) {
            relMgr = new RelationshipManagerImpl(op);
            this.managedRelationDetails.put(op, relMgr);
         }

         return relMgr;
      }
   }

   public boolean isManagingRelations() {
      return this.runningManageRelations;
   }

   protected void performManagedRelationships() {
      if (this.getManageRelations() && this.managedRelationDetails != null && !this.managedRelationDetails.isEmpty()) {
         try {
            this.runningManageRelations = true;
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("013000"));
            }

            if (this.getManageRelationsChecks()) {
               for(ObjectProvider op : this.managedRelationDetails.keySet()) {
                  LifeCycleState lc = op.getLifecycleState();
                  if (lc != null && !lc.isDeleted()) {
                     RelationshipManager relMgr = (RelationshipManager)this.managedRelationDetails.get(op);
                     relMgr.checkConsistency();
                  }
               }
            }

            for(ObjectProvider op : this.managedRelationDetails.keySet()) {
               LifeCycleState lc = op.getLifecycleState();
               if (lc != null && !lc.isDeleted()) {
                  RelationshipManager relMgr = (RelationshipManager)this.managedRelationDetails.get(op);
                  relMgr.process();
                  relMgr.clearFields();
               }
            }

            this.managedRelationDetails.clear();
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("013001"));
            }
         } finally {
            this.runningManageRelations = false;
         }
      }

   }

   public List getObjectsToBeFlushed() {
      List<ObjectProvider> ops = new ArrayList();

      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         ops.addAll(this.dirtyOPs);
         ops.addAll(this.indirectDirtyOPs);
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

      return ops;
   }

   public boolean isFlushing() {
      return this.flushing > 0;
   }

   public void flush() {
      if (this.tx.isActive()) {
         this.performManagedRelationships();
         this.flushInternal(true);
         if (!this.dirtyOPs.isEmpty() || !this.indirectDirtyOPs.isEmpty()) {
            NucleusLogger.PERSISTENCE.debug("Flush pass 1 resulted in " + (this.dirtyOPs.size() + this.indirectDirtyOPs.size()) + " additional objects being made dirty. Performing flush pass 2");
            this.flushInternal(true);
         }

         if (this.operationQueue != null && !this.operationQueue.getOperations().isEmpty()) {
            NucleusLogger.PERSISTENCE.warn("Queue of operations after flush() is not empty! Generate a testcase and report this. See below (debug) for full details of unflushed ops");
            this.operationQueue.log();
         }
      }

   }

   public void flushInternal(boolean flushToDatastore) {
      if (flushToDatastore || !this.dirtyOPs.isEmpty() || !this.indirectDirtyOPs.isEmpty()) {
         if (!this.tx.isActive()) {
            if (this.nontxProcessedOPs == null) {
               this.nontxProcessedOPs = new HashSet();
            }

            this.nontxProcessedOPs.addAll(this.dirtyOPs);
            this.nontxProcessedOPs.addAll(this.indirectDirtyOPs);
         }

         ++this.flushing;

         try {
            if (flushToDatastore) {
               this.tx.preFlush();
            }

            FlushProcess flusher = this.getStoreManager().getFlushProcess();
            List<NucleusOptimisticException> optimisticFailures = flusher.execute(this, this.dirtyOPs, this.indirectDirtyOPs, this.operationQueue);
            if (flushToDatastore) {
               this.tx.flush();
            }

            if (optimisticFailures != null) {
               throw new NucleusOptimisticException(Localiser.msg("010031"), (Throwable[])optimisticFailures.toArray(new Throwable[optimisticFailures.size()]));
            }
         } finally {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("010004"));
            }

            --this.flushing;
         }

      }
   }

   public OperationQueue getOperationQueue() {
      return this.operationQueue;
   }

   public void addOperationToQueue(Operation oper) {
      if (this.operationQueue == null) {
         this.operationQueue = new OperationQueue();
      }

      this.operationQueue.enqueue(oper);
   }

   public void flushOperationsForBackingStore(Store backingStore, ObjectProvider op) {
      if (this.operationQueue != null) {
         this.operationQueue.performAll(backingStore, op);
      }

   }

   public boolean operationQueueIsActive() {
      return this.isDelayDatastoreOperationsEnabled() && !this.isFlushing() && this.getTransaction().isActive();
   }

   public void postBegin() {
      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         ObjectProvider[] ops = (ObjectProvider[])this.dirtyOPs.toArray(new ObjectProvider[this.dirtyOPs.size()]);

         for(int i = 0; i < ops.length; ++i) {
            ops[i].preBegin(this.tx);
         }

         ops = (ObjectProvider[])this.indirectDirtyOPs.toArray(new ObjectProvider[this.indirectDirtyOPs.size()]);

         for(int i = 0; i < ops.length; ++i) {
            ops[i].preBegin(this.tx);
         }
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

   }

   public void preCommit() {
      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         this.flush();
         if (this.getReachabilityAtCommit()) {
            try {
               this.runningPBRAtCommit = true;
               this.performReachabilityAtCommit();
            } catch (Throwable t) {
               NucleusLogger.PERSISTENCE.error(t);
               if (t instanceof NucleusException) {
                  throw (NucleusException)t;
               }

               throw new NucleusException("Unexpected error during precommit", t);
            } finally {
               this.runningPBRAtCommit = false;
            }
         }

         if (this.l2CacheEnabled) {
            this.performLevel2CacheUpdateAtCommit();
         }

         if (this.properties.getFrequentProperties().getDetachAllOnCommit()) {
            this.performDetachAllOnTxnEndPreparation();
         }
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

   }

   public boolean isObjectModifiedInTransaction(Object id) {
      return this.l2CacheTxIds != null ? this.l2CacheTxIds.contains(id) : false;
   }

   public void markFieldsForUpdateInLevel2Cache(Object id, boolean[] fields) {
      if (this.l2CacheTxFieldsToUpdateById != null) {
         BitSet bits = (BitSet)this.l2CacheTxFieldsToUpdateById.get(id);
         if (bits == null) {
            bits = new BitSet();
            this.l2CacheTxFieldsToUpdateById.put(id, bits);
         }

         for(int i = 0; i < fields.length; ++i) {
            if (fields[i]) {
               bits.set(i);
            }
         }

      }
   }

   private void performLevel2CacheUpdateAtCommit() {
      if (this.l2CacheTxIds != null) {
         String cacheStoreMode = this.getLevel2CacheStoreMode();
         if (!"bypass".equalsIgnoreCase(cacheStoreMode)) {
            Set<ObjectProvider> opsToCache = null;
            Set<Object> idsToRemove = null;

            for(Object id : this.l2CacheTxIds) {
               ObjectProvider op = (ObjectProvider)this.enlistedOPCache.get(id);
               if (op == null) {
                  if (NucleusLogger.CACHE.isDebugEnabled() && this.nucCtx.getLevel2Cache().containsOid(id)) {
                     NucleusLogger.CACHE.debug(Localiser.msg("004014", id));
                  }

                  if (idsToRemove == null) {
                     idsToRemove = new HashSet();
                  }

                  idsToRemove.add(id);
               } else {
                  Object obj = op.getObject();
                  Object objID = this.getApiAdapter().getIdForObject(obj);
                  if (objID != null && !(objID instanceof IdentityReference)) {
                     if (this.getApiAdapter().isDeleted(obj)) {
                        if (NucleusLogger.CACHE.isDebugEnabled()) {
                           NucleusLogger.CACHE.debug(Localiser.msg("004007", StringUtils.toJVMIDString(obj), op.getInternalObjectId()));
                        }

                        if (idsToRemove == null) {
                           idsToRemove = new HashSet();
                        }

                        idsToRemove.add(objID);
                     } else if (!this.getApiAdapter().isDetached(obj)) {
                        if (opsToCache == null) {
                           opsToCache = new HashSet();
                        }

                        opsToCache.add(op);
                        if (this.objectsToEvictUponRollback == null) {
                           this.objectsToEvictUponRollback = new LinkedList();
                        }

                        this.objectsToEvictUponRollback.add(id);
                     }
                  }
               }
            }

            if (idsToRemove != null && !idsToRemove.isEmpty()) {
               this.nucCtx.getLevel2Cache().evictAll((Collection)idsToRemove);
            }

            if (opsToCache != null && !opsToCache.isEmpty()) {
               this.putObjectsIntoLevel2Cache(opsToCache);
            }

            this.l2CacheTxIds.clear();
            this.l2CacheTxFieldsToUpdateById.clear();
         }
      }
   }

   private void performReachabilityAtCommit() {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("010032"));
      }

      if (!this.reachabilityPersistedIds.isEmpty() && !this.reachabilityFlushedNewIds.isEmpty()) {
         Set currentReachables = new HashSet();
         Object[] ids = this.reachabilityPersistedIds.toArray();
         Set objectNotFound = new HashSet();

         for(int i = 0; i < ids.length; ++i) {
            if (!this.reachabilityDeletedIds.contains(ids[i])) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug("Performing reachability algorithm on object with id \"" + ids[i] + "\"");
               }

               try {
                  ObjectProvider op = this.findObjectProvider(this.findObject(ids[i], true, true, (String)null));
                  if (!op.isDeleted() && !currentReachables.contains(ids[i])) {
                     op.loadUnloadedRelationFields();
                     if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug(Localiser.msg("007000", StringUtils.toJVMIDString(op.getObject()), ids[i], op.getLifecycleState()));
                     }

                     currentReachables.add(ids[i]);
                     ReachabilityFieldManager pcFM = new ReachabilityFieldManager(op, currentReachables);
                     int[] relationFieldNums = op.getClassMetaData().getRelationMemberPositions(this.getClassLoaderResolver(), this.getMetaDataManager());
                     if (relationFieldNums != null && relationFieldNums.length > 0) {
                        op.provideFields(relationFieldNums, pcFM);
                     }
                  }
               } catch (NucleusObjectNotFoundException var10) {
                  objectNotFound.add(ids[i]);
               }
            }
         }

         this.reachabilityFlushedNewIds.removeAll(currentReachables);
         Object[] nonReachableIds = this.reachabilityFlushedNewIds.toArray();
         if (nonReachableIds != null && nonReachableIds.length > 0) {
            for(int i = 0; i < nonReachableIds.length; ++i) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("010033", nonReachableIds[i]));
               }

               try {
                  if (!objectNotFound.contains(nonReachableIds[i])) {
                     ObjectProvider op = this.findObjectProvider(this.findObject(nonReachableIds[i], true, true, (String)null));
                     if (!op.getLifecycleState().isDeleted() && !this.getApiAdapter().isDetached(op.getObject())) {
                        op.replaceFields(op.getClassMetaData().getNonPKMemberPositions(), new NullifyRelationFieldManager(op));
                        this.flush();
                     }
                  }
               } catch (NucleusObjectNotFoundException var9) {
               }
            }

            for(int i = 0; i < nonReachableIds.length; ++i) {
               try {
                  if (!objectNotFound.contains(nonReachableIds[i])) {
                     ObjectProvider op = this.findObjectProvider(this.findObject(nonReachableIds[i], true, true, (String)null));
                     op.deletePersistent();
                  }
               } catch (NucleusObjectNotFoundException var8) {
               }
            }
         }

         this.flushInternal(true);
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("010034"));
      }

   }

   private void performDetachAllOnTxnEndPreparation() {
      Collection<ObjectProvider> ops = new ArrayList();
      Collection roots = this.fetchPlan.getDetachmentRoots();
      Class[] rootClasses = this.fetchPlan.getDetachmentRootClasses();
      if (roots != null && !roots.isEmpty()) {
         for(Object obj : roots) {
            ops.add(this.findObjectProvider(obj));
         }
      } else if (rootClasses != null && rootClasses.length > 0) {
         ObjectProvider[] txOPs = (ObjectProvider[])this.enlistedOPCache.values().toArray(new ObjectProvider[this.enlistedOPCache.size()]);

         for(int i = 0; i < txOPs.length; ++i) {
            for(int j = 0; j < rootClasses.length; ++j) {
               if (txOPs[i].getObject().getClass() == rootClasses[j]) {
                  ops.add(txOPs[i]);
                  break;
               }
            }
         }
      } else if (this.cache != null) {
         ops.addAll(this.cache.values());
      }

      Iterator<ObjectProvider> opsIter = ops.iterator();

      while(opsIter.hasNext()) {
         ObjectProvider op = (ObjectProvider)opsIter.next();
         Object pc = op.getObject();
         if (pc != null && !this.getApiAdapter().isDetached(pc) && !this.getApiAdapter().isDeleted(pc)) {
            FetchPlanState state = new FetchPlanState();

            try {
               op.loadFieldsInFetchPlan(state);
            } catch (NucleusObjectNotFoundException var9) {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("010013", StringUtils.toJVMIDString(pc), op.getInternalObjectId()));
               opsIter.remove();
            }
         }
      }

      this.detachAllOnTxnEndOPs = (ObjectProvider[])ops.toArray(new ObjectProvider[ops.size()]);
   }

   private void performDetachAllOnTxnEnd() {
      try {
         this.runningDetachAllOnTxnEnd = true;
         if (this.detachAllOnTxnEndOPs != null) {
            ObjectProvider[] opsToDetach = this.detachAllOnTxnEndOPs;
            DetachState state = new DetachState(this.getApiAdapter());

            for(int i = 0; i < opsToDetach.length; ++i) {
               Object pc = opsToDetach[i].getObject();
               if (pc != null) {
                  opsToDetach[i].detach(state);
               }
            }
         }
      } finally {
         this.detachAllOnTxnEndOPs = null;
         this.runningDetachAllOnTxnEnd = false;
      }

   }

   public boolean isRunningDetachAllOnCommit() {
      return this.runningDetachAllOnTxnEnd;
   }

   public void postCommit() {
      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         if (this.properties.getFrequentProperties().getDetachAllOnCommit()) {
            this.performDetachAllOnTxnEnd();
         }

         List failures = null;

         try {
            ApiAdapter api = this.getApiAdapter();
            ObjectProvider[] ops = (ObjectProvider[])this.enlistedOPCache.values().toArray(new ObjectProvider[this.enlistedOPCache.size()]);

            for(int i = 0; i < ops.length; ++i) {
               try {
                  if (ops[i] != null && ops[i].getObject() != null && (api.isPersistent(ops[i].getObject()) || api.isTransactional(ops[i].getObject()))) {
                     ops[i].postCommit(this.getTransaction());
                     if (this.properties.getFrequentProperties().getDetachAllOnCommit() && api.isDetachable(ops[i].getObject())) {
                        this.removeObjectProvider(ops[i]);
                     }
                  }
               } catch (RuntimeException e) {
                  if (failures == null) {
                     failures = new ArrayList();
                  }

                  failures.add(e);
               }
            }
         } finally {
            this.resetTransactionalVariables();
         }

         if (failures != null && !failures.isEmpty()) {
            throw new CommitStateTransitionException((Exception[])failures.toArray(new Exception[failures.size()]));
         }
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

   }

   public void preRollback() {
      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         List<Exception> failures = null;

         try {
            for(ObjectProvider op : this.enlistedOPCache.values()) {
               try {
                  op.preRollback(this.getTransaction());
               } catch (RuntimeException e) {
                  if (failures == null) {
                     failures = new ArrayList();
                  }

                  failures.add(e);
               }
            }

            this.clearDirty();
         } finally {
            this.resetTransactionalVariables();
         }

         if (failures != null && !failures.isEmpty()) {
            throw new RollbackStateTransitionException((Exception[])failures.toArray(new Exception[failures.size()]));
         }

         if (this.getBooleanProperty("datanucleus.DetachAllOnRollback")) {
            this.performDetachAllOnTxnEndPreparation();
         }
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

   }

   public void postRollback() {
      try {
         if (this.getMultithreaded()) {
            this.lock.lock();
         }

         if (this.getBooleanProperty("datanucleus.DetachAllOnRollback")) {
            this.performDetachAllOnTxnEnd();
         }

         if (this.objectsToEvictUponRollback != null) {
            this.nucCtx.getLevel2Cache().evictAll((Collection)this.objectsToEvictUponRollback);
            this.objectsToEvictUponRollback = null;
         }
      } finally {
         if (this.getMultithreaded()) {
            this.lock.unlock();
         }

      }

   }

   private void resetTransactionalVariables() {
      if (this.getReachabilityAtCommit()) {
         this.reachabilityEnlistedIds.clear();
         this.reachabilityPersistedIds.clear();
         this.reachabilityDeletedIds.clear();
         this.reachabilityFlushedNewIds.clear();
      }

      this.enlistedOPCache.clear();
      this.dirtyOPs.clear();
      this.indirectDirtyOPs.clear();
      this.fetchPlan.resetDetachmentRoots();
      if (this.getManageRelations() && this.managedRelationDetails != null) {
         this.managedRelationDetails.clear();
      }

      if (this.l2CacheTxIds != null) {
         this.l2CacheTxIds.clear();
      }

      if (this.l2CacheTxFieldsToUpdateById != null) {
         this.l2CacheTxFieldsToUpdateById.clear();
      }

      if (this.operationQueue != null) {
         this.operationQueue.clear();
      }

      this.opAttachDetachObjectReferenceMap = null;
   }

   protected String getLevel2CacheRetrieveMode() {
      return this.properties.getFrequentProperties().getLevel2CacheRetrieveMode();
   }

   protected String getLevel2CacheStoreMode() {
      return this.properties.getFrequentProperties().getLevel2CacheStoreMode();
   }

   public void putObjectIntoLevel1Cache(ObjectProvider op) {
      if (this.cache != null) {
         Object id = op.getInternalObjectId();
         if (id == null || op.getObject() == null) {
            NucleusLogger.CACHE.warn(Localiser.msg("003006"));
            return;
         }

         Object oldOP = this.cache.put(id, op);
         if (NucleusLogger.CACHE.isDebugEnabled() && oldOP == null) {
            NucleusLogger.CACHE.debug(Localiser.msg("003004", StringUtils.toJVMIDString(op.getObject()), IdentityUtils.getPersistableIdentityForId(id), StringUtils.booleanArrayToString(op.getLoadedFields())));
         }
      }

   }

   protected void putObjectIntoLevel2Cache(ObjectProvider op, boolean updateIfPresent) {
      if (op.getInternalObjectId() != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
         String storeMode = this.getLevel2CacheStoreMode();
         if (!storeMode.equalsIgnoreCase("bypass")) {
            if (this.l2CacheTxIds != null && !this.l2CacheTxIds.contains(op.getInternalObjectId())) {
               this.putObjectIntoLevel2CacheInternal(op, updateIfPresent);
            }

         }
      }
   }

   protected CachedPC getL2CacheableObject(ObjectProvider op, CachedPC currentCachedPC) {
      CachedPC cachedPC = null;
      int[] fieldsToUpdate = null;
      if (currentCachedPC != null) {
         cachedPC = currentCachedPC.getCopy();
         cachedPC.setVersion(op.getTransactionalVersion());
         BitSet fieldsToUpdateBitSet = (BitSet)this.l2CacheTxFieldsToUpdateById.get(op.getInternalObjectId());
         if (fieldsToUpdateBitSet != null) {
            int num = 0;

            for(int i = 0; i < fieldsToUpdateBitSet.length(); ++i) {
               if (fieldsToUpdateBitSet.get(i)) {
                  ++num;
               }
            }

            fieldsToUpdate = new int[num];
            int j = 0;

            for(int i = 0; i < fieldsToUpdateBitSet.length(); ++i) {
               if (fieldsToUpdateBitSet.get(i)) {
                  fieldsToUpdate[j++] = i;
               }
            }
         }

         if (fieldsToUpdate == null || fieldsToUpdate.length == 0) {
            return null;
         }

         if (NucleusLogger.CACHE.isDebugEnabled()) {
            int[] loadedFieldNums = cachedPC.getLoadedFieldNumbers();
            String fieldNames = loadedFieldNums != null && loadedFieldNums.length != 0 ? StringUtils.intArrayToString(loadedFieldNums) : "";
            NucleusLogger.CACHE.debug(Localiser.msg("004015", StringUtils.toJVMIDString(op.getObject()), op.getInternalObjectId(), fieldNames, cachedPC.getVersion(), StringUtils.intArrayToString(fieldsToUpdate)));
         }
      } else {
         int[] loadedFieldNumbers = op.getLoadedFieldNumbers();
         if (loadedFieldNumbers == null || loadedFieldNumbers.length == 0) {
            return null;
         }

         cachedPC = new CachedPC(op.getObject().getClass(), op.getLoadedFields(), op.getTransactionalVersion());
         fieldsToUpdate = loadedFieldNumbers;
         if (NucleusLogger.CACHE.isDebugEnabled()) {
            int[] loadedFieldNums = cachedPC.getLoadedFieldNumbers();
            String fieldNames = loadedFieldNums != null && loadedFieldNums.length != 0 ? StringUtils.intArrayToString(loadedFieldNums) : "";
            NucleusLogger.CACHE.debug(Localiser.msg("004003", StringUtils.toJVMIDString(op.getObject()), op.getInternalObjectId(), fieldNames, cachedPC.getVersion()));
         }
      }

      op.provideFields(fieldsToUpdate, new L2CachePopulateFieldManager(op, cachedPC));
      return cachedPC;
   }

   protected void putObjectsIntoLevel2Cache(Set ops) {
      int batchSize = this.nucCtx.getConfiguration().getIntProperty("datanucleus.cache.level2.batchSize");
      Level2Cache l2Cache = this.nucCtx.getLevel2Cache();
      Map<Object, CachedPC> dataToUpdate = new HashMap();

      for(ObjectProvider op : ops) {
         Object id = op.getInternalObjectId();
         if (id != null && this.nucCtx.isClassCacheable(op.getClassMetaData())) {
            CachedPC currentCachedPC = l2Cache.get(id);
            CachedPC cachedPC = this.getL2CacheableObject(op, currentCachedPC);
            if (cachedPC != null && id != null && !(id instanceof IdentityReference)) {
               dataToUpdate.put(id, cachedPC);
               if (dataToUpdate.size() == batchSize) {
                  l2Cache.putAll(dataToUpdate);
                  dataToUpdate.clear();
               }
            }
         }
      }

      if (!dataToUpdate.isEmpty()) {
         l2Cache.putAll(dataToUpdate);
         dataToUpdate.clear();
      }

   }

   protected void putObjectIntoLevel2CacheInternal(ObjectProvider op, boolean updateIfPresent) {
      Object id = op.getInternalObjectId();
      if (id != null && !(id instanceof IdentityReference)) {
         Level2Cache l2Cache = this.nucCtx.getLevel2Cache();
         if (updateIfPresent || !l2Cache.containsOid(id)) {
            CachedPC currentCachedPC = l2Cache.get(id);
            CachedPC cachedPC = this.getL2CacheableObject(op, currentCachedPC);
            if (cachedPC != null) {
               l2Cache.put(id, cachedPC);
            }

         }
      }
   }

   public void removeObjectFromLevel1Cache(Object id) {
      if (id != null && this.cache != null) {
         if (NucleusLogger.CACHE.isDebugEnabled()) {
            NucleusLogger.CACHE.debug(Localiser.msg("003009", IdentityUtils.getPersistableIdentityForId(id), String.valueOf(this.cache.size())));
         }

         Object pcRemoved = this.cache.remove(id);
         if (pcRemoved == null && NucleusLogger.CACHE.isDebugEnabled()) {
            NucleusLogger.CACHE.debug(Localiser.msg("003010", IdentityUtils.getPersistableIdentityForId(id)));
         }
      }

   }

   public void removeObjectFromLevel2Cache(Object id) {
      if (id != null && !(id instanceof IdentityReference)) {
         Level2Cache l2Cache = this.nucCtx.getLevel2Cache();
         if (l2Cache.containsOid(id)) {
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("004016", id));
            }

            l2Cache.evict(id);
         }
      }

   }

   public boolean hasIdentityInCache(Object id) {
      if (this.cache != null && this.cache.containsKey(id)) {
         return true;
      } else {
         if (this.l2CacheEnabled) {
            Level2Cache l2Cache = this.nucCtx.getLevel2Cache();
            if (l2Cache.containsOid(id)) {
               return true;
            }
         }

         return false;
      }
   }

   public Object getObjectFromCache(Object id) {
      Object pc = this.getObjectFromLevel1Cache(id);
      return pc != null ? pc : this.getObjectFromLevel2Cache(id);
   }

   public Object[] getObjectsFromCache(Object[] ids) {
      if (ids != null && ids.length != 0) {
         Object[] objs = new Object[ids.length];
         Collection idsNotFound = new HashSet();

         for(int i = 0; i < ids.length; ++i) {
            objs[i] = this.getObjectFromLevel1Cache(ids[i]);
            if (objs[i] == null) {
               idsNotFound.add(ids[i]);
            }
         }

         if (!idsNotFound.isEmpty()) {
            Map l2ObjsById = this.getObjectsFromLevel2Cache(idsNotFound);

            for(int i = 0; i < ids.length; ++i) {
               if (objs[i] == null) {
                  objs[i] = l2ObjsById.get(ids[i]);
               }
            }
         }

         return objs;
      } else {
         return null;
      }
   }

   public Object getObjectFromLevel1Cache(Object id) {
      Object pc = null;
      ObjectProvider op = null;
      if (this.cache != null) {
         op = (ObjectProvider)this.cache.get(id);
         if (op != null) {
            pc = op.getObject();
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("003008", StringUtils.toJVMIDString(pc), IdentityUtils.getPersistableIdentityForId(id), StringUtils.booleanArrayToString(op.getLoadedFields()), "" + this.cache.size()));
            }

            op.resetDetachState();
            return pc;
         }

         if (NucleusLogger.CACHE.isDebugEnabled()) {
            NucleusLogger.CACHE.debug(Localiser.msg("003007", IdentityUtils.getPersistableIdentityForId(id), "" + this.cache.size()));
         }
      }

      return null;
   }

   protected Object getObjectFromLevel2Cache(Object id) {
      Object pc = null;
      if (id instanceof SCOID) {
         return null;
      } else {
         if (this.l2CacheEnabled) {
            if (!this.nucCtx.isClassWithIdentityCacheable(id)) {
               return null;
            }

            String cacheRetrieveMode = this.getLevel2CacheRetrieveMode();
            if ("bypass".equalsIgnoreCase(cacheRetrieveMode)) {
               return null;
            }

            Level2Cache l2Cache = this.nucCtx.getLevel2Cache();
            CachedPC cachedPC = l2Cache.get(id);
            if (cachedPC != null) {
               ObjectProvider op = this.nucCtx.getObjectProviderFactory().newForCachedPC(this, id, cachedPC);
               pc = op.getObject();
               if (NucleusLogger.CACHE.isDebugEnabled()) {
                  NucleusLogger.CACHE.debug(Localiser.msg("004006", IdentityUtils.getPersistableIdentityForId(id), StringUtils.intArrayToString(cachedPC.getLoadedFieldNumbers()), cachedPC.getVersion(), StringUtils.toJVMIDString(pc)));
               }

               if (this.tx.isActive() && this.tx.getOptimistic()) {
                  op.makeNontransactional();
               } else if (!this.tx.isActive() && this.getApiAdapter().isTransactional(pc)) {
                  op.makeNontransactional();
               }

               return pc;
            }

            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("004005", IdentityUtils.getPersistableIdentityForId(id)));
            }
         }

         return null;
      }
   }

   protected Map getObjectsFromLevel2Cache(Collection ids) {
      if (!this.l2CacheEnabled) {
         return null;
      } else {
         Level2Cache l2Cache = this.nucCtx.getLevel2Cache();
         Map<Object, CachedPC> cachedPCs = l2Cache.getAll(ids);
         Map pcsById = new HashMap(cachedPCs.size());

         for(Map.Entry entry : cachedPCs.entrySet()) {
            Object id = entry.getKey();
            CachedPC cachedPC = (CachedPC)entry.getValue();
            if (cachedPC != null) {
               ObjectProvider op = this.nucCtx.getObjectProviderFactory().newForCachedPC(this, id, cachedPC);
               Object pc = op.getObject();
               if (NucleusLogger.CACHE.isDebugEnabled()) {
                  NucleusLogger.CACHE.debug(Localiser.msg("004006", IdentityUtils.getPersistableIdentityForId(id), StringUtils.intArrayToString(cachedPC.getLoadedFieldNumbers()), cachedPC.getVersion(), StringUtils.toJVMIDString(pc)));
               }

               if (this.tx.isActive() && this.tx.getOptimistic()) {
                  op.makeNontransactional();
               } else if (!this.tx.isActive() && this.getApiAdapter().isTransactional(pc)) {
                  op.makeNontransactional();
               }

               pcsById.put(id, pc);
            } else if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("004005", IdentityUtils.getPersistableIdentityForId(id)));
            }
         }

         return pcsById;
      }
   }

   public void replaceObjectId(Object pc, Object oldID, Object newID) {
      if (pc != null && this.getApiAdapter().getIdForObject(pc) != null) {
         ObjectProvider op = this.findObjectProvider(pc);
         if (this.cache != null) {
            Object o = this.cache.get(oldID);
            if (o != null) {
               if (NucleusLogger.CACHE.isDebugEnabled()) {
                  NucleusLogger.CACHE.debug(Localiser.msg("003012", StringUtils.toJVMIDString(pc), IdentityUtils.getPersistableIdentityForId(oldID), IdentityUtils.getPersistableIdentityForId(newID)));
               }

               this.cache.remove(oldID);
            }

            if (op != null) {
               this.putObjectIntoLevel1Cache(op);
            }
         }

         if (this.enlistedOPCache.get(oldID) != null && op != null) {
            this.enlistedOPCache.remove(oldID);
            this.enlistedOPCache.put(newID, op);
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(Localiser.msg("015018", StringUtils.toJVMIDString(pc), IdentityUtils.getPersistableIdentityForId(oldID), IdentityUtils.getPersistableIdentityForId(newID)));
            }
         }

         if (this.l2CacheTxIds != null && this.l2CacheTxIds.contains(oldID)) {
            this.l2CacheTxIds.remove(oldID);
            this.l2CacheTxIds.add(newID);
         }

         if (this.getReachabilityAtCommit() && this.tx.isActive()) {
            if (this.reachabilityEnlistedIds.remove(oldID)) {
               this.reachabilityEnlistedIds.add(newID);
            }

            if (this.reachabilityFlushedNewIds.remove(oldID)) {
               this.reachabilityFlushedNewIds.add(newID);
            }

            if (this.reachabilityPersistedIds.remove(oldID)) {
               this.reachabilityPersistedIds.add(newID);
            }

            if (this.reachabilityDeletedIds.remove(oldID)) {
               this.reachabilityDeletedIds.add(newID);
            }
         }

      } else {
         NucleusLogger.CACHE.warn(Localiser.msg("003006"));
      }
   }

   public boolean getSerializeReadForClass(String className) {
      if (this.tx.isActive() && this.tx.getSerializeRead() != null) {
         return this.tx.getSerializeRead();
      } else if (this.getProperty("datanucleus.SerializeRead") != null) {
         return this.properties.getBooleanProperty("datanucleus.SerializeRead");
      } else {
         if (className != null) {
            AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, this.clr);
            if (cmd != null) {
               return cmd.isSerializeRead();
            }
         }

         return false;
      }
   }

   public Extent getExtent(Class pcClass, boolean subclasses) {
      Extent var3;
      try {
         this.clr.setPrimary(pcClass.getClassLoader());
         this.assertClassPersistable(pcClass);
         var3 = this.getStoreManager().getExtent(this, pcClass, subclasses);
      } finally {
         this.clr.unsetPrimary();
      }

      return var3;
   }

   public CallbackHandler getCallbackHandler() {
      if (this.callbackHandler != null) {
         return this.callbackHandler;
      } else if (!this.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.allowCallbacks")) {
         this.callbackHandler = new NullCallbackHandler();
         return this.callbackHandler;
      } else {
         String callbackHandlerClassName = this.getNucleusContext().getPluginManager().getAttributeValueForExtension("org.datanucleus.callbackhandler", "name", this.getNucleusContext().getApiName(), "class-name");
         if (callbackHandlerClassName != null) {
            try {
               this.callbackHandler = (CallbackHandler)this.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.callbackhandler", "name", this.getNucleusContext().getApiName(), "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this.getNucleusContext()});
               return this.callbackHandler;
            } catch (Exception e) {
               NucleusLogger.PERSISTENCE.error(Localiser.msg("025000", callbackHandlerClassName, e));
            }
         }

         return null;
      }
   }

   public void closeCallbackHandler() {
      if (this.callbackHandler != null) {
         this.callbackHandler.close();
      }

   }

   protected void assertIsOpen() {
      if (this.isClosed()) {
         throw (new NucleusUserException(Localiser.msg("010002"))).setFatal();
      }
   }

   public void assertClassPersistable(Class cls) {
      if (cls != null && !this.getNucleusContext().getApiAdapter().isPersistable(cls) && !cls.isInterface()) {
         throw new ClassNotPersistableException(cls.getName());
      } else if (!this.hasPersistenceInformationForClass(cls)) {
         throw new NoPersistenceInformationException(cls.getName());
      }
   }

   protected void assertDetachable(Object object) {
      if (object != null && !this.getApiAdapter().isDetachable(object)) {
         throw new ClassNotDetachableException(object.getClass().getName());
      }
   }

   protected void assertNotDetached(Object object) {
      if (object != null && this.getApiAdapter().isDetached(object)) {
         throw new ObjectDetachedException(object.getClass().getName());
      }
   }

   protected void assertActiveTransaction() {
      if (!this.tx.isActive()) {
         throw new TransactionNotActiveException();
      }
   }

   protected void assertHasImplementationCreator() {
      if (this.getNucleusContext().getImplementationCreator() == null) {
         throw new NucleusUserException(Localiser.msg("010035"));
      }
   }

   public boolean hasPersistenceInformationForClass(Class cls) {
      if (cls == null) {
         return false;
      } else if (this.getMetaDataManager().getMetaDataForClass(cls, this.clr) != null) {
         return true;
      } else if (cls.isInterface()) {
         try {
            this.newInstance(cls);
         } catch (RuntimeException ex) {
            NucleusLogger.PERSISTENCE.warn(ex);
         }

         return this.getMetaDataManager().getMetaDataForClass(cls, this.clr) != null;
      } else {
         return false;
      }
   }

   protected FetchGroupManager getFetchGroupManager() {
      if (this.fetchGrpMgr == null) {
         this.fetchGrpMgr = new FetchGroupManager(this.getNucleusContext());
      }

      return this.fetchGrpMgr;
   }

   public void addInternalFetchGroup(FetchGroup grp) {
      this.getFetchGroupManager().addFetchGroup(grp);
   }

   protected void removeInternalFetchGroup(FetchGroup grp) {
      if (this.fetchGrpMgr != null) {
         this.getFetchGroupManager().removeFetchGroup(grp);
      }
   }

   public FetchGroup getInternalFetchGroup(Class cls, String name) {
      if (!cls.isInterface() && !this.getNucleusContext().getApiAdapter().isPersistable(cls)) {
         throw new NucleusUserException("Cannot create FetchGroup for " + cls + " since it is not persistable");
      } else if (cls.isInterface() && !this.getNucleusContext().getMetaDataManager().isPersistentInterface(cls.getName())) {
         throw new NucleusUserException("Cannot create FetchGroup for " + cls + " since it is not persistable");
      } else {
         return this.fetchGrpMgr == null ? null : this.getFetchGroupManager().getFetchGroup(cls, name, true);
      }
   }

   public Set getFetchGroupsWithName(String name) {
      return this.fetchGrpMgr == null ? null : this.getFetchGroupManager().getFetchGroupsWithName(name);
   }

   public Lock getLock() {
      return this.lock;
   }

   public ExecutionContext.EmbeddedOwnerRelation registerEmbeddedRelation(ObjectProvider ownerOP, int ownerFieldNum, ObjectProvider embOP) {
      ExecutionContext.EmbeddedOwnerRelation relation = new ExecutionContext.EmbeddedOwnerRelation(ownerOP, ownerFieldNum, embOP);
      if (this.opEmbeddedInfoByEmbedded == null) {
         this.opEmbeddedInfoByEmbedded = new HashMap();
      }

      List<ExecutionContext.EmbeddedOwnerRelation> relations = (List)this.opEmbeddedInfoByEmbedded.get(embOP);
      if (relations == null) {
         relations = new ArrayList();
      }

      relations.add(relation);
      this.opEmbeddedInfoByEmbedded.put(embOP, relations);
      if (this.opEmbeddedInfoByOwner == null) {
         this.opEmbeddedInfoByOwner = new HashMap();
      }

      relations = (List)this.opEmbeddedInfoByOwner.get(ownerOP);
      if (relations == null) {
         relations = new ArrayList();
      }

      relations.add(relation);
      this.opEmbeddedInfoByOwner.put(ownerOP, relations);
      return relation;
   }

   public void deregisterEmbeddedRelation(ExecutionContext.EmbeddedOwnerRelation rel) {
      if (this.opEmbeddedInfoByEmbedded != null) {
         List<ExecutionContext.EmbeddedOwnerRelation> ownerRels = (List)this.opEmbeddedInfoByEmbedded.get(rel.getEmbeddedOP());
         ownerRels.remove(rel);
         if (ownerRels.isEmpty()) {
            this.opEmbeddedInfoByEmbedded.remove(rel.getEmbeddedOP());
            if (this.opEmbeddedInfoByEmbedded.isEmpty()) {
               this.opEmbeddedInfoByEmbedded = null;
            }
         }
      }

      if (this.opEmbeddedInfoByOwner != null) {
         List<ExecutionContext.EmbeddedOwnerRelation> embRels = (List)this.opEmbeddedInfoByOwner.get(rel.getOwnerOP());
         embRels.remove(rel);
         if (embRels.isEmpty()) {
            this.opEmbeddedInfoByOwner.remove(rel.getOwnerOP());
            if (this.opEmbeddedInfoByOwner.isEmpty()) {
               this.opEmbeddedInfoByOwner = null;
            }
         }
      }

   }

   public void removeEmbeddedOwnerRelation(ObjectProvider ownerOP, int ownerFieldNum, ObjectProvider embOP) {
      if (this.opEmbeddedInfoByOwner != null) {
         List<ExecutionContext.EmbeddedOwnerRelation> ownerRels = (List)this.opEmbeddedInfoByOwner.get(ownerOP);
         ExecutionContext.EmbeddedOwnerRelation rel = null;

         for(ExecutionContext.EmbeddedOwnerRelation ownerRel : ownerRels) {
            if (ownerRel.getEmbeddedOP() == embOP && ownerRel.getOwnerFieldNum() == ownerFieldNum) {
               rel = ownerRel;
               break;
            }
         }

         if (rel != null) {
            this.deregisterEmbeddedRelation(rel);
         }
      }

   }

   public ObjectProvider[] getOwnersForEmbeddedObjectProvider(ObjectProvider embOP) {
      if (this.opEmbeddedInfoByEmbedded != null && this.opEmbeddedInfoByEmbedded.containsKey(embOP)) {
         List<ExecutionContext.EmbeddedOwnerRelation> ownerRels = (List)this.opEmbeddedInfoByEmbedded.get(embOP);
         ObjectProvider[] owners = new ObjectProvider[ownerRels.size()];
         int i = 0;

         for(ExecutionContext.EmbeddedOwnerRelation rel : ownerRels) {
            owners[i++] = rel.getOwnerOP();
         }

         return owners;
      } else {
         return null;
      }
   }

   public List getOwnerInformationForEmbedded(ObjectProvider embOP) {
      return this.opEmbeddedInfoByEmbedded == null ? null : (List)this.opEmbeddedInfoByEmbedded.get(embOP);
   }

   public List getEmbeddedInformationForOwner(ObjectProvider ownerOP) {
      return this.opEmbeddedInfoByOwner == null ? null : (List)this.opEmbeddedInfoByOwner.get(ownerOP);
   }

   public void setObjectProviderAssociatedValue(ObjectProvider op, Object key, Object value) {
      Map opMap = null;
      if (this.opAssociatedValuesMapByOP == null) {
         this.opAssociatedValuesMapByOP = new HashMap();
         opMap = new HashMap();
         this.opAssociatedValuesMapByOP.put(op, opMap);
      } else {
         opMap = (Map)this.opAssociatedValuesMapByOP.get(op);
         if (opMap == null) {
            opMap = new HashMap();
            this.opAssociatedValuesMapByOP.put(op, opMap);
         }
      }

      opMap.put(key, value);
   }

   public Object getObjectProviderAssociatedValue(ObjectProvider op, Object key) {
      if (this.opAssociatedValuesMapByOP == null) {
         return null;
      } else {
         Map opMap = (Map)this.opAssociatedValuesMapByOP.get(op);
         return opMap == null ? null : opMap.get(key);
      }
   }

   public void removeObjectProviderAssociatedValue(ObjectProvider op, Object key) {
      if (this.opAssociatedValuesMapByOP != null) {
         Map opMap = (Map)this.opAssociatedValuesMapByOP.get(op);
         if (opMap != null) {
            opMap.remove(key);
         }
      }

   }

   public boolean containsObjectProviderAssociatedValue(ObjectProvider op, Object key) {
      return this.opAssociatedValuesMapByOP != null && this.opAssociatedValuesMapByOP.containsKey(op) ? ((Map)this.opAssociatedValuesMapByOP.get(op)).containsKey(key) : false;
   }

   static class ThreadContextInfo {
      int referenceCounter = 0;
      Map attachedOwnerByObject = null;
      Map attachedPCById = null;
      boolean merging = false;
      boolean nontxPersistDelete = false;
   }

   private static class ClassDetailsForId {
      Object id;
      String className;
      Object pc;

      public ClassDetailsForId(Object id, String className, Object pc) {
         this.id = id;
         this.className = className;
         this.pc = pc;
      }
   }
}
