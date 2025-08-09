package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import java.util.Comparator;
import java.util.Iterator;
import org.datanucleus.flush.CollectionAddOperation;
import org.datanucleus.flush.CollectionRemoveOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.types.SCOCollection;
import org.datanucleus.store.types.SCOCollectionIterator;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class PriorityQueue extends java.util.PriorityQueue implements SCOCollection, Cloneable {
   protected ObjectProvider ownerOP;
   protected AbstractMemberMetaData ownerMmd;
   protected java.util.PriorityQueue delegate;

   public PriorityQueue(ObjectProvider ownerOP, AbstractMemberMetaData mmd) {
      super(1);
      this.ownerOP = ownerOP;
      this.ownerMmd = mmd;
   }

   public void initialise(java.util.PriorityQueue newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.util.PriorityQueue c) {
      if (c != null) {
         this.initialiseDelegate();
         this.delegate.addAll(c);
      } else {
         this.initialiseDelegate();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("023003", this.getClass().getName(), this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + this.size(), SCOUtils.getSCOWrapperOptionsMessage(true, false, false, false)));
      }

   }

   public void initialise() {
      this.initialise((java.util.PriorityQueue)null);
   }

   protected void initialiseDelegate() {
      Comparator comparator = SCOUtils.getComparator(this.ownerMmd, this.ownerOP.getExecutionContext().getClassLoaderResolver());
      if (comparator != null) {
         this.delegate = new java.util.PriorityQueue(5, comparator);
      } else {
         this.delegate = new java.util.PriorityQueue();
      }

   }

   public java.util.PriorityQueue getValue() {
      return this.delegate;
   }

   public void setValue(java.util.PriorityQueue value) {
      this.delegate = value;
   }

   public void load() {
   }

   public boolean isLoaded() {
      return true;
   }

   public void updateEmbeddedElement(Object element, int fieldNumber, Object value, boolean makeDirty) {
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

   public void unsetOwner() {
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

   public java.util.PriorityQueue detachCopy(FetchPlanState state) {
      java.util.PriorityQueue detached = new java.util.PriorityQueue();
      SCOUtils.detachCopyForCollection(this.ownerOP, this.toArray(), state, detached);
      return detached;
   }

   public void attachCopy(java.util.PriorityQueue value) {
      boolean elementsWithoutIdentity = SCOUtils.collectionHasElementsWithoutIdentity(this.ownerMmd);
      SCOUtils.attachCopyElements(this.ownerOP, this, value, elementsWithoutIdentity);
   }

   public Object clone() {
      return null;
   }

   public Comparator comparator() {
      return this.delegate.comparator();
   }

   public boolean contains(Object element) {
      return this.delegate.contains(element);
   }

   public boolean containsAll(java.util.Collection c) {
      return this.delegate.containsAll(c);
   }

   public boolean equals(Object o) {
      return this.delegate.equals(o);
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public Iterator iterator() {
      return new SCOCollectionIterator(this, this.ownerOP, this.delegate, (CollectionStore)null, true);
   }

   public Object peek() {
      return this.delegate.peek();
   }

   public int size() {
      return this.delegate.size();
   }

   public Object[] toArray() {
      return this.delegate.toArray();
   }

   public Object[] toArray(Object[] a) {
      return this.delegate.toArray(a);
   }

   public String toString() {
      StringBuilder s = new StringBuilder("[");
      int i = 0;

      for(Iterator iter = this.iterator(); iter.hasNext(); ++i) {
         if (i > 0) {
            s.append(',');
         }

         s.append(iter.next());
      }

      s.append("]");
      return s.toString();
   }

   public boolean add(Object element) {
      boolean success = this.delegate.add(element);
      if (success) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionAddOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), element));
         }

         this.makeDirty();
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

      return success;
   }

   public boolean addAll(java.util.Collection elements) {
      boolean success = this.delegate.addAll(elements);
      if (success) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            for(Object element : elements) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionAddOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), element));
            }
         }

         this.makeDirty();
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

      return success;
   }

   public void clear() {
      if (this.ownerOP != null && !this.delegate.isEmpty()) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            java.util.List copy = new java.util.ArrayList(this.delegate);
            Iterator iter = copy.iterator();

            while(iter.hasNext()) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), iter.next(), true));
            }
         } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
            java.util.List copy = new java.util.ArrayList(this.delegate);
            Iterator iter = copy.iterator();

            while(iter.hasNext()) {
               this.ownerOP.getExecutionContext().deleteObjectInternal(iter.next());
            }
         }
      }

      this.delegate.clear();
      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

   }

   public boolean offer(Object element) {
      return this.add(element);
   }

   public Object poll() {
      E obj = (E)this.delegate.poll();
      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return obj;
   }

   public boolean remove(Object element) {
      return this.remove(element, true);
   }

   public boolean remove(Object element, boolean allowCascadeDelete) {
      boolean success = this.delegate.remove(element);
      if (this.ownerOP != null && allowCascadeDelete) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), element, allowCascadeDelete));
         } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
            this.ownerOP.getExecutionContext().deleteObjectInternal(element);
         }
      }

      if (success) {
         this.makeDirty();
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

      return success;
   }

   public boolean removeAll(java.util.Collection elements) {
      boolean success = this.delegate.removeAll(elements);
      if (this.ownerOP != null && elements != null && !elements.isEmpty()) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            Iterator iter = elements.iterator();

            while(iter.hasNext()) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), iter.next(), true));
            }
         } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
            Iterator iter = elements.iterator();

            while(iter.hasNext()) {
               this.ownerOP.getExecutionContext().deleteObjectInternal(iter.next());
            }
         }
      }

      if (success) {
         this.makeDirty();
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

      return success;
   }

   public boolean retainAll(java.util.Collection c) {
      boolean success = this.delegate.retainAll(c);
      if (success) {
         this.makeDirty();
         if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

      return success;
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new java.util.PriorityQueue(this.delegate);
   }
}
