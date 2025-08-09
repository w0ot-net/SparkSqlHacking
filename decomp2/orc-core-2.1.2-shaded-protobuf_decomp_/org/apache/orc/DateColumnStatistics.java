package org.apache.orc;

import java.time.chrono.ChronoLocalDate;
import java.util.Date;

public interface DateColumnStatistics extends ColumnStatistics {
   ChronoLocalDate getMinimumLocalDate();

   long getMinimumDayOfEpoch();

   ChronoLocalDate getMaximumLocalDate();

   long getMaximumDayOfEpoch();

   /** @deprecated */
   Date getMinimum();

   /** @deprecated */
   Date getMaximum();
}
