package org.apache.parquet.hadoop;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ColumnReadStore;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.column.impl.ColumnReadStoreImpl;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.example.DummyRecordConverter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.metadata.FileMetaData;
import org.apache.parquet.internal.column.columnindex.BoundaryOrder;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveStringifier;
import org.apache.parquet.schema.PrimitiveType;

public class ColumnIndexValidator {
   static StatValue.Builder getBuilder(PrimitiveType type) {
      switch (type.getPrimitiveTypeName()) {
         case BINARY:
         case FIXED_LEN_BYTE_ARRAY:
         case INT96:
            return new BinaryStatValueBuilder(type);
         case BOOLEAN:
            return new BooleanStatValueBuilder(type);
         case DOUBLE:
            return new DoubleStatValueBuilder(type);
         case FLOAT:
            return new FloatStatValueBuilder(type);
         case INT32:
            return new IntStatValueBuilder(type);
         case INT64:
            return new LongStatValueBuilder(type);
         default:
            throw new IllegalArgumentException("Unsupported type: " + type);
      }
   }

   public static List checkContractViolations(InputFile file) throws IOException {
      List<ContractViolation> violations = new ArrayList();
      ParquetFileReader reader = ParquetFileReader.open(file);
      Throwable var3 = null;

      try {
         FileMetaData meta = reader.getFooter().getFileMetaData();
         MessageType schema = meta.getSchema();
         List<ColumnDescriptor> columns = schema.getColumns();
         List<BlockMetaData> blocks = reader.getFooter().getBlocks();
         int rowGroupNumber = 0;

         for(PageReadStore rowGroup = reader.readNextRowGroup(); rowGroup != null; ++rowGroupNumber) {
            ColumnReadStore columnReadStore = new ColumnReadStoreImpl(rowGroup, (new DummyRecordConverter(schema)).getRootConverter(), schema, (String)null);
            List<ColumnChunkMetaData> columnChunks = ((BlockMetaData)blocks.get(rowGroupNumber)).getColumns();

            assert columnChunks.size() == columns.size();

            for(int columnNumber = 0; columnNumber < columns.size(); ++columnNumber) {
               ColumnDescriptor column = (ColumnDescriptor)columns.get(columnNumber);
               ColumnChunkMetaData columnChunk = (ColumnChunkMetaData)columnChunks.get(columnNumber);
               ColumnIndex columnIndex = reader.readColumnIndex(columnChunk);
               if (columnIndex != null) {
                  ColumnPath columnPath = columnChunk.getPath();
                  OffsetIndex offsetIndex = reader.readOffsetIndex(columnChunk);
                  List<ByteBuffer> minValues = columnIndex.getMinValues();
                  List<ByteBuffer> maxValues = columnIndex.getMaxValues();
                  BoundaryOrder boundaryOrder = columnIndex.getBoundaryOrder();
                  List<Long> nullCounts = columnIndex.getNullCounts();
                  List<Boolean> nullPages = columnIndex.getNullPages();
                  long rowNumber = 0L;
                  ColumnReader columnReader = columnReadStore.getColumnReader(column);
                  ByteBuffer prevMinValue = null;
                  ByteBuffer prevMaxValue = null;

                  for(int pageNumber = 0; pageNumber < offsetIndex.getPageCount(); ++pageNumber) {
                     boolean isNullPage = (Boolean)nullPages.get(pageNumber);
                     ByteBuffer minValue = (ByteBuffer)minValues.get(pageNumber);
                     ByteBuffer maxValue = (ByteBuffer)maxValues.get(pageNumber);
                     PageValidator pageValidator = new PageValidator(column.getPrimitiveType(), rowGroupNumber, columnNumber, columnPath, pageNumber, violations, columnReader, minValue, maxValue, prevMinValue, prevMaxValue, boundaryOrder, (Long)nullCounts.get(pageNumber), isNullPage);
                     if (!isNullPage) {
                        prevMinValue = minValue;
                        prevMaxValue = maxValue;
                     }

                     for(long lastRowNumberInPage = offsetIndex.getLastRowIndex(pageNumber, rowGroup.getRowCount()); rowNumber <= lastRowNumberInPage; ++rowNumber) {
                        pageValidator.validateValuesBelongingToRow();
                     }

                     pageValidator.finishPage();
                  }
               }
            }

            rowGroup.close();
            rowGroup = reader.readNextRowGroup();
         }
      } catch (Throwable var42) {
         var3 = var42;
         throw var42;
      } finally {
         if (reader != null) {
            if (var3 != null) {
               try {
                  reader.close();
               } catch (Throwable var41) {
                  var3.addSuppressed(var41);
               }
            } else {
               reader.close();
            }
         }

      }

      return violations;
   }

