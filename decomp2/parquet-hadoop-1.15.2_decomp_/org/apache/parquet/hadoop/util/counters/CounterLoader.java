package org.apache.parquet.hadoop.util.counters;

public interface CounterLoader {
   ICounter getCounterByNameAndFlag(String var1, String var2, String var3);
}
