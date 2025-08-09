package org.apache.arrow.vector.extension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.FixedSizeBinaryVector;
import org.apache.arrow.vector.Float2Vector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.IntervalDayVector;
import org.apache.arrow.vector.IntervalMonthDayNanoVector;
import org.apache.arrow.vector.IntervalYearVector;
import org.apache.arrow.vector.LargeVarBinaryVector;
import org.apache.arrow.vector.LargeVarCharVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.TimeMicroVector;
import org.apache.arrow.vector.TimeMilliVector;
import org.apache.arrow.vector.TimeNanoVector;
import org.apache.arrow.vector.TimeSecVector;
import org.apache.arrow.vector.TimeStampMicroTZVector;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.TimeStampMilliTZVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TimeStampNanoTZVector;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.TimeStampSecTZVector;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.VarBinaryVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.ViewVarBinaryVector;
import org.apache.arrow.vector.ViewVarCharVector;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.ExtensionTypeRegistry;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;

public class OpaqueType extends ArrowType.ExtensionType {
   private static final AtomicBoolean registered = new AtomicBoolean(false);
   public static final String EXTENSION_NAME = "arrow.opaque";
   private final ArrowType storageType;
   private final String typeName;
   private final String vendorName;

   public static void ensureRegistered() {
      if (!registered.getAndSet(true)) {
         ExtensionTypeRegistry.register(new OpaqueType(Types.MinorType.NULL.getType(), "", ""));
      }

   }

   public OpaqueType(ArrowType storageType, String typeName, String vendorName) {
      this.storageType = (ArrowType)Objects.requireNonNull(storageType, "storageType");
      this.typeName = (String)Objects.requireNonNull(typeName, "typeName");
      this.vendorName = (String)Objects.requireNonNull(vendorName, "vendorName");
   }

   public ArrowType storageType() {
      return this.storageType;
   }

   public String typeName() {
      return this.typeName;
   }

   public String vendorName() {
      return this.vendorName;
   }

   public String extensionName() {
      return "arrow.opaque";
   }

   public boolean extensionEquals(ArrowType.ExtensionType other) {
      return other != null && "arrow.opaque".equals(other.extensionName()) && other instanceof OpaqueType && this.storageType.equals(other.storageType()) && this.typeName.equals(((OpaqueType)other).typeName()) && this.vendorName.equals(((OpaqueType)other).vendorName());
   }

   public String serialize() {
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode object = mapper.createObjectNode();
      object.put("type_name", this.typeName);
      object.put("vendor_name", this.vendorName);

      try {
         return mapper.writeValueAsString(object);
      } catch (JsonProcessingException e) {
         throw new RuntimeException("Could not serialize " + String.valueOf(this), e);
      }
   }

   public ArrowType deserialize(ArrowType storageType, String serializedData) {
      ObjectMapper mapper = new ObjectMapper();

      JsonNode object;
      try {
         object = mapper.readTree(serializedData);
      } catch (JsonProcessingException e) {
         throw new InvalidExtensionMetadataException("Extension metadata is invalid", e);
      }

      JsonNode typeName = object.get("type_name");
      JsonNode vendorName = object.get("vendor_name");
      if (typeName == null) {
         throw new InvalidExtensionMetadataException("typeName is missing");
      } else if (vendorName == null) {
         throw new InvalidExtensionMetadataException("vendorName is missing");
      } else if (!typeName.isTextual()) {
         throw new InvalidExtensionMetadataException("typeName should be string, was " + String.valueOf(typeName));
      } else if (!vendorName.isTextual()) {
         throw new InvalidExtensionMetadataException("vendorName should be string, was " + String.valueOf(vendorName));
      } else {
         return new OpaqueType(storageType, typeName.asText(), vendorName.asText());
      }
   }

