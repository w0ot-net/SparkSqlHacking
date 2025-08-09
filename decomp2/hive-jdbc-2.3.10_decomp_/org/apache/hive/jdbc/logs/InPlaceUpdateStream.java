package org.apache.hive.jdbc.logs;

import org.apache.hive.service.rpc.thrift.TProgressUpdateResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface InPlaceUpdateStream {
   InPlaceUpdateStream NO_OP = new InPlaceUpdateStream() {
      private final EventNotifier eventNotifier = new EventNotifier();

      public void update(TProgressUpdateResp response) {
      }

      public EventNotifier getEventNotifier() {
         return this.eventNotifier;
      }
   };

   void update(TProgressUpdateResp var1);

   EventNotifier getEventNotifier();

   public static class EventNotifier {
      public static final Logger LOG = LoggerFactory.getLogger(EventNotifier.class.getName());
      boolean isComplete = false;
      boolean isOperationLogUpdatedOnceAtLeast = false;

      public synchronized void progressBarCompleted() {
         LOG.debug("progress bar is complete");
         this.isComplete = true;
      }

      private synchronized boolean isProgressBarComplete() {
         return this.isComplete;
      }

      public synchronized void operationLogShowedToUser() {
         LOG.debug("operations log is shown to the user");
         this.isOperationLogUpdatedOnceAtLeast = true;
      }

      public synchronized boolean isOperationLogUpdatedAtLeastOnce() {
         return this.isOperationLogUpdatedOnceAtLeast;
      }

      public boolean canOutputOperationLogs() {
         return !this.isOperationLogUpdatedAtLeastOnce() || this.isProgressBarComplete();
      }
   }
}
