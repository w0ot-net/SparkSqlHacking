package org.apache.parquet.column.values.dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.values.RequiresFallback;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.plain.FixedLenByteArrayPlainValuesWriter;
import org.apache.parquet.column.values.plain.PlainValuesWriter;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridEncoder;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.util.AutoCloseables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntLinkedOpenHashMap;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.Double2IntMap;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterator;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntLinkedOpenHashMap;
import shaded.parquet.it.unimi.dsi.fastutil.floats.Float2IntMap;
import shaded.parquet.it.unimi.dsi.fastutil.floats.FloatIterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2IntLinkedOpenHashMap;
import shaded.parquet.it.unimi.dsi.fastutil.ints.Int2IntMap;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import shaded.parquet.it.unimi.dsi.fastutil.longs.Long2IntMap;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import shaded.parquet.it.unimi.dsi.fastutil.objects.Object2IntMap;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;

public abstract class DictionaryValuesWriter extends ValuesWriter implements RequiresFallback {
   private static final Logger LOG = LoggerFactory.getLogger(DictionaryValuesWriter.class);
   private static final int MAX_DICTIONARY_ENTRIES = 2147483646;
   private static final int MIN_INITIAL_SLAB_SIZE = 64;
   private final Encoding encodingForDataPage;
   protected final Encoding encodingForDictionaryPage;
   protected final int maxDictionaryByteSize;
   protected boolean dictionaryTooBig;
   protected long dictionaryByteSize;
   protected int lastUsedDictionaryByteSize;
   protected int lastUsedDictionarySize;
   protected IntList encodedValues = new IntList();
   protected boolean firstPage = true;
   protected ByteBufferAllocator allocator;
   private List toClose = new ArrayList();

   protected DictionaryValuesWriter(int maxDictionaryByteSize, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
      this.allocator = allocator;
      this.maxDictionaryByteSize = maxDictionaryByteSize;
      this.encodingForDataPage = encodingForDataPage;
      this.encodingForDictionaryPage = encodingForDictionaryPage;
   }

   protected DictionaryPage dictPage(ValuesWriter dictPageWriter) {
      DictionaryPage ret = new DictionaryPage(dictPageWriter.getBytes(), this.lastUsedDictionarySize, this.encodingForDictionaryPage);
      this.toClose.add(dictPageWriter);
      return ret;
   }

   public boolean shouldFallBack() {
      return this.dictionaryByteSize > (long)this.maxDictionaryByteSize || this.getDictionarySize() > 2147483646;
   }

   public boolean isCompressionSatisfying(long rawSize, long encodedSize) {
      return encodedSize + this.dictionaryByteSize < rawSize;
   }

   public void fallBackAllValuesTo(ValuesWriter writer) {
      this.fallBackDictionaryEncodedData(writer);
      if (this.lastUsedDictionarySize == 0) {
         this.clearDictionaryContent();
         this.dictionaryByteSize = 0L;
         this.encodedValues = new IntList();
      }

   }

   protected abstract void fallBackDictionaryEncodedData(ValuesWriter var1);

   public long getBufferedSize() {
      return (long)(this.encodedValues.size() * 4);
   }

   public long getAllocatedSize() {
      return (long)(this.encodedValues.size() * 4) + this.dictionaryByteSize;
   }

   public BytesInput getBytes() {
      int maxDicId = this.getDictionarySize() - 1;
      LOG.debug("max dic id {}", maxDicId);
      int bitWidth = BytesUtils.getWidthFromMaxInt(maxDicId);
      int initialSlabSize = CapacityByteArrayOutputStream.initialSlabSizeHeuristic(64, this.maxDictionaryByteSize, 10);
      RunLengthBitPackingHybridEncoder encoder = new RunLengthBitPackingHybridEncoder(bitWidth, initialSlabSize, this.maxDictionaryByteSize, this.allocator);
      this.toClose.add(encoder);
      IntList.IntIterator iterator = this.encodedValues.iterator();

      try {
         while(iterator.hasNext()) {
            encoder.writeInt(iterator.next());
         }

         byte[] bytesHeader = new byte[]{(byte)bitWidth};
         BytesInput rleEncodedBytes = encoder.toBytes();
         LOG.debug("rle encoded bytes {}", rleEncodedBytes.size());
         BytesInput bytes = BytesInput.concat(new BytesInput[]{BytesInput.from(bytesHeader), rleEncodedBytes});
         this.lastUsedDictionarySize = this.getDictionarySize();
         this.lastUsedDictionaryByteSize = Math.toIntExact(this.dictionaryByteSize);
         return bytes;
      } catch (IOException e) {
         throw new ParquetEncodingException("could not encode the values", e);
      }
   }

