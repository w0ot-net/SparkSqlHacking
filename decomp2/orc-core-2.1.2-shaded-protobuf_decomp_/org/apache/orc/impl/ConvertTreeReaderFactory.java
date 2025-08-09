package org.apache.orc.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.EnumMap;
import java.util.TimeZone;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DateColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.Decimal64ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.expressions.StringExpr;
import org.apache.hadoop.hive.ql.io.filter.FilterContext;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.impl.reader.StripePlanner;
import org.apache.orc.impl.reader.tree.TypeReader;
import org.threeten.extra.chrono.HybridChronology;

public class ConvertTreeReaderFactory extends TreeReaderFactory {
   static final DateTimeFormatter DATE_FORMAT;
   static final DateTimeFormatter TIMESTAMP_FORMAT;
   static final DateTimeFormatter INSTANT_TIMESTAMP_FORMAT;
   static final long MIN_EPOCH_SECONDS;
   static final long MAX_EPOCH_SECONDS;

   private static TypeReader createFromInteger(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
      switch (fileType.getCategory()) {
         case BOOLEAN -> {
            return new TreeReaderFactory.BooleanTreeReader(columnId, context);
         }
         case BYTE -> {
            return new TreeReaderFactory.ByteTreeReader(columnId, context);
         }
         case SHORT -> {
            return new TreeReaderFactory.ShortTreeReader(columnId, context);
         }
         case INT -> {
            return new TreeReaderFactory.IntTreeReader(columnId, context);
         }
         case LONG -> {
            return new TreeReaderFactory.LongTreeReader(columnId, context);
         }
         default -> throw new RuntimeException("Unexpected type kind " + String.valueOf(fileType));
      }
   }

   static Instant timestampToInstant(TimestampColumnVector vector, int element) {
      return Instant.ofEpochSecond(Math.floorDiv(vector.time[element], 1000), (long)vector.nanos[element]);
   }

   static Instant decimalToInstant(DecimalColumnVector vector, int element, HiveDecimalWritable value) {
      HiveDecimalWritable writable = vector.vector[element];
      long seconds = writable.longValue();
      if (seconds >= MIN_EPOCH_SECONDS && seconds <= MAX_EPOCH_SECONDS) {
         value.set(writable);
         value.mutateFractionPortion();
         value.mutateScaleByPowerOfTen(9);
         int nanos = (int)value.longValue();
         return Instant.ofEpochSecond(seconds, (long)nanos);
      } else {
         return null;
      }
   }

