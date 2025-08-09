package org.apache.parquet.schema;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.apache.parquet.io.api.Binary;

public abstract class PrimitiveStringifier {
   private final String name;
   private static final String BINARY_NULL = "null";
   private static final String BINARY_HEXA_PREFIX = "0x";
   private static final String BINARY_INVALID = "<INVALID>";
   static final PrimitiveStringifier DEFAULT_STRINGIFIER = new BinaryStringifierBase("DEFAULT_STRINGIFIER") {
      private final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

      public String stringify(boolean value) {
         return Boolean.toString(value);
      }

      public String stringify(int value) {
         return Integer.toString(value);
      }

      public String stringify(long value) {
         return Long.toString(value);
      }

      public String stringify(float value) {
         return Float.toString(value);
      }

      public String stringify(double value) {
         return Double.toString(value);
      }

      String stringifyNotNull(Binary value) {
         ByteBuffer buffer = value.toByteBuffer();
         StringBuilder builder = new StringBuilder(2 + buffer.remaining() * 2);
         builder.append("0x");
         int i = buffer.position();

         for(int n = buffer.limit(); i < n; ++i) {
            byte b = buffer.get(i);
            builder.append(this.digits[b >>> 4 & 15]);
            builder.append(this.digits[b & 15]);
         }

         return builder.toString();
      }
   };
   static final PrimitiveStringifier UNSIGNED_STRINGIFIER = new PrimitiveStringifier("UNSIGNED_STRINGIFIER") {
      private static final long INT_MASK = 4294967295L;

      public String stringify(int value) {
         return Long.toString((long)value & 4294967295L);
      }

      public String stringify(long value) {
         if (value == 0L) {
            return "0";
         } else if (value > 0L) {
            return Long.toString(value);
         } else {
            char[] buf = new char[64];
            int i = buf.length;
            long top = value >>> 32;
            long bot = (value & 4294967295L) + (top % 10L << 32);

            for(long var9 = top / 10L; bot > 0L || var9 > 0L; var9 /= 10L) {
               --i;
               buf[i] = Character.forDigit((int)(bot % 10L), 10);
               bot = bot / 10L + (var9 % 10L << 32);
            }

            return new String(buf, i, buf.length - i);
         }
      }
   };
   static final PrimitiveStringifier UTF8_STRINGIFIER = new BinaryStringifierBase("UTF8_STRINGIFIER") {
      String stringifyNotNull(Binary value) {
         return value.toStringUsingUTF8();
      }
   };
   static final PrimitiveStringifier INTERVAL_STRINGIFIER = new BinaryStringifierBase("INTERVAL_STRINGIFIER") {
      String stringifyNotNull(Binary value) {
         if (value.length() != 12) {
            return "<INVALID>";
         } else {
            ByteBuffer buffer = value.toByteBuffer().order(ByteOrder.LITTLE_ENDIAN);
            int pos = buffer.position();
            String months = UNSIGNED_STRINGIFIER.stringify(buffer.getInt(pos));
            String days = UNSIGNED_STRINGIFIER.stringify(buffer.getInt(pos + 4));
            String millis = UNSIGNED_STRINGIFIER.stringify(buffer.getInt(pos + 8));
            return "interval(" + months + " months, " + days + " days, " + millis + " millis)";
         }
      }
   };
   static final PrimitiveStringifier DATE_STRINGIFIER = new DateStringifier("DATE_STRINGIFIER", "yyyy-MM-dd") {
      Instant getInstant(int value) {
         return Instant.ofEpochMilli(TimeUnit.DAYS.toMillis((long)value));
      }
   };
   static final PrimitiveStringifier TIMESTAMP_MILLIS_STRINGIFIER = new DateStringifier("TIMESTAMP_MILLIS_STRINGIFIER", "yyyy-MM-dd'T'HH:mm:ss.SSS") {
      Instant getInstant(long value) {
         return Instant.ofEpochMilli(value);
      }
   };
   static final PrimitiveStringifier TIMESTAMP_MICROS_STRINGIFIER = new DateStringifier("TIMESTAMP_MICROS_STRINGIFIER", "yyyy-MM-dd'T'HH:mm:ss.SSSSSS") {
      Instant getInstant(long value) {
         return Instant.ofEpochSecond(TimeUnit.MICROSECONDS.toSeconds(value), TimeUnit.MICROSECONDS.toNanos(value % TimeUnit.SECONDS.toMicros(1L)));
      }
   };
   static final PrimitiveStringifier TIMESTAMP_NANOS_STRINGIFIER = new DateStringifier("TIMESTAMP_NANOS_STRINGIFIER", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS") {
      Instant getInstant(long value) {
         return Instant.ofEpochSecond(TimeUnit.NANOSECONDS.toSeconds(value), TimeUnit.NANOSECONDS.toNanos(value % TimeUnit.SECONDS.toNanos(1L)));
      }
   };
   static final PrimitiveStringifier TIMESTAMP_MILLIS_UTC_STRINGIFIER = new DateStringifier("TIMESTAMP_MILLIS_UTC_STRINGIFIER", "yyyy-MM-dd'T'HH:mm:ss.SSSZ") {
      Instant getInstant(long value) {
         return Instant.ofEpochMilli(value);
      }
   };
   static final PrimitiveStringifier TIMESTAMP_MICROS_UTC_STRINGIFIER = new DateStringifier("TIMESTAMP_MICROS_UTC_STRINGIFIER", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ") {
      Instant getInstant(long value) {
         return Instant.ofEpochSecond(TimeUnit.MICROSECONDS.toSeconds(value), TimeUnit.MICROSECONDS.toNanos(value % TimeUnit.SECONDS.toMicros(1L)));
      }
   };
   static final PrimitiveStringifier TIMESTAMP_NANOS_UTC_STRINGIFIER = new DateStringifier("TIMESTAMP_NANOS_UTC_STRINGIFIER", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ") {
      Instant getInstant(long value) {
         return Instant.ofEpochSecond(TimeUnit.NANOSECONDS.toSeconds(value), TimeUnit.NANOSECONDS.toNanos(value % TimeUnit.SECONDS.toNanos(1L)));
      }
   };
   static final PrimitiveStringifier TIME_STRINGIFIER = new TimeStringifier("TIME_STRINGIFIER", false) {
      public String stringify(int millis) {
         return this.toTimeString((long)millis, TimeUnit.MILLISECONDS);
      }

      public String stringify(long micros) {
         return this.toTimeString(micros, TimeUnit.MICROSECONDS);
      }
   };
   static final PrimitiveStringifier TIME_NANOS_STRINGIFIER = new TimeStringifier("TIME_NANOS_STRINGIFIER", false) {
      public String stringify(long nanos) {
         return this.toTimeString(nanos, TimeUnit.NANOSECONDS);
      }
   };
   static final PrimitiveStringifier TIME_UTC_STRINGIFIER = new TimeStringifier("TIME_UTC_STRINGIFIER", true) {
      public String stringify(int millis) {
         return this.toTimeString((long)millis, TimeUnit.MILLISECONDS);
      }

      public String stringify(long micros) {
         return this.toTimeString(micros, TimeUnit.MICROSECONDS);
      }
   };
   static final PrimitiveStringifier TIME_NANOS_UTC_STRINGIFIER = new TimeStringifier("TIME_NANOS_UTC_STRINGIFIER", true) {
      public String stringify(long nanos) {
         return this.toTimeString(nanos, TimeUnit.NANOSECONDS);
      }
   };
   static final PrimitiveStringifier UUID_STRINGIFIER = new PrimitiveStringifier("UUID_STRINGIFIER") {
      private final char[] digit = "0123456789abcdef".toCharArray();

      public String stringify(Binary value) {
         byte[] bytes = value.getBytesUnsafe();
         StringBuilder builder = new StringBuilder(36);
         this.appendHex(bytes, 0, 4, builder);
         builder.append('-');
         this.appendHex(bytes, 4, 2, builder);
         builder.append('-');
         this.appendHex(bytes, 6, 2, builder);
         builder.append('-');
         this.appendHex(bytes, 8, 2, builder);
         builder.append('-');
         this.appendHex(bytes, 10, 6, builder);
         return builder.toString();
      }

      private void appendHex(byte[] array, int offset, int length, StringBuilder builder) {
         int i = offset;

         for(int n = offset + length; i < n; ++i) {
            int value = array[i] & 255;
            builder.append(this.digit[value >>> 4]).append(this.digit[value & 15]);
         }

      }
   };
   static final PrimitiveStringifier FLOAT16_STRINGIFIER = new BinaryStringifierBase("FLOAT16_STRINGIFIER") {
      String stringifyNotNull(Binary value) {
         return Float16.toFloatString(value);
      }
   };

