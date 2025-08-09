package org.apache.parquet.column.values.plain;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
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

public class PlainValuesWriter extends ValuesWriter {
   private static final Logger LOG = LoggerFactory.getLogger(PlainValuesWriter.class);
   /** @deprecated */
   @Deprecated
   public static final Charset CHARSET = Charset.forName("UTF-8");
   private CapacityByteArrayOutputStream arrayOut;
   private LittleEndianDataOutputStream out;

   public PlainValuesWriter(int initialSize, int pageSize, ByteBufferAllocator allocator) {
      this.arrayOut = new CapacityByteArrayOutputStream(initialSize, pageSize, allocator);
      this.out = new LittleEndianDataOutputStream(this.arrayOut);
   }

   public final void writeBytes(Binary v) {
      try {
         this.out.writeInt(v.length());
         v.writeTo((OutputStream)this.out);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write bytes", e);
      }
   }

   public final void writeInteger(int v) {
      try {
         this.out.writeInt(v);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write int", e);
      }
   }

   public final void writeLong(long v) {
      try {
         this.out.writeLong(v);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write long", e);
      }
   }

   public final void writeFloat(float v) {
      try {
         this.out.writeFloat(v);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write float", e);
      }
   }

   public final void writeDouble(double v) {
      try {
         this.out.writeDouble(v);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write double", e);
      }
   }

   public void writeByte(int value) {
      try {
         this.out.write(value);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write byte", e);
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

      if (LOG.isDebugEnabled()) {
         LOG.debug("writing a buffer of size {}", this.arrayOut.size());
      }

      return BytesInput.from(this.arrayOut);
   }

   public void reset() {
      this.arrayOut.reset();
   }

   public void close() {
      this.arrayOut.close();
      this.out.close();
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
