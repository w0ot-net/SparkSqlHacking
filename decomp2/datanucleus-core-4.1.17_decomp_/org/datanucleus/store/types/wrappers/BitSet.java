package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;

public class BitSet extends java.util.BitSet implements SCO {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;

   public BitSet(ObjectProvider op, AbstractMemberMetaData mmd) {
      this.ownerOP = op;
      this.ownerMmd = mmd;
   }

   public void initialise() {
   }

   public void initialise(java.util.BitSet newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.util.BitSet set) {
      for(int i = 0; i < this.length(); ++i) {
         super.clear(i);
      }

      super.or(set);
   }

   public java.util.BitSet getValue() {
      java.util.BitSet bits = new java.util.BitSet();
      bits.or(this);
      return bits;
   }

   public void unsetOwner() {
      this.ownerOP = null;
      this.ownerMmd = null;
   }

   public Object getOwner() {
      return this.ownerOP != null ? this.ownerOP.getObject() : null;
   }

   public String getFieldName() {
      return this.ownerMmd.getName();
   }

   public void makeDirty() {
      if (this.ownerOP != null) {
         this.ownerOP.makeDirty(this.ownerMmd.getAbsoluteFieldNumber());
         if (!this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

   }

   public java.util.BitSet detachCopy(FetchPlanState state) {
      java.util.BitSet detached = new java.util.BitSet();
      detached.or(this);
      return detached;
   }

   public void attachCopy(java.util.BitSet value) {
      this.initialise(value);
      this.makeDirty();
   }

   public Object clone() {
      Object obj = super.clone();
      ((BitSet)obj).unsetOwner();
      return obj;
   }

   protected Object writeReplace() throws ObjectStreamException {
      java.util.BitSet copy = new java.util.BitSet();
      copy.and(this);
      return copy;
   }

   public void and(java.util.BitSet set) {
      super.and(set);
      this.makeDirty();
   }

   public void andNot(java.util.BitSet set) {
      super.andNot(set);
      this.makeDirty();
   }

   public void clear(int bitIndex) {
      super.clear(bitIndex);
      this.makeDirty();
   }

   public void or(java.util.BitSet set) {
      super.or(set);
      this.makeDirty();
   }

   public void set(int bitIndex) {
      super.set(bitIndex);
      this.makeDirty();
   }

   public void xor(java.util.BitSet set) {
      super.xor(set);
      this.makeDirty();
   }

   public void clear() {
      super.clear();
      this.makeDirty();
   }

   public void clear(int fromIndex, int toIndex) {
      super.clear(fromIndex, toIndex);
      this.makeDirty();
   }

   public void flip(int fromIndex, int toIndex) {
      super.flip(fromIndex, toIndex);
      this.makeDirty();
   }

   public void flip(int bitIndex) {
      super.flip(bitIndex);
      this.makeDirty();
   }

   public void set(int bitIndex, boolean value) {
      super.set(bitIndex, value);
      this.makeDirty();
   }

   public void set(int fromIndex, int toIndex, boolean value) {
      super.set(fromIndex, toIndex, value);
      this.makeDirty();
   }

   public void set(int fromIndex, int toIndex) {
      super.set(fromIndex, toIndex);
      this.makeDirty();
   }
}
