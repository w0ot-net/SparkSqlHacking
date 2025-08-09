package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.io.InputStream;

public class InputChunked extends Input {
   private int chunkSize = -1;

   public InputChunked() {
      super(2048);
   }

   public InputChunked(int bufferSize) {
      super(bufferSize);
   }

   public InputChunked(InputStream inputStream) {
      super(inputStream, 2048);
   }

   public InputChunked(InputStream inputStream, int bufferSize) {
      super(inputStream, bufferSize);
   }

   public void setInputStream(InputStream inputStream) {
      super.setInputStream(inputStream);
      this.chunkSize = -1;
   }

   public void setBuffer(byte[] bytes, int offset, int count) {
      super.setBuffer(bytes, offset, count);
      this.chunkSize = -1;
   }

   public void rewind() {
      super.rewind();
      this.chunkSize = -1;
   }

   protected int fill(byte[] buffer, int offset, int count) throws KryoException {
      if (this.chunkSize == -1) {
         if (!this.readChunkSize()) {
            return -1;
         }
      } else if (this.chunkSize == 0) {
         return -1;
      }

      int actual = super.fill(buffer, offset, Math.min(this.chunkSize, count));
      this.chunkSize -= actual;
      return this.chunkSize == 0 && !this.readChunkSize() ? -1 : actual;
   }

   private boolean readChunkSize() {
      try {
         InputStream inputStream = this.getInputStream();
         int offset = 0;

         for(int result = 0; offset < 32; offset += 7) {
            int b = inputStream.read();
            if (b == -1) {
               return false;
            }

            result |= (b & 127) << offset;
            if ((b & 128) == 0) {
               this.chunkSize = result;
               if (Log.TRACE && this.chunkSize > 0) {
                  Log.trace("kryo", "Read chunk: " + this.chunkSize);
               }

               return true;
            }
         }
      } catch (IOException ex) {
         throw new KryoException("Unable to read chunk size.", ex);
      }

      throw new KryoException("Unable to read chunk size: malformed integer");
   }

   public void nextChunks() {
      this.position = this.limit;
      if (this.chunkSize == -1) {
         this.readChunkSize();
      }

      while(this.chunkSize > 0) {
         this.skip(this.chunkSize);
      }

      this.chunkSize = -1;
      if (Log.TRACE) {
         Log.trace("kryo", "Next chunks.");
      }

   }
}
