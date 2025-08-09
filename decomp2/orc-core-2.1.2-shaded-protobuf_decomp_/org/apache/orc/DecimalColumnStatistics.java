package org.apache.orc;

import org.apache.hadoop.hive.common.type.HiveDecimal;

public interface DecimalColumnStatistics extends ColumnStatistics {
   HiveDecimal getMinimum();

   HiveDecimal getMaximum();

   HiveDecimal getSum();
}
