package org.apache.spark.api.r;

public final class RRunnerModes$ {
   public static final RRunnerModes$ MODULE$ = new RRunnerModes$();
   private static final int RDD = 0;
   private static final int DATAFRAME_DAPPLY = 1;
   private static final int DATAFRAME_GAPPLY = 2;

   public int RDD() {
      return RDD;
   }

   public int DATAFRAME_DAPPLY() {
      return DATAFRAME_DAPPLY;
   }

   public int DATAFRAME_GAPPLY() {
      return DATAFRAME_GAPPLY;
   }

   private RRunnerModes$() {
   }
}
