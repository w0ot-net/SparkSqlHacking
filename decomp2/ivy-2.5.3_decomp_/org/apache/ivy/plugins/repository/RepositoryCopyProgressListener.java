package org.apache.ivy.plugins.repository;

import org.apache.ivy.util.CopyProgressEvent;
import org.apache.ivy.util.CopyProgressListener;

public class RepositoryCopyProgressListener implements CopyProgressListener {
   private final AbstractRepository repository;
   private Long totalLength = null;

   public RepositoryCopyProgressListener(AbstractRepository repository) {
      this.repository = repository;
   }

   public void start(CopyProgressEvent evt) {
      if (this.totalLength == null) {
         this.repository.fireTransferStarted();
      } else {
         this.repository.fireTransferStarted(this.totalLength);
      }

   }

   public void progress(CopyProgressEvent evt) {
      this.repository.fireTransferProgress((long)evt.getReadBytes());
   }

   public void end(CopyProgressEvent evt) {
      this.repository.fireTransferProgress((long)evt.getReadBytes());
      this.repository.fireTransferCompleted();
   }

   public Long getTotalLength() {
      return this.totalLength;
   }

   public void setTotalLength(Long totalLength) {
      this.totalLength = totalLength;
   }
}
