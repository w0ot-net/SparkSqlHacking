package org.apache.derby.impl.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

abstract class InputStreamFile implements StorageFile {
   final String path;
   final int nameStart;
   final BaseStorageFactory storageFactory;

   InputStreamFile(BaseStorageFactory var1, String var2) {
      this.storageFactory = var1;
      if (var2 != null && var2.length() != 0) {
         StringBuilder var3 = new StringBuilder(var1.separatedDataDirectory);
         if (File.separatorChar != '/') {
            var3.append(var2.replace(File.separatorChar, '/'));
         } else {
            var3.append(var2);
         }

         this.path = var3.toString();
         this.nameStart = this.path.lastIndexOf(47) + 1;
      } else {
         this.path = var1.dataDirectory;
         this.nameStart = -1;
      }

   }

   InputStreamFile(BaseStorageFactory var1, String var2, String var3) {
      this.storageFactory = var1;
      StringBuilder var4 = new StringBuilder(var1.separatedDataDirectory);
      if (File.separatorChar != '/') {
         var4.append(var2.replace(File.separatorChar, '/'));
         var4.append('/');
         var4.append(var3.replace(File.separatorChar, '/'));
      } else {
         var4.append(var2);
         var4.append('/');
         var4.append(var3);
      }

      this.path = var4.toString();
      this.nameStart = this.path.lastIndexOf(47) + 1;
   }

   InputStreamFile(InputStreamFile var1, String var2) {
      this.storageFactory = var1.storageFactory;
      StringBuilder var3 = new StringBuilder(var1.path);
      var3.append('/');
      if (File.separatorChar != '/') {
         var3.append(var2.replace(File.separatorChar, '/'));
      } else {
         var3.append(var2);
      }

      this.path = var3.toString();
      this.nameStart = this.path.lastIndexOf(47) + 1;
   }

   InputStreamFile(BaseStorageFactory var1, String var2, int var3) {
      this.storageFactory = var1;
      this.path = var2.substring(0, var3);
      this.nameStart = this.path.lastIndexOf(47) + 1;
   }

   public boolean equals(Object var1) {
      if (var1 != null && this.getClass().equals(var1.getClass())) {
         InputStreamFile var2 = (InputStreamFile)var1;
         return this.path.equals(var2.path);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.path.hashCode();
   }

   public String[] list() {
      return null;
   }

   public boolean canWrite() {
      return false;
   }

   public abstract boolean exists();

   public boolean isDirectory() {
      return false;
   }

   public boolean delete() {
      return false;
   }

   public boolean deleteAll() {
      return false;
   }

   public String getPath() {
      return File.separatorChar != '/' ? this.path.replace('/', File.separatorChar) : this.path;
   }

   public String getCanonicalPath() throws IOException {
      String var10000 = this.storageFactory.getCanonicalName();
      return var10000 + "/" + this.path;
   }

   public String getName() {
      return this.nameStart < 0 ? "" : this.path.substring(this.nameStart);
   }

   public boolean createNewFile() throws IOException {
      throw new IOException("createNewFile called in a read-only file system.");
   }

   public boolean renameTo(StorageFile var1) {
      return false;
   }

   public boolean mkdir() {
      return false;
   }

   public boolean mkdirs() {
      return false;
   }

   public StorageFile getParentDir() {
      return this.path.length() <= this.storageFactory.separatedDataDirectory.length() ? null : this.getParentDir(this.path.lastIndexOf(47));
   }

   abstract StorageFile getParentDir(int var1);

   public boolean setReadOnly() {
      return true;
   }

   public OutputStream getOutputStream() throws FileNotFoundException {
      throw new FileNotFoundException("Attempt to write into a read only file system.");
   }

   public OutputStream getOutputStream(boolean var1) throws FileNotFoundException {
      throw new FileNotFoundException("Attempt to write into a read only file system.");
   }

   public abstract InputStream getInputStream() throws FileNotFoundException;

   public int getExclusiveFileLock() throws StandardException {
      return 0;
   }

   public void releaseExclusiveFileLock() {
   }

   public StorageRandomAccessFile getRandomAccessFile(String var1) throws FileNotFoundException {
      return null;
   }

   public String toString() {
      return this.path;
   }

   public void limitAccessToOwner() {
   }
}
