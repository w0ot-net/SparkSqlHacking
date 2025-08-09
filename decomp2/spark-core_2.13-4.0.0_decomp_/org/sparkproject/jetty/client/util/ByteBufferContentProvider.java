package org.sparkproject.jetty.client.util;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** @deprecated */
@Deprecated
public class ByteBufferContentProvider extends AbstractTypedContentProvider {
   private final ByteBuffer[] buffers;
   private final int length;

   public ByteBufferContentProvider(ByteBuffer... buffers) {
      this("application/octet-stream", buffers);
   }

   public ByteBufferContentProvider(String contentType, ByteBuffer... buffers) {
      super(contentType);
      this.buffers = buffers;
      int length = 0;

      for(ByteBuffer buffer : buffers) {
         length += buffer.remaining();
      }

      this.length = length;
   }

   public long getLength() {
      return (long)this.length;
   }

   public boolean isReproducible() {
      return true;
   }

   public Iterator iterator() {
      return new Iterator() {
         private int index;

         public boolean hasNext() {
            return this.index < ByteBufferContentProvider.this.buffers.length;
         }

         public ByteBuffer next() {
            try {
               ByteBuffer buffer = ByteBufferContentProvider.this.buffers[this.index];
               ByteBufferContentProvider.this.buffers[this.index] = buffer.slice();
               ++this.index;
               return buffer;
            } catch (ArrayIndexOutOfBoundsException var2) {
               throw new NoSuchElementException();
            }
         }
      };
   }
}
