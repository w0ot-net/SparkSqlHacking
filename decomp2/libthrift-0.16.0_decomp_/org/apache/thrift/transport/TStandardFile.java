package org.apache.thrift.transport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class TStandardFile implements TSeekableFile {
   protected String path_ = null;
   protected RandomAccessFile inputFile_ = null;

   public TStandardFile(String path) throws IOException {
      this.path_ = path;
      this.inputFile_ = new RandomAccessFile(this.path_, "r");
   }

   public InputStream getInputStream() throws IOException {
      return new FileInputStream(this.inputFile_.getFD());
   }

   public OutputStream getOutputStream() throws IOException {
      return new FileOutputStream(this.path_);
   }

   public void close() throws IOException {
      if (this.inputFile_ != null) {
         this.inputFile_.close();
      }

   }

   public long length() throws IOException {
      return this.inputFile_.length();
   }

   public void seek(long pos) throws IOException {
      this.inputFile_.seek(pos);
   }
}
