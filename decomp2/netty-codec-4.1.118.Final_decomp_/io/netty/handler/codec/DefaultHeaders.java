package io.netty.handler.codec;

import io.netty.util.HashingStrategy;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class DefaultHeaders implements Headers {
   static final int HASH_CODE_SEED = -1028477387;
   private final HeaderEntry[] entries;
   protected final HeaderEntry head;
   private final byte hashMask;
   private final ValueConverter valueConverter;
   private final NameValidator nameValidator;
   private final ValueValidator valueValidator;
   private final HashingStrategy hashingStrategy;
   int size;

   public DefaultHeaders(ValueConverter valueConverter) {
      this(HashingStrategy.JAVA_HASHER, valueConverter);
   }

   public DefaultHeaders(ValueConverter valueConverter, NameValidator nameValidator) {
      this(HashingStrategy.JAVA_HASHER, valueConverter, nameValidator);
   }

   public DefaultHeaders(HashingStrategy nameHashingStrategy, ValueConverter valueConverter) {
      this(nameHashingStrategy, valueConverter, DefaultHeaders.NameValidator.NOT_NULL);
   }

   public DefaultHeaders(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, NameValidator nameValidator) {
      this(nameHashingStrategy, valueConverter, nameValidator, 16);
   }

   public DefaultHeaders(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, NameValidator nameValidator, int arraySizeHint) {
      this(nameHashingStrategy, valueConverter, nameValidator, arraySizeHint, DefaultHeaders.ValueValidator.NO_VALIDATION);
   }

   public DefaultHeaders(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, NameValidator nameValidator, int arraySizeHint, ValueValidator valueValidator) {
      this.valueConverter = (ValueConverter)ObjectUtil.checkNotNull(valueConverter, "valueConverter");
      this.nameValidator = (NameValidator)ObjectUtil.checkNotNull(nameValidator, "nameValidator");
      this.hashingStrategy = (HashingStrategy)ObjectUtil.checkNotNull(nameHashingStrategy, "nameHashingStrategy");
      this.valueValidator = (ValueValidator)ObjectUtil.checkNotNull(valueValidator, "valueValidator");
      this.entries = new HeaderEntry[MathUtil.findNextPositivePowerOfTwo(Math.max(2, Math.min(arraySizeHint, 128)))];
      this.hashMask = (byte)(this.entries.length - 1);
      this.head = new HeaderEntry();
   }

   public Object get(Object name) {
      ObjectUtil.checkNotNull(name, "name");
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      HeaderEntry<K, V> e = this.entries[i];

      V value;
      for(value = (V)null; e != null; e = e.next) {
         if (e.hash == h && this.hashingStrategy.equals(name, e.key)) {
            value = (V)e.value;
         }
      }

      return value;
   }

   public Object get(Object name, Object defaultValue) {
      V value = (V)this.get(name);
      return value == null ? defaultValue : value;
   }

   public Object getAndRemove(Object name) {
      int h = this.hashingStrategy.hashCode(name);
      return this.remove0(h, this.index(h), ObjectUtil.checkNotNull(name, "name"));
   }

   public Object getAndRemove(Object name, Object defaultValue) {
      V value = (V)this.getAndRemove(name);
      return value == null ? defaultValue : value;
   }

   public List getAll(Object name) {
      ObjectUtil.checkNotNull(name, "name");
      LinkedList<V> values = new LinkedList();
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);

      for(HeaderEntry<K, V> e = this.entries[i]; e != null; e = e.next) {
         if (e.hash == h && this.hashingStrategy.equals(name, e.key)) {
            values.addFirst(e.getValue());
         }
      }

      return values;
   }

   public Iterator valueIterator(Object name) {
      return new ValueIterator(name);
   }

   public List getAllAndRemove(Object name) {
      List<V> all = this.getAll(name);
      this.remove(name);
      return all;
   }

   public boolean contains(Object name) {
      return this.get(name) != null;
   }

   public boolean containsObject(Object name, Object value) {
      return this.contains(name, this.fromObject(name, value));
   }

   public boolean containsBoolean(Object name, boolean value) {
      return this.contains(name, this.fromBoolean(name, value));
   }

   public boolean containsByte(Object name, byte value) {
      return this.contains(name, this.fromByte(name, value));
   }

   public boolean containsChar(Object name, char value) {
      return this.contains(name, this.fromChar(name, value));
   }

   public boolean containsShort(Object name, short value) {
      return this.contains(name, this.fromShort(name, value));
   }

   public boolean containsInt(Object name, int value) {
      return this.contains(name, this.fromInt(name, value));
   }

   public boolean containsLong(Object name, long value) {
      return this.contains(name, this.fromLong(name, value));
   }

   public boolean containsFloat(Object name, float value) {
      return this.contains(name, this.fromFloat(name, value));
   }

   public boolean containsDouble(Object name, double value) {
      return this.contains(name, this.fromDouble(name, value));
   }

   public boolean containsTimeMillis(Object name, long value) {
      return this.contains(name, this.fromTimeMillis(name, value));
   }

   public boolean contains(Object name, Object value) {
      return this.contains(name, value, HashingStrategy.JAVA_HASHER);
   }

   public final boolean contains(Object name, Object value, HashingStrategy valueHashingStrategy) {
      ObjectUtil.checkNotNull(name, "name");
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);

      for(HeaderEntry<K, V> e = this.entries[i]; e != null; e = e.next) {
         if (e.hash == h && this.hashingStrategy.equals(name, e.key) && valueHashingStrategy.equals(value, e.value)) {
            return true;
         }
      }

      return false;
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.head == this.head.after;
   }

   public Set names() {
      if (this.isEmpty()) {
         return Collections.emptySet();
      } else {
         Set<K> names = new LinkedHashSet(this.size());

         for(HeaderEntry<K, V> e = this.head.after; e != this.head; e = e.after) {
            names.add(e.getKey());
         }

         return names;
      }
   }

   public Headers add(Object name, Object value) {
      this.validateName(this.nameValidator, true, name);
      this.validateValue(this.valueValidator, name, value);
      ObjectUtil.checkNotNull(value, "value");
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      this.add0(h, i, name, value);
      return this.thisT();
   }

   public Headers add(Object name, Iterable values) {
      this.validateName(this.nameValidator, true, name);
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);

      for(Object v : values) {
         this.validateValue(this.valueValidator, name, v);
         this.add0(h, i, name, v);
      }

      return this.thisT();
   }

   public Headers add(Object name, Object... values) {
      this.validateName(this.nameValidator, true, name);
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);

      for(Object v : values) {
         this.validateValue(this.valueValidator, name, v);
         this.add0(h, i, name, v);
      }

      return this.thisT();
   }

   public Headers addObject(Object name, Object value) {
      return this.add(name, this.fromObject(name, value));
   }

   public Headers addObject(Object name, Iterable values) {
      for(Object value : values) {
         this.addObject(name, value);
      }

      return this.thisT();
   }

   public Headers addObject(Object name, Object... values) {
      for(Object value : values) {
         this.addObject(name, value);
      }

      return this.thisT();
   }

   public Headers addInt(Object name, int value) {
      return this.add(name, this.fromInt(name, value));
   }

   public Headers addLong(Object name, long value) {
      return this.add(name, this.fromLong(name, value));
   }

   public Headers addDouble(Object name, double value) {
      return this.add(name, this.fromDouble(name, value));
   }

   public Headers addTimeMillis(Object name, long value) {
      return this.add(name, this.fromTimeMillis(name, value));
   }

   public Headers addChar(Object name, char value) {
      return this.add(name, this.fromChar(name, value));
   }

   public Headers addBoolean(Object name, boolean value) {
      return this.add(name, this.fromBoolean(name, value));
   }

   public Headers addFloat(Object name, float value) {
      return this.add(name, this.fromFloat(name, value));
   }

   public Headers addByte(Object name, byte value) {
      return this.add(name, this.fromByte(name, value));
   }

   public Headers addShort(Object name, short value) {
      return this.add(name, this.fromShort(name, value));
   }

   public Headers add(Headers headers) {
      if (headers == this) {
         throw new IllegalArgumentException("can't add to itself.");
      } else {
         this.addImpl(headers);
         return this.thisT();
      }
   }

   protected void addImpl(Headers headers) {
      if (headers instanceof DefaultHeaders) {
         DefaultHeaders<? extends K, ? extends V, T> defaultHeaders = (DefaultHeaders)headers;
         HeaderEntry<? extends K, ? extends V> e = defaultHeaders.head.after;
         if (defaultHeaders.hashingStrategy == this.hashingStrategy && defaultHeaders.nameValidator == this.nameValidator) {
            while(e != defaultHeaders.head) {
               this.add0(e.hash, this.index(e.hash), e.key, e.value);
               e = e.after;
            }
         } else {
            while(e != defaultHeaders.head) {
               this.add(e.key, e.value);
               e = e.after;
            }
         }
      } else {
         for(Map.Entry header : headers) {
            this.add(header.getKey(), header.getValue());
         }
      }

   }

   public Headers set(Object name, Object value) {
      this.validateName(this.nameValidator, false, name);
      this.validateValue(this.valueValidator, name, value);
      ObjectUtil.checkNotNull(value, "value");
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      this.remove0(h, i, name);
      this.add0(h, i, name, value);
      return this.thisT();
   }

   public Headers set(Object name, Iterable values) {
      this.validateName(this.nameValidator, false, name);
      ObjectUtil.checkNotNull(values, "values");
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      this.remove0(h, i, name);

      for(Object v : values) {
         if (v == null) {
            break;
         }

         this.validateValue(this.valueValidator, name, v);
         this.add0(h, i, name, v);
      }

      return this.thisT();
   }

   public Headers set(Object name, Object... values) {
      this.validateName(this.nameValidator, false, name);
      ObjectUtil.checkNotNull(values, "values");
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      this.remove0(h, i, name);

      for(Object v : values) {
         if (v == null) {
            break;
         }

         this.validateValue(this.valueValidator, name, v);
         this.add0(h, i, name, v);
      }

      return this.thisT();
   }

   public Headers setObject(Object name, Object value) {
      V convertedValue = (V)ObjectUtil.checkNotNull(this.fromObject(name, value), "convertedValue");
      return this.set(name, convertedValue);
   }

   public Headers setObject(Object name, Iterable values) {
      this.validateName(this.nameValidator, false, name);
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      this.remove0(h, i, name);

      for(Object v : values) {
         if (v == null) {
            break;
         }

         V converted = (V)this.fromObject(name, v);
         this.validateValue(this.valueValidator, name, converted);
         this.add0(h, i, name, converted);
      }

      return this.thisT();
   }

   public Headers setObject(Object name, Object... values) {
      this.validateName(this.nameValidator, false, name);
      int h = this.hashingStrategy.hashCode(name);
      int i = this.index(h);
      this.remove0(h, i, name);

      for(Object v : values) {
         if (v == null) {
            break;
         }

         V converted = (V)this.fromObject(name, v);
         this.validateValue(this.valueValidator, name, converted);
         this.add0(h, i, name, converted);
      }

      return this.thisT();
   }

   public Headers setInt(Object name, int value) {
      return this.set(name, this.fromInt(name, value));
   }

   public Headers setLong(Object name, long value) {
      return this.set(name, this.fromLong(name, value));
   }

   public Headers setDouble(Object name, double value) {
      return this.set(name, this.fromDouble(name, value));
   }

   public Headers setTimeMillis(Object name, long value) {
      return this.set(name, this.fromTimeMillis(name, value));
   }

   public Headers setFloat(Object name, float value) {
      return this.set(name, this.fromFloat(name, value));
   }

   public Headers setChar(Object name, char value) {
      return this.set(name, this.fromChar(name, value));
   }

   public Headers setBoolean(Object name, boolean value) {
      return this.set(name, this.fromBoolean(name, value));
   }

   public Headers setByte(Object name, byte value) {
      return this.set(name, this.fromByte(name, value));
   }

   public Headers setShort(Object name, short value) {
      return this.set(name, this.fromShort(name, value));
   }

   public Headers set(Headers headers) {
      if (headers != this) {
         this.clear();
         this.addImpl(headers);
      }

      return this.thisT();
   }

   public Headers setAll(Headers headers) {
      if (headers != this) {
         for(Object key : headers.names()) {
            this.remove(key);
         }

         this.addImpl(headers);
      }

      return this.thisT();
   }

   public boolean remove(Object name) {
      return this.getAndRemove(name) != null;
   }

   public Headers clear() {
      Arrays.fill(this.entries, (Object)null);
      this.head.before = this.head.after = this.head;
      this.size = 0;
      return this.thisT();
   }

   public Iterator iterator() {
      return new HeaderIterator();
   }

   public Boolean getBoolean(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toBoolean(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public boolean getBoolean(Object name, boolean defaultValue) {
      Boolean v = this.getBoolean(name);
      return v != null ? v : defaultValue;
   }

   public Byte getByte(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toByte(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public byte getByte(Object name, byte defaultValue) {
      Byte v = this.getByte(name);
      return v != null ? v : defaultValue;
   }

   public Character getChar(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toChar(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public char getChar(Object name, char defaultValue) {
      Character v = this.getChar(name);
      return v != null ? v : defaultValue;
   }

   public Short getShort(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toShort(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public short getShort(Object name, short defaultValue) {
      Short v = this.getShort(name);
      return v != null ? v : defaultValue;
   }

   public Integer getInt(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toInt(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public int getInt(Object name, int defaultValue) {
      Integer v = this.getInt(name);
      return v != null ? v : defaultValue;
   }

   public Long getLong(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toLong(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public long getLong(Object name, long defaultValue) {
      Long v = this.getLong(name);
      return v != null ? v : defaultValue;
   }

   public Float getFloat(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toFloat(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public float getFloat(Object name, float defaultValue) {
      Float v = this.getFloat(name);
      return v != null ? v : defaultValue;
   }

   public Double getDouble(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toDouble(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public double getDouble(Object name, double defaultValue) {
      Double v = this.getDouble(name);
      return v != null ? v : defaultValue;
   }

   public Long getTimeMillis(Object name) {
      V v = (V)this.get(name);

      try {
         return v != null ? this.toTimeMillis(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public long getTimeMillis(Object name, long defaultValue) {
      Long v = this.getTimeMillis(name);
      return v != null ? v : defaultValue;
   }

   public Boolean getBooleanAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toBoolean(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public boolean getBooleanAndRemove(Object name, boolean defaultValue) {
      Boolean v = this.getBooleanAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Byte getByteAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toByte(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public byte getByteAndRemove(Object name, byte defaultValue) {
      Byte v = this.getByteAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Character getCharAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toChar(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public char getCharAndRemove(Object name, char defaultValue) {
      Character v = this.getCharAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Short getShortAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toShort(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public short getShortAndRemove(Object name, short defaultValue) {
      Short v = this.getShortAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Integer getIntAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toInt(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public int getIntAndRemove(Object name, int defaultValue) {
      Integer v = this.getIntAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Long getLongAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toLong(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public long getLongAndRemove(Object name, long defaultValue) {
      Long v = this.getLongAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Float getFloatAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toFloat(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public float getFloatAndRemove(Object name, float defaultValue) {
      Float v = this.getFloatAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Double getDoubleAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toDouble(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public double getDoubleAndRemove(Object name, double defaultValue) {
      Double v = this.getDoubleAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public Long getTimeMillisAndRemove(Object name) {
      V v = (V)this.getAndRemove(name);

      try {
         return v != null ? this.toTimeMillis(name, v) : null;
      } catch (RuntimeException var4) {
         return null;
      }
   }

   public long getTimeMillisAndRemove(Object name, long defaultValue) {
      Long v = this.getTimeMillisAndRemove(name);
      return v != null ? v : defaultValue;
   }

   public boolean equals(Object o) {
      return !(o instanceof Headers) ? false : this.equals((Headers)o, HashingStrategy.JAVA_HASHER);
   }

   public int hashCode() {
      return this.hashCode(HashingStrategy.JAVA_HASHER);
   }

   public final boolean equals(Headers h2, HashingStrategy valueHashingStrategy) {
      if (h2.size() != this.size()) {
         return false;
      } else if (this == h2) {
         return true;
      } else {
         for(Object name : this.names()) {
            List<V> otherValues = h2.getAll(name);
            List<V> values = this.getAll(name);
            if (otherValues.size() != values.size()) {
               return false;
            }

            for(int i = 0; i < otherValues.size(); ++i) {
               if (!valueHashingStrategy.equals(otherValues.get(i), values.get(i))) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public final int hashCode(HashingStrategy valueHashingStrategy) {
      int result = -1028477387;

      for(Object name : this.names()) {
         result = 31 * result + this.hashingStrategy.hashCode(name);
         List<V> values = this.getAll(name);

         for(int i = 0; i < values.size(); ++i) {
            result = 31 * result + valueHashingStrategy.hashCode(values.get(i));
         }
      }

      return result;
   }

   public String toString() {
      return HeadersUtils.toString(this.getClass(), this.iterator(), this.size());
   }

   protected void validateName(NameValidator validator, boolean forAdd, Object name) {
      validator.validateName(name);
   }

   protected void validateValue(ValueValidator validator, Object name, Object value) {
      try {
         validator.validate(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Validation failed for header '" + name + "'", e);
      }
   }

   protected HeaderEntry newHeaderEntry(int h, Object name, Object value, HeaderEntry next) {
      return new HeaderEntry(h, name, value, next, this.head);
   }

   protected ValueConverter valueConverter() {
      return this.valueConverter;
   }

   protected NameValidator nameValidator() {
      return this.nameValidator;
   }

   protected ValueValidator valueValidator() {
      return this.valueValidator;
   }

   private int index(int hash) {
      return hash & this.hashMask;
   }

   private void add0(int h, int i, Object name, Object value) {
      this.entries[i] = this.newHeaderEntry(h, name, value, this.entries[i]);
      ++this.size;
   }

   private Object remove0(int h, int i, Object name) {
      HeaderEntry<K, V> e = this.entries[i];
      if (e == null) {
         return null;
      } else {
         V value = (V)null;

         for(HeaderEntry<K, V> next = e.next; next != null; next = e.next) {
            if (next.hash == h && this.hashingStrategy.equals(name, next.key)) {
               value = (V)next.value;
               e.next = next.next;
               next.remove();
               --this.size;
            } else {
               e = next;
            }
         }

         e = this.entries[i];
         if (e.hash == h && this.hashingStrategy.equals(name, e.key)) {
            if (value == null) {
               value = (V)e.value;
            }

            this.entries[i] = e.next;
            e.remove();
            --this.size;
         }

         return value;
      }
   }

   HeaderEntry remove0(HeaderEntry entry, HeaderEntry previous) {
      int i = this.index(entry.hash);
      HeaderEntry<K, V> firstEntry = this.entries[i];
      if (firstEntry == entry) {
         this.entries[i] = entry.next;
         previous = this.entries[i];
      } else if (previous == null) {
         previous = firstEntry;

         HeaderEntry<K, V> next;
         for(next = firstEntry.next; next != null && next != entry; next = next.next) {
            previous = next;
         }

         assert next != null : "Entry not found in its hash bucket: " + entry;

         previous.next = entry.next;
      } else {
         previous.next = entry.next;
      }

      entry.remove();
      --this.size;
      return previous;
   }

   private Headers thisT() {
      return this;
   }

   private Object fromObject(Object name, Object value) {
      try {
         return this.valueConverter.convertObject(ObjectUtil.checkNotNull(value, "value"));
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert object value for header '" + name + '\'', e);
      }
   }

   private Object fromBoolean(Object name, boolean value) {
      try {
         return this.valueConverter.convertBoolean(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert boolean value for header '" + name + '\'', e);
      }
   }

   private Object fromByte(Object name, byte value) {
      try {
         return this.valueConverter.convertByte(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert byte value for header '" + name + '\'', e);
      }
   }

   private Object fromChar(Object name, char value) {
      try {
         return this.valueConverter.convertChar(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert char value for header '" + name + '\'', e);
      }
   }

   private Object fromShort(Object name, short value) {
      try {
         return this.valueConverter.convertShort(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert short value for header '" + name + '\'', e);
      }
   }

   private Object fromInt(Object name, int value) {
      try {
         return this.valueConverter.convertInt(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert int value for header '" + name + '\'', e);
      }
   }

   private Object fromLong(Object name, long value) {
      try {
         return this.valueConverter.convertLong(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert long value for header '" + name + '\'', e);
      }
   }

   private Object fromFloat(Object name, float value) {
      try {
         return this.valueConverter.convertFloat(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert float value for header '" + name + '\'', e);
      }
   }

   private Object fromDouble(Object name, double value) {
      try {
         return this.valueConverter.convertDouble(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert double value for header '" + name + '\'', e);
      }
   }

   private Object fromTimeMillis(Object name, long value) {
      try {
         return this.valueConverter.convertTimeMillis(value);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Failed to convert millsecond value for header '" + name + '\'', e);
      }
   }

   private boolean toBoolean(Object name, Object value) {
      try {
         return this.valueConverter.convertToBoolean(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to boolean for header '" + name + '\'');
      }
   }

   private byte toByte(Object name, Object value) {
      try {
         return this.valueConverter.convertToByte(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to byte for header '" + name + '\'');
      }
   }

   private char toChar(Object name, Object value) {
      try {
         return this.valueConverter.convertToChar(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to char for header '" + name + '\'');
      }
   }

   private short toShort(Object name, Object value) {
      try {
         return this.valueConverter.convertToShort(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to short for header '" + name + '\'');
      }
   }

   private int toInt(Object name, Object value) {
      try {
         return this.valueConverter.convertToInt(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to int for header '" + name + '\'');
      }
   }

   private long toLong(Object name, Object value) {
      try {
         return this.valueConverter.convertToLong(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to long for header '" + name + '\'');
      }
   }

   private float toFloat(Object name, Object value) {
      try {
         return this.valueConverter.convertToFloat(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to float for header '" + name + '\'');
      }
   }

   private double toDouble(Object name, Object value) {
      try {
         return this.valueConverter.convertToDouble(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to double for header '" + name + '\'');
      }
   }

   private long toTimeMillis(Object name, Object value) {
      try {
         return this.valueConverter.convertToTimeMillis(value);
      } catch (IllegalArgumentException var4) {
         throw new IllegalArgumentException("Failed to convert header value to millsecond for header '" + name + '\'');
      }
   }

   public DefaultHeaders copy() {
      DefaultHeaders<K, V, T> copy = new DefaultHeaders(this.hashingStrategy, this.valueConverter, this.nameValidator, this.entries.length);
      copy.addImpl(this);
      return copy;
   }

   public interface NameValidator {
      NameValidator NOT_NULL = new NameValidator() {
         public void validateName(Object name) {
            ObjectUtil.checkNotNull(name, "name");
         }
      };

      void validateName(Object var1);
   }

   public interface ValueValidator {
      ValueValidator NO_VALIDATION = new ValueValidator() {
         public void validate(Object value) {
         }
      };

      void validate(Object var1);
   }

   private final class HeaderIterator implements Iterator {
      private HeaderEntry current;

      private HeaderIterator() {
         this.current = DefaultHeaders.this.head;
      }

      public boolean hasNext() {
         return this.current.after != DefaultHeaders.this.head;
      }

      public Map.Entry next() {
         this.current = this.current.after;
         if (this.current == DefaultHeaders.this.head) {
            throw new NoSuchElementException();
         } else {
            return this.current;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("read only");
      }
   }

   private final class ValueIterator implements Iterator {
      private final Object name;
      private final int hash;
      private HeaderEntry removalPrevious;
      private HeaderEntry previous;
      private HeaderEntry next;

      ValueIterator(Object name) {
         this.name = ObjectUtil.checkNotNull(name, "name");
         this.hash = DefaultHeaders.this.hashingStrategy.hashCode(name);
         this.calculateNext(DefaultHeaders.this.entries[DefaultHeaders.this.index(this.hash)]);
      }

      public boolean hasNext() {
         return this.next != null;
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            if (this.previous != null) {
               this.removalPrevious = this.previous;
            }

            this.previous = this.next;
            this.calculateNext(this.next.next);
            return this.previous.value;
         }
      }

      public void remove() {
         if (this.previous == null) {
            throw new IllegalStateException();
         } else {
            this.removalPrevious = DefaultHeaders.this.remove0(this.previous, this.removalPrevious);
            this.previous = null;
         }
      }

      private void calculateNext(HeaderEntry entry) {
         while(entry != null) {
            if (entry.hash == this.hash && DefaultHeaders.this.hashingStrategy.equals(this.name, entry.key)) {
               this.next = entry;
               return;
            }

            entry = entry.next;
         }

         this.next = null;
      }
   }

   protected static class HeaderEntry implements Map.Entry {
      protected final int hash;
      protected final Object key;
      protected Object value;
      protected HeaderEntry next;
      protected HeaderEntry before;
      protected HeaderEntry after;

      protected HeaderEntry(int hash, Object key) {
         this.hash = hash;
         this.key = key;
      }

      HeaderEntry(int hash, Object key, Object value, HeaderEntry next, HeaderEntry head) {
         this.hash = hash;
         this.key = key;
         this.value = value;
         this.next = next;
         this.after = head;
         this.before = head.before;
         this.pointNeighborsToThis();
      }

      HeaderEntry() {
         this.hash = -1;
         this.key = null;
         this.before = this.after = this;
      }

      protected final void pointNeighborsToThis() {
         this.before.after = this;
         this.after.before = this;
      }

      public final HeaderEntry before() {
         return this.before;
      }

      public final HeaderEntry after() {
         return this.after;
      }

      protected void remove() {
         this.before.after = this.after;
         this.after.before = this.before;
      }

      public final Object getKey() {
         return this.key;
      }

      public final Object getValue() {
         return this.value;
      }

      public final Object setValue(Object value) {
         ObjectUtil.checkNotNull(value, "value");
         V oldValue = (V)this.value;
         this.value = value;
         return oldValue;
      }

      public final String toString() {
         return this.key.toString() + '=' + this.value.toString();
      }

      public boolean equals(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            boolean var10000;
            label38: {
               label27: {
                  Map.Entry<?, ?> other = (Map.Entry)o;
                  if (this.getKey() == null) {
                     if (other.getKey() != null) {
                        break label27;
                     }
                  } else if (!this.getKey().equals(other.getKey())) {
                     break label27;
                  }

                  if (this.getValue() == null) {
                     if (other.getValue() == null) {
                        break label38;
                     }
                  } else if (this.getValue().equals(other.getValue())) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public int hashCode() {
         return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
      }
   }
}
