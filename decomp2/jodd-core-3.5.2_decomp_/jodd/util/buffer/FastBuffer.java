package jodd.util.buffer;

import java.util.Iterator;
import java.util.RandomAccess;

public class FastBuffer implements RandomAccess, Iterable {
   private Object[][] buffers = new Object[16][];
   private int buffersCount;
   private int currentBufferIndex = -1;
   private Object[] currentBuffer;
   private int offset;
   private int size;
   private final int minChunkLen;

   public FastBuffer() {
      this.minChunkLen = 1024;
   }

   public FastBuffer(int size) {
      if (size < 0) {
         throw new IllegalArgumentException("Invalid size: " + size);
      } else {
         this.minChunkLen = size;
      }
   }

   private void needNewBuffer(int newSize) {
      int delta = newSize - this.size;
      int newBufferSize = Math.max(this.minChunkLen, delta);
      ++this.currentBufferIndex;
      this.currentBuffer = new Object[newBufferSize];
      this.offset = 0;
      if (this.currentBufferIndex >= this.buffers.length) {
         int newLen = this.buffers.length << 1;
         E[][] newBuffers = (E[][])((Object[][])(new Object[newLen][]));
         System.arraycopy(this.buffers, 0, newBuffers, 0, this.buffers.length);
         this.buffers = newBuffers;
      }

      this.buffers[this.currentBufferIndex] = this.currentBuffer;
      ++this.buffersCount;
   }

   public FastBuffer append(Object[] array, int off, int len) {
      int end = off + len;
      if (off >= 0 && len >= 0 && end <= array.length) {
         if (len == 0) {
            return this;
         } else {
            int newSize = this.size + len;
            int remaining = len;
            if (this.currentBuffer != null) {
               int part = Math.min(len, this.currentBuffer.length - this.offset);
               System.arraycopy(array, end - len, this.currentBuffer, this.offset, part);
               remaining = len - part;
               this.offset += part;
               this.size += part;
            }

            if (remaining > 0) {
               this.needNewBuffer(newSize);
               int part = Math.min(remaining, this.currentBuffer.length - this.offset);
               System.arraycopy(array, end - remaining, this.currentBuffer, this.offset, part);
               this.offset += part;
               this.size += part;
            }

            return this;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public FastBuffer append(Object[] array) {
      return this.append(array, 0, array.length);
   }

   public FastBuffer append(Object element) {
      if (this.currentBuffer == null || this.offset == this.currentBuffer.length) {
         this.needNewBuffer(this.size + 1);
      }

      this.currentBuffer[this.offset] = element;
      ++this.offset;
      ++this.size;
      return this;
   }

   public FastBuffer append(FastBuffer buff) {
      if (buff.size == 0) {
         return this;
      } else {
         for(int i = 0; i < buff.currentBufferIndex; ++i) {
            this.append(buff.buffers[i]);
         }

         this.append(buff.currentBuffer, 0, buff.offset);
         return this;
      }
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public int index() {
      return this.currentBufferIndex;
   }

   public int offset() {
      return this.offset;
   }

   public Object[] array(int index) {
      return this.buffers[index];
   }

   public void clear() {
      this.size = 0;
      this.offset = 0;
      this.currentBufferIndex = -1;
      this.currentBuffer = null;
      this.buffersCount = 0;
   }

   public Object[] toArray() {
      int pos = 0;
      E[] array = (E[])((Object[])(new Object[this.size]));
      if (this.currentBufferIndex == -1) {
         return array;
      } else {
         for(int i = 0; i < this.currentBufferIndex; ++i) {
            int len = this.buffers[i].length;
            System.arraycopy(this.buffers[i], 0, array, pos, len);
            pos += len;
         }

         System.arraycopy(this.buffers[this.currentBufferIndex], 0, array, pos, this.offset);
         return array;
      }
   }

   public Object[] toArray(int start, int len) {
      int remaining = len;
      int pos = 0;
      E[] array = (E[])((Object[])(new Object[len]));
      if (len == 0) {
         return array;
      } else {
         int i;
         for(i = 0; start >= this.buffers[i].length; ++i) {
            start -= this.buffers[i].length;
         }

         while(i < this.buffersCount) {
            E[] buf = (E[])this.buffers[i];
            int c = Math.min(buf.length - start, remaining);
            System.arraycopy(buf, start, array, pos, c);
            pos += c;
            remaining -= c;
            if (remaining == 0) {
               break;
            }

            start = 0;
            ++i;
         }

         return array;
      }
   }

   public Object get(int index) {
      if (index < this.size && index >= 0) {
         int ndx = 0;

         while(true) {
            E[] b = (E[])this.buffers[ndx];
            if (index < b.length) {
               return b[index];
            }

            ++ndx;
            index -= b.length;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void add(Object element) {
      this.append(element);
   }

   public Iterator iterator() {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   static int access$000(FastBuffer x0) {
      return x0.size;
   }

   // $FF: synthetic method
   static Object[][] access$100(FastBuffer x0) {
      return x0.buffers;
   }
}