   private PrimitiveStringifier(String name) {
      this.name = name;
   }

   public final String toString() {
      return this.name;
   }

   public String stringify(boolean value) {
      throw new UnsupportedOperationException("stringify(boolean) was called on a non-boolean stringifier: " + this.toString());
   }

   public String stringify(int value) {
      throw new UnsupportedOperationException("stringify(int) was called on a non-int stringifier: " + this.toString());
   }

   public String stringify(long value) {
      throw new UnsupportedOperationException("stringify(long) was called on a non-long stringifier: " + this.toString());
   }

   public String stringify(float value) {
      throw new UnsupportedOperationException("stringify(float) was called on a non-float stringifier: " + this.toString());
   }

   public String stringify(double value) {
      throw new UnsupportedOperationException("stringify(double) was called on a non-double stringifier: " + this.toString());
   }

   public String stringify(Binary value) {
      throw new UnsupportedOperationException("stringify(Binary) was called on a non-Binary stringifier: " + this.toString());
   }

   static PrimitiveStringifier createDecimalStringifier(final int scale) {
      return new BinaryStringifierBase("DECIMAL_STRINGIFIER(scale: " + scale + ")") {
         public String stringify(int value) {
            return this.stringifyWithScale(BigInteger.valueOf((long)value));
         }

         public String stringify(long value) {
            return this.stringifyWithScale(BigInteger.valueOf(value));
         }

         String stringifyNotNull(Binary value) {
            try {
               return this.stringifyWithScale(new BigInteger(value.getBytesUnsafe()));
            } catch (NumberFormatException var3) {
               return "<INVALID>";
            }
         }

         private String stringifyWithScale(BigInteger i) {
            return (new BigDecimal(i, scale)).toString();
         }
      };
   }