   public static enum Contract {
      MIN_LTEQ_VALUE("The min value stored in the index for the page must be less than or equal to all values in the page.\nActual value in the page: %s\nMin value in the index: %s\n"),
      MAX_GTEQ_VALUE("The max value stored in the index for the page must be greater than or equal to all values in the page.\nActual value in the page: %s\nMax value in the index: %s\n"),
      NULL_COUNT_CORRECT("The null count stored in the index for the page must be equal to the number of nulls in the page.\nActual null count: %s\nNull count in the index: %s\n"),
      NULL_PAGE_HAS_NO_VALUES("Only pages consisting entirely of NULL-s can be marked as a null page in the index.\nActual non-null value in the page: %s"),
      NULL_PAGE_HAS_NO_MIN("A null page shall not have a min value in the index\nMin value in the index: %s\n"),
      NULL_PAGE_HAS_NO_MAX("A null page shall not have a max value in the index\nMax value in the index: %s\n"),
      MIN_ASCENDING("According to the ASCENDING boundary order, the min value for a page must be greater than or equal to the min value of the previous page.\nMin value for the page: %s\nMin value for the previous page: %s\n"),
      MAX_ASCENDING("According to the ASCENDING boundary order, the max value for a page must be greater than or equal to the max value of the previous page.\nMax value for the page: %s\nMax value for the previous page: %s\n"),
      MIN_DESCENDING("According to the DESCENDING boundary order, the min value for a page must be less than or equal to the min value of the previous page.\nMin value for the page: %s\nMin value for the previous page: %s\n"),
      MAX_DESCENDING("According to the DESCENDING boundary order, the max value for a page must be less than or equal to the max value of the previous page.\nMax value for the page: %s\nMax value for the previous page: %s\n");

      public final String description;

      private Contract(String description) {
         this.description = description;
      }
   }

   public static class ContractViolation {
      private final Contract violatedContract;
      private final String referenceValue;
      private final String offendingValue;
      private final int rowGroupNumber;
      private final int columnNumber;
      private final ColumnPath columnPath;
      private final int pageNumber;

      public ContractViolation(Contract violatedContract, String referenceValue, String offendingValue, int rowGroupNumber, int columnNumber, ColumnPath columnPath, int pageNumber) {
         this.violatedContract = violatedContract;
         this.referenceValue = referenceValue;
         this.offendingValue = offendingValue;
         this.rowGroupNumber = rowGroupNumber;
         this.columnNumber = columnNumber;
         this.columnPath = columnPath;
         this.pageNumber = pageNumber;
      }

      public String toString() {
         return String.format("Contract violation\nLocation: row group %d, column %d (\"%s\"), page %d\nViolated contract: " + this.violatedContract.description, this.rowGroupNumber, this.columnNumber, this.columnPath.toDotString(), this.pageNumber, this.referenceValue, this.offendingValue);
      }
   }

   private static class BinaryStatValueBuilder extends StatValue.Builder {
      private BinaryStatValueBuilder(PrimitiveType type) {
         super(type);
      }

      StatValue build(ByteBuffer value) {
         return new Value(Binary.fromConstantByteBuffer(value));
      }

      String stringifyValue(ColumnReader reader) {
         return this.stringifier.stringify(reader.getBinary());
      }

      private class Value implements StatValue {
         final Binary value;

         private Value(Binary value) {
            this.value = value;
         }

         public int compareTo(StatValue o) {
            return BinaryStatValueBuilder.this.comparator.compare(this.value, ((Value)o).value);
         }

         public int compareToValue(ColumnReader reader) {
            return BinaryStatValueBuilder.this.comparator.compare(this.value, reader.getBinary());
         }

         public String toString() {
            return BinaryStatValueBuilder.this.stringifier.stringify(this.value);
         }
      }
   }

   private static class BooleanStatValueBuilder extends StatValue.Builder {
      private BooleanStatValueBuilder(PrimitiveType type) {
         super(type);
      }

      StatValue build(ByteBuffer value) {
         return new Value(value.get(0) != 0);
      }

      String stringifyValue(ColumnReader reader) {
         return this.stringifier.stringify(reader.getBoolean());
      }

      private class Value implements StatValue {
         final boolean value;

         private Value(boolean value) {
            this.value = value;
         }

         public int compareTo(StatValue o) {
            return BooleanStatValueBuilder.this.comparator.compare(this.value, ((Value)o).value);
         }

         public int compareToValue(ColumnReader reader) {
            return BooleanStatValueBuilder.this.comparator.compare(this.value, reader.getBoolean());
         }

         public String toString() {
            return BooleanStatValueBuilder.this.stringifier.stringify(this.value);
         }
      }
   }

