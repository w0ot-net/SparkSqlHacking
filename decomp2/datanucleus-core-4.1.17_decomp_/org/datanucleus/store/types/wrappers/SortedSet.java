package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import org.datanucleus.flush.CollectionAddOperation;
import org.datanucleus.flush.CollectionRemoveOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.RelationshipManager;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.types.SCOCollection;
import org.datanucleus.store.types.SCOCollectionIterator;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class SortedSet extends AbstractSet implements java.util.SortedSet, SCOCollection, Cloneable, Serializable {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;
   protected java.util.SortedSet delegate;

   public SortedSet(ObjectProvider ownerOP, AbstractMemberMetaData mmd) {
      this.ownerOP = ownerOP;
      this.ownerMmd = mmd;
   }

   public void initialise(java.util.SortedSet newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.util.SortedSet c) {
      if (c != null) {
         this.initialiseDelegate();
         this.delegate.addAll(c);
      } else {
         this.initialiseDelegate();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("023003", this.getClass().getName(), this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + this.size(), SCOUtils.getSCOWrapperOptionsMessage(true, false, true, false)));
      }

   }

   public void initialise() {
      this.initialise((java.util.SortedSet)null);
   }

   protected void initialiseDelegate() {
      Comparator comparator = SCOUtils.getComparator(this.ownerMmd, this.ownerOP.getExecutionContext().getClassLoaderResolver());
      if (comparator != null) {
         this.delegate = new java.util.TreeSet(comparator);
      } else {
         this.delegate = new java.util.TreeSet();
      }

   }

   public java.util.SortedSet getValue() {
      return this.delegate;
   }

   public void setValue(java.util.SortedSet value) {
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

   public java.util.SortedSet detachCopy(FetchPlanState state) {
      Comparator comparator = SCOUtils.getComparator(this.ownerMmd, this.ownerOP.getExecutionContext().getClassLoaderResolver());
      java.util.SortedSet detached = null;
      if (comparator != null) {
         detached = new java.util.TreeSet(comparator);
      } else {
         detached = new java.util.TreeSet();
      }

      SCOUtils.detachCopyForCollection(this.ownerOP, this.toArray(), state, detached);
      return detached;
   }

   public void attachCopy(java.util.SortedSet value) {
      boolean elementsWithoutIdentity = SCOUtils.collectionHasElementsWithoutIdentity(this.ownerMmd);
      SCOUtils.attachCopyElements(this.ownerOP, this, value, elementsWithoutIdentity);
   }

   public Object clone() {
      return ((java.util.TreeSet)this.delegate).clone();
   }

   public Comparator comparator() {
      return this.delegate.comparator();
   }

   public boolean contains(Object element) {
      return this.delegate.contains(element);
   }

   public synchronized boolean containsAll(java.util.Collection c) {
      return this.delegate.containsAll(c);
   }

   public synchronized boolean equals(Object o) {
      return this.delegate.equals(o);
   }

   public Object first() {
      return this.delegate.first();
   }

   public synchronized int hashCode() {
      return this.delegate.hashCode();
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public Iterator iterator() {
      return new SCOCollectionIterator(this, this.ownerOP, this.delegate, (CollectionStore)null, true);
   }

   public java.util.SortedSet headSet(Object toElement) {
      return this.delegate.headSet(toElement);
   }

   public java.util.SortedSet subSet(Object fromElement, Object toElement) {
      return this.delegate.subSet(fromElement, toElement);
   }

   public java.util.SortedSet tailSet(Object fromElement) {
      return this.delegate.headSet(fromElement);
   }

   public Object last() {
      return this.delegate.last();
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

   public boolean add(Object element) {
      boolean success = this.delegate.add(element);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), element);
      }

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
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         Iterator iter = elements.iterator();

         while(iter.hasNext()) {
            this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
         }
      }

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
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         Iterator iter = this.delegate.iterator();
         RelationshipManager relMgr = this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP);

         while(iter.hasNext()) {
            relMgr.relationRemove(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
         }
      }

      if (this.ownerOP != null && !this.delegate.isEmpty()) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            Iterator iter = this.delegate.iterator();

            while(iter.hasNext()) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), iter.next(), true));
            }
         } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
            Iterator iter = this.delegate.iterator();

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

   public synchronized boolean remove(Object element) {
      return this.remove(element, true);
   }

   public synchronized boolean remove(Object element, boolean allowCascadeDelete) {
      boolean success = this.delegate.remove(element);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationRemove(this.ownerMmd.getAbsoluteFieldNumber(), element);
      }

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
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         Iterator iter = elements.iterator();
         RelationshipManager relMgr = this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP);

         while(iter.hasNext()) {
            relMgr.relationRemove(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
         }
      }

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

   public synchronized boolean retainAll(java.util.Collection c) {
      if (c == null) {
         throw new NullPointerException("Input collection was null");
      } else {
         java.util.Collection collToRemove = new java.util.TreeSet();

         for(Object o : this.delegate) {
            if (!c.contains(o)) {
               collToRemove.add(o);
            }
         }

         boolean success = this.delegate.retainAll(c);
         if (success) {
            this.makeDirty();
            if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
               Iterator iter = collToRemove.iterator();

               while(iter.hasNext()) {
                  this.ownerOP.getExecutionContext().addOperationToQueue(new CollectionRemoveOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), iter.next(), true));
               }
            } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
               Iterator iter = collToRemove.iterator();

               while(iter.hasNext()) {
                  this.ownerOP.getExecutionContext().deleteObjectInternal(iter.next());
               }
            }

            if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
               this.ownerOP.getExecutionContext().processNontransactionalUpdate();
            }
         }

         return success;
      }
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new java.util.TreeSet(this.delegate);
   }
}
