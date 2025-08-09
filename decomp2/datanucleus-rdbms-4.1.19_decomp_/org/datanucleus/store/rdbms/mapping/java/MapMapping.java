package org.datanucleus.store.rdbms.mapping.java;

import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.ReachableObjectNotCascadedException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.store.types.wrappers.backed.BackedSCO;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class MapMapping extends AbstractContainerMapping implements MappingCallbacks {
   public Class getJavaType() {
      return Map.class;
   }

   public void insertPostProcessing(ObjectProvider ownerOP) {
   }

   public void postInsert(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      Map value = (Map)ownerOP.provideField(this.getAbsoluteFieldNumber());
      if (this.containerIsStoredInSingleColumn()) {
         if (value != null && (this.mmd.getMap().keyIsPersistent() || this.mmd.getMap().valueIsPersistent())) {
            for(Map.Entry entry : value.entrySet()) {
               if (this.mmd.getMap().keyIsPersistent() && entry.getKey() != null) {
                  Object key = entry.getKey();
                  if (ec.findObjectProvider(key) == null || ec.getApiAdapter().getExecutionContext(key) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, key, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }

               if (this.mmd.getMap().valueIsPersistent() && entry.getValue() != null) {
                  Object val = entry.getValue();
                  if (ec.findObjectProvider(val) == null || ec.getApiAdapter().getExecutionContext(val) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, val, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

      } else if (value == null) {
         this.replaceFieldWithWrapper(ownerOP, (Object)null);
      } else {
         if (!this.mmd.isCascadePersist()) {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007006", new Object[]{this.mmd.getFullFieldName()}));
            }

            ApiAdapter api = ec.getApiAdapter();

            for(Map.Entry entry : value.entrySet()) {
               if (api.isPersistable(entry.getKey()) && !api.isPersistent(entry.getKey()) && !api.isDetached(entry.getKey())) {
                  throw new ReachableObjectNotCascadedException(this.mmd.getFullFieldName(), entry.getKey());
               }

               if (api.isPersistable(entry.getValue()) && !api.isPersistent(entry.getValue()) && !api.isDetached(entry.getValue())) {
                  throw new ReachableObjectNotCascadedException(this.mmd.getFullFieldName(), entry.getValue());
               }
            }
         } else if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("007007", new Object[]{this.mmd.getFullFieldName()}));
         }

         if (value.size() > 0) {
            ((MapStore)this.table.getStoreManager().getBackingStoreForField(ownerOP.getExecutionContext().getClassLoaderResolver(), this.mmd, value.getClass())).putAll(ownerOP, value);
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
      RDBMSStoreManager storeMgr = this.table.getStoreManager();
      Map value = (Map)ownerOP.provideField(this.getAbsoluteFieldNumber());
      if (!this.containerIsStoredInSingleColumn()) {
         if (value == null) {
            ((MapStore)storeMgr.getBackingStoreForField(ec.getClassLoaderResolver(), this.mmd, (Class)null)).clear(ownerOP);
            this.replaceFieldWithWrapper(ownerOP, (Object)null);
         } else if (value instanceof BackedSCO) {
            ownerOP.getExecutionContext().flushOperationsForBackingStore(((BackedSCO)value).getBackingStore(), ownerOP);
         } else if (this.mmd.isCascadeUpdate()) {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007009", new Object[]{this.mmd.getFullFieldName()}));
            }

            MapStore store = (MapStore)storeMgr.getBackingStoreForField(ec.getClassLoaderResolver(), this.mmd, value.getClass());
            store.clear(ownerOP);
            store.putAll(ownerOP, value);
            this.replaceFieldWithWrapper(ownerOP, value);
         } else {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("007008", new Object[]{this.mmd.getFullFieldName()}));
            }

         }
      } else {
         if (value != null && (this.mmd.getMap().keyIsPersistent() || this.mmd.getMap().valueIsPersistent())) {
            for(Map.Entry entry : value.entrySet()) {
               if (this.mmd.getMap().keyIsPersistent() && entry.getKey() != null) {
                  Object key = entry.getKey();
                  if (ec.findObjectProvider(key) == null || ec.getApiAdapter().getExecutionContext(key) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, key, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }

               if (this.mmd.getMap().valueIsPersistent() && entry.getValue() != null) {
                  Object val = entry.getValue();
                  if (ec.findObjectProvider(val) == null || ec.getApiAdapter().getExecutionContext(val) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, val, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

      }
   }

   public void preDelete(ObjectProvider ownerOP) {
      if (!this.containerIsStoredInSingleColumn()) {
         ownerOP.isLoaded(this.getAbsoluteFieldNumber());
         Map value = (Map)ownerOP.provideField(this.getAbsoluteFieldNumber());
         if (value != null) {
            if (!(value instanceof SCO)) {
               value = (Map)SCOUtils.wrapSCOField(ownerOP, this.mmd.getAbsoluteFieldNumber(), value, true);
            }

            value.clear();
            ownerOP.getExecutionContext().flushOperationsForBackingStore(((BackedSCO)value).getBackingStore(), ownerOP);
         }

      }
   }
}