   public Encoding getEncoding() {
      return this.encodingForDataPage;
   }

   public void reset() {
      this.close();
      this.encodedValues = new IntList();
   }

   public void close() {
      this.encodedValues = null;
      AutoCloseables.uncheckedClose(this.toClose);
      this.toClose.clear();
   }

   public void resetDictionary() {
      this.lastUsedDictionaryByteSize = 0;
      this.lastUsedDictionarySize = 0;
      this.dictionaryTooBig = false;
      this.clearDictionaryContent();
   }

   protected abstract void clearDictionaryContent();

   protected abstract int getDictionarySize();

   public String memUsageString(String prefix) {
      return String.format("%s DictionaryValuesWriter{\n%s\n%s\n%s}\n", prefix, prefix + " dict:" + this.dictionaryByteSize, prefix + " values:" + this.encodedValues.size() * 4, prefix);
   }

   public static class PlainBinaryDictionaryValuesWriter extends DictionaryValuesWriter {
      protected Object2IntMap binaryDictionaryContent = new Object2IntLinkedOpenHashMap();

      public PlainBinaryDictionaryValuesWriter(int maxDictionaryByteSize, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
         super(maxDictionaryByteSize, encodingForDataPage, encodingForDictionaryPage, allocator);
         this.binaryDictionaryContent.defaultReturnValue(-1);
      }

      public void writeBytes(Binary v) {
         int id = this.binaryDictionaryContent.getInt(v);
         if (id == -1) {
            id = this.binaryDictionaryContent.size();
            this.binaryDictionaryContent.put(v.copy(), id);
            this.dictionaryByteSize += 4L + (long)v.length();
         }

         this.encodedValues.add(id);
      }

      public DictionaryPage toDictPageAndClose() {
         if (this.lastUsedDictionarySize <= 0) {
            return null;
         } else {
            PlainValuesWriter dictionaryEncoder = new PlainValuesWriter(this.lastUsedDictionaryByteSize, this.maxDictionaryByteSize, this.allocator);
            Iterator<Binary> binaryIterator = this.binaryDictionaryContent.keySet().iterator();

            for(int i = 0; i < this.lastUsedDictionarySize; ++i) {
               Binary entry = (Binary)binaryIterator.next();
               dictionaryEncoder.writeBytes(entry);
            }

            return this.dictPage(dictionaryEncoder);
         }
      }

      public int getDictionarySize() {
         return this.binaryDictionaryContent.size();
      }

      protected void clearDictionaryContent() {
         this.binaryDictionaryContent.clear();
      }

      public void fallBackDictionaryEncodedData(ValuesWriter writer) {
         Binary[] reverseDictionary = new Binary[this.getDictionarySize()];

         for(Object2IntMap.Entry entry : this.binaryDictionaryContent.object2IntEntrySet()) {
            reverseDictionary[entry.getIntValue()] = (Binary)entry.getKey();
         }

         IntList.IntIterator iterator = this.encodedValues.iterator();

         while(iterator.hasNext()) {
            int id = iterator.next();
            writer.writeBytes(reverseDictionary[id]);
         }

      }
   }

   public static class PlainFixedLenArrayDictionaryValuesWriter extends PlainBinaryDictionaryValuesWriter {
      private final int length;

      public PlainFixedLenArrayDictionaryValuesWriter(int maxDictionaryByteSize, int length, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
         super(maxDictionaryByteSize, encodingForDataPage, encodingForDictionaryPage, allocator);
         this.length = length;
      }

      public void writeBytes(Binary value) {
         int id = this.binaryDictionaryContent.getInt(value);
         if (id == -1) {
            id = this.binaryDictionaryContent.size();
            this.binaryDictionaryContent.put(value.copy(), id);
            this.dictionaryByteSize += (long)this.length;
         }

         this.encodedValues.add(id);
      }

      public DictionaryPage toDictPageAndClose() {
         if (this.lastUsedDictionarySize <= 0) {
            return null;
         } else {
            FixedLenByteArrayPlainValuesWriter dictionaryEncoder = new FixedLenByteArrayPlainValuesWriter(this.length, this.lastUsedDictionaryByteSize, this.maxDictionaryByteSize, this.allocator);
            Iterator<Binary> binaryIterator = this.binaryDictionaryContent.keySet().iterator();

            for(int i = 0; i < this.lastUsedDictionarySize; ++i) {
               Binary entry = (Binary)binaryIterator.next();
               dictionaryEncoder.writeBytes(entry);
            }

            return this.dictPage(dictionaryEncoder);
         }
      }
   }

