package org.apache.avro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericFixed;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.TimePeriod;

public class Conversions {
   public static Object convertToLogicalType(Object datum, Schema schema, LogicalType type, Conversion conversion) {
      if (datum == null) {
         return null;
      } else if (schema != null && type != null && conversion != null) {
         try {
            switch (schema.getType()) {
               case RECORD:
                  return conversion.fromRecord((IndexedRecord)datum, schema, type);
               case ENUM:
                  return conversion.fromEnumSymbol((GenericEnumSymbol)datum, schema, type);
               case ARRAY:
                  return conversion.fromArray((Collection)datum, schema, type);
               case MAP:
                  return conversion.fromMap((Map)datum, schema, type);
               case FIXED:
                  return conversion.fromFixed((GenericFixed)datum, schema, type);
               case STRING:
                  return conversion.fromCharSequence((CharSequence)datum, schema, type);
               case BYTES:
                  return conversion.fromBytes((ByteBuffer)datum, schema, type);
               case INT:
                  return conversion.fromInt((Integer)datum, schema, type);
               case LONG:
                  return conversion.fromLong((Long)datum, schema, type);
               case FLOAT:
                  return conversion.fromFloat((Float)datum, schema, type);
               case DOUBLE:
                  return conversion.fromDouble((Double)datum, schema, type);
               case BOOLEAN:
                  return conversion.fromBoolean((Boolean)datum, schema, type);
               default:
                  return datum;
            }
         } catch (ClassCastException e) {
            throw new AvroRuntimeException("Cannot convert " + String.valueOf(datum) + ":" + datum.getClass().getSimpleName() + ": expected generic type", e);
         }
      } else {
         Object[] var10002 = new Object[]{datum, schema, type, conversion};
         throw new IllegalArgumentException("Parameters cannot be null! Parameter values:" + Arrays.deepToString(var10002));
      }
   }

   public static Object convertToRawType(Object datum, Schema schema, LogicalType type, Conversion conversion) {
      if (datum == null) {
         return null;
      } else if (schema != null && type != null && conversion != null) {
         try {
            Class<T> fromClass = conversion.getConvertedType();
            switch (schema.getType()) {
               case RECORD:
                  return conversion.toRecord(fromClass.cast(datum), schema, type);
               case ENUM:
                  return conversion.toEnumSymbol(fromClass.cast(datum), schema, type);
               case ARRAY:
                  return conversion.toArray(fromClass.cast(datum), schema, type);
               case MAP:
                  return conversion.toMap(fromClass.cast(datum), schema, type);
               case FIXED:
                  return conversion.toFixed(fromClass.cast(datum), schema, type);
               case STRING:
                  return conversion.toCharSequence(fromClass.cast(datum), schema, type);
               case BYTES:
                  return conversion.toBytes(fromClass.cast(datum), schema, type);
               case INT:
                  return conversion.toInt(fromClass.cast(datum), schema, type);
               case LONG:
                  return conversion.toLong(fromClass.cast(datum), schema, type);
               case FLOAT:
                  return conversion.toFloat(fromClass.cast(datum), schema, type);
               case DOUBLE:
                  return conversion.toDouble(fromClass.cast(datum), schema, type);
               case BOOLEAN:
                  return conversion.toBoolean(fromClass.cast(datum), schema, type);
               default:
                  return datum;
            }
         } catch (ClassCastException e) {
            throw new AvroRuntimeException("Cannot convert " + String.valueOf(datum) + ":" + datum.getClass().getSimpleName() + ": expected logical type", e);
         }
      } else {
         Object[] var10002 = new Object[]{datum, schema, type, conversion};
         throw new IllegalArgumentException("Parameters cannot be null! Parameter values:" + Arrays.deepToString(var10002));
      }
   }

   public static class UUIDConversion extends Conversion {
      public Class getConvertedType() {
         return UUID.class;
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.uuid().addToSchema(Schema.create(Schema.Type.STRING));
      }

      public String getLogicalTypeName() {
         return "uuid";
      }

      public UUID fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
         return UUID.fromString(value.toString());
      }

      public CharSequence toCharSequence(UUID value, Schema schema, LogicalType type) {
         return value.toString();
      }

      public UUID fromFixed(final GenericFixed value, final Schema schema, final LogicalType type) {
         ByteBuffer buffer = ByteBuffer.wrap(value.bytes());
         long mostSigBits = buffer.getLong();
         long leastSigBits = buffer.getLong();
         return new UUID(mostSigBits, leastSigBits);
      }