   private static class DoubleStatValueBuilder extends StatValue.Builder {
      private DoubleStatValueBuilder(PrimitiveType type) {
         super(type);
      }

      StatValue build(ByteBuffer value) {
         return new Value(value.getDouble(0));
      }

      String stringifyValue(ColumnReader reader) {
         return this.stringifier.stringify(reader.getDouble());
      }

      private class Value implements StatValue {
         final double value;

         private Value(double value) {
            this.value = value;
         }

         public int compareTo(StatValue o) {
            return DoubleStatValueBuilder.this.comparator.compare(this.value, ((Value)o).value);
         }

         public int compareToValue(ColumnReader reader) {
            return DoubleStatValueBuilder.this.comparator.compare(this.value, reader.getDouble());
         }

         public String toString() {
            return DoubleStatValueBuilder.this.stringifier.stringify(this.value);
         }
      }
   }

   private static class FloatStatValueBuilder extends StatValue.Builder {
      private FloatStatValueBuilder(PrimitiveType type) {
         super(type);
      }

      StatValue build(ByteBuffer value) {
         return new Value(value.getFloat(0));
      }

      String stringifyValue(ColumnReader reader) {
         return this.stringifier.stringify(reader.getFloat());
      }

      private class Value implements StatValue {
         final float value;

         private Value(float value) {
            this.value = value;
         }

         public int compareTo(StatValue o) {
            return FloatStatValueBuilder.this.comparator.compare(this.value, ((Value)o).value);
         }

         public int compareToValue(ColumnReader reader) {
            return FloatStatValueBuilder.this.comparator.compare(this.value, reader.getFloat());
         }

         public String toString() {
            return FloatStatValueBuilder.this.stringifier.stringify(this.value);
         }
      }
   }

   private static class IntStatValueBuilder extends StatValue.Builder {
      private IntStatValueBuilder(PrimitiveType type) {
         super(type);
      }

      StatValue build(ByteBuffer value) {
         return new Value(value.getInt(0));
      }

      String stringifyValue(ColumnReader reader) {
         return this.stringifier.stringify(reader.getInteger());
      }

      private class Value implements StatValue {
         final int value;

         private Value(int value) {
            this.value = value;
         }

         public int compareTo(StatValue o) {
            return IntStatValueBuilder.this.comparator.compare(this.value, ((Value)o).value);
         }

         public int compareToValue(ColumnReader reader) {
            return IntStatValueBuilder.this.comparator.compare(this.value, reader.getInteger());
         }

         public String toString() {
            return IntStatValueBuilder.this.stringifier.stringify(this.value);
         }
      }
   }

   private static class LongStatValueBuilder extends StatValue.Builder {
      private LongStatValueBuilder(PrimitiveType type) {
         super(type);
      }

      StatValue build(ByteBuffer value) {
         return new Value(value.getLong(0));
      }

      String stringifyValue(ColumnReader reader) {
         return this.stringifier.stringify(reader.getLong());
      }

      private class Value implements StatValue {
         final long value;

         private Value(long value) {
            this.value = value;
         }

         public int compareTo(StatValue o) {
            return LongStatValueBuilder.this.comparator.compare(this.value, ((Value)o).value);
         }

         public int compareToValue(ColumnReader reader) {
            return LongStatValueBuilder.this.comparator.compare(this.value, reader.getLong());
         }

         public String toString() {
            return LongStatValueBuilder.this.stringifier.stringify(this.value);
         }
      }
   }

   private static class PageValidator {
      private final int rowGroupNumber;
      private final int columnNumber;
      private final ColumnPath columnPath;
      private final int pageNumber;
      private final int maxDefinitionLevel;
      private final long nullCountInIndex;
      private long nullCountActual;
      private final boolean isNullPage;
      private final ColumnReader columnReader;
      private final List violations;
      private final Set pageViolations = EnumSet.noneOf(Contract.class);
      private final StatValue minValue;
      private final StatValue maxValue;
      private final StatValue.Builder statValueBuilder;

