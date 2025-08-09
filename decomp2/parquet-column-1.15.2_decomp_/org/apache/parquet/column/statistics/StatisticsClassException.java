package org.apache.parquet.column.statistics;

import org.apache.parquet.ParquetRuntimeException;

public class StatisticsClassException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public StatisticsClassException(String className1, String className2) {
      this("Statistics classes mismatched: " + className1 + " vs. " + className2);
   }

   private StatisticsClassException(String msg) {
      super(msg);
   }

   static StatisticsClassException create(Statistics stats1, Statistics stats2) {
      return stats1.getClass() != stats2.getClass() ? new StatisticsClassException(stats1.getClass().toString(), stats2.getClass().toString()) : new StatisticsClassException("Statistics comparator mismatched: " + stats1.comparator() + " vs. " + stats2.comparator());
   }
}
