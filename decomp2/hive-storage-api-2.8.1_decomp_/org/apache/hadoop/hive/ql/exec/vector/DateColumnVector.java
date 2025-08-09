package org.apache.hadoop.hive.ql.exec.vector;

import org.apache.hadoop.hive.common.type.CalendarUtils;

public class DateColumnVector extends LongColumnVector {
   private boolean usingProlepticCalendar;

   public DateColumnVector() {
      this(1024);
   }

   public void changeCalendar(boolean useProleptic, boolean updateData) {
      if (useProleptic != this.usingProlepticCalendar) {
         this.usingProlepticCalendar = useProleptic;
         if (updateData) {
            try {
               this.updateDataAccordingProlepticSetting();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   private void updateDataAccordingProlepticSetting() throws Exception {
      for(int i = 0; i < this.vector.length; ++i) {
         if (this.vector[i] < CalendarUtils.SWITCHOVER_DAYS) {
            this.vector[i] = this.usingProlepticCalendar ? (long)CalendarUtils.convertDateToProleptic((int)this.vector[i]) : (long)CalendarUtils.convertDateToHybrid((int)this.vector[i]);
         }
      }

   }

   public String formatDate(int i) {
      return CalendarUtils.formatDate(this.vector[i], this.usingProlepticCalendar);
   }

   public DateColumnVector setUsingProlepticCalendar(boolean usingProlepticCalendar) {
      this.usingProlepticCalendar = usingProlepticCalendar;
      return this;
   }

   public boolean isUsingProlepticCalendar() {
      return this.usingProlepticCalendar;
   }

   public DateColumnVector(int len) {
      super(len);
      this.usingProlepticCalendar = false;
   }

   public void shallowCopyTo(ColumnVector otherCv) {
      DateColumnVector other = (DateColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.vector = this.vector;
   }
}
