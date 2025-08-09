package org.apache.parquet.column.values.fallback;

import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.values.RequiresFallback;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.api.Binary;

public class FallbackValuesWriter extends ValuesWriter {
   public final ValuesWriter initialWriter;
   public final ValuesWriter fallBackWriter;
   private boolean fellBackAlready = false;
   private ValuesWriter currentWriter;
   private boolean initialUsedAndHadDictionary = false;
   private long rawDataByteSize = 0L;
   private boolean firstPage = true;

   public static FallbackValuesWriter of(ValuesWriter initialWriter, ValuesWriter fallBackWriter) {
      return new FallbackValuesWriter(initialWriter, fallBackWriter);
   }

   public FallbackValuesWriter(ValuesWriter initialWriter, ValuesWriter fallBackWriter) {
      this.initialWriter = initialWriter;
      this.fallBackWriter = fallBackWriter;
      this.currentWriter = initialWriter;
   }

   public long getBufferedSize() {
      return this.rawDataByteSize;
   }

   public BytesInput getBytes() {
      if (!this.fellBackAlready && this.firstPage) {
         BytesInput bytes = this.initialWriter.getBytes();
         if (((RequiresFallback)this.initialWriter).isCompressionSatisfying(this.rawDataByteSize, bytes.size())) {
            return bytes;
         }

         this.fallBack();
      }

      return this.currentWriter.getBytes();
   }

   public Encoding getEncoding() {
      Encoding encoding = this.currentWriter.getEncoding();
      if (!this.fellBackAlready && !this.initialUsedAndHadDictionary) {
         this.initialUsedAndHadDictionary = encoding.usesDictionary();
      }

      return encoding;
   }

   public void reset() {
      this.rawDataByteSize = 0L;
      this.firstPage = false;
      this.currentWriter.reset();
   }

   public void close() {
      this.initialWriter.close();
      this.fallBackWriter.close();
   }

   public DictionaryPage toDictPageAndClose() {
      return this.initialUsedAndHadDictionary ? this.initialWriter.toDictPageAndClose() : this.currentWriter.toDictPageAndClose();
   }

   public void resetDictionary() {
      if (this.initialUsedAndHadDictionary) {
         this.initialWriter.resetDictionary();
      } else {
         this.currentWriter.resetDictionary();
      }

      this.currentWriter = this.initialWriter;
      this.fellBackAlready = false;
      this.initialUsedAndHadDictionary = false;
      this.firstPage = true;
   }

   public long getAllocatedSize() {
      return this.currentWriter.getAllocatedSize();
   }

   public String memUsageString(String prefix) {
      return String.format("%s FallbackValuesWriter{\n%s\n%s\n%s}\n", prefix, this.initialWriter.memUsageString(prefix + " initial:"), this.fallBackWriter.memUsageString(prefix + " fallback:"), prefix);
   }

   private void checkFallback() {
      if (!this.fellBackAlready && ((RequiresFallback)this.initialWriter).shouldFallBack()) {
         this.fallBack();
      }

   }

   private void fallBack() {
      this.fellBackAlready = true;
      ((RequiresFallback)this.initialWriter).fallBackAllValuesTo(this.fallBackWriter);
      this.currentWriter = this.fallBackWriter;
   }

   public void writeByte(int value) {
      ++this.rawDataByteSize;
      this.currentWriter.writeByte(value);
      this.checkFallback();
   }

   public void writeBytes(Binary v) {
      this.rawDataByteSize += (long)(v.length() + 4);
      this.currentWriter.writeBytes(v);
      this.checkFallback();
   }

   public void writeInteger(int v) {
      this.rawDataByteSize += 4L;
      this.currentWriter.writeInteger(v);
      this.checkFallback();
   }

   public void writeLong(long v) {
      this.rawDataByteSize += 8L;
      this.currentWriter.writeLong(v);
      this.checkFallback();
   }

   public void writeFloat(float v) {
      this.rawDataByteSize += 4L;
      this.currentWriter.writeFloat(v);
      this.checkFallback();
   }

   public void writeDouble(double v) {
      this.rawDataByteSize += 8L;
      this.currentWriter.writeDouble(v);
      this.checkFallback();
   }
}
