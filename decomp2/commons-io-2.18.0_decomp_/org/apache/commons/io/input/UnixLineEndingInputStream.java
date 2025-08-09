package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class UnixLineEndingInputStream extends InputStream {
   private boolean atEos;
   private boolean atSlashCr;
   private boolean atSlashLf;
   private final InputStream in;
   private final boolean lineFeedAtEndOfFile;

   public UnixLineEndingInputStream(InputStream inputStream, boolean ensureLineFeedAtEndOfFile) {
      this.in = inputStream;
      this.lineFeedAtEndOfFile = ensureLineFeedAtEndOfFile;
   }

   public void close() throws IOException {
      super.close();
      this.in.close();
   }

   private int handleEos(boolean previousWasSlashCr) {
      if (!previousWasSlashCr && this.lineFeedAtEndOfFile) {
         if (!this.atSlashLf) {
            this.atSlashLf = true;
            return 10;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public synchronized void mark(int readLimit) {
      throw UnsupportedOperationExceptions.mark();
   }

   public int read() throws IOException {
      boolean previousWasSlashR = this.atSlashCr;
      if (this.atEos) {
         return this.handleEos(previousWasSlashR);
      } else {
         int target = this.readWithUpdate();
         if (this.atEos) {
            return this.handleEos(previousWasSlashR);
         } else if (this.atSlashCr) {
            return 10;
         } else {
            return previousWasSlashR && this.atSlashLf ? this.read() : target;
         }
      }
   }

   private int readWithUpdate() throws IOException {
      int target = this.in.read();
      this.atEos = target == -1;
      if (this.atEos) {
         return target;
      } else {
         this.atSlashCr = target == 13;
         this.atSlashLf = target == 10;
         return target;
      }
   }
}
