package org.datanucleus.store.types.wrappers.backed;

import java.io.ObjectStreamException;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.flush.CollectionAddOperation;
import org.datanucleus.flush.CollectionClearOperation;
import org.datanucleus.flush.CollectionRemoveOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.RelationshipManager;
import org.datanucleus.store.BackedSCOStoreManager;
import org.datanucleus.store.scostore.SetStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.SCOCollection;
import org.datanucleus.store.types.SCOCollectionIterator;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class LinkedHashSet extends org.datanucleus.store.types.wrappers.LinkedHashSet implements BackedSCO {
   protected transient boolean allowNulls = false;
   protected transient SetStore backingStore;
   protected transient boolean useCache = true;
   protected transient boolean isCacheLoaded = false;
   protected transient boolean initialising = false;

   public LinkedHashSet(ObjectProvider op, AbstractMemberMetaData mmd) {
      super(op, mmd);
      this.delegate = new java.util.LinkedHashSet();
      ExecutionContext ec = this.ownerOP.getExecutionContext();
      this.allowNulls = SCOUtils.allowNullsInContainer(this.allowNulls, mmd);
      this.useCache = SCOUtils.useContainerCache(this.ownerOP, mmd);
      if (!SCOUtils.collectionHasSerialisedElements(mmd) && mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         this.backingStore = (SetStore)((BackedSCOStoreManager)this.ownerOP.getStoreManager()).getBackingStoreForField(clr, mmd, java.util.LinkedHashSet.class);
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(SCOUtils.getContainerInfoMessage(this.ownerOP, this.ownerMmd.getName(), this, this.useCache, this.allowNulls, SCOUtils.useCachedLazyLoading(this.ownerOP, this.ownerMmd)));
      }

   }

   public void initialise(java.util.LinkedHashSet newValue, Object oldValue) {
      if (newValue != null) {
         if (SCOUtils.collectionHasSerialisedElements(this.ownerMmd) && this.ownerMmd.getCollection().elementIsPersistent()) {
            ExecutionContext ec = this.ownerOP.getExecutionContext();

            for(Object pc : newValue) {
               ObjectProvider objSM = ec.findObjectProvider(pc);
               if (objSM == null) {
                  ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, pc, false, this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber());
               }
            }
         }

         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("023008", this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + newValue.size()));
         }

         this.initialising = true;
         if (this.useCache) {
            java.util.Collection oldColl = (java.util.Collection)oldValue;
            if (oldColl != null) {
               this.delegate.addAll(oldColl);
            }

            this.isCacheLoaded = true;
            SCOUtils.updateCollectionWithCollection(this.ownerOP.getExecutionContext().getApiAdapter(), this, newValue);
         } else {
            java.util.Collection oldColl = (java.util.Collection)oldValue;
            if (oldColl instanceof SCOCollection) {
               oldColl = (java.util.Collection)((SCOCollection)oldColl).getValue();
            }

            for(Object elem : newValue) {
               if (oldColl == null || !oldColl.contains(elem)) {
                  this.add(elem);
               }
            }

            if (oldColl != null) {
               for(Object elem : oldColl) {
                  if (!newValue.contains(elem)) {
                     this.remove(elem);
                  }
               }
            }
         }

         this.initialising = false;
      }

   }

   public void initialise(java.util.LinkedHashSet c) {
      if (c != null) {
         if (SCOUtils.collectionHasSerialisedElements(this.ownerMmd) && this.ownerMmd.getCollection().elementIsPersistent()) {
            ExecutionContext ec = this.ownerOP.getExecutionContext();

            for(Object pc : c) {
               ObjectProvider objSM = ec.findObjectProvider(pc);
               if (objSM == null) {
                  ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, pc, false, this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber());
               }
            }
         }

         if (this.backingStore != null && this.useCache && !this.isCacheLoaded) {
            this.isCacheLoaded = true;
         }

         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("023007", this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + c.size()));
         }

         this.delegate.clear();
         this.delegate.addAll(c);
      }

   }

   public void initialise() {
      if (this.useCache && !SCOUtils.useCachedLazyLoading(this.ownerOP, this.ownerMmd)) {
         this.loadFromStore();
      }

   }

   public java.util.LinkedHashSet getValue() {
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
         Iterator<E> iter = this.backingStore.iterator(this.ownerOP);

         while(iter.hasNext()) {
            this.delegate.add(iter.next());
         }

         this.isCacheLoaded = true;
      }

   }

   public Store getBackingStore() {
      return this.backingStore;
   }

   public void updateEmbeddedElement(Object element, int fieldNumber, Object value, boolean makeDirty) {
      if (this.backingStore != null) {
         this.backingStore.updateEmbeddedElement(this.ownerOP, element, fieldNumber, value);
      }

   }

   public void unsetOwner() {
      super.unsetOwner();
      if (this.backingStore != null) {
         this.backingStore = null;
      }

   }

   public Object clone() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return super.clone();
   }

   public boolean contains(Object element) {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.contains(element);
      } else {
         return this.backingStore != null ? this.backingStore.contains(this.ownerOP, element) : this.delegate.contains(element);
      }
   }

   public boolean containsAll(java.util.Collection c) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         java.util.LinkedHashSet h = new java.util.LinkedHashSet(c);
         Iterator iter = this.iterator();

         while(iter.hasNext()) {
            h.remove(iter.next());
         }

         return h.isEmpty();
      }

      return this.delegate.containsAll(c);
   }

   public boolean equals(Object o) {
      if (this.useCache) {
         this.loadFromStore();
      }

      if (o == this) {
         return true;
      } else if (!(o instanceof java.util.Set)) {
         return false;
      } else {
         java.util.Set c = (java.util.Set)o;
         return c.size() == this.size() && this.containsAll(c);
      }
   }

   public int hashCode() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return this.delegate.hashCode();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Iterator iterator() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return new SCOCollectionIterator(this, this.ownerOP, this.delegate, this.backingStore, this.useCache);
   }

   public int size() {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.size();
      } else {
         return this.backingStore != null ? this.backingStore.size(this.ownerOP) : this.delegate.size();
      }
   }

   public Object[] toArray() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return this.delegate.toArray();
   }

   public Object[] toArray(Object[] a) {
      if (this.useCache) {
         this.loadFromStore();
      }

      return this.delegate.toArray(a);
   }

   public boolean add(Object element) {
      if (!this.allowNulls && element == null) {
         throw new NullPointerException("Nulls not allowed for collection at field " + this.ownerMmd.getName() + " but element is null");
      } else {
         if (this.useCache) {
            this.loadFromStore();
         }

         if (this.contains(element)) {
            return false;
         } else {
            if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations() && !this.initialising) {
               this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), element);
            }

            boolean backingSuccess = true;
            if (this.backingStore != null) {
               if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
                  this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionAddOperation(this.ownerOP, this.backingStore, element));
               } else {
                  try {
                     backingSuccess = this.backingStore.add(this.ownerOP, element, this.useCache ? this.delegate.size() : -1);
                  } catch (NucleusDataStoreException dse) {
                     throw new IllegalArgumentException(Localiser.msg("023013", "add", this.ownerMmd.getName(), dse), dse);
                  }
               }
            }

            this.makeDirty();
            boolean delegateSuccess = this.delegate.add(element);
            if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
               this.ownerOP.getExecutionContext().processNontransactionalUpdate();
            }

            return this.backingStore != null ? backingSuccess : delegateSuccess;
         }
      }
   }

   public boolean addAll(java.util.Collection elements) {
      if (this.useCache) {
         this.loadFromStore();
      }

      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations() && !this.initialising) {
         Iterator iter = elements.iterator();
         RelationshipManager relMgr = this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP);

         while(iter.hasNext()) {
            relMgr.relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
         }
      }

      boolean backingSuccess = true;
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            for(Object element : elements) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionAddOperation(this.ownerOP, this.backingStore, element));
            }
         } else {
            try {
               backingSuccess = this.backingStore.addAll(this.ownerOP, elements, this.useCache ? this.delegate.size() : -1);
            } catch (NucleusDataStoreException dse) {
               throw new IllegalArgumentException(Localiser.msg("023013", "addAll", this.ownerMmd.getName(), dse), dse);
            }
         }
      }

      this.makeDirty();
      boolean delegateSuccess = this.delegate.addAll(elements);
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return this.backingStore != null ? backingSuccess : delegateSuccess;
   }

   public void clear() {
      this.makeDirty();
      this.delegate.clear();
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionClearOperation(this.ownerOP, this.backingStore));
         } else {
            this.backingStore.clear(this.ownerOP);
         }
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

   }

   public boolean remove(Object element) {
      return this.remove(element, true);
   }

   public boolean remove(Object element, boolean allowCascadeDelete) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      int size = this.useCache ? this.delegate.size() : -1;
      boolean contained = this.delegate.contains(element);
      boolean delegateSuccess = this.delegate.remove(element);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations() && !this.initialising) {
         this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationRemove(this.ownerMmd.getAbsoluteFieldNumber(), element);
      }

      boolean backingSuccess = true;
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            backingSuccess = contained;
            if (contained) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.backingStore, element, allowCascadeDelete));
            }
         } else {
            try {
               backingSuccess = this.backingStore.remove(this.ownerOP, element, size, allowCascadeDelete);
            } catch (NucleusDataStoreException dse) {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("023013", "remove", this.ownerMmd.getName(), dse));
               backingSuccess = false;
            }
         }
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return this.backingStore != null ? backingSuccess : delegateSuccess;
   }

   public boolean removeAll(java.util.Collection elements) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      int size = this.useCache ? this.delegate.size() : -1;
      java.util.Collection contained = null;
      if (this.backingStore != null && SCOUtils.useQueuedUpdate(this.ownerOP)) {
         contained = new java.util.HashSet();

         for(Object elem : elements) {
            if (this.contains(elem)) {
               contained.add(elem);
            }
         }
      }

      boolean delegateSuccess = this.delegate.removeAll(elements);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations() && !this.initialising) {
         Iterator iter = elements.iterator();
         RelationshipManager relMgr = this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP);

         while(iter.hasNext()) {
            relMgr.relationRemove(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
         }
      }

      if (this.backingStore == null) {
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }

         return delegateSuccess;
      } else {
         boolean backingSuccess = true;
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            backingSuccess = false;

            for(Object element : contained) {
               backingSuccess = true;
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.backingStore, element, true));
            }
         } else {
            try {
               backingSuccess = this.backingStore.removeAll(this.ownerOP, elements, size);
            } catch (NucleusDataStoreException dse) {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("023013", "removeAll", this.ownerMmd.getName(), dse));
               backingSuccess = false;
            }
         }

         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }

         return backingSuccess;
      }
   }

   public boolean retainAll(java.util.Collection c) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      boolean modified = false;
      Iterator iter = this.iterator();

      while(iter.hasNext()) {
         Object element = iter.next();
         if (!c.contains(element)) {
            iter.remove();
            modified = true;
         }
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return modified;
   }

   protected Object writeReplace() throws ObjectStreamException {
      if (this.useCache) {
         this.loadFromStore();
         return new java.util.LinkedHashSet(this.delegate);
      } else {
         return new java.util.LinkedHashSet(this.delegate);
      }
   }
}
