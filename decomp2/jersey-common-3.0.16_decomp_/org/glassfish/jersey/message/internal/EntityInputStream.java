package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.ProcessingException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import org.glassfish.jersey.internal.LocalizationMessages;

public class EntityInputStream extends InputStream {
   private InputStream input;
   private boolean closed = false;

   public static EntityInputStream create(InputStream inputStream) {
      return inputStream instanceof EntityInputStream ? (EntityInputStream)inputStream : new EntityInputStream(inputStream);
   }

   public EntityInputStream(InputStream input) {
      this.input = input;
   }

   public int read() throws IOException {
      return this.input.read();
   }

   public int read(byte[] b) throws IOException {
      return this.input.read(b);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.input.read(b, off, len);
   }

   public long skip(long n) throws IOException {
      return this.input.skip(n);
   }

   public int available() throws IOException {
      return this.input.available();
   }

   public void mark(int readLimit) {
      this.input.mark(readLimit);
   }

   public boolean markSupported() {
      return this.input.markSupported();
   }

   public void reset() {
      try {
         this.input.reset();
      } catch (IOException ex) {
         throw new ProcessingException(LocalizationMessages.MESSAGE_CONTENT_BUFFER_RESET_FAILED(), ex);
      }
   }

   public void close() throws ProcessingException {
      InputStream in = this.input;
      if (in != null) {
         if (!this.closed) {
            try {
               in.close();
            } catch (IOException ex) {
               throw new ProcessingException(LocalizationMessages.MESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED(), ex);
            } finally {
               this.closed = true;
            }
         }

      }
   }

   public boolean isEmpty() {
      this.ensureNotClosed();
      InputStream in = this.input;
      if (in == null) {
         return true;
      } else {
         try {
            if (in.markSupported()) {
               in.mark(1);
               int i = in.read();
               in.reset();
               return i == -1;
            } else {
               try {
                  if (in.available() > 0) {
                     return false;
                  }
               } catch (IOException var4) {
               }

               int b = in.read();
               if (b == -1) {
                  return true;
               } else {
                  PushbackInputStream pbis;
                  if (in instanceof PushbackInputStream) {
                     pbis = (PushbackInputStream)in;
                  } else {
                     pbis = new PushbackInputStream(in, 1);
                     this.input = pbis;
                  }

                  pbis.unread(b);
                  return false;
               }
            }
         } catch (IOException ex) {
            throw new ProcessingException(ex);
         }
      }
   }

   public void ensureNotClosed() throws IllegalStateException {
      if (this.closed) {
         throw new IllegalStateException(LocalizationMessages.ERROR_ENTITY_STREAM_CLOSED());
      }
   }

   public boolean isClosed() {
      return this.closed;
   }

   public final InputStream getWrappedStream() {
      return this.input;
   }

   public final void setWrappedStream(InputStream wrapped) {
      this.input = wrapped;
   }
}
