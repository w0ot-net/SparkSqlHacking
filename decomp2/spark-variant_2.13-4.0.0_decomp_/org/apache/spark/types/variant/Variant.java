package org.apache.spark.types.variant;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;

public final class Variant {
   final byte[] value;
   final byte[] metadata;
   final int pos;
   private static final DateTimeFormatter TIMESTAMP_NTZ_FORMATTER;
   private static final DateTimeFormatter TIMESTAMP_FORMATTER;

   public Variant(byte[] value, byte[] metadata) {
      this(value, metadata, 0);
   }

   Variant(byte[] value, byte[] metadata, int pos) {
      this.value = value;
      this.metadata = metadata;
      this.pos = pos;
      if (metadata.length >= 1 && (metadata[0] & 15) == 1) {
         if (metadata.length > 134217728 || value.length > 134217728) {
            throw VariantUtil.variantConstructorSizeLimit();
         }
      } else {
         throw VariantUtil.malformedVariant();
      }
   }

   public byte[] getValue() {
      if (this.pos == 0) {
         return this.value;
      } else {
         int size = VariantUtil.valueSize(this.value, this.pos);
         VariantUtil.checkIndex(this.pos + size - 1, this.value.length);
         return Arrays.copyOfRange(this.value, this.pos, this.pos + size);
      }
   }

   public byte[] getMetadata() {
      return this.metadata;
   }

   public boolean getBoolean() {
      return VariantUtil.getBoolean(this.value, this.pos);
   }

   public long getLong() {
      return VariantUtil.getLong(this.value, this.pos);
   }

   public double getDouble() {
      return VariantUtil.getDouble(this.value, this.pos);
   }

   public BigDecimal getDecimal() {
      return VariantUtil.getDecimal(this.value, this.pos);
   }

   public float getFloat() {
      return VariantUtil.getFloat(this.value, this.pos);
   }

   public byte[] getBinary() {
      return VariantUtil.getBinary(this.value, this.pos);
   }

   public String getString() {
      return VariantUtil.getString(this.value, this.pos);
   }

   public int getTypeInfo() {
      return VariantUtil.getTypeInfo(this.value, this.pos);
   }

   public VariantUtil.Type getType() {
      return VariantUtil.getType(this.value, this.pos);
   }

   public UUID getUuid() {
      return VariantUtil.getUuid(this.value, this.pos);
   }

   public int objectSize() {
      return (Integer)VariantUtil.handleObject(this.value, this.pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> size);
   }

   public Variant getFieldByKey(String key) {
      return (Variant)VariantUtil.handleObject(this.value, this.pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> {
         int BINARY_SEARCH_THRESHOLD = 32;
         if (size < 32) {
            for(int i = 0; i < size; ++i) {
               int id = VariantUtil.readUnsigned(this.value, idStart + idSize * i, idSize);
               if (key.equals(VariantUtil.getMetadataKey(this.metadata, id))) {
                  int offset = VariantUtil.readUnsigned(this.value, offsetStart + offsetSize * i, offsetSize);
                  return new Variant(this.value, this.metadata, dataStart + offset);
               }
            }
         } else {
            int low = 0;
            int high = size - 1;

            while(low <= high) {
               int mid = low + high >>> 1;
               int id = VariantUtil.readUnsigned(this.value, idStart + idSize * mid, idSize);
               int cmp = VariantUtil.getMetadataKey(this.metadata, id).compareTo(key);
               if (cmp < 0) {
                  low = mid + 1;
               } else {
                  if (cmp <= 0) {
                     int offset = VariantUtil.readUnsigned(this.value, offsetStart + offsetSize * mid, offsetSize);
                     return new Variant(this.value, this.metadata, dataStart + offset);
                  }

                  high = mid - 1;
               }
            }
         }

         return null;
      });
   }

   public ObjectField getFieldAtIndex(int index) {
      return (ObjectField)VariantUtil.handleObject(this.value, this.pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> {
         if (index >= 0 && index < size) {
            int id = VariantUtil.readUnsigned(this.value, idStart + idSize * index, idSize);
            int offset = VariantUtil.readUnsigned(this.value, offsetStart + offsetSize * index, offsetSize);
            String key = VariantUtil.getMetadataKey(this.metadata, id);
            Variant v = new Variant(this.value, this.metadata, dataStart + offset);
            return new ObjectField(key, v);
         } else {
            return null;
         }
      });
   }

   public int getDictionaryIdAtIndex(int index) {
      return (Integer)VariantUtil.handleObject(this.value, this.pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> {
         if (index >= 0 && index < size) {
            return VariantUtil.readUnsigned(this.value, idStart + idSize * index, idSize);
         } else {
            throw VariantUtil.malformedVariant();
         }
      });
   }

   public int arraySize() {
      return (Integer)VariantUtil.handleArray(this.value, this.pos, (size, offsetSize, offsetStart, dataStart) -> size);
   }

