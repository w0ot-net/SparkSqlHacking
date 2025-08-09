package org.sparkproject.jetty.client.util;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** @deprecated */
@Deprecated
public class BytesContentProvider extends AbstractTypedContentProvider {
   private final byte[][] bytes;
   private final long length;

   public BytesContentProvider(byte[]... bytes) {
      this("application/octet-stream", bytes);
   }

   public BytesContentProvider(String contentType, byte[]... bytes) {
      super(contentType);
      this.bytes = bytes;
      long length = 0L;

      for(byte[] buffer : bytes) {
         length += (long)buffer.length;
      }

      this.length = length;
   }

   public long getLength() {
      return this.length;
   }

   public boolean isReproducible() {
      return true;
   }

   public Iterator iterator() {
      return new Iterator() {
         private int index;

         public boolean hasNext() {
            return this.index < BytesContentProvider.this.bytes.length;
         }

         public ByteBuffer next() {
            try {
               return ByteBuffer.wrap(BytesContentProvider.this.bytes[this.index++]);
            } catch (ArrayIndexOutOfBoundsException var2) {
               throw new NoSuchElementException();
            }
         }
      };
   }
}
