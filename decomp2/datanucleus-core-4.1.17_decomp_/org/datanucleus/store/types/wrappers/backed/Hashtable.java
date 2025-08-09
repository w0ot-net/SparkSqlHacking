package org.datanucleus.store.types.wrappers.backed;

import java.io.ObjectStreamException;
import java.util.Iterator;
import org.datanucleus.ExecutionContext;
import org.datanucleus.flush.MapClearOperation;
import org.datanucleus.flush.MapPutOperation;
import org.datanucleus.flush.MapRemoveOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.BackedSCOStoreManager;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class Hashtable extends org.datanucleus.store.types.wrappers.Hashtable implements BackedSCO {
   protected transient MapStore backingStore;
   protected transient boolean allowNulls = false;
   protected transient boolean useCache = true;
   protected transient boolean isCacheLoaded = false;

   public Hashtable(ObjectProvider op, AbstractMemberMetaData mmd) {
      super(op, mmd);
      this.delegate = new java.util.Hashtable();
      this.useCache = SCOUtils.useContainerCache(this.ownerOP, mmd);
      if (!SCOUtils.mapHasSerialisedKeysAndValues(mmd) && mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
         this.backingStore = (MapStore)((BackedSCOStoreManager)this.ownerOP.getStoreManager()).getBackingStoreForField(this.ownerOP.getExecutionContext().getClassLoaderResolver(), mmd, java.util.Hashtable.class);
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(SCOUtils.getContainerInfoMessage(this.ownerOP, this.ownerMmd.getName(), this, this.useCache, this.allowNulls, SCOUtils.useCachedLazyLoading(this.ownerOP, this.ownerMmd)));
      }

   }

   public void initialise(java.util.Hashtable newValue, Object oldValue) {
      if (newValue != null) {
         if (SCOUtils.mapHasSerialisedKeysAndValues(this.ownerMmd) && (this.ownerMmd.getMap().keyIsPersistent() || this.ownerMmd.getMap().valueIsPersistent())) {
            ExecutionContext ec = this.ownerOP.getExecutionContext();

            for(java.util.Map.Entry entry : newValue.entrySet()) {
               Object key = entry.getKey();
               Object value = entry.getValue();
               if (this.ownerMmd.getMap().keyIsPersistent()) {
                  ObjectProvider keyOP = ec.findObjectProvider(key);
                  if (keyOP == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, key, false, this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber());
                  }
               }

               if (this.ownerMmd.getMap().valueIsPersistent()) {
                  ObjectProvider valOP = ec.findObjectProvider(value);
                  if (valOP == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, value, false, this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("023008", this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + newValue.size()));
         }

         if (this.useCache) {
            java.util.Map oldMap = (java.util.Map)oldValue;
            if (oldMap != null) {
               this.delegate.putAll(oldMap);
            }

            this.isCacheLoaded = true;
            SCOUtils.updateMapWithMapKeysValues(this.ownerOP.getExecutionContext().getApiAdapter(), this, newValue);
         } else {
            if (this.backingStore != null) {
               if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
                  if (this.ownerOP.isFlushedToDatastore() || !this.ownerOP.getLifecycleState().isNew()) {
                     this.ownerOP.getExecutionContext().addOperationToQueue(new MapClearOperation(this.ownerOP, this.backingStore));

                     for(java.util.Map.Entry entry : newValue.entrySet()) {
                        this.ownerOP.getExecutionContext().addOperationToQueue(new MapPutOperation(this.ownerOP, this.backingStore, entry.getKey(), entry.getValue()));
                     }
                  }
               } else {
                  this.backingStore.clear(this.ownerOP);
                  this.backingStore.putAll(this.ownerOP, newValue);
               }
            }

            this.delegate.putAll(newValue);
            this.isCacheLoaded = true;
            this.makeDirty();
         }
      }

   }

   public void initialise(java.util.Hashtable m) {
      if (m != null) {
         if (SCOUtils.mapHasSerialisedKeysAndValues(this.ownerMmd) && (this.ownerMmd.getMap().keyIsPersistent() || this.ownerMmd.getMap().valueIsPersistent())) {
            ExecutionContext ec = this.ownerOP.getExecutionContext();

            for(java.util.Map.Entry entry : m.entrySet()) {
               Object key = entry.getKey();
               Object value = entry.getValue();
               if (this.ownerMmd.getMap().keyIsPersistent()) {
                  ObjectProvider keyOP = ec.findObjectProvider(key);
                  if (keyOP == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, key, false, this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber());
                  }
               }

               if (this.ownerMmd.getMap().valueIsPersistent()) {
                  ObjectProvider valOP = ec.findObjectProvider(value);
                  if (valOP == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, value, false, this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("023007", this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + m.size()));
         }

         this.delegate.putAll(m);
         this.isCacheLoaded = true;
      }

   }

   public void initialise() {
      if (this.useCache && !SCOUtils.useCachedLazyLoading(this.ownerOP, this.ownerMmd)) {
         this.loadFromStore();
      }

   }

   public java.util.Hashtable getValue() {
      this.loadFromStore();
      return super.getValue();
   }

   public void load() {
      if (this.useCache) {
         this.loadFromStore();
      }

   }

   public boolean isLoaded() {
      return this.useCache ? this.isCacheLoaded : false;
   }

   protected void loadFromStore() {
      if (this.backingStore != null && !this.isCacheLoaded) {
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("023006", this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName()));
         }

         this.delegate.clear();
         SCOUtils.populateMapDelegateWithStoreData(this.delegate, this.backingStore, this.ownerOP);
         this.isCacheLoaded = true;
      }

   }

   public Store getBackingStore() {
      return this.backingStore;
   }

   public void updateEmbeddedKey(Object key, int fieldNumber, Object newValue, boolean makeDirty) {
      if (this.backingStore != null) {
         this.backingStore.updateEmbeddedKey(this.ownerOP, key, fieldNumber, newValue);
      }

   }

   public void updateEmbeddedValue(Object value, int fieldNumber, Object newValue, boolean makeDirty) {
      if (this.backingStore != null) {
         this.backingStore.updateEmbeddedValue(this.ownerOP, value, fieldNumber, newValue);
      }

   }

   public synchronized void unsetOwner() {
      if (this.backingStore != null) {
         this.backingStore = null;
      }

   }

   public synchronized Object clone() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return this.delegate.clone();
   }

   public synchronized boolean containsKey(Object key) {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.containsKey(key);
      } else {
         return this.backingStore != null ? this.backingStore.containsKey(this.ownerOP, key) : this.delegate.containsKey(key);
      }
   }

   public boolean containsValue(Object value) {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.containsValue(value);
      } else {
         return this.backingStore != null ? this.backingStore.containsValue(this.ownerOP, value) : this.delegate.containsValue(value);
      }
   }

   public java.util.Set entrySet() {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return new Set(this.ownerOP, this.ownerMmd, false, this.backingStore.entrySetStore());
      }

      return this.delegate.entrySet();
   }

   public synchronized boolean equals(Object o) {
      if (this.useCache) {
         this.loadFromStore();
      }

      if (o == this) {
         return true;
      } else if (!(o instanceof java.util.Map)) {
         return false;
      } else {
         java.util.Map m = (java.util.Map)o;
         return this.entrySet().equals(m.entrySet());
      }
   }

   public synchronized Object get(Object key) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return this.backingStore.get(this.ownerOP, key);
      }

      return this.delegate.get(key);
   }

   public synchronized int hashCode() {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         int h = 0;

         for(Iterator i = this.entrySet().iterator(); i.hasNext(); h += i.next().hashCode()) {
         }

         return h;
      }

      return this.delegate.hashCode();
   }

   public synchronized boolean isEmpty() {
      return this.size() == 0;
   }

   public java.util.Set keySet() {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return new Set(this.ownerOP, this.ownerMmd, false, this.backingStore.keySetStore());
      }

      return this.delegate.keySet();
   }

   public synchronized int size() {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.size();
      } else {
         return this.backingStore != null ? this.backingStore.entrySetStore().size(this.ownerOP) : this.delegate.size();
      }
   }

   public java.util.Collection values() {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return new Collection(this.ownerOP, this.ownerMmd, true, this.backingStore.valueCollectionStore());
      }

      return this.delegate.values();
   }

   public synchronized void clear() {
      this.makeDirty();
      this.delegate.clear();
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new MapClearOperation(this.ownerOP, this.backingStore));
         } else {
            this.backingStore.clear(this.ownerOP);
         }
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

   }

   public synchronized Object put(Object key, Object value) {
      if (!this.allowNulls) {
         if (value == null) {
            throw new NullPointerException("Nulls not allowed for map at field " + this.ownerMmd.getName() + " but value is null");
         }

         if (key == null) {
            throw new NullPointerException("Nulls not allowed for map at field " + this.ownerMmd.getName() + " but key is null");
         }
      }

      if (this.useCache) {
         this.loadFromStore();
      }

      this.makeDirty();
      V oldValue = (V)null;
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new MapPutOperation(this.ownerOP, this.backingStore, key, value));
         } else {
            oldValue = (V)this.backingStore.put(this.ownerOP, key, value);
         }
      }

      V delegateOldValue = (V)this.delegate.put(key, value);
      if (this.backingStore == null) {
         oldValue = delegateOldValue;
      } else if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
         oldValue = delegateOldValue;
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return oldValue;
   }

   public synchronized void putAll(java.util.Map m) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            for(java.util.Map.Entry entry : m.entrySet()) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new MapPutOperation(this.ownerOP, this.backingStore, entry.getKey(), entry.getValue()));
            }
         } else {
            this.backingStore.putAll(this.ownerOP, m);
         }
      }

      this.delegate.putAll(m);
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

   }

   public synchronized Object remove(Object key) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      V removed = (V)null;
      V delegateRemoved = (V)this.delegate.remove(key);
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new MapRemoveOperation(this.ownerOP, this.backingStore, key, delegateRemoved));
            removed = delegateRemoved;
         } else {
            removed = (V)this.backingStore.remove(this.ownerOP, key);
         }
      } else {
         removed = delegateRemoved;
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return removed;
   }

   protected Object writeReplace() throws ObjectStreamException {
      if (this.useCache) {
         this.loadFromStore();
         return new java.util.Hashtable(this.delegate);
      } else {
         return new java.util.Hashtable(this.delegate);
      }
   }
}
