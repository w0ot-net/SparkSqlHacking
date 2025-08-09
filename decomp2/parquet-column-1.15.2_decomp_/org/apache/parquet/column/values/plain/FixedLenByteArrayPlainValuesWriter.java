package org.apache.parquet.column.values.plain;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.bytes.LittleEndianDataOutputStream;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedLenByteArrayPlainValuesWriter extends ValuesWriter {
   private static final Logger LOG = LoggerFactory.getLogger(PlainValuesWriter.class);
   private CapacityByteArrayOutputStream arrayOut;
   private LittleEndianDataOutputStream out;
   private int length;
   private ByteBufferAllocator allocator;

   public FixedLenByteArrayPlainValuesWriter(int length, int initialSize, int pageSize, ByteBufferAllocator allocator) {
      this.length = length;
      this.allocator = allocator;
      this.arrayOut = new CapacityByteArrayOutputStream(initialSize, pageSize, this.allocator);
      this.out = new LittleEndianDataOutputStream(this.arrayOut);
   }

   public final void writeBytes(Binary v) {
      if (v.length() != this.length) {
         throw new IllegalArgumentException("Fixed Binary size " + v.length() + " does not match field type length " + this.length);
      } else {
         try {
            v.writeTo((OutputStream)this.out);
         } catch (IOException e) {
            throw new ParquetEncodingException("could not write fixed bytes", e);
         }
      }
   }

   public long getBufferedSize() {
      return this.arrayOut.size();
   }

   public BytesInput getBytes() {
      try {
         this.out.flush();
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write page", e);
      }

      LOG.debug("writing a buffer of size {}", this.arrayOut.size());
      return BytesInput.from(this.arrayOut);
   }

   public void reset() {
      this.arrayOut.reset();
   }

   public void close() {
      this.arrayOut.close();
   }

   public long getAllocatedSize() {
      return (long)this.arrayOut.getCapacity();
   }

   public Encoding getEncoding() {
      return Encoding.PLAIN;
   }

   public String memUsageString(String prefix) {
      return this.arrayOut.memUsageString(prefix + " PLAIN");
   }
}
