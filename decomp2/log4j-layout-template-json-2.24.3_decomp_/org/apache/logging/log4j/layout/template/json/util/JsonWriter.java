package org.apache.logging.log4j.layout.template.json.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import org.apache.logging.log4j.util.IndexedReadOnlyStringMap;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringMap;

public final class JsonWriter implements AutoCloseable, Cloneable {
   private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
   private static final int[] ESC_CODES;
   private final char[] quoteBuffer;
   private final StringBuilder stringBuilder;
   private final StringBuilder formattableBuffer;
   private final int maxStringLength;
   private final String truncatedStringSuffix;
   private final String quotedTruncatedStringSuffix;

   private JsonWriter(final Builder builder) {
      this.quoteBuffer = new char[]{'\\', '-', '0', '0', '-', '-'};
      this.stringBuilder = new StringBuilder(builder.maxStringLength);
      this.formattableBuffer = new StringBuilder(builder.maxStringLength);
      this.maxStringLength = builder.maxStringLength;
      this.truncatedStringSuffix = builder.truncatedStringSuffix;
      this.quotedTruncatedStringSuffix = this.quoteString(builder.truncatedStringSuffix);
   }

   private String quoteString(final String string) {
      int startIndex = this.stringBuilder.length();
      this.quoteString((CharSequence)string, 0, string.length());
      StringBuilder quotedStringBuilder = new StringBuilder();
      quotedStringBuilder.append(this.stringBuilder, startIndex, this.stringBuilder.length());
      String quotedString = quotedStringBuilder.toString();
      this.stringBuilder.setLength(startIndex);
      return quotedString;
   }

   public String use(final Runnable runnable) {
      Objects.requireNonNull(runnable, "runnable");
      int startIndex = this.stringBuilder.length();

      String var4;
      try {
         runnable.run();
         StringBuilder sliceStringBuilder = new StringBuilder();
         sliceStringBuilder.append(this.stringBuilder, startIndex, this.stringBuilder.length());
         var4 = sliceStringBuilder.toString();
      } finally {
         this.trimStringBuilder(this.stringBuilder, startIndex);
      }

      return var4;
   }

   public StringBuilder getStringBuilder() {
      return this.stringBuilder;
   }

   public int getMaxStringLength() {
      return this.maxStringLength;
   }

   public String getTruncatedStringSuffix() {
      return this.truncatedStringSuffix;
   }

   public void writeValue(final Object value) {
      if (value == null) {
         this.writeNull();
      } else if (value instanceof IndexedReadOnlyStringMap) {
         IndexedReadOnlyStringMap map = (IndexedReadOnlyStringMap)value;
         this.writeObject(map);
      } else if (value instanceof StringMap) {
         StringMap map = (StringMap)value;
         this.writeObject(map);
      } else if (value instanceof Map) {
         Map<String, Object> map = (Map)value;
         this.writeObject(map);
      } else if (value instanceof List) {
         List<Object> list = (List)value;
         this.writeArray(list);
      } else if (value instanceof Collection) {
         Collection<Object> collection = (Collection)value;
         this.writeArray(collection);
      } else if (value instanceof Number) {
         Number number = (Number)value;
         this.writeNumber(number);
      } else if (value instanceof Boolean) {
         boolean booleanValue = (Boolean)value;
         this.writeBoolean(booleanValue);
      } else if (value instanceof StringBuilderFormattable) {
         StringBuilderFormattable formattable = (StringBuilderFormattable)value;
         this.writeString(formattable);
      } else if (value instanceof char[]) {
         char[] charValues = (char[])value;
         this.writeArray(charValues);
      } else if (value instanceof boolean[]) {
         boolean[] booleanValues = (boolean[])value;
         this.writeArray(booleanValues);
      } else if (value instanceof byte[]) {
         byte[] byteValues = (byte[])value;
         this.writeArray(byteValues);
      } else if (value instanceof short[]) {
         short[] shortValues = (short[])value;
         this.writeArray(shortValues);
      } else if (value instanceof int[]) {
         int[] intValues = (int[])value;
         this.writeArray(intValues);
      } else if (value instanceof long[]) {
         long[] longValues = (long[])value;
         this.writeArray(longValues);
      } else if (value instanceof float[]) {
         float[] floatValues = (float[])value;
         this.writeArray(floatValues);
      } else if (value instanceof double[]) {
         double[] doubleValues = (double[])value;
         this.writeArray(doubleValues);
      } else if (value instanceof Object[]) {
         Object[] values = value;
         this.writeArray(values);
      } else {
         String stringValue = value instanceof String ? (String)value : String.valueOf(value);
         this.writeString((CharSequence)stringValue);
      }

   }

