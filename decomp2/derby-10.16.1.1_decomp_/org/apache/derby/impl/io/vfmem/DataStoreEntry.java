package org.apache.derby.impl.io.vfmem;

import java.io.FileNotFoundException;

public class DataStoreEntry {
   private final String path;
   private final boolean isDir;
   private boolean isReadOnly = false;
   private final BlockedByteArray src;
   private volatile boolean released = false;

   public DataStoreEntry(String var1, boolean var2) {
      this.path = var1;
      this.isDir = var2;
      if (var2) {
         this.src = null;
      } else {
         this.src = new BlockedByteArray();
      }

   }

   public boolean isDirectory() {
      this.checkIfReleased();
      return this.isDir;
   }

   BlockedByteArrayInputStream getInputStream() throws FileNotFoundException {
      this.checkIfReleased();
      if (this.isDir) {
         throw new FileNotFoundException("'" + this.path + "' is a directory");
      } else {
         return this.src.getInputStream();
      }
   }

   BlockedByteArrayOutputStream getOutputStream(boolean var1) throws FileNotFoundException {
      this.checkIfReleased();
      if (this.isDir) {
         throw new FileNotFoundException("'" + this.path + "' is a directory");
      } else if (this.isReadOnly) {
         throw new FileNotFoundException("'" + this.path + "' is read-only");
      } else {
         BlockedByteArrayOutputStream var2;
         if (var1) {
            var2 = this.src.getOutputStream(this.src.length());
         } else {
            this.src.setLength(0L);
            var2 = this.src.getOutputStream(0L);
         }

         return var2;
      }
   }

   public long length() {
      this.checkIfReleased();
      return this.src.length();
   }

   public void setReadOnly() {
      this.checkIfReleased();
      this.isReadOnly = true;
   }

   public boolean isReadOnly() {
      this.checkIfReleased();
      return this.isReadOnly;
   }

   void release() {
      this.released = true;
      if (this.src != null) {
         this.src.release();
      }

   }

   public void setLength(long var1) {
      this.checkIfReleased();
      this.src.setLength(var1);
   }

   private void checkIfReleased() {
      if (this.released) {
         throw new IllegalStateException("Entry has been released.");
      }
   }
}
