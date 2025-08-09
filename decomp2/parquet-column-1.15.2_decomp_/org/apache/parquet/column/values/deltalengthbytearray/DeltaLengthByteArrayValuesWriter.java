package org.apache.parquet.column.values.deltalengthbytearray;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.bytes.LittleEndianDataOutputStream;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.delta.DeltaBinaryPackingValuesWriterForInteger;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeltaLengthByteArrayValuesWriter extends ValuesWriter {
   private static final Logger LOG = LoggerFactory.getLogger(DeltaLengthByteArrayValuesWriter.class);
   private ValuesWriter lengthWriter;
   private CapacityByteArrayOutputStream arrayOut;
   private LittleEndianDataOutputStream out;

   public DeltaLengthByteArrayValuesWriter(int initialSize, int pageSize, ByteBufferAllocator allocator) {
      this.arrayOut = new CapacityByteArrayOutputStream(initialSize, pageSize, allocator);
      this.out = new LittleEndianDataOutputStream(this.arrayOut);
      this.lengthWriter = new DeltaBinaryPackingValuesWriterForInteger(128, 4, initialSize, pageSize, allocator);
   }

   public void writeBytes(Binary v) {
      try {
         this.lengthWriter.writeInteger(v.length());
         v.writeTo((OutputStream)this.out);
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write bytes", e);
      }
   }

   public long getBufferedSize() {
      return this.lengthWriter.getBufferedSize() + this.arrayOut.size();
   }

   public BytesInput getBytes() {
      try {
         this.out.flush();
      } catch (IOException e) {
         throw new ParquetEncodingException("could not write page", e);
      }

      LOG.debug("writing a buffer of size {}", this.arrayOut.size());
      return BytesInput.concat(new BytesInput[]{this.lengthWriter.getBytes(), BytesInput.from(this.arrayOut)});
   }

   public Encoding getEncoding() {
      return Encoding.DELTA_LENGTH_BYTE_ARRAY;
   }

   public void reset() {
      this.lengthWriter.reset();
      this.arrayOut.reset();
   }

   public void close() {
      this.lengthWriter.close();
      this.arrayOut.close();
   }

   public long getAllocatedSize() {
      return this.lengthWriter.getAllocatedSize() + (long)this.arrayOut.getCapacity();
   }

   public String memUsageString(String prefix) {
      return this.arrayOut.memUsageString(this.lengthWriter.memUsageString(prefix) + " DELTA_LENGTH_BYTE_ARRAY");
   }
}
