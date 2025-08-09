package org.datanucleus.state;

import java.io.PrintWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.FetchPlanForClass;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.cache.CachedPC;
import org.datanucleus.cache.L2CacheRetrieveFieldManager;
import org.datanucleus.enhancement.Detachable;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.enhancer.EnhancementHelper;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.flush.DeleteOperation;
import org.datanucleus.flush.PersistOperation;
import org.datanucleus.flush.UpdateMemberOperation;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.ObjectReferencingStoreManager;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.fieldmanager.AbstractFetchDepthFieldManager;
import org.datanucleus.store.fieldmanager.AttachFieldManager;
import org.datanucleus.store.fieldmanager.DeleteFieldManager;
import org.datanucleus.store.fieldmanager.DetachFieldManager;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.fieldmanager.MakeTransientFieldManager;
import org.datanucleus.store.fieldmanager.PersistFieldManager;
import org.datanucleus.store.fieldmanager.SingleValueFieldManager;
import org.datanucleus.store.fieldmanager.UnsetOwnerFieldManager;
import org.datanucleus.store.objectvaluegenerator.ObjectValueGenerator;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOCollection;
import org.datanucleus.store.types.SCOContainer;
import org.datanucleus.store.types.SCOMap;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.TypeConversionHelper;

public class StateManagerImpl extends AbstractStateManager implements StateManager {
   protected Persistable savedImage = null;
   private static final EnhancementHelper HELPER = (EnhancementHelper)AccessController.doPrivileged(new PrivilegedAction() {
      public Object run() {
         try {
            return EnhancementHelper.getInstance();
         } catch (SecurityException e) {
            throw (new NucleusUserException(Localiser.msg("026000"), e)).setFatal();
         }
      }
   });
   boolean validating = false;

   public StateManagerImpl(ExecutionContext ec, AbstractClassMetaData cmd) {
      super(ec, cmd);
   }

   public void connect(ExecutionContext ec, AbstractClassMetaData cmd) {
      super.connect(ec, cmd);
      this.savedImage = null;
      ec.setAttachDetachReferencedObject(this, (Object)null);
   }

