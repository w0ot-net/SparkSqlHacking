package org.apache.parquet.column.impl;

import java.io.IOException;
import java.util.Objects;
import org.apache.parquet.CorruptDeltaByteArrays;
import org.apache.parquet.VersionParser;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.column.Dictionary;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.ValuesType;
import org.apache.parquet.column.page.DataPage;
import org.apache.parquet.column.page.DataPageV1;
import org.apache.parquet.column.page.DataPageV2;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.PageReader;
import org.apache.parquet.column.values.RequiresPreviousReader;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridDecoder;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.schema.PrimitiveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ColumnReaderBase implements ColumnReader {
   private static final Logger LOG = LoggerFactory.getLogger(ColumnReaderBase.class);
   private final VersionParser.ParsedVersion writerVersion;
   private final ColumnDescriptor path;
   private final long totalValueCount;
   private final PageReader pageReader;
   private final Dictionary dictionary;
   private IntIterator repetitionLevelColumn;
   private IntIterator definitionLevelColumn;
   protected ValuesReader dataColumn;
   private Encoding currentEncoding;
   private int repetitionLevel;
   private int definitionLevel;
   private int dictionaryId;
   private long endOfPageValueCount;
   private long readValues = 0L;
   private int pageValueCount = 0;
   private final PrimitiveConverter converter;
   private Binding binding;
   private final int maxDefinitionLevel;
   private boolean valueRead;

   private void bindToDictionary(final Dictionary dictionary) {
      this.binding = new Binding() {
         void read() {
            ColumnReaderBase.this.dictionaryId = ColumnReaderBase.this.dataColumn.readValueDictionaryId();
         }

         public void skip() {
            ColumnReaderBase.this.dataColumn.skip();
         }

         void skip(int n) {
            ColumnReaderBase.this.dataColumn.skip(n);
         }

         public int getDictionaryId() {
            return ColumnReaderBase.this.dictionaryId;
         }

         void writeValue() {
            ColumnReaderBase.this.converter.addValueFromDictionary(ColumnReaderBase.this.dictionaryId);
         }

         public int getInteger() {
            return dictionary.decodeToInt(ColumnReaderBase.this.dictionaryId);
         }

         public boolean getBoolean() {
            return dictionary.decodeToBoolean(ColumnReaderBase.this.dictionaryId);
         }

         public long getLong() {
            return dictionary.decodeToLong(ColumnReaderBase.this.dictionaryId);
         }

         public Binary getBinary() {
            return dictionary.decodeToBinary(ColumnReaderBase.this.dictionaryId);
         }

         public float getFloat() {
            return dictionary.decodeToFloat(ColumnReaderBase.this.dictionaryId);
         }

         public double getDouble() {
            return dictionary.decodeToDouble(ColumnReaderBase.this.dictionaryId);
         }
      };
   }

   private void bind(PrimitiveType.PrimitiveTypeName type) {
      this.binding = (Binding)type.convert(new PrimitiveType.PrimitiveTypeNameConverter() {
         public Binding convertFLOAT(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return new Binding() {
               float current;

               void read() {
                  this.current = ColumnReaderBase.this.dataColumn.readFloat();
               }

               public void skip() {
                  this.current = 0.0F;
                  ColumnReaderBase.this.dataColumn.skip();
               }

               void skip(int n) {
                  this.current = 0.0F;
                  ColumnReaderBase.this.dataColumn.skip(n);
               }

               public float getFloat() {
                  return this.current;
               }

               void writeValue() {
                  ColumnReaderBase.this.converter.addFloat(this.current);
               }
            };
         }

         public Binding convertDOUBLE(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return new Binding() {
               double current;

               void read() {
                  this.current = ColumnReaderBase.this.dataColumn.readDouble();
               }

               public void skip() {
                  this.current = (double)0.0F;
                  ColumnReaderBase.this.dataColumn.skip();
               }

               void skip(int n) {
                  this.current = (double)0.0F;
                  ColumnReaderBase.this.dataColumn.skip(n);
               }

               public double getDouble() {
                  return this.current;
               }

               void writeValue() {
                  ColumnReaderBase.this.converter.addDouble(this.current);
               }
            };
         }

         public Binding convertINT32(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return new Binding() {
               int current;

               void read() {
                  this.current = ColumnReaderBase.this.dataColumn.readInteger();
               }

               public void skip() {
                  this.current = 0;
                  ColumnReaderBase.this.dataColumn.skip();
               }

               void skip(int n) {
                  this.current = 0;
                  ColumnReaderBase.this.dataColumn.skip(n);
               }

               public int getInteger() {
                  return this.current;
               }

               void writeValue() {
                  ColumnReaderBase.this.converter.addInt(this.current);
               }
            };
         }

         public Binding convertINT64(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return new Binding() {
               long current;

               void read() {
                  this.current = ColumnReaderBase.this.dataColumn.readLong();
               }

               public void skip() {
                  this.current = 0L;
                  ColumnReaderBase.this.dataColumn.skip();
               }

               void skip(int n) {
                  this.current = 0L;
                  ColumnReaderBase.this.dataColumn.skip(n);
               }

               public long getLong() {
                  return this.current;
               }

               void writeValue() {
                  ColumnReaderBase.this.converter.addLong(this.current);
               }
            };
         }

         public Binding convertINT96(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return this.convertBINARY(primitiveTypeName);
         }

         public Binding convertFIXED_LEN_BYTE_ARRAY(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return this.convertBINARY(primitiveTypeName);
         }

         public Binding convertBOOLEAN(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return new Binding() {
               boolean current;

               void read() {
                  this.current = ColumnReaderBase.this.dataColumn.readBoolean();
               }

               public void skip() {
                  this.current = false;
                  ColumnReaderBase.this.dataColumn.skip();
               }

               void skip(int n) {
                  this.current = false;
                  ColumnReaderBase.this.dataColumn.skip(n);
               }

               public boolean getBoolean() {
                  return this.current;
               }

               void writeValue() {
                  ColumnReaderBase.this.converter.addBoolean(this.current);
               }
            };
         }

         public Binding convertBINARY(PrimitiveType.PrimitiveTypeName primitiveTypeName) throws RuntimeException {
            return new Binding() {
               Binary current;

               void read() {
                  this.current = ColumnReaderBase.this.dataColumn.readBytes();
               }

               public void skip() {
                  this.current = null;
                  ColumnReaderBase.this.dataColumn.skip();
               }

               void skip(int n) {
                  this.current = null;
                  ColumnReaderBase.this.dataColumn.skip(n);
               }

               public Binary getBinary() {
                  return this.current;
               }

               void writeValue() {
                  ColumnReaderBase.this.converter.addBinary(this.current);
               }
            };
         }
      });
   }

   ColumnReaderBase(ColumnDescriptor path, PageReader pageReader, PrimitiveConverter converter, VersionParser.ParsedVersion writerVersion) {
      this.path = (ColumnDescriptor)Objects.requireNonNull(path, "path cannot be null");
      this.pageReader = (PageReader)Objects.requireNonNull(pageReader, "pageReader cannot be null");
      this.converter = (PrimitiveConverter)Objects.requireNonNull(converter, "converter cannot be null");
      this.writerVersion = writerVersion;
      this.maxDefinitionLevel = path.getMaxDefinitionLevel();
      DictionaryPage dictionaryPage = pageReader.readDictionaryPage();
      if (dictionaryPage != null) {
         try {
            this.dictionary = dictionaryPage.getEncoding().initDictionary(path, dictionaryPage);
            if (converter.hasDictionarySupport()) {
               converter.setDictionary(this.dictionary);
            }
         } catch (IOException e) {
            throw new ParquetDecodingException("could not decode the dictionary for " + path, e);
         }
      } else {
         this.dictionary = null;
      }

      this.totalValueCount = pageReader.getTotalValueCount();
      if (this.totalValueCount <= 0L) {
         throw new ParquetDecodingException("totalValueCount '" + this.totalValueCount + "' <= 0");
      }
   }

   boolean isFullyConsumed() {
      return this.readValues >= this.totalValueCount;
   }

   public void writeCurrentValueToConverter() {
      this.readValue();
      this.binding.writeValue();
   }

   public int getCurrentValueDictionaryID() {
      this.readValue();
      return this.binding.getDictionaryId();
   }

   public int getInteger() {
      this.readValue();
      return this.binding.getInteger();
   }

   public boolean getBoolean() {
      this.readValue();
      return this.binding.getBoolean();
   }

   public long getLong() {
      this.readValue();
      return this.binding.getLong();
   }

   public Binary getBinary() {
      this.readValue();
      return this.binding.getBinary();
   }

   public float getFloat() {
      this.readValue();
      return this.binding.getFloat();
   }

   public double getDouble() {
      this.readValue();
      return this.binding.getDouble();
   }

   public int getCurrentRepetitionLevel() {
      return this.repetitionLevel;
   }

   public ColumnDescriptor getDescriptor() {
      return this.path;
   }

   public void readValue() {
      try {
         if (!this.valueRead) {
            this.binding.read();
            this.valueRead = true;
         }

      } catch (RuntimeException var2) {
         if (CorruptDeltaByteArrays.requiresSequentialReads(this.writerVersion, this.currentEncoding) && var2 instanceof ArrayIndexOutOfBoundsException) {
            throw new ParquetDecodingException("Read failure possibly due to PARQUET-246: try setting parquet.split.files to false", new ParquetDecodingException(String.format("Can't read value in column %s at value %d out of %d, %d out of %d in currentPage. repetition level: %d, definition level: %d", this.path, this.readValues, this.totalValueCount, this.readValues - (this.endOfPageValueCount - (long)this.pageValueCount), this.pageValueCount, this.repetitionLevel, this.definitionLevel), var2));
         } else {
            throw new ParquetDecodingException(String.format("Can't read value in column %s at value %d out of %d, %d out of %d in currentPage. repetition level: %d, definition level: %d", this.path, this.readValues, this.totalValueCount, this.readValues - (this.endOfPageValueCount - (long)this.pageValueCount), this.pageValueCount, this.repetitionLevel, this.definitionLevel), var2);
         }
      }
   }

   public void skip() {
      if (!this.valueRead) {
         this.binding.skip();
         this.valueRead = true;
      }

   }

   public int getCurrentDefinitionLevel() {
      return this.definitionLevel;
   }

   private void checkRead() {
      int skipValues = 0;

      while(true) {
         if (this.isPageFullyConsumed()) {
            if (this.isFullyConsumed()) {
               LOG.debug("end reached");
               this.repetitionLevel = 0;
               return;
            }

            this.readPage();
            skipValues = 0;
         }

         int rl = this.repetitionLevelColumn.nextInt();
         int dl = this.definitionLevelColumn.nextInt();
         ++this.readValues;
         if (!this.skipRL(rl)) {
            this.binding.skip(skipValues);
            this.repetitionLevel = rl;
            this.definitionLevel = dl;
            return;
         }

         if (dl == this.maxDefinitionLevel) {
            ++skipValues;
         }
      }
   }

   abstract boolean skipRL(int var1);

   private void readPage() {
      LOG.debug("loading page");
      DataPage page = this.pageReader.readPage();
      page.accept(new DataPage.Visitor() {
         public Void visit(DataPageV1 dataPageV1) {
            ColumnReaderBase.this.readPageV1(dataPageV1);
            return null;
         }

         public Void visit(DataPageV2 dataPageV2) {
            ColumnReaderBase.this.readPageV2(dataPageV2);
            return null;
         }
      });
   }

   private void initDataReader(Encoding dataEncoding, ByteBufferInputStream in, int valueCount) {
      ValuesReader previousReader = this.dataColumn;
      this.currentEncoding = dataEncoding;
      this.pageValueCount = valueCount;
      this.endOfPageValueCount = this.readValues + (long)this.pageValueCount;
      if (dataEncoding.usesDictionary()) {
         if (this.dictionary == null) {
            throw new ParquetDecodingException("could not read page in col " + this.path + " as the dictionary was missing for encoding " + dataEncoding);
         }

         this.dataColumn = dataEncoding.getDictionaryBasedValuesReader(this.path, ValuesType.VALUES, this.dictionary);
      } else {
         this.dataColumn = dataEncoding.getValuesReader(this.path, ValuesType.VALUES);
      }

      if (dataEncoding.usesDictionary() && this.converter.hasDictionarySupport()) {
         this.bindToDictionary(this.dictionary);
      } else {
         this.bind(this.path.getType());
      }

      try {
         this.dataColumn.initFromPage(this.pageValueCount, in);
      } catch (IOException e) {
         throw new ParquetDecodingException("could not read page in col " + this.path, e);
      }

      if (CorruptDeltaByteArrays.requiresSequentialReads(this.writerVersion, dataEncoding) && this.dataColumn instanceof RequiresPreviousReader) {
         ((RequiresPreviousReader)this.dataColumn).setPreviousReader(previousReader);
      }

   }

   private void readPageV1(DataPageV1 page) {
      ValuesReader rlReader = page.getRlEncoding().getValuesReader(this.path, ValuesType.REPETITION_LEVEL);
      ValuesReader dlReader = page.getDlEncoding().getValuesReader(this.path, ValuesType.DEFINITION_LEVEL);
      this.repetitionLevelColumn = new ValuesReaderIntIterator(rlReader);
      this.definitionLevelColumn = new ValuesReaderIntIterator(dlReader);
      int valueCount = page.getValueCount();

      try {
         BytesInput bytes = page.getBytes();
         LOG.debug("page size {} bytes and {} values", bytes.size(), valueCount);
         LOG.debug("reading repetition levels at 0");
         ByteBufferInputStream in = bytes.toInputStream();
         rlReader.initFromPage(valueCount, in);
         LOG.debug("reading definition levels at {}", in.position());
         dlReader.initFromPage(valueCount, in);
         LOG.debug("reading data at {}", in.position());
         this.initDataReader(page.getValueEncoding(), in, valueCount);
      } catch (IOException e) {
         throw new ParquetDecodingException("could not read page " + page + " in col " + this.path, e);
      }

      this.newPageInitialized(page);
   }

   private void readPageV2(DataPageV2 page) {
      this.repetitionLevelColumn = this.newRLEIterator(this.path.getMaxRepetitionLevel(), page.getRepetitionLevels());
      this.definitionLevelColumn = this.newRLEIterator(this.path.getMaxDefinitionLevel(), page.getDefinitionLevels());
      int valueCount = page.getValueCount();
      LOG.debug("page data size {} bytes and {} values", page.getData().size(), valueCount);

      try {
         this.initDataReader(page.getDataEncoding(), page.getData().toInputStream(), valueCount);
      } catch (IOException e) {
         throw new ParquetDecodingException("could not read page " + page + " in col " + this.path, e);
      }

      this.newPageInitialized(page);
   }

   final int getPageValueCount() {
      return this.pageValueCount;
   }

   abstract void newPageInitialized(DataPage var1);

   private IntIterator newRLEIterator(int maxLevel, BytesInput bytes) {
      try {
         return (IntIterator)(maxLevel == 0 ? new NullIntIterator() : new RLEIntIterator(new RunLengthBitPackingHybridDecoder(BytesUtils.getWidthFromMaxInt(maxLevel), bytes.toInputStream())));
      } catch (IOException e) {
         throw new ParquetDecodingException("could not read levels in page for col " + this.path, e);
      }
   }

   boolean isPageFullyConsumed() {
      return this.readValues >= this.endOfPageValueCount;
   }

   public void consume() {
      this.checkRead();
      this.valueRead = false;
   }

   /** @deprecated */
   @Deprecated
   public long getTotalValueCount() {
      return this.totalValueCount;
   }

   private abstract static class Binding {
      private Binding() {
      }

      abstract void read();

      abstract void skip();

      abstract void skip(int var1);

      abstract void writeValue();

      public int getDictionaryId() {
         throw new UnsupportedOperationException();
      }

      public int getInteger() {
         throw new UnsupportedOperationException();
      }

      public boolean getBoolean() {
         throw new UnsupportedOperationException();
      }

      public long getLong() {
         throw new UnsupportedOperationException();
      }

      public Binary getBinary() {
         throw new UnsupportedOperationException();
      }

      public float getFloat() {
         throw new UnsupportedOperationException();
      }

      public double getDouble() {
         throw new UnsupportedOperationException();
      }
   }

   abstract static class IntIterator {
      abstract int nextInt();
   }

   static class ValuesReaderIntIterator extends IntIterator {
      ValuesReader delegate;

      public ValuesReaderIntIterator(ValuesReader delegate) {
         this.delegate = delegate;
      }

      int nextInt() {
         return this.delegate.readInteger();
      }
   }

   static class RLEIntIterator extends IntIterator {
      RunLengthBitPackingHybridDecoder delegate;

      public RLEIntIterator(RunLengthBitPackingHybridDecoder delegate) {
         this.delegate = delegate;
      }

      int nextInt() {
         try {
            return this.delegate.readInt();
         } catch (IOException e) {
            throw new ParquetDecodingException(e);
         }
      }
   }

   private static final class NullIntIterator extends IntIterator {
      private NullIntIterator() {
      }

      int nextInt() {
         return 0;
      }
   }
}
