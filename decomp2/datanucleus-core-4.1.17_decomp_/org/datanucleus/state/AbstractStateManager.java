package org.datanucleus.state;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlanForClass;
import org.datanucleus.Transaction;
import org.datanucleus.cache.CachedPC;
import org.datanucleus.cache.L2CachePopulateFieldManager;
import org.datanucleus.cache.L2CacheRetrieveFieldManager;
import org.datanucleus.cache.Level2Cache;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityReference;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.federation.FederatedStoreManager;
import org.datanucleus.store.fieldmanager.AbstractFetchDepthFieldManager;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.fieldmanager.LoadFieldManager;
import org.datanucleus.store.fieldmanager.SingleTypeFieldManager;
import org.datanucleus.store.objectvaluegenerator.ObjectValueGenerator;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractStateManager implements ObjectProvider {
   protected static final SingleTypeFieldManager HOLLOWFIELDMANAGER = new SingleTypeFieldManager();
   protected static final int FLAG_STORING_PC = 65536;
   protected static final int FLAG_NEED_INHERITANCE_VALIDATION = 32768;
   protected static final int FLAG_POSTINSERT_UPDATE = 16384;
   protected static final int FLAG_LOADINGFPFIELDS = 8192;
   protected static final int FLAG_POSTLOAD_PENDING = 4096;
   protected static final int FLAG_CHANGING_STATE = 2048;
   protected static final int FLAG_FLUSHED_NEW = 1024;
   protected static final int FLAG_BECOMING_DELETED = 512;
   protected static final int FLAG_UPDATING_EMBEDDING_FIELDS_WITH_OWNER = 256;
   protected static final int FLAG_RETRIEVING_DETACHED_STATE = 128;
   protected static final int FLAG_RESETTING_DETACHED_STATE = 64;
   protected static final int FLAG_ATTACHING = 32;
   protected static final int FLAG_DETACHING = 16;
   protected static final int FLAG_MAKING_TRANSIENT = 8;
   protected static final int FLAG_FLUSHING = 4;
   protected static final int FLAG_DISCONNECTING = 2;
   protected Object myPC;
   protected int flags;
   protected boolean restoreValues = false;
   protected ExecutionContext myEC;
   protected AbstractClassMetaData cmd;
   protected Object myInternalID;
   protected Object myID;
   protected LifeCycleState myLC;
   protected Object myVersion;
   protected Object transactionalVersion;
   protected byte persistenceFlags;
   protected FetchPlanForClass myFP;
   protected boolean dirty = false;
   protected boolean[] dirtyFields;
   protected boolean[] loadedFields;
   protected Lock lock = null;
   protected short lockMode = 0;
   protected byte savedFlags;
   protected boolean[] savedLoadedFields = null;
   protected ActivityState activity;
   protected FieldManager currFM = null;
   protected short objectType = 0;
   boolean[] preDeleteLoadedFields = null;
   public static final Map objectValGenerators = new HashMap(1);

   public AbstractStateManager(ExecutionContext ec, AbstractClassMetaData cmd) {
      this.connect(ec, cmd);
   }

   public void connect(ExecutionContext ec, AbstractClassMetaData cmd) {
      int fieldCount = cmd.getMemberCount();
      this.cmd = cmd;
      this.dirtyFields = new boolean[fieldCount];
      this.loadedFields = new boolean[fieldCount];
      this.dirty = false;
      this.myEC = ec;
      this.myFP = this.myEC.getFetchPlan().getFetchPlanForClass(cmd);
      this.lock = new ReentrantLock();
      this.lockMode = 0;
      this.savedFlags = 0;
      this.savedLoadedFields = null;
      this.objectType = 0;
      this.activity = ActivityState.NONE;
      this.myVersion = null;
      this.transactionalVersion = null;
      this.persistenceFlags = 0;
   }

   public AbstractClassMetaData getClassMetaData() {
      return this.cmd;
   }

   public ExecutionContext getExecutionContext() {
      return this.myEC;
   }

   public StoreManager getStoreManager() {
      return this.myEC.getNucleusContext().isFederated() ? ((FederatedStoreManager)this.myEC.getStoreManager()).getStoreManagerForClass(this.cmd) : this.myEC.getStoreManager();
   }

   public LifeCycleState getLifecycleState() {
      return this.myLC;
   }

   protected CallbackHandler getCallbackHandler() {
      return this.myEC.getCallbackHandler();
   }

   public abstract Object getObject();

   public String getObjectAsPrintable() {
      return StringUtils.toJVMIDString(this.getObject());
   }

   public String toString() {
      return "StateManager[pc=" + StringUtils.toJVMIDString(this.getObject()) + ", lifecycle=" + this.myLC + "]";
   }

   public Object getInternalObjectId() {
      if (this.myID != null) {
         return this.myID;
      } else if (this.myInternalID == null) {
         this.myInternalID = new IdentityReference(this);
         return this.myInternalID;
      } else {
         return this.myInternalID;
      }
   }

   public boolean isInserting() {
      return this.activity == ActivityState.INSERTING;
   }

   public boolean isWaitingToBeFlushedToDatastore() {
      return this.myLC.stateType() == 1 && !this.isFlushedNew();
   }

   public boolean isRestoreValues() {
      return this.restoreValues;
   }

   public void setStoringPC() {
      this.flags |= 65536;
   }

   public void unsetStoringPC() {
      this.flags &= -65537;
   }

   protected boolean isStoringPC() {
      return (this.flags & 65536) != 0;
   }

   void setPostLoadPending(boolean flag) {
      if (flag) {
         this.flags |= 4096;
      } else {
         this.flags &= -4097;
      }

   }

   protected boolean isPostLoadPending() {
      return (this.flags & 4096) != 0;
   }

   protected boolean isChangingState() {
      return (this.flags & 2048) != 0;
   }

   void setResettingDetachedState(boolean flag) {
      if (flag) {
         this.flags |= 64;
      } else {
         this.flags &= -65;
      }

   }

   protected boolean isResettingDetachedState() {
      return (this.flags & 64) != 0;
   }

   void setRetrievingDetachedState(boolean flag) {
      if (flag) {
         this.flags |= 128;
      } else {
         this.flags &= -129;
      }

   }

   protected boolean isRetrievingDetachedState() {
      return (this.flags & 128) != 0;
   }

   void setDisconnecting(boolean flag) {
      if (flag) {
         this.flags |= 2;
      } else {
         this.flags &= -3;
      }

   }

   protected boolean isDisconnecting() {
      return (this.flags & 2) != 0;
   }

   void setMakingTransient(boolean flag) {
      if (flag) {
         this.flags |= 8;
      } else {
         this.flags &= -9;
      }

   }

   protected boolean isMakingTransient() {
      return (this.flags & 8) != 0;
   }

   public boolean isDeleting() {
      return this.activity == ActivityState.DELETING;
   }

   void setBecomingDeleted(boolean flag) {
      if (flag) {
         this.flags |= 512;
      } else {
         this.flags &= -513;
      }

   }

   public boolean becomingDeleted() {
      return (this.flags & 512) > 0;
   }

   public void markForInheritanceValidation() {
      this.flags |= 32768;
   }

   void setDetaching(boolean flag) {
      if (flag) {
         this.flags |= 16;
      } else {
         this.flags &= -17;
      }

   }

   public boolean isDetaching() {
      return (this.flags & 16) != 0;
   }

   void setAttaching(boolean flag) {
      if (flag) {
         this.flags |= 32;
      } else {
         this.flags &= -33;
      }

   }

   public boolean isAttaching() {
      return (this.flags & 32) != 0;
   }

   public void setTransactionalVersion(Object version) {
      this.transactionalVersion = version;
   }

   public Object getTransactionalVersion(Object pc) {
      return this.transactionalVersion;
   }

   public void setVersion(Object version) {
      this.myVersion = version;
      this.transactionalVersion = version;
   }

   public void setFlushedNew(boolean flag) {
      if (flag) {
         this.flags |= 1024;
      } else {
         this.flags &= -1025;
      }

   }

   public boolean isFlushedNew() {
      return (this.flags & 1024) != 0;
   }

   public boolean isFlushedToDatastore() {
      return !this.dirty;
   }

   public void setFlushing(boolean flushing) {
      if (flushing) {
         this.flags |= 4;
      } else {
         this.flags &= -5;
      }

   }

   protected boolean isFlushing() {
      return (this.flags & 4) != 0;
   }

   public void markAsFlushed() {
      this.clearDirtyFlags();
   }

   protected void preStateChange() {
      this.flags |= 2048;
   }

   protected abstract void postStateChange();

   public void refresh() {
      this.preStateChange();

      try {
         this.myLC = this.myLC.transitionRefresh(this);
      } finally {
         this.postStateChange();
      }

   }

   public void retrieve(boolean fgOnly) {
      this.preStateChange();

      try {
         this.myLC = this.myLC.transitionRetrieve(this, fgOnly);
      } finally {
         this.postStateChange();
      }

   }

   public void makePersistentTransactionalTransient() {
      this.preStateChange();

      try {
         if (this.myLC.isTransactional && !this.myLC.isPersistent) {
            this.makePersistent();
            this.myLC = this.myLC.transitionMakePersistent(this);
         }
      } finally {
         this.postStateChange();
      }

   }

   public void makeNontransactional() {
      this.preStateChange();

      try {
         this.myLC = this.myLC.transitionMakeNontransactional(this);
      } finally {
         this.postStateChange();
      }

   }

   protected void transitionReadField(boolean isLoaded) {
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         if (this.myLC != null) {
            this.preStateChange();

            try {
               this.myLC = this.myLC.transitionReadField(this, isLoaded);
               return;
            } finally {
               this.postStateChange();
            }
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

   }

   protected void transitionWriteField() {
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         this.preStateChange();

         try {
            this.myLC = this.myLC.transitionWriteField(this);
         } finally {
            this.postStateChange();
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

   }

   public void evict() {
      if (this.myLC == this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(2) || this.myLC == this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(9)) {
         this.preStateChange();

         try {
            try {
               this.getCallbackHandler().preClear(this.getObject());
               this.getCallbackHandler().postClear(this.getObject());
            } finally {
               this.myLC = this.myLC.transitionEvict(this);
            }
         } finally {
            this.postStateChange();
         }

      }
   }

   public void preBegin(Transaction tx) {
      this.preStateChange();

      try {
         this.myLC = this.myLC.transitionBegin(this, tx);
      } finally {
         this.postStateChange();
      }

   }

   public void postCommit(Transaction tx) {
      this.preStateChange();

      try {
         this.myLC = this.myLC.transitionCommit(this, tx);
         if (this.transactionalVersion != this.myVersion) {
            this.myVersion = this.transactionalVersion;
         }

         this.lockMode = 0;
      } finally {
         this.postStateChange();
      }

   }

   public void preRollback(Transaction tx) {
      this.preStateChange();

      try {
         this.myEC.clearDirty(this);
         this.myLC = this.myLC.transitionRollback(this, tx);
         if (this.transactionalVersion != this.myVersion) {
            this.transactionalVersion = this.myVersion;
         }

         this.lockMode = 0;
      } finally {
         this.postStateChange();
      }

   }

   protected void internalDeletePersistent() {
      if (this.isDeleting()) {
         throw new NucleusUserException(Localiser.msg("026008"));
      } else {
         this.activity = ActivityState.DELETING;

         try {
            if (this.dirty) {
               this.clearDirtyFlags();
               this.myEC.flushInternal(false);
            }

            this.myEC.getNucleusContext();
            if (!this.isEmbedded()) {
               this.getStoreManager().getPersistenceHandler().deleteObject(this);
            }

            this.preDeleteLoadedFields = null;
         } finally {
            this.activity = ActivityState.NONE;
         }

      }
   }

   public void locate() {
      this.getStoreManager().getPersistenceHandler().locateObject(this);
   }

   public Object getReferencedPC() {
      return this.myEC.getAttachDetachReferencedObject(this);
   }

   public abstract void provideFields(int[] var1, FieldManager var2);

   public abstract void replaceFields(int[] var1, FieldManager var2);

   protected boolean areFieldsLoaded(int[] fieldNumbers) {
      if (fieldNumbers == null) {
         return true;
      } else {
         for(int i = 0; i < fieldNumbers.length; ++i) {
            if (!this.loadedFields[fieldNumbers[i]]) {
               return false;
            }
         }

         return true;
      }
   }

   public void unloadNonFetchPlanFields() {
      int[] fpFieldNumbers = this.myFP.getMemberNumbers();
      int[] nonfpFieldNumbers = null;
      if (fpFieldNumbers != null && fpFieldNumbers.length != 0) {
         int fieldCount = this.cmd.getMemberCount();
         if (fieldCount == fpFieldNumbers.length) {
            return;
         }

         nonfpFieldNumbers = new int[fieldCount - fpFieldNumbers.length];
         int currentFPFieldIndex = 0;
         int j = 0;

         for(int i = 0; i < fieldCount; ++i) {
            if (currentFPFieldIndex >= fpFieldNumbers.length) {
               nonfpFieldNumbers[j++] = i;
            } else if (fpFieldNumbers[currentFPFieldIndex] == i) {
               ++currentFPFieldIndex;
            } else {
               nonfpFieldNumbers[j++] = i;
            }
         }
      } else {
         nonfpFieldNumbers = this.cmd.getAllMemberPositions();
      }

      for(int i = 0; i < nonfpFieldNumbers.length; ++i) {
         this.loadedFields[nonfpFieldNumbers[i]] = false;
      }

   }

   protected void markPKFieldsAsLoaded() {
      if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
         int[] pkPositions = this.cmd.getPKMemberPositions();

         for(int i = 0; i < pkPositions.length; ++i) {
            this.loadedFields[pkPositions[i]] = true;
         }
      }

   }

   protected void updateLevel2CacheForFields(int[] fieldNumbers) {
      String updateMode = (String)this.myEC.getProperty("datanucleus.cache.level2.updateMode");
      if (updateMode == null || !updateMode.equalsIgnoreCase("commit-only")) {
         if (fieldNumbers != null && fieldNumbers.length != 0) {
            Level2Cache l2cache = this.myEC.getNucleusContext().getLevel2Cache();
            if (l2cache != null && this.myEC.getNucleusContext().isClassCacheable(this.cmd) && !this.myEC.isObjectModifiedInTransaction(this.myID)) {
               CachedPC<T> cachedPC = l2cache.get(this.myID);
               if (cachedPC != null) {
                  CachedPC copyCachedPC = cachedPC.getCopy();
                  if (NucleusLogger.CACHE.isDebugEnabled()) {
                     NucleusLogger.CACHE.debug(Localiser.msg("026033", StringUtils.toJVMIDString(this.getObject()), this.myID, StringUtils.intArrayToString(fieldNumbers)));
                  }

                  this.provideFields(fieldNumbers, new L2CachePopulateFieldManager(this, copyCachedPC));
                  this.myEC.getNucleusContext().getLevel2Cache().put(this.getInternalObjectId(), copyCachedPC);
               }
            }

         }
      }
   }

   protected int[] loadFieldsFromLevel2Cache(int[] fieldNumbers) {
      if (fieldNumbers != null && fieldNumbers.length != 0 && !this.myEC.isFlushing() && !this.myLC.isDeleted() && !this.isDeleting() && !this.getExecutionContext().getTransaction().isCommitting()) {
         if (!this.myEC.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.cache.level2.loadFields", true)) {
            return fieldNumbers;
         } else {
            Level2Cache l2cache = this.myEC.getNucleusContext().getLevel2Cache();
            if (l2cache != null && this.myEC.getNucleusContext().isClassCacheable(this.cmd)) {
               CachedPC<T> cachedPC = l2cache.get(this.myID);
               if (cachedPC != null) {
                  int[] cacheFieldsToLoad = ClassUtils.getFlagsSetTo(cachedPC.getLoadedFields(), fieldNumbers, true);
                  if (cacheFieldsToLoad != null && cacheFieldsToLoad.length > 0) {
                     if (NucleusLogger.CACHE.isDebugEnabled()) {
                        NucleusLogger.CACHE.debug(Localiser.msg("026034", StringUtils.toJVMIDString(this.getObject()), this.myID, StringUtils.intArrayToString(cacheFieldsToLoad)));
                     }

                     L2CacheRetrieveFieldManager l2RetFM = new L2CacheRetrieveFieldManager(this, cachedPC);
                     this.replaceFields(cacheFieldsToLoad, l2RetFM);
                     int[] fieldsNotLoaded = l2RetFM.getFieldsNotLoaded();
                     if (fieldsNotLoaded != null) {
                        for(int i = 0; i < fieldsNotLoaded.length; ++i) {
                           this.loadedFields[fieldsNotLoaded[i]] = false;
                        }
                     }
                  }
               }
            }

            return ClassUtils.getFlagsSetTo(this.loadedFields, fieldNumbers, false);
         }
      } else {
         return fieldNumbers;
      }
   }

   public void loadFieldsInFetchPlan(FetchPlanState state) {
      if ((this.flags & 8192) == 0) {
         this.flags |= 8192;

         try {
            this.loadUnloadedFieldsInFetchPlan();
            int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getAllMemberPositions(), true);
            if (fieldNumbers != null && fieldNumbers.length > 0) {
               this.replaceFields(fieldNumbers, new LoadFieldManager(this, this.cmd.getSCOMutableMemberFlags(), this.myFP, state));
               this.updateLevel2CacheForFields(fieldNumbers);
            }
         } finally {
            this.flags &= -8193;
         }

      }
   }

   public void loadFieldFromDatastore(int fieldNumber) {
      this.loadFieldsFromDatastore(new int[]{fieldNumber});
   }

   protected void loadFieldsFromDatastore(int[] fieldNumbers) {
      if (!this.myLC.isNew() || !this.myLC.isPersistent() || this.isFlushedNew()) {
         if ((this.flags & '耀') != 0) {
            String className = this.getStoreManager().getClassNameForObjectID(this.myID, this.myEC.getClassLoaderResolver(), this.myEC);
            if (!this.getObject().getClass().getName().equals(className)) {
               this.myEC.removeObjectFromLevel1Cache(this.myID);
               this.myEC.removeObjectFromLevel2Cache(this.myID);
               throw new NucleusObjectNotFoundException("Object with id " + this.myID + " was created without validating of type " + this.getObject().getClass().getName() + " but is actually of type " + className);
            }

            this.flags &= -32769;
         }

         this.getStoreManager().getPersistenceHandler().fetchObject(this, fieldNumbers);
      }
   }

   protected int[] getFieldNumbersOfLoadedOrDirtyFields(boolean[] loadedFields, boolean[] dirtyFields) {
      int numFields = 0;

      for(int i = 0; i < loadedFields.length; ++i) {
         if (loadedFields[i] || dirtyFields[i]) {
            ++numFields;
         }
      }

      int[] fieldNumbers = new int[numFields];
      int n = 0;
      int[] allFieldNumbers = this.cmd.getAllMemberPositions();

      for(int i = 0; i < loadedFields.length; ++i) {
         if (loadedFields[i] || dirtyFields[i]) {
            fieldNumbers[n++] = allFieldNumbers[i];
         }
      }

      return fieldNumbers;
   }

   public boolean[] getDirtyFields() {
      boolean[] copy = new boolean[this.dirtyFields.length];
      System.arraycopy(this.dirtyFields, 0, copy, 0, this.dirtyFields.length);
      return copy;
   }

   public int[] getDirtyFieldNumbers() {
      return ClassUtils.getFlagsSetTo(this.dirtyFields, true);
   }

   public boolean[] getLoadedFields() {
      return (boolean[])this.loadedFields.clone();
   }

   public int[] getLoadedFieldNumbers() {
      return ClassUtils.getFlagsSetTo(this.loadedFields, true);
   }

   public boolean getAllFieldsLoaded() {
      for(int i = 0; i < this.loadedFields.length; ++i) {
         if (!this.loadedFields[i]) {
            return false;
         }
      }

      return true;
   }

   public String[] getDirtyFieldNames() {
      int[] dirtyFieldNumbers = ClassUtils.getFlagsSetTo(this.dirtyFields, true);
      if (dirtyFieldNumbers != null && dirtyFieldNumbers.length > 0) {
         String[] dirtyFieldNames = new String[dirtyFieldNumbers.length];

         for(int i = 0; i < dirtyFieldNumbers.length; ++i) {
            dirtyFieldNames[i] = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(dirtyFieldNumbers[i]).getName();
         }

         return dirtyFieldNames;
      } else {
         return null;
      }
   }

   public String[] getLoadedFieldNames() {
      int[] loadedFieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, true);
      if (loadedFieldNumbers != null && loadedFieldNumbers.length > 0) {
         String[] loadedFieldNames = new String[loadedFieldNumbers.length];

         for(int i = 0; i < loadedFieldNumbers.length; ++i) {
            loadedFieldNames[i] = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(loadedFieldNumbers[i]).getName();
         }

         return loadedFieldNames;
      } else {
         return null;
      }
   }

   public boolean isFieldLoaded(int fieldNumber) {
      return this.loadedFields[fieldNumber];
   }

   protected void clearFieldsByNumbers(int[] fieldNumbers) {
      this.replaceFields(fieldNumbers, HOLLOWFIELDMANAGER);

      for(int i = 0; i < fieldNumbers.length; ++i) {
         this.loadedFields[fieldNumbers[i]] = false;
         this.dirtyFields[fieldNumbers[i]] = false;
      }

   }

   protected void clearDirtyFlags() {
      this.dirty = false;
      ClassUtils.clearFlags(this.dirtyFields);
   }

   protected void clearDirtyFlags(int[] fieldNumbers) {
      this.dirty = false;
      ClassUtils.clearFlags(this.dirtyFields, fieldNumbers);
   }

   public void unloadField(String fieldName) {
      if (this.objectType == 0) {
         AbstractMemberMetaData mmd = this.getClassMetaData().getMetaDataForMember(fieldName);
         this.loadedFields[mmd.getAbsoluteFieldNumber()] = false;
      } else {
         throw new NucleusUserException("Cannot unload field/property of embedded object");
      }
   }

   public boolean isEmbedded() {
      return this.objectType > 0;
   }

   public void providedBooleanField(Object pc, int fieldNumber, boolean currentValue) {
      this.currFM.storeBooleanField(fieldNumber, currentValue);
   }

   public void providedByteField(Object pc, int fieldNumber, byte currentValue) {
      this.currFM.storeByteField(fieldNumber, currentValue);
   }

   public void providedCharField(Object pc, int fieldNumber, char currentValue) {
      this.currFM.storeCharField(fieldNumber, currentValue);
   }

   public void providedDoubleField(Object pc, int fieldNumber, double currentValue) {
      this.currFM.storeDoubleField(fieldNumber, currentValue);
   }

   public void providedFloatField(Object pc, int fieldNumber, float currentValue) {
      this.currFM.storeFloatField(fieldNumber, currentValue);
   }

   public void providedIntField(Object pc, int fieldNumber, int currentValue) {
      this.currFM.storeIntField(fieldNumber, currentValue);
   }

   public void providedLongField(Object pc, int fieldNumber, long currentValue) {
      this.currFM.storeLongField(fieldNumber, currentValue);
   }

   public void providedShortField(Object pc, int fieldNumber, short currentValue) {
      this.currFM.storeShortField(fieldNumber, currentValue);
   }

   public void providedStringField(Object pc, int fieldNumber, String currentValue) {
      this.currFM.storeStringField(fieldNumber, currentValue);
   }

   public void providedObjectField(Object pc, int fieldNumber, Object currentValue) {
      this.currFM.storeObjectField(fieldNumber, currentValue);
   }

   public boolean replacingBooleanField(Object pc, int fieldNumber) {
      boolean value = this.currFM.fetchBooleanField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public byte replacingByteField(Object obj, int fieldNumber) {
      byte value = this.currFM.fetchByteField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public char replacingCharField(Object obj, int fieldNumber) {
      char value = this.currFM.fetchCharField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public double replacingDoubleField(Object obj, int fieldNumber) {
      double value = this.currFM.fetchDoubleField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public float replacingFloatField(Object obj, int fieldNumber) {
      float value = this.currFM.fetchFloatField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public int replacingIntField(Object obj, int fieldNumber) {
      int value = this.currFM.fetchIntField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public long replacingLongField(Object obj, int fieldNumber) {
      long value = this.currFM.fetchLongField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public short replacingShortField(Object obj, int fieldNumber) {
      short value = this.currFM.fetchShortField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public String replacingStringField(Object obj, int fieldNumber) {
      String value = this.currFM.fetchStringField(fieldNumber);
      this.loadedFields[fieldNumber] = true;
      return value;
   }

   public Object replacingObjectField(Object obj, int fieldNumber) {
      try {
         Object value = this.currFM.fetchObjectField(fieldNumber);
         this.loadedFields[fieldNumber] = true;
         return value;
      } catch (AbstractFetchDepthFieldManager.EndOfFetchPlanGraphException var4) {
         return null;
      }
   }

   public void setPcObjectType(short objType) {
      this.objectType = objType;
   }

   public void lock(short lockMode) {
      this.lockMode = lockMode;
   }

   public void unlock() {
      this.lockMode = 0;
   }

   public short getLockMode() {
      return this.lockMode;
   }

   public void registerTransactional() {
      this.myEC.addObjectProvider(this);
   }

   public void setAssociatedValue(Object key, Object value) {
      this.myEC.setObjectProviderAssociatedValue(this, key, value);
   }

   public Object getAssociatedValue(Object key) {
      return this.myEC.getObjectProviderAssociatedValue(this, key);
   }

   public void removeAssociatedValue(Object key) {
      this.myEC.removeObjectProviderAssociatedValue(this, key);
   }

   public boolean containsAssociatedValue(Object key) {
      return this.myEC.containsObjectProviderAssociatedValue(this, key);
   }

   protected static ObjectValueGenerator getObjectValueGenerator(ExecutionContext ec, String genName) {
      if (!objectValGenerators.isEmpty()) {
         ObjectValueGenerator valGen = (ObjectValueGenerator)objectValGenerators.get(genName);
         if (valGen != null) {
            return valGen;
         }
      }

      try {
         ObjectValueGenerator valGen = (ObjectValueGenerator)ec.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_objectvaluegenerator", (String[])(new String[]{"name"}), (String[])(new String[]{genName}), "class-name", (Class[])null, (Object[])null);
         objectValGenerators.put(genName, valGen);
         return valGen;
      } catch (Exception e) {
         NucleusLogger.VALUEGENERATION.info("Exception thrown generating value using objectvaluegenerator " + genName, e);
         throw new NucleusException("Exception thrown generating value for object", e);
      }
   }
}
