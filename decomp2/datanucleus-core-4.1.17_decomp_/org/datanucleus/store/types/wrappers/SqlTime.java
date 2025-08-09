package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import java.sql.Time;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;

public class SqlTime extends Time implements SCO {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;

   public SqlTime(ObjectProvider op, AbstractMemberMetaData mmd) {
      super(0L);
      this.ownerOP = op;
      this.ownerMmd = mmd;
   }

   public void initialise() {
   }

   public void initialise(Time newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(Time t) {
      super.setTime(t.getTime());
   }

   public Time getValue() {
      return new Time(this.getTime());
   }

   public void unsetOwner() {
      this.ownerOP = null;
      this.ownerMmd = null;
   }

   public Object getOwner() {
      return this.ownerOP != null ? this.ownerOP.getObject() : null;
   }

   public String getFieldName() {
      return this.ownerMmd.getFullFieldName();
   }

   public void makeDirty() {
      if (this.ownerOP != null) {
         this.ownerOP.makeDirty(this.ownerMmd.getAbsoluteFieldNumber());
         if (!this.ownerOP.getExecutionContext().getTransaction().isActive()) {
            this.ownerOP.getExecutionContext().processNontransactionalUpdate();
         }
      }

   }

   public Time detachCopy(FetchPlanState state) {
      return new Time(this.getTime());
   }

   public void attachCopy(Time value) {
      long oldValue = this.getTime();
      this.initialise(value);
      long newValue = value.getTime();
      if (oldValue != newValue) {
         this.makeDirty();
      }

   }

   public Object clone() {
      Object obj = super.clone();
      ((SqlTime)obj).unsetOwner();
      return obj;
   }

   public void setTime(long time) {
      super.setTime(time);
      this.makeDirty();
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new Time(this.getTime());
   }
}
