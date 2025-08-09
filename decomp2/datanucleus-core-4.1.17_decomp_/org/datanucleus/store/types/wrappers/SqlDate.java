package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;

public class SqlDate extends java.sql.Date implements SCO {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;

   public SqlDate(ObjectProvider op, AbstractMemberMetaData mmd) {
      super(0L);
      this.ownerOP = op;
      this.ownerMmd = mmd;
   }

   public void initialise() {
   }

   public void initialise(java.sql.Date newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.sql.Date d) {
      super.setTime(d.getTime());
   }

   public java.sql.Date getValue() {
      return new java.sql.Date(this.getTime());
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

   public java.sql.Date detachCopy(FetchPlanState state) {
      return new java.sql.Date(this.getTime());
   }

   public void attachCopy(java.sql.Date value) {
      long oldValue = this.getTime();
      this.initialise(value);
      long newValue = value.getTime();
      if (oldValue != newValue) {
         this.makeDirty();
      }

   }

   public Object clone() {
      Object obj = super.clone();
      ((SqlDate)obj).unsetOwner();
      return obj;
   }

   public void setTime(long time) {
      super.setTime(time);
      this.makeDirty();
   }

   /** @deprecated */
   public void setYear(int year) {
      super.setYear(year);
      this.makeDirty();
   }

   /** @deprecated */
   public void setMonth(int month) {
      super.setMonth(month);
      this.makeDirty();
   }

   /** @deprecated */
   public void setDate(int date) {
      super.setDate(date);
      this.makeDirty();
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new java.sql.Date(this.getTime());
   }
}
