package org.apache.parquet.hadoop;

import org.apache.hadoop.classification.InterfaceStability.Unstable;

@Unstable
public interface ParquetMetricsCallback {
   void setValueInt(String var1, int var2);

   void setValueLong(String var1, long var2);

   void setValueFloat(String var1, float var2);

   void setValueDouble(String var1, double var2);

   void setDuration(String var1, long var2);
}