   public Variant getElementAtIndex(int index) {
      return (Variant)VariantUtil.handleArray(this.value, this.pos, (size, offsetSize, offsetStart, dataStart) -> {
         if (index >= 0 && index < size) {
            int offset = VariantUtil.readUnsigned(this.value, offsetStart + offsetSize * index, offsetSize);
            return new Variant(this.value, this.metadata, dataStart + offset);
         } else {
            return null;
         }
      });
   }

   public String toJson(ZoneId zoneId) {
      StringBuilder sb = new StringBuilder();
      toJsonImpl(this.value, this.metadata, this.pos, sb, zoneId);
      return sb.toString();
   }

   static String escapeJson(String str) {
      try {
         CharArrayWriter writer = new CharArrayWriter();

         String var3;
         try {
            JsonGenerator gen = (new JsonFactory()).createGenerator(writer);

            try {
               gen.writeString(str);
               gen.flush();
               var3 = writer.toString();
            } catch (Throwable var7) {
               if (gen != null) {
                  try {
                     gen.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }
               }

               throw var7;
            }

            if (gen != null) {
               gen.close();
            }
         } catch (Throwable var8) {
            try {
               writer.close();
            } catch (Throwable var5) {
               var8.addSuppressed(var5);
            }

            throw var8;
         }

         writer.close();
         return var3;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   static void appendQuoted(StringBuilder sb, String str) {
      sb.append('"');
      sb.append(str);
      sb.append('"');
   }

   private static Instant microsToInstant(long timestamp) {
      return Instant.EPOCH.plus(timestamp, ChronoUnit.MICROS);
   }

   static void toJsonImpl(byte[] value, byte[] metadata, int pos, StringBuilder sb, ZoneId zoneId) {
      switch (VariantUtil.getType(value, pos)) {
         case OBJECT -> VariantUtil.handleObject(value, pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> {
   sb.append('{');

   for(int i = 0; i < size; ++i) {
      int id = VariantUtil.readUnsigned(value, idStart + idSize * i, idSize);
      int offset = VariantUtil.readUnsigned(value, offsetStart + offsetSize * i, offsetSize);
      int elementPos = dataStart + offset;
      if (i != 0) {
         sb.append(',');
      }

      sb.append(escapeJson(VariantUtil.getMetadataKey(metadata, id)));
      sb.append(':');
      toJsonImpl(value, metadata, elementPos, sb, zoneId);
   }

   sb.append('}');
   return null;
});
         case ARRAY -> VariantUtil.handleArray(value, pos, (size, offsetSize, offsetStart, dataStart) -> {
   sb.append('[');

   for(int i = 0; i < size; ++i) {
      int offset = VariantUtil.readUnsigned(value, offsetStart + offsetSize * i, offsetSize);
      int elementPos = dataStart + offset;
      if (i != 0) {
         sb.append(',');
      }

      toJsonImpl(value, metadata, elementPos, sb, zoneId);
   }

   sb.append(']');
   return null;
});
         case NULL -> sb.append("null");
         case BOOLEAN -> sb.append(VariantUtil.getBoolean(value, pos));
         case LONG -> sb.append(VariantUtil.getLong(value, pos));
         case STRING -> sb.append(escapeJson(VariantUtil.getString(value, pos)));
         case DOUBLE -> sb.append(VariantUtil.getDouble(value, pos));
         case DECIMAL -> sb.append(VariantUtil.getDecimal(value, pos).toPlainString());
         case DATE -> appendQuoted(sb, LocalDate.ofEpochDay((long)((int)VariantUtil.getLong(value, pos))).toString());
         case TIMESTAMP -> appendQuoted(sb, TIMESTAMP_FORMATTER.format(microsToInstant(VariantUtil.getLong(value, pos)).atZone(zoneId)));
         case TIMESTAMP_NTZ -> appendQuoted(sb, TIMESTAMP_NTZ_FORMATTER.format(microsToInstant(VariantUtil.getLong(value, pos)).atZone(ZoneOffset.UTC)));
         case FLOAT -> sb.append(VariantUtil.getFloat(value, pos));
         case BINARY -> appendQuoted(sb, Base64.getEncoder().encodeToString(VariantUtil.getBinary(value, pos)));
         case UUID -> appendQuoted(sb, VariantUtil.getUuid(value, pos).toString());
      }

   }

   static {
      TIMESTAMP_NTZ_FORMATTER = (new DateTimeFormatterBuilder()).append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral(' ').append(DateTimeFormatter.ISO_LOCAL_TIME).toFormatter(Locale.US);
      TIMESTAMP_FORMATTER = (new DateTimeFormatterBuilder()).append(TIMESTAMP_NTZ_FORMATTER).appendOffset("+HH:MM", "+00:00").toFormatter(Locale.US);
   }

   public static final class ObjectField {
      public final String key;
      public final Variant value;

      public ObjectField(String key, Variant value) {
         this.key = key;
         this.value = value;
      }
   }
}
