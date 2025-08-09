package org.apache.spark.executor;

public final class ProcfsMetricsGetter$ {
   public static final ProcfsMetricsGetter$ MODULE$ = new ProcfsMetricsGetter$();
   private static final ProcfsMetricsGetter pTreeInfo;

   static {
      pTreeInfo = new ProcfsMetricsGetter(MODULE$.$lessinit$greater$default$1());
   }

   public String $lessinit$greater$default$1() {
      return "/proc/";
   }

   public final ProcfsMetricsGetter pTreeInfo() {
      return pTreeInfo;
   }

   private ProcfsMetricsGetter$() {
   }
}
