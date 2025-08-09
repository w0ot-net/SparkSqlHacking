package org.apache.derby.impl.io.vfmem;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

public class VirtualFile implements StorageFile {
   private final String path;
   private final DataStore dStore;

   public VirtualFile(String var1, DataStore var2) {
      this.path = var1;
      this.dStore = var2;
   }

   public String[] list() {
      DataStoreEntry var1 = this.getEntry();
      return var1 != null && var1.isDirectory() ? this.dStore.listChildren(this.path) : null;
   }

   public boolean canWrite() {
      return this.getEntry() != null && !this.getEntry().isReadOnly();
   }

   public boolean exists() {
      return this.getEntry() != null;
   }

   public boolean isDirectory() {
      DataStoreEntry var1 = this.getEntry();
      return var1 != null && var1.isDirectory();
   }

   public boolean delete() {
      return this.dStore.deleteEntry(this.path);
   }

   public boolean deleteAll() {
      DataStoreEntry var1 = this.getEntry();
      if (var1 == null) {
         return false;
      } else {
         return var1.isDirectory() ? this.dStore.deleteAll(this.path) : this.delete();
      }
   }

   public String getPath() {
      return this.path;
   }

   public String getCanonicalPath() {
      return this.getPath();
   }

   public String getName() {
      return PathUtil.getBaseName(this.path);
   }

   public boolean createNewFile() {
      return this.dStore.createEntry(this.path, false) != null;
   }

   public boolean renameTo(StorageFile var1) {
      return this.dStore.move(this, var1);
   }

   public boolean mkdir() {
      DataStoreEntry var1 = this.getEntry();
      if (var1 == null) {
         return this.dStore.createEntry(this.path, true) != null;
      } else {
         return false;
      }
   }

   public boolean mkdirs() {
      DataStoreEntry var1 = this.getEntry();
      if (var1 != null) {
         return false;
      } else {
         return this.dStore.createAllParents(this.path) && this.dStore.createEntry(this.path, true) != null;
      }
   }

   public long length() {
      DataStoreEntry var1 = this.getEntry();
      return var1 != null && !var1.isDirectory() ? var1.length() : 0L;
   }

   public StorageFile getParentDir() {
      String var1 = PathUtil.getParent(this.path);
      return var1 == null ? null : new VirtualFile(var1, this.dStore);
   }

   public boolean setReadOnly() {
      DataStoreEntry var1 = this.getEntry();
      if (var1 == null) {
         return false;
      } else {
         var1.setReadOnly();
         return true;
      }
   }

   public OutputStream getOutputStream() throws FileNotFoundException {
      return this.getOutputStream(false);
   }

   public OutputStream getOutputStream(boolean var1) throws FileNotFoundException {
      DataStoreEntry var2 = this.getEntry();
      if (var2 == null) {
         var2 = this.dStore.createEntry(this.path, false);
         if (var2 == null) {
            throw new FileNotFoundException("Unable to create file: " + this.path);
         }
      }

      return var2.getOutputStream(var1);
   }

   public InputStream getInputStream() throws FileNotFoundException {
      DataStoreEntry var1 = this.getEntry();
      if (var1 == null) {
         throw new FileNotFoundException(this.path);
      } else {
         return var1.getInputStream();
      }
   }

   public int getExclusiveFileLock() throws StandardException {
      return 1;
   }

   public void releaseExclusiveFileLock() {
   }

   public StorageRandomAccessFile getRandomAccessFile(String var1) throws FileNotFoundException {
      if (!var1.equals("r") && !var1.equals("rw") && !var1.equals("rws") && !var1.equals("rwd")) {
         throw new IllegalArgumentException("Invalid mode: " + var1);
      } else {
         DataStoreEntry var2 = this.getEntry();
         if (var2 == null) {
            if (var1.equals("r")) {
               throw new FileNotFoundException("Cannot read from non-existing file: " + this.path + " (mode=" + var1 + ")");
            }

            var2 = this.dStore.createEntry(this.path, false);
            if (var2 == null) {
               throw new FileNotFoundException("Unable to create file: " + this.path + " (mode=" + var1 + ")");
            }
         }

         return new VirtualRandomAccessFile(var2, var1.equals("r"));
      }
   }

   public String toString() {
      String var10000 = this.dStore.getDatabaseName();
      return "(db=" + var10000 + ")" + this.path + "#exists=" + this.exists() + ", isDirectory=" + this.isDirectory() + ", length=" + this.length() + ", canWrite=" + this.canWrite();
   }

   private DataStoreEntry getEntry() {
      return this.dStore.getEntry(this.path);
   }

   public void limitAccessToOwner() {
   }
}
