package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class WindowsLineEndingInputStream extends InputStream {
   private boolean atEos;
   private boolean atSlashCr;
   private boolean atSlashLf;
   private final InputStream in;
   private boolean injectSlashLf;
   private final boolean lineFeedAtEos;

   public WindowsLineEndingInputStream(InputStream in, boolean lineFeedAtEos) {
      this.in = in;
      this.lineFeedAtEos = lineFeedAtEos;
   }

   public void close() throws IOException {
      super.close();
      this.in.close();
   }

   private int handleEos() {
      if (!this.lineFeedAtEos) {
         return -1;
      } else if (!this.atSlashLf && !this.atSlashCr) {
         this.atSlashCr = true;
         return 13;
      } else if (!this.atSlashLf) {
         this.atSlashCr = false;
         this.atSlashLf = true;
         return 10;
      } else {
         return -1;
      }
   }

   public synchronized void mark(int readLimit) {
      throw UnsupportedOperationExceptions.mark();
   }

   public int read() throws IOException {
      if (this.atEos) {
         return this.handleEos();
      } else if (this.injectSlashLf) {
         this.injectSlashLf = false;
         return 10;
      } else {
         boolean prevWasSlashR = this.atSlashCr;
         int target = this.in.read();
         this.atEos = target == -1;
         if (!this.atEos) {
            this.atSlashCr = target == 13;
            this.atSlashLf = target == 10;
         }

         if (this.atEos) {
            return this.handleEos();
         } else if (target == 10 && !prevWasSlashR) {
            this.injectSlashLf = true;
            return 13;
         } else {
            return target;
         }
      }
   }
}