   public FieldVector getNewVector(String name, FieldType fieldType, BufferAllocator allocator) {
      Field field = new Field(name, fieldType, Collections.emptyList());
      FieldVector underlyingVector = (FieldVector)this.storageType.accept(new UnderlyingVectorTypeVisitor(name, allocator));
      return new OpaqueVector(field, allocator, underlyingVector);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{super.hashCode(), this.storageType, this.typeName, this.vendorName});
   }

   public String toString() {
      String var10000 = String.valueOf(this.storageType);
      return "OpaqueType(" + var10000 + ", typeName='" + this.typeName + "', vendorName='" + this.vendorName + "')";
   }

   private static class UnderlyingVectorTypeVisitor implements ArrowType.ArrowTypeVisitor {
      private final String name;
      private final BufferAllocator allocator;

      UnderlyingVectorTypeVisitor(String name, BufferAllocator allocator) {
         this.name = name;
         this.allocator = allocator;
      }

      public FieldVector visit(ArrowType.Null type) {
         return new NullVector(this.name);
      }

      private RuntimeException unsupported(ArrowType type) {
         throw new UnsupportedOperationException("OpaqueType#getUnderlyingVector is not supported for storage type: " + String.valueOf(type));
      }

      public FieldVector visit(ArrowType.Struct type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.List type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.LargeList type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.FixedSizeList type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.Union type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.Map type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.Int type) {
         return new IntVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.FloatingPoint type) {
         switch (type.getPrecision()) {
            case HALF:
               return new Float2Vector(this.name, this.allocator);
            case SINGLE:
               return new Float4Vector(this.name, this.allocator);
            case DOUBLE:
               return new Float8Vector(this.name, this.allocator);
            default:
               throw this.unsupported(type);
         }
      }

      public FieldVector visit(ArrowType.Utf8 type) {
         return new VarCharVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.Utf8View type) {
         return new ViewVarCharVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.LargeUtf8 type) {
         return new LargeVarCharVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.Binary type) {
         return new VarBinaryVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.BinaryView type) {
         return new ViewVarBinaryVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.LargeBinary type) {
         return new LargeVarBinaryVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.FixedSizeBinary type) {
         return new FixedSizeBinaryVector(Field.nullable(this.name, type), this.allocator);
      }

      public FieldVector visit(ArrowType.Bool type) {
         return new BitVector(this.name, this.allocator);
      }

      public FieldVector visit(ArrowType.Decimal type) {
         if (type.getBitWidth() == 128) {
            return new DecimalVector(Field.nullable(this.name, type), this.allocator);
         } else if (type.getBitWidth() == 256) {
            return new Decimal256Vector(Field.nullable(this.name, type), this.allocator);
         } else {
            throw this.unsupported(type);
         }
      }

      public FieldVector visit(ArrowType.Date type) {
         switch (type.getUnit()) {
            case DAY:
               return new DateDayVector(this.name, this.allocator);
            case MILLISECOND:
               return new DateMilliVector(this.name, this.allocator);
            default:
               throw this.unsupported(type);
         }
      }

      public FieldVector visit(ArrowType.Time type) {
         switch (type.getUnit()) {
            case SECOND:
               return new TimeSecVector(this.name, this.allocator);
            case MILLISECOND:
               return new TimeMilliVector(this.name, this.allocator);
            case MICROSECOND:
               return new TimeMicroVector(this.name, this.allocator);
            case NANOSECOND:
               return new TimeNanoVector(this.name, this.allocator);
            default:
               throw this.unsupported(type);
         }
      }

      public FieldVector visit(ArrowType.Timestamp type) {
         if (type.getTimezone() != null && !type.getTimezone().isEmpty()) {
            switch (type.getUnit()) {
               case SECOND:
                  return new TimeStampSecTZVector(Field.nullable(this.name, type), this.allocator);
               case MILLISECOND:
                  return new TimeStampMilliTZVector(Field.nullable(this.name, type), this.allocator);
               case MICROSECOND:
                  return new TimeStampMicroTZVector(Field.nullable(this.name, type), this.allocator);
               case NANOSECOND:
                  return new TimeStampNanoTZVector(Field.nullable(this.name, type), this.allocator);
               default:
                  throw this.unsupported(type);
            }
         } else {
            switch (type.getUnit()) {
               case SECOND:
                  return new TimeStampSecVector(Field.nullable(this.name, type), this.allocator);
               case MILLISECOND:
                  return new TimeStampMilliVector(Field.nullable(this.name, type), this.allocator);
               case MICROSECOND:
                  return new TimeStampMicroVector(Field.nullable(this.name, type), this.allocator);
               case NANOSECOND:
                  return new TimeStampNanoVector(Field.nullable(this.name, type), this.allocator);
               default:
                  throw this.unsupported(type);
            }
         }
      }

      public FieldVector visit(ArrowType.Interval type) {
         switch (type.getUnit()) {
            case YEAR_MONTH:
               return new IntervalYearVector(this.name, this.allocator);
            case DAY_TIME:
               return new IntervalDayVector(this.name, this.allocator);
            case MONTH_DAY_NANO:
               return new IntervalMonthDayNanoVector(this.name, this.allocator);
            default:
               throw this.unsupported(type);
         }
      }

      public FieldVector visit(ArrowType.Duration type) {
         return new DurationVector(Field.nullable(this.name, type), this.allocator);
      }

      public FieldVector visit(ArrowType.ListView type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.LargeListView type) {
         throw this.unsupported(type);
      }

      public FieldVector visit(ArrowType.RunEndEncoded type) {
         throw this.unsupported(type);
      }
   }
}