   public void writeObject(final StringMap map) {
      if (map == null) {
         this.writeNull();
      } else {
         this.writeObjectStart();
         boolean[] firstEntry = new boolean[]{true};
         map.forEach(this::writeStringMap, firstEntry);
         this.writeObjectEnd();
      }

   }

   private void writeStringMap(final String key, final Object value, final boolean[] firstEntry) {
      if (key == null) {
         throw new IllegalArgumentException("null keys are not allowed");
      } else {
         if (firstEntry[0]) {
            firstEntry[0] = false;
         } else {
            this.writeSeparator();
         }

         this.writeObjectKey(key);
         this.writeValue(value);
      }
   }

   public void writeObject(final IndexedReadOnlyStringMap map) {
      if (map == null) {
         this.writeNull();
      } else {
         this.writeObjectStart();

         for(int entryIndex = 0; entryIndex < map.size(); ++entryIndex) {
            String key = map.getKeyAt(entryIndex);
            Object value = map.getValueAt(entryIndex);
            if (entryIndex > 0) {
               this.writeSeparator();
            }

            this.writeObjectKey(key);
            this.writeValue(value);
         }

         this.writeObjectEnd();
      }

   }

   public void writeObject(final Map map) {
      if (map == null) {
         this.writeNull();
      } else {
         this.writeObjectStart();
         boolean[] firstEntry = new boolean[]{true};
         map.forEach((key, value) -> {
            if (key == null) {
               throw new IllegalArgumentException("null keys are not allowed");
            } else {
               if (firstEntry[0]) {
                  firstEntry[0] = false;
               } else {
                  this.writeSeparator();
               }

               this.writeObjectKey(key);
               this.writeValue(value);
            }
         });
         this.writeObjectEnd();
      }

   }

   public void writeObjectStart() {
      this.stringBuilder.append('{');
   }

   public void writeObjectEnd() {
      this.stringBuilder.append('}');
   }

   public void writeObjectKey(final CharSequence key) {
      this.writeString(key);
      this.stringBuilder.append(':');
   }

   public void writeArray(final List items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.size(); ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            Object item = items.get(itemIndex);
            this.writeValue(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final Collection items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();
         boolean[] firstItem = new boolean[]{true};
         items.forEach((item) -> {
            if (firstItem[0]) {
               firstItem[0] = false;
            } else {
               this.writeSeparator();
            }

            this.writeValue(item);
         });
         this.writeArrayEnd();
      }

   }

