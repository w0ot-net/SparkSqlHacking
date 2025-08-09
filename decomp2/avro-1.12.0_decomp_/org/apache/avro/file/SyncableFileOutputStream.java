package org.apache.avro.file;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SyncableFileOutputStream extends FileOutputStream implements Syncable {
   public SyncableFileOutputStream(String name) throws FileNotFoundException {
      super(name);
   }

   public SyncableFileOutputStream(File file) throws FileNotFoundException {
      super(file);
   }

   public SyncableFileOutputStream(String name, boolean append) throws FileNotFoundException {
      super(name, append);
   }

   public SyncableFileOutputStream(File file, boolean append) throws FileNotFoundException {
      super(file, append);
   }

   public SyncableFileOutputStream(FileDescriptor fdObj) {
      super(fdObj);
   }

   public void sync() throws IOException {
      this.getFD().sync();
   }
}