   public static class PlainLongDictionaryValuesWriter extends DictionaryValuesWriter {
      private Long2IntMap longDictionaryContent = new Long2IntLinkedOpenHashMap();

      public PlainLongDictionaryValuesWriter(int maxDictionaryByteSize, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
         super(maxDictionaryByteSize, encodingForDataPage, encodingForDictionaryPage, allocator);
         this.longDictionaryContent.defaultReturnValue(-1);
      }

      public void writeLong(long v) {
         int id = this.longDictionaryContent.get(v);
         if (id == -1) {
            id = this.longDictionaryContent.size();
            this.longDictionaryContent.put(v, id);
            this.dictionaryByteSize += 8L;
         }

         this.encodedValues.add(id);
      }

      public DictionaryPage toDictPageAndClose() {
         if (this.lastUsedDictionarySize <= 0) {
            return null;
         } else {
            PlainValuesWriter dictionaryEncoder = new PlainValuesWriter(this.lastUsedDictionaryByteSize, this.maxDictionaryByteSize, this.allocator);
            LongIterator longIterator = this.longDictionaryContent.keySet().iterator();

            for(int i = 0; i < this.lastUsedDictionarySize; ++i) {
               dictionaryEncoder.writeLong(longIterator.nextLong());
            }

            return this.dictPage(dictionaryEncoder);
         }
      }

      public int getDictionarySize() {
         return this.longDictionaryContent.size();
      }

      protected void clearDictionaryContent() {
         this.longDictionaryContent.clear();
      }

      public void fallBackDictionaryEncodedData(ValuesWriter writer) {
         long[] reverseDictionary = new long[this.getDictionarySize()];

         Long2IntMap.Entry entry;
         for(ObjectIterator<Long2IntMap.Entry> entryIterator = this.longDictionaryContent.long2IntEntrySet().iterator(); entryIterator.hasNext(); reverseDictionary[entry.getIntValue()] = entry.getLongKey()) {
            entry = (Long2IntMap.Entry)entryIterator.next();
         }

         IntList.IntIterator iterator = this.encodedValues.iterator();

         while(iterator.hasNext()) {
            int id = iterator.next();
            writer.writeLong(reverseDictionary[id]);
         }

      }
   }

   public static class PlainDoubleDictionaryValuesWriter extends DictionaryValuesWriter {
      private Double2IntMap doubleDictionaryContent = new Double2IntLinkedOpenHashMap();

      public PlainDoubleDictionaryValuesWriter(int maxDictionaryByteSize, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
         super(maxDictionaryByteSize, encodingForDataPage, encodingForDictionaryPage, allocator);
         this.doubleDictionaryContent.defaultReturnValue(-1);
      }

      public void writeDouble(double v) {
         int id = this.doubleDictionaryContent.get(v);
         if (id == -1) {
            id = this.doubleDictionaryContent.size();
            this.doubleDictionaryContent.put(v, id);
            this.dictionaryByteSize += 8L;
         }

         this.encodedValues.add(id);
      }

      public DictionaryPage toDictPageAndClose() {
         if (this.lastUsedDictionarySize <= 0) {
            return null;
         } else {
            PlainValuesWriter dictionaryEncoder = new PlainValuesWriter(this.lastUsedDictionaryByteSize, this.maxDictionaryByteSize, this.allocator);
            DoubleIterator doubleIterator = this.doubleDictionaryContent.keySet().iterator();

            for(int i = 0; i < this.lastUsedDictionarySize; ++i) {
               dictionaryEncoder.writeDouble(doubleIterator.nextDouble());
            }

            return this.dictPage(dictionaryEncoder);
         }
      }

      public int getDictionarySize() {
         return this.doubleDictionaryContent.size();
      }

      protected void clearDictionaryContent() {
         this.doubleDictionaryContent.clear();
      }

      public void fallBackDictionaryEncodedData(ValuesWriter writer) {
         double[] reverseDictionary = new double[this.getDictionarySize()];

         Double2IntMap.Entry entry;
         for(ObjectIterator<Double2IntMap.Entry> entryIterator = this.doubleDictionaryContent.double2IntEntrySet().iterator(); entryIterator.hasNext(); reverseDictionary[entry.getIntValue()] = entry.getDoubleKey()) {
            entry = (Double2IntMap.Entry)entryIterator.next();
         }

         IntList.IntIterator iterator = this.encodedValues.iterator();

         while(iterator.hasNext()) {
            int id = iterator.next();
            writer.writeDouble(reverseDictionary[id]);
         }

      }
   }

