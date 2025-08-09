package org.sparkproject.jpmml.model.temporals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataType;

public class SecondsSinceDate extends ComplexPeriod {
   private long seconds;
   private static final Map dataTypes = new LinkedHashMap();

   private SecondsSinceDate() {
      this.seconds = 0L;
   }

   public SecondsSinceDate(Date epoch, LocalDateTime dateTime) {
      this(epoch, ChronoUnit.SECONDS.between(epoch.getDate().atStartOfDay(), dateTime));
   }

   public SecondsSinceDate(Date epoch, long seconds) {
      super(epoch);
      this.seconds = 0L;
      this.setSeconds(seconds);
   }

   public DataType getDataType() {
      return getDataType(this.getEpoch());
   }

   public SecondsSinceDate forEpoch(Date newEpoch) {
      Date epoch = this.getEpoch();
      long seconds = this.getSeconds();
      if (Objects.equals(epoch, newEpoch)) {
         return this;
      } else {
         long newSeconds = ChronoUnit.SECONDS.between(newEpoch.getDate().atStartOfDay(), epoch.getDate().atStartOfDay()) + seconds;
         return new SecondsSinceDate(newEpoch, newSeconds);
      }
   }

   public long longValue() {
      return this.getSeconds();
   }

   public int compareTo(SecondsSinceDate that) {
      if (!Objects.equals(this.getEpoch(), that.getEpoch())) {
         throw new ClassCastException();
      } else {
         return Long.compare(this.getSeconds(), that.getSeconds());
      }
   }

   public int hashCode() {
      return 31 * this.getEpoch().hashCode() + Objects.hashCode(this.getSeconds());
   }

   public boolean equals(Object object) {
      if (!(object instanceof SecondsSinceDate)) {
         return false;
      } else {
         SecondsSinceDate that = (SecondsSinceDate)object;
         return Objects.equals(this.getEpoch(), that.getEpoch()) && this.getSeconds() == that.getSeconds();
      }
   }

   public long getSeconds() {
      return this.seconds;
   }

   private void setSeconds(long seconds) {
      this.seconds = seconds;
   }

   public static DataType getDataType(Date epoch) {
      return (DataType)dataTypes.get(epoch);
   }

   static {
      dataTypes.put(Epochs.YEAR_1960, DataType.DATE_TIME_SECONDS_SINCE_1960);
      dataTypes.put(Epochs.YEAR_1970, DataType.DATE_TIME_SECONDS_SINCE_1970);
      dataTypes.put(Epochs.YEAR_1980, DataType.DATE_TIME_SECONDS_SINCE_1980);
      dataTypes.put(Epochs.YEAR_1990, DataType.DATE_TIME_SECONDS_SINCE_1990);
      dataTypes.put(Epochs.YEAR_2000, DataType.DATE_TIME_SECONDS_SINCE_2000);
      dataTypes.put(Epochs.YEAR_2010, DataType.DATE_TIME_SECONDS_SINCE_2010);
      dataTypes.put(Epochs.YEAR_2020, DataType.DATE_TIME_SECONDS_SINCE_2020);
   }
}
