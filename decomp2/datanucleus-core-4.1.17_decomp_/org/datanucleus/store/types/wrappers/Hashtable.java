package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import org.datanucleus.flush.MapPutOperation;
import org.datanucleus.flush.MapRemoveOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCOMap;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class Hashtable extends java.util.Hashtable implements SCOMap {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;
   protected java.util.Hashtable delegate;

   public Hashtable(ObjectProvider ownerOP, AbstractMemberMetaData mmd) {
      super(0);
      this.ownerOP = ownerOP;
      this.ownerMmd = mmd;
   }

   public void initialise(java.util.Hashtable newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.util.Hashtable m) {
      if (m != null) {
         this.delegate = m;
      } else {
         this.delegate = new java.util.Hashtable();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("023003", this.getClass().getName(), this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + this.size(), SCOUtils.getSCOWrapperOptionsMessage(true, false, false, false)));
      }

   }

   public void initialise() {
      this.initialise((java.util.Hashtable)null);
   }

   public java.util.Hashtable getValue() {
      return this.delegate;
   }

   public void setValue(java.util.Hashtable value) {
      this.delegate = value;
   }

   public void load() {
   }

   public boolean isLoaded() {
      return true;
   }

   public void updateEmbeddedKey(Object key, int fieldNumber, Object newValue, boolean makeDirty) {
      if (makeDirty) {
         this.makeDirty();
      }

   }

   public void updateEmbeddedValue(Object value, int fieldNumber, Object newValue, boolean makeDirty) {
      if (makeDirty) {
         this.makeDirty();
      }

   }

   public String getFieldName() {
      return this.ownerMmd.getName();
   }

   public Object getOwner() {
      return this.ownerOP != null ? this.ownerOP.getObject() : null;
   }

   public synchronized void unsetOwner() {
      if (this.ownerOP != null) {
         this.ownerOP = null;
         this.ownerMmd = null;
      }

   }

   public void makeDirty() {
      if (this.ownerOP != null) {
         this.ownerOP.makeDirty(this.ownerMmd.getAbsoluteFieldNumber());
      }

   }

   public java.util.Hashtable detachCopy(FetchPlanState state) {
      java.util.Hashtable detached = new java.util.Hashtable();
      SCOUtils.detachCopyForMap(this.ownerOP, this.entrySet(), state, detached);
      return detached;
   }

   public void attachCopy(java.util.Hashtable value) {
      boolean keysWithoutIdentity = SCOUtils.mapHasKeysWithoutIdentity(this.ownerMmd);
      boolean valuesWithoutIdentity = SCOUtils.mapHasValuesWithoutIdentity(this.ownerMmd);
      java.util.Map attachedKeysValues = new java.util.HashMap(value.size());
      SCOUtils.attachCopyForMap(this.ownerOP, value.entrySet(), attachedKeysValues, keysWithoutIdentity, valuesWithoutIdentity);
      SCOUtils.updateMapWithMapKeysValues(this.ownerOP.getExecutionContext().getApiAdapter(), this, attachedKeysValues);
   }

   public synchronized Object clone() {
      return this.delegate.clone();
   }

   public synchronized boolean containsKey(Object key) {
      return this.delegate.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.delegate.containsValue(value);
   }

   public java.util.Set entrySet() {
      return this.delegate.entrySet();
   }

   public synchronized boolean equals(Object o) {
      return this.delegate.equals(o);
   }

   public synchronized Object get(Object key) {
      return this.delegate.get(key);
   }

   public synchronized int hashCode() {
      return this.delegate.hashCode();
   }

   public synchronized boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public java.util.Set keySet() {
      return this.delegate.keySet();
   }

   public synchronized int size() {
      return this.delegate.size();
   }

   public java.util.Collection values() {
      return this.delegate.values();
   }

   public synchronized void clear() {
      if (this.ownerOP != null && !this.delegate.isEmpty()) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            for(java.util.Map.Entry entry : this.delegate.entrySet()) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new MapRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), entry.getKey(), entry.getValue()));
            }
         } else if (SCOUtils.hasDependentKey(this.ownerMmd) || SCOUtils.hasDependentValue(this.ownerMmd)) {
            for(java.util.Map.Entry entry : this.delegate.entrySet()) {
               if (SCOUtils.hasDependentKey(this.ownerMmd)) {
                  this.ownerOP.getExecutionContext().deleteObjectInternal(entry.getKey());
               }

               if (SCOUtils.hasDependentValue(this.ownerMmd)) {
                  this.ownerOP.getExecutionContext().deleteObjectInternal(entry.getValue());
               }
            }
         }
      }

      this.delegate.clear();
      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

   }

   public synchronized Object put(Object key, Object value) {
      V oldValue = (V)this.delegate.put(key, value);
      this.makeDirty();
      if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
         this.ownerOP.getExecutionContext().addOperationToQueue(new MapPutOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), key, value));
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return oldValue;
   }

   public synchronized void putAll(java.util.Map m) {
      this.delegate.putAll(m);
      this.makeDirty();
      if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
         for(java.util.Map.Entry entry : m.entrySet()) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new MapPutOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), entry.getKey(), entry.getValue()));
         }
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

   }

   public synchronized Object remove(Object key) {
      V value = (V)this.delegate.remove(key);
      if (this.ownerOP != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new MapRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), key, value));
         } else if (SCOUtils.hasDependentKey(this.ownerMmd) || SCOUtils.hasDependentValue(this.ownerMmd)) {
            if (SCOUtils.hasDependentKey(this.ownerMmd)) {
               this.ownerOP.getExecutionContext().deleteObjectInternal(key);
            }

            if (SCOUtils.hasDependentValue(this.ownerMmd)) {
               this.ownerOP.getExecutionContext().deleteObjectInternal(value);
            }
         }
      }

      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return value;
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new java.util.Hashtable(this.delegate);
   }
}
