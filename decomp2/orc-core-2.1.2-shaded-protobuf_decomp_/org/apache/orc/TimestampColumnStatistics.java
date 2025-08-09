package org.apache.orc;

import java.sql.Timestamp;

public interface TimestampColumnStatistics extends ColumnStatistics {
   Timestamp getMinimum();

   Timestamp getMaximum();

   Timestamp getMinimumUTC();

   Timestamp getMaximumUTC();
}
