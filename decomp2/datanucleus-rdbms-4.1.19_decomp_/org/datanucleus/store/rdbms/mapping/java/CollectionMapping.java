package org.datanucleus.store.rdbms.mapping.java;

import java.util.Collection;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.ReachableObjectNotCascadedException;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.store.types.wrappers.backed.BackedSCO;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class CollectionMapping extends AbstractContainerMapping implements MappingCallbacks {
   public Class getJavaType() {
      return Collection.class;
   }

   public void insertPostProcessing(ObjectProvider ownerOP) {
   }

   public void postInsert(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      Collection value = (Collection)ownerOP.provideField(this.getAbsoluteFieldNumber());
      if (this.containerIsStoredInSingleColumn()) {
         if (value != null && this.mmd.getCollection().elementIsPersistent()) {
            Object[] collElements = value.toArray();

            for(Object elem : collElements) {
               if (elem != null) {
                  ObjectProvider elemOP = ec.findObjectProvider(elem);
                  if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

      } else if (value == null) {
         this.replaceFieldWithWrapper(ownerOP, (Object)null);
      } else {
         Object[] collElements = value.toArray();
         if (!this.mmd.isCascadePersist()) {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007006", new Object[]{this.mmd.getFullFieldName()}));
            }

            for(int i = 0; i < collElements.length; ++i) {
               if (!ec.getApiAdapter().isDetached(collElements[i]) && !ec.getApiAdapter().isPersistent(collElements[i])) {
                  throw new ReachableObjectNotCascadedException(this.mmd.getFullFieldName(), collElements[i]);
               }
            }
         } else if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("007007", new Object[]{this.mmd.getFullFieldName()}));
         }

         boolean needsAttaching = false;

         for(int i = 0; i < collElements.length; ++i) {
            if (ownerOP.getExecutionContext().getApiAdapter().isDetached(collElements[i])) {
               needsAttaching = true;
               break;
            }
         }

         if (needsAttaching) {
            SCO collWrapper = this.replaceFieldWithWrapper(ownerOP, (Object)null);
            if (value.size() > 0) {
               collWrapper.attachCopy(value);
               ownerOP.getExecutionContext().flushOperationsForBackingStore(((BackedSCO)collWrapper).getBackingStore(), ownerOP);
            }
         } else if (value.size() > 0) {
            ((CollectionStore)this.storeMgr.getBackingStoreForField(ownerOP.getExecutionContext().getClassLoaderResolver(), this.mmd, value.getClass())).addAll(ownerOP, value, 0);
            this.replaceFieldWithWrapper(ownerOP, value);
         } else if (this.mmd.getRelationType(ownerOP.getExecutionContext().getClassLoaderResolver()) == RelationType.MANY_TO_MANY_BI) {
            this.replaceFieldWithWrapper(ownerOP, (Object)null);
         } else {
            this.replaceFieldWithWrapper(ownerOP, value);
         }

      }
   }

   public void postUpdate(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      Collection value = (Collection)ownerOP.provideField(this.getAbsoluteFieldNumber());
      if (!this.containerIsStoredInSingleColumn()) {
         if (value == null) {
            ((CollectionStore)this.storeMgr.getBackingStoreForField(ec.getClassLoaderResolver(), this.mmd, (Class)null)).clear(ownerOP);
            this.replaceFieldWithWrapper(ownerOP, (Object)null);
         } else if (value instanceof BackedSCO) {
            ownerOP.getExecutionContext().flushOperationsForBackingStore(((BackedSCO)value).getBackingStore(), ownerOP);
         } else if (this.mmd.isCascadeUpdate()) {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007009", new Object[]{this.mmd.getFullFieldName()}));
            }

            CollectionStore backingStore = (CollectionStore)this.storeMgr.getBackingStoreForField(ec.getClassLoaderResolver(), this.mmd, value.getClass());
            backingStore.update(ownerOP, value);
            this.replaceFieldWithWrapper(ownerOP, value);
         } else {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007008", new Object[]{this.mmd.getFullFieldName()}));
            }

         }
      } else {
         if (value != null && this.mmd.getCollection().elementIsPersistent()) {
            Object[] collElements = value.toArray();

            for(Object elem : collElements) {
               if (elem != null) {
                  ObjectProvider elemOP = ec.findObjectProvider(elem);
                  if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

      }
   }

   public void preDelete(ObjectProvider ownerOP) {
      if (!this.containerIsStoredInSingleColumn()) {
         ownerOP.isLoaded(this.getAbsoluteFieldNumber());
         Collection value = (Collection)ownerOP.provideField(this.getAbsoluteFieldNumber());
         if (value != null) {
            boolean dependent = this.mmd.getCollection().isDependentElement();
            if (this.mmd.isCascadeRemoveOrphans()) {
               dependent = true;
            }

            boolean hasJoin = this.mmd.getJoinMetaData() != null;
            boolean hasFK = false;
            if (!hasJoin) {
               if (this.mmd.getElementMetaData() != null && this.mmd.getElementMetaData().getForeignKeyMetaData() != null) {
                  hasFK = true;
               } else if (this.mmd.getForeignKeyMetaData() != null) {
                  hasFK = true;
               }

               AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(ownerOP.getExecutionContext().getClassLoaderResolver());
               if (relatedMmds != null && relatedMmds[0].getForeignKeyMetaData() != null) {
                  hasFK = true;
               }
            }

            if (ownerOP.getExecutionContext().getStringProperty("datanucleus.deletionPolicy").equals("JDO2")) {
               hasFK = false;
            }

            if (ownerOP.getExecutionContext().getManageRelations()) {
               ownerOP.getExecutionContext().getRelationshipManager(ownerOP).relationChange(this.getAbsoluteFieldNumber(), value, (Object)null);
            }

            if (dependent || hasJoin || !hasFK) {
               if (!(value instanceof SCO)) {
                  value = (Collection)SCOUtils.wrapSCOField(ownerOP, this.getAbsoluteFieldNumber(), value, true);
               }

               value.clear();
               ownerOP.getExecutionContext().flushOperationsForBackingStore(((BackedSCO)value).getBackingStore(), ownerOP);
            }

         }
      }
   }
}