      public GenericFixed toFixed(final UUID value, final Schema schema, final LogicalType type) {
         ByteBuffer buffer = ByteBuffer.allocate(16);
         buffer.putLong(value.getMostSignificantBits());
         buffer.putLong(value.getLeastSignificantBits());
         return new GenericData.Fixed(schema, buffer.array());
      }
   }

   public static class DecimalConversion extends Conversion {
      public Class getConvertedType() {
         return BigDecimal.class;
      }

      public Schema getRecommendedSchema() {
         throw new UnsupportedOperationException("No recommended schema for decimal (scale is required)");
      }

      public String getLogicalTypeName() {
         return "decimal";
      }

      public BigDecimal fromBytes(ByteBuffer value, Schema schema, LogicalType type) {
         int scale = ((LogicalTypes.Decimal)type).getScale();
         byte[] bytes = new byte[value.remaining()];
         value.duplicate().get(bytes);
         return new BigDecimal(new BigInteger(bytes), scale);
      }

      public ByteBuffer toBytes(BigDecimal value, Schema schema, LogicalType type) {
         value = validate((LogicalTypes.Decimal)type, value);
         return ByteBuffer.wrap(value.unscaledValue().toByteArray());
      }

      public BigDecimal fromFixed(GenericFixed value, Schema schema, LogicalType type) {
         int scale = ((LogicalTypes.Decimal)type).getScale();
         return new BigDecimal(new BigInteger(value.bytes()), scale);
      }

      public GenericFixed toFixed(BigDecimal value, Schema schema, LogicalType type) {
         value = validate((LogicalTypes.Decimal)type, value);
         byte fillByte = (byte)(value.signum() < 0 ? 255 : 0);
         byte[] unscaled = value.unscaledValue().toByteArray();
         byte[] bytes = new byte[schema.getFixedSize()];
         int unscaledLength = unscaled.length;
         int offset = bytes.length - unscaledLength;
         Arrays.fill(bytes, 0, offset, fillByte);
         System.arraycopy(unscaled, 0, bytes, offset, unscaledLength);
         return new GenericData.Fixed(schema, bytes);
      }

      private static BigDecimal validate(final LogicalTypes.Decimal decimal, BigDecimal value) {
         int scale = decimal.getScale();
         int valueScale = value.scale();
         boolean scaleAdjusted = false;
         if (valueScale != scale) {
            try {
               value = value.setScale(scale, RoundingMode.UNNECESSARY);
               scaleAdjusted = true;
            } catch (ArithmeticException var7) {
               throw new AvroTypeException("Cannot encode decimal with scale " + valueScale + " as scale " + scale + " without rounding");
            }
         }

         int precision = decimal.getPrecision();
         int valuePrecision = value.precision();
         if (valuePrecision > precision) {
            if (scaleAdjusted) {
               throw new AvroTypeException("Cannot encode decimal with precision " + valuePrecision + " as max precision " + precision + ". This is after safely adjusting scale from " + valueScale + " to required " + scale);
            } else {
               throw new AvroTypeException("Cannot encode decimal with precision " + valuePrecision + " as max precision " + precision);
            }
         } else {
            return value;
         }
      }
   }

   public static class BigDecimalConversion extends Conversion {
      public Class getConvertedType() {
         return BigDecimal.class;
      }

      public String getLogicalTypeName() {
         return "big-decimal";
      }

      public BigDecimal fromBytes(final ByteBuffer value, final Schema schema, final LogicalType type) {
         BinaryDecoder decoder = DecoderFactory.get().binaryDecoder((byte[])value.array(), (BinaryDecoder)null);

         try {
            BigInteger bg = null;
            ByteBuffer buffer = decoder.readBytes((ByteBuffer)null);
            byte[] array = buffer.array();
            if (array != null && array.length > 0) {
               bg = new BigInteger(array);
            }

            int scale = decoder.readInt();
            return new BigDecimal(bg, scale);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public ByteBuffer toBytes(final BigDecimal value, final Schema schema, final LogicalType type) {
         try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, (BinaryEncoder)null);
            BigInteger unscaledValue = value.unscaledValue();
            if (unscaledValue != null) {
               encoder.writeBytes(unscaledValue.toByteArray());
            } else {
               encoder.writeBytes(new byte[0]);
            }

            encoder.writeInt(value.scale());
            encoder.flush();
            return ByteBuffer.wrap(out.toByteArray());
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.bigDecimal().addToSchema(Schema.create(Schema.Type.BYTES));
      }
   }

   public static class DurationConversion extends Conversion {
      public Class getConvertedType() {
         return TimePeriod.class;
      }

      public String getLogicalTypeName() {
         return "duration";
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.duration().addToSchema(Schema.createFixed("time.Duration", "A 12-byte byte array encoding a duration in months, days and milliseconds.", (String)null, 12));
      }

      public TimePeriod fromFixed(GenericFixed value, Schema schema, LogicalType type) {
         IntBuffer buffer = ByteBuffer.wrap(value.bytes()).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
         long months = Integer.toUnsignedLong(buffer.get());
         long days = Integer.toUnsignedLong(buffer.get());
         long millis = Integer.toUnsignedLong(buffer.get());
         return TimePeriod.of(months, days, millis);
      }

      public GenericFixed toFixed(TimePeriod value, Schema schema, LogicalType type) {
         ByteBuffer buffer = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
         IntBuffer intBuffer = buffer.asIntBuffer();
         intBuffer.put((int)value.getMonths());
         intBuffer.put((int)value.getDays());
         intBuffer.put((int)value.getMillis());
         return new GenericData.Fixed(schema, buffer.array());
      }
   }
}