   public void writeArray(final char[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            this.stringBuilder.append('"');
            this.quoteString((char[])items, itemIndex, 1);
            this.stringBuilder.append('"');
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final boolean[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            boolean item = items[itemIndex];
            this.writeBoolean(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final byte[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            byte item = items[itemIndex];
            this.writeNumber((short)item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final short[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            short item = items[itemIndex];
            this.writeNumber(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final int[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            int item = items[itemIndex];
            this.writeNumber(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final long[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            long item = items[itemIndex];
            this.writeNumber(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final float[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            float item = items[itemIndex];
            this.writeNumber(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final double[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            double item = items[itemIndex];
            this.writeNumber(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArray(final Object[] items) {
      if (items == null) {
         this.writeNull();
      } else {
         this.writeArrayStart();

         for(int itemIndex = 0; itemIndex < items.length; ++itemIndex) {
            if (itemIndex > 0) {
               this.writeSeparator();
            }

            Object item = items[itemIndex];
            this.writeValue(item);
         }

         this.writeArrayEnd();
      }

   }

   public void writeArrayStart() {
      this.stringBuilder.append('[');
   }

   public void writeArrayEnd() {
      this.stringBuilder.append(']');
   }

   public void writeSeparator() {
      this.stringBuilder.append(',');
   }

   public void writeString(final BiConsumer emitter, final Object state) {
      Objects.requireNonNull(emitter, "emitter");
      this.stringBuilder.append('"');

      try {
         emitter.accept(this.formattableBuffer, state);
         int length = this.formattableBuffer.length();
         if (length <= this.maxStringLength) {
            this.quoteString((CharSequence)this.formattableBuffer, 0, length);
         } else {
            this.quoteString((CharSequence)this.formattableBuffer, 0, this.maxStringLength);
            this.stringBuilder.append(this.quotedTruncatedStringSuffix);
         }

         this.stringBuilder.append('"');
      } finally {
         this.trimStringBuilder(this.formattableBuffer, 0);
      }

   }

   public void writeString(final StringBuilderFormattable formattable) {
      if (formattable == null) {
         this.writeNull();
      } else {
         this.stringBuilder.append('"');

         try {
            formattable.formatTo(this.formattableBuffer);
            int length = this.formattableBuffer.length();
            if (length <= this.maxStringLength) {
               this.quoteString((CharSequence)this.formattableBuffer, 0, length);
            } else {
               this.quoteString((CharSequence)this.formattableBuffer, 0, this.maxStringLength);
               this.stringBuilder.append(this.quotedTruncatedStringSuffix);
            }

            this.stringBuilder.append('"');
         } finally {
            this.trimStringBuilder(this.formattableBuffer, 0);
         }
      }

   }

   public void writeString(final CharSequence seq) {
      if (seq == null) {
         this.writeNull();
      } else {
         this.writeString((CharSequence)seq, 0, seq.length());
      }

   }

   public void writeString(final CharSequence seq, final int offset, final int length) {
      if (seq == null) {
         this.writeNull();
      } else if (offset < 0) {
         throw new IllegalArgumentException("was expecting a positive offset: " + offset);
      } else if (length < 0) {
         throw new IllegalArgumentException("was expecting a positive length: " + length);
      } else {
         this.stringBuilder.append('"');
         if (length <= this.maxStringLength) {
            this.quoteString(seq, offset, length);
         } else {
            this.quoteString(seq, offset, this.maxStringLength);
            this.stringBuilder.append(this.quotedTruncatedStringSuffix);
         }

         this.stringBuilder.append('"');
      }
   }

   private void quoteString(final CharSequence seq, final int offset, final int length) {
      int surrogateCorrection = length > 0 && Character.isHighSurrogate(seq.charAt(offset + length - 1)) ? -1 : 0;
      int limit = offset + length + surrogateCorrection;
      int i = offset;

      while(i < limit) {
         char c = seq.charAt(i);
         if (c < ESC_CODES.length && ESC_CODES[c] != 0) {
            c = seq.charAt(i++);
            int escCode = ESC_CODES[c];
            int quoteBufferLength = escCode < 0 ? this.quoteNumeric(c) : this.quoteNamed(escCode);
            this.stringBuilder.append(this.quoteBuffer, 0, quoteBufferLength);
         } else {
            this.stringBuilder.append(c);
            ++i;
            if (i >= limit) {
               return;
            }
         }
      }

   }

   public void writeString(final char[] buffer) {
      if (buffer == null) {
         this.writeNull();
      } else {
         this.writeString((char[])buffer, 0, buffer.length);
      }

   }

   public void writeString(final char[] buffer, final int offset, final int length) {
      if (buffer == null) {
         this.writeNull();
      } else if (offset < 0) {
         throw new IllegalArgumentException("was expecting a positive offset: " + offset);
      } else if (length < 0) {
         throw new IllegalArgumentException("was expecting a positive length: " + length);
      } else {
         this.stringBuilder.append('"');
         if (length <= this.maxStringLength) {
            this.quoteString(buffer, offset, length);
         } else {
            this.quoteString(buffer, offset, this.maxStringLength);
            this.stringBuilder.append(this.quotedTruncatedStringSuffix);
         }

         this.stringBuilder.append('"');
      }
   }

   private void quoteString(final char[] buffer, final int offset, final int length) {
      int surrogateCorrection = length > 0 && Character.isHighSurrogate(buffer[offset + length - 1]) ? -1 : 0;
      int limit = offset + length + surrogateCorrection;
      int i = offset;

      while(i < limit) {
         char c = buffer[i];
         if (c < ESC_CODES.length && ESC_CODES[c] != 0) {
            c = buffer[i++];
            int escCode = ESC_CODES[c];
            int quoteBufferLength = escCode < 0 ? this.quoteNumeric(c) : this.quoteNamed(escCode);
            this.stringBuilder.append(this.quoteBuffer, 0, quoteBufferLength);
         } else {
            this.stringBuilder.append(c);
            ++i;
            if (i >= limit) {
               return;
            }
         }
      }

   }

   private int quoteNumeric(final int value) {
      this.quoteBuffer[1] = 'u';
      this.quoteBuffer[4] = HEX_CHARS[value >> 4];
      this.quoteBuffer[5] = HEX_CHARS[value & 15];
      return 6;
   }

   private int quoteNamed(final int esc) {
      this.quoteBuffer[1] = (char)esc;
      return 2;
   }

   private void writeNumber(final Number number) {
      if (number instanceof BigDecimal) {
         BigDecimal decimalNumber = (BigDecimal)number;
         this.writeNumber(decimalNumber);
      } else if (number instanceof BigInteger) {
         BigInteger integerNumber = (BigInteger)number;
         this.writeNumber(integerNumber);
      } else if (number instanceof Double) {
         double doubleNumber = (Double)number;
         this.writeNumber(doubleNumber);
      } else if (number instanceof Float) {
         float floatNumber = (Float)number;
         this.writeNumber(floatNumber);
      } else if (!(number instanceof Byte) && !(number instanceof Short) && !(number instanceof Integer) && !(number instanceof Long)) {
         long longNumber = number.longValue();
         double doubleValue = number.doubleValue();
         if (Double.compare((double)longNumber, doubleValue) == 0) {
            this.writeNumber(longNumber);
         } else {
            this.writeNumber(doubleValue);
         }
      } else {
         long longNumber = number.longValue();
         this.writeNumber(longNumber);
      }

   }

   public void writeNumber(final BigDecimal number) {
      if (number == null) {
         this.writeNull();
      } else {
         this.stringBuilder.append(number);
      }

   }

   public void writeNumber(final BigInteger number) {
      if (number == null) {
         this.writeNull();
      } else {
         this.stringBuilder.append(number);
      }

   }

   public void writeNumber(final float number) {
      this.stringBuilder.append(number);
   }

   public void writeNumber(final double number) {
      this.stringBuilder.append(number);
   }

   public void writeNumber(final short number) {
      this.stringBuilder.append(number);
   }

   public void writeNumber(final int number) {
      this.stringBuilder.append(number);
   }

   public void writeNumber(final long number) {
      this.stringBuilder.append(number);
   }

   public void writeNumber(final long integralPart, final long fractionalPart) {
      if (fractionalPart < 0L) {
         throw new IllegalArgumentException("was expecting a positive fraction: " + fractionalPart);
      } else {
         this.stringBuilder.append(integralPart);
         if (fractionalPart != 0L) {
            this.stringBuilder.append('.');
            this.stringBuilder.append(fractionalPart);
         }

      }
   }

   public void writeBoolean(final boolean value) {
      this.writeRawString((CharSequence)(value ? "true" : "false"));
   }

   public void writeNull() {
      this.writeRawString((CharSequence)"null");
   }

   public void writeRawString(final CharSequence seq) {
      Objects.requireNonNull(seq, "seq");
      this.writeRawString((CharSequence)seq, 0, seq.length());
   }

   public void writeRawString(final CharSequence seq, final int offset, final int length) {
      Objects.requireNonNull(seq, "seq");
      if (offset < 0) {
         throw new IllegalArgumentException("was expecting a positive offset: " + offset);
      } else if (length < 0) {
         throw new IllegalArgumentException("was expecting a positive length: " + length);
      } else {
         int limit = offset + length;
         this.stringBuilder.append(seq, offset, limit);
      }
   }

   public void writeRawString(final char[] buffer) {
      Objects.requireNonNull(buffer, "buffer");
      this.writeRawString((char[])buffer, 0, buffer.length);
   }

   public void writeRawString(final char[] buffer, final int offset, final int length) {
      Objects.requireNonNull(buffer, "buffer");
      if (offset < 0) {
         throw new IllegalArgumentException("was expecting a positive offset: " + offset);
      } else if (length < 0) {
         throw new IllegalArgumentException("was expecting a positive length: " + length);
      } else {
         this.stringBuilder.append(buffer, offset, length);
      }
   }

   public void close() {
      this.trimStringBuilder(this.stringBuilder, 0);
   }

   private void trimStringBuilder(final StringBuilder stringBuilder, final int length) {
      int trimLength = Math.max(this.maxStringLength, length);
      if (stringBuilder.capacity() > trimLength) {
         stringBuilder.setLength(trimLength);
         stringBuilder.trimToSize();
      }

      stringBuilder.setLength(length);
   }

   public JsonWriter clone() {
      JsonWriter jsonWriter = newBuilder().setMaxStringLength(this.maxStringLength).setTruncatedStringSuffix(this.truncatedStringSuffix).build();
      jsonWriter.stringBuilder.append(this.stringBuilder);
      return jsonWriter;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   static {
      int[] table = new int[128];

      for(int i = 0; i < 32; ++i) {
         table[i] = -1;
      }

      table[34] = 34;
      table[92] = 92;
      table[8] = 98;
      table[9] = 116;
      table[12] = 102;
      table[10] = 110;
      table[13] = 114;
      ESC_CODES = table;
   }

   public static final class Builder {
      private int maxStringLength;
      private String truncatedStringSuffix;

      public int getMaxStringLength() {
         return this.maxStringLength;
      }

      public Builder setMaxStringLength(final int maxStringLength) {
         this.maxStringLength = maxStringLength;
         return this;
      }

      public String getTruncatedStringSuffix() {
         return this.truncatedStringSuffix;
      }

      public Builder setTruncatedStringSuffix(final String truncatedStringSuffix) {
         this.truncatedStringSuffix = truncatedStringSuffix;
         return this;
      }

      public JsonWriter build() {
         this.validate();
         return new JsonWriter(this);
      }

      private void validate() {
         if (this.maxStringLength <= 0) {
            throw new IllegalArgumentException("was expecting maxStringLength > 0: " + this.maxStringLength);
         } else {
            Objects.requireNonNull(this.truncatedStringSuffix, "truncatedStringSuffix");
         }
      }
   }
}
