package org.apache.avro;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogicalTypes {
   private static final Logger LOG = LoggerFactory.getLogger(LogicalTypes.class);
   private static final Map REGISTERED_TYPES = new ConcurrentHashMap();
   private static final String DECIMAL = "decimal";
   private static final String BIG_DECIMAL = "big-decimal";
   private static final String DURATION = "duration";
   private static final String UUID = "uuid";
   private static final String DATE = "date";
   private static final String TIME_MILLIS = "time-millis";
   private static final String TIME_MICROS = "time-micros";
   private static final String TIMESTAMP_MILLIS = "timestamp-millis";
   private static final String TIMESTAMP_MICROS = "timestamp-micros";
   private static final String TIMESTAMP_NANOS = "timestamp-nanos";
   private static final String LOCAL_TIMESTAMP_MILLIS = "local-timestamp-millis";
   private static final String LOCAL_TIMESTAMP_MICROS = "local-timestamp-micros";
   private static final String LOCAL_TIMESTAMP_NANOS = "local-timestamp-nanos";
   private static final BigDecimal BIG_DECIMAL_TYPE;
   private static final LogicalType UUID_TYPE;
   private static final LogicalType DURATION_TYPE;
   private static final Date DATE_TYPE;
   private static final TimeMillis TIME_MILLIS_TYPE;
   private static final TimeMicros TIME_MICROS_TYPE;
   private static final TimestampMillis TIMESTAMP_MILLIS_TYPE;
   private static final TimestampMicros TIMESTAMP_MICROS_TYPE;
   private static final TimestampNanos TIMESTAMP_NANOS_TYPE;
   private static final LocalTimestampMillis LOCAL_TIMESTAMP_MILLIS_TYPE;
   private static final LocalTimestampMicros LOCAL_TIMESTAMP_MICROS_TYPE;
   private static final LocalTimestampNanos LOCAL_TIMESTAMP_NANOS_TYPE;

   public static void register(LogicalTypeFactory factory) {
      Objects.requireNonNull(factory, "Logical type factory cannot be null");
      register(factory.getTypeName(), factory);
   }

   public static void register(String logicalTypeName, LogicalTypeFactory factory) {
      Objects.requireNonNull(logicalTypeName, "Logical type name cannot be null");
      Objects.requireNonNull(factory, "Logical type factory cannot be null");

      try {
         String factoryTypeName = factory.getTypeName();
         if (!logicalTypeName.equals(factoryTypeName)) {
            LOG.debug("Provided logicalTypeName '{}' does not match factory typeName '{}'", logicalTypeName, factoryTypeName);
         }
      } catch (UnsupportedOperationException var3) {
      }

      REGISTERED_TYPES.put(logicalTypeName, factory);
   }

   public static Map getCustomRegisteredTypes() {
      return Collections.unmodifiableMap(REGISTERED_TYPES);
   }

   public static LogicalType fromSchema(Schema schema) {
      return fromSchemaImpl(schema, true);
   }

   public static LogicalType fromSchemaIgnoreInvalid(Schema schema) {
      return fromSchemaImpl(schema, false);
   }

   private static LogicalType fromSchemaImpl(Schema schema, boolean throwErrors) {
      String typeName = schema.getProp("logicalType");
      if (typeName == null) {
         return null;
      } else {
         try {
            LogicalType logicalType;
            switch (typeName) {
               case "timestamp-millis":
                  logicalType = TIMESTAMP_MILLIS_TYPE;
                  break;
               case "decimal":
                  logicalType = new Decimal(schema);
                  break;
               case "big-decimal":
                  logicalType = BIG_DECIMAL_TYPE;
                  break;
               case "uuid":
                  logicalType = UUID_TYPE;
                  break;
               case "date":
                  logicalType = DATE_TYPE;
                  break;
               case "timestamp-micros":
                  logicalType = TIMESTAMP_MICROS_TYPE;
                  break;
               case "timestamp-nanos":
                  logicalType = TIMESTAMP_NANOS_TYPE;
                  break;
               case "time-millis":
                  logicalType = TIME_MILLIS_TYPE;
                  break;
               case "time-micros":
                  logicalType = TIME_MICROS_TYPE;
                  break;
               case "local-timestamp-micros":
                  logicalType = LOCAL_TIMESTAMP_MICROS_TYPE;
                  break;
               case "local-timestamp-millis":
                  logicalType = LOCAL_TIMESTAMP_MILLIS_TYPE;
                  break;
               case "local-timestamp-nanos":
                  logicalType = LOCAL_TIMESTAMP_NANOS_TYPE;
                  break;
               default:
                  LogicalTypeFactory typeFactory = (LogicalTypeFactory)REGISTERED_TYPES.get(typeName);
                  logicalType = typeFactory == null ? null : typeFactory.fromSchema(schema);
            }

            if (logicalType != null) {
               logicalType.validate(schema);
            }

            return logicalType;
         } catch (RuntimeException e) {
            LOG.debug("Invalid logical type found", e);
            if (throwErrors) {
               throw e;
            } else {
               LOG.warn("Ignoring invalid logical type for name: {}", typeName);
               return null;
            }
         }
      }
   }

   public static Decimal decimal(int precision) {
      return decimal(precision, 0);
   }

   public static Decimal decimal(int precision, int scale) {
      return new Decimal(precision, scale);
   }

   public static BigDecimal bigDecimal() {
      return BIG_DECIMAL_TYPE;
   }

   public static LogicalType uuid() {
      return UUID_TYPE;
   }

   public static LogicalType duration() {
      return DURATION_TYPE;
   }

   public static Date date() {
      return DATE_TYPE;
   }

   public static TimeMillis timeMillis() {
      return TIME_MILLIS_TYPE;
   }

   public static TimeMicros timeMicros() {
      return TIME_MICROS_TYPE;
   }

   public static TimestampMillis timestampMillis() {
      return TIMESTAMP_MILLIS_TYPE;
   }

   public static TimestampMicros timestampMicros() {
      return TIMESTAMP_MICROS_TYPE;
   }

   public static TimestampNanos timestampNanos() {
      return TIMESTAMP_NANOS_TYPE;
   }

   public static LocalTimestampMillis localTimestampMillis() {
      return LOCAL_TIMESTAMP_MILLIS_TYPE;
   }

   public static LocalTimestampMicros localTimestampMicros() {
      return LOCAL_TIMESTAMP_MICROS_TYPE;
   }

   public static LocalTimestampNanos localTimestampNanos() {
      return LOCAL_TIMESTAMP_NANOS_TYPE;
   }

   static {
      for(LogicalTypeFactory logicalTypeFactory : ServiceLoader.load(LogicalTypeFactory.class)) {
         register(logicalTypeFactory);
      }

      BIG_DECIMAL_TYPE = new BigDecimal();
      UUID_TYPE = new Uuid();
      DURATION_TYPE = new Duration();
      DATE_TYPE = new Date();
      TIME_MILLIS_TYPE = new TimeMillis();
      TIME_MICROS_TYPE = new TimeMicros();
      TIMESTAMP_MILLIS_TYPE = new TimestampMillis();
      TIMESTAMP_MICROS_TYPE = new TimestampMicros();
      TIMESTAMP_NANOS_TYPE = new TimestampNanos();
      LOCAL_TIMESTAMP_MILLIS_TYPE = new LocalTimestampMillis();
      LOCAL_TIMESTAMP_MICROS_TYPE = new LocalTimestampMicros();
      LOCAL_TIMESTAMP_NANOS_TYPE = new LocalTimestampNanos();
   }

   public interface LogicalTypeFactory {
      LogicalType fromSchema(Schema schema);

      default String getTypeName() {
         throw new UnsupportedOperationException("LogicalTypeFactory TypeName has not been provided");
      }
   }

   public static class Uuid extends LogicalType {
      private static final int UUID_BYTES = 16;

      private Uuid() {
         super("uuid");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.STRING && schema.getType() != Schema.Type.FIXED) {
            throw new IllegalArgumentException("Uuid can only be used with an underlying string or fixed type");
         } else if (schema.getType() == Schema.Type.FIXED && schema.getFixedSize() != 16) {
            throw new IllegalArgumentException("Uuid with fixed type must have a size of 16 bytes");
         }
      }
   }

   public static class Duration extends LogicalType {
      private Duration() {
         super("duration");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.FIXED || schema.getFixedSize() != 12) {
            throw new IllegalArgumentException("Duration can only be used with an underlying fixed type of size 12.");
         }
      }
   }

   public static class Decimal extends LogicalType {
      private static final String PRECISION_PROP = "precision";
      private static final String SCALE_PROP = "scale";
      private final int precision;
      private final int scale;

      private Decimal(int precision, int scale) {
         super("decimal");
         this.precision = precision;
         this.scale = scale;
      }

      private Decimal(Schema schema) {
         super("decimal");
         if (!this.hasProperty(schema, "precision")) {
            throw new IllegalArgumentException("Invalid decimal: missing precision");
         } else {
            this.precision = this.getInt(schema, "precision");
            if (this.hasProperty(schema, "scale")) {
               this.scale = this.getInt(schema, "scale");
            } else {
               this.scale = 0;
            }

         }
      }

      public Schema addToSchema(Schema schema) {
         super.addToSchema(schema);
         schema.addProp("precision", (Object)this.precision);
         schema.addProp("scale", (Object)this.scale);
         return schema;
      }

      public int getPrecision() {
         return this.precision;
      }

      public int getScale() {
         return this.scale;
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.FIXED && schema.getType() != Schema.Type.BYTES) {
            throw new IllegalArgumentException("Logical type decimal must be backed by fixed or bytes");
         } else if (this.precision <= 0) {
            throw new IllegalArgumentException("Invalid decimal precision: " + this.precision + " (must be positive)");
         } else if ((long)this.precision > this.maxPrecision(schema)) {
            int var10002 = schema.getFixedSize();
            throw new IllegalArgumentException("fixed(" + var10002 + ") cannot store " + this.precision + " digits (max " + this.maxPrecision(schema) + ")");
         } else if (this.scale < 0) {
            throw new IllegalArgumentException("Invalid decimal scale: " + this.scale + " (must be positive)");
         } else if (this.scale > this.precision) {
            throw new IllegalArgumentException("Invalid decimal scale: " + this.scale + " (greater than precision: " + this.precision + ")");
         }
      }

      private long maxPrecision(Schema schema) {
         if (schema.getType() == Schema.Type.BYTES) {
            return 2147483647L;
         } else if (schema.getType() == Schema.Type.FIXED) {
            int size = schema.getFixedSize();
            return Math.round(Math.floor(Math.log10((double)2.0F) * (double)(8 * size - 1)));
         } else {
            return 0L;
         }
      }

      private boolean hasProperty(Schema schema, String name) {
         return schema.propsContainsKey(name);
      }

      private int getInt(Schema schema, String name) {
         Object obj = schema.getObjectProp(name);
         if (obj instanceof Integer) {
            return (Integer)obj;
         } else {
            throw new IllegalArgumentException("Expected int " + name + ": " + (obj == null ? "null" : String.valueOf(obj) + ":" + obj.getClass().getSimpleName()));
         }
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Decimal decimal = (Decimal)o;
            if (this.precision != decimal.precision) {
               return false;
            } else {
               return this.scale == decimal.scale;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.precision;
         result = 31 * result + this.scale;
         return result;
      }
   }

   public static class BigDecimal extends LogicalType {
      private BigDecimal() {
         super("big-decimal");
      }

      public void validate(final Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.BYTES) {
            throw new IllegalArgumentException("BigDecimal can only be used with an underlying bytes type");
         }
      }
   }

   public static class Date extends LogicalType {
      private Date() {
         super("date");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.INT) {
            throw new IllegalArgumentException("Date can only be used with an underlying int type");
         }
      }
   }

   public static class TimeMillis extends LogicalType {
      private TimeMillis() {
         super("time-millis");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.INT) {
            throw new IllegalArgumentException("Time (millis) can only be used with an underlying int type");
         }
      }
   }

   public static class TimeMicros extends LogicalType {
      private TimeMicros() {
         super("time-micros");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Time (micros) can only be used with an underlying long type");
         }
      }
   }

   public static class TimestampMillis extends LogicalType {
      private TimestampMillis() {
         super("timestamp-millis");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Timestamp (millis) can only be used with an underlying long type");
         }
      }
   }

   public static class TimestampMicros extends LogicalType {
      private TimestampMicros() {
         super("timestamp-micros");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Timestamp (micros) can only be used with an underlying long type");
         }
      }
   }

   public static class TimestampNanos extends LogicalType {
      private TimestampNanos() {
         super("timestamp-nanos");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Timestamp (nanos) can only be used with an underlying long type");
         }
      }
   }

   public static class LocalTimestampMillis extends LogicalType {
      private LocalTimestampMillis() {
         super("local-timestamp-millis");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Local timestamp (millis) can only be used with an underlying long type");
         }
      }
   }

   public static class LocalTimestampMicros extends LogicalType {
      private LocalTimestampMicros() {
         super("local-timestamp-micros");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Local timestamp (micros) can only be used with an underlying long type");
         }
      }
   }

   public static class LocalTimestampNanos extends LogicalType {
      private LocalTimestampNanos() {
         super("local-timestamp-nanos");
      }

      public void validate(Schema schema) {
         super.validate(schema);
         if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Local timestamp (micros) can only be used with an underlying long type");
         }
      }
   }
}
