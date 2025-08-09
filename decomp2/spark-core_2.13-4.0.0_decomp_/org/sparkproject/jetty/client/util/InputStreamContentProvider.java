package org.sparkproject.jetty.client.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.ContentProvider;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;

/** @deprecated */
@Deprecated
public class InputStreamContentProvider implements ContentProvider, Callback, Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(InputStreamContentProvider.class);
   private final InputStreamContentProviderIterator iterator;
   private final InputStream stream;
   private final int bufferSize;
   private final boolean autoClose;

   public InputStreamContentProvider(InputStream stream) {
      this(stream, 4096);
   }

   public InputStreamContentProvider(InputStream stream, int bufferSize) {
      this(stream, bufferSize, true);
   }

   public InputStreamContentProvider(InputStream stream, int bufferSize, boolean autoClose) {
      this.iterator = new InputStreamContentProviderIterator();
      this.stream = stream;
      this.bufferSize = bufferSize;
      this.autoClose = autoClose;
   }

   public long getLength() {
      return -1L;
   }

   protected ByteBuffer onRead(byte[] buffer, int offset, int length) {
      return length <= 0 ? BufferUtil.EMPTY_BUFFER : ByteBuffer.wrap(buffer, offset, length);
   }

   protected void onReadFailure(Throwable failure) {
   }

   public Iterator iterator() {
      return this.iterator;
   }

   public void close() {
      if (this.autoClose) {
         try {
            this.stream.close();
         } catch (IOException x) {
            LOG.trace("IGNORED", x);
         }
      }

   }

   public void failed(Throwable failure) {
      this.close();
   }

   private class InputStreamContentProviderIterator implements Iterator, Closeable {
      private Throwable failure;
      private ByteBuffer buffer;
      private Boolean hasNext;

      public boolean hasNext() {
         try {
            if (this.hasNext != null) {
               return this.hasNext;
            } else {
               byte[] bytes = new byte[InputStreamContentProvider.this.bufferSize];
               int read = InputStreamContentProvider.this.stream.read(bytes);
               if (InputStreamContentProvider.LOG.isDebugEnabled()) {
                  InputStreamContentProvider.LOG.debug("Read {} bytes from {}", read, InputStreamContentProvider.this.stream);
               }

               if (read > 0) {
                  this.hasNext = Boolean.TRUE;
                  this.buffer = InputStreamContentProvider.this.onRead(bytes, 0, read);
                  return true;
               } else if (read < 0) {
                  this.hasNext = Boolean.FALSE;
                  this.buffer = null;
                  this.close();
                  return false;
               } else {
                  this.hasNext = Boolean.TRUE;
                  this.buffer = BufferUtil.EMPTY_BUFFER;
                  return true;
               }
            }
         } catch (Throwable var3) {
            if (InputStreamContentProvider.LOG.isDebugEnabled()) {
               InputStreamContentProvider.LOG.debug("Failed to read", var3);
            }

            if (this.failure == null) {
               this.failure = var3;
               InputStreamContentProvider.this.onReadFailure(var3);
               this.hasNext = Boolean.TRUE;
               this.buffer = null;
               this.close();
               return true;
            } else {
               throw new IllegalStateException();
            }
         }
      }

      public ByteBuffer next() {
         if (this.failure != null) {
            this.hasNext = Boolean.FALSE;
            this.buffer = null;
            throw (NoSuchElementException)(new NoSuchElementException()).initCause(this.failure);
         } else if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            ByteBuffer result = this.buffer;
            if (result == null) {
               this.hasNext = Boolean.FALSE;
               this.buffer = null;
               throw new NoSuchElementException();
            } else {
               this.hasNext = null;
               this.buffer = null;
               return result;
            }
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      public void close() {
         InputStreamContentProvider.this.close();
      }
   }
}
