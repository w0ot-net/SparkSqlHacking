package org.apache.hadoop.hive.common.log;

import java.util.Collections;
import java.util.List;

public interface ProgressMonitor {
   ProgressMonitor NULL = new ProgressMonitor() {
      public List headers() {
         return Collections.emptyList();
      }

      public List rows() {
         return Collections.emptyList();
      }

      public String footerSummary() {
         return "";
      }

      public long startTime() {
         return 0L;
      }

      public String executionStatus() {
         return "";
      }

      public double progressedPercentage() {
         return (double)0.0F;
      }
   };

   List headers();

   List rows();

   String footerSummary();

   long startTime();

   String executionStatus();

   double progressedPercentage();
}
