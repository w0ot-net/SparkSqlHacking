package shaded.parquet.com.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder {
   static final int INITIAL_CHUNK_SIZE = 12;
   static final int SMALL_CHUNK_SIZE = 16384;
   static final int MAX_CHUNK_SIZE = 262144;
   protected Object _freeBuffer;
   protected Node _bufferHead;
   protected Node _bufferTail;
   protected int _bufferedEntryCount;

   protected PrimitiveArrayBuilder() {
   }

   public int bufferedSize() {
      return this._bufferedEntryCount;
   }

   public Object resetAndStart() {
      this._reset();
      return this._freeBuffer == null ? this._constructArray(12) : this._freeBuffer;
   }

   public final Object appendCompletedChunk(Object fullChunk, int fullChunkLength) {
      Node<T> next = new Node(fullChunk, fullChunkLength);
      if (this._bufferHead == null) {
         this._bufferHead = this._bufferTail = next;
      } else {
         this._bufferTail.linkNext(next);
         this._bufferTail = next;
      }

      this._bufferedEntryCount += fullChunkLength;
      int nextLen;
      if (fullChunkLength < 16384) {
         nextLen = fullChunkLength + fullChunkLength;
      } else {
         nextLen = fullChunkLength + (fullChunkLength >> 2);
      }

      return this._constructArray(nextLen);
   }

   public Object completeAndClearBuffer(Object lastChunk, int lastChunkEntries) {
      int totalSize = lastChunkEntries + this._bufferedEntryCount;
      T resultArray = (T)this._constructArray(totalSize);
      int ptr = 0;

      for(Node<T> n = this._bufferHead; n != null; n = n.next()) {
         ptr = n.copyData(resultArray, ptr);
      }

      System.arraycopy(lastChunk, 0, resultArray, ptr, lastChunkEntries);
      ptr += lastChunkEntries;
      if (ptr != totalSize) {
         throw new IllegalStateException("Should have gotten " + totalSize + " entries, got " + ptr);
      } else {
         return resultArray;
      }
   }

   protected abstract Object _constructArray(int var1);

   protected void _reset() {
      if (this._bufferTail != null) {
         this._freeBuffer = this._bufferTail.getData();
      }

      this._bufferHead = this._bufferTail = null;
      this._bufferedEntryCount = 0;
   }

   static final class Node {
      final Object _data;
      final int _dataLength;
      Node _next;

      public Node(Object data, int dataLen) {
         this._data = data;
         this._dataLength = dataLen;
      }

      public Object getData() {
         return this._data;
      }

      public int copyData(Object dst, int ptr) {
         System.arraycopy(this._data, 0, dst, ptr, this._dataLength);
         ptr += this._dataLength;
         return ptr;
      }

      public Node next() {
         return this._next;
      }

      public void linkNext(Node next) {
         if (this._next != null) {
            throw new IllegalStateException();
         } else {
            this._next = next;
         }
      }
   }
}
