package org.apache.derby.impl.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

class LOBFile {
   private final StorageFile storageFile;
   private final StorageRandomAccessFile randomAccessFile;

   LOBFile(StorageFile var1) throws FileNotFoundException {
      this.storageFile = var1;
      this.randomAccessFile = var1.getRandomAccessFile("rw");
   }

   StorageFile getStorageFile() {
      return this.storageFile;
   }

   long length() throws IOException {
      return this.randomAccessFile.length();
   }

   void seek(long var1) throws IOException {
      this.randomAccessFile.seek(var1);
   }

   void write(int var1) throws IOException, StandardException {
      this.randomAccessFile.write(var1);
   }

   long getFilePointer() throws IOException {
      return this.randomAccessFile.getFilePointer();
   }

   void write(byte[] var1, int var2, int var3) throws IOException, StandardException {
      this.randomAccessFile.write(var1, var2, var3);
   }

   int readByte() throws IOException, StandardException {
      return this.randomAccessFile.readByte();
   }

   int read(byte[] var1, int var2, int var3) throws IOException, StandardException {
      return this.randomAccessFile.read(var1, var2, var3);
   }

   void close() throws IOException {
      this.randomAccessFile.close();
   }

   void setLength(long var1) throws IOException, StandardException {
      this.randomAccessFile.setLength(var1);
   }

   void write(byte[] var1) throws IOException, StandardException {
      this.randomAccessFile.write(var1);
   }
}
