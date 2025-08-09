package org.apache.thrift.transport;

import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.thrift.TConfiguration;

public final class TSimpleFileTransport extends TEndpointTransport {
   private RandomAccessFile file;
   private boolean readable;
   private boolean writable;
   private String path_;

   public TSimpleFileTransport(String path, boolean read, boolean write, boolean openFile) throws TTransportException {
      this(new TConfiguration(), path, read, write, openFile);
   }

   public TSimpleFileTransport(TConfiguration config, String path, boolean read, boolean write, boolean openFile) throws TTransportException {
      super(config);
      this.file = null;
      if (path.length() <= 0) {
         throw new TTransportException("No path specified");
      } else if (!read && !write) {
         throw new TTransportException("Neither READ nor WRITE specified");
      } else {
         this.readable = read;
         this.writable = write;
         this.path_ = path;
         if (openFile) {
            this.open();
         }

      }
   }

   public TSimpleFileTransport(String path, boolean read, boolean write) throws TTransportException {
      this(path, read, write, true);
   }

   public TSimpleFileTransport(String path) throws TTransportException {
      this(path, true, false, true);
   }

   public boolean isOpen() {
      return this.file != null;
   }

   public void open() throws TTransportException {
      if (this.file == null) {
         try {
            String access = "r";
            if (this.writable) {
               access = access + "w";
            }

            this.file = new RandomAccessFile(this.path_, access);
         } catch (IOException ioe) {
            this.file = null;
            throw new TTransportException(ioe.getMessage());
         }
      }

   }

   public void close() {
      if (this.file != null) {
         try {
            this.file.close();
         } catch (Exception var2) {
         }

         this.file = null;
      }

   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      if (!this.readable) {
         throw new TTransportException("Read operation on write only file");
      } else {
         this.checkReadBytesAvailable((long)len);
         int iBytesRead = 0;

         try {
            iBytesRead = this.file.read(buf, off, len);
            return iBytesRead;
         } catch (IOException ioe) {
            this.file = null;
            throw new TTransportException(ioe.getMessage());
         }
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      try {
         this.file.write(buf, off, len);
      } catch (IOException ioe) {
         this.file = null;
         throw new TTransportException(ioe.getMessage());
      }
   }

   public void seek(long offset) throws TTransportException {
      try {
         this.file.seek(offset);
      } catch (IOException ex) {
         throw new TTransportException(ex.getMessage());
      }
   }

   public long length() throws TTransportException {
      try {
         return this.file.length();
      } catch (IOException ex) {
         throw new TTransportException(ex.getMessage());
      }
   }

   public long getFilePointer() throws TTransportException {
      try {
         return this.file.getFilePointer();
      } catch (IOException ex) {
         throw new TTransportException(ex.getMessage());
      }
   }
}