   public void disconnect() {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("026011", StringUtils.toJVMIDString(this.myPC), this));
      }

      if (this.isPostLoadPending()) {
         this.flags &= -2049;
         this.setPostLoadPending(false);
         this.postLoad();
      }

      int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getSCOMutableMemberPositions(), true);
      if (fieldNumbers != null && fieldNumbers.length > 0) {
         this.provideFields(fieldNumbers, new UnsetOwnerFieldManager());
      }

      this.myEC.removeObjectProvider(this);
      this.persistenceFlags = 0;
      ((Persistable)this.myPC).dnReplaceFlags();
      this.setDisconnecting(true);

      try {
         this.replaceStateManager((Persistable)this.myPC, (StateManager)null);
      } finally {
         this.setDisconnecting(false);
      }

      this.clearSavedFields();
      this.preDeleteLoadedFields = null;
      this.objectType = 0;
      this.myPC = null;
      this.myID = null;
      this.myInternalID = null;
      this.myLC = null;
      this.myEC = null;
      this.myFP = null;
      this.myVersion = null;
      this.persistenceFlags = 0;
      this.flags = 0;
      this.restoreValues = false;
      this.transactionalVersion = null;
      this.currFM = null;
      this.dirty = false;
      this.cmd = null;
      this.dirtyFields = null;
      this.loadedFields = null;
   }

   public void initialiseForHollow(Object id, FieldValues fv, Class pcClass) {
      this.myID = id;
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(4);
      this.persistenceFlags = 1;
      if (!IdentityUtils.isDatastoreIdentity(id) && id != null) {
         this.myPC = HELPER.newInstance(pcClass, this, this.myID);
         this.markPKFieldsAsLoaded();
      } else {
         this.myPC = HELPER.newInstance(pcClass, this);
      }

      this.myEC.putObjectIntoLevel1Cache(this);
      if (fv != null) {
         this.loadFieldValues(fv);
      }

   }

   /** @deprecated */
   public void initialiseForHollowAppId(FieldValues fv, Class pcClass) {
      if (this.cmd.getIdentityType() != IdentityType.APPLICATION) {
         throw (new NucleusUserException("This constructor is only for objects using application identity.")).setFatal();
      } else {
         this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(4);
         this.persistenceFlags = 1;
         this.myPC = HELPER.newInstance(pcClass, this);
         if (this.myPC == null) {
            if (!HELPER.getRegisteredClasses().contains(pcClass)) {
               throw (new NucleusUserException(Localiser.msg("026018", pcClass.getName()))).setFatal();
            } else {
               throw (new NucleusUserException(Localiser.msg("026019", pcClass.getName()))).setFatal();
            }
         } else {
            this.loadFieldValues(fv);
            this.myID = ((Persistable)this.myPC).dnNewObjectIdInstance();
            if (!this.cmd.usesSingleFieldIdentityClass()) {
               ((Persistable)this.myPC).dnCopyKeyFieldsToObjectId(this.myID);
            }

         }
      }
   }

   public void initialiseForHollowPreConstructed(Object id, Persistable pc) {
      this.myID = id;
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(4);
      this.persistenceFlags = 1;
      this.myPC = pc;
      this.replaceStateManager((Persistable)this.myPC, this);
      ((Persistable)this.myPC).dnReplaceFlags();
   }

   public void initialiseForPersistentClean(Object id, Persistable pc) {
      this.myID = id;
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(2);
      this.persistenceFlags = 1;
      this.myPC = pc;
      this.replaceStateManager((Persistable)this.myPC, this);
      ((Persistable)this.myPC).dnReplaceFlags();

      for(int i = 0; i < this.loadedFields.length; ++i) {
         this.loadedFields[i] = true;
      }

      this.myEC.putObjectIntoLevel1Cache(this);
   }

   public void initialiseForEmbedded(Persistable pc, boolean copyPc) {
      this.objectType = 1;
      this.myID = null;
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(2);
      this.persistenceFlags = 1;
      this.myPC = pc;
      this.replaceStateManager((Persistable)this.myPC, this);
      if (copyPc) {
         Persistable pcCopy = ((Persistable)this.myPC).dnNewInstance(this);
         pcCopy.dnCopyFields(this.myPC, this.cmd.getAllMemberPositions());
         this.replaceStateManager(pcCopy, this);
         this.myPC = pcCopy;
         this.disconnectClone(pc);
      }

      for(int i = 0; i < this.loadedFields.length; ++i) {
         this.loadedFields[i] = true;
      }

   }

   public void initialiseForPersistentNew(Persistable pc, FieldValues preInsertChanges) {
      this.myPC = pc;
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(1);
      this.persistenceFlags = -1;

      for(int i = 0; i < this.loadedFields.length; ++i) {
         this.loadedFields[i] = true;
      }

      this.replaceStateManager((Persistable)this.myPC, this);
      ((Persistable)this.myPC).dnReplaceFlags();
      this.saveFields();
      this.populateStrategyFields();
      if (preInsertChanges != null) {
         preInsertChanges.fetchFields(this);
      }

      if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
         int[] pkFieldNumbers = this.cmd.getPKMemberPositions();

         for(int i = 0; i < pkFieldNumbers.length; ++i) {
            int fieldNumber = pkFieldNumbers[i];
            AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
            if (this.myEC.getMetaDataManager().getMetaDataForClass(fmd.getType(), this.getExecutionContext().getClassLoaderResolver()) != null) {
               try {
                  if (this.myEC.getMultithreaded()) {
                     this.myEC.getLock().lock();
                     this.lock.lock();
                  }

                  FieldManager prevFM = this.currFM;

                  try {
                     this.currFM = new SingleValueFieldManager();
                     ((Persistable)this.myPC).dnProvideField(fieldNumber);
                     Persistable pkFieldPC = (Persistable)((SingleValueFieldManager)this.currFM).fetchObjectField(fieldNumber);
                     if (pkFieldPC == null) {
                        throw new NucleusUserException(Localiser.msg("026016", fmd.getFullFieldName()));
                     }

                     if (!this.myEC.getApiAdapter().isPersistent(pkFieldPC)) {
                        Object persistedFieldPC = this.myEC.persistObjectInternal(pkFieldPC, (FieldValues)null, (ObjectProvider)null, -1, 0);
                        this.replaceField((Persistable)this.myPC, fieldNumber, persistedFieldPC, false);
                     }
                  } finally {
                     this.currFM = prevFM;
                  }
               } finally {
                  if (this.myEC.getMultithreaded()) {
                     this.lock.unlock();
                     this.myEC.getLock().unlock();
                  }

               }
            }
         }
      }

      this.setIdentity(false);
      if (this.myEC.getTransaction().isActive()) {
         this.myEC.enlistInTransaction(this);
      }

      this.getCallbackHandler().postCreate(this.myPC);
      if (this.myEC.getManageRelations()) {
         ClassLoaderResolver clr = this.myEC.getClassLoaderResolver();
         int[] relationPositions = this.cmd.getRelationMemberPositions(clr, this.myEC.getMetaDataManager());
         if (relationPositions != null) {
            for(int i = 0; i < relationPositions.length; ++i) {
               AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(relationPositions[i]);
               if (RelationType.isBidirectional(mmd.getRelationType(clr))) {
                  Object value = this.provideField(relationPositions[i]);
                  if (value != null) {
                     this.myEC.getRelationshipManager(this).relationChange(relationPositions[i], (Object)null, value);
                  }
               }
            }
         }
      }

   }

   public void initialiseForTransactionalTransient(Persistable pc) {
      this.myPC = pc;
      this.myLC = null;
      this.persistenceFlags = -1;

      for(int i = 0; i < this.loadedFields.length; ++i) {
         this.loadedFields[i] = true;
      }

      ((Persistable)this.myPC).dnReplaceFlags();
      this.populateStrategyFields();
      this.setIdentity(false);
      if (this.myEC.getTransaction().isActive()) {
         this.myEC.enlistInTransaction(this);
      }

   }

   public void initialiseForDetached(Persistable pc, Object id, Object version) {
      this.myID = id;
      this.myPC = pc;
      this.setVersion(version);
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(11);
      ((Persistable)this.myPC).dnReplaceFlags();
      this.replaceStateManager((Persistable)this.myPC, this);
   }

   public void initialiseForPNewToBeDeleted(Persistable pc) {
      this.myID = null;
      this.myPC = pc;
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(1);

      for(int i = 0; i < this.loadedFields.length; ++i) {
         this.loadedFields[i] = true;
      }

      this.replaceStateManager((Persistable)this.myPC, this);
   }

   public void initialiseForCachedPC(CachedPC cachedPC, Object id) {
      this.initialiseForHollow(id, (FieldValues)null, cachedPC.getObjectClass());
      this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(2);
      this.persistenceFlags = -1;
      int[] fieldsToLoad = ClassUtils.getFlagsSetTo(cachedPC.getLoadedFields(), this.myFP.getMemberNumbers(), true);
      if (fieldsToLoad != null) {
         this.myEC.putObjectIntoLevel1Cache(this);
         L2CacheRetrieveFieldManager l2RetFM = new L2CacheRetrieveFieldManager(this, cachedPC);
         this.replaceFields(fieldsToLoad, l2RetFM);

         for(int i = 0; i < fieldsToLoad.length; ++i) {
            this.loadedFields[fieldsToLoad[i]] = true;
         }

         int[] fieldsNotLoaded = l2RetFM.getFieldsNotLoaded();
         if (fieldsNotLoaded != null) {
            for(int i = 0; i < fieldsNotLoaded.length; ++i) {
               this.loadedFields[fieldsNotLoaded[i]] = false;
            }
         }
      }

      if (cachedPC.getVersion() != null) {
         this.setVersion(cachedPC.getVersion());
      }

      this.replaceAllLoadedSCOFieldsWithWrappers();
      if (this.myEC.getTransaction().isActive()) {
         this.myEC.enlistInTransaction(this);
      }

      if (this.areFieldsLoaded(this.myFP.getMemberNumbers())) {
         this.postLoad();
      }

   }

   public Persistable getObject() {
      return (Persistable)this.myPC;
   }

   public void saveFields() {
      this.savedImage = ((Persistable)this.myPC).dnNewInstance(this);
      this.savedImage.dnCopyFields(this.myPC, this.cmd.getAllMemberPositions());
      this.savedFlags = this.persistenceFlags;
      this.savedLoadedFields = (boolean[])this.loadedFields.clone();
   }

   public void clearSavedFields() {
      this.savedImage = null;
      this.savedFlags = 0;
      this.savedLoadedFields = null;
   }

   public void restoreFields() {
      if (this.savedImage != null) {
         this.loadedFields = this.savedLoadedFields;
         this.persistenceFlags = this.savedFlags;
         ((Persistable)this.myPC).dnReplaceFlags();
         ((Persistable)this.myPC).dnCopyFields(this.savedImage, this.cmd.getAllMemberPositions());
         this.clearDirtyFlags();
         this.clearSavedFields();
      }

   }

   public void enlistInTransaction() {
      if (this.myEC.getTransaction().isActive()) {
         this.myEC.enlistInTransaction(this);
         if (this.persistenceFlags == 1 && this.areFieldsLoaded(this.cmd.getDFGMemberPositions())) {
            this.persistenceFlags = -1;
            ((Persistable)this.myPC).dnReplaceFlags();
         }

      }
   }

   public void evictFromTransaction() {
      this.myEC.evictFromTransaction(this);
      this.persistenceFlags = 1;
      ((Persistable)this.myPC).dnReplaceFlags();
   }

   protected void replaceStateManager(final Persistable pc, final StateManager sm) {
      try {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               pc.dnReplaceStateManager(sm);
               return null;
            }
         });
      } catch (SecurityException e) {
         throw (new NucleusUserException(Localiser.msg("026000"), e)).setFatal();
      }
   }

   public StateManager replacingStateManager(Persistable pc, StateManager sm) {
      if (this.myLC == null) {
         throw (new NucleusException("Null LifeCycleState")).setFatal();
      } else if (this.myLC.stateType() == 11) {
         return sm;
      } else if (pc == this.myPC) {
         if (sm == null) {
            return null;
         } else if (sm == this) {
            return this;
         } else if (this.myEC == ((StateManagerImpl)sm).getExecutionContext()) {
            NucleusLogger.PERSISTENCE.debug("StateManagerImpl.replacingStateManager this=" + this + " sm=" + sm + " with same EC");
            ((StateManagerImpl)sm).disconnect();
            return this;
         } else {
            throw this.myEC.getApiAdapter().getUserExceptionForException(Localiser.msg("026003"), (Exception)null);
         }
      } else {
         return pc == this.savedImage ? null : sm;
      }
   }

   public void replaceManagedPC(Persistable pc) {
      if (pc != null) {
         this.replaceStateManager(pc, this);
         this.replaceStateManager((Persistable)this.myPC, (StateManager)null);
         this.myPC = pc;
         this.myEC.putObjectIntoLevel1Cache(this);
      }
   }

   public ExecutionContextReference getExecutionContext(Persistable pc) {
      if (this.myPC != null && this.disconnectClone(pc)) {
         return null;
      } else if (this.myEC == null) {
         return null;
      } else {
         this.myEC.hereIsObjectProvider(this, this.myPC);
         return this.myEC;
      }
   }

   public boolean isDirty(Persistable pc) {
      return this.disconnectClone(pc) ? false : this.myLC.isDirty();
   }

   public boolean isTransactional(Persistable pc) {
      return this.disconnectClone(pc) ? false : this.myLC.isTransactional();
   }

   public boolean isPersistent(Persistable pc) {
      return this.disconnectClone(pc) ? false : this.myLC.isPersistent();
   }

   public boolean isNew(Persistable pc) {
      return this.disconnectClone(pc) ? false : this.myLC.isNew();
   }

   public boolean isDeleted() {
      return this.isDeleted((Persistable)this.myPC);
   }

   public boolean isDeleted(Persistable pc) {
      return this.disconnectClone(pc) ? false : this.myLC.isDeleted();
   }

   public Object getVersion(Persistable pc) {
      return pc == this.myPC ? this.transactionalVersion : null;
   }

   public Object getVersion() {
      return this.getVersion((Persistable)this.myPC);
   }

   public Object getTransactionalVersion() {
      return this.getTransactionalVersion(this.myPC);
   }

   public void clearFields() {
      try {
         this.getCallbackHandler().preClear(this.myPC);
      } finally {
         this.clearFieldsByNumbers(this.cmd.getAllMemberPositions());
         this.clearDirtyFlags();
         if (this.myEC.getStoreManager() instanceof ObjectReferencingStoreManager) {
            ((ObjectReferencingStoreManager)this.myEC.getStoreManager()).notifyObjectIsOutdated(this);
         }

         this.persistenceFlags = 1;
         ((Persistable)this.myPC).dnReplaceFlags();
         this.getCallbackHandler().postClear(this.myPC);
      }

   }

   public void clearNonPrimaryKeyFields() {
      try {
         this.getCallbackHandler().preClear(this.myPC);
      } finally {
         int[] nonpkFields = this.cmd.getNonPKMemberPositions();
         int[] nonPkScoFields = ClassUtils.getFlagsSetTo(this.cmd.getSCOMutableMemberFlags(), ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getNonPKMemberPositions(), true), true);
         if (nonPkScoFields != null) {
            this.provideFields(nonPkScoFields, new UnsetOwnerFieldManager());
         }

         this.clearFieldsByNumbers(nonpkFields);
         this.clearDirtyFlags(nonpkFields);
         if (this.myEC.getStoreManager() instanceof ObjectReferencingStoreManager) {
            ((ObjectReferencingStoreManager)this.myEC.getStoreManager()).notifyObjectIsOutdated(this);
         }

         this.persistenceFlags = 1;
         ((Persistable)this.myPC).dnReplaceFlags();
         this.getCallbackHandler().postClear(this.myPC);
      }

   }

   public void clearLoadedFlags() {
      if (this.myEC.getStoreManager() instanceof ObjectReferencingStoreManager) {
         ((ObjectReferencingStoreManager)this.myEC.getStoreManager()).notifyObjectIsOutdated(this);
      }

      this.persistenceFlags = 1;
      ((Persistable)this.myPC).dnReplaceFlags();
      ClassUtils.clearFlags(this.loadedFields);
   }

   public byte replacingFlags(Persistable pc) {
      return pc != this.myPC ? 0 : this.persistenceFlags;
   }

   public Object provideField(int fieldNumber) {
      return this.provideField((Persistable)this.myPC, fieldNumber);
   }

   protected Object provideField(Persistable pc, int fieldNumber) {
      Object obj;
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         FieldManager prevFM = this.currFM;
         this.currFM = new SingleValueFieldManager();

         try {
            pc.dnProvideField(fieldNumber);
            obj = this.currFM.fetchObjectField(fieldNumber);
         } finally {
            this.currFM = prevFM;
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

      return obj;
   }

   public void provideFields(int[] fieldNumbers, FieldManager fm) {
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         FieldManager prevFM = this.currFM;
         this.currFM = fm;

         try {
            ((Persistable)this.myPC).dnProvideFields(fieldNumbers);
         } finally {
            this.currFM = prevFM;
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

   }

   public void setBooleanField(Persistable pc, int fieldNumber, boolean currentValue, boolean newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue ? Boolean.TRUE : Boolean.FALSE, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue ? Boolean.TRUE : Boolean.FALSE);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue ? Boolean.TRUE : Boolean.FALSE, true);
      }

   }

   public void setByteField(Persistable pc, int fieldNumber, byte currentValue, byte newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setCharField(Persistable pc, int fieldNumber, char currentValue, char newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setDoubleField(Persistable pc, int fieldNumber, double currentValue, double newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setFloatField(Persistable pc, int fieldNumber, float currentValue, float newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setIntField(Persistable pc, int fieldNumber, int currentValue, int newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setLongField(Persistable pc, int fieldNumber, long currentValue, long newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setShortField(Persistable pc, int fieldNumber, short currentValue, short newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (!this.loadedFields[fieldNumber] || currentValue != newValue) {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, currentValue);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (!this.myEC.getTransaction().isActive()) {
               this.myEC.processNontransactionalUpdate();
            }
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setStringField(Persistable pc, int fieldNumber, String currentValue, String newValue) {
      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         if (this.loadedFields[fieldNumber]) {
            if (currentValue == null) {
               if (newValue == null) {
                  return;
               }
            } else if (currentValue.equals(newValue)) {
               return;
            }
         }

         if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
            String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
            if (!this.containsAssociatedValue(key)) {
               this.setAssociatedValue(key, currentValue);
            }
         }

         this.updateField(pc, fieldNumber, newValue);
         if (!this.myEC.getTransaction().isActive()) {
            this.myEC.processNontransactionalUpdate();
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   public void setObjectField(Persistable pc, int fieldNumber, Object currentValue, Object newValue) {
      if (currentValue != null && currentValue != newValue && currentValue instanceof Persistable) {
         ObjectProvider<?> currentSM = this.myEC.findObjectProvider(currentValue);
         if (currentSM != null && currentSM.isEmbedded()) {
            this.myEC.removeEmbeddedOwnerRelation(this, fieldNumber, currentSM);
         }
      }

      if (pc != this.myPC) {
         this.replaceField(pc, fieldNumber, newValue, true);
         this.disconnectClone(pc);
      } else if (this.myLC != null) {
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            this.loadUnloadedFieldsInFetchPlanAndVersion();
         }

         boolean loadedOldValue = false;
         Object oldValue = currentValue;
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         ClassLoaderResolver clr = this.myEC.getClassLoaderResolver();
         RelationType relationType = mmd.getRelationType(clr);
         this.myEC.removeObjectFromLevel2Cache(this.myID);
         if (!this.loadedFields[fieldNumber] && currentValue == null) {
            if (this.myEC.getManageRelations() && (relationType == RelationType.ONE_TO_ONE_BI || relationType == RelationType.MANY_TO_ONE_BI)) {
               this.loadField(fieldNumber);
               loadedOldValue = true;
               oldValue = this.provideField(fieldNumber);
            }

            if (relationType != RelationType.NONE && newValue == null && (mmd.isDependent() || mmd.isCascadeRemoveOrphans())) {
               this.loadField(fieldNumber);
               loadedOldValue = true;
               oldValue = this.provideField(fieldNumber);
            }
         }

         boolean equal = false;
         boolean equalButContainerRefChanged = false;
         if (oldValue == null && newValue == null) {
            equal = true;
         } else if (oldValue != null && newValue != null) {
            if (RelationType.isRelationSingleValued(relationType)) {
               if (oldValue == newValue) {
                  equal = true;
               }
            } else if (oldValue.equals(newValue)) {
               equal = true;
               if (oldValue instanceof SCOContainer && ((SCOContainer)oldValue).getValue() != newValue && !(newValue instanceof SCO)) {
                  equalButContainerRefChanged = true;
               }
            }
         }

         boolean needsSCOUpdating = false;
         if (this.loadedFields[fieldNumber] && equal && !equalButContainerRefChanged && !mmd.hasArray()) {
            if (loadedOldValue) {
               this.updateField(pc, fieldNumber, newValue);
            }
         } else {
            if (this.cmd.getIdentityType() == IdentityType.NONDURABLE && relationType == RelationType.NONE) {
               String key = "FIELD_VALUE.ORIGINAL." + fieldNumber;
               if (!this.containsAssociatedValue(key)) {
                  this.setAssociatedValue(key, oldValue);
               }
            }

            if (oldValue instanceof SCO) {
               if (oldValue instanceof SCOContainer) {
                  ((SCOContainer)oldValue).load();
               }

               if (!equalButContainerRefChanged) {
                  ((SCO)oldValue).unsetOwner();
               }
            }

            if (newValue instanceof SCO) {
               SCO sco = (SCO)newValue;
               Object owner = sco.getOwner();
               if (owner != null) {
                  throw this.myEC.getApiAdapter().getUserExceptionForException(Localiser.msg("026007", sco.getFieldName(), owner), (Exception)null);
               }
            }

            this.updateField(pc, fieldNumber, newValue);
            if (this.cmd.getSCOMutableMemberFlags()[fieldNumber] && !(newValue instanceof SCO)) {
               needsSCOUpdating = true;
            }
         }

         if (!equal) {
            if (RelationType.isBidirectional(relationType) && this.myEC.getManageRelations()) {
               this.myEC.getRelationshipManager(this).relationChange(fieldNumber, oldValue, newValue);
            }

            if (this.myEC.operationQueueIsActive()) {
               this.myEC.addOperationToQueue(new UpdateMemberOperation(this, fieldNumber, newValue, oldValue));
            }
         } else if (equalButContainerRefChanged) {
            ((SCOContainer)oldValue).setValue(newValue);
            newValue = oldValue;
            needsSCOUpdating = false;
            this.replaceField(fieldNumber, oldValue);
         }

         if (needsSCOUpdating) {
            newValue = SCOUtils.wrapAndReplaceSCOField(this, fieldNumber, newValue, oldValue, true);
         }

         if (oldValue != null && newValue == null && RelationType.isRelationSingleValued(relationType) && (mmd.isDependent() || mmd.isCascadeRemoveOrphans()) && this.myEC.getApiAdapter().isPersistent(oldValue)) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("026026", oldValue, mmd.getFullFieldName()));
            this.myEC.deleteObjectInternal(oldValue);
         }

         if (!this.myEC.getTransaction().isActive()) {
            this.myEC.processNontransactionalUpdate();
         }
      } else {
         this.replaceField(pc, fieldNumber, newValue, true);
      }

   }

   protected void updateField(Persistable pc, int fieldNumber, Object value) {
      boolean wasDirty = this.dirty;
      if (this.activity != ActivityState.INSERTING && this.activity != ActivityState.INSERTING_CALLBACKS) {
         if (!wasDirty) {
            this.getCallbackHandler().preDirty(this.myPC);
         }

         this.transitionWriteField();
         this.dirty = true;
         this.dirtyFields[fieldNumber] = true;
         this.loadedFields[fieldNumber] = true;
      }

      this.replaceField(pc, fieldNumber, value, true);
      if (this.dirty && !wasDirty) {
         this.getCallbackHandler().postDirty(this.myPC);
      }

      if (this.activity == ActivityState.NONE && !this.isFlushing() && (!this.myLC.isTransactional() || this.myLC.isPersistent())) {
         this.myEC.markDirty(this, true);
      }

   }

   protected void replaceField(Persistable pc, int fieldNumber, Object value) {
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         FieldManager prevFM = this.currFM;
         this.currFM = new SingleValueFieldManager();

         try {
            this.currFM.storeObjectField(fieldNumber, value);
            pc.dnReplaceField(fieldNumber);
         } finally {
            this.currFM = prevFM;
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

   }

   protected boolean disconnectClone(Persistable pc) {
      if (this.isDetaching()) {
         return false;
      } else if (pc != this.myPC) {
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("026001", StringUtils.toJVMIDString(pc), this));
         }

         pc.dnReplaceFlags();
         this.replaceStateManager(pc, (StateManager)null);
         return true;
      } else {
         return false;
      }
   }

   public void retrieveDetachState(ObjectProvider op) {
      if (op.getObject() instanceof Detachable) {
         ((AbstractStateManager)op).setRetrievingDetachedState(true);
         ((Detachable)op.getObject()).dnReplaceDetachedState();
         ((AbstractStateManager)op).setRetrievingDetachedState(false);
      }

   }

   public void resetDetachState() {
      if (this.getObject() instanceof Detachable) {
         this.setResettingDetachedState(true);

         try {
            ((Detachable)this.getObject()).dnReplaceDetachedState();
         } finally {
            this.setResettingDetachedState(false);
         }
      }

   }

   public Object[] replacingDetachedState(Detachable pc, Object[] currentState) {
      if (this.isResettingDetachedState()) {
         return null;
      } else if (!this.isRetrievingDetachedState()) {
         Object[] state = new Object[4];
         state[0] = this.myID;
         state[1] = this.getVersion((Persistable)this.myPC);
         BitSet loadedState = new BitSet();

         for(int i = 0; i < this.loadedFields.length; ++i) {
            if (this.loadedFields[i]) {
               loadedState.set(i);
            } else {
               loadedState.clear(i);
            }
         }

         state[2] = loadedState;
         BitSet modifiedState = new BitSet();

         for(int i = 0; i < this.dirtyFields.length; ++i) {
            if (this.dirtyFields[i]) {
               modifiedState.set(i);
            } else {
               modifiedState.clear(i);
            }
         }

         state[3] = modifiedState;
         return state;
      } else {
         BitSet jdoLoadedFields = (BitSet)currentState[2];

         for(int i = 0; i < this.loadedFields.length; ++i) {
            this.loadedFields[i] = jdoLoadedFields.get(i);
         }

         BitSet jdoModifiedFields = (BitSet)currentState[3];

         for(int i = 0; i < this.dirtyFields.length; ++i) {
            this.dirtyFields[i] = jdoModifiedFields.get(i);
         }

         this.setVersion(currentState[1]);
         return currentState;
      }
   }

   public boolean getBooleanField(Persistable pc, int fieldNumber, boolean currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public byte getByteField(Persistable pc, int fieldNumber, byte currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public char getCharField(Persistable pc, int fieldNumber, char currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public double getDoubleField(Persistable pc, int fieldNumber, double currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public float getFloatField(Persistable pc, int fieldNumber, float currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public int getIntField(Persistable pc, int fieldNumber, int currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public long getLongField(Persistable pc, int fieldNumber, long currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public short getShortField(Persistable pc, int fieldNumber, short currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public String getStringField(Persistable pc, int fieldNumber, String currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   public Object getObjectField(Persistable pc, int fieldNumber, Object currentValue) {
      throw new NucleusException(Localiser.msg("026006"));
   }

   /** @deprecated */
   public void checkInheritance(FieldValues fv) {
      ClassLoaderResolver clr = this.myEC.getClassLoaderResolver();
      String className = this.getStoreManager().getClassNameForObjectID(this.myID, clr, this.myEC);
      if (className == null) {
         throw new NucleusObjectNotFoundException(Localiser.msg("026013", IdentityUtils.getPersistableIdentityForId(this.myID)), this.myID);
      } else {
         if (!this.cmd.getFullClassName().equals(className)) {
            Class pcClass;
            try {
               pcClass = clr.classForName(className, this.myID.getClass().getClassLoader(), true);
               this.cmd = this.myEC.getMetaDataManager().getMetaDataForClass(pcClass, clr);
            } catch (ClassNotResolvedException e) {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("026014", IdentityUtils.getPersistableIdentityForId(this.myID)));
               throw new NucleusUserException(Localiser.msg("026014", IdentityUtils.getPersistableIdentityForId(this.myID)), e);
            }

            if (this.cmd == null) {
               throw (new NucleusUserException(Localiser.msg("026012", pcClass))).setFatal();
            }

            if (this.cmd.getIdentityType() != IdentityType.APPLICATION) {
               throw (new NucleusUserException("This method should only be used for objects using application identity.")).setFatal();
            }

            this.myFP = this.myEC.getFetchPlan().getFetchPlanForClass(this.cmd);
            int fieldCount = this.cmd.getMemberCount();
            this.dirtyFields = new boolean[fieldCount];
            this.loadedFields = new boolean[fieldCount];
            this.myPC = HELPER.newInstance(pcClass, this);
            if (this.myPC == null) {
               throw (new NucleusUserException(Localiser.msg("026018", this.cmd.getFullClassName()))).setFatal();
            }

            this.loadFieldValues(fv);
            this.myID = ((Persistable)this.myPC).dnNewObjectIdInstance();
            if (!this.cmd.usesSingleFieldIdentityClass()) {
               ((Persistable)this.myPC).dnCopyKeyFieldsToObjectId(this.myID);
            }
         }

      }
   }

   private void populateStrategyFields() {
      int totalFieldCount = this.cmd.getNoOfInheritedManagedMembers() + this.cmd.getNoOfManagedMembers();

      for(int fieldNumber = 0; fieldNumber < totalFieldCount; ++fieldNumber) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         IdentityStrategy strategy = mmd.getValueStrategy();
         if (strategy != null && !this.getStoreManager().isStrategyDatastoreAttributed(this.cmd, fieldNumber)) {
            boolean applyStrategy = true;
            if (!mmd.getType().isPrimitive() && mmd.hasExtension("strategy-when-notnull") && mmd.getValueForExtension("strategy-when-notnull").equalsIgnoreCase("false") && this.provideField(fieldNumber) != null) {
               applyStrategy = false;
            }

            if (applyStrategy) {
               Object obj = this.getStoreManager().getStrategyValue(this.myEC, this.cmd, fieldNumber);
               this.replaceField(fieldNumber, obj);
            }
         } else if (mmd.hasExtension("object-value-generator")) {
            String valGenName = mmd.getValueForExtension("object-value-generator");
            ObjectValueGenerator valGen = getObjectValueGenerator(this.myEC, valGenName);
            Object value = valGen.generate(this.myEC, this.myPC, mmd.getExtensions());
            this.replaceField((Persistable)this.myPC, fieldNumber, value, true);
         }
      }

   }

   public void loadFieldValues(FieldValues fv) {
      FetchPlanForClass origFetchPlan = this.myFP;
      FetchPlan loadFetchPlan = fv.getFetchPlanForLoading();
      if (loadFetchPlan != null) {
         this.myFP = loadFetchPlan.getFetchPlanForClass(this.cmd);
      }

      boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
      if (this.loadedFields.length == 0) {
         callPostLoad = true;
      }

      fv.fetchFields(this);
      if (callPostLoad && this.areFieldsLoaded(this.myFP.getMemberNumbers())) {
         this.postLoad();
      }

      this.myFP = origFetchPlan;
   }

   private void setIdentity(boolean afterPreStore) {
      if (!this.cmd.isEmbeddedOnly()) {
         if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
            if (this.cmd.getIdentityMetaData() == null || !this.getStoreManager().isStrategyDatastoreAttributed(this.cmd, -1)) {
               this.myID = this.myEC.newObjectId(this.cmd.getFullClassName(), this.myPC);
            }
         } else if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
            boolean idSetInDatastore = false;
            int[] pkMemberNumbers = this.cmd.getPKMemberPositions();

            for(int i = 0; i < pkMemberNumbers.length; ++i) {
               int fieldNumber = pkMemberNumbers[i];
               AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
               if (fmd.isPrimaryKey()) {
                  if (this.getStoreManager().isStrategyDatastoreAttributed(this.cmd, fieldNumber)) {
                     idSetInDatastore = true;
                     break;
                  }

                  if (pkMemberNumbers.length == 1 && afterPreStore) {
                     try {
                        if (this.provideField(fieldNumber) == null) {
                           throw (new NucleusUserException(Localiser.msg("026017", this.cmd.getFullClassName(), fmd.getName()))).setFatal();
                        }
                     } catch (Exception var8) {
                        return;
                     }
                  }
               }
            }

            if (!idSetInDatastore) {
               this.myID = this.myEC.newObjectId(this.cmd.getFullClassName(), this.myPC);
            }
         }

         if (this.myInternalID != this.myID && this.myID != null && this.myEC.getApiAdapter().getIdForObject(this.myPC) != null) {
            this.myEC.replaceObjectId(this.myPC, this.myInternalID, this.myID);
            this.myInternalID = this.myID;
         }

      }
   }

   public void makeDirty(int fieldNumber) {
      if (this.activity != ActivityState.DELETING) {
         boolean wasDirty = this.preWriteField(fieldNumber);
         this.postWriteField(wasDirty);
         List<ExecutionContext.EmbeddedOwnerRelation> embeddedOwners = this.myEC.getOwnerInformationForEmbedded(this);
         if (embeddedOwners != null) {
            for(ExecutionContext.EmbeddedOwnerRelation owner : embeddedOwners) {
               AbstractStateManager ownerOP = (AbstractStateManager)owner.getOwnerOP();
               if ((ownerOP.flags & 256) == 0) {
                  ownerOP.makeDirty(owner.getOwnerFieldNum());
               }
            }
         }
      }

   }

   public void makeDirty(Persistable pc, String fieldName) {
      if (!this.disconnectClone(pc)) {
         int fieldNumber = this.cmd.getAbsolutePositionOfMember(fieldName);
         if (fieldNumber == -1) {
            throw this.myEC.getApiAdapter().getUserExceptionForException(Localiser.msg("026002", fieldName, this.cmd.getFullClassName()), (Exception)null);
         }

         this.makeDirty(fieldNumber);
      }

   }

   public Object getObjectId(Persistable pc) {
      if (this.disconnectClone(pc)) {
         return null;
      } else {
         try {
            return this.getExternalObjectId(pc);
         } catch (NucleusException ne) {
            throw this.myEC.getApiAdapter().getApiExceptionForNucleusException(ne);
         }
      }
   }

   public Object getTransactionalObjectId(Persistable pc) {
      return this.getObjectId(pc);
   }

   public void setPostStoreNewObjectId(Object id) {
      if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
         if (IdentityUtils.isDatastoreIdentity(id)) {
            this.myID = id;
         } else {
            this.myID = this.myEC.getNucleusContext().getIdentityManager().getDatastoreId(this.cmd.getFullClassName(), id);
         }
      } else if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
         try {
            this.myID = null;
            int fieldCount = this.cmd.getMemberCount();

            for(int fieldNumber = 0; fieldNumber < fieldCount; ++fieldNumber) {
               AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
               if (fmd.isPrimaryKey() && this.getStoreManager().isStrategyDatastoreAttributed(this.cmd, fieldNumber)) {
                  this.replaceField((Persistable)this.myPC, fieldNumber, TypeConversionHelper.convertTo(id, fmd.getType()), false);
               }
            }
         } catch (Exception e) {
            NucleusLogger.PERSISTENCE.error(e);
         } finally {
            this.myID = this.myEC.getNucleusContext().getIdentityManager().getApplicationId((Object)this.getObject(), (AbstractClassMetaData)this.cmd);
         }
      }

      if (this.myInternalID != this.myID && this.myID != null) {
         this.myEC.replaceObjectId(this.myPC, this.myInternalID, this.myID);
         this.myInternalID = this.myID;
      }

   }

   protected Object getExternalObjectId(Object obj) {
      List<ExecutionContext.EmbeddedOwnerRelation> embeddedOwners = this.myEC.getOwnerInformationForEmbedded(this);
      if (embeddedOwners != null) {
         return this.myID;
      } else {
         if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
            if (!this.isFlushing() && !this.isFlushedNew() && this.activity != ActivityState.INSERTING && this.activity != ActivityState.INSERTING_CALLBACKS && this.myLC.stateType() == 1 && this.getStoreManager().isStrategyDatastoreAttributed(this.cmd, -1)) {
               this.flush();
            }
         } else if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
            if (!this.isFlushing() && !this.isFlushedNew() && this.activity != ActivityState.INSERTING && this.activity != ActivityState.INSERTING_CALLBACKS && this.myLC.stateType() == 1) {
               int[] pkFieldNumbers = this.cmd.getPKMemberPositions();

               for(int i = 0; i < pkFieldNumbers.length; ++i) {
                  if (this.getStoreManager().isStrategyDatastoreAttributed(this.cmd, pkFieldNumbers[i])) {
                     this.flush();
                     break;
                  }
               }
            }

            if (this.cmd.usesSingleFieldIdentityClass()) {
               return this.myID;
            }

            return this.myEC.getNucleusContext().getIdentityManager().getApplicationId(this.myPC, this.cmd);
         }

         return this.myID;
      }
   }

   public Object getExternalObjectId() {
      return this.getExternalObjectId(this.myPC);
   }

   protected void loadSpecifiedFields(int[] fieldNumbers) {
      if (!this.myEC.getApiAdapter().isDetached(this.myPC)) {
         int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
         if (unloadedFieldNumbers != null && !this.isEmbedded()) {
            this.loadFieldsFromDatastore(unloadedFieldNumbers);
            this.updateLevel2CacheForFields(unloadedFieldNumbers);
         }

      }
   }

   public void loadField(int fieldNumber) {
      if (!this.loadedFields[fieldNumber]) {
         this.loadSpecifiedFields(new int[]{fieldNumber});
      }
   }

   public void loadUnloadedRelationFields() {
      int[] fieldsConsidered = this.cmd.getRelationMemberPositions(this.myEC.getClassLoaderResolver(), this.myEC.getMetaDataManager());
      int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, fieldsConsidered, false);
      if (fieldNumbers != null && fieldNumbers.length != 0) {
         if (this.preDeleteLoadedFields != null && (this.myLC.isDeleted() && this.myEC.isFlushing() || this.activity == ActivityState.DELETING)) {
            fieldNumbers = ClassUtils.getFlagsSetTo(this.preDeleteLoadedFields, fieldNumbers, false);
         }

         if (fieldNumbers != null && fieldNumbers.length > 0) {
            boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
            int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
            if (unloadedFieldNumbers != null) {
               this.loadFieldsFromDatastore(unloadedFieldNumbers);
            }

            int[] secondClassMutableFieldNumbers = this.cmd.getSCOMutableMemberPositions();

            for(int i = 0; i < secondClassMutableFieldNumbers.length; ++i) {
               AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(secondClassMutableFieldNumbers[i]);
               if (mmd.getRelationType(this.myEC.getClassLoaderResolver()) != RelationType.NONE) {
                  SingleValueFieldManager sfv = new SingleValueFieldManager();
                  this.provideFields(new int[]{secondClassMutableFieldNumbers[i]}, sfv);
                  Object value = sfv.fetchObjectField(i);
                  if (value instanceof SCOContainer) {
                     ((SCOContainer)value).load();
                  }
               }
            }

            this.updateLevel2CacheForFields(fieldNumbers);
            if (callPostLoad) {
               this.postLoad();
            }
         }

      }
   }

   public void loadUnloadedFields() {
      int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getAllMemberPositions(), false);
      if (fieldNumbers != null && fieldNumbers.length != 0) {
         if (this.preDeleteLoadedFields != null && (this.myLC.isDeleted() && this.myEC.isFlushing() || this.activity == ActivityState.DELETING)) {
            fieldNumbers = ClassUtils.getFlagsSetTo(this.preDeleteLoadedFields, fieldNumbers, false);
         }

         if (fieldNumbers != null && fieldNumbers.length > 0) {
            boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
            int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
            if (unloadedFieldNumbers != null) {
               this.loadFieldsFromDatastore(unloadedFieldNumbers);
            }

            int[] secondClassMutableFieldNumbers = this.cmd.getSCOMutableMemberPositions();

            for(int i = 0; i < secondClassMutableFieldNumbers.length; ++i) {
               SingleValueFieldManager sfv = new SingleValueFieldManager();
               this.provideFields(new int[]{secondClassMutableFieldNumbers[i]}, sfv);
               Object value = sfv.fetchObjectField(i);
               if (value instanceof SCOContainer) {
                  ((SCOContainer)value).load();
               }
            }

            this.updateLevel2CacheForFields(fieldNumbers);
            if (callPostLoad) {
               this.postLoad();
            }
         }

      }
   }

   public void loadUnloadedFieldsInFetchPlan() {
      int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.myFP.getMemberNumbers(), false);
      if (fieldNumbers != null && fieldNumbers.length > 0) {
         boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
         int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
         if (unloadedFieldNumbers != null) {
            this.loadFieldsFromDatastore(unloadedFieldNumbers);
            this.updateLevel2CacheForFields(unloadedFieldNumbers);
         }

         if (callPostLoad) {
            this.postLoad();
         }
      }

   }

   protected void loadUnloadedFieldsInFetchPlanAndVersion() {
      if (!this.cmd.isVersioned()) {
         this.loadUnloadedFieldsInFetchPlan();
      } else {
         int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.myFP.getMemberNumbers(), false);
         if (fieldNumbers == null) {
            fieldNumbers = new int[0];
         }

         boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
         int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
         if (unloadedFieldNumbers != null) {
            this.loadFieldsFromDatastore(unloadedFieldNumbers);
            this.updateLevel2CacheForFields(unloadedFieldNumbers);
         }

         if (callPostLoad && fieldNumbers.length > 0) {
            this.postLoad();
         }
      }

   }

   public void loadUnloadedFieldsOfClassInFetchPlan(FetchPlan fetchPlan) {
      FetchPlanForClass fpc = fetchPlan.getFetchPlanForClass(this.cmd);
      int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, fpc.getMemberNumbers(), false);
      if (fieldNumbers != null && fieldNumbers.length > 0) {
         boolean callPostLoad = fpc.isToCallPostLoadFetchPlan(this.loadedFields);
         int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
         if (unloadedFieldNumbers != null) {
            this.loadFieldsFromDatastore(unloadedFieldNumbers);
            this.updateLevel2CacheForFields(unloadedFieldNumbers);
         }

         if (callPostLoad) {
            this.postLoad();
         }
      }

   }

   public void refreshFieldsInFetchPlan() {
      int[] fieldNumbers = this.myFP.getMemberNumbers();
      if (fieldNumbers != null && fieldNumbers.length > 0) {
         this.clearDirtyFlags(fieldNumbers);
         ClassUtils.clearFlags(this.loadedFields, fieldNumbers);
         this.markPKFieldsAsLoaded();
         boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
         this.setTransactionalVersion((Object)null);
         this.loadFieldsFromDatastore(fieldNumbers);
         if (this.cmd.hasRelations(this.myEC.getClassLoaderResolver(), this.myEC.getMetaDataManager())) {
            for(int i = 0; i < fieldNumbers.length; ++i) {
               AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumbers[i]);
               RelationType relationType = fmd.getRelationType(this.myEC.getClassLoaderResolver());
               if (relationType != RelationType.NONE && fmd.isCascadeRefresh()) {
                  Object value = this.provideField(fieldNumbers[i]);
                  if (value != null) {
                     if (value instanceof Collection) {
                        SCOUtils.refreshFetchPlanFieldsForCollection(this, ((Collection)value).toArray());
                     } else if (value instanceof Map) {
                        SCOUtils.refreshFetchPlanFieldsForMap(this, ((Map)value).entrySet());
                     } else if (value instanceof Persistable) {
                        this.myEC.refreshObject(value);
                     }
                  }
               }
            }
         }

         this.updateLevel2CacheForFields(fieldNumbers);
         if (callPostLoad) {
            this.postLoad();
         }

         this.getCallbackHandler().postRefresh(this.myPC);
      }

   }

   public void refreshLoadedFields() {
      int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.myFP.getMemberNumbers(), true);
      if (fieldNumbers != null && fieldNumbers.length > 0) {
         this.clearDirtyFlags();
         ClassUtils.clearFlags(this.loadedFields);
         this.markPKFieldsAsLoaded();
         boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
         this.loadFieldsFromDatastore(fieldNumbers);
         this.updateLevel2CacheForFields(fieldNumbers);
         if (callPostLoad) {
            this.postLoad();
         }
      }

   }

   public boolean isLoaded(int fieldNumber) {
      return this.isLoaded((Persistable)this.myPC, fieldNumber);
   }

   public boolean isLoaded(Persistable pc, int fieldNumber) {
      try {
         if (this.disconnectClone(pc)) {
            return true;
         } else {
            boolean checkRead = true;
            boolean beingDeleted = false;
            if (this.myLC.isDeleted() && this.myEC.isFlushing() || this.activity == ActivityState.DELETING) {
               checkRead = false;
               beingDeleted = true;
            }

            if (checkRead) {
               this.transitionReadField(this.loadedFields[fieldNumber]);
            }

            if (!this.loadedFields[fieldNumber]) {
               if (this.objectType != 0) {
                  return true;
               }

               if (beingDeleted && this.preDeleteLoadedFields != null && this.preDeleteLoadedFields[fieldNumber]) {
                  return true;
               }

               if (!beingDeleted && this.myFP.hasMember(fieldNumber)) {
                  this.loadUnloadedFieldsInFetchPlan();
               } else {
                  this.loadSpecifiedFields(new int[]{fieldNumber});
               }
            }

            return true;
         }
      } catch (NucleusException ne) {
         NucleusLogger.PERSISTENCE.warn("Exception thrown by StateManager.isLoaded", ne);
         throw this.myEC.getApiAdapter().getApiExceptionForNucleusException(ne);
      }
   }

   public void replaceFieldValue(int fieldNumber, Object newValue) {
      if (!this.myLC.isDeleted()) {
         boolean currentWasDirty = this.preWriteField(fieldNumber);
         this.replaceField((Persistable)this.myPC, fieldNumber, newValue, true);
         this.postWriteField(currentWasDirty);
      }
   }

   public void replaceField(int fieldNumber, Object value) {
      this.replaceField((Persistable)this.myPC, fieldNumber, value, false);
   }

   public void replaceFieldMakeDirty(int fieldNumber, Object value) {
      this.replaceField((Persistable)this.myPC, fieldNumber, value, true);
   }

   protected void replaceField(Persistable pc, int fieldNumber, Object value, boolean makeDirty) {
      List<ExecutionContext.EmbeddedOwnerRelation> embeddedOwners = this.myEC.getOwnerInformationForEmbedded(this);
      if (embeddedOwners != null) {
         for(ExecutionContext.EmbeddedOwnerRelation ownerRel : embeddedOwners) {
            AbstractStateManager ownerOP = (AbstractStateManager)ownerRel.getOwnerOP();
            AbstractMemberMetaData ownerMmd = ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(ownerRel.getOwnerFieldNum());
            if (ownerMmd.getCollection() != null) {
               Object ownerField = ownerOP.provideField(ownerRel.getOwnerFieldNum());
               if (ownerField instanceof SCOCollection) {
                  ((SCOCollection)ownerField).updateEmbeddedElement(this.myPC, fieldNumber, value, makeDirty);
               }
            } else if (ownerMmd.getMap() != null) {
               Object ownerField = ownerOP.provideField(ownerRel.getOwnerFieldNum());
               if (ownerField instanceof SCOMap) {
                  if (this.objectType == 3) {
                     ((SCOMap)ownerField).updateEmbeddedKey(this.myPC, fieldNumber, value, makeDirty);
                  }

                  if (this.objectType == 4) {
                     ((SCOMap)ownerField).updateEmbeddedValue(this.myPC, fieldNumber, value, makeDirty);
                  }
               }
            } else if ((ownerOP.flags & 256) == 0) {
               if (makeDirty) {
                  ownerOP.replaceFieldMakeDirty(ownerRel.getOwnerFieldNum(), pc);
               } else {
                  ownerOP.replaceField(ownerRel.getOwnerFieldNum(), pc);
               }
            }
         }
      }

      if (embeddedOwners == null && makeDirty && !this.myLC.isDeleted() && this.myEC.getTransaction().isActive()) {
         boolean wasDirty = this.preWriteField(fieldNumber);
         this.replaceField(pc, fieldNumber, value);
         this.postWriteField(wasDirty);
      } else {
         this.replaceField(pc, fieldNumber, value);
      }

   }

   public void replaceFields(int[] fieldNumbers, FieldManager fm, boolean replaceWhenDirty) {
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         FieldManager prevFM = this.currFM;
         this.currFM = fm;

         try {
            int[] fieldsToReplace = fieldNumbers;
            if (!replaceWhenDirty) {
               int numberToReplace = fieldNumbers.length;

               for(int i = 0; i < fieldNumbers.length; ++i) {
                  if (this.dirtyFields[fieldNumbers[i]]) {
                     --numberToReplace;
                  }
               }

               if (numberToReplace > 0 && numberToReplace != fieldNumbers.length) {
                  fieldsToReplace = new int[numberToReplace];
                  int n = 0;

                  for(int i = 0; i < fieldNumbers.length; ++i) {
                     if (!this.dirtyFields[fieldNumbers[i]]) {
                        fieldsToReplace[n++] = fieldNumbers[i];
                     }
                  }
               } else if (numberToReplace == 0) {
                  fieldsToReplace = null;
               }
            }

            if (fieldsToReplace != null) {
               ((Persistable)this.myPC).dnReplaceFields(fieldsToReplace);
            }
         } finally {
            this.currFM = prevFM;
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

   }

   public void replaceFields(int[] fieldNumbers, FieldManager fm) {
      this.replaceFields(fieldNumbers, fm, true);
   }

   public void replaceNonLoadedFields(int[] fieldNumbers, FieldManager fm) {
      try {
         if (this.myEC.getMultithreaded()) {
            this.myEC.getLock().lock();
            this.lock.lock();
         }

         FieldManager prevFM = this.currFM;
         this.currFM = fm;
         boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);

         try {
            int[] fieldsToReplace = ClassUtils.getFlagsSetTo(this.loadedFields, fieldNumbers, false);
            if (fieldsToReplace != null && fieldsToReplace.length > 0) {
               ((Persistable)this.myPC).dnReplaceFields(fieldsToReplace);
            }
         } finally {
            this.currFM = prevFM;
         }

         if (callPostLoad && this.areFieldsLoaded(this.myFP.getMemberNumbers())) {
            this.postLoad();
         }
      } finally {
         if (this.myEC.getMultithreaded()) {
            this.lock.unlock();
            this.myEC.getLock().unlock();
         }

      }

   }

   public void replaceAllLoadedSCOFieldsWithWrappers() {
      boolean[] scoMutableFieldFlags = this.cmd.getSCOMutableMemberFlags();

      for(int i = 0; i < scoMutableFieldFlags.length; ++i) {
         if (scoMutableFieldFlags[i] && this.loadedFields[i]) {
            Object value = this.provideField(i);
            if (!(value instanceof SCO)) {
               SCOUtils.wrapSCOField(this, i, value, true);
            }
         }
      }

   }

   public void replaceAllLoadedSCOFieldsWithValues() {
      boolean[] scoMutableFieldFlags = this.cmd.getSCOMutableMemberFlags();

      for(int i = 0; i < scoMutableFieldFlags.length; ++i) {
         if (scoMutableFieldFlags[i] && this.loadedFields[i]) {
            Object value = this.provideField(i);
            if (value instanceof SCO) {
               SCOUtils.unwrapSCOField(this, i, (SCO)value);
            }
         }
      }

   }

   public void updateOwnerFieldInEmbeddedField(int fieldNumber, Object value) {
      if (value != null) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         RelationType relationType = mmd.getRelationType(this.myEC.getClassLoaderResolver());
         if (RelationType.isRelationSingleValued(relationType) && mmd.getEmbeddedMetaData() != null && mmd.getEmbeddedMetaData().getOwnerMember() != null) {
            ObjectProvider subSM = this.myEC.findObjectProvider(value);
            int ownerAbsFieldNum = subSM.getClassMetaData().getAbsolutePositionOfMember(mmd.getEmbeddedMetaData().getOwnerMember());
            if (ownerAbsFieldNum >= 0) {
               this.flags |= 256;
               subSM.replaceFieldMakeDirty(ownerAbsFieldNum, this.myPC);
               this.flags &= -257;
            }
         }
      }

   }

   public void makePersistent() {
      if (!this.myLC.isDeleted() || this.myEC.getNucleusContext().getApiAdapter().allowPersistOfDeletedObject()) {
         if (this.activity == ActivityState.NONE) {
            if (this.myEC.operationQueueIsActive()) {
               this.myEC.addOperationToQueue(new PersistOperation(this));
            }

            if (this.dirty && !this.myLC.isDeleted() && this.myLC.isTransactional() && this.myEC.isDelayDatastoreOperationsEnabled()) {
               if (this.cmd.hasRelations(this.myEC.getClassLoaderResolver(), this.myEC.getMetaDataManager())) {
                  this.provideFields(this.cmd.getAllMemberPositions(), new PersistFieldManager(this, false));
               }

            } else {
               this.getCallbackHandler().prePersist(this.myPC);
               if (this.isFlushedNew()) {
                  this.registerTransactional();
               } else if (!this.cmd.isEmbeddedOnly()) {
                  if (this.myID == null) {
                     this.setIdentity(false);
                  }

                  this.dirty = true;
                  if (this.myEC.isDelayDatastoreOperationsEnabled()) {
                     this.myEC.markDirty(this, false);
                     if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug(Localiser.msg("026028", StringUtils.toJVMIDString(this.myPC)));
                     }

                     this.registerTransactional();
                     if (this.myLC.isTransactional() && this.myLC.isDeleted()) {
                        this.myLC = this.myLC.transitionMakePersistent(this);
                     }

                     if (this.cmd.hasRelations(this.myEC.getClassLoaderResolver(), this.myEC.getMetaDataManager())) {
                        this.provideFields(this.cmd.getAllMemberPositions(), new PersistFieldManager(this, false));
                     }
                  } else {
                     this.internalMakePersistent();
                     this.registerTransactional();
                  }

               }
            }
         }
      }
   }

   private void internalMakePersistent() {
      this.activity = ActivityState.INSERTING;
      boolean[] tmpDirtyFields = (boolean[])this.dirtyFields.clone();

      try {
         this.getCallbackHandler().preStore(this.myPC);
         if (this.myID == null) {
            this.setIdentity(true);
         }

         this.clearDirtyFlags();
         this.getStoreManager().getPersistenceHandler().insertObject(this);
         this.setFlushedNew(true);
         this.getCallbackHandler().postStore(this.myPC);
         if (!this.isEmbedded()) {
            this.myEC.putObjectIntoLevel1Cache(this);
         }
      } catch (NotYetFlushedException ex) {
         this.dirtyFields = tmpDirtyFields;
         this.myEC.markDirty(this, false);
         this.dirty = true;
         throw ex;
      } finally {
         this.activity = ActivityState.NONE;
      }

   }

   public void makeTransactional() {
      this.preStateChange();

      try {
         if (this.myLC == null) {
            StateManagerImpl thisSM = this;
            this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(5);

            try {
               if (this.myLC.isPersistent()) {
                  this.myEC.addObjectProvider(this);
               }

               this.replaceStateManager((Persistable)this.myPC, thisSM);
            } catch (SecurityException e) {
               throw new NucleusUserException(e.getMessage());
            } catch (NucleusException ne) {
               if (this.myEC.findObjectProvider(this.myEC.getObjectFromCache(this.myID)) == this) {
                  this.myEC.removeObjectProvider(this);
               }

               throw ne;
            }

            this.restoreValues = true;
         } else {
            this.myLC = this.myLC.transitionMakeTransactional(this, true);
         }
      } finally {
         this.postStateChange();
      }

   }

   public void makeTransient(FetchPlanState state) {
      if (!this.isMakingTransient()) {
         try {
            this.setMakingTransient(true);
            if (state == null) {
               int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getSCOMutableMemberPositions(), true);
               if (fieldNumbers != null && fieldNumbers.length > 0) {
                  this.provideFields(fieldNumbers, new UnsetOwnerFieldManager());
               }
            } else {
               this.loadUnloadedFieldsInFetchPlan();
               int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getAllMemberPositions(), true);
               if (fieldNumbers != null && fieldNumbers.length > 0) {
                  this.replaceFields(fieldNumbers, new MakeTransientFieldManager(this, this.cmd.getSCOMutableMemberFlags(), this.myFP, state));
               }
            }

            this.preStateChange();

            try {
               this.myLC = this.myLC.transitionMakeTransient(this, state != null, this.myEC.isRunningDetachAllOnCommit());
            } finally {
               this.postStateChange();
            }
         } finally {
            this.setMakingTransient(false);
         }

      }
   }

   public void detach(FetchPlanState state) {
      if (this.myEC != null) {
         ApiAdapter api = this.myEC.getApiAdapter();
         if (!this.myLC.isDeleted() && !api.isDetached(this.myPC) && !this.isDetaching()) {
            boolean detachable = api.isDetachable(this.myPC);
            if (detachable) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("010009", StringUtils.toJVMIDString(this.myPC), "" + state.getCurrentFetchDepth()));
               }

               this.getCallbackHandler().preDetach(this.myPC);
            }

            try {
               this.setDetaching(true);
               String detachedState = this.myEC.getNucleusContext().getConfiguration().getStringProperty("datanucleus.detachedState");
               if (detachedState.equalsIgnoreCase("all")) {
                  this.loadUnloadedFields();
               } else if (!detachedState.equalsIgnoreCase("loaded")) {
                  if ((this.myEC.getFetchPlan().getDetachmentOptions() & 1) != 0) {
                     this.loadUnloadedFieldsInFetchPlan();
                  }

                  if ((this.myEC.getFetchPlan().getDetachmentOptions() & 2) != 0) {
                     this.unloadNonFetchPlanFields();
                     int[] unloadedFields = ClassUtils.getFlagsSetTo(this.loadedFields, this.cmd.getAllMemberPositions(), false);
                     if (unloadedFields != null && unloadedFields.length > 0) {
                        Persistable dummyPC = ((Persistable)this.myPC).dnNewInstance(this);
                        ((Persistable)this.myPC).dnCopyFields(dummyPC, unloadedFields);
                        this.replaceStateManager(dummyPC, (StateManager)null);
                     }
                  }
               }

               FieldManager detachFieldManager = new DetachFieldManager(this, this.cmd.getSCOMutableMemberFlags(), this.myFP, state, false);

               for(int i = 0; i < this.loadedFields.length; ++i) {
                  if (this.loadedFields[i]) {
                     try {
                        detachFieldManager.fetchObjectField(i);
                     } catch (AbstractFetchDepthFieldManager.EndOfFetchPlanGraphException var14) {
                        Object value = this.provideField(i);
                        if (api.isPersistable(value)) {
                           StateManagerImpl valueSM = (StateManagerImpl)this.myEC.findObjectProvider(value);
                           if (!api.isDetached(value) && (valueSM == null || !valueSM.isDetaching())) {
                              String fieldName = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(i).getName();
                              if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                                 NucleusLogger.PERSISTENCE.debug(Localiser.msg("026032", StringUtils.toJVMIDString(this.myPC), IdentityUtils.getPersistableIdentityForId(this.myID), fieldName));
                              }

                              this.unloadField(fieldName);
                           }
                        }
                     }
                  }
               }

               if (detachable) {
                  this.myLC = this.myLC.transitionDetach(this);
                  ((Persistable)this.myPC).dnReplaceFlags();
                  ((Detachable)this.myPC).dnReplaceDetachedState();
                  this.getCallbackHandler().postDetach(this.myPC, this.myPC);
                  Persistable toCheckPC = (Persistable)this.myPC;
                  Object toCheckID = this.myID;
                  this.disconnect();
                  if (!toCheckPC.dnIsDetached()) {
                     throw new NucleusUserException(Localiser.msg("026025", toCheckPC.getClass().getName(), toCheckID));
                  }
               } else {
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("026031", ((Persistable)this.myPC).getClass().getName(), IdentityUtils.getPersistableIdentityForId(this.myID)));
                  this.makeTransient((FetchPlanState)null);
               }
            } finally {
               this.setDetaching(false);
            }

         }
      }
   }

   public Persistable detachCopy(FetchPlanState state) {
      if (this.myLC.isDeleted()) {
         throw new NucleusUserException(Localiser.msg("026023", ((Persistable)this.myPC).getClass().getName(), this.myID));
      } else if (this.myEC.getApiAdapter().isDetached(this.myPC)) {
         throw new NucleusUserException(Localiser.msg("026024", ((Persistable)this.myPC).getClass().getName(), this.myID));
      } else {
         if (this.dirty) {
            this.myEC.flushInternal(false);
         }

         if (this.isDetaching()) {
            return (Persistable)this.getReferencedPC();
         } else {
            DetachState detachState = (DetachState)state;
            DetachState.Entry existingDetached = detachState.getDetachedCopyEntry(this.myPC);
            Persistable detachedPC;
            if (existingDetached == null) {
               detachedPC = ((Persistable)this.myPC).dnNewInstance(this);
               detachState.setDetachedCopyEntry(this.myPC, detachedPC);
            } else {
               detachedPC = (Persistable)existingDetached.getDetachedCopyObject();
               if (existingDetached.checkCurrentState()) {
                  return detachedPC;
               }
            }

            this.myEC.setAttachDetachReferencedObject(this, detachedPC);
            boolean detachable = this.myEC.getApiAdapter().isDetachable(this.myPC);
            Object referencedPC = this.getReferencedPC();
            synchronized(referencedPC) {
               int[] detachFieldNums = this.getFieldsNumbersToDetach();
               if (detachable) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     int[] fieldsToLoad = null;
                     if ((this.myEC.getFetchPlan().getDetachmentOptions() & 1) != 0) {
                        fieldsToLoad = ClassUtils.getFlagsSetTo(this.loadedFields, this.myFP.getMemberNumbers(), false);
                     }

                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("010010", StringUtils.toJVMIDString(this.myPC), "" + state.getCurrentFetchDepth(), StringUtils.toJVMIDString(detachedPC), StringUtils.intArrayToString(detachFieldNums), StringUtils.intArrayToString(fieldsToLoad)));
                  }

                  this.getCallbackHandler().preDetach(this.myPC);
               }

               try {
                  this.setDetaching(true);
                  if ((this.myEC.getFetchPlan().getDetachmentOptions() & 1) != 0) {
                     this.loadUnloadedFieldsInFetchPlan();
                  }

                  if (this.myLC == this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(4) || this.myLC == this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(9)) {
                     this.myLC = this.myLC.transitionReadField(this, true);
                  }

                  StateManagerImpl smDetachedPC = new StateManagerImpl(this.myEC, this.cmd);
                  smDetachedPC.initialiseForDetached(detachedPC, this.getExternalObjectId(this.myPC), this.getVersion((Persistable)this.myPC));
                  this.myEC.setAttachDetachReferencedObject(smDetachedPC, this.myPC);
                  if (existingDetached != null) {
                     smDetachedPC.retrieveDetachState(smDetachedPC);
                  }

                  smDetachedPC.replaceFields(detachFieldNums, new DetachFieldManager(this, this.cmd.getSCOMutableMemberFlags(), this.myFP, state, true));
                  this.myEC.setAttachDetachReferencedObject(smDetachedPC, (Object)null);
                  if (detachable) {
                     detachedPC.dnReplaceFlags();
                     ((Detachable)detachedPC).dnReplaceDetachedState();
                  } else {
                     smDetachedPC.makeTransient((FetchPlanState)null);
                  }

                  this.replaceStateManager(detachedPC, (StateManager)null);
               } catch (Exception e) {
                  NucleusLogger.PERSISTENCE.warn("DETACH ERROR : Error thrown while detaching " + StringUtils.toJVMIDString(this.myPC) + " (id=" + this.myID + "). Provide a testcase that demonstrates this", e);
               } finally {
                  this.setDetaching(false);
                  referencedPC = null;
               }

               if (detachable && !this.myEC.getApiAdapter().isDetached(detachedPC)) {
                  throw new NucleusUserException(Localiser.msg("026025", detachedPC.getClass().getName(), this.myID));
               } else {
                  if (detachable) {
                     this.getCallbackHandler().postDetach(this.myPC, detachedPC);
                  }

                  return detachedPC;
               }
            }
         }
      }
   }

   private int[] getFieldsNumbersToDetach() {
      String detachedState = this.myEC.getNucleusContext().getConfiguration().getStringProperty("datanucleus.detachedState");
      if (detachedState.equalsIgnoreCase("all")) {
         return this.cmd.getAllMemberPositions();
      } else if (detachedState.equalsIgnoreCase("loaded")) {
         return this.getLoadedFieldNumbers();
      } else if ((this.myEC.getFetchPlan().getDetachmentOptions() & 2) == 0) {
         if ((this.myEC.getFetchPlan().getDetachmentOptions() & 1) == 0) {
            return this.getLoadedFieldNumbers();
         } else {
            int[] fieldsToDetach = this.myFP.getMemberNumbers();
            int[] allFieldNumbers = this.cmd.getAllMemberPositions();
            int[] loadedFieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, allFieldNumbers, true);
            if (loadedFieldNumbers != null && loadedFieldNumbers.length > 0) {
               boolean[] flds = new boolean[allFieldNumbers.length];

               for(int i = 0; i < fieldsToDetach.length; ++i) {
                  flds[fieldsToDetach[i]] = true;
               }

               for(int i = 0; i < loadedFieldNumbers.length; ++i) {
                  flds[loadedFieldNumbers[i]] = true;
               }

               fieldsToDetach = ClassUtils.getFlagsSetTo(flds, true);
            }

            return fieldsToDetach;
         }
      } else {
         return (this.myEC.getFetchPlan().getDetachmentOptions() & 1) == 0 ? ClassUtils.getFlagsSetTo(this.loadedFields, this.myFP.getMemberNumbers(), true) : this.myFP.getMemberNumbers();
      }
   }

   public void attach(Persistable detachedPC) {
      if (!this.isAttaching()) {
         this.setAttaching(true);

         try {
            this.getCallbackHandler().preAttach(this.myPC);
            StateManagerImpl detachedSM = new StateManagerImpl(this.myEC, this.cmd);
            detachedSM.initialiseForDetached((Persistable)detachedPC, this.myID, (Object)null);
            this.myEC.putObjectIntoLevel1Cache(this);
            int[] nonPKFieldNumbers = this.cmd.getNonPKMemberPositions();
            if (nonPKFieldNumbers != null && nonPKFieldNumbers.length > 0) {
               NucleusLogger.GENERAL.debug("Attaching id=" + this.getInternalObjectId() + " fields=" + StringUtils.intArrayToString(nonPKFieldNumbers));
               detachedSM.provideFields(nonPKFieldNumbers, new AttachFieldManager(this, this.cmd.getSCOMutableMemberFlags(), this.cmd.getNonPKMemberFlags(), true, true, false));
            }

            this.replaceStateManager(detachedPC, (StateManager)null);
            this.getCallbackHandler().postAttach(this.myPC, this.myPC);
         } finally {
            this.setAttaching(false);
         }

      }
   }

   public void attach(boolean embedded) {
      if (!this.isAttaching()) {
         this.setAttaching(true);

         try {
            boolean persistent = false;
            if (embedded) {
               persistent = true;
            } else if (!this.myEC.getBooleanProperty("datanucleus.attachSameDatastore")) {
               try {
                  this.locate();
                  persistent = true;
               } catch (NucleusObjectNotFoundException var7) {
               }
            } else {
               persistent = true;
            }

            this.getCallbackHandler().preAttach(this.myPC);
            this.replaceStateManager((Persistable)this.myPC, this);
            this.retrieveDetachState(this);
            if (!persistent) {
               this.makePersistent();
            }

            this.myLC = this.myLC.transitionAttach(this);
            this.myEC.putObjectIntoLevel1Cache(this);
            int[] attachFieldNumbers = this.getFieldNumbersOfLoadedOrDirtyFields(this.loadedFields, this.dirtyFields);
            if (attachFieldNumbers != null) {
               NucleusLogger.GENERAL.debug("Attaching id=" + this.getInternalObjectId() + " fields=" + StringUtils.intArrayToString(attachFieldNumbers));
               this.provideFields(attachFieldNumbers, new AttachFieldManager(this, this.cmd.getSCOMutableMemberFlags(), this.dirtyFields, persistent, true, false));
            }

            this.getCallbackHandler().postAttach(this.myPC, this.myPC);
         } finally {
            this.setAttaching(false);
         }

      }
   }

   public Persistable attachCopy(Persistable detachedPC, boolean embedded) {
      if (this.isAttaching()) {
         return (Persistable)this.myPC;
      } else {
         this.setAttaching(true);

         try {
            boolean persistent = false;
            if (embedded) {
               persistent = true;
            } else if (!this.myEC.getBooleanProperty("datanucleus.attachSameDatastore")) {
               try {
                  this.locate();
                  persistent = true;
               } catch (NucleusObjectNotFoundException var12) {
               }
            } else {
               persistent = true;
            }

            this.getCallbackHandler().preAttach(detachedPC);
            if (this.myEC.getApiAdapter().isDeleted(detachedPC)) {
               this.myLC = this.myLC.transitionDeletePersistent(this);
            }

            if (!this.myEC.getTransaction().getOptimistic() && (this.myLC == this.myEC.getApiAdapter().getLifeCycleState(4) || this.myLC == this.myEC.getApiAdapter().getLifeCycleState(9))) {
               this.myLC = this.myLC.transitionMakeTransactional(this, persistent);
            }

            StateManagerImpl smDetachedPC = null;
            if (persistent) {
               int[] noncontainerFieldNumbers = this.cmd.getSCONonContainerMemberPositions();
               int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, noncontainerFieldNumbers, false);
               if (fieldNumbers != null && fieldNumbers.length > 0) {
                  int[] unloadedFieldNumbers = this.loadFieldsFromLevel2Cache(fieldNumbers);
                  if (unloadedFieldNumbers != null) {
                     this.loadFieldsFromDatastore(unloadedFieldNumbers);
                     this.updateLevel2CacheForFields(unloadedFieldNumbers);
                  }
               }

               smDetachedPC = new StateManagerImpl(this.myEC, this.cmd);
               smDetachedPC.initialiseForDetached((Persistable)detachedPC, this.getExternalObjectId(detachedPC), (Object)null);
               this.myEC.setAttachDetachReferencedObject(smDetachedPC, this.myPC);
               this.myEC.setAttachDetachReferencedObject(this, detachedPC);
               this.retrieveDetachState(smDetachedPC);
            } else {
               this.myLC = this.myEC.getNucleusContext().getApiAdapter().getLifeCycleState(1);
               this.replaceStateManager(detachedPC, this);
               ((Persistable)this.myPC).dnCopyFields(detachedPC, this.cmd.getAllMemberPositions());
               this.replaceStateManager(detachedPC, (StateManager)null);
               smDetachedPC = new StateManagerImpl(this.myEC, this.cmd);
               smDetachedPC.initialiseForDetached((Persistable)detachedPC, this.getExternalObjectId(detachedPC), (Object)null);
               this.myEC.setAttachDetachReferencedObject(smDetachedPC, this.myPC);
               this.myEC.setAttachDetachReferencedObject(this, detachedPC);
               this.retrieveDetachState(smDetachedPC);
               this.internalAttachCopy(smDetachedPC, smDetachedPC.loadedFields, smDetachedPC.dirtyFields, persistent, smDetachedPC.myVersion, false);
               this.makePersistent();
            }

            this.internalAttachCopy(smDetachedPC, smDetachedPC.loadedFields, smDetachedPC.dirtyFields, persistent, smDetachedPC.myVersion, true);
            this.replaceStateManager(detachedPC, (StateManager)null);
            this.myEC.setAttachDetachReferencedObject(smDetachedPC, (Object)null);
            this.myEC.setAttachDetachReferencedObject(this, (Object)null);
            this.getCallbackHandler().postAttach(this.myPC, detachedPC);
         } catch (NucleusException ne) {
            NucleusLogger.PERSISTENCE.debug("Unexpected exception thrown in attach", ne);
            throw ne;
         } finally {
            this.setAttaching(false);
         }

         return (Persistable)this.myPC;
      }
   }

   private void internalAttachCopy(ObjectProvider detachedOP, boolean[] loadedFields, boolean[] dirtyFields, boolean persistent, Object version, boolean cascade) {
      int[] attachFieldNumbers = this.getFieldNumbersOfLoadedOrDirtyFields(loadedFields, dirtyFields);
      this.setVersion(version);
      if (attachFieldNumbers != null) {
         NucleusLogger.GENERAL.debug("Attaching id=" + this.getInternalObjectId() + " fields=" + StringUtils.intArrayToString(attachFieldNumbers));
         detachedOP.provideFields(attachFieldNumbers, new AttachFieldManager(this, this.cmd.getSCOMutableMemberFlags(), dirtyFields, persistent, cascade, true));
      }

   }

   public void deletePersistent() {
      if (!this.myLC.isDeleted()) {
         if (this.myEC.isDelayDatastoreOperationsEnabled()) {
            if (this.myEC.operationQueueIsActive()) {
               this.myEC.addOperationToQueue(new DeleteOperation(this));
            }

            this.getCallbackHandler().preDelete(this.myPC);
            this.myEC.markDirty(this, false);
            if (this.myLC.stateType() == 2 || this.myLC.stateType() == 3 || this.myLC.stateType() == 4 || this.myLC.stateType() == 9 || this.myLC.stateType() == 10) {
               this.loadUnloadedRelationFields();
            }

            this.setBecomingDeleted(true);
            if (this.cmd.hasRelations(this.myEC.getClassLoaderResolver(), this.myEC.getMetaDataManager())) {
               this.provideFields(this.cmd.getAllMemberPositions(), new DeleteFieldManager(this));
            }

            this.dirty = true;
            this.preStateChange();

            try {
               this.preDeleteLoadedFields = new boolean[this.loadedFields.length];

               for(int i = 0; i < this.preDeleteLoadedFields.length; ++i) {
                  this.preDeleteLoadedFields[i] = this.loadedFields[i];
               }

               this.myLC = this.myLC.transitionDeletePersistent(this);
            } finally {
               this.setBecomingDeleted(false);
               this.postStateChange();
            }
         } else {
            this.getCallbackHandler().preDelete(this.myPC);
            this.dirty = true;
            this.preStateChange();

            try {
               this.preDeleteLoadedFields = new boolean[this.loadedFields.length];

               for(int i = 0; i < this.preDeleteLoadedFields.length; ++i) {
                  this.preDeleteLoadedFields[i] = this.loadedFields[i];
               }

               this.myLC = this.myLC.transitionDeletePersistent(this);
            } finally {
               this.postStateChange();
            }

            this.internalDeletePersistent();
            this.getCallbackHandler().postDelete(this.myPC);
         }
      }

   }

   public void validate() {
      if (!this.myLC.isTransactional()) {
         int[] fieldNumbers = ClassUtils.getFlagsSetTo(this.loadedFields, this.myFP.getMemberNumbers(), false);
         if (fieldNumbers != null && fieldNumbers.length > 0) {
            fieldNumbers = ClassUtils.getFlagsSetTo(this.cmd.getNonPKMemberFlags(), fieldNumbers, true);
         }

         if (fieldNumbers != null && fieldNumbers.length > 0) {
            fieldNumbers = ClassUtils.getFlagsSetTo(this.cmd.getSCOMutableMemberFlags(), fieldNumbers, false);
         }

         boolean versionNeedsLoading = false;
         if (this.cmd.isVersioned() && this.transactionalVersion == null) {
            versionNeedsLoading = true;
         }

         if ((fieldNumbers == null || fieldNumbers.length <= 0) && !versionNeedsLoading) {
            this.locate();
            this.transitionReadField(false);
         } else if (!this.validating) {
            try {
               this.validating = true;
               this.transitionReadField(false);
               fieldNumbers = this.myFP.getMemberNumbers();
               if (fieldNumbers != null || versionNeedsLoading) {
                  boolean callPostLoad = this.myFP.isToCallPostLoadFetchPlan(this.loadedFields);
                  this.setTransactionalVersion((Object)null);
                  this.loadFieldsFromDatastore(fieldNumbers);
                  if (callPostLoad) {
                     this.postLoad();
                  }
               }
            } finally {
               this.validating = false;
            }
         }
      }

   }

   protected boolean preWriteField(int fieldNumber) {
      boolean wasDirty = this.dirty;
      if (this.activity != ActivityState.INSERTING && this.activity != ActivityState.INSERTING_CALLBACKS) {
         if (!wasDirty) {
            this.getCallbackHandler().preDirty(this.myPC);
         }

         this.transitionWriteField();
         this.dirty = true;
         this.dirtyFields[fieldNumber] = true;
         this.loadedFields[fieldNumber] = true;
      }

      return wasDirty;
   }

   protected void postWriteField(boolean wasDirty) {
      if (this.dirty && !wasDirty) {
         this.getCallbackHandler().postDirty(this.myPC);
      }

      if (this.activity == ActivityState.NONE && !this.isFlushing() && (!this.myLC.isTransactional() || this.myLC.isPersistent())) {
         if (this.isDetaching() && this.getReferencedPC() == null) {
            return;
         }

         this.myEC.markDirty(this, true);
      }

   }

   protected void postStateChange() {
      this.flags &= -2049;
      if (this.isPostLoadPending() && this.areFieldsLoaded(this.myFP.getMemberNumbers())) {
         this.setPostLoadPending(false);
         this.postLoad();
      }

   }

   private void postLoad() {
      if (this.isChangingState()) {
         this.setPostLoadPending(true);
      } else {
         if (this.persistenceFlags == 1 && this.myLC.isTransactional()) {
            this.persistenceFlags = -1;
            ((Persistable)this.myPC).dnReplaceFlags();
         }

         this.getCallbackHandler().postLoad(this.myPC);
      }

   }

   public void preSerialize(Persistable pc) {
      if (!this.disconnectClone(pc)) {
         this.retrieve(false);
         this.myLC = this.myLC.transitionSerialize(this);
         if (!this.isStoringPC() && pc instanceof Detachable && !this.myLC.isDeleted() && this.myLC.isPersistent()) {
            if (this.myLC.isDirty()) {
               this.flush();
            }

            ((Detachable)pc).dnReplaceDetachedState();
         }

      }
   }

   public void flush() {
      if (this.dirty) {
         if (this.isFlushing()) {
            return;
         }

         if (this.activity == ActivityState.INSERTING || this.activity == ActivityState.INSERTING_CALLBACKS) {
            return;
         }

         this.setFlushing(true);

         try {
            if (this.myLC.stateType() == 1 && !this.isFlushedNew()) {
               if (!this.isEmbedded()) {
                  this.internalMakePersistent();
               } else {
                  this.getCallbackHandler().preStore(this.myPC);
                  if (this.myID == null) {
                     this.setIdentity(true);
                  }

                  this.getCallbackHandler().postStore(this.myPC);
               }

               this.dirty = false;
            } else if (this.myLC.stateType() == 8) {
               this.getCallbackHandler().preDelete(this.myPC);
               if (!this.isEmbedded()) {
                  this.internalDeletePersistent();
               }

               this.getCallbackHandler().postDelete(this.myPC);
            } else if (this.myLC.stateType() == 7) {
               if (this.isFlushedNew()) {
                  this.getCallbackHandler().preDelete(this.myPC);
                  if (!this.isEmbedded()) {
                     this.internalDeletePersistent();
                  }

                  this.setFlushedNew(false);
                  this.getCallbackHandler().postDelete(this.myPC);
               } else {
                  this.dirty = false;
               }
            } else {
               if (!this.isDeleting()) {
                  this.getCallbackHandler().preStore(this.myPC);
                  if (this.myID == null) {
                     this.setIdentity(true);
                  }
               }

               if (!this.isEmbedded()) {
                  int[] dirtyFieldNumbers = ClassUtils.getFlagsSetTo(this.dirtyFields, true);
                  if (!this.isEmbedded() && dirtyFieldNumbers == null) {
                     throw (new NucleusException(Localiser.msg("026010"))).setFatal();
                  }

                  if (this.myEC.getNucleusContext().isClassCacheable(this.getClassMetaData())) {
                     this.myEC.markFieldsForUpdateInLevel2Cache(this.getInternalObjectId(), this.dirtyFields);
                  }

                  this.getStoreManager().getPersistenceHandler().updateObject(this, dirtyFieldNumbers);
                  this.myEC.putObjectIntoLevel1Cache(this);
               }

               this.clearDirtyFlags();
               this.getCallbackHandler().postStore(this.myPC);
            }
         } finally {
            this.setFlushing(false);
         }
      }

   }

   private static void dumpPC(Object pc, PrintWriter out) {
      out.println(StringUtils.toJVMIDString(pc));
      if (pc != null) {
         out.print("dnStateManager = " + peekField(pc, "dnStateManager"));
         out.print("dnFlags = ");
         Object flagsObj = peekField(pc, "dnFlags");
         if (flagsObj instanceof Byte) {
            switch ((Byte)flagsObj) {
               case -1:
                  out.println("READ_OK");
                  break;
               case 0:
                  out.println("READ_WRITE_OK");
                  break;
               case 1:
                  out.println("LOAD_REQUIRED");
                  break;
               default:
                  out.println("???");
            }
         } else {
            out.println(flagsObj);
         }

         Class c = pc.getClass();

         do {
            String[] fieldNames = HELPER.getFieldNames(c);

            for(int i = 0; i < fieldNames.length; ++i) {
               out.print(fieldNames[i]);
               out.print(" = ");
               out.println(peekField(pc, fieldNames[i]));
            }

            c = c.getSuperclass();
         } while(c != null && Persistable.class.isAssignableFrom(c));

      }
   }

   public void dump(PrintWriter out) {
      out.println("myEC = " + this.myEC);
      out.println("myID = " + this.myID);
      out.println("myLC = " + this.myLC);
      out.println("cmd = " + this.cmd);
      out.println("fieldCount = " + this.cmd.getMemberCount());
      out.println("dirty = " + this.dirty);
      out.println("flushing = " + this.isFlushing());
      out.println("changingState = " + this.isChangingState());
      out.println("postLoadPending = " + this.isPostLoadPending());
      out.println("disconnecting = " + this.isDisconnecting());
      out.println("dirtyFields = " + StringUtils.booleanArrayToString(this.dirtyFields));
      out.println("getSecondClassMutableFields() = " + StringUtils.booleanArrayToString(this.cmd.getSCOMutableMemberFlags()));
      out.println("getAllFieldNumbers() = " + StringUtils.intArrayToString(this.cmd.getAllMemberPositions()));
      out.println("secondClassMutableFieldNumbers = " + StringUtils.intArrayToString(this.cmd.getSCOMutableMemberPositions()));
      out.println();
      switch (this.persistenceFlags) {
         case -1:
            out.println("persistenceFlags = READ_OK");
            break;
         case 0:
            out.println("persistenceFlags = READ_WRITE_OK");
            break;
         case 1:
            out.println("persistenceFlags = LOAD_REQUIRED");
            break;
         default:
            out.println("persistenceFlags = ???");
      }

      out.println("loadedFields = " + StringUtils.booleanArrayToString(this.loadedFields));
      out.print("myPC = ");
      dumpPC(this.myPC, out);
      out.println();
      switch (this.savedFlags) {
         case 1:
            out.println("savedFlags = LOAD_REQUIRED");
         case -1:
            out.println("savedFlags = READ_OK");
         case 0:
            out.println("savedFlags = READ_WRITE_OK");
         default:
            out.println("savedFlags = ???");
            out.println("savedLoadedFields = " + StringUtils.booleanArrayToString(this.savedLoadedFields));
            out.print("savedImage = ");
            dumpPC(this.savedImage, out);
      }
   }

   protected static Object peekField(Object obj, String fieldName) {
      try {
         Object value = obj.getClass().getDeclaredField(fieldName).get(obj);
         return value instanceof Persistable ? StringUtils.toJVMIDString(value) : value;
      } catch (Exception e) {
         return e.toString();
      }
   }

   public void changeActivityState(ActivityState state) {
   }

   public void updateFieldAfterInsert(Object pc, int fieldNumber) {
   }

   public void copyFieldsFromObject(Object obj, int[] fieldNumbers) {
      if (obj != null) {
         if (obj.getClass().getName().equals(((Persistable)this.myPC).getClass().getName())) {
            if (!(obj instanceof Persistable)) {
               throw new NucleusUserException("Must be Persistable");
            } else {
               Persistable pc = (Persistable)obj;
               this.replaceStateManager(pc, this);
               ((Persistable)this.myPC).dnCopyFields(pc, fieldNumbers);
               this.replaceStateManager(pc, (StateManager)null);

               for(int i = 0; i < fieldNumbers.length; ++i) {
                  this.loadedFields[fieldNumbers[i]] = true;
               }

            }
         }
      }
   }
}
