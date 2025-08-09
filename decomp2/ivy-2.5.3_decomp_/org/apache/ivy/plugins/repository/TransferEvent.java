package org.apache.ivy.plugins.repository;

import java.io.File;
import org.apache.ivy.core.event.IvyEvent;

public class TransferEvent extends IvyEvent {
   public static final int TRANSFER_INITIATED = 0;
   public static final int TRANSFER_STARTED = 1;
   public static final int TRANSFER_COMPLETED = 2;
   public static final int TRANSFER_PROGRESS = 3;
   public static final int TRANSFER_ERROR = 4;
   private static final int LAST_EVENT_TYPE = 4;
   public static final int REQUEST_GET = 5;
   public static final int REQUEST_PUT = 6;
   public static final String TRANSFER_INITIATED_NAME = "transfer-initiated";
   public static final String TRANSFER_STARTED_NAME = "transfer-started";
   public static final String TRANSFER_PROGRESS_NAME = "transfer-progress";
   public static final String TRANSFER_COMPLETED_NAME = "transfer-completed";
   public static final String TRANSFER_ERROR_NAME = "transfer-error";
   private Resource resource;
   private int eventType;
   private int requestType;
   private Exception exception;
   private File localFile;
   private Repository repository;
   private long length;
   private long totalLength;
   private boolean isTotalLengthSet;
   private long[] timeTracking;

   public TransferEvent(Repository repository, Resource resource, int eventType, int requestType) {
      super(getName(eventType));
      this.isTotalLengthSet = false;
      this.timeTracking = new long[5];
      this.repository = repository;
      this.setResource(resource);
      this.setEventType(eventType);
      this.setRequestType(requestType);
   }

   public TransferEvent(Repository repository, Resource resource, Exception exception, int requestType) {
      this(repository, resource, 4, requestType);
      this.exception = exception;
   }

   public TransferEvent(Repository repository, Resource resource, long length, int requestType) {
      this(repository, resource, 3, requestType);
      this.length = length;
      this.totalLength = length;
   }

   private static String getName(int eventType) {
      switch (eventType) {
         case 0:
            return "transfer-initiated";
         case 1:
            return "transfer-started";
         case 2:
            return "transfer-completed";
         case 3:
            return "transfer-progress";
         case 4:
            return "transfer-error";
         default:
            return null;
      }
   }

   public Resource getResource() {
      return this.resource;
   }

   public Exception getException() {
      return this.exception;
   }

   public int getRequestType() {
      return this.requestType;
   }

   protected void setRequestType(int requestType) {
      switch (requestType) {
         case 5:
         case 6:
            this.requestType = requestType;
            this.addAttribute("request-type", requestType == 5 ? "get" : "put");
            return;
         default:
            throw new IllegalArgumentException("Illegal request type: " + requestType);
      }
   }

   public int getEventType() {
      return this.eventType;
   }

   protected void setEventType(int eventType) {
      this.checkEventType(eventType);
      if (this.eventType != eventType) {
         this.eventType = eventType;
         this.timeTracking[eventType] = System.currentTimeMillis();
         if (eventType > 0) {
            this.addAttribute("total-duration", String.valueOf(this.getElapsedTime(0, eventType)));
            if (eventType > 1) {
               this.addAttribute("duration", String.valueOf(this.getElapsedTime(1, eventType)));
            }
         }
      }

   }

   protected void setResource(Resource resource) {
      this.resource = resource;
      this.addAttribute("resource", this.resource.getName());
   }

   public File getLocalFile() {
      return this.localFile;
   }

   protected void setLocalFile(File localFile) {
      this.localFile = localFile;
   }

   public long getLength() {
      return this.length;
   }

   protected void setLength(long length) {
      this.length = length;
   }

   public long getTotalLength() {
      return this.totalLength;
   }

   protected void setTotalLength(long totalLength) {
      this.totalLength = totalLength;
   }

   public void setException(Exception exception) {
      this.exception = exception;
   }

   public boolean isTotalLengthSet() {
      return this.isTotalLengthSet;
   }

   public void setTotalLengthSet(boolean isTotalLengthSet) {
      this.isTotalLengthSet = isTotalLengthSet;
   }

   public Repository getRepository() {
      return this.repository;
   }

   public long getElapsedTime(int fromEventType, int toEventType) {
      this.checkEventType(fromEventType);
      this.checkEventType(toEventType);
      long start = this.timeTracking[fromEventType];
      long end = this.timeTracking[toEventType];
      if (start != 0L && end != 0L) {
         return end < start ? 0L : end - start;
      } else {
         return -1L;
      }
   }

   private void checkEventType(int eventType) {
      if (eventType < 0 || eventType > 4) {
         throw new IllegalArgumentException("invalid event type " + eventType);
      }
   }
}
