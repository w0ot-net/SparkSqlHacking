package org.datanucleus.store.types.wrappers.backed;

import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.ListIterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.flush.CollectionAddOperation;
import org.datanucleus.flush.CollectionClearOperation;
import org.datanucleus.flush.CollectionRemoveOperation;
import org.datanucleus.flush.ListAddAtOperation;
import org.datanucleus.flush.ListRemoveAtOperation;
import org.datanucleus.flush.ListSetOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.BackedSCOStoreManager;
import org.datanucleus.store.scostore.ListStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.SCOListIterator;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class Stack extends org.datanucleus.store.types.wrappers.Stack implements BackedSCO {
   protected transient ListStore backingStore;
   protected transient boolean allowNulls = false;
   protected transient boolean useCache = true;
   protected transient boolean isCacheLoaded = false;

   public Stack(ObjectProvider op, AbstractMemberMetaData mmd) {
      super(op, mmd);
      this.delegate = new java.util.Stack();
      ExecutionContext ec = this.ownerOP.getExecutionContext();
      this.allowNulls = SCOUtils.allowNullsInContainer(this.allowNulls, mmd);
      this.useCache = SCOUtils.useContainerCache(this.ownerOP, mmd);
      if (!SCOUtils.collectionHasSerialisedElements(mmd) && mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         this.backingStore = (ListStore)((BackedSCOStoreManager)this.ownerOP.getStoreManager()).getBackingStoreForField(clr, mmd, java.util.Stack.class);
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(SCOUtils.getContainerInfoMessage(this.ownerOP, this.ownerMmd.getName(), this, this.useCache, this.allowNulls, SCOUtils.useCachedLazyLoading(this.ownerOP, this.ownerMmd)));
      }

   }

   public void initialise(java.util.Stack newValue, Object oldValue) {
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

         if (this.backingStore != null) {
            if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
               if (this.ownerOP.isFlushedToDatastore() || !this.ownerOP.getLifecycleState().isNew()) {
                  this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionClearOperation(this.ownerOP, this.backingStore));

                  for(Object element : newValue) {
                     this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionAddOperation(this.ownerOP, this.backingStore, element));
                  }
               }
            } else {
               this.backingStore.clear(this.ownerOP);

               try {
                  this.backingStore.addAll(this.ownerOP, newValue, this.useCache ? 0 : -1);
               } catch (NucleusDataStoreException dse) {
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("023013", "addAll", this.ownerMmd.getName(), dse));
               }
            }
         }

         this.delegate.addAll(newValue);
         this.isCacheLoaded = true;
         this.makeDirty();
      }

   }

   public void initialise(java.util.Stack c) {
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

   public java.util.Stack getValue() {
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

   public synchronized void unsetOwner() {
      super.unsetOwner();
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

   public boolean contains(Object element) {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.contains(element);
      } else {
         return this.backingStore != null ? this.backingStore.contains(this.ownerOP, element) : this.delegate.contains(element);
      }
   }

   public boolean empty() {
      return this.isEmpty();
   }

   public synchronized boolean equals(Object o) {
      if (this.useCache) {
         this.loadFromStore();
      }

      if (o == this) {
         return true;
      } else if (!(o instanceof java.util.List)) {
         return false;
      } else {
         java.util.List l = (java.util.List)o;
         if (l.size() != this.size()) {
            return false;
         } else {
            Object[] elements = this.toArray();
            Object[] otherElements = l.toArray();

            for(int i = 0; i < elements.length; ++i) {
               if (!elements[i].equals(otherElements[i])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public synchronized Object get(int index) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return this.backingStore.get(this.ownerOP, index);
      }

      return this.delegate.get(index);
   }

   public int indexOf(Object element) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return this.backingStore.indexOf(this.ownerOP, element);
      }

      return this.delegate.indexOf(element);
   }

   public synchronized boolean isEmpty() {
      return this.size() == 0;
   }

   public synchronized Iterator iterator() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return new SCOListIterator(this, this.ownerOP, this.delegate, this.backingStore, this.useCache, -1);
   }

   public synchronized ListIterator listIterator() {
      if (this.useCache) {
         this.loadFromStore();
      }

      return new SCOListIterator(this, this.ownerOP, this.delegate, this.backingStore, this.useCache, -1);
   }

   public synchronized ListIterator listIterator(int index) {
      if (this.useCache) {
         this.loadFromStore();
      }

      return new SCOListIterator(this, this.ownerOP, this.delegate, this.backingStore, this.useCache, index);
   }

   public synchronized int lastIndexOf(Object element) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return this.backingStore.lastIndexOf(this.ownerOP, element);
      }

      return this.delegate.lastIndexOf(element);
   }

   public synchronized Object peek() {
      return this.get(0);
   }

   public synchronized int size() {
      if (this.useCache && this.isCacheLoaded) {
         return this.delegate.size();
      } else {
         return this.backingStore != null ? this.backingStore.size(this.ownerOP) : this.delegate.size();
      }
   }

   public synchronized java.util.List subList(int from, int to) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return this.backingStore.subList(this.ownerOP, from, to);
      }

      return this.delegate.subList(from, to);
   }

   public synchronized Object[] toArray() {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return SCOUtils.toArray(this.backingStore, this.ownerOP);
      }

      return this.delegate.toArray();
   }

   public synchronized Object[] toArray(Object[] a) {
      if (this.useCache) {
         this.loadFromStore();
      } else if (this.backingStore != null) {
         return SCOUtils.toArray(this.backingStore, this.ownerOP, a);
      }

      return this.delegate.toArray(a);
   }

   public void add(int index, Object element) {
      if (!this.allowNulls && element == null) {
         throw new NullPointerException("Nulls not allowed for collection at field " + this.ownerMmd.getName() + " but element is null");
      } else {
         if (this.useCache) {
            this.loadFromStore();
         }

         if (this.backingStore != null) {
            if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new ListAddAtOperation(this.ownerOP, this.backingStore, index, element));
            } else {
               try {
                  this.backingStore.add(this.ownerOP, element, index, this.useCache ? this.delegate.size() : -1);
               } catch (NucleusDataStoreException dse) {
                  throw new IllegalArgumentException(Localiser.msg("023013", "add", this.ownerMmd.getName(), dse), dse);
               }
            }
         }

         this.makeDirty();
         this.delegate.add(index, element);
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }

      }
   }

   public synchronized boolean add(Object element) {
      if (!this.allowNulls && element == null) {
         throw new NullPointerException("Nulls not allowed for collection at field " + this.ownerMmd.getName() + " but element is null");
      } else {
         if (this.useCache) {
            this.loadFromStore();
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

   public synchronized void addElement(Object element) {
      this.add(element);
   }

   public synchronized boolean addAll(java.util.Collection elements) {
      if (this.useCache) {
         this.loadFromStore();
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

   public synchronized boolean addAll(int index, java.util.Collection elements) {
      if (this.useCache) {
         this.loadFromStore();
      }

      boolean backingSuccess = true;
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            int pos = index;

            for(Object element : elements) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new ListAddAtOperation(this.ownerOP, this.backingStore, pos++, element));
            }
         } else {
            try {
               backingSuccess = this.backingStore.addAll(this.ownerOP, elements, index, this.useCache ? this.delegate.size() : -1);
            } catch (NucleusDataStoreException dse) {
               throw new IllegalArgumentException(Localiser.msg("023013", "addAll", this.ownerMmd.getName(), dse), dse);
            }
         }
      }

      this.makeDirty();
      boolean delegateSuccess = this.delegate.addAll(index, elements);
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return this.backingStore != null ? backingSuccess : delegateSuccess;
   }

   public synchronized void clear() {
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

   public synchronized Object pop() {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new ListRemoveAtOperation(this.ownerOP, this.backingStore, 0));
         } else {
            this.backingStore.remove(this.ownerOP, 0, this.useCache ? this.delegate.size() : -1);
         }
      }

      E removed = (E)this.delegate.remove(0);
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return removed;
   }

   public Object push(Object element) {
      if (!this.allowNulls && element == null) {
         throw new NullPointerException("Nulls not allowed for collection at field " + this.ownerMmd.getName() + " but element is null");
      } else {
         if (this.useCache) {
            this.loadFromStore();
         }

         if (this.backingStore != null) {
            if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new ListAddAtOperation(this.ownerOP, this.backingStore, 0, element));
            } else {
               this.backingStore.add(this.ownerOP, element, 0, this.useCache ? this.delegate.size() : -1);
            }
         }

         this.makeDirty();
         this.delegate.add(0, element);
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }

         return element;
      }
   }

   public synchronized boolean remove(Object element) {
      return this.remove(element, true);
   }

   public synchronized boolean remove(Object element, boolean allowCascadeDelete) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      int size = this.useCache ? this.delegate.size() : -1;
      boolean contained = this.delegate.contains(element);
      boolean delegateSuccess = this.delegate.remove(element);
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

   public synchronized boolean removeAll(java.util.Collection elements) {
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

   public synchronized boolean removeElement(Object element) {
      return this.remove(element);
   }

   public synchronized Object remove(int index) {
      this.makeDirty();
      if (this.useCache) {
         this.loadFromStore();
      }

      int size = this.useCache ? this.delegate.size() : -1;
      E delegateObject = (E)(this.useCache ? this.delegate.remove(index) : null);
      E backingObject = (E)null;
      if (this.backingStore != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            backingObject = delegateObject;
            this.ownerOP.getExecutionContext().addOperationToQueue(new ListRemoveAtOperation(this.ownerOP, this.backingStore, index));
         } else {
            try {
               backingObject = (E)this.backingStore.remove(this.ownerOP, index, size);
            } catch (NucleusDataStoreException dse) {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("023013", "remove", this.ownerMmd.getName(), dse));
               backingObject = (E)null;
            }
         }
      }

      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return this.backingStore != null ? backingObject : delegateObject;
   }

   public synchronized void removeElementAt(int index) {
      this.remove(index);
   }

   public synchronized void removeAllElements() {
      this.clear();
   }

   public synchronized boolean retainAll(java.util.Collection c) {
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

   public Object set(int index, Object element, boolean allowDependentField) {
      if (!this.allowNulls && element == null) {
         throw new NullPointerException("Nulls not allowed for collection at field " + this.ownerMmd.getName() + " but element is null");
      } else {
         this.makeDirty();
         if (this.useCache) {
            this.loadFromStore();
         }

         E delegateReturn = (E)this.delegate.set(index, element);
         if (this.backingStore != null) {
            if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new ListSetOperation(this.ownerOP, this.backingStore, index, element, allowDependentField));
            } else {
               this.backingStore.set(this.ownerOP, index, element, allowDependentField);
            }
         }

         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }

         return delegateReturn;
      }
   }

   public synchronized Object set(int index, Object element) {
      return this.set(index, element, true);
   }

   public synchronized void setElementAt(Object element, int index) {
      this.set(index, element);
   }

   protected Object writeReplace() throws ObjectStreamException {
      if (this.useCache) {
         this.loadFromStore();
         java.util.Stack stack = new java.util.Stack();
         stack.addAll(this.delegate);
         return stack;
      } else {
         java.util.Stack stack = new java.util.Stack();
         stack.addAll(this.delegate);
         return stack;
      }
   }
}
