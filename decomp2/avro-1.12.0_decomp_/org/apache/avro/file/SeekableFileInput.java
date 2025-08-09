package org.apache.avro.file;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public class SeekableFileInput extends FileInputStream implements SeekableInput {
   public SeekableFileInput(File file) throws IOException {
      super(file);
   }

   public SeekableFileInput(FileDescriptor fd) throws IOException {
      super(fd);
   }

   public void seek(long p) throws IOException {
      this.getChannel().position(p);
   }

   public long tell() throws IOException {
      return this.getChannel().position();
   }

   public long length() throws IOException {
      return this.getChannel().size();
   }
}
