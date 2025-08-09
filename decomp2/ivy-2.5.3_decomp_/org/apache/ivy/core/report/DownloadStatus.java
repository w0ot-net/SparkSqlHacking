package org.apache.ivy.core.report;

public final class DownloadStatus {
   private String name;
   public static final DownloadStatus NO = new DownloadStatus("no");
   public static final DownloadStatus SUCCESSFUL = new DownloadStatus("successful");
   public static final DownloadStatus FAILED = new DownloadStatus("failed");

   private DownloadStatus(String name) {
      this.name = name;
   }

   public static final DownloadStatus fromString(String status) {
      if (NO.name.equals(status)) {
         return NO;
      } else if (SUCCESSFUL.name.equals(status)) {
         return SUCCESSFUL;
      } else if (FAILED.name.equals(status)) {
         return FAILED;
      } else {
         throw new IllegalArgumentException("unknown download status '" + status + "'");
      }
   }

   public String toString() {
      return this.name;
   }
}
