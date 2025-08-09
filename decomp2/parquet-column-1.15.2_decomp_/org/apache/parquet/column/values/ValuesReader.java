package org.apache.parquet.column.values;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;

public abstract class ValuesReader {
   private int actualOffset = -1;
   private int nextOffset;

   /** @deprecated */
   @Deprecated
   public void initFromPage(int valueCount, ByteBuffer page, int offset) throws IOException {
      if (offset < 0) {
         throw new IllegalArgumentException("Illegal offset: " + offset);
      } else {
         this.actualOffset = offset;
         ByteBuffer pageWithOffset = page.duplicate();
         pageWithOffset.position(offset);
         this.initFromPage(valueCount, ByteBufferInputStream.wrap(new ByteBuffer[]{pageWithOffset}));
         this.actualOffset = -1;
      }
   }

   /** @deprecated */
   @Deprecated
   public void initFromPage(int valueCount, byte[] page, int offset) throws IOException {
      this.initFromPage(valueCount, ByteBuffer.wrap(page), offset);
   }

   public void initFromPage(int valueCount, ByteBufferInputStream in) throws IOException {
      if (this.actualOffset != -1) {
         throw new UnsupportedOperationException("Either initFromPage(int, ByteBuffer, int) or initFromPage(int, ByteBufferInputStream) must be implemented in " + this.getClass().getName());
      } else {
         this.initFromPage(valueCount, (ByteBuffer)in.slice(valueCount), 0);
      }
   }

   /** @deprecated */
   @Deprecated
   public int getNextOffset() {
      if (this.nextOffset == -1) {
         throw new ParquetDecodingException("Unsupported: cannot get offset of the next section.");
      } else {
         return this.nextOffset;
      }
   }

   protected void updateNextOffset(int bytesRead) {
      this.nextOffset = this.actualOffset == -1 ? -1 : this.actualOffset + bytesRead;
   }

   public int readValueDictionaryId() {
      throw new UnsupportedOperationException();
   }

   public boolean readBoolean() {
      throw new UnsupportedOperationException();
   }

   public Binary readBytes() {
      throw new UnsupportedOperationException();
   }

   public float readFloat() {
      throw new UnsupportedOperationException();
   }

   public double readDouble() {
      throw new UnsupportedOperationException();
   }

   public int readInteger() {
      throw new UnsupportedOperationException();
   }

   public long readLong() {
      throw new UnsupportedOperationException();
   }

   public abstract void skip();

   public void skip(int n) {
      for(int i = 0; i < n; ++i) {
         this.skip();
      }

   }
}