   abstract static class BinaryStringifierBase extends PrimitiveStringifier {
      private BinaryStringifierBase(String name) {
         super(name, null);
      }

      public final String stringify(Binary value) {
         return value == null ? "null" : this.stringifyNotNull(value);
      }

      abstract String stringifyNotNull(Binary var1);
   }

   private static class DateStringifier extends PrimitiveStringifier {
      private final DateTimeFormatter formatter;

      private DateStringifier(String name, String format) {
         super(name, null);
         this.formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneOffset.UTC);
      }

      public String stringify(int value) {
         return this.toFormattedString(this.getInstant(value));
      }

      public String stringify(long value) {
         return this.toFormattedString(this.getInstant(value));
      }

      private String toFormattedString(Instant instant) {
         return this.formatter.format(instant);
      }

      Instant getInstant(int value) {
         super.stringify(value);
         return null;
      }

      Instant getInstant(long value) {
         super.stringify(value);
         return null;
      }
   }

   private abstract static class TimeStringifier extends PrimitiveStringifier {
      private final boolean withZone;

      TimeStringifier(String name, boolean withZone) {
         super(name, null);
         this.withZone = withZone;
      }

      protected String toTimeString(long duration, TimeUnit unit) {
         String additionalFormat = unit == TimeUnit.MILLISECONDS ? "3d" : (unit == TimeUnit.MICROSECONDS ? "6d" : "9d");
         String timeZone = this.withZone ? "+0000" : "";
         String format = "%02d:%02d:%02d.%0" + additionalFormat + timeZone;
         return String.format(format, unit.toHours(duration), this.convert(duration, unit, TimeUnit.MINUTES, TimeUnit.HOURS), this.convert(duration, unit, TimeUnit.SECONDS, TimeUnit.MINUTES), this.convert(duration, unit, unit, TimeUnit.SECONDS));
      }

      protected long convert(long duration, TimeUnit from, TimeUnit to, TimeUnit higher) {
         return Math.abs(to.convert(duration, from) % to.convert(1L, higher));
      }
   }
}