   public static class PlainIntegerDictionaryValuesWriter extends DictionaryValuesWriter {
      private Int2IntMap intDictionaryContent = new Int2IntLinkedOpenHashMap();

      public PlainIntegerDictionaryValuesWriter(int maxDictionaryByteSize, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
         super(maxDictionaryByteSize, encodingForDataPage, encodingForDictionaryPage, allocator);
         this.intDictionaryContent.defaultReturnValue(-1);
      }

      public void writeInteger(int v) {
         int id = this.intDictionaryContent.get(v);
         if (id == -1) {
            id = this.intDictionaryContent.size();
            this.intDictionaryContent.put(v, id);
            this.dictionaryByteSize += 4L;
         }

         this.encodedValues.add(id);
      }

      public DictionaryPage toDictPageAndClose() {
         if (this.lastUsedDictionarySize <= 0) {
            return null;
         } else {
            PlainValuesWriter dictionaryEncoder = new PlainValuesWriter(this.lastUsedDictionaryByteSize, this.maxDictionaryByteSize, this.allocator);
            IntIterator intIterator = this.intDictionaryContent.keySet().iterator();

            for(int i = 0; i < this.lastUsedDictionarySize; ++i) {
               dictionaryEncoder.writeInteger(intIterator.nextInt());
            }

            return this.dictPage(dictionaryEncoder);
         }
      }

      public int getDictionarySize() {
         return this.intDictionaryContent.size();
      }

      protected void clearDictionaryContent() {
         this.intDictionaryContent.clear();
      }

      public void fallBackDictionaryEncodedData(ValuesWriter writer) {
         int[] reverseDictionary = new int[this.getDictionarySize()];

         Int2IntMap.Entry entry;
         for(ObjectIterator<Int2IntMap.Entry> entryIterator = this.intDictionaryContent.int2IntEntrySet().iterator(); entryIterator.hasNext(); reverseDictionary[entry.getIntValue()] = entry.getIntKey()) {
            entry = (Int2IntMap.Entry)entryIterator.next();
         }

         IntList.IntIterator iterator = this.encodedValues.iterator();

         while(iterator.hasNext()) {
            int id = iterator.next();
            writer.writeInteger(reverseDictionary[id]);
         }

      }
   }

   public static class PlainFloatDictionaryValuesWriter extends DictionaryValuesWriter {
      private Float2IntMap floatDictionaryContent = new Float2IntLinkedOpenHashMap();

      public PlainFloatDictionaryValuesWriter(int maxDictionaryByteSize, Encoding encodingForDataPage, Encoding encodingForDictionaryPage, ByteBufferAllocator allocator) {
         super(maxDictionaryByteSize, encodingForDataPage, encodingForDictionaryPage, allocator);
         this.floatDictionaryContent.defaultReturnValue(-1);
      }

      public void writeFloat(float v) {
         int id = this.floatDictionaryContent.get(v);
         if (id == -1) {
            id = this.floatDictionaryContent.size();
            this.floatDictionaryContent.put(v, id);
            this.dictionaryByteSize += 4L;
         }

         this.encodedValues.add(id);
      }

      public DictionaryPage toDictPageAndClose() {
         if (this.lastUsedDictionarySize <= 0) {
            return null;
         } else {
            PlainValuesWriter dictionaryEncoder = new PlainValuesWriter(this.lastUsedDictionaryByteSize, this.maxDictionaryByteSize, this.allocator);
            FloatIterator floatIterator = this.floatDictionaryContent.keySet().iterator();

            for(int i = 0; i < this.lastUsedDictionarySize; ++i) {
               dictionaryEncoder.writeFloat(floatIterator.nextFloat());
            }

            return this.dictPage(dictionaryEncoder);
         }
      }

      public int getDictionarySize() {
         return this.floatDictionaryContent.size();
      }

      protected void clearDictionaryContent() {
         this.floatDictionaryContent.clear();
      }

      public void fallBackDictionaryEncodedData(ValuesWriter writer) {
         float[] reverseDictionary = new float[this.getDictionarySize()];

         Float2IntMap.Entry entry;
         for(ObjectIterator<Float2IntMap.Entry> entryIterator = this.floatDictionaryContent.float2IntEntrySet().iterator(); entryIterator.hasNext(); reverseDictionary[entry.getIntValue()] = entry.getFloatKey()) {
            entry = (Float2IntMap.Entry)entryIterator.next();
         }

         IntList.IntIterator iterator = this.encodedValues.iterator();

         while(iterator.hasNext()) {
            int id = iterator.next();
            writer.writeFloat(reverseDictionary[id]);
         }

      }
   }
}
