package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.io.OutputStream;

public class OutputChunked extends Output {
   public OutputChunked() {
      super(2048);
   }

   public OutputChunked(int bufferSize) {
      super(bufferSize);
   }

   public OutputChunked(OutputStream outputStream) {
      super((OutputStream)outputStream, 2048);
   }

   public OutputChunked(OutputStream outputStream, int bufferSize) {
      super(outputStream, bufferSize);
   }

   public void flush() throws KryoException {
      if (this.position() > 0) {
         try {
            this.writeChunkSize();
            super.flush();
         } catch (IOException ex) {
            throw new KryoException(ex);
         }
      } else {
         super.flush();
      }

   }

   private void writeChunkSize() throws IOException {
      int size = this.position();
      if (Log.TRACE) {
         Log.trace("kryo", "Write chunk: " + size);
      }

      OutputStream outputStream = this.getOutputStream();
      if ((size & -128) == 0) {
         outputStream.write(size);
      } else {
         outputStream.write(size & 127 | 128);
         size >>>= 7;
         if ((size & -128) == 0) {
            outputStream.write(size);
         } else {
            outputStream.write(size & 127 | 128);
            size >>>= 7;
            if ((size & -128) == 0) {
               outputStream.write(size);
            } else {
               outputStream.write(size & 127 | 128);
               size >>>= 7;
               if ((size & -128) == 0) {
                  outputStream.write(size);
               } else {
                  outputStream.write(size & 127 | 128);
                  size >>>= 7;
                  outputStream.write(size);
               }
            }
         }
      }
   }

   public void endChunks() {
      this.flush();
      if (Log.TRACE) {
         Log.trace("kryo", "End chunks.");
      }

      try {
         this.getOutputStream().write(0);
      } catch (IOException ex) {
         throw new KryoException(ex);
      }
   }
}
