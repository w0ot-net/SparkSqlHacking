package org.datanucleus.store.types.wrappers;

import java.io.ObjectStreamException;
import java.util.Calendar;
import java.util.TimeZone;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.types.SCO;

public class GregorianCalendar extends java.util.GregorianCalendar implements SCO {
   protected transient ObjectProvider ownerOP;
   protected transient AbstractMemberMetaData ownerMmd;

   public GregorianCalendar(ObjectProvider op, AbstractMemberMetaData mmd) {
      this.ownerOP = op;
      this.ownerMmd = mmd;
   }

   public void initialise() {
   }

   public void initialise(java.util.GregorianCalendar newValue, Object oldValue) {
      this.initialise(newValue);
   }

   public void initialise(java.util.GregorianCalendar cal) {
      super.setTimeInMillis(cal.getTime().getTime());
      super.setTimeZone(cal.getTimeZone());
   }

   public java.util.GregorianCalendar getValue() {
      java.util.GregorianCalendar cal = new java.util.GregorianCalendar(this.getTimeZone());
      cal.setTime(this.getTime());
      return cal;
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

   public java.util.GregorianCalendar detachCopy(FetchPlanState state) {
      java.util.GregorianCalendar cal = new java.util.GregorianCalendar(this.getTimeZone());
      cal.setTime(this.getTime());
      return cal;
   }

   public void attachCopy(java.util.GregorianCalendar value) {
      long oldValue = this.getTimeInMillis();
      this.initialise(value);
      long newValue = ((Calendar)value).getTime().getTime();
      if (oldValue != newValue) {
         this.makeDirty();
      }

   }

   public Object clone() {
      Object obj = super.clone();
      ((GregorianCalendar)obj).unsetOwner();
      return obj;
   }

   protected Object writeReplace() throws ObjectStreamException {
      java.util.GregorianCalendar cal = new java.util.GregorianCalendar(this.getTimeZone());
      cal.setTime(this.getTime());
      return cal;
   }

   public void add(int field, int amount) {
      super.add(field, amount);
      this.makeDirty();
   }

   public void roll(int field, boolean up) {
      super.roll(field, up);
      this.makeDirty();
   }

   public void roll(int field, int amount) {
      super.roll(field, amount);
      this.makeDirty();
   }

   public void set(int field, int value) {
      super.set(field, value);
      this.makeDirty();
   }

   public void setGregorianChange(java.util.Date date) {
      super.setGregorianChange(date);
      this.makeDirty();
   }

   public void setFirstDayOfWeek(int value) {
      super.setFirstDayOfWeek(value);
      this.makeDirty();
   }

   public void setLenient(boolean lenient) {
      super.setLenient(lenient);
      this.makeDirty();
   }

   public void setMinimalDaysInFirstWeek(int value) {
      super.setMinimalDaysInFirstWeek(value);
      this.makeDirty();
   }

   public void setTimeInMillis(long millis) {
      super.setTimeInMillis(millis);
      this.makeDirty();
   }

   public void setTimeZone(TimeZone value) {
      super.setTimeZone(value);
      this.makeDirty();
   }
}
