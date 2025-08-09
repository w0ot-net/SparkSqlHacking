package org.apache.spark.types.variant;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkRuntimeException;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map.;

public class VariantBuilder {
   private byte[] writeBuffer = new byte[128];
   private int writePos = 0;
   private final HashMap dictionary = new HashMap();
   private final ArrayList dictionaryKeys = new ArrayList();
   private final boolean allowDuplicateKeys;

   public VariantBuilder(boolean allowDuplicateKeys) {
      this.allowDuplicateKeys = allowDuplicateKeys;
   }

   public static Variant parseJson(String json, boolean allowDuplicateKeys) throws IOException {
      JsonParser parser = (new JsonFactory()).createParser(json);

      Variant var3;
      try {
         parser.nextToken();
         var3 = parseJson(parser, allowDuplicateKeys);
      } catch (Throwable var6) {
         if (parser != null) {
            try {
               parser.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (parser != null) {
         parser.close();
      }

      return var3;
   }

   public static Variant parseJson(JsonParser parser, boolean allowDuplicateKeys) throws IOException {
      VariantBuilder builder = new VariantBuilder(allowDuplicateKeys);
      builder.buildJson(parser);
      return builder.result();
   }

   public Variant result() {
      int numKeys = this.dictionaryKeys.size();
      long dictionaryStringSize = 0L;

      for(byte[] key : this.dictionaryKeys) {
         dictionaryStringSize += (long)key.length;
      }

      long maxSize = Math.max(dictionaryStringSize, (long)numKeys);
      if (maxSize > 134217728L) {
         throw new VariantSizeLimitException();
      } else {
         int offsetSize = this.getIntegerSize((int)maxSize);
         int offsetStart = 1 + offsetSize;
         int stringStart = offsetStart + (numKeys + 1) * offsetSize;
         long metadataSize = (long)stringStart + dictionaryStringSize;
         if (metadataSize > 134217728L) {
            throw new VariantSizeLimitException();
         } else {
            byte[] metadata = new byte[(int)metadataSize];
            int headerByte = 1 | offsetSize - 1 << 6;
            VariantUtil.writeLong(metadata, 0, (long)headerByte, 1);
            VariantUtil.writeLong(metadata, 1, (long)numKeys, offsetSize);
            int currentOffset = 0;

            for(int i = 0; i < numKeys; ++i) {
               VariantUtil.writeLong(metadata, offsetStart + i * offsetSize, (long)currentOffset, offsetSize);
               byte[] key = (byte[])this.dictionaryKeys.get(i);
               System.arraycopy(key, 0, metadata, stringStart + currentOffset, key.length);
               currentOffset += key.length;
            }

            VariantUtil.writeLong(metadata, offsetStart + numKeys * offsetSize, (long)currentOffset, offsetSize);
            return new Variant(Arrays.copyOfRange(this.writeBuffer, 0, this.writePos), metadata);
         }
      }
   }

   public byte[] valueWithoutMetadata() {
      return Arrays.copyOfRange(this.writeBuffer, 0, this.writePos);
   }

   public void appendString(String str) {
      byte[] text = str.getBytes(StandardCharsets.UTF_8);
      boolean longStr = text.length > 63;
      this.checkCapacity((longStr ? 5 : 1) + text.length);
      if (longStr) {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(16);
         VariantUtil.writeLong(this.writeBuffer, this.writePos, (long)text.length, 4);
         this.writePos += 4;
      } else {
         this.writeBuffer[this.writePos++] = VariantUtil.shortStrHeader(text.length);
      }

      System.arraycopy(text, 0, this.writeBuffer, this.writePos, text.length);
      this.writePos += text.length;
   }

   public void appendNull() {
      this.checkCapacity(1);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(0);
   }

   public void appendBoolean(boolean b) {
      this.checkCapacity(1);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(b ? 1 : 2);
   }

   public void appendLong(long l) {
      this.checkCapacity(9);
      if (l == (long)((byte)((int)l))) {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(3);
         VariantUtil.writeLong(this.writeBuffer, this.writePos, l, 1);
         ++this.writePos;
      } else if (l == (long)((short)((int)l))) {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(4);
         VariantUtil.writeLong(this.writeBuffer, this.writePos, l, 2);
         this.writePos += 2;
      } else if (l == (long)((int)l)) {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(5);
         VariantUtil.writeLong(this.writeBuffer, this.writePos, l, 4);
         this.writePos += 4;
      } else {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(6);
         VariantUtil.writeLong(this.writeBuffer, this.writePos, l, 8);
         this.writePos += 8;
      }

   }

   public void appendDouble(double d) {
      this.checkCapacity(9);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(7);
      VariantUtil.writeLong(this.writeBuffer, this.writePos, Double.doubleToLongBits(d), 8);
      this.writePos += 8;
   }

   public void appendDecimal(BigDecimal d) {
      this.checkCapacity(18);
      BigInteger unscaled = d.unscaledValue();
      if (d.scale() <= 9 && d.precision() <= 9) {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(8);
         this.writeBuffer[this.writePos++] = (byte)d.scale();
         VariantUtil.writeLong(this.writeBuffer, this.writePos, (long)unscaled.intValueExact(), 4);
         this.writePos += 4;
      } else if (d.scale() <= 18 && d.precision() <= 18) {
         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(9);
         this.writeBuffer[this.writePos++] = (byte)d.scale();
         VariantUtil.writeLong(this.writeBuffer, this.writePos, unscaled.longValueExact(), 8);
         this.writePos += 8;
      } else {
         assert d.scale() <= 38 && d.precision() <= 38;

         this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(10);
         this.writeBuffer[this.writePos++] = (byte)d.scale();
         byte[] bytes = unscaled.toByteArray();

         for(int i = 0; i < bytes.length; ++i) {
            this.writeBuffer[this.writePos + i] = bytes[bytes.length - 1 - i];
         }

         byte sign = (byte)(bytes[0] < 0 ? -1 : 0);

         for(int i = bytes.length; i < 16; ++i) {
            this.writeBuffer[this.writePos + i] = sign;
         }

         this.writePos += 16;
      }

   }

   public void appendDate(int daysSinceEpoch) {
      this.checkCapacity(5);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(11);
      VariantUtil.writeLong(this.writeBuffer, this.writePos, (long)daysSinceEpoch, 4);
      this.writePos += 4;
   }

   public void appendTimestamp(long microsSinceEpoch) {
      this.checkCapacity(9);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(12);
      VariantUtil.writeLong(this.writeBuffer, this.writePos, microsSinceEpoch, 8);
      this.writePos += 8;
   }

   public void appendTimestampNtz(long microsSinceEpoch) {
      this.checkCapacity(9);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(13);
      VariantUtil.writeLong(this.writeBuffer, this.writePos, microsSinceEpoch, 8);
      this.writePos += 8;
   }

   public void appendFloat(float f) {
      this.checkCapacity(5);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(14);
      VariantUtil.writeLong(this.writeBuffer, this.writePos, (long)Float.floatToIntBits(f), 8);
      this.writePos += 4;
   }

   public void appendBinary(byte[] binary) {
      this.checkCapacity(5 + binary.length);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(15);
      VariantUtil.writeLong(this.writeBuffer, this.writePos, (long)binary.length, 4);
      this.writePos += 4;
      System.arraycopy(binary, 0, this.writeBuffer, this.writePos, binary.length);
      this.writePos += binary.length;
   }

   public void appendUuid(UUID uuid) {
      this.checkCapacity(17);
      this.writeBuffer[this.writePos++] = VariantUtil.primitiveHeader(20);
      ByteBuffer buffer = ByteBuffer.wrap(this.writeBuffer, this.writePos, 16);
      buffer.order(ByteOrder.BIG_ENDIAN);
      buffer.putLong(this.writePos, uuid.getMostSignificantBits());
      buffer.putLong(this.writePos + 8, uuid.getLeastSignificantBits());
      this.writePos += 16;
   }

   public int addKey(String key) {
      int id;
      if (this.dictionary.containsKey(key)) {
         id = (Integer)this.dictionary.get(key);
      } else {
         id = this.dictionaryKeys.size();
         this.dictionary.put(key, id);
         this.dictionaryKeys.add(key.getBytes(StandardCharsets.UTF_8));
      }

      return id;
   }

   public int getWritePos() {
      return this.writePos;
   }

   public void finishWritingObject(int start, ArrayList fields) {
      int size = fields.size();
      Collections.sort(fields);
      int maxId = size == 0 ? 0 : ((FieldEntry)fields.get(0)).id;
      if (this.allowDuplicateKeys) {
         int distinctPos = 0;

         for(int i = 1; i < size; ++i) {
            maxId = Math.max(maxId, ((FieldEntry)fields.get(i)).id);
            if (((FieldEntry)fields.get(i)).id == ((FieldEntry)fields.get(i - 1)).id) {
               if (((FieldEntry)fields.get(distinctPos)).offset < ((FieldEntry)fields.get(i)).offset) {
                  fields.set(distinctPos, ((FieldEntry)fields.get(distinctPos)).withNewOffset(((FieldEntry)fields.get(i)).offset));
               }
            } else {
               ++distinctPos;
               fields.set(distinctPos, (FieldEntry)fields.get(i));
            }
         }

         if (distinctPos + 1 < fields.size()) {
            size = distinctPos + 1;
            fields.subList(size, fields.size()).clear();
            fields.sort(Comparator.comparingInt((f) -> f.offset));
            int currentOffset = 0;

            for(int i = 0; i < size; ++i) {
               int oldOffset = ((FieldEntry)fields.get(i)).offset;
               int fieldSize = VariantUtil.valueSize(this.writeBuffer, start + oldOffset);
               System.arraycopy(this.writeBuffer, start + oldOffset, this.writeBuffer, start + currentOffset, fieldSize);
               fields.set(i, ((FieldEntry)fields.get(i)).withNewOffset(currentOffset));
               currentOffset += fieldSize;
            }

            this.writePos = start + currentOffset;
            Collections.sort(fields);
         }
      } else {
         for(int i = 1; i < size; ++i) {
            maxId = Math.max(maxId, ((FieldEntry)fields.get(i)).id);
            String key = ((FieldEntry)fields.get(i)).key;
            if (key.equals(((FieldEntry)fields.get(i - 1)).key)) {
               Map<String, String> parameters = (Map).MODULE$.empty().updated("key", key);
               throw new SparkRuntimeException("VARIANT_DUPLICATE_KEY", parameters, (Throwable)null, new QueryContext[0], "");
            }
         }
      }

      int dataSize = this.writePos - start;
      boolean largeSize = size > 255;
      int sizeBytes = largeSize ? 4 : 1;
      int idSize = this.getIntegerSize(maxId);
      int offsetSize = this.getIntegerSize(dataSize);
      int headerSize = 1 + sizeBytes + size * idSize + (size + 1) * offsetSize;
      this.checkCapacity(headerSize);
      System.arraycopy(this.writeBuffer, start, this.writeBuffer, start + headerSize, dataSize);
      this.writePos += headerSize;
      this.writeBuffer[start] = VariantUtil.objectHeader(largeSize, idSize, offsetSize);
      VariantUtil.writeLong(this.writeBuffer, start + 1, (long)size, sizeBytes);
      int idStart = start + 1 + sizeBytes;
      int offsetStart = idStart + size * idSize;

      for(int i = 0; i < size; ++i) {
         VariantUtil.writeLong(this.writeBuffer, idStart + i * idSize, (long)((FieldEntry)fields.get(i)).id, idSize);
         VariantUtil.writeLong(this.writeBuffer, offsetStart + i * offsetSize, (long)((FieldEntry)fields.get(i)).offset, offsetSize);
      }

      VariantUtil.writeLong(this.writeBuffer, offsetStart + size * offsetSize, (long)dataSize, offsetSize);
   }

   public void finishWritingArray(int start, ArrayList offsets) {
      int dataSize = this.writePos - start;
      int size = offsets.size();
      boolean largeSize = size > 255;
      int sizeBytes = largeSize ? 4 : 1;
      int offsetSize = this.getIntegerSize(dataSize);
      int headerSize = 1 + sizeBytes + (size + 1) * offsetSize;
      this.checkCapacity(headerSize);
      System.arraycopy(this.writeBuffer, start, this.writeBuffer, start + headerSize, dataSize);
      this.writePos += headerSize;
      this.writeBuffer[start] = VariantUtil.arrayHeader(largeSize, offsetSize);
      VariantUtil.writeLong(this.writeBuffer, start + 1, (long)size, sizeBytes);
      int offsetStart = start + 1 + sizeBytes;

      for(int i = 0; i < size; ++i) {
         VariantUtil.writeLong(this.writeBuffer, offsetStart + i * offsetSize, (long)(Integer)offsets.get(i), offsetSize);
      }

      VariantUtil.writeLong(this.writeBuffer, offsetStart + size * offsetSize, (long)dataSize, offsetSize);
   }

   public void appendVariant(Variant v) {
      this.appendVariantImpl(v.value, v.metadata, v.pos);
   }

   private void appendVariantImpl(byte[] value, byte[] metadata, int pos) {
      VariantUtil.checkIndex(pos, value.length);
      int basicType = value[pos] & 3;
      switch (basicType) {
         case 2 -> VariantUtil.handleObject(value, pos, (size, idSize, offsetSize, idStart, offsetStart, dataStart) -> {
   ArrayList<FieldEntry> fields = new ArrayList(size);
   int start = this.writePos;

   for(int i = 0; i < size; ++i) {
      int id = VariantUtil.readUnsigned(value, idStart + idSize * i, idSize);
      int offset = VariantUtil.readUnsigned(value, offsetStart + offsetSize * i, offsetSize);
      int elementPos = dataStart + offset;
      String key = VariantUtil.getMetadataKey(metadata, id);
      int newId = this.addKey(key);
      fields.add(new FieldEntry(key, newId, this.writePos - start));
      this.appendVariantImpl(value, metadata, elementPos);
   }

   this.finishWritingObject(start, fields);
   return null;
});
         case 3 -> VariantUtil.handleArray(value, pos, (size, offsetSize, offsetStart, dataStart) -> {
   ArrayList<Integer> offsets = new ArrayList(size);
   int start = this.writePos;

   for(int i = 0; i < size; ++i) {
      int offset = VariantUtil.readUnsigned(value, offsetStart + offsetSize * i, offsetSize);
      int elementPos = dataStart + offset;
      offsets.add(this.writePos - start);
      this.appendVariantImpl(value, metadata, elementPos);
   }

   this.finishWritingArray(start, offsets);
   return null;
});
         default -> this.shallowAppendVariantImpl(value, pos);
      }

   }

   public void shallowAppendVariant(Variant v) {
      this.shallowAppendVariantImpl(v.value, v.pos);
   }

   private void shallowAppendVariantImpl(byte[] value, int pos) {
      int size = VariantUtil.valueSize(value, pos);
      VariantUtil.checkIndex(pos + size - 1, value.length);
      this.checkCapacity(size);
      System.arraycopy(value, pos, this.writeBuffer, this.writePos, size);
      this.writePos += size;
   }

   private void checkCapacity(int additional) {
      int required = this.writePos + additional;
      if (required > this.writeBuffer.length) {
         int newCapacity = Integer.highestOneBit(required);
         newCapacity = newCapacity < required ? newCapacity * 2 : newCapacity;
         if (newCapacity > 134217728) {
            throw new VariantSizeLimitException();
         }

         byte[] newValue = new byte[newCapacity];
         System.arraycopy(this.writeBuffer, 0, newValue, 0, this.writePos);
         this.writeBuffer = newValue;
      }

   }

   private void buildJson(JsonParser parser) throws IOException {
      JsonToken token = parser.currentToken();
      if (token == null) {
         throw new JsonParseException(parser, "Unexpected null token");
      } else {
         switch (token) {
            case START_OBJECT:
               ArrayList<FieldEntry> fields = new ArrayList();
               int start = this.writePos;

               while(parser.nextToken() != JsonToken.END_OBJECT) {
                  String key = parser.currentName();
                  parser.nextToken();
                  int id = this.addKey(key);
                  fields.add(new FieldEntry(key, id, this.writePos - start));
                  this.buildJson(parser);
               }

               this.finishWritingObject(start, fields);
               break;
            case START_ARRAY:
               ArrayList<Integer> offsets = new ArrayList();
               int start = this.writePos;

               while(parser.nextToken() != JsonToken.END_ARRAY) {
                  offsets.add(this.writePos - start);
                  this.buildJson(parser);
               }

               this.finishWritingArray(start, offsets);
               break;
            case VALUE_STRING:
               this.appendString(parser.getText());
               break;
            case VALUE_NUMBER_INT:
               try {
                  this.appendLong(parser.getLongValue());
               } catch (InputCoercionException var7) {
                  this.parseFloatingPoint(parser);
               }
               break;
            case VALUE_NUMBER_FLOAT:
               this.parseFloatingPoint(parser);
               break;
            case VALUE_TRUE:
               this.appendBoolean(true);
               break;
            case VALUE_FALSE:
               this.appendBoolean(false);
               break;
            case VALUE_NULL:
               this.appendNull();
               break;
            default:
               throw new JsonParseException(parser, "Unexpected token " + String.valueOf(token));
         }

      }
   }

   private int getIntegerSize(int value) {
      assert value >= 0 && value <= 134217728;

      if (value <= 255) {
         return 1;
      } else if (value <= 65535) {
         return 2;
      } else {
         return value <= 16777215 ? 3 : 4;
      }
   }

   private void parseFloatingPoint(JsonParser parser) throws IOException {
      if (!this.tryParseDecimal(parser.getText())) {
         this.appendDouble(parser.getDoubleValue());
      }

   }

   private boolean tryParseDecimal(String input) {
      for(int i = 0; i < input.length(); ++i) {
         char ch = input.charAt(i);
         if (ch != '-' && ch != '.' && (ch < '0' || ch > '9')) {
            return false;
         }
      }

      BigDecimal d = new BigDecimal(input);
      if (d.scale() <= 38 && d.precision() <= 38) {
         this.appendDecimal(d);
         return true;
      } else {
         return false;
      }
   }

   public static final class FieldEntry implements Comparable {
      final String key;
      final int id;
      final int offset;

      public FieldEntry(String key, int id, int offset) {
         this.key = key;
         this.id = id;
         this.offset = offset;
      }

      FieldEntry withNewOffset(int newOffset) {
         return new FieldEntry(this.key, this.id, newOffset);
      }

      public int compareTo(FieldEntry other) {
         return this.key.compareTo(other.key);
      }
   }
}
