package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.ListIterator;
import org.datanucleus.flush.CollectionAddOperation;
import org.datanucleus.flush.CollectionRemoveOperation;
import org.datanucleus.flush.ListAddAtOperation;
import org.datanucleus.flush.ListRemoveAtOperation;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.RelationshipManager;
import org.datanucleus.store.scostore.ListStore;
import org.datanucleus.store.types.SCOList;
import org.datanucleus.store.types.SCOListIterator;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ArrayList extends java.util.ArrayList implements SCOList {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;
   protected java.util.ArrayList delegate;

   public ArrayList(ObjectProvider ownerOP, AbstractMemberMetaData mmd) {
      super(0);
      this.ownerOP = ownerOP;
      this.ownerMmd = mmd;
   }

   public void initialise(java.util.ArrayList newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.util.ArrayList c) {
      if (c != null) {
         this.delegate = c;
      } else {
         this.delegate = new java.util.ArrayList();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("023003", this.getClass().getName(), this.ownerOP.getObjectAsPrintable(), this.ownerMmd.getName(), "" + this.size(), SCOUtils.getSCOWrapperOptionsMessage(true, false, true, false)));
      }

   }

   public void initialise() {
      this.initialise((java.util.ArrayList)null);
   }

   public java.util.ArrayList getValue() {
      return this.delegate;
   }

   public void setValue(java.util.ArrayList value) {
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

   public java.util.ArrayList detachCopy(FetchPlanState state) {
      java.util.ArrayList detached = new java.util.ArrayList();
      SCOUtils.detachCopyForCollection(this.ownerOP, this.toArray(), state, detached);
      return detached;
   }

   public void attachCopy(java.util.ArrayList value) {
      boolean elementsWithoutIdentity = SCOUtils.collectionHasElementsWithoutIdentity(this.ownerMmd);
      java.util.List attachedElements = new java.util.ArrayList(value.size());
      SCOUtils.attachCopyForCollection(this.ownerOP, value.toArray(), attachedElements, elementsWithoutIdentity);
      SCOUtils.updateListWithListElements(this, attachedElements);
   }

   public Object clone() {
      return this.delegate.clone();
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

   public Object get(int index) {
      return this.delegate.get(index);
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }

   public int indexOf(Object element) {
      return this.delegate.indexOf(element);
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public Iterator iterator() {
      return new SCOListIterator(this, this.ownerOP, this.delegate, (ListStore)null, true, -1);
   }

   public ListIterator listIterator() {
      return new SCOListIterator(this, this.ownerOP, this.delegate, (ListStore)null, true, -1);
   }

   public ListIterator listIterator(int index) {
      return new SCOListIterator(this, this.ownerOP, this.delegate, (ListStore)null, true, index);
   }

   public int lastIndexOf(Object element) {
      return this.delegate.lastIndexOf(element);
   }

   public int size() {
      return this.delegate.size();
   }

   public java.util.List subList(int from, int to) {
      return this.delegate.subList(from, to);
   }

   public Object[] toArray() {
      return this.delegate.toArray();
   }

   public Object[] toArray(Object[] a) {
      return this.delegate.toArray(a);
   }

   public void add(int index, Object element) {
      this.delegate.add(index, element);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), element);
      }

      if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
         this.ownerOP.getExecutionContext().addOperationToQueue(new ListAddAtOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), index, element));
      }

      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

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
         RelationshipManager relMgr = this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP);

         while(iter.hasNext()) {
            relMgr.relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
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

   public boolean addAll(int index, java.util.Collection elements) {
      boolean success = this.delegate.addAll(index, elements);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         Iterator iter = elements.iterator();
         RelationshipManager relMgr = this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP);

         while(iter.hasNext()) {
            relMgr.relationAdd(this.ownerMmd.getAbsoluteFieldNumber(), iter.next());
         }
      }

      if (success) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            int pos = index;

            for(Object element : elements) {
               this.ownerOP.getExecutionContext().addOperationToQueue(new ListAddAtOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), pos++, element));
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

   public boolean remove(Object element) {
      return this.remove(element, true);
   }

   public boolean remove(Object element, boolean allowCascadeDelete) {
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

   public Object remove(int index) {
      E element = (E)this.delegate.remove(index);
      if (this.ownerOP != null && this.ownerOP.getExecutionContext().getManageRelations()) {
         this.ownerOP.getExecutionContext().getRelationshipManager(this.ownerOP).relationRemove(this.ownerMmd.getAbsoluteFieldNumber(), element);
      }

      if (this.ownerOP != null) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new ListRemoveAtOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), index, element));
         } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
            this.ownerOP.getExecutionContext().deleteObjectInternal(element);
         }
      }

      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return element;
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

   public Object set(int index, Object element, boolean allowDependentField) {
      E prevElement = (E)this.delegate.set(index, element);
      if (this.ownerOP != null && allowDependentField && !this.delegate.contains(prevElement)) {
         if (SCOUtils.useQueuedUpdate(this.ownerOP)) {
            this.ownerOP.getExecutionContext().addOperationToQueue(new ListRemoveAtOperation(this.ownerOP, this.ownerMmd.getAbsoluteFieldNumber(), index, prevElement));
         } else if (SCOUtils.hasDependentElement(this.ownerMmd)) {
            this.ownerOP.getExecutionContext().deleteObjectInternal(prevElement);
         }
      }

      this.makeDirty();
      if (this.ownerOP != null && !this.ownerOP.getExecutionContext().getTransaction().isActive()) {
         this.ownerOP.getExecutionContext().processNontransactionalUpdate();
      }

      return prevElement;
   }

   public Object set(int index, Object element) {
      return this.set(index, element, true);
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new java.util.ArrayList(this.delegate);
   }
}
