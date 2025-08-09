package org.sparkproject.jpmml.model.temporals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataType;

public class DaysSinceDate extends ComplexPeriod {
   private long days;
   private static final Map dataTypes = new LinkedHashMap();

   private DaysSinceDate() {
      this.days = 0L;
   }

   public DaysSinceDate(Date epoch, LocalDate date) {
      this(epoch, ChronoUnit.DAYS.between(epoch.getDate(), date));
   }

   public DaysSinceDate(Date epoch, long days) {
      super(epoch);
      this.days = 0L;
      this.setDays(days);
   }

   public DataType getDataType() {
      return getDataType(this.getEpoch());
   }

   public DaysSinceDate forEpoch(Date newEpoch) {
      Date epoch = this.getEpoch();
      long days = this.getDays();
      if (Objects.equals(epoch, newEpoch)) {
         return this;
      } else {
         long newDays = ChronoUnit.DAYS.between(newEpoch.getDate(), epoch.getDate()) + days;
         return new DaysSinceDate(newEpoch, newDays);
      }
   }

   public long longValue() {
      return this.getDays();
   }

   public int compareTo(DaysSinceDate that) {
      if (!Objects.equals(this.getEpoch(), that.getEpoch())) {
         throw new ClassCastException();
      } else {
         return Long.compare(this.getDays(), that.getDays());
      }
   }

   public int hashCode() {
      return 31 * this.getEpoch().hashCode() + Objects.hashCode(this.getDays());
   }

   public boolean equals(Object object) {
      if (!(object instanceof DaysSinceDate)) {
         return false;
      } else {
         DaysSinceDate that = (DaysSinceDate)object;
         return Objects.equals(this.getEpoch(), that.getEpoch()) && this.getDays() == that.getDays();
      }
   }

   public long getDays() {
      return this.days;
   }

   private void setDays(long days) {
      this.days = days;
   }

   public static DataType getDataType(Date epoch) {
      return (DataType)dataTypes.get(epoch);
   }

   static {
      dataTypes.put(Epochs.YEAR_1960, DataType.DATE_DAYS_SINCE_1960);
      dataTypes.put(Epochs.YEAR_1970, DataType.DATE_DAYS_SINCE_1970);
      dataTypes.put(Epochs.YEAR_1980, DataType.DATE_DAYS_SINCE_1980);
      dataTypes.put(Epochs.YEAR_1990, DataType.DATE_DAYS_SINCE_1990);
      dataTypes.put(Epochs.YEAR_2000, DataType.DATE_DAYS_SINCE_2000);
      dataTypes.put(Epochs.YEAR_2010, DataType.DATE_DAYS_SINCE_2010);
      dataTypes.put(Epochs.YEAR_2020, DataType.DATE_DAYS_SINCE_2020);
   }
}