      PageValidator(PrimitiveType type, int rowGroupNumber, int columnNumber, ColumnPath columnPath, int pageNumber, List violations, ColumnReader columnReader, ByteBuffer minValue, ByteBuffer maxValue, ByteBuffer prevMinValue, ByteBuffer prevMaxValue, BoundaryOrder boundaryOrder, long nullCount, boolean isNullPage) {
         this.columnReader = columnReader;
         this.rowGroupNumber = rowGroupNumber;
         this.columnNumber = columnNumber;
         this.columnPath = columnPath;
         this.pageNumber = pageNumber;
         this.nullCountInIndex = nullCount;
         this.nullCountActual = 0L;
         this.isNullPage = isNullPage;
         this.maxDefinitionLevel = columnReader.getDescriptor().getMaxDefinitionLevel();
         this.violations = violations;
         this.statValueBuilder = ColumnIndexValidator.getBuilder(type);
         this.minValue = isNullPage ? null : this.statValueBuilder.build(minValue);
         this.maxValue = isNullPage ? null : this.statValueBuilder.build(maxValue);
         if (isNullPage) {
            this.validateContract(!minValue.hasRemaining(), ColumnIndexValidator.Contract.NULL_PAGE_HAS_NO_MIN, () -> this.statValueBuilder.build(minValue).toString());
            this.validateContract(!maxValue.hasRemaining(), ColumnIndexValidator.Contract.NULL_PAGE_HAS_NO_MAX, () -> this.statValueBuilder.build(maxValue).toString());
         } else if (prevMinValue != null) {
            this.validateBoundaryOrder(this.statValueBuilder.build(prevMinValue), this.statValueBuilder.build(prevMaxValue), boundaryOrder);
         }

      }

      void validateValuesBelongingToRow() {
         do {
            if (this.columnReader.getCurrentDefinitionLevel() == this.maxDefinitionLevel) {
               this.validateValue();
            } else {
               ++this.nullCountActual;
            }

            this.columnReader.consume();
         } while(this.columnReader.getCurrentRepetitionLevel() != 0);

      }

      void finishPage() {
         this.validateContract(this.nullCountInIndex == this.nullCountActual, ColumnIndexValidator.Contract.NULL_COUNT_CORRECT, () -> Long.toString(this.nullCountActual), () -> Long.toString(this.nullCountInIndex));
      }

      void validateContract(boolean contractCondition, Contract type, Supplier value1) {
         this.validateContract(contractCondition, type, value1, () -> "N/A");
      }

      void validateContract(boolean contractCondition, Contract type, Supplier value1, Supplier value2) {
         if (!contractCondition && !this.pageViolations.contains(type)) {
            this.violations.add(new ContractViolation(type, (String)value1.get(), (String)value2.get(), this.rowGroupNumber, this.columnNumber, this.columnPath, this.pageNumber));
            this.pageViolations.add(type);
         }

      }

      private void validateValue() {
         this.validateContract(!this.isNullPage, ColumnIndexValidator.Contract.NULL_PAGE_HAS_NO_VALUES, () -> this.statValueBuilder.stringifyValue(this.columnReader));
         boolean var10001 = this.minValue.compareToValue(this.columnReader) <= 0;
         Contract var10002 = ColumnIndexValidator.Contract.MIN_LTEQ_VALUE;
         Supplier var10003 = () -> this.statValueBuilder.stringifyValue(this.columnReader);
         StatValue var10004 = this.minValue;
         this.validateContract(var10001, var10002, var10003, var10004::toString);
         var10001 = this.maxValue.compareToValue(this.columnReader) >= 0;
         var10002 = ColumnIndexValidator.Contract.MAX_GTEQ_VALUE;
         var10003 = () -> this.statValueBuilder.stringifyValue(this.columnReader);
         var10004 = this.maxValue;
         this.validateContract(var10001, var10002, var10003, var10004::toString);
      }

      private void validateBoundaryOrder(StatValue prevMinValue, StatValue prevMaxValue, BoundaryOrder boundaryOrder) {
         switch (boundaryOrder) {
            case ASCENDING:
               boolean var4 = this.minValue.compareTo(prevMinValue) >= 0;
               StatValue var5 = this.minValue;
               this.validateContract(var4, ColumnIndexValidator.Contract.MIN_ASCENDING, var5::toString, prevMinValue::toString);
               this.validateContract(this.maxValue.compareTo(prevMaxValue) >= 0, ColumnIndexValidator.Contract.MAX_ASCENDING, this.maxValue::toString, prevMaxValue::toString);
               break;
            case DESCENDING:
               boolean var10001 = this.minValue.compareTo(prevMinValue) <= 0;
               StatValue var10003 = this.minValue;
               this.validateContract(var10001, ColumnIndexValidator.Contract.MIN_DESCENDING, var10003::toString, prevMinValue::toString);
               this.validateContract(this.maxValue.compareTo(prevMaxValue) <= 0, ColumnIndexValidator.Contract.MAX_DESCENDING, this.maxValue::toString, prevMaxValue::toString);
            case UNORDERED:
         }

      }
   }

   interface StatValue extends Comparable {
      int compareToValue(ColumnReader var1);

      public abstract static class Builder {
         final PrimitiveComparator comparator;
         final PrimitiveStringifier stringifier;

         Builder(PrimitiveType type) {
            this.comparator = type.comparator();
            this.stringifier = type.stringifier();
         }

         abstract StatValue build(ByteBuffer var1);

         abstract String stringifyValue(ColumnReader var1);
      }
   }
}
