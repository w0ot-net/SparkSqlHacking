package org.apache.parquet.schema;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import org.apache.parquet.Preconditions;
import org.apache.parquet.ShouldNeverHappenException;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.io.InvalidRecordException;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.io.api.RecordConsumer;

public final class PrimitiveType extends Type {
   private final PrimitiveTypeName primitive;
   private final int length;
   private final DecimalMetadata decimalMeta;
   private final ColumnOrder columnOrder;

   public PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, String name) {
      this(repetition, primitive, 0, name, (LogicalTypeAnnotation)((LogicalTypeAnnotation)null), (Type.ID)null, (ColumnOrder)null);
   }

   public PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, int length, String name) {
      this(repetition, primitive, length, name, (LogicalTypeAnnotation)((LogicalTypeAnnotation)null), (Type.ID)null, (ColumnOrder)null);
   }

   /** @deprecated */
   @Deprecated
   public PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, String name, OriginalType originalType) {
      this(repetition, primitive, 0, name, (OriginalType)originalType, (DecimalMetadata)null, (Type.ID)null);
   }

   /** @deprecated */
   @Deprecated
   public PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, int length, String name, OriginalType originalType) {
      this(repetition, primitive, length, name, (OriginalType)originalType, (DecimalMetadata)null, (Type.ID)null);
   }

   /** @deprecated */
   @Deprecated
   public PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, int length, String name, OriginalType originalType, DecimalMetadata decimalMeta, Type.ID id) {
      this(repetition, primitive, length, name, originalType, decimalMeta, id, (ColumnOrder)null);
   }

   PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, int length, String name, OriginalType originalType, DecimalMetadata decimalMeta, Type.ID id, ColumnOrder columnOrder) {
      super(name, repetition, originalType, decimalMeta, id);
      this.primitive = primitive;
      this.length = length;
      this.decimalMeta = decimalMeta;
      if (columnOrder == null) {
         columnOrder = primitive != PrimitiveType.PrimitiveTypeName.INT96 && originalType != OriginalType.INTERVAL ? ColumnOrder.typeDefined() : ColumnOrder.undefined();
      }

      this.columnOrder = this.requireValidColumnOrder(columnOrder);
   }

   PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, String name, LogicalTypeAnnotation logicalTypeAnnotation) {
      this(repetition, primitive, 0, name, (LogicalTypeAnnotation)logicalTypeAnnotation, (Type.ID)null, (ColumnOrder)null);
   }

   PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, int length, String name, LogicalTypeAnnotation logicalTypeAnnotation, Type.ID id) {
      this(repetition, primitive, length, name, (LogicalTypeAnnotation)logicalTypeAnnotation, (Type.ID)id, (ColumnOrder)null);
   }

   PrimitiveType(Type.Repetition repetition, PrimitiveTypeName primitive, int length, String name, LogicalTypeAnnotation logicalTypeAnnotation, Type.ID id, ColumnOrder columnOrder) {
      super(name, repetition, logicalTypeAnnotation, id);
      this.primitive = primitive;
      this.length = length;
      if (logicalTypeAnnotation instanceof LogicalTypeAnnotation.DecimalLogicalTypeAnnotation) {
         LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimal = (LogicalTypeAnnotation.DecimalLogicalTypeAnnotation)logicalTypeAnnotation;
         this.decimalMeta = new DecimalMetadata(decimal.getPrecision(), decimal.getScale());
      } else {
         this.decimalMeta = null;
      }

      if (columnOrder == null) {
         columnOrder = primitive != PrimitiveType.PrimitiveTypeName.INT96 && !(logicalTypeAnnotation instanceof LogicalTypeAnnotation.IntervalLogicalTypeAnnotation) ? ColumnOrder.typeDefined() : ColumnOrder.undefined();
      }

      this.columnOrder = this.requireValidColumnOrder(columnOrder);
   }

   private ColumnOrder requireValidColumnOrder(ColumnOrder columnOrder) {
      if (this.primitive == PrimitiveType.PrimitiveTypeName.INT96) {
         Preconditions.checkArgument(columnOrder.getColumnOrderName() == ColumnOrder.ColumnOrderName.UNDEFINED, "The column order %s is not supported by INT96", columnOrder);
      }

      if (this.getLogicalTypeAnnotation() != null) {
         Preconditions.checkArgument(this.getLogicalTypeAnnotation().isValidColumnOrder(columnOrder), "The column order %s is not supported by %s (%s)", columnOrder, this.primitive, this.getLogicalTypeAnnotation());
      }

      return columnOrder;
   }

   public PrimitiveType withId(int id) {
      return new PrimitiveType(this.getRepetition(), this.primitive, this.length, this.getName(), this.getLogicalTypeAnnotation(), new Type.ID(id), this.columnOrder);
   }

   public PrimitiveType withLogicalTypeAnnotation(LogicalTypeAnnotation logicalType) {
      return new PrimitiveType(this.getRepetition(), this.primitive, this.length, this.getName(), logicalType, this.getId());
   }

   public PrimitiveTypeName getPrimitiveTypeName() {
      return this.primitive;
   }

   public int getTypeLength() {
      return this.length;
   }

   /** @deprecated */
   @Deprecated
   public DecimalMetadata getDecimalMetadata() {
      return this.decimalMeta;
   }

   public boolean isPrimitive() {
      return true;
   }

   public void accept(TypeVisitor visitor) {
      visitor.visit(this);
   }

   public void writeToStringBuilder(StringBuilder sb, String indent) {
      sb.append(indent).append(this.getRepetition().name().toLowerCase(Locale.ENGLISH)).append(" ").append(this.primitive.name().toLowerCase());
      if (this.primitive == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY) {
         sb.append("(" + this.length + ")");
      }

      sb.append(" ").append(this.getName());
      if (this.getLogicalTypeAnnotation() != null) {
         sb.append(" (").append(this.getLogicalTypeAnnotation().toString()).append(")");
      }

      if (this.getId() != null) {
         sb.append(" = ").append(this.getId());
      }

   }

   /** @deprecated */
   @Deprecated
   protected int typeHashCode() {
      return this.hashCode();
   }

   /** @deprecated */
   @Deprecated
   protected boolean typeEquals(Type other) {
      return this.equals(other);
   }

   protected boolean equals(Type other) {
      if (!other.isPrimitive()) {
         return false;
      } else {
         PrimitiveType otherPrimitive = other.asPrimitiveType();
         return super.equals(other) && this.primitive == otherPrimitive.getPrimitiveTypeName() && this.length == otherPrimitive.length && this.columnOrder.equals(otherPrimitive.columnOrder) && this.eqOrBothNull(this.decimalMeta, otherPrimitive.decimalMeta);
      }
   }

   public int hashCode() {
      int hash = super.hashCode();
      hash = hash * 31 + this.primitive.hashCode();
      hash = hash * 31 + this.length;
      hash = hash * 31 + this.columnOrder.hashCode();
      if (this.decimalMeta != null) {
         hash = hash * 31 + this.decimalMeta.hashCode();
      }

      return hash;
   }

   public int getMaxRepetitionLevel(String[] path, int i) {
      if (path.length != i) {
         throw new InvalidRecordException("Arrived at primitive node, path invalid");
      } else {
         return this.isRepetition(Type.Repetition.REPEATED) ? 1 : 0;
      }
   }

   public int getMaxDefinitionLevel(String[] path, int i) {
      if (path.length != i) {
         throw new InvalidRecordException("Arrived at primitive node, path invalid");
      } else {
         return this.isRepetition(Type.Repetition.REQUIRED) ? 0 : 1;
      }
   }

   public Type getType(String[] path, int i) {
      if (path.length != i) {
         throw new InvalidRecordException("Arrived at primitive node at index " + i + " , path invalid: " + Arrays.toString(path));
      } else {
         return this;
      }
   }

   protected List getPaths(int depth) {
      return Collections.singletonList(new String[depth]);
   }

   void checkContains(Type subType) {
      super.checkContains(subType);
      if (!subType.isPrimitive()) {
         throw new InvalidRecordException(subType + " found: expected " + this);
      } else {
         PrimitiveType primitiveType = subType.asPrimitiveType();
         if (this.primitive != primitiveType.primitive) {
            throw new InvalidRecordException(subType + " found: expected " + this);
         }
      }
   }

   public Object convert(List path, TypeConverter converter) {
      return converter.convertPrimitiveType(path, this);
   }

   protected boolean containsPath(String[] path, int depth) {
      return path.length == depth;
   }

   protected Type union(Type toMerge) {
      return this.union(toMerge, true);
   }

   private void reportSchemaMergeError(Type toMerge) {
      throw new IncompatibleSchemaModificationException("can not merge type " + toMerge + " into " + this);
   }

   private void reportSchemaMergeErrorWithColumnOrder(Type toMerge) {
      throw new IncompatibleSchemaModificationException("can not merge type " + toMerge + " with column order " + toMerge.asPrimitiveType().columnOrder() + " into " + this + " with column order " + this.columnOrder());
   }

   protected Type union(Type toMerge, boolean strict) {
      if (!toMerge.isPrimitive()) {
         this.reportSchemaMergeError(toMerge);
      }

      if (strict) {
         if (!this.primitive.equals(toMerge.asPrimitiveType().getPrimitiveTypeName()) || !Objects.equals(this.getLogicalTypeAnnotation(), toMerge.getLogicalTypeAnnotation())) {
            this.reportSchemaMergeError(toMerge);
         }

         int toMergeLength = toMerge.asPrimitiveType().getTypeLength();
         if (this.primitive == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY && this.length != toMergeLength) {
            this.reportSchemaMergeError(toMerge);
         }

         if (!this.columnOrder().equals(toMerge.asPrimitiveType().columnOrder())) {
            this.reportSchemaMergeErrorWithColumnOrder(toMerge);
         }
      }

      Type.Repetition repetition = Type.Repetition.leastRestrictive(this.getRepetition(), toMerge.getRepetition());
      Types.PrimitiveBuilder<PrimitiveType> builder = Types.primitive(this.primitive, repetition);
      if (PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY == this.primitive) {
         builder.length(this.length);
      }

      return (Type)((Types.PrimitiveBuilder)builder.as(this.getLogicalTypeAnnotation())).named(this.getName());
   }

   public PrimitiveComparator comparator() {
      return this.getPrimitiveTypeName().comparator(this.getLogicalTypeAnnotation());
   }

   public ColumnOrder columnOrder() {
      return this.columnOrder;
   }

   public PrimitiveStringifier stringifier() {
      LogicalTypeAnnotation logicalTypeAnnotation = this.getLogicalTypeAnnotation();
      return logicalTypeAnnotation == null ? PrimitiveStringifier.DEFAULT_STRINGIFIER : logicalTypeAnnotation.valueStringifier(this);
   }

   public static enum PrimitiveTypeName {
      INT64("getLong", Long.TYPE) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getLong());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addLong(columnReader.getLong());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addLong(columnReader.getLong());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertINT64(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return logicalType == null ? PrimitiveComparator.SIGNED_INT64_COMPARATOR : (PrimitiveComparator)logicalType.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
               public Optional visit(LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
                  return intLogicalType.isSigned() ? Optional.of(PrimitiveComparator.SIGNED_INT64_COMPARATOR) : Optional.of(PrimitiveComparator.UNSIGNED_INT64_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
                  return Optional.of(PrimitiveComparator.SIGNED_INT64_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
                  return Optional.of(PrimitiveComparator.SIGNED_INT64_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
                  return Optional.of(PrimitiveComparator.SIGNED_INT64_COMPARATOR);
               }
            }).orElseThrow(() -> new ShouldNeverHappenException("No comparator logic implemented for INT64 logical type: " + logicalType));
         }
      },
      INT32("getInteger", Integer.TYPE) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getInteger());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addInteger(columnReader.getInteger());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addInt(columnReader.getInteger());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertINT32(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return logicalType == null ? PrimitiveComparator.SIGNED_INT32_COMPARATOR : (PrimitiveComparator)logicalType.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
               public Optional visit(LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
                  if (intLogicalType.getBitWidth() == 64) {
                     return Optional.empty();
                  } else {
                     return intLogicalType.isSigned() ? Optional.of(PrimitiveComparator.SIGNED_INT32_COMPARATOR) : Optional.of(PrimitiveComparator.UNSIGNED_INT32_COMPARATOR);
                  }
               }

               public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
                  return Optional.of(PrimitiveComparator.SIGNED_INT32_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
                  return Optional.of(PrimitiveComparator.SIGNED_INT32_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
                  return timeLogicalType.getUnit() == LogicalTypeAnnotation.TimeUnit.MILLIS ? Optional.of(PrimitiveComparator.SIGNED_INT32_COMPARATOR) : Optional.empty();
               }
            }).orElseThrow(() -> new ShouldNeverHappenException("No comparator logic implemented for INT32 logical type: " + logicalType));
         }
      },
      BOOLEAN("getBoolean", Boolean.TYPE) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getBoolean());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addBoolean(columnReader.getBoolean());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addBoolean(columnReader.getBoolean());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertBOOLEAN(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return PrimitiveComparator.BOOLEAN_COMPARATOR;
         }
      },
      BINARY("getBinary", Binary.class) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getBinary());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addBinary(columnReader.getBinary());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addBinary(columnReader.getBinary());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertBINARY(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return logicalType == null ? PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR : (PrimitiveComparator)logicalType.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
               public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
                  return Optional.of(PrimitiveComparator.BINARY_AS_SIGNED_INTEGER_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
                  return Optional.of(PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
                  return Optional.of(PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
                  return Optional.of(PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
                  return Optional.of(PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR);
               }
            }).orElseThrow(() -> new ShouldNeverHappenException("No comparator logic implemented for BINARY logical type: " + logicalType));
         }
      },
      FLOAT("getFloat", Float.TYPE) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getFloat());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addFloat(columnReader.getFloat());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addFloat(columnReader.getFloat());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertFLOAT(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return PrimitiveComparator.FLOAT_COMPARATOR;
         }
      },
      DOUBLE("getDouble", Double.TYPE) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getDouble());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addDouble(columnReader.getDouble());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addDouble(columnReader.getDouble());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertDOUBLE(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return PrimitiveComparator.DOUBLE_COMPARATOR;
         }
      },
      INT96("getBinary", Binary.class) {
         public String toString(ColumnReader columnReader) {
            return Arrays.toString(columnReader.getBinary().getBytesUnsafe());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addBinary(columnReader.getBinary());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addBinary(columnReader.getBinary());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertINT96(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return PrimitiveComparator.BINARY_AS_SIGNED_INTEGER_COMPARATOR;
         }
      },
      FIXED_LEN_BYTE_ARRAY("getBinary", Binary.class) {
         public String toString(ColumnReader columnReader) {
            return String.valueOf(columnReader.getBinary());
         }

         public void addValueToRecordConsumer(RecordConsumer recordConsumer, ColumnReader columnReader) {
            recordConsumer.addBinary(columnReader.getBinary());
         }

         public void addValueToPrimitiveConverter(PrimitiveConverter primitiveConverter, ColumnReader columnReader) {
            primitiveConverter.addBinary(columnReader.getBinary());
         }

         public Object convert(PrimitiveTypeNameConverter converter) throws Exception {
            return converter.convertFIXED_LEN_BYTE_ARRAY(this);
         }

         PrimitiveComparator comparator(LogicalTypeAnnotation logicalType) {
            return logicalType == null ? PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR : (PrimitiveComparator)logicalType.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
               public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
                  return Optional.of(PrimitiveComparator.BINARY_AS_SIGNED_INTEGER_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
                  return Optional.of(PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.UUIDLogicalTypeAnnotation uuidLogicalType) {
                  return Optional.of(PrimitiveComparator.UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR);
               }

               public Optional visit(LogicalTypeAnnotation.Float16LogicalTypeAnnotation float16LogicalType) {
                  return Optional.of(PrimitiveComparator.BINARY_AS_FLOAT16_COMPARATOR);
               }
            }).orElseThrow(() -> new ShouldNeverHappenException("No comparator logic implemented for FIXED_LEN_BYTE_ARRAY logical type: " + logicalType));
         }
      };

      public final String getMethod;
      public final Class javaType;

      private PrimitiveTypeName(String getMethod, Class javaType) {
         this.getMethod = getMethod;
         this.javaType = javaType;
      }

      public abstract String toString(ColumnReader var1);

      public abstract void addValueToRecordConsumer(RecordConsumer var1, ColumnReader var2);

      public abstract void addValueToPrimitiveConverter(PrimitiveConverter var1, ColumnReader var2);

      public abstract Object convert(PrimitiveTypeNameConverter var1) throws Exception;

      abstract PrimitiveComparator comparator(LogicalTypeAnnotation var1);
   }

   public interface PrimitiveTypeNameConverter {
      Object convertFLOAT(PrimitiveTypeName var1) throws Exception;

      Object convertDOUBLE(PrimitiveTypeName var1) throws Exception;

      Object convertINT32(PrimitiveTypeName var1) throws Exception;

      Object convertINT64(PrimitiveTypeName var1) throws Exception;

      Object convertINT96(PrimitiveTypeName var1) throws Exception;

      Object convertFIXED_LEN_BYTE_ARRAY(PrimitiveTypeName var1) throws Exception;

      Object convertBOOLEAN(PrimitiveTypeName var1) throws Exception;

      Object convertBINARY(PrimitiveTypeName var1) throws Exception;
   }
}
