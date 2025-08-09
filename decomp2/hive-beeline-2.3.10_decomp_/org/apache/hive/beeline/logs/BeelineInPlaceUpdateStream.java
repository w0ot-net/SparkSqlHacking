package org.apache.hive.beeline.logs;

import java.io.PrintStream;
import java.util.List;
import org.apache.hadoop.hive.common.log.InPlaceUpdate;
import org.apache.hadoop.hive.common.log.ProgressMonitor;
import org.apache.hive.jdbc.logs.InPlaceUpdateStream;
import org.apache.hive.service.rpc.thrift.TJobExecutionStatus;
import org.apache.hive.service.rpc.thrift.TProgressUpdateResp;

public class BeelineInPlaceUpdateStream implements InPlaceUpdateStream {
   private InPlaceUpdate inPlaceUpdate;
   private InPlaceUpdateStream.EventNotifier notifier;

   public BeelineInPlaceUpdateStream(PrintStream out, InPlaceUpdateStream.EventNotifier notifier) {
      this.inPlaceUpdate = new InPlaceUpdate(out);
      this.notifier = notifier;
   }

   public void update(TProgressUpdateResp response) {
      if (response != null && !response.getStatus().equals(TJobExecutionStatus.NOT_AVAILABLE)) {
         if (this.notifier.isOperationLogUpdatedAtLeastOnce()) {
            this.inPlaceUpdate.render(new ProgressMonitorWrapper(response));
         }
      } else {
         this.notifier.progressBarCompleted();
      }

   }

   public InPlaceUpdateStream.EventNotifier getEventNotifier() {
      return this.notifier;
   }

   static class ProgressMonitorWrapper implements ProgressMonitor {
      private TProgressUpdateResp response;

      ProgressMonitorWrapper(TProgressUpdateResp response) {
         this.response = response;
      }

      public List headers() {
         return this.response.getHeaderNames();
      }

      public List rows() {
         return this.response.getRows();
      }

      public String footerSummary() {
         return this.response.getFooterSummary();
      }

      public long startTime() {
         return this.response.getStartTime();
      }

      public String executionStatus() {
         throw new UnsupportedOperationException("This should never be used for anything. All the required data is available via other methods");
      }

      public double progressedPercentage() {
         return this.response.getProgressedPercentage();
      }
   }
}
