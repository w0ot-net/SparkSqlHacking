package org.apache.thrift.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.thrift.TConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TFileTransport extends TTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TFileTransport.class.getName());
   TailPolicy currentPolicy_;
   protected TSeekableFile inputFile_;
   protected OutputStream outputStream_;
   Event currentEvent_;
   InputStream inputStream_;
   ChunkState cs;
   private boolean readOnly_;

   public TailPolicy getTailPolicy() {
      return this.currentPolicy_;
   }

   public TailPolicy setTailPolicy(TailPolicy policy) {
      TailPolicy old = this.currentPolicy_;
      this.currentPolicy_ = policy;
      return old;
   }

   private InputStream createInputStream() throws TTransportException {
      try {
         InputStream is;
         if (this.inputStream_ != null) {
            ((TruncableBufferedInputStream)this.inputStream_).trunc();
            is = this.inputStream_;
         } else {
            is = new TruncableBufferedInputStream(this.inputFile_.getInputStream());
         }

         return is;
      } catch (IOException iox) {
         throw new TTransportException(iox.getMessage(), iox);
      }
   }

   private int tailRead(InputStream is, byte[] buf, int off, int len, TailPolicy tp) throws TTransportException {
      int orig_len = len;

      try {
         int retries = 0;

         while(len > 0) {
            int cnt = is.read(buf, off, len);
            if (cnt > 0) {
               off += cnt;
               len -= cnt;
               retries = 0;
               this.cs.skip(cnt);
            } else {
               if (cnt != -1) {
                  throw new TTransportException("Unexpected return from InputStream.read = " + cnt);
               }

               ++retries;
               if (tp.retries_ != -1 && tp.retries_ < retries) {
                  return orig_len - len;
               }

               if (tp.timeout_ > 0) {
                  try {
                     Thread.sleep((long)tp.timeout_);
                  } catch (InterruptedException var10) {
                  }
               }
            }
         }
      } catch (IOException iox) {
         throw new TTransportException(iox.getMessage(), iox);
      }

      return orig_len - len;
   }

   private boolean performRecovery() throws TTransportException {
      int numChunks = this.getNumChunks();
      int curChunk = this.cs.getChunkNum();
      if (curChunk >= numChunks - 1) {
         return false;
      } else {
         this.seekToChunk(curChunk + 1);
         return true;
      }
   }

   private boolean readEvent() throws TTransportException {
      byte[] ebytes = new byte[4];

      int esize;
      do {
         int nrequested = this.cs.getRemaining();
         if (nrequested < 4) {
            int nread = this.tailRead(this.inputStream_, ebytes, 0, nrequested, this.currentPolicy_);
            if (nread != nrequested) {
               return false;
            }
         }

         int nread = this.tailRead(this.inputStream_, ebytes, 0, 4, this.currentPolicy_);
         if (nread != 4) {
            return false;
         }

         esize = 0;

         for(int i = 3; i >= 0; --i) {
            int val = 255 & ebytes[i];
            esize |= val << i * 8;
         }

         if (esize > this.cs.getRemaining()) {
            throw new TTransportException("FileTransport error: bad event size");
         }
      } while(esize == 0);

      if (this.currentEvent_.getSize() < esize) {
         this.currentEvent_ = new Event(new byte[esize]);
      }

      byte[] buf = this.currentEvent_.getBuf();
      int var8 = this.tailRead(this.inputStream_, buf, 0, esize, this.currentPolicy_);
      if (var8 != esize) {
         return false;
      } else {
         this.currentEvent_.setAvailable(esize);
         return true;
      }
   }

   public boolean isOpen() {
      return this.inputStream_ != null && (this.readOnly_ || this.outputStream_ != null);
   }

   public void open() throws TTransportException {
      if (this.isOpen()) {
         throw new TTransportException(2);
      } else {
         try {
            this.inputStream_ = this.createInputStream();
            this.cs = new ChunkState();
            this.currentEvent_ = new Event(new byte[256]);
            if (!this.readOnly_) {
               this.outputStream_ = new BufferedOutputStream(this.inputFile_.getOutputStream());
            }

         } catch (IOException iox) {
            throw new TTransportException(1, iox);
         }
      }
   }

   public void close() {
      if (this.inputFile_ != null) {
         try {
            this.inputFile_.close();
         } catch (IOException iox) {
            LOGGER.warn("WARNING: Error closing input file: " + iox.getMessage());
         }

         this.inputFile_ = null;
      }

      if (this.outputStream_ != null) {
         try {
            this.outputStream_.close();
         } catch (IOException iox) {
            LOGGER.warn("WARNING: Error closing output stream: " + iox.getMessage());
         }

         this.outputStream_ = null;
      }

   }

   public TFileTransport(String path, boolean readOnly) throws IOException {
      this.currentPolicy_ = TFileTransport.TailPolicy.NOWAIT;
      this.inputFile_ = null;
      this.outputStream_ = null;
      this.currentEvent_ = null;
      this.inputStream_ = null;
      this.cs = null;
      this.readOnly_ = false;
      this.inputFile_ = new TStandardFile(path);
      this.readOnly_ = readOnly;
   }

   public TFileTransport(TSeekableFile inputFile, boolean readOnly) {
      this.currentPolicy_ = TFileTransport.TailPolicy.NOWAIT;
      this.inputFile_ = null;
      this.outputStream_ = null;
      this.currentEvent_ = null;
      this.inputStream_ = null;
      this.cs = null;
      this.readOnly_ = false;
      this.inputFile_ = inputFile;
      this.readOnly_ = readOnly;
   }

   public int readAll(byte[] buf, int off, int len) throws TTransportException {
      int got = 0;

      int ret;
      for(ret = 0; got < len; got += ret) {
         ret = this.read(buf, off + got, len - got);
         if (ret < 0) {
            throw new TTransportException("Error in reading from file");
         }

         if (ret == 0) {
            throw new TTransportException(4, "End of File reached");
         }
      }

      return got;
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException(1, "Must open before reading");
      } else if (this.currentEvent_.getRemaining() == 0 && !this.readEvent()) {
         return 0;
      } else {
         int nread = this.currentEvent_.emit(buf, off, len);
         return nread;
      }
   }

   public int getNumChunks() throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException(1, "Must open before getNumChunks");
      } else {
         try {
            long len = this.inputFile_.length();
            return len == 0L ? 0 : (int)(len / (long)this.cs.getChunkSize()) + 1;
         } catch (IOException iox) {
            throw new TTransportException(iox.getMessage(), iox);
         }
      }
   }

   public int getCurChunk() throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException(1, "Must open before getCurChunk");
      } else {
         return this.cs.getChunkNum();
      }
   }

   public void seekToChunk(int chunk) throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException(1, "Must open before seeking");
      } else {
         int numChunks = this.getNumChunks();
         if (numChunks != 0) {
            if (chunk < 0) {
               chunk += numChunks;
            }

            if (chunk < 0) {
               chunk = 0;
            }

            long eofOffset = 0L;
            boolean seekToEnd = chunk >= numChunks;
            if (seekToEnd) {
               --chunk;

               try {
                  eofOffset = this.inputFile_.length();
               } catch (IOException iox) {
                  throw new TTransportException(iox.getMessage(), iox);
               }
            }

            if ((long)(chunk * this.cs.getChunkSize()) != this.cs.getOffset()) {
               try {
                  this.inputFile_.seek((long)chunk * (long)this.cs.getChunkSize());
               } catch (IOException iox) {
                  throw new TTransportException("Seek to chunk " + chunk + " " + iox.getMessage(), iox);
               }

               this.cs.seek((long)chunk * (long)this.cs.getChunkSize());
               this.currentEvent_.setAvailable(0);
               this.inputStream_ = this.createInputStream();
            }

            if (seekToEnd) {
               TailPolicy old = this.setTailPolicy(TFileTransport.TailPolicy.WAIT_FOREVER);

               while(this.cs.getOffset() < eofOffset) {
                  this.readEvent();
               }

               this.currentEvent_.setAvailable(0);
               this.setTailPolicy(old);
            }

         }
      }
   }

   public void seekToEnd() throws TTransportException {
      if (!this.isOpen()) {
         throw new TTransportException(1, "Must open before seeking");
      } else {
         this.seekToChunk(this.getNumChunks());
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      throw new TTransportException("Not Supported");
   }

   public void flush() throws TTransportException {
      throw new TTransportException("Not Supported");
   }

   public TConfiguration getConfiguration() {
      return null;
   }

   public void updateKnownMessageSize(long size) throws TTransportException {
   }

   public void checkReadBytesAvailable(long numBytes) throws TTransportException {
   }

   public static void main(String[] args) throws Exception {
      int num_chunks = 10;
      if (args.length < 1 || args[0].equals("--help") || args[0].equals("-h") || args[0].equals("-?")) {
         printUsage();
      }

      if (args.length > 1) {
         try {
            num_chunks = Integer.parseInt(args[1]);
         } catch (Exception var8) {
            LOGGER.error("Cannot parse " + args[1]);
            printUsage();
         }
      }

      TFileTransport t = new TFileTransport(args[0], true);
      t.open();
      LOGGER.info("NumChunks=" + t.getNumChunks());
      Random r = new Random();

      for(int j = 0; j < num_chunks; ++j) {
         byte[] buf = new byte[4096];
         int cnum = r.nextInt(t.getNumChunks() - 1);
         LOGGER.info("Reading chunk " + cnum);
         t.seekToChunk(cnum);

         for(int i = 0; i < 4096; ++i) {
            t.read(buf, 0, 4096);
         }
      }

   }

   private static void printUsage() {
      LOGGER.error("Usage: TFileTransport <filename> [num_chunks]");
      LOGGER.error("       (Opens and reads num_chunks chunks from file randomly)");
      System.exit(1);
   }

   public static class TruncableBufferedInputStream extends BufferedInputStream {
      public void trunc() {
         this.pos = this.count = 0;
      }

      public TruncableBufferedInputStream(InputStream in) {
         super(in);
      }

      public TruncableBufferedInputStream(InputStream in, int size) {
         super(in, size);
      }
   }

   public static class Event {
      private byte[] buf_;
      private int nread_;
      private int navailable_;

      public Event(byte[] buf) {
         this.buf_ = buf;
         this.nread_ = this.navailable_ = 0;
      }

      public byte[] getBuf() {
         return this.buf_;
      }

      public int getSize() {
         return this.buf_.length;
      }

      public void setAvailable(int sz) {
         this.nread_ = 0;
         this.navailable_ = sz;
      }

      public int getRemaining() {
         return this.navailable_ - this.nread_;
      }

      public int emit(byte[] buf, int offset, int ndesired) {
         if (ndesired == 0 || ndesired > this.getRemaining()) {
            ndesired = this.getRemaining();
         }

         if (ndesired <= 0) {
            return ndesired;
         } else {
            System.arraycopy(this.buf_, this.nread_, buf, offset, ndesired);
            this.nread_ += ndesired;
            return ndesired;
         }
      }
   }

   public static class ChunkState {
      public static final int DEFAULT_CHUNK_SIZE = 16777216;
      private int chunk_size_ = 16777216;
      private long offset_ = 0L;

      public ChunkState() {
      }

      public ChunkState(int chunk_size) {
         this.chunk_size_ = chunk_size;
      }

      public void skip(int size) {
         this.offset_ += (long)size;
      }

      public void seek(long offset) {
         this.offset_ = offset;
      }

      public int getChunkSize() {
         return this.chunk_size_;
      }

      public int getChunkNum() {
         return (int)(this.offset_ / (long)this.chunk_size_);
      }

      public int getRemaining() {
         return this.chunk_size_ - (int)(this.offset_ % (long)this.chunk_size_);
      }

      public long getOffset() {
         return this.offset_;
      }
   }

   public static enum TailPolicy {
      NOWAIT(0, 0),
      WAIT_FOREVER(500, -1);

      public final int timeout_;
      public final int retries_;

      private TailPolicy(int timeout, int retries) {
         this.timeout_ = timeout;
         this.retries_ = retries;
      }
   }
}
