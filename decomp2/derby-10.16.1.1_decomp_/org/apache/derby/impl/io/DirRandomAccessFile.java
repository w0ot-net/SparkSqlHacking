package org.apache.derby.impl.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.derby.io.StorageRandomAccessFile;

class DirRandomAccessFile extends RandomAccessFile implements StorageRandomAccessFile {
   private final File _name;
   private final String _mode;

   DirRandomAccessFile(File var1, String var2) throws FileNotFoundException {
      super(var1, var2);
      this._name = var1;
      this._mode = var2;
   }

   public DirRandomAccessFile clone() {
      try {
         return new DirRandomAccessFile(this._name, this._mode);
      } catch (IOException var2) {
         throw new RuntimeException(var2.getMessage(), var2);
      }
   }

   public void sync() throws IOException {
      this.getFD().sync();
   }
}