   private static TypeReader createBooleanConvertTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromBooleanTreeReader(columnId, fileType, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            if (fileType.getCategory() == readerType.getCategory()) {
               throw new IllegalArgumentException("No conversion of type " + String.valueOf(readerType.getCategory()) + " to self needed");
            }

            return new AnyIntegerFromAnyIntegerTreeReader(columnId, fileType, readerType, context);
         case FLOAT:
         case DOUBLE:
            return new DoubleFromAnyIntegerTreeReader(columnId, fileType, context);
         case DECIMAL:
            return new DecimalFromAnyIntegerTreeReader(columnId, fileType, context);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampFromAnyIntegerTreeReader(columnId, fileType, context, readerType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT);
         case BINARY:
         case DATE:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
      }
   }

   private static TypeReader createAnyIntegerConvertTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromAnyIntegerTreeReader(columnId, fileType, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            if (fileType.getCategory() == readerType.getCategory()) {
               throw new IllegalArgumentException("No conversion of type " + String.valueOf(readerType.getCategory()) + " to self needed");
            }

            return new AnyIntegerFromAnyIntegerTreeReader(columnId, fileType, readerType, context);
         case FLOAT:
         case DOUBLE:
            return new DoubleFromAnyIntegerTreeReader(columnId, fileType, context);
         case DECIMAL:
            return new DecimalFromAnyIntegerTreeReader(columnId, fileType, context);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampFromAnyIntegerTreeReader(columnId, fileType, context, readerType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT);
         case BINARY:
         case DATE:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
      }
   }

   private static TypeReader createDoubleConvertTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromDoubleTreeReader(columnId, fileType, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return new AnyIntegerFromDoubleTreeReader(columnId, fileType, readerType, context);
         case FLOAT:
            return new FloatFromDoubleTreeReader(columnId, context);
         case DOUBLE:
            return new TreeReaderFactory.FloatTreeReader(columnId, context);
         case DECIMAL:
            return new DecimalFromDoubleTreeReader(columnId, fileType, readerType, context);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampFromDoubleTreeReader(columnId, fileType, readerType, context);
         case BINARY:
         case DATE:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
      }
   }

   private static TypeReader createDecimalConvertTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromDecimalTreeReader(columnId, fileType, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return new AnyIntegerFromDecimalTreeReader(columnId, fileType, readerType, context);
         case FLOAT:
         case DOUBLE:
            return new DoubleFromDecimalTreeReader(columnId, fileType, context);
         case DECIMAL:
            return new DecimalFromDecimalTreeReader(columnId, fileType, readerType, context);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampFromDecimalTreeReader(columnId, fileType, context, readerType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT);
         case BINARY:
         case DATE:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
      }
   }

   private static TypeReader createStringConvertTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromStringGroupTreeReader(columnId, fileType, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return new AnyIntegerFromStringGroupTreeReader(columnId, fileType, readerType, context);
         case FLOAT:
         case DOUBLE:
            return new DoubleFromStringGroupTreeReader(columnId, fileType, context);
         case DECIMAL:
            return new DecimalFromStringGroupTreeReader(columnId, fileType, readerType, context);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampFromStringGroupTreeReader(columnId, fileType, context, readerType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT);
         case BINARY:
            return new TreeReaderFactory.BinaryTreeReader(columnId, context);
         case DATE:
            return new DateFromStringGroupTreeReader(columnId, fileType, context);
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
      }
   }

   private static TypeReader createTimestampConvertTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      boolean isInstant = fileType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT;
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromTimestampTreeReader(columnId, readerType, context, isInstant);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return new AnyIntegerFromTimestampTreeReader(columnId, readerType, context, isInstant);
         case FLOAT:
         case DOUBLE:
            return new DoubleFromTimestampTreeReader(columnId, context, isInstant);
         case DECIMAL:
            return new DecimalFromTimestampTreeReader(columnId, context, isInstant);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TreeReaderFactory.TimestampTreeReader(columnId, context, isInstant);
         case BINARY:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
         case DATE:
            return new DateFromTimestampTreeReader(columnId, context, isInstant);
      }
   }

   private static TypeReader createDateConvertTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromDateTreeReader(columnId, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
         case FLOAT:
         case DOUBLE:
         case DECIMAL:
         case BINARY:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampFromDateTreeReader(columnId, readerType, context);
         case DATE:
            throw new IllegalArgumentException("No conversion of type " + String.valueOf(readerType.getCategory()) + " to self needed");
      }
   }

   private static TypeReader createBinaryConvertTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      switch (readerType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringGroupFromBinaryTreeReader(columnId, readerType, context);
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
         case FLOAT:
         case DOUBLE:
         case DECIMAL:
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
         case DATE:
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(readerType.getCategory()));
         case BINARY:
            throw new IllegalArgumentException("No conversion of type " + String.valueOf(readerType.getCategory()) + " to self needed");
      }
   }

   public static TypeReader createConvertTreeReader(TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
      SchemaEvolution evolution = context.getSchemaEvolution();
      TypeDescription fileType = evolution.getFileType(readerType.getId());
      int columnId = fileType.getId();
      switch (fileType.getCategory()) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return createStringConvertTreeReader(columnId, fileType, readerType, context);
         case BOOLEAN:
            return createBooleanConvertTreeReader(columnId, fileType, readerType, context);
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return createAnyIntegerConvertTreeReader(columnId, fileType, readerType, context);
         case FLOAT:
         case DOUBLE:
            return createDoubleConvertTreeReader(columnId, fileType, readerType, context);
         case DECIMAL:
            return createDecimalConvertTreeReader(columnId, fileType, readerType, context);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return createTimestampConvertTreeReader(columnId, fileType, readerType, context);
         case BINARY:
            return createBinaryConvertTreeReader(columnId, readerType, context);
         case DATE:
            return createDateConvertTreeReader(columnId, readerType, context);
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
         default:
            throw new IllegalArgumentException("Unsupported type " + String.valueOf(fileType.getCategory()));
      }
   }

   public static boolean canConvert(TypeDescription fileType, TypeDescription readerType) {
      TypeDescription.Category readerTypeCategory = readerType.getCategory();
      switch (readerTypeCategory) {
         case STRUCT:
         case LIST:
         case MAP:
         case UNION:
            return false;
         default:
            switch (fileType.getCategory()) {
               case STRING:
               case CHAR:
               case VARCHAR:
                  switch (readerType.getCategory()) {
                     default -> {
                        return true;
                     }
                  }
               case BOOLEAN:
               case BYTE:
               case SHORT:
               case INT:
               case LONG:
               case FLOAT:
               case DOUBLE:
               case DECIMAL:
                  switch (readerType.getCategory()) {
                     case BINARY:
                     case DATE:
                        return false;
                     default:
                        return true;
                  }
               case TIMESTAMP:
               case TIMESTAMP_INSTANT:
                  switch (readerType.getCategory()) {
                     case BINARY -> {
                        return false;
                     }
                     default -> {
                        return true;
                     }
                  }
               case BINARY:
                  switch (readerType.getCategory()) {
                     case BOOLEAN:
                     case BYTE:
                     case SHORT:
                     case INT:
                     case LONG:
                     case FLOAT:
                     case DOUBLE:
                     case DECIMAL:
                     case TIMESTAMP:
                     case TIMESTAMP_INSTANT:
                        return false;
                     default:
                        return true;
                  }
               case DATE:
                  switch (readerType.getCategory()) {
                     case BOOLEAN:
                     case BYTE:
                     case SHORT:
                     case INT:
                     case LONG:
                     case FLOAT:
                     case DOUBLE:
                     case DECIMAL:
                     case BINARY:
                        return false;
                     case TIMESTAMP:
                     case TIMESTAMP_INSTANT:
                     default:
                        return true;
                  }
               case STRUCT:
               case LIST:
               case MAP:
               case UNION:
                  return false;
               default:
                  throw new IllegalArgumentException("Unsupported type " + String.valueOf(fileType.getCategory()));
            }
      }
   }

   static {
      DATE_FORMAT = (new DateTimeFormatterBuilder()).appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter();
      TIMESTAMP_FORMAT = (new DateTimeFormatterBuilder()).append(DATE_FORMAT).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).optionalStart().appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
      INSTANT_TIMESTAMP_FORMAT = (new DateTimeFormatterBuilder()).append(TIMESTAMP_FORMAT).appendPattern(" VV").toFormatter();
      MIN_EPOCH_SECONDS = Instant.MIN.getEpochSecond();
      MAX_EPOCH_SECONDS = Instant.MAX.getEpochSecond();
   }

   public static class ConvertTreeReader extends TreeReaderFactory.TreeReader {
      TypeReader fromReader;
      private static EnumMap numericTypes = new EnumMap(TypeDescription.Category.class);
      private boolean isParseError;
      private static final double MIN_LONG_AS_DOUBLE = (double)Long.MIN_VALUE;
      private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = (double)Long.MAX_VALUE;

      ConvertTreeReader(int columnId, TypeReader fromReader, TreeReaderFactory.Context context) throws IOException {
         super(columnId, context);
         this.fromReader = fromReader;
      }

      private static void registerNumericType(TypeDescription.Category kind, int level) {
         numericTypes.put(kind, level);
      }

      static TreeReaderFactory.TreeReader getStringGroupTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
         switch (fileType.getCategory()) {
            case STRING -> {
               return new TreeReaderFactory.StringTreeReader(columnId, context);
            }
            case CHAR -> {
               return new TreeReaderFactory.CharTreeReader(columnId, fileType.getMaxLength(), context);
            }
            case VARCHAR -> {
               return new TreeReaderFactory.VarcharTreeReader(columnId, fileType.getMaxLength(), context);
            }
            default -> throw new RuntimeException("Unexpected type kind " + fileType.getCategory().name());
         }
      }

      protected void assignStringGroupVectorEntry(BytesColumnVector bytesColVector, int elementNum, TypeDescription readerType, byte[] bytes) {
         this.assignStringGroupVectorEntry(bytesColVector, elementNum, readerType, bytes, 0, bytes.length);
      }

      protected void assignStringGroupVectorEntry(BytesColumnVector bytesColVector, int elementNum, TypeDescription readerType, byte[] bytes, int start, int length) {
         switch (readerType.getCategory()) {
            case STRING:
               bytesColVector.setVal(elementNum, bytes, start, length);
               break;
            case CHAR:
               int charAdjustedDownLen = StringExpr.rightTrimAndTruncate(bytes, start, length, readerType.getMaxLength());
               bytesColVector.setVal(elementNum, bytes, start, charAdjustedDownLen);
               break;
            case VARCHAR:
               int varcharAdjustedDownLen = StringExpr.truncate(bytes, start, length, readerType.getMaxLength());
               bytesColVector.setVal(elementNum, bytes, start, varcharAdjustedDownLen);
               break;
            default:
               throw new RuntimeException("Unexpected type kind " + readerType.getCategory().name());
         }

      }

      protected void convertStringGroupVectorElement(BytesColumnVector bytesColVector, int elementNum, TypeDescription readerType) {
         switch (readerType.getCategory()) {
            case STRING:
               break;
            case CHAR:
               int charLength = bytesColVector.length[elementNum];
               int charAdjustedDownLen = StringExpr.rightTrimAndTruncate(bytesColVector.vector[elementNum], bytesColVector.start[elementNum], charLength, readerType.getMaxLength());
               if (charAdjustedDownLen < charLength) {
                  bytesColVector.length[elementNum] = charAdjustedDownLen;
               }
               break;
            case VARCHAR:
               int varcharLength = bytesColVector.length[elementNum];
               int varcharAdjustedDownLen = StringExpr.truncate(bytesColVector.vector[elementNum], bytesColVector.start[elementNum], varcharLength, readerType.getMaxLength());
               if (varcharAdjustedDownLen < varcharLength) {
                  bytesColVector.length[elementNum] = varcharAdjustedDownLen;
               }
               break;
            default:
               throw new RuntimeException("Unexpected type kind " + readerType.getCategory().name());
         }

      }

      protected boolean getIsParseError() {
         return this.isParseError;
      }

      protected long parseLongFromString(String string) {
         try {
            long longValue = Long.parseLong(string);
            this.isParseError = false;
            return longValue;
         } catch (NumberFormatException var4) {
            this.isParseError = true;
            return 0L;
         }
      }

      protected float parseFloatFromString(String string) {
         try {
            float floatValue = Float.parseFloat(string);
            this.isParseError = false;
            return floatValue;
         } catch (NumberFormatException var3) {
            this.isParseError = true;
            return Float.NaN;
         }
      }

      protected double parseDoubleFromString(String string) {
         try {
            double value = Double.parseDouble(string);
            this.isParseError = false;
            return value;
         } catch (NumberFormatException var4) {
            this.isParseError = true;
            return Double.NaN;
         }
      }

      protected HiveDecimal parseDecimalFromString(String string) {
         try {
            HiveDecimal value = HiveDecimal.create(string);
            return value;
         } catch (NumberFormatException var3) {
            return null;
         }
      }

      public boolean doubleCanFitInLong(double doubleValue) {
         return (double)Long.MIN_VALUE - doubleValue < (double)1.0F && doubleValue < (double)Long.MAX_VALUE;
      }

      public void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
         this.fromReader.checkEncoding(encoding);
      }

      public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.startStripe(planner, readPhase);
      }

      public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.seek(index, readPhase);
      }

      public void seek(PositionProvider index, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.seek(index, readPhase);
      }

      public void skipRows(long items, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.skipRows(items, readPhase);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         throw new RuntimeException("Expected this method to be overridden");
      }

      public void convertVector(ColumnVector fromColVector, ColumnVector resultColVector, int batchSize) throws IOException {
         resultColVector.reset();
         if (fromColVector.isRepeating) {
            resultColVector.isRepeating = true;
            if (!fromColVector.noNulls && fromColVector.isNull[0]) {
               resultColVector.noNulls = false;
               resultColVector.isNull[0] = true;
            } else {
               this.setConvertVectorElement(0);
            }
         } else if (fromColVector.noNulls) {
            for(int i = 0; i < batchSize; ++i) {
               this.setConvertVectorElement(i);
            }
         } else {
            for(int i = 0; i < batchSize; ++i) {
               if (!fromColVector.isNull[i]) {
                  this.setConvertVectorElement(i);
               } else {
                  resultColVector.noNulls = false;
                  resultColVector.isNull[i] = true;
               }
            }
         }

      }

      public void downCastAnyInteger(LongColumnVector longColVector, int elementNum, TypeDescription readerType) {
         this.downCastAnyInteger(longColVector, elementNum, longColVector.vector[elementNum], readerType);
      }

      public void downCastAnyInteger(LongColumnVector longColVector, int elementNum, long inputLong, TypeDescription readerType) {
         long[] vector = longColVector.vector;
         TypeDescription.Category readerCategory = readerType.getCategory();
         long outputLong;
         switch (readerCategory) {
            case BOOLEAN:
               vector[elementNum] = inputLong == 0L ? 0L : 1L;
               return;
            case BYTE:
               outputLong = (long)((byte)((int)inputLong));
               break;
            case SHORT:
               outputLong = (long)((short)((int)inputLong));
               break;
            case INT:
               outputLong = (long)((int)inputLong);
               break;
            case LONG:
               vector[elementNum] = inputLong;
               return;
            default:
               throw new RuntimeException("Unexpected type kind " + readerCategory.name());
         }

         if (outputLong != inputLong) {
            longColVector.isNull[elementNum] = true;
            longColVector.noNulls = false;
         } else {
            vector[elementNum] = outputLong;
         }

      }

      protected boolean integerDownCastNeeded(TypeDescription fileType, TypeDescription readerType) {
         Integer fileLevel = (Integer)numericTypes.get(fileType.getCategory());
         Integer schemaLevel = (Integer)numericTypes.get(readerType.getCategory());
         return schemaLevel < fileLevel;
      }

      static {
         registerNumericType(TypeDescription.Category.BOOLEAN, 1);
         registerNumericType(TypeDescription.Category.BYTE, 2);
         registerNumericType(TypeDescription.Category.SHORT, 3);
         registerNumericType(TypeDescription.Category.INT, 4);
         registerNumericType(TypeDescription.Category.LONG, 5);
         registerNumericType(TypeDescription.Category.FLOAT, 6);
         registerNumericType(TypeDescription.Category.DOUBLE, 7);
         registerNumericType(TypeDescription.Category.DECIMAL, 8);
      }
   }

   public static class AnyIntegerFromAnyIntegerTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private final boolean downCastNeeded;

      AnyIntegerFromAnyIntegerTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, ConvertTreeReaderFactory.createFromInteger(columnId, fileType, context), context);
         this.readerType = readerType;
         this.downCastNeeded = this.integerDownCastNeeded(fileType, readerType);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
         LongColumnVector resultColVector = (LongColumnVector)previousVector;
         if (this.downCastNeeded) {
            if (resultColVector.isRepeating) {
               if (resultColVector.noNulls || !resultColVector.isNull[0]) {
                  this.downCastAnyInteger(resultColVector, 0, this.readerType);
               }
            } else if (resultColVector.noNulls) {
               for(int i = 0; i < batchSize; ++i) {
                  this.downCastAnyInteger(resultColVector, i, this.readerType);
               }
            } else {
               for(int i = 0; i < batchSize; ++i) {
                  if (!resultColVector.isNull[i]) {
                     this.downCastAnyInteger(resultColVector, i, this.readerType);
                  }
               }
            }
         }

      }
   }

   public static class AnyIntegerFromDoubleTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private DoubleColumnVector doubleColVector;
      private LongColumnVector longColVector;

      AnyIntegerFromDoubleTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, (TypeReader)(fileType.getCategory() == TypeDescription.Category.DOUBLE ? new TreeReaderFactory.DoubleTreeReader(columnId, context) : new TreeReaderFactory.FloatTreeReader(columnId, context)), context);
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         double doubleValue = this.doubleColVector.vector[elementNum];
         if (!this.doubleCanFitInLong(doubleValue)) {
            this.longColVector.isNull[elementNum] = true;
            this.longColVector.noNulls = false;
         } else {
            this.downCastAnyInteger(this.longColVector, elementNum, (long)doubleValue, this.readerType);
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.doubleColVector == null) {
            this.doubleColVector = new DoubleColumnVector(batchSize);
            this.longColVector = (LongColumnVector)previousVector;
         } else {
            this.doubleColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.doubleColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.doubleColVector, this.longColVector, batchSize);
      }
   }

   public static class AnyIntegerFromDecimalTreeReader extends ConvertTreeReader {
      private final int precision;
      private final int scale;
      private final TypeDescription readerType;
      private DecimalColumnVector decimalColVector;
      private LongColumnVector longColVector;

      AnyIntegerFromDecimalTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DecimalTreeReader(columnId, fileType.getPrecision(), fileType.getScale(), context), context);
         this.precision = fileType.getPrecision();
         this.scale = fileType.getScale();
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         HiveDecimalWritable decWritable = this.decimalColVector.vector[elementNum];
         long[] vector = this.longColVector.vector;
         TypeDescription.Category readerCategory = this.readerType.getCategory();
         boolean isInRange;
         switch (readerCategory) {
            case BOOLEAN:
               vector[elementNum] = decWritable.signum() == 0 ? 0L : 1L;
               return;
            case BYTE:
               isInRange = decWritable.isByte();
               break;
            case SHORT:
               isInRange = decWritable.isShort();
               break;
            case INT:
               isInRange = decWritable.isInt();
               break;
            case LONG:
               isInRange = decWritable.isLong();
               break;
            default:
               throw new RuntimeException("Unexpected type kind " + readerCategory.name());
         }

         if (!isInRange) {
            this.longColVector.isNull[elementNum] = true;
            this.longColVector.noNulls = false;
         } else {
            switch (readerCategory) {
               case BYTE -> vector[elementNum] = (long)decWritable.byteValue();
               case SHORT -> vector[elementNum] = (long)decWritable.shortValue();
               case INT -> vector[elementNum] = (long)decWritable.intValue();
               case LONG -> vector[elementNum] = decWritable.longValue();
               default -> throw new RuntimeException("Unexpected type kind " + readerCategory.name());
            }
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.decimalColVector == null) {
            this.decimalColVector = new DecimalColumnVector(batchSize, this.precision, this.scale);
            this.longColVector = (LongColumnVector)previousVector;
         } else {
            this.decimalColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.decimalColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.decimalColVector, this.longColVector, batchSize);
      }
   }

   public static class AnyIntegerFromStringGroupTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private BytesColumnVector bytesColVector;
      private LongColumnVector longColVector;

      AnyIntegerFromStringGroupTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, getStringGroupTreeReader(columnId, fileType, context), context);
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         String string = SerializationUtils.bytesVectorToString(this.bytesColVector, elementNum);
         long longValue = this.parseLongFromString(string);
         if (!this.getIsParseError()) {
            this.downCastAnyInteger(this.longColVector, elementNum, longValue, this.readerType);
         } else {
            this.longColVector.noNulls = false;
            this.longColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.bytesColVector == null) {
            this.bytesColVector = new BytesColumnVector(batchSize);
            this.longColVector = (LongColumnVector)previousVector;
         } else {
            this.bytesColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.bytesColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.bytesColVector, this.longColVector, batchSize);
      }
   }

   public static class AnyIntegerFromTimestampTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private TimestampColumnVector timestampColVector;
      private LongColumnVector longColVector;

      AnyIntegerFromTimestampTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context, boolean instantType) throws IOException {
         super(columnId, new TreeReaderFactory.TimestampTreeReader(columnId, context, instantType), context);
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) {
         long millis = this.timestampColVector.asScratchTimestamp(elementNum).getTime();
         long longValue = Math.floorDiv(millis, 1000);
         this.downCastAnyInteger(this.longColVector, elementNum, longValue, this.readerType);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.timestampColVector == null) {
            this.timestampColVector = new TimestampColumnVector(batchSize);
            this.longColVector = (LongColumnVector)previousVector;
         } else {
            this.timestampColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.timestampColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.timestampColVector, this.longColVector, batchSize);
      }
   }

   public static class DoubleFromAnyIntegerTreeReader extends ConvertTreeReader {
      private LongColumnVector longColVector;
      private DoubleColumnVector doubleColVector;

      DoubleFromAnyIntegerTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, ConvertTreeReaderFactory.createFromInteger(columnId, fileType, context), context);
      }

      public void setConvertVectorElement(int elementNum) {
         double doubleValue = (double)this.longColVector.vector[elementNum];
         if (!Double.isNaN(doubleValue)) {
            this.doubleColVector.vector[elementNum] = doubleValue;
         } else {
            this.doubleColVector.vector[elementNum] = Double.NaN;
            this.doubleColVector.noNulls = false;
            this.doubleColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.longColVector == null) {
            this.longColVector = new LongColumnVector(batchSize);
            this.doubleColVector = (DoubleColumnVector)previousVector;
         } else {
            this.longColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.longColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.longColVector, this.doubleColVector, batchSize);
      }
   }

   public static class DoubleFromDecimalTreeReader extends ConvertTreeReader {
      private final int precision;
      private final int scale;
      private DecimalColumnVector decimalColVector;
      private DoubleColumnVector doubleColVector;

      DoubleFromDecimalTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DecimalTreeReader(columnId, fileType.getPrecision(), fileType.getScale(), context), context);
         this.precision = fileType.getPrecision();
         this.scale = fileType.getScale();
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         this.doubleColVector.vector[elementNum] = this.decimalColVector.vector[elementNum].doubleValue();
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.decimalColVector == null) {
            this.decimalColVector = new DecimalColumnVector(batchSize, this.precision, this.scale);
            this.doubleColVector = (DoubleColumnVector)previousVector;
         } else {
            this.decimalColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.decimalColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.decimalColVector, this.doubleColVector, batchSize);
      }
   }

   public static class DoubleFromStringGroupTreeReader extends ConvertTreeReader {
      private BytesColumnVector bytesColVector;
      private DoubleColumnVector doubleColVector;

      DoubleFromStringGroupTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, getStringGroupTreeReader(columnId, fileType, context), context);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         String string = SerializationUtils.bytesVectorToString(this.bytesColVector, elementNum);
         double doubleValue = this.parseDoubleFromString(string);
         if (!this.getIsParseError()) {
            this.doubleColVector.vector[elementNum] = doubleValue;
         } else {
            this.doubleColVector.noNulls = false;
            this.doubleColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.bytesColVector == null) {
            this.bytesColVector = new BytesColumnVector(batchSize);
            this.doubleColVector = (DoubleColumnVector)previousVector;
         } else {
            this.bytesColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.bytesColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.bytesColVector, this.doubleColVector, batchSize);
      }
   }

   public static class DoubleFromTimestampTreeReader extends ConvertTreeReader {
      private TimestampColumnVector timestampColVector;
      private DoubleColumnVector doubleColVector;

      DoubleFromTimestampTreeReader(int columnId, TreeReaderFactory.Context context, boolean instantType) throws IOException {
         super(columnId, new TreeReaderFactory.TimestampTreeReader(columnId, context, instantType), context);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         Timestamp ts = this.timestampColVector.asScratchTimestamp(elementNum);
         double result = (double)Math.floorDiv(ts.getTime(), 1000);
         int nano = ts.getNanos();
         if (nano != 0) {
            result += (double)nano / (double)1.0E9F;
         }

         this.doubleColVector.vector[elementNum] = result;
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.timestampColVector == null) {
            this.timestampColVector = new TimestampColumnVector(batchSize);
            this.doubleColVector = (DoubleColumnVector)previousVector;
         } else {
            this.timestampColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.timestampColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.timestampColVector, this.doubleColVector, batchSize);
      }
   }

   public static class FloatFromDoubleTreeReader extends ConvertTreeReader {
      FloatFromDoubleTreeReader(int columnId, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DoubleTreeReader(columnId, context), context);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
         DoubleColumnVector vector = (DoubleColumnVector)previousVector;
         if (previousVector.isRepeating) {
            vector.vector[0] = (double)((float)vector.vector[0]);
         } else {
            for(int i = 0; i < batchSize; ++i) {
               vector.vector[i] = (double)((float)vector.vector[i]);
            }
         }

      }
   }

   public static class DecimalFromAnyIntegerTreeReader extends ConvertTreeReader {
      private LongColumnVector longColVector;
      private ColumnVector decimalColVector;
      private final HiveDecimalWritable value = new HiveDecimalWritable();

      DecimalFromAnyIntegerTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, ConvertTreeReaderFactory.createFromInteger(columnId, fileType, context), context);
      }

      public void setConvertVectorElement(int elementNum) {
         long longValue = this.longColVector.vector[elementNum];
         this.value.setFromLong(longValue);
         if (this.decimalColVector instanceof Decimal64ColumnVector) {
            ((Decimal64ColumnVector)this.decimalColVector).set(elementNum, this.value);
         } else {
            ((DecimalColumnVector)this.decimalColVector).set(elementNum, this.value);
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.longColVector == null) {
            this.longColVector = new LongColumnVector(batchSize);
            this.decimalColVector = previousVector;
         } else {
            this.longColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.longColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.longColVector, this.decimalColVector, batchSize);
      }
   }

   public static class DecimalFromDoubleTreeReader extends ConvertTreeReader {
      private DoubleColumnVector doubleColVector;
      private ColumnVector decimalColVector;

      DecimalFromDoubleTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, (TypeReader)(fileType.getCategory() == TypeDescription.Category.DOUBLE ? new TreeReaderFactory.DoubleTreeReader(columnId, context) : new TreeReaderFactory.FloatTreeReader(columnId, context)), context);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         HiveDecimal value = HiveDecimal.create(Double.toString(this.doubleColVector.vector[elementNum]));
         if (value != null) {
            if (this.decimalColVector instanceof Decimal64ColumnVector) {
               ((Decimal64ColumnVector)this.decimalColVector).set(elementNum, value);
            } else {
               ((DecimalColumnVector)this.decimalColVector).set(elementNum, value);
            }
         } else {
            this.decimalColVector.noNulls = false;
            this.decimalColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.doubleColVector == null) {
            this.doubleColVector = new DoubleColumnVector(batchSize);
            this.decimalColVector = previousVector;
         } else {
            this.doubleColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.doubleColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.doubleColVector, this.decimalColVector, batchSize);
      }
   }

   public static class DecimalFromStringGroupTreeReader extends ConvertTreeReader {
      private BytesColumnVector bytesColVector;
      private ColumnVector decimalColVector;

      DecimalFromStringGroupTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, getStringGroupTreeReader(columnId, fileType, context), context);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         String string = SerializationUtils.bytesVectorToString(this.bytesColVector, elementNum);
         HiveDecimal value = this.parseDecimalFromString(string);
         if (value != null) {
            if (this.decimalColVector instanceof Decimal64ColumnVector) {
               ((Decimal64ColumnVector)this.decimalColVector).set(elementNum, value);
            } else {
               ((DecimalColumnVector)this.decimalColVector).set(elementNum, value);
            }
         } else {
            this.decimalColVector.noNulls = false;
            this.decimalColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.bytesColVector == null) {
            this.bytesColVector = new BytesColumnVector(batchSize);
            this.decimalColVector = previousVector;
         } else {
            this.bytesColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.bytesColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.bytesColVector, this.decimalColVector, batchSize);
      }
   }

   public static class DecimalFromTimestampTreeReader extends ConvertTreeReader {
      private TimestampColumnVector timestampColVector;
      private ColumnVector decimalColVector;

      DecimalFromTimestampTreeReader(int columnId, TreeReaderFactory.Context context, boolean instantType) throws IOException {
         super(columnId, new TreeReaderFactory.TimestampTreeReader(columnId, context, instantType), context);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         long seconds = Math.floorDiv(this.timestampColVector.time[elementNum], 1000);
         long nanos = (long)this.timestampColVector.nanos[elementNum];
         if (seconds < 0L && nanos > 0L) {
            ++seconds;
            nanos = 1000000000L - nanos;
         }

         BigDecimal secondsBd = new BigDecimal(seconds);
         BigDecimal nanosBd = (new BigDecimal(nanos)).movePointLeft(9);
         BigDecimal resultBd = seconds >= 0L ? secondsBd.add(nanosBd) : secondsBd.subtract(nanosBd);
         HiveDecimal value = HiveDecimal.create(resultBd);
         if (value != null) {
            if (this.decimalColVector instanceof Decimal64ColumnVector) {
               ((Decimal64ColumnVector)this.decimalColVector).set(elementNum, value);
            } else {
               ((DecimalColumnVector)this.decimalColVector).set(elementNum, value);
            }
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.timestampColVector == null) {
            this.timestampColVector = new TimestampColumnVector(batchSize);
            this.decimalColVector = previousVector;
         } else {
            this.timestampColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.timestampColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.timestampColVector, this.decimalColVector, batchSize);
      }
   }

   public static class DecimalFromDecimalTreeReader extends ConvertTreeReader {
      private DecimalColumnVector fileDecimalColVector;
      private int filePrecision;
      private int fileScale;
      private ColumnVector decimalColVector;

      DecimalFromDecimalTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DecimalTreeReader(columnId, fileType.getPrecision(), fileType.getScale(), context), context);
         this.filePrecision = fileType.getPrecision();
         this.fileScale = fileType.getScale();
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         if (this.decimalColVector instanceof Decimal64ColumnVector) {
            ((Decimal64ColumnVector)this.decimalColVector).set(elementNum, this.fileDecimalColVector.vector[elementNum]);
         } else {
            ((DecimalColumnVector)this.decimalColVector).set(elementNum, this.fileDecimalColVector.vector[elementNum]);
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.fileDecimalColVector == null) {
            this.fileDecimalColVector = new DecimalColumnVector(batchSize, this.filePrecision, this.fileScale);
            this.decimalColVector = previousVector;
         } else {
            this.fileDecimalColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.fileDecimalColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.fileDecimalColVector, this.decimalColVector, batchSize);
      }
   }

   public static class StringGroupFromAnyIntegerTreeReader extends ConvertTreeReader {
      protected final TypeDescription readerType;
      protected LongColumnVector longColVector;
      protected BytesColumnVector bytesColVector;

      StringGroupFromAnyIntegerTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, ConvertTreeReaderFactory.createFromInteger(columnId, fileType, context), context);
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) {
         byte[] bytes = Long.toString(this.longColVector.vector[elementNum]).getBytes(StandardCharsets.UTF_8);
         this.assignStringGroupVectorEntry(this.bytesColVector, elementNum, this.readerType, bytes);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.longColVector == null) {
            this.longColVector = new LongColumnVector(batchSize);
            this.bytesColVector = (BytesColumnVector)previousVector;
         } else {
            this.longColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.longColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.longColVector, this.bytesColVector, batchSize);
      }
   }

   public static class StringGroupFromBooleanTreeReader extends StringGroupFromAnyIntegerTreeReader {
      private static final byte[] TRUE_BYTES;
      private static final byte[] FALSE_BYTES;

      StringGroupFromBooleanTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, fileType, readerType, context);
      }

      public void setConvertVectorElement(int elementNum) {
         byte[] bytes = this.longColVector.vector[elementNum] != 0L ? TRUE_BYTES : FALSE_BYTES;
         this.assignStringGroupVectorEntry(this.bytesColVector, elementNum, this.readerType, bytes);
      }

      static {
         TRUE_BYTES = "TRUE".getBytes(StandardCharsets.US_ASCII);
         FALSE_BYTES = "FALSE".getBytes(StandardCharsets.US_ASCII);
      }
   }

   public static class StringGroupFromDoubleTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private DoubleColumnVector doubleColVector;
      private BytesColumnVector bytesColVector;

      StringGroupFromDoubleTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, (TypeReader)(fileType.getCategory() == TypeDescription.Category.DOUBLE ? new TreeReaderFactory.DoubleTreeReader(columnId, context) : new TreeReaderFactory.FloatTreeReader(columnId, context)), context);
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) {
         double doubleValue = this.doubleColVector.vector[elementNum];
         if (!Double.isNaN(doubleValue)) {
            String string = Double.toString(doubleValue);
            byte[] bytes = string.getBytes(StandardCharsets.US_ASCII);
            this.assignStringGroupVectorEntry(this.bytesColVector, elementNum, this.readerType, bytes);
         } else {
            this.bytesColVector.noNulls = false;
            this.bytesColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.doubleColVector == null) {
            this.doubleColVector = new DoubleColumnVector(batchSize);
            this.bytesColVector = (BytesColumnVector)previousVector;
         } else {
            this.doubleColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.doubleColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.doubleColVector, this.bytesColVector, batchSize);
      }
   }

   public static class StringGroupFromDecimalTreeReader extends ConvertTreeReader {
      private int precision;
      private int scale;
      private final TypeDescription readerType;
      private DecimalColumnVector decimalColVector;
      private BytesColumnVector bytesColVector;
      private byte[] scratchBuffer;

      StringGroupFromDecimalTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DecimalTreeReader(columnId, fileType.getPrecision(), fileType.getScale(), context), context);
         this.precision = fileType.getPrecision();
         this.scale = fileType.getScale();
         this.readerType = readerType;
         this.scratchBuffer = new byte[79];
      }

      public void setConvertVectorElement(int elementNum) {
         HiveDecimalWritable decWritable = this.decimalColVector.vector[elementNum];
         int byteIndex = decWritable.toBytes(this.scratchBuffer);
         this.assignStringGroupVectorEntry(this.bytesColVector, elementNum, this.readerType, this.scratchBuffer, byteIndex, 79 - byteIndex);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.decimalColVector == null) {
            this.decimalColVector = new DecimalColumnVector(batchSize, this.precision, this.scale);
            this.bytesColVector = (BytesColumnVector)previousVector;
         } else {
            this.decimalColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.decimalColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.decimalColVector, this.bytesColVector, batchSize);
      }
   }

   public static class StringGroupFromTimestampTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private final ZoneId local;
      private final DateTimeFormatter formatter;
      private TimestampColumnVector timestampColVector;
      private BytesColumnVector bytesColVector;

      StringGroupFromTimestampTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context, boolean instantType) throws IOException {
         super(columnId, new TreeReaderFactory.TimestampTreeReader(columnId, context, instantType), context);
         this.readerType = readerType;
         this.local = context.getUseUTCTimestamp() ? ZoneId.of("UTC") : ZoneId.systemDefault();
         Chronology chronology = (Chronology)(context.useProlepticGregorian() ? IsoChronology.INSTANCE : HybridChronology.INSTANCE);
         this.formatter = (instantType ? ConvertTreeReaderFactory.INSTANT_TIMESTAMP_FORMAT : ConvertTreeReaderFactory.TIMESTAMP_FORMAT).withChronology(chronology);
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         String string = ConvertTreeReaderFactory.timestampToInstant(this.timestampColVector, elementNum).atZone(this.local).format(this.formatter);
         byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
         this.assignStringGroupVectorEntry(this.bytesColVector, elementNum, this.readerType, bytes);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.timestampColVector == null) {
            this.timestampColVector = new TimestampColumnVector(batchSize);
            this.bytesColVector = (BytesColumnVector)previousVector;
         } else {
            this.timestampColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.timestampColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.timestampColVector, this.bytesColVector, batchSize);
      }
   }

   public static class StringGroupFromDateTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;
      private DateColumnVector longColVector;
      private BytesColumnVector bytesColVector;
      private final boolean useProlepticGregorian;

      StringGroupFromDateTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DateTreeReader(columnId, context), context);
         this.readerType = readerType;
         this.useProlepticGregorian = context.useProlepticGregorian();
      }

      public void setConvertVectorElement(int elementNum) {
         String dateStr = DateUtils.printDate((int)this.longColVector.vector[elementNum], this.useProlepticGregorian);
         byte[] bytes = dateStr.getBytes(StandardCharsets.UTF_8);
         this.assignStringGroupVectorEntry(this.bytesColVector, elementNum, this.readerType, bytes);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.longColVector == null) {
            this.longColVector = new DateColumnVector(batchSize);
            this.bytesColVector = (BytesColumnVector)previousVector;
         } else {
            this.longColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.longColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.longColVector, this.bytesColVector, batchSize);
      }
   }

   public static class StringGroupFromStringGroupTreeReader extends ConvertTreeReader {
      private final TypeDescription readerType;

      StringGroupFromStringGroupTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, getStringGroupTreeReader(columnId, fileType, context), context);
         this.readerType = readerType;
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         this.fromReader.nextVector(previousVector, isNull, batchSize, filterContext, readPhase);
         BytesColumnVector resultColVector = (BytesColumnVector)previousVector;
         if (resultColVector.isRepeating) {
            if (resultColVector.noNulls || !resultColVector.isNull[0]) {
               this.convertStringGroupVectorElement(resultColVector, 0, this.readerType);
            }
         } else if (resultColVector.noNulls) {
            for(int i = 0; i < batchSize; ++i) {
               this.convertStringGroupVectorElement(resultColVector, i, this.readerType);
            }
         } else {
            for(int i = 0; i < batchSize; ++i) {
               if (!resultColVector.isNull[i]) {
                  this.convertStringGroupVectorElement(resultColVector, i, this.readerType);
               }
            }
         }

      }
   }

   public static class StringGroupFromBinaryTreeReader extends ConvertTreeReader {
      public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
      private final TypeDescription readerType;
      private BytesColumnVector inBytesColVector;
      private BytesColumnVector outBytesColVector;

      StringGroupFromBinaryTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.BinaryTreeReader(columnId, context), context);
         this.readerType = readerType;
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         byte[] bytes = this.inBytesColVector.vector[elementNum];
         int start = this.inBytesColVector.start[elementNum];
         int length = this.inBytesColVector.length[elementNum];
         byte[] string = length == 0 ? EMPTY_BYTE_ARRAY : new byte[3 * length - 1];

         for(int p = 0; p < string.length; p += 2) {
            if (p != 0) {
               string[p++] = 32;
            }

            int num = 255 & bytes[start++];
            int digit = num / 16;
            string[p] = (byte)(digit + (digit < 10 ? 48 : 87));
            digit = num % 16;
            string[p + 1] = (byte)(digit + (digit < 10 ? 48 : 87));
         }

         this.assignStringGroupVectorEntry(this.outBytesColVector, elementNum, this.readerType, string, 0, string.length);
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.inBytesColVector == null) {
            this.inBytesColVector = new BytesColumnVector(batchSize);
            this.outBytesColVector = (BytesColumnVector)previousVector;
         } else {
            this.inBytesColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.inBytesColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.inBytesColVector, this.outBytesColVector, batchSize);
      }
   }

   public static class TimestampFromAnyIntegerTreeReader extends ConvertTreeReader {
      private LongColumnVector longColVector;
      private TimestampColumnVector timestampColVector;
      private final boolean useUtc;
      private final TimeZone local;
      private final boolean fileUsedProlepticGregorian;
      private final boolean useProlepticGregorian;

      TimestampFromAnyIntegerTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context, boolean isInstant) throws IOException {
         super(columnId, ConvertTreeReaderFactory.createFromInteger(columnId, fileType, context), context);
         this.useUtc = isInstant || context.getUseUTCTimestamp();
         this.local = TimeZone.getDefault();
         this.fileUsedProlepticGregorian = context.fileUsedProlepticGregorian();
         this.useProlepticGregorian = context.useProlepticGregorian();
      }

      public void setConvertVectorElement(int elementNum) {
         long millis = this.longColVector.vector[elementNum] * 1000L;
         this.timestampColVector.time[elementNum] = this.useUtc ? millis : SerializationUtils.convertFromUtc(this.local, millis);
         this.timestampColVector.nanos[elementNum] = 0;
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.longColVector == null) {
            this.longColVector = new LongColumnVector(batchSize);
            this.timestampColVector = (TimestampColumnVector)previousVector;
         } else {
            this.longColVector.ensureSize(batchSize, false);
         }

         this.timestampColVector.changeCalendar(this.fileUsedProlepticGregorian, false);
         this.fromReader.nextVector(this.longColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.longColVector, this.timestampColVector, batchSize);
         this.timestampColVector.changeCalendar(this.useProlepticGregorian, true);
      }
   }

   public static class TimestampFromDoubleTreeReader extends ConvertTreeReader {
      private DoubleColumnVector doubleColVector;
      private TimestampColumnVector timestampColVector;
      private final boolean useUtc;
      private final TimeZone local;
      private final boolean useProlepticGregorian;
      private final boolean fileUsedProlepticGregorian;

      TimestampFromDoubleTreeReader(int columnId, TypeDescription fileType, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, (TypeReader)(fileType.getCategory() == TypeDescription.Category.DOUBLE ? new TreeReaderFactory.DoubleTreeReader(columnId, context) : new TreeReaderFactory.FloatTreeReader(columnId, context)), context);
         this.useUtc = readerType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT || context.getUseUTCTimestamp();
         this.local = TimeZone.getDefault();
         this.useProlepticGregorian = context.useProlepticGregorian();
         this.fileUsedProlepticGregorian = context.fileUsedProlepticGregorian();
      }

      public void setConvertVectorElement(int elementNum) {
         double seconds = this.doubleColVector.vector[elementNum];
         if (!this.useUtc) {
            seconds = SerializationUtils.convertFromUtc(this.local, seconds);
         }

         double doubleMillis = seconds * (double)1000.0F;
         long millis = Math.round(doubleMillis);
         if (!(doubleMillis > (double)Long.MAX_VALUE) && !(doubleMillis < (double)Long.MIN_VALUE) && millis >= 0L == doubleMillis >= (double)0.0F) {
            this.timestampColVector.time[elementNum] = millis;
            this.timestampColVector.nanos[elementNum] = Math.floorMod(millis, 1000) * 1000000;
         } else {
            this.timestampColVector.time[elementNum] = 0L;
            this.timestampColVector.nanos[elementNum] = 0;
            this.timestampColVector.isNull[elementNum] = true;
            this.timestampColVector.noNulls = false;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.doubleColVector == null) {
            this.doubleColVector = new DoubleColumnVector(batchSize);
            this.timestampColVector = (TimestampColumnVector)previousVector;
         } else {
            this.doubleColVector.ensureSize(batchSize, false);
         }

         this.timestampColVector.changeCalendar(this.fileUsedProlepticGregorian, false);
         this.fromReader.nextVector(this.doubleColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.doubleColVector, this.timestampColVector, batchSize);
         this.timestampColVector.changeCalendar(this.useProlepticGregorian, true);
      }
   }

   public static class TimestampFromDecimalTreeReader extends ConvertTreeReader {
      private final int precision;
      private final int scale;
      private DecimalColumnVector decimalColVector;
      private TimestampColumnVector timestampColVector;
      private final boolean useUtc;
      private final TimeZone local;
      private final boolean useProlepticGregorian;
      private final boolean fileUsedProlepticGregorian;
      private final HiveDecimalWritable value;

      TimestampFromDecimalTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context, boolean isInstant) throws IOException {
         super(columnId, new TreeReaderFactory.DecimalTreeReader(columnId, fileType.getPrecision(), fileType.getScale(), context), context);
         this.precision = fileType.getPrecision();
         this.scale = fileType.getScale();
         this.useUtc = isInstant || context.getUseUTCTimestamp();
         this.local = TimeZone.getDefault();
         this.useProlepticGregorian = context.useProlepticGregorian();
         this.fileUsedProlepticGregorian = context.fileUsedProlepticGregorian();
         this.value = new HiveDecimalWritable();
      }

      public void setConvertVectorElement(int elementNum) {
         Instant t = ConvertTreeReaderFactory.decimalToInstant(this.decimalColVector, elementNum, this.value);
         if (t == null) {
            this.timestampColVector.noNulls = false;
            this.timestampColVector.isNull[elementNum] = true;
         } else if (!this.useUtc) {
            long millis = t.toEpochMilli();
            this.timestampColVector.time[elementNum] = SerializationUtils.convertFromUtc(this.local, millis);
            this.timestampColVector.nanos[elementNum] = t.getNano();
         } else {
            this.timestampColVector.time[elementNum] = t.toEpochMilli();
            this.timestampColVector.nanos[elementNum] = t.getNano();
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.decimalColVector == null) {
            this.decimalColVector = new DecimalColumnVector(batchSize, this.precision, this.scale);
            this.timestampColVector = (TimestampColumnVector)previousVector;
         } else {
            this.decimalColVector.ensureSize(batchSize, false);
         }

         this.timestampColVector.changeCalendar(this.fileUsedProlepticGregorian, false);
         this.fromReader.nextVector(this.decimalColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.decimalColVector, this.timestampColVector, batchSize);
         this.timestampColVector.changeCalendar(this.useProlepticGregorian, true);
      }
   }

   public static class TimestampFromStringGroupTreeReader extends ConvertTreeReader {
      private BytesColumnVector bytesColVector;
      private TimestampColumnVector timestampColVector;
      private final DateTimeFormatter formatter;
      private final boolean useProlepticGregorian;

      TimestampFromStringGroupTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context, boolean isInstant) throws IOException {
         super(columnId, getStringGroupTreeReader(columnId, fileType, context), context);
         this.useProlepticGregorian = context.useProlepticGregorian();
         Chronology chronology = (Chronology)(this.useProlepticGregorian ? IsoChronology.INSTANCE : HybridChronology.INSTANCE);
         if (isInstant) {
            this.formatter = ConvertTreeReaderFactory.INSTANT_TIMESTAMP_FORMAT.withChronology(chronology);
         } else {
            this.formatter = ConvertTreeReaderFactory.TIMESTAMP_FORMAT.withZone(context.getUseUTCTimestamp() ? ZoneId.of("UTC") : ZoneId.systemDefault()).withChronology(chronology);
         }

      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         String str = SerializationUtils.bytesVectorToString(this.bytesColVector, elementNum);

         try {
            Instant instant = Instant.from(this.formatter.parse(str));
            this.timestampColVector.time[elementNum] = instant.toEpochMilli();
            this.timestampColVector.nanos[elementNum] = instant.getNano();
         } catch (DateTimeParseException var4) {
            this.timestampColVector.noNulls = false;
            this.timestampColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.bytesColVector == null) {
            this.bytesColVector = new BytesColumnVector(batchSize);
            this.timestampColVector = (TimestampColumnVector)previousVector;
         } else {
            this.bytesColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.bytesColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.bytesColVector, this.timestampColVector, batchSize);
         this.timestampColVector.changeCalendar(this.useProlepticGregorian, false);
      }
   }

   public static class TimestampFromDateTreeReader extends ConvertTreeReader {
      private DateColumnVector longColVector;
      private TimestampColumnVector timestampColVector;
      private final boolean useUtc;
      private final TimeZone local = TimeZone.getDefault();
      private final boolean useProlepticGregorian;

      TimestampFromDateTreeReader(int columnId, TypeDescription readerType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, new TreeReaderFactory.DateTreeReader(columnId, context), context);
         this.useUtc = readerType.getCategory() == TypeDescription.Category.TIMESTAMP_INSTANT || context.getUseUTCTimestamp();
         this.useProlepticGregorian = context.useProlepticGregorian();
      }

      public void setConvertVectorElement(int elementNum) {
         long days = this.longColVector.vector[elementNum];
         long millis = days * 24L * 60L * 60L * 1000L;
         this.timestampColVector.time[elementNum] = this.useUtc ? millis : SerializationUtils.convertFromUtc(this.local, millis);
         this.timestampColVector.nanos[elementNum] = 0;
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.longColVector == null) {
            this.longColVector = new DateColumnVector(batchSize);
            this.timestampColVector = (TimestampColumnVector)previousVector;
         } else {
            this.longColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.longColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.longColVector, this.timestampColVector, batchSize);
         this.timestampColVector.changeCalendar(this.useProlepticGregorian, false);
      }
   }

   public static class DateFromStringGroupTreeReader extends ConvertTreeReader {
      private BytesColumnVector bytesColVector;
      private LongColumnVector longColVector;
      private DateColumnVector dateColumnVector;
      private final boolean useProlepticGregorian;

      DateFromStringGroupTreeReader(int columnId, TypeDescription fileType, TreeReaderFactory.Context context) throws IOException {
         super(columnId, getStringGroupTreeReader(columnId, fileType, context), context);
         this.useProlepticGregorian = context.useProlepticGregorian();
      }

      public void setConvertVectorElement(int elementNum) {
         String stringValue = SerializationUtils.bytesVectorToString(this.bytesColVector, elementNum);
         Integer dateValue = DateUtils.parseDate(stringValue, this.useProlepticGregorian);
         if (dateValue != null) {
            this.longColVector.vector[elementNum] = (long)dateValue;
         } else {
            this.longColVector.noNulls = false;
            this.longColVector.isNull[elementNum] = true;
         }

      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.bytesColVector == null) {
            this.bytesColVector = new BytesColumnVector(batchSize);
            this.longColVector = (LongColumnVector)previousVector;
            if (this.longColVector instanceof DateColumnVector) {
               this.dateColumnVector = (DateColumnVector)this.longColVector;
            } else {
               this.dateColumnVector = null;
               if (this.useProlepticGregorian) {
                  throw new IllegalArgumentException("Can't use LongColumnVector with proleptic Gregorian dates.");
               }
            }
         } else {
            this.bytesColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.bytesColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.bytesColVector, this.longColVector, batchSize);
         if (this.dateColumnVector != null) {
            this.dateColumnVector.changeCalendar(this.useProlepticGregorian, false);
         }

      }
   }

   public static class DateFromTimestampTreeReader extends ConvertTreeReader {
      private TimestampColumnVector timestampColVector;
      private LongColumnVector longColVector;
      private final ZoneId local;
      private final boolean useProlepticGregorian;

      DateFromTimestampTreeReader(int columnId, TreeReaderFactory.Context context, boolean instantType) throws IOException {
         super(columnId, new TreeReaderFactory.TimestampTreeReader(columnId, context, instantType), context);
         boolean useUtc = instantType || context.getUseUTCTimestamp();
         this.local = useUtc ? ZoneId.of("UTC") : ZoneId.systemDefault();
         this.useProlepticGregorian = context.useProlepticGregorian();
      }

      public void setConvertVectorElement(int elementNum) throws IOException {
         LocalDate day = LocalDate.from(Instant.ofEpochSecond(this.timestampColVector.time[elementNum] / 1000L, (long)this.timestampColVector.nanos[elementNum]).atZone(this.local));
         this.longColVector.vector[elementNum] = day.toEpochDay();
      }

      public void nextVector(ColumnVector previousVector, boolean[] isNull, int batchSize, FilterContext filterContext, TypeReader.ReadPhase readPhase) throws IOException {
         if (this.timestampColVector == null) {
            this.timestampColVector = new TimestampColumnVector(batchSize);
            this.longColVector = (LongColumnVector)previousVector;
            if (this.useProlepticGregorian && !(this.longColVector instanceof DateColumnVector)) {
               throw new IllegalArgumentException("Can't use LongColumnVector with proleptic Gregorian dates.");
            }
         } else {
            this.timestampColVector.ensureSize(batchSize, false);
         }

         this.fromReader.nextVector(this.timestampColVector, isNull, batchSize, filterContext, readPhase);
         this.convertVector(this.timestampColVector, this.longColVector, batchSize);
         if (this.longColVector instanceof DateColumnVector) {
            ((DateColumnVector)this.longColVector).changeCalendar(this.useProlepticGregorian, false);
         }

      }
   }
}
