package org.apache.parquet.schema;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.apache.parquet.Preconditions;

public abstract class LogicalTypeAnnotation {
   /** @deprecated */
   @Deprecated
   public abstract OriginalType toOriginalType();

   public abstract Optional accept(LogicalTypeAnnotationVisitor var1);

   abstract LogicalTypeToken getType();

   String typeParametersAsString() {
      return "";
   }

   boolean isValidColumnOrder(ColumnOrder columnOrder) {
      return columnOrder.getColumnOrderName() == ColumnOrder.ColumnOrderName.UNDEFINED || columnOrder.getColumnOrderName() == ColumnOrder.ColumnOrderName.TYPE_DEFINED_ORDER;
   }

   public String toString() {
      return this.getType() + this.typeParametersAsString();
   }

   PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
      throw new UnsupportedOperationException("Stringifier is not supported for the logical type: " + this);
   }

   public static LogicalTypeAnnotation fromOriginalType(OriginalType originalType, DecimalMetadata decimalMetadata) {
      if (originalType == null) {
         return null;
      } else {
         switch (originalType) {
            case UTF8:
               return stringType();
            case MAP:
               return mapType();
            case DECIMAL:
               int scale = decimalMetadata == null ? 0 : decimalMetadata.getScale();
               int precision = decimalMetadata == null ? 0 : decimalMetadata.getPrecision();
               return decimalType(scale, precision);
            case LIST:
               return listType();
            case DATE:
               return dateType();
            case INTERVAL:
               return LogicalTypeAnnotation.IntervalLogicalTypeAnnotation.getInstance();
            case TIMESTAMP_MILLIS:
               return timestampType(true, LogicalTypeAnnotation.TimeUnit.MILLIS);
            case TIMESTAMP_MICROS:
               return timestampType(true, LogicalTypeAnnotation.TimeUnit.MICROS);
            case TIME_MILLIS:
               return timeType(true, LogicalTypeAnnotation.TimeUnit.MILLIS);
            case TIME_MICROS:
               return timeType(true, LogicalTypeAnnotation.TimeUnit.MICROS);
            case UINT_8:
               return intType(8, false);
            case UINT_16:
               return intType(16, false);
            case UINT_32:
               return intType(32, false);
            case UINT_64:
               return intType(64, false);
            case INT_8:
               return intType(8, true);
            case INT_16:
               return intType(16, true);
            case INT_32:
               return intType(32, true);
            case INT_64:
               return intType(64, true);
            case ENUM:
               return enumType();
            case JSON:
               return jsonType();
            case BSON:
               return bsonType();
            case MAP_KEY_VALUE:
               return LogicalTypeAnnotation.MapKeyValueTypeAnnotation.getInstance();
            default:
               throw new RuntimeException("Can't convert original type to logical type, unknown original type " + originalType);
         }
      }
   }

   public static StringLogicalTypeAnnotation stringType() {
      return LogicalTypeAnnotation.StringLogicalTypeAnnotation.INSTANCE;
   }

   public static MapLogicalTypeAnnotation mapType() {
      return LogicalTypeAnnotation.MapLogicalTypeAnnotation.INSTANCE;
   }

   public static ListLogicalTypeAnnotation listType() {
      return LogicalTypeAnnotation.ListLogicalTypeAnnotation.INSTANCE;
   }

   public static EnumLogicalTypeAnnotation enumType() {
      return LogicalTypeAnnotation.EnumLogicalTypeAnnotation.INSTANCE;
   }

   public static DecimalLogicalTypeAnnotation decimalType(int scale, int precision) {
      return new DecimalLogicalTypeAnnotation(scale, precision);
   }

   public static DateLogicalTypeAnnotation dateType() {
      return LogicalTypeAnnotation.DateLogicalTypeAnnotation.INSTANCE;
   }

   public static TimeLogicalTypeAnnotation timeType(boolean isAdjustedToUTC, TimeUnit unit) {
      return new TimeLogicalTypeAnnotation(isAdjustedToUTC, unit);
   }

   public static TimestampLogicalTypeAnnotation timestampType(boolean isAdjustedToUTC, TimeUnit unit) {
      return new TimestampLogicalTypeAnnotation(isAdjustedToUTC, unit);
   }

   public static IntLogicalTypeAnnotation intType(int bitWidth) {
      return new IntLogicalTypeAnnotation(bitWidth, true);
   }

   public static IntLogicalTypeAnnotation intType(int bitWidth, boolean isSigned) {
      Preconditions.checkArgument(bitWidth == 8 || bitWidth == 16 || bitWidth == 32 || bitWidth == 64, "Invalid bit width for integer logical type, %s is not allowed, valid bit width values: 8, 16, 32, 64", bitWidth);
      return new IntLogicalTypeAnnotation(bitWidth, isSigned);
   }

   public static IntervalLogicalTypeAnnotation intervalType() {
      return new IntervalLogicalTypeAnnotation();
   }

   public static JsonLogicalTypeAnnotation jsonType() {
      return LogicalTypeAnnotation.JsonLogicalTypeAnnotation.INSTANCE;
   }

   public static BsonLogicalTypeAnnotation bsonType() {
      return LogicalTypeAnnotation.BsonLogicalTypeAnnotation.INSTANCE;
   }

   public static UUIDLogicalTypeAnnotation uuidType() {
      return LogicalTypeAnnotation.UUIDLogicalTypeAnnotation.INSTANCE;
   }

   public static Float16LogicalTypeAnnotation float16Type() {
      return LogicalTypeAnnotation.Float16LogicalTypeAnnotation.INSTANCE;
   }

   static enum LogicalTypeToken {
      MAP {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.mapType();
         }
      },
      LIST {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.listType();
         }
      },
      STRING {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.stringType();
         }
      },
      MAP_KEY_VALUE {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.MapKeyValueTypeAnnotation.getInstance();
         }
      },
      ENUM {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.enumType();
         }
      },
      DECIMAL {
         protected LogicalTypeAnnotation fromString(List params) {
            if (params.size() != 2) {
               throw new RuntimeException("Expecting 2 parameters for decimal logical type, got " + params.size());
            } else {
               return LogicalTypeAnnotation.decimalType(Integer.parseInt((String)params.get(1)), Integer.parseInt((String)params.get(0)));
            }
         }
      },
      DATE {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.dateType();
         }
      },
      TIME {
         protected LogicalTypeAnnotation fromString(List params) {
            if (params.size() != 2) {
               throw new RuntimeException("Expecting 2 parameters for time logical type, got " + params.size());
            } else {
               return LogicalTypeAnnotation.timeType(Boolean.parseBoolean((String)params.get(1)), LogicalTypeAnnotation.TimeUnit.valueOf((String)params.get(0)));
            }
         }
      },
      TIMESTAMP {
         protected LogicalTypeAnnotation fromString(List params) {
            if (params.size() != 2) {
               throw new RuntimeException("Expecting 2 parameters for timestamp logical type, got " + params.size());
            } else {
               return LogicalTypeAnnotation.timestampType(Boolean.parseBoolean((String)params.get(1)), LogicalTypeAnnotation.TimeUnit.valueOf((String)params.get(0)));
            }
         }
      },
      INTEGER {
         protected LogicalTypeAnnotation fromString(List params) {
            if (params.size() != 2) {
               throw new RuntimeException("Expecting 2 parameters for integer logical type, got " + params.size());
            } else {
               return LogicalTypeAnnotation.intType(Integer.parseInt((String)params.get(0)), Boolean.parseBoolean((String)params.get(1)));
            }
         }
      },
      JSON {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.jsonType();
         }
      },
      BSON {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.bsonType();
         }
      },
      UUID {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.uuidType();
         }
      },
      INTERVAL {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.IntervalLogicalTypeAnnotation.getInstance();
         }
      },
      FLOAT16 {
         protected LogicalTypeAnnotation fromString(List params) {
            return LogicalTypeAnnotation.float16Type();
         }
      };

      private LogicalTypeToken() {
      }

      protected abstract LogicalTypeAnnotation fromString(List var1);
   }

   public static class StringLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final StringLogicalTypeAnnotation INSTANCE = new StringLogicalTypeAnnotation();

      private StringLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.UTF8;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.STRING;
      }

      public boolean equals(Object obj) {
         return obj instanceof StringLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.UTF8_STRINGIFIER;
      }
   }

   public static class MapLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final MapLogicalTypeAnnotation INSTANCE = new MapLogicalTypeAnnotation();

      private MapLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.MAP;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.MAP;
      }

      public boolean equals(Object obj) {
         return obj instanceof MapLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }
   }

   public static class ListLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final ListLogicalTypeAnnotation INSTANCE = new ListLogicalTypeAnnotation();

      private ListLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.LIST;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.LIST;
      }

      public boolean equals(Object obj) {
         return obj instanceof ListLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }
   }

   public static class EnumLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final EnumLogicalTypeAnnotation INSTANCE = new EnumLogicalTypeAnnotation();

      private EnumLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.ENUM;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.ENUM;
      }

      public boolean equals(Object obj) {
         return obj instanceof EnumLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.UTF8_STRINGIFIER;
      }
   }

   public static class DecimalLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private final PrimitiveStringifier stringifier;
      private final int scale;
      private final int precision;

      private DecimalLogicalTypeAnnotation(int scale, int precision) {
         this.scale = scale;
         this.precision = precision;
         this.stringifier = PrimitiveStringifier.createDecimalStringifier(scale);
      }

      public int getPrecision() {
         return this.precision;
      }

      public int getScale() {
         return this.scale;
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.DECIMAL;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.DECIMAL;
      }

      protected String typeParametersAsString() {
         return "(" + this.precision + "," + this.scale + ")";
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof DecimalLogicalTypeAnnotation)) {
            return false;
         } else {
            DecimalLogicalTypeAnnotation other = (DecimalLogicalTypeAnnotation)obj;
            return this.scale == other.scale && this.precision == other.precision;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.scale, this.precision});
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return this.stringifier;
      }
   }

   public static class DateLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final DateLogicalTypeAnnotation INSTANCE = new DateLogicalTypeAnnotation();

      private DateLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.DATE;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.DATE;
      }

      public boolean equals(Object obj) {
         return obj instanceof DateLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.DATE_STRINGIFIER;
      }
   }

   public static enum TimeUnit {
      MILLIS,
      MICROS,
      NANOS;
   }

   public static class TimeLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private final boolean isAdjustedToUTC;
      private final TimeUnit unit;

      private TimeLogicalTypeAnnotation(boolean isAdjustedToUTC, TimeUnit unit) {
         this.isAdjustedToUTC = isAdjustedToUTC;
         this.unit = unit;
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         switch (this.unit) {
            case MILLIS:
               return OriginalType.TIME_MILLIS;
            case MICROS:
               return OriginalType.TIME_MICROS;
            default:
               return null;
         }
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.TIME;
      }

      protected String typeParametersAsString() {
         return "(" + this.unit + "," + this.isAdjustedToUTC + ")";
      }

      public TimeUnit getUnit() {
         return this.unit;
      }

      public boolean isAdjustedToUTC() {
         return this.isAdjustedToUTC;
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof TimeLogicalTypeAnnotation)) {
            return false;
         } else {
            TimeLogicalTypeAnnotation other = (TimeLogicalTypeAnnotation)obj;
            return this.isAdjustedToUTC == other.isAdjustedToUTC && this.unit == other.unit;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.isAdjustedToUTC, this.unit});
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         switch (this.unit) {
            case MILLIS:
            case MICROS:
               return this.isAdjustedToUTC ? PrimitiveStringifier.TIME_UTC_STRINGIFIER : PrimitiveStringifier.TIME_STRINGIFIER;
            case NANOS:
               return this.isAdjustedToUTC ? PrimitiveStringifier.TIME_NANOS_UTC_STRINGIFIER : PrimitiveStringifier.TIME_NANOS_STRINGIFIER;
            default:
               return super.valueStringifier(primitiveType);
         }
      }
   }

   public static class TimestampLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private final boolean isAdjustedToUTC;
      private final TimeUnit unit;

      private TimestampLogicalTypeAnnotation(boolean isAdjustedToUTC, TimeUnit unit) {
         this.isAdjustedToUTC = isAdjustedToUTC;
         this.unit = unit;
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         switch (this.unit) {
            case MILLIS:
               return OriginalType.TIMESTAMP_MILLIS;
            case MICROS:
               return OriginalType.TIMESTAMP_MICROS;
            default:
               return null;
         }
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.TIMESTAMP;
      }

      protected String typeParametersAsString() {
         return "(" + this.unit + "," + this.isAdjustedToUTC + ")";
      }

      public TimeUnit getUnit() {
         return this.unit;
      }

      public boolean isAdjustedToUTC() {
         return this.isAdjustedToUTC;
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof TimestampLogicalTypeAnnotation)) {
            return false;
         } else {
            TimestampLogicalTypeAnnotation other = (TimestampLogicalTypeAnnotation)obj;
            return this.isAdjustedToUTC == other.isAdjustedToUTC && this.unit == other.unit;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.isAdjustedToUTC, this.unit});
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         switch (this.unit) {
            case MILLIS:
               return this.isAdjustedToUTC ? PrimitiveStringifier.TIMESTAMP_MILLIS_UTC_STRINGIFIER : PrimitiveStringifier.TIMESTAMP_MILLIS_STRINGIFIER;
            case MICROS:
               return this.isAdjustedToUTC ? PrimitiveStringifier.TIMESTAMP_MICROS_UTC_STRINGIFIER : PrimitiveStringifier.TIMESTAMP_MICROS_STRINGIFIER;
            case NANOS:
               return this.isAdjustedToUTC ? PrimitiveStringifier.TIMESTAMP_NANOS_UTC_STRINGIFIER : PrimitiveStringifier.TIMESTAMP_NANOS_STRINGIFIER;
            default:
               return super.valueStringifier(primitiveType);
         }
      }
   }

   public static class IntLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final Set VALID_BIT_WIDTH = Collections.unmodifiableSet(new HashSet(Arrays.asList(8, 16, 32, 64)));
      private final int bitWidth;
      private final boolean isSigned;

      private IntLogicalTypeAnnotation(int bitWidth, boolean isSigned) {
         if (!VALID_BIT_WIDTH.contains(bitWidth)) {
            throw new IllegalArgumentException("Invalid integer bit width: " + bitWidth);
         } else {
            this.bitWidth = bitWidth;
            this.isSigned = isSigned;
         }
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         switch (this.bitWidth) {
            case 8:
               return this.isSigned ? OriginalType.INT_8 : OriginalType.UINT_8;
            case 16:
               return this.isSigned ? OriginalType.INT_16 : OriginalType.UINT_16;
            case 32:
               return this.isSigned ? OriginalType.INT_32 : OriginalType.UINT_32;
            case 64:
               return this.isSigned ? OriginalType.INT_64 : OriginalType.UINT_64;
            default:
               return null;
         }
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.INTEGER;
      }

      protected String typeParametersAsString() {
         return "(" + this.bitWidth + "," + this.isSigned + ")";
      }

      public int getBitWidth() {
         return this.bitWidth;
      }

      public boolean isSigned() {
         return this.isSigned;
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof IntLogicalTypeAnnotation)) {
            return false;
         } else {
            IntLogicalTypeAnnotation other = (IntLogicalTypeAnnotation)obj;
            return this.bitWidth == other.bitWidth && this.isSigned == other.isSigned;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.bitWidth, this.isSigned});
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return this.isSigned ? PrimitiveStringifier.DEFAULT_STRINGIFIER : PrimitiveStringifier.UNSIGNED_STRINGIFIER;
      }
   }

   public static class JsonLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final JsonLogicalTypeAnnotation INSTANCE = new JsonLogicalTypeAnnotation();

      private JsonLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.JSON;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.JSON;
      }

      public boolean equals(Object obj) {
         return obj instanceof JsonLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.UTF8_STRINGIFIER;
      }
   }

   public static class BsonLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final BsonLogicalTypeAnnotation INSTANCE = new BsonLogicalTypeAnnotation();

      private BsonLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.BSON;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.BSON;
      }

      public boolean equals(Object obj) {
         return obj instanceof BsonLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.DEFAULT_STRINGIFIER;
      }
   }

   public static class UUIDLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final UUIDLogicalTypeAnnotation INSTANCE = new UUIDLogicalTypeAnnotation();
      public static final int BYTES = 16;

      private UUIDLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return null;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.UUID;
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.UUID_STRINGIFIER;
      }
   }

   public static class Float16LogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static final Float16LogicalTypeAnnotation INSTANCE = new Float16LogicalTypeAnnotation();
      public static final int BYTES = 2;

      private Float16LogicalTypeAnnotation() {
      }

      public OriginalType toOriginalType() {
         return null;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.FLOAT16;
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.FLOAT16_STRINGIFIER;
      }
   }

   public static class IntervalLogicalTypeAnnotation extends LogicalTypeAnnotation {
      private static IntervalLogicalTypeAnnotation INSTANCE = new IntervalLogicalTypeAnnotation();

      public static LogicalTypeAnnotation getInstance() {
         return INSTANCE;
      }

      private IntervalLogicalTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.INTERVAL;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.INTERVAL;
      }

      public boolean equals(Object obj) {
         return obj instanceof IntervalLogicalTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }

      PrimitiveStringifier valueStringifier(PrimitiveType primitiveType) {
         return PrimitiveStringifier.INTERVAL_STRINGIFIER;
      }

      boolean isValidColumnOrder(ColumnOrder columnOrder) {
         return columnOrder.getColumnOrderName() == ColumnOrder.ColumnOrderName.UNDEFINED;
      }
   }

   public static class MapKeyValueTypeAnnotation extends LogicalTypeAnnotation {
      private static MapKeyValueTypeAnnotation INSTANCE = new MapKeyValueTypeAnnotation();

      public static MapKeyValueTypeAnnotation getInstance() {
         return INSTANCE;
      }

      private MapKeyValueTypeAnnotation() {
      }

      /** @deprecated */
      @Deprecated
      public OriginalType toOriginalType() {
         return OriginalType.MAP_KEY_VALUE;
      }

      public Optional accept(LogicalTypeAnnotationVisitor logicalTypeAnnotationVisitor) {
         return logicalTypeAnnotationVisitor.visit(this);
      }

      LogicalTypeToken getType() {
         return LogicalTypeAnnotation.LogicalTypeToken.MAP_KEY_VALUE;
      }

      public boolean equals(Object obj) {
         return obj instanceof MapKeyValueTypeAnnotation;
      }

      public int hashCode() {
         return this.getClass().hashCode();
      }
   }

   public interface LogicalTypeAnnotationVisitor {
      default Optional visit(StringLogicalTypeAnnotation stringLogicalType) {
         return Optional.empty();
      }

      default Optional visit(MapLogicalTypeAnnotation mapLogicalType) {
         return Optional.empty();
      }

      default Optional visit(ListLogicalTypeAnnotation listLogicalType) {
         return Optional.empty();
      }

      default Optional visit(EnumLogicalTypeAnnotation enumLogicalType) {
         return Optional.empty();
      }

      default Optional visit(DecimalLogicalTypeAnnotation decimalLogicalType) {
         return Optional.empty();
      }

      default Optional visit(DateLogicalTypeAnnotation dateLogicalType) {
         return Optional.empty();
      }

      default Optional visit(TimeLogicalTypeAnnotation timeLogicalType) {
         return Optional.empty();
      }

      default Optional visit(TimestampLogicalTypeAnnotation timestampLogicalType) {
         return Optional.empty();
      }

      default Optional visit(IntLogicalTypeAnnotation intLogicalType) {
         return Optional.empty();
      }

      default Optional visit(JsonLogicalTypeAnnotation jsonLogicalType) {
         return Optional.empty();
      }

      default Optional visit(BsonLogicalTypeAnnotation bsonLogicalType) {
         return Optional.empty();
      }

      default Optional visit(UUIDLogicalTypeAnnotation uuidLogicalType) {
         return Optional.empty();
      }

      default Optional visit(IntervalLogicalTypeAnnotation intervalLogicalType) {
         return Optional.empty();
      }

      default Optional visit(MapKeyValueTypeAnnotation mapKeyValueLogicalType) {
         return Optional.empty();
      }

      default Optional visit(Float16LogicalTypeAnnotation float16LogicalType) {
         return Optional.empty();
      }
   }
}
