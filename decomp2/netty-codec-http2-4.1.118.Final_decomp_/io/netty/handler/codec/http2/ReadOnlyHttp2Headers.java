package io.netty.handler.codec.http2;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.handler.codec.Headers;
import io.netty.util.AsciiString;
import io.netty.util.HashingStrategy;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class ReadOnlyHttp2Headers implements Http2Headers {
   private static final byte PSEUDO_HEADER_TOKEN = 58;
   private final AsciiString[] pseudoHeaders;
   private final AsciiString[] otherHeaders;

   public static ReadOnlyHttp2Headers trailers(boolean validateHeaders, AsciiString... otherHeaders) {
      return new ReadOnlyHttp2Headers(validateHeaders, EmptyArrays.EMPTY_ASCII_STRINGS, otherHeaders);
   }

   public static ReadOnlyHttp2Headers clientHeaders(boolean validateHeaders, AsciiString method, AsciiString path, AsciiString scheme, AsciiString authority, AsciiString... otherHeaders) {
      return new ReadOnlyHttp2Headers(validateHeaders, new AsciiString[]{Http2Headers.PseudoHeaderName.METHOD.value(), method, Http2Headers.PseudoHeaderName.PATH.value(), path, Http2Headers.PseudoHeaderName.SCHEME.value(), scheme, Http2Headers.PseudoHeaderName.AUTHORITY.value(), authority}, otherHeaders);
   }

   public static ReadOnlyHttp2Headers serverHeaders(boolean validateHeaders, AsciiString status, AsciiString... otherHeaders) {
      return new ReadOnlyHttp2Headers(validateHeaders, new AsciiString[]{Http2Headers.PseudoHeaderName.STATUS.value(), status}, otherHeaders);
   }

   private ReadOnlyHttp2Headers(boolean validateHeaders, AsciiString[] pseudoHeaders, AsciiString... otherHeaders) {
      assert (pseudoHeaders.length & 1) == 0;

      if ((otherHeaders.length & 1) != 0) {
         throw newInvalidArraySizeException();
      } else {
         if (validateHeaders) {
            validateHeaders(pseudoHeaders, otherHeaders);
         }

         this.pseudoHeaders = pseudoHeaders;
         this.otherHeaders = otherHeaders;
      }
   }

   private static IllegalArgumentException newInvalidArraySizeException() {
      return new IllegalArgumentException("pseudoHeaders and otherHeaders must be arrays of [name, value] pairs");
   }

   private static void validateHeaders(AsciiString[] pseudoHeaders, AsciiString... otherHeaders) {
      for(int i = 1; i < pseudoHeaders.length; i += 2) {
         ObjectUtil.checkNotNullArrayParam(pseudoHeaders[i], i, "pseudoHeaders");
      }

      boolean seenNonPseudoHeader = false;
      int otherHeadersEnd = otherHeaders.length - 1;

      for(int i = 0; i < otherHeadersEnd; i += 2) {
         AsciiString name = otherHeaders[i];
         DefaultHttp2Headers.HTTP2_NAME_VALIDATOR.validateName(name);
         if (!seenNonPseudoHeader && !name.isEmpty() && name.byteAt(0) != 58) {
            seenNonPseudoHeader = true;
         } else if (seenNonPseudoHeader && !name.isEmpty() && name.byteAt(0) == 58) {
            throw new IllegalArgumentException("otherHeaders name at index " + i + " is a pseudo header that appears after non-pseudo headers.");
         }

         ObjectUtil.checkNotNullArrayParam(otherHeaders[i + 1], i + 1, "otherHeaders");
      }

   }

   private AsciiString get0(CharSequence name) {
      int nameHash = AsciiString.hashCode(name);
      int pseudoHeadersEnd = this.pseudoHeaders.length - 1;

      for(int i = 0; i < pseudoHeadersEnd; i += 2) {
         AsciiString roName = this.pseudoHeaders[i];
         if (roName.hashCode() == nameHash && roName.contentEqualsIgnoreCase(name)) {
            return this.pseudoHeaders[i + 1];
         }
      }

      int otherHeadersEnd = this.otherHeaders.length - 1;

      for(int i = 0; i < otherHeadersEnd; i += 2) {
         AsciiString roName = this.otherHeaders[i];
         if (roName.hashCode() == nameHash && roName.contentEqualsIgnoreCase(name)) {
            return this.otherHeaders[i + 1];
         }
      }

      return null;
   }

   public CharSequence get(CharSequence name) {
      return this.get0(name);
   }

   public CharSequence get(CharSequence name, CharSequence defaultValue) {
      CharSequence value = this.get(name);
      return value != null ? value : defaultValue;
   }

   public CharSequence getAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public CharSequence getAndRemove(CharSequence name, CharSequence defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public List getAll(CharSequence name) {
      int nameHash = AsciiString.hashCode(name);
      List<CharSequence> values = new ArrayList();
      int pseudoHeadersEnd = this.pseudoHeaders.length - 1;

      for(int i = 0; i < pseudoHeadersEnd; i += 2) {
         AsciiString roName = this.pseudoHeaders[i];
         if (roName.hashCode() == nameHash && roName.contentEqualsIgnoreCase(name)) {
            values.add(this.pseudoHeaders[i + 1]);
         }
      }

      int otherHeadersEnd = this.otherHeaders.length - 1;

      for(int i = 0; i < otherHeadersEnd; i += 2) {
         AsciiString roName = this.otherHeaders[i];
         if (roName.hashCode() == nameHash && roName.contentEqualsIgnoreCase(name)) {
            values.add(this.otherHeaders[i + 1]);
         }
      }

      return values;
   }

   public List getAllAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public Boolean getBoolean(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToBoolean(value) : null;
   }

   public boolean getBoolean(CharSequence name, boolean defaultValue) {
      Boolean value = this.getBoolean(name);
      return value != null ? value : defaultValue;
   }

   public Byte getByte(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToByte(value) : null;
   }

   public byte getByte(CharSequence name, byte defaultValue) {
      Byte value = this.getByte(name);
      return value != null ? value : defaultValue;
   }

   public Character getChar(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToChar(value) : null;
   }

   public char getChar(CharSequence name, char defaultValue) {
      Character value = this.getChar(name);
      return value != null ? value : defaultValue;
   }

   public Short getShort(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToShort(value) : null;
   }

   public short getShort(CharSequence name, short defaultValue) {
      Short value = this.getShort(name);
      return value != null ? value : defaultValue;
   }

   public Integer getInt(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToInt(value) : null;
   }

   public int getInt(CharSequence name, int defaultValue) {
      Integer value = this.getInt(name);
      return value != null ? value : defaultValue;
   }

   public Long getLong(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToLong(value) : null;
   }

   public long getLong(CharSequence name, long defaultValue) {
      Long value = this.getLong(name);
      return value != null ? value : defaultValue;
   }

   public Float getFloat(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToFloat(value) : null;
   }

   public float getFloat(CharSequence name, float defaultValue) {
      Float value = this.getFloat(name);
      return value != null ? value : defaultValue;
   }

   public Double getDouble(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToDouble(value) : null;
   }

   public double getDouble(CharSequence name, double defaultValue) {
      Double value = this.getDouble(name);
      return value != null ? value : defaultValue;
   }

   public Long getTimeMillis(CharSequence name) {
      AsciiString value = this.get0(name);
      return value != null ? CharSequenceValueConverter.INSTANCE.convertToTimeMillis(value) : null;
   }

   public long getTimeMillis(CharSequence name, long defaultValue) {
      Long value = this.getTimeMillis(name);
      return value != null ? value : defaultValue;
   }

   public Boolean getBooleanAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public boolean getBooleanAndRemove(CharSequence name, boolean defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Byte getByteAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public byte getByteAndRemove(CharSequence name, byte defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Character getCharAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public char getCharAndRemove(CharSequence name, char defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Short getShortAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public short getShortAndRemove(CharSequence name, short defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Integer getIntAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public int getIntAndRemove(CharSequence name, int defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Long getLongAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public long getLongAndRemove(CharSequence name, long defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Float getFloatAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public float getFloatAndRemove(CharSequence name, float defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Double getDoubleAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public double getDoubleAndRemove(CharSequence name, double defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public Long getTimeMillisAndRemove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public long getTimeMillisAndRemove(CharSequence name, long defaultValue) {
      throw new UnsupportedOperationException("read only");
   }

   public boolean contains(CharSequence name) {
      return this.get(name) != null;
   }

   public boolean contains(CharSequence name, CharSequence value) {
      return this.contains(name, value, false);
   }

   public boolean containsObject(CharSequence name, Object value) {
      return value instanceof CharSequence ? this.contains(name, (CharSequence)value) : this.contains((CharSequence)name, (CharSequence)value.toString());
   }

   public boolean containsBoolean(CharSequence name, boolean value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsByte(CharSequence name, byte value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsChar(CharSequence name, char value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsShort(CharSequence name, short value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsInt(CharSequence name, int value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsLong(CharSequence name, long value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsFloat(CharSequence name, float value) {
      return false;
   }

   public boolean containsDouble(CharSequence name, double value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public boolean containsTimeMillis(CharSequence name, long value) {
      return this.contains((CharSequence)name, (CharSequence)String.valueOf(value));
   }

   public int size() {
      return this.pseudoHeaders.length + this.otherHeaders.length >>> 1;
   }

   public boolean isEmpty() {
      return this.pseudoHeaders.length == 0 && this.otherHeaders.length == 0;
   }

   public Set names() {
      if (this.isEmpty()) {
         return Collections.emptySet();
      } else {
         Set<CharSequence> names = new LinkedHashSet(this.size());
         int pseudoHeadersEnd = this.pseudoHeaders.length - 1;

         for(int i = 0; i < pseudoHeadersEnd; i += 2) {
            names.add(this.pseudoHeaders[i]);
         }

         int otherHeadersEnd = this.otherHeaders.length - 1;

         for(int i = 0; i < otherHeadersEnd; i += 2) {
            names.add(this.otherHeaders[i]);
         }

         return names;
      }
   }

   public Http2Headers add(CharSequence name, CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers add(CharSequence name, Iterable values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers add(CharSequence name, CharSequence... values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addObject(CharSequence name, Object value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addObject(CharSequence name, Iterable values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addObject(CharSequence name, Object... values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addBoolean(CharSequence name, boolean value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addByte(CharSequence name, byte value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addChar(CharSequence name, char value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addShort(CharSequence name, short value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addInt(CharSequence name, int value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addLong(CharSequence name, long value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addFloat(CharSequence name, float value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addDouble(CharSequence name, double value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers addTimeMillis(CharSequence name, long value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers add(Headers headers) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers set(CharSequence name, CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers set(CharSequence name, Iterable values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers set(CharSequence name, CharSequence... values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setObject(CharSequence name, Object value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setObject(CharSequence name, Iterable values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setObject(CharSequence name, Object... values) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setBoolean(CharSequence name, boolean value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setByte(CharSequence name, byte value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setChar(CharSequence name, char value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setShort(CharSequence name, short value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setInt(CharSequence name, int value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setLong(CharSequence name, long value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setFloat(CharSequence name, float value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setDouble(CharSequence name, double value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setTimeMillis(CharSequence name, long value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers set(Headers headers) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers setAll(Headers headers) {
      throw new UnsupportedOperationException("read only");
   }

   public boolean remove(CharSequence name) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers clear() {
      throw new UnsupportedOperationException("read only");
   }

   public Iterator iterator() {
      return new ReadOnlyIterator();
   }

   public Iterator valueIterator(CharSequence name) {
      return new ReadOnlyValueIterator(name);
   }

   public Http2Headers method(CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers scheme(CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers authority(CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers path(CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public Http2Headers status(CharSequence value) {
      throw new UnsupportedOperationException("read only");
   }

   public CharSequence method() {
      return this.get((CharSequence)Http2Headers.PseudoHeaderName.METHOD.value());
   }

   public CharSequence scheme() {
      return this.get((CharSequence)Http2Headers.PseudoHeaderName.SCHEME.value());
   }

   public CharSequence authority() {
      return this.get((CharSequence)Http2Headers.PseudoHeaderName.AUTHORITY.value());
   }

   public CharSequence path() {
      return this.get((CharSequence)Http2Headers.PseudoHeaderName.PATH.value());
   }

   public CharSequence status() {
      return this.get((CharSequence)Http2Headers.PseudoHeaderName.STATUS.value());
   }

   public boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
      int nameHash = AsciiString.hashCode(name);
      HashingStrategy<CharSequence> strategy = caseInsensitive ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER;
      int valueHash = strategy.hashCode(value);
      return contains(name, nameHash, value, valueHash, strategy, this.otherHeaders) || contains(name, nameHash, value, valueHash, strategy, this.pseudoHeaders);
   }

   private static boolean contains(CharSequence name, int nameHash, CharSequence value, int valueHash, HashingStrategy hashingStrategy, AsciiString[] headers) {
      int headersEnd = headers.length - 1;

      for(int i = 0; i < headersEnd; i += 2) {
         AsciiString roName = headers[i];
         AsciiString roValue = headers[i + 1];
         if (roName.hashCode() == nameHash && roValue.hashCode() == valueHash && roName.contentEqualsIgnoreCase(name) && hashingStrategy.equals(roValue, value)) {
            return true;
         }
      }

      return false;
   }

   public String toString() {
      StringBuilder builder = (new StringBuilder(this.getClass().getSimpleName())).append('[');
      String separator = "";

      for(Map.Entry entry : this) {
         builder.append(separator);
         builder.append((CharSequence)entry.getKey()).append(": ").append((CharSequence)entry.getValue());
         separator = ", ";
      }

      return builder.append(']').toString();
   }

   private final class ReadOnlyValueIterator implements Iterator {
      private int i;
      private final int nameHash;
      private final CharSequence name;
      private AsciiString[] current;
      private AsciiString next;

      ReadOnlyValueIterator(CharSequence name) {
         this.current = ReadOnlyHttp2Headers.this.pseudoHeaders.length != 0 ? ReadOnlyHttp2Headers.this.pseudoHeaders : ReadOnlyHttp2Headers.this.otherHeaders;
         this.nameHash = AsciiString.hashCode(name);
         this.name = name;
         this.calculateNext();
      }

      public boolean hasNext() {
         return this.next != null;
      }

      public CharSequence next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            CharSequence current = this.next;
            this.calculateNext();
            return current;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("read only");
      }

      private void calculateNext() {
         while(this.i < this.current.length) {
            AsciiString roName = this.current[this.i];
            if (roName.hashCode() == this.nameHash && roName.contentEqualsIgnoreCase(this.name)) {
               if (this.i + 1 < this.current.length) {
                  this.next = this.current[this.i + 1];
                  this.i += 2;
               }

               return;
            }

            this.i += 2;
         }

         if (this.current == ReadOnlyHttp2Headers.this.pseudoHeaders) {
            this.i = 0;
            this.current = ReadOnlyHttp2Headers.this.otherHeaders;
            this.calculateNext();
         } else {
            this.next = null;
         }

      }
   }

   private final class ReadOnlyIterator implements Map.Entry, Iterator {
      private int i;
      private AsciiString[] current;
      private AsciiString key;
      private AsciiString value;

      private ReadOnlyIterator() {
         this.current = ReadOnlyHttp2Headers.this.pseudoHeaders.length != 0 ? ReadOnlyHttp2Headers.this.pseudoHeaders : ReadOnlyHttp2Headers.this.otherHeaders;
      }

      public boolean hasNext() {
         return this.i != this.current.length;
      }

      public Map.Entry next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.key = this.current[this.i];
            this.value = this.current[this.i + 1];
            this.i += 2;
            if (this.i == this.current.length && this.current == ReadOnlyHttp2Headers.this.pseudoHeaders) {
               this.current = ReadOnlyHttp2Headers.this.otherHeaders;
               this.i = 0;
            }

            return this;
         }
      }

      public CharSequence getKey() {
         return this.key;
      }

      public CharSequence getValue() {
         return this.value;
      }

      public CharSequence setValue(CharSequence value) {
         throw new UnsupportedOperationException("read only");
      }

      public void remove() {
         throw new UnsupportedOperationException("read only");
      }

      public String toString() {
         return this.key.toString() + '=' + this.value.toString();
      }
   }
}
