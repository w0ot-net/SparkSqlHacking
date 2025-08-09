package org.apache.parquet.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.parquet.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Types {
   private static final int NOT_SET = 0;

   public static MessageTypeBuilder buildMessage() {
      return new MessageTypeBuilder();
   }

   public static PrimitiveBuilder primitive(PrimitiveType.PrimitiveTypeName type, Type.Repetition repetition) {
      return (PrimitiveBuilder)(new PrimitiveBuilder(PrimitiveType.class, type)).repetition(repetition);
   }

   public static PrimitiveBuilder required(PrimitiveType.PrimitiveTypeName type) {
      return (PrimitiveBuilder)(new PrimitiveBuilder(PrimitiveType.class, type)).repetition(Type.Repetition.REQUIRED);
   }

   public static PrimitiveBuilder optional(PrimitiveType.PrimitiveTypeName type) {
      return (PrimitiveBuilder)(new PrimitiveBuilder(PrimitiveType.class, type)).repetition(Type.Repetition.OPTIONAL);
   }

   public static PrimitiveBuilder repeated(PrimitiveType.PrimitiveTypeName type) {
      return (PrimitiveBuilder)(new PrimitiveBuilder(PrimitiveType.class, type)).repetition(Type.Repetition.REPEATED);
   }

   public static GroupBuilder buildGroup(Type.Repetition repetition) {
      return (GroupBuilder)(new GroupBuilder(GroupType.class)).repetition(repetition);
   }

   public static GroupBuilder requiredGroup() {
      return (GroupBuilder)(new GroupBuilder(GroupType.class)).repetition(Type.Repetition.REQUIRED);
   }

   public static GroupBuilder optionalGroup() {
      return (GroupBuilder)(new GroupBuilder(GroupType.class)).repetition(Type.Repetition.OPTIONAL);
   }

   public static GroupBuilder repeatedGroup() {
      return (GroupBuilder)(new GroupBuilder(GroupType.class)).repetition(Type.Repetition.REPEATED);
   }

   public static MapBuilder map(Type.Repetition repetition) {
      return (MapBuilder)(new MapBuilder(GroupType.class)).repetition(repetition);
   }

   public static MapBuilder requiredMap() {
      return map(Type.Repetition.REQUIRED);
   }

   public static MapBuilder optionalMap() {
      return map(Type.Repetition.OPTIONAL);
   }

   public static ListBuilder list(Type.Repetition repetition) {
      return (ListBuilder)(new ListBuilder(GroupType.class)).repetition(repetition);
   }

   public static ListBuilder requiredList() {
      return list(Type.Repetition.REQUIRED);
   }

   public static ListBuilder optionalList() {
      return list(Type.Repetition.OPTIONAL);
   }

   public abstract static class Builder {
      protected final Object parent;
      protected final Class returnClass;
      protected Type.Repetition repetition = null;
      protected LogicalTypeAnnotation logicalTypeAnnotation = null;
      protected Type.ID id = null;
      private boolean repetitionAlreadySet = false;
      protected boolean newLogicalTypeSet;

      protected Builder(Object parent) {
         this.parent = parent;
         this.returnClass = null;
      }

      protected Builder(Class returnClass) {
         Preconditions.checkArgument(Type.class.isAssignableFrom(returnClass), "The requested return class must extend Type");
         this.returnClass = returnClass;
         this.parent = null;
      }

      protected abstract Builder self();

      protected final Builder repetition(Type.Repetition repetition) {
         Preconditions.checkArgument(!this.repetitionAlreadySet, "Repetition has already been set");
         this.repetition = (Type.Repetition)Objects.requireNonNull(repetition, "Repetition cannot be null");
         this.repetitionAlreadySet = true;
         return this.self();
      }

      /** @deprecated */
      @Deprecated
      public Builder as(OriginalType type) {
         this.logicalTypeAnnotation = LogicalTypeAnnotation.fromOriginalType(type, (DecimalMetadata)null);
         return this.self();
      }

      public Builder as(LogicalTypeAnnotation type) {
         this.logicalTypeAnnotation = type;
         this.newLogicalTypeSet = true;
         return this.self();
      }

      public Builder id(int id) {
         this.id = new Type.ID(id);
         return this.self();
      }

      protected abstract Type build(String var1);

      public Object named(String name) {
         Objects.requireNonNull(name, "Name is required");
         Objects.requireNonNull(this.repetition, "Repetition is required");
         Type type = this.build(name);
         if (this.parent != null) {
            if (BaseGroupBuilder.class.isAssignableFrom(this.parent.getClass())) {
               ((BaseGroupBuilder)BaseGroupBuilder.class.cast(this.parent)).addField(type);
            }

            return this.parent;
         } else if (this.returnClass != null) {
            return this.returnClass.cast(type);
         } else {
            throw new IllegalStateException("[BUG] Parent and return type are null: must override named");
         }
      }

      protected OriginalType getOriginalType() {
         return this.logicalTypeAnnotation == null ? null : this.logicalTypeAnnotation.toOriginalType();
      }
   }

   public abstract static class BasePrimitiveBuilder extends Builder {
      private static final Logger LOGGER = LoggerFactory.getLogger(BasePrimitiveBuilder.class);
      private static final long MAX_PRECISION_INT32 = maxPrecision(4);
      private static final long MAX_PRECISION_INT64 = maxPrecision(8);
      private static final String LOGICAL_TYPES_DOC_URL = "https://github.com/apache/parquet-format/blob/master/LogicalTypes.md";
      private final PrimitiveType.PrimitiveTypeName primitiveType;
      private int length;
      private int precision;
      private int scale;
      private ColumnOrder columnOrder;
      private boolean precisionAlreadySet;
      private boolean scaleAlreadySet;

      private BasePrimitiveBuilder(Object parent, PrimitiveType.PrimitiveTypeName type) {
         super(parent);
         this.length = 0;
         this.precision = 0;
         this.scale = 0;
         this.primitiveType = type;
      }

      private BasePrimitiveBuilder(Class returnType, PrimitiveType.PrimitiveTypeName type) {
         super(returnType);
         this.length = 0;
         this.precision = 0;
         this.scale = 0;
         this.primitiveType = type;
      }

      protected abstract BasePrimitiveBuilder self();

      public BasePrimitiveBuilder length(int length) {
         this.length = length;
         return this.self();
      }

      /** @deprecated */
      @Deprecated
      public BasePrimitiveBuilder precision(int precision) {
         this.precision = precision;
         this.precisionAlreadySet = true;
         return this.self();
      }

      /** @deprecated */
      @Deprecated
      public BasePrimitiveBuilder scale(int scale) {
         this.scale = scale;
         this.scaleAlreadySet = true;
         return this.self();
      }

      public BasePrimitiveBuilder columnOrder(ColumnOrder columnOrder) {
         this.columnOrder = columnOrder;
         return this.self();
      }

      protected PrimitiveType build(String name) {
         if (this.length == 0 && this.logicalTypeAnnotation instanceof LogicalTypeAnnotation.UUIDLogicalTypeAnnotation) {
            this.length = 16;
         }

         if (PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY == this.primitiveType) {
            Preconditions.checkArgument(this.length > 0, "Invalid FIXED_LEN_BYTE_ARRAY length: %s", this.length);
         }

         final DecimalMetadata meta = this.decimalMetadata();
         if (this.logicalTypeAnnotation != null) {
            this.logicalTypeAnnotation.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
               public Optional visit(LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
                  return this.checkBinaryPrimitiveType(stringLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
                  return this.checkBinaryPrimitiveType(jsonLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
                  return this.checkBinaryPrimitiveType(bsonLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.UUIDLogicalTypeAnnotation uuidLogicalType) {
                  return this.checkFixedPrimitiveType(16, uuidLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.Float16LogicalTypeAnnotation float16LogicalType) {
                  return this.checkFixedPrimitiveType(2, float16LogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
                  Preconditions.checkState(BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.INT32 || BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.INT64 || BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.BINARY || BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY, "DECIMAL can only annotate INT32, INT64, BINARY, and FIXED");
                  if (BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.INT32) {
                     Preconditions.checkState((long)meta.getPrecision() <= Types.BasePrimitiveBuilder.MAX_PRECISION_INT32, "INT32 cannot store %s digits (max %s)", meta.getPrecision(), Types.BasePrimitiveBuilder.MAX_PRECISION_INT32);
                  } else if (BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.INT64) {
                     Preconditions.checkState((long)meta.getPrecision() <= Types.BasePrimitiveBuilder.MAX_PRECISION_INT64, "INT64 cannot store %s digits (max %s)", meta.getPrecision(), Types.BasePrimitiveBuilder.MAX_PRECISION_INT64);
                     if ((long)meta.getPrecision() <= Types.BasePrimitiveBuilder.MAX_PRECISION_INT32) {
                        Types.BasePrimitiveBuilder.LOGGER.warn("Decimal with {} digits is stored in an INT64, but fits in an INT32. See {}.", BasePrimitiveBuilder.this.precision, "https://github.com/apache/parquet-format/blob/master/LogicalTypes.md");
                     }
                  } else if (BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY) {
                     Preconditions.checkState((long)meta.getPrecision() <= Types.BasePrimitiveBuilder.maxPrecision(BasePrimitiveBuilder.this.length), "FIXED(%s) cannot store %s digits (max %s)", BasePrimitiveBuilder.this.length, meta.getPrecision(), Types.BasePrimitiveBuilder.maxPrecision(BasePrimitiveBuilder.this.length));
                  }

                  return Optional.of(true);
               }

               public Optional visit(LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
                  return this.checkInt32PrimitiveType(dateLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
                  LogicalTypeAnnotation.TimeUnit unit = timeLogicalType.getUnit();
                  switch (unit) {
                     case MILLIS:
                        this.checkInt32PrimitiveType(timeLogicalType);
                        break;
                     case MICROS:
                     case NANOS:
                        this.checkInt64PrimitiveType(timeLogicalType);
                        break;
                     default:
                        throw new RuntimeException("Invalid time unit: " + unit);
                  }

                  return Optional.of(true);
               }

               public Optional visit(LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
                  int bitWidth = intLogicalType.getBitWidth();
                  switch (bitWidth) {
                     case 8:
                     case 16:
                     case 32:
                        this.checkInt32PrimitiveType(intLogicalType);
                        break;
                     case 64:
                        this.checkInt64PrimitiveType(intLogicalType);
                        break;
                     default:
                        throw new RuntimeException("Invalid bit width: " + bitWidth);
                  }

                  return Optional.of(true);
               }

               public Optional visit(LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
                  return this.checkInt64PrimitiveType(timestampLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
                  return this.checkFixedPrimitiveType(12, intervalLogicalType);
               }

               public Optional visit(LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
                  return this.checkBinaryPrimitiveType(enumLogicalType);
               }

               private Optional checkFixedPrimitiveType(int l, LogicalTypeAnnotation logicalTypeAnnotation) {
                  Preconditions.checkState(BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY && BasePrimitiveBuilder.this.length == l, "%s can only annotate FIXED_LEN_BYTE_ARRAY(%s)", logicalTypeAnnotation, l);
                  return Optional.of(true);
               }

               private Optional checkBinaryPrimitiveType(LogicalTypeAnnotation logicalTypeAnnotation) {
                  Preconditions.checkState(BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.BINARY, "%s can only annotate BINARY", logicalTypeAnnotation);
                  return Optional.of(true);
               }

               private Optional checkInt32PrimitiveType(LogicalTypeAnnotation logicalTypeAnnotation) {
                  Preconditions.checkState(BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.INT32, "%s can only annotate INT32", logicalTypeAnnotation);
                  return Optional.of(true);
               }

               private Optional checkInt64PrimitiveType(LogicalTypeAnnotation logicalTypeAnnotation) {
                  Preconditions.checkState(BasePrimitiveBuilder.this.primitiveType == PrimitiveType.PrimitiveTypeName.INT64, "%s can only annotate INT64", logicalTypeAnnotation);
                  return Optional.of(true);
               }
            }).orElseThrow(() -> new IllegalStateException(this.logicalTypeAnnotation + " can not be applied to a primitive type"));
         }

         return this.newLogicalTypeSet ? new PrimitiveType(this.repetition, this.primitiveType, this.length, name, this.logicalTypeAnnotation, this.id, this.columnOrder) : new PrimitiveType(this.repetition, this.primitiveType, this.length, name, this.getOriginalType(), meta, this.id, this.columnOrder);
      }

      private static long maxPrecision(int numBytes) {
         return Math.round(Math.floor(Math.log10(Math.pow((double)2.0F, (double)(8 * numBytes - 1)) - (double)1.0F)));
      }

      protected DecimalMetadata decimalMetadata() {
         DecimalMetadata meta = null;
         if (this.logicalTypeAnnotation instanceof LogicalTypeAnnotation.DecimalLogicalTypeAnnotation) {
            LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalType = (LogicalTypeAnnotation.DecimalLogicalTypeAnnotation)this.logicalTypeAnnotation;
            if (this.newLogicalTypeSet) {
               if (this.scaleAlreadySet) {
                  Preconditions.checkArgument(this.scale == decimalType.getScale(), "Decimal scale should match with the scale of the logical type");
               }

               if (this.precisionAlreadySet) {
                  Preconditions.checkArgument(this.precision == decimalType.getPrecision(), "Decimal precision should match with the precision of the logical type");
               }

               this.scale = decimalType.getScale();
               this.precision = decimalType.getPrecision();
            }

            Preconditions.checkArgument(this.precision > 0, "Invalid DECIMAL precision: %s", this.precision);
            Preconditions.checkArgument(this.scale >= 0, "Invalid DECIMAL scale: %s", this.scale);
            Preconditions.checkArgument(this.scale <= this.precision, "Invalid DECIMAL scale: cannot be greater than precision");
            meta = new DecimalMetadata(this.precision, this.scale);
         }

         return meta;
      }
   }

   public static class PrimitiveBuilder extends BasePrimitiveBuilder {
      private PrimitiveBuilder(Object parent, PrimitiveType.PrimitiveTypeName type) {
         super((Object)parent, type, null);
      }

      private PrimitiveBuilder(Class returnType, PrimitiveType.PrimitiveTypeName type) {
         super((Class)returnType, type, null);
      }

      protected PrimitiveBuilder self() {
         return this;
      }
   }

   public abstract static class BaseGroupBuilder extends Builder {
      protected final List fields;

      private BaseGroupBuilder(Object parent) {
         super(parent);
         this.fields = new ArrayList();
      }

      private BaseGroupBuilder(Class returnType) {
         super(returnType);
         this.fields = new ArrayList();
      }

      protected abstract BaseGroupBuilder self();

      public PrimitiveBuilder primitive(PrimitiveType.PrimitiveTypeName type, Type.Repetition repetition) {
         return (PrimitiveBuilder)(new PrimitiveBuilder(this.self(), type)).repetition(repetition);
      }

      public PrimitiveBuilder required(PrimitiveType.PrimitiveTypeName type) {
         return (PrimitiveBuilder)(new PrimitiveBuilder(this.self(), type)).repetition(Type.Repetition.REQUIRED);
      }

      public PrimitiveBuilder optional(PrimitiveType.PrimitiveTypeName type) {
         return (PrimitiveBuilder)(new PrimitiveBuilder(this.self(), type)).repetition(Type.Repetition.OPTIONAL);
      }

      public PrimitiveBuilder repeated(PrimitiveType.PrimitiveTypeName type) {
         return (PrimitiveBuilder)(new PrimitiveBuilder(this.self(), type)).repetition(Type.Repetition.REPEATED);
      }

      public GroupBuilder group(Type.Repetition repetition) {
         return (GroupBuilder)(new GroupBuilder(this.self())).repetition(repetition);
      }

      public GroupBuilder requiredGroup() {
         return (GroupBuilder)(new GroupBuilder(this.self())).repetition(Type.Repetition.REQUIRED);
      }

      public GroupBuilder optionalGroup() {
         return (GroupBuilder)(new GroupBuilder(this.self())).repetition(Type.Repetition.OPTIONAL);
      }

      public GroupBuilder repeatedGroup() {
         return (GroupBuilder)(new GroupBuilder(this.self())).repetition(Type.Repetition.REPEATED);
      }

      public BaseGroupBuilder addField(Type type) {
         this.fields.add(type);
         return this.self();
      }

      public BaseGroupBuilder addFields(Type... types) {
         Collections.addAll(this.fields, types);
         return this.self();
      }

      protected GroupType build(String name) {
         return this.newLogicalTypeSet ? new GroupType(this.repetition, name, this.logicalTypeAnnotation, this.fields, this.id) : new GroupType(this.repetition, name, this.getOriginalType(), this.fields, this.id);
      }

      public MapBuilder map(Type.Repetition repetition) {
         return (MapBuilder)(new MapBuilder(this.self())).repetition(repetition);
      }

      public MapBuilder requiredMap() {
         return (MapBuilder)(new MapBuilder(this.self())).repetition(Type.Repetition.REQUIRED);
      }

      public MapBuilder optionalMap() {
         return (MapBuilder)(new MapBuilder(this.self())).repetition(Type.Repetition.OPTIONAL);
      }

      public ListBuilder list(Type.Repetition repetition) {
         return (ListBuilder)(new ListBuilder(this.self())).repetition(repetition);
      }

      public ListBuilder requiredList() {
         return this.list(Type.Repetition.REQUIRED);
      }

      public ListBuilder optionalList() {
         return this.list(Type.Repetition.OPTIONAL);
      }
   }

   public static class GroupBuilder extends BaseGroupBuilder {
      private GroupBuilder(Object parent) {
         super((Object)parent, null);
      }

      private GroupBuilder(Class returnType) {
         super((Class)returnType, null);
      }

      protected GroupBuilder self() {
         return this;
      }
   }

   public abstract static class BaseMapBuilder extends Builder {
      private static final Type STRING_KEY;
      private Type keyType;
      private Type valueType;

      protected void setKeyType(Type keyType) {
         Preconditions.checkState(this.keyType == null, "Only one key type can be built with a MapBuilder");
         this.keyType = keyType;
      }

      protected void setValueType(Type valueType) {
         Preconditions.checkState(this.valueType == null, "Only one key type can be built with a ValueBuilder");
         this.valueType = valueType;
      }

      public BaseMapBuilder(Object parent) {
         super(parent);
         this.keyType = null;
         this.valueType = null;
      }

      private BaseMapBuilder(Class returnType) {
         super(returnType);
         this.keyType = null;
         this.valueType = null;
      }

      protected abstract BaseMapBuilder self();

      public KeyBuilder key(PrimitiveType.PrimitiveTypeName type) {
         return new KeyBuilder(this.self(), type);
      }

      public BaseMapBuilder key(Type type) {
         this.setKeyType(type);
         return this.self();
      }

      public GroupKeyBuilder groupKey() {
         return new GroupKeyBuilder(this.self());
      }

      public ValueBuilder value(PrimitiveType.PrimitiveTypeName type, Type.Repetition repetition) {
         return (ValueBuilder)(new ValueBuilder(this.self(), type)).repetition(repetition);
      }

      public ValueBuilder requiredValue(PrimitiveType.PrimitiveTypeName type) {
         return this.value(type, Type.Repetition.REQUIRED);
      }

      public ValueBuilder optionalValue(PrimitiveType.PrimitiveTypeName type) {
         return this.value(type, Type.Repetition.OPTIONAL);
      }

      public GroupValueBuilder groupValue(Type.Repetition repetition) {
         return (GroupValueBuilder)(new GroupValueBuilder(this.self())).repetition(repetition);
      }

      public GroupValueBuilder requiredGroupValue() {
         return this.groupValue(Type.Repetition.REQUIRED);
      }

      public GroupValueBuilder optionalGroupValue() {
         return this.groupValue(Type.Repetition.OPTIONAL);
      }

      public MapValueBuilder mapValue(Type.Repetition repetition) {
         return (MapValueBuilder)(new MapValueBuilder(this.self())).repetition(repetition);
      }

      public MapValueBuilder requiredMapValue() {
         return this.mapValue(Type.Repetition.REQUIRED);
      }

      public MapValueBuilder optionalMapValue() {
         return this.mapValue(Type.Repetition.OPTIONAL);
      }

      public ListValueBuilder listValue(Type.Repetition repetition) {
         return (ListValueBuilder)(new ListValueBuilder(this.self())).repetition(repetition);
      }

      public ListValueBuilder requiredListValue() {
         return this.listValue(Type.Repetition.REQUIRED);
      }

      public ListValueBuilder optionalListValue() {
         return this.listValue(Type.Repetition.OPTIONAL);
      }

      public BaseMapBuilder value(Type type) {
         this.setValueType(type);
         return this.self();
      }

      protected Type build(String name) {
         Preconditions.checkState(this.logicalTypeAnnotation == null, "MAP is already a logical type and can't be changed.");
         if (this.keyType == null) {
            this.keyType = STRING_KEY;
         }

         GroupBuilder<GroupType> builder = (GroupBuilder)Types.buildGroup(this.repetition).as(LogicalTypeAnnotation.mapType());
         if (this.id != null) {
            builder.id(this.id.intValue());
         }

         return this.valueType != null ? (Type)((GroupBuilder)((GroupBuilder)builder.repeatedGroup().addFields(new Type[]{this.keyType, this.valueType})).named("key_value")).named(name) : (Type)((GroupBuilder)((GroupBuilder)builder.repeatedGroup().addFields(new Type[]{this.keyType})).named("key_value")).named(name);
      }

      static {
         STRING_KEY = (Type)((PrimitiveBuilder)Types.required(PrimitiveType.PrimitiveTypeName.BINARY).as(OriginalType.UTF8)).named("key");
      }

      public static class KeyBuilder extends BasePrimitiveBuilder {
         private final BaseMapBuilder mapBuilder;

         public KeyBuilder(BaseMapBuilder mapBuilder, PrimitiveType.PrimitiveTypeName type) {
            super((Object)mapBuilder.parent, type, null);
            this.mapBuilder = mapBuilder;
            this.repetition(Type.Repetition.REQUIRED);
         }

         public ValueBuilder value(PrimitiveType.PrimitiveTypeName type, Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (ValueBuilder)(new ValueBuilder(this.mapBuilder, type)).repetition(repetition);
         }

         public ValueBuilder requiredValue(PrimitiveType.PrimitiveTypeName type) {
            return this.value(type, Type.Repetition.REQUIRED);
         }

         public ValueBuilder optionalValue(PrimitiveType.PrimitiveTypeName type) {
            return this.value(type, Type.Repetition.OPTIONAL);
         }

         public GroupValueBuilder groupValue(Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (GroupValueBuilder)(new GroupValueBuilder(this.mapBuilder)).repetition(repetition);
         }

         public GroupValueBuilder requiredGroupValue() {
            return this.groupValue(Type.Repetition.REQUIRED);
         }

         public GroupValueBuilder optionalGroupValue() {
            return this.groupValue(Type.Repetition.OPTIONAL);
         }

         public MapValueBuilder mapValue(Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (MapValueBuilder)(new MapValueBuilder(this.mapBuilder)).repetition(repetition);
         }

         public MapValueBuilder requiredMapValue() {
            return this.mapValue(Type.Repetition.REQUIRED);
         }

         public MapValueBuilder optionalMapValue() {
            return this.mapValue(Type.Repetition.OPTIONAL);
         }

         public ListValueBuilder listValue(Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (ListValueBuilder)(new ListValueBuilder(this.mapBuilder)).repetition(repetition);
         }

         public ListValueBuilder requiredListValue() {
            return this.listValue(Type.Repetition.REQUIRED);
         }

         public ListValueBuilder optionalListValue() {
            return this.listValue(Type.Repetition.OPTIONAL);
         }

         public BaseMapBuilder value(Type type) {
            this.mapBuilder.setKeyType(this.build("key"));
            this.mapBuilder.setValueType(type);
            return this.mapBuilder;
         }

         public Object named(String name) {
            this.mapBuilder.setKeyType(this.build("key"));
            return this.mapBuilder.named(name);
         }

         protected KeyBuilder self() {
            return this;
         }
      }

      public static class ValueBuilder extends BasePrimitiveBuilder {
         private final BaseMapBuilder mapBuilder;

         public ValueBuilder(BaseMapBuilder mapBuilder, PrimitiveType.PrimitiveTypeName type) {
            super((Object)mapBuilder.parent, type, null);
            this.mapBuilder = mapBuilder;
         }

         public Object named(String name) {
            this.mapBuilder.setValueType(this.build("value"));
            return this.mapBuilder.named(name);
         }

         protected ValueBuilder self() {
            return this;
         }
      }

      public static class GroupKeyBuilder extends BaseGroupBuilder {
         private final BaseMapBuilder mapBuilder;

         public GroupKeyBuilder(BaseMapBuilder mapBuilder) {
            super((Object)mapBuilder.parent, null);
            this.mapBuilder = mapBuilder;
            this.repetition(Type.Repetition.REQUIRED);
         }

         protected GroupKeyBuilder self() {
            return this;
         }

         public ValueBuilder value(PrimitiveType.PrimitiveTypeName type, Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (ValueBuilder)(new ValueBuilder(this.mapBuilder, type)).repetition(repetition);
         }

         public ValueBuilder requiredValue(PrimitiveType.PrimitiveTypeName type) {
            return this.value(type, Type.Repetition.REQUIRED);
         }

         public ValueBuilder optionalValue(PrimitiveType.PrimitiveTypeName type) {
            return this.value(type, Type.Repetition.OPTIONAL);
         }

         public GroupValueBuilder groupValue(Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (GroupValueBuilder)(new GroupValueBuilder(this.mapBuilder)).repetition(repetition);
         }

         public GroupValueBuilder requiredGroupValue() {
            return this.groupValue(Type.Repetition.REQUIRED);
         }

         public GroupValueBuilder optionalGroupValue() {
            return this.groupValue(Type.Repetition.OPTIONAL);
         }

         public MapValueBuilder mapValue(Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (MapValueBuilder)(new MapValueBuilder(this.mapBuilder)).repetition(repetition);
         }

         public MapValueBuilder requiredMapValue() {
            return this.mapValue(Type.Repetition.REQUIRED);
         }

         public MapValueBuilder optionalMapValue() {
            return this.mapValue(Type.Repetition.OPTIONAL);
         }

         public ListValueBuilder listValue(Type.Repetition repetition) {
            this.mapBuilder.setKeyType(this.build("key"));
            return (ListValueBuilder)(new ListValueBuilder(this.mapBuilder)).repetition(repetition);
         }

         public ListValueBuilder requiredListValue() {
            return this.listValue(Type.Repetition.REQUIRED);
         }

         public ListValueBuilder optionalListValue() {
            return this.listValue(Type.Repetition.OPTIONAL);
         }

         public BaseMapBuilder value(Type type) {
            this.mapBuilder.setKeyType(this.build("key"));
            this.mapBuilder.setValueType(type);
            return this.mapBuilder;
         }

         public Object named(String name) {
            this.mapBuilder.setKeyType(this.build("key"));
            return this.mapBuilder.named(name);
         }
      }

      public static class GroupValueBuilder extends BaseGroupBuilder {
         private final BaseMapBuilder mapBuilder;

         public GroupValueBuilder(BaseMapBuilder mapBuilder) {
            super((Object)mapBuilder.parent, null);
            this.mapBuilder = mapBuilder;
         }

         public Object named(String name) {
            this.mapBuilder.setValueType(this.build("value"));
            return this.mapBuilder.named(name);
         }

         protected GroupValueBuilder self() {
            return this;
         }
      }

      public static class MapValueBuilder extends BaseMapBuilder {
         private final BaseMapBuilder mapBuilder;

         public MapValueBuilder(BaseMapBuilder mapBuilder) {
            super(mapBuilder.parent);
            this.mapBuilder = mapBuilder;
         }

         public Object named(String name) {
            this.mapBuilder.setValueType(this.build("value"));
            return this.mapBuilder.named(name);
         }

         protected MapValueBuilder self() {
            return this;
         }
      }

      public static class ListValueBuilder extends BaseListBuilder {
         private final BaseMapBuilder mapBuilder;

         public ListValueBuilder(BaseMapBuilder mapBuilder) {
            super(mapBuilder.parent);
            this.mapBuilder = mapBuilder;
         }

         public Object named(String name) {
            this.mapBuilder.setValueType(this.build("value"));
            return this.mapBuilder.named(name);
         }

         protected ListValueBuilder self() {
            return this;
         }
      }
   }

   public static class MapBuilder extends BaseMapBuilder {
      public MapBuilder(Object parent) {
         super(parent);
      }

      private MapBuilder(Class returnType) {
         super(returnType, null);
      }

      protected MapBuilder self() {
         return this;
      }
   }

   public abstract static class BaseListBuilder extends Builder {
      private Type elementType = null;
      private Object parent;

      public BaseListBuilder(Object parent) {
         super(parent);
         this.parent = parent;
      }

      public BaseListBuilder(Class returnType) {
         super(returnType);
      }

      public BaseListBuilder setElementType(Type elementType) {
         Preconditions.checkState(this.elementType == null, "Only one element can be built with a ListBuilder");
         this.elementType = elementType;
         return this.self();
      }

      protected abstract BaseListBuilder self();

      protected Type build(String name) {
         Preconditions.checkState(this.logicalTypeAnnotation == null, "LIST is already the logical type and can't be changed");
         Objects.requireNonNull(this.elementType, "List element type cannot be null");
         GroupBuilder<GroupType> builder = (GroupBuilder)Types.buildGroup(this.repetition).as(OriginalType.LIST);
         if (this.id != null) {
            builder.id(this.id.intValue());
         }

         return (Type)((GroupBuilder)((GroupBuilder)builder.repeatedGroup().addFields(new Type[]{this.elementType})).named("list")).named(name);
      }

      public ElementBuilder element(PrimitiveType.PrimitiveTypeName type, Type.Repetition repetition) {
         return (ElementBuilder)(new ElementBuilder(this.self(), type)).repetition(repetition);
      }

      public ElementBuilder requiredElement(PrimitiveType.PrimitiveTypeName type) {
         return this.element(type, Type.Repetition.REQUIRED);
      }

      public ElementBuilder optionalElement(PrimitiveType.PrimitiveTypeName type) {
         return this.element(type, Type.Repetition.OPTIONAL);
      }

      public GroupElementBuilder groupElement(Type.Repetition repetition) {
         return (GroupElementBuilder)(new GroupElementBuilder(this.self())).repetition(repetition);
      }

      public GroupElementBuilder requiredGroupElement() {
         return this.groupElement(Type.Repetition.REQUIRED);
      }

      public GroupElementBuilder optionalGroupElement() {
         return this.groupElement(Type.Repetition.OPTIONAL);
      }

      public MapElementBuilder mapElement(Type.Repetition repetition) {
         return (MapElementBuilder)(new MapElementBuilder(this.self())).repetition(repetition);
      }

      public MapElementBuilder requiredMapElement() {
         return this.mapElement(Type.Repetition.REQUIRED);
      }

      public MapElementBuilder optionalMapElement() {
         return this.mapElement(Type.Repetition.OPTIONAL);
      }

      public ListElementBuilder listElement(Type.Repetition repetition) {
         return (ListElementBuilder)(new ListElementBuilder(this.self())).repetition(repetition);
      }

      public ListElementBuilder requiredListElement() {
         return this.listElement(Type.Repetition.REQUIRED);
      }

      public ListElementBuilder optionalListElement() {
         return this.listElement(Type.Repetition.OPTIONAL);
      }

      public BaseListBuilder element(Type type) {
         this.setElementType(type);
         return this.self();
      }

      public static class ElementBuilder extends BasePrimitiveBuilder {
         private final BaseListBuilder listBuilder;

         public ElementBuilder(BaseListBuilder listBuilder, PrimitiveType.PrimitiveTypeName type) {
            super((Object)listBuilder.parent, type, null);
            this.listBuilder = listBuilder;
         }

         public Object named(String name) {
            this.listBuilder.setElementType(this.build("element"));
            return this.listBuilder.named(name);
         }

         protected ElementBuilder self() {
            return this;
         }
      }

      public static class GroupElementBuilder extends BaseGroupBuilder {
         private final BaseListBuilder listBuilder;

         public GroupElementBuilder(BaseListBuilder listBuilder) {
            super((Object)listBuilder.parent, null);
            this.listBuilder = listBuilder;
         }

         public Object named(String name) {
            this.listBuilder.setElementType(this.build("element"));
            return this.listBuilder.named(name);
         }

         protected GroupElementBuilder self() {
            return this;
         }
      }

      public static class MapElementBuilder extends BaseMapBuilder {
         private final BaseListBuilder listBuilder;

         public MapElementBuilder(BaseListBuilder listBuilder) {
            super(listBuilder.parent);
            this.listBuilder = listBuilder;
         }

         protected MapElementBuilder self() {
            return this;
         }

         public Object named(String name) {
            this.listBuilder.setElementType(this.build("element"));
            return this.listBuilder.named(name);
         }
      }

      public static class ListElementBuilder extends BaseListBuilder {
         private final BaseListBuilder listBuilder;

         public ListElementBuilder(BaseListBuilder listBuilder) {
            super(listBuilder.parent);
            this.listBuilder = listBuilder;
         }

         protected ListElementBuilder self() {
            return this;
         }

         public Object named(String name) {
            this.listBuilder.setElementType(this.build("element"));
            return this.listBuilder.named(name);
         }
      }
   }

   public static class ListBuilder extends BaseListBuilder {
      public ListBuilder(Object parent) {
         super(parent);
      }

      public ListBuilder(Class returnType) {
         super(returnType);
      }

      protected ListBuilder self() {
         return this;
      }
   }

   public static class MessageTypeBuilder extends GroupBuilder {
      private MessageTypeBuilder() {
         super((Class)MessageType.class, null);
         this.repetition(Type.Repetition.REQUIRED);
      }

      public MessageType named(String name) {
         Objects.requireNonNull(name, "Name is required");
         return new MessageType(name, this.fields);
      }
   }
}
