package org.apache.orc.protobuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

public final class Internal {
   static final Charset US_ASCII = Charset.forName("US-ASCII");
   static final Charset UTF_8 = Charset.forName("UTF-8");
   static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
   private static final int DEFAULT_BUFFER_SIZE = 4096;
   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
   public static final ByteBuffer EMPTY_BYTE_BUFFER;
   public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;

   private Internal() {
   }

   static Object checkNotNull(Object obj) {
      if (obj == null) {
         throw new NullPointerException();
      } else {
         return obj;
      }
   }

   static Object checkNotNull(Object obj, String message) {
      if (obj == null) {
         throw new NullPointerException(message);
      } else {
         return obj;
      }
   }

   public static String stringDefaultValue(String bytes) {
      return new String(bytes.getBytes(ISO_8859_1), UTF_8);
   }

   public static ByteString bytesDefaultValue(String bytes) {
      return ByteString.copyFrom(bytes.getBytes(ISO_8859_1));
   }

   public static byte[] byteArrayDefaultValue(String bytes) {
      return bytes.getBytes(ISO_8859_1);
   }

   public static ByteBuffer byteBufferDefaultValue(String bytes) {
      return ByteBuffer.wrap(byteArrayDefaultValue(bytes));
   }

   public static ByteBuffer copyByteBuffer(ByteBuffer source) {
      ByteBuffer temp = source.duplicate();
      ((Buffer)temp).clear();
      ByteBuffer result = ByteBuffer.allocate(temp.capacity());
      result.put(temp);
      ((Buffer)result).clear();
      return result;
   }

   public static boolean isValidUtf8(ByteString byteString) {
      return byteString.isValidUtf8();
   }

   public static boolean isValidUtf8(byte[] byteArray) {
      return Utf8.isValidUtf8(byteArray);
   }

   public static byte[] toByteArray(String value) {
      return value.getBytes(UTF_8);
   }

   public static String toStringUtf8(byte[] bytes) {
      return new String(bytes, UTF_8);
   }

   public static int hashLong(long n) {
      return (int)(n ^ n >>> 32);
   }

   public static int hashBoolean(boolean b) {
      return b ? 1231 : 1237;
   }

   public static int hashEnum(EnumLite e) {
      return e.getNumber();
   }

   public static int hashEnumList(List list) {
      int hash = 1;

      for(EnumLite e : list) {
         hash = 31 * hash + hashEnum(e);
      }

      return hash;
   }

   public static boolean equals(List a, List b) {
      if (a.size() != b.size()) {
         return false;
      } else {
         for(int i = 0; i < a.size(); ++i) {
            if (!Arrays.equals((byte[])a.get(i), (byte[])b.get(i))) {
               return false;
            }
         }

         return true;
      }
   }

   public static int hashCode(List list) {
      int hash = 1;

      for(byte[] bytes : list) {
         hash = 31 * hash + hashCode(bytes);
      }

      return hash;
   }

   public static int hashCode(byte[] bytes) {
      return hashCode(bytes, 0, bytes.length);
   }

   static int hashCode(byte[] bytes, int offset, int length) {
      int h = partialHash(length, bytes, offset, length);
      return h == 0 ? 1 : h;
   }

   static int partialHash(int h, byte[] bytes, int offset, int length) {
      for(int i = offset; i < offset + length; ++i) {
         h = h * 31 + bytes[i];
      }

      return h;
   }

   public static boolean equalsByteBuffer(ByteBuffer a, ByteBuffer b) {
      if (a.capacity() != b.capacity()) {
         return false;
      } else {
         ByteBuffer aDuplicate = a.duplicate();
         Java8Compatibility.clear(aDuplicate);
         ByteBuffer bDuplicate = b.duplicate();
         Java8Compatibility.clear(bDuplicate);
         return aDuplicate.equals(bDuplicate);
      }
   }

   public static boolean equalsByteBuffer(List a, List b) {
      if (a.size() != b.size()) {
         return false;
      } else {
         for(int i = 0; i < a.size(); ++i) {
            if (!equalsByteBuffer((ByteBuffer)a.get(i), (ByteBuffer)b.get(i))) {
               return false;
            }
         }

         return true;
      }
   }

   public static int hashCodeByteBuffer(List list) {
      int hash = 1;

      for(ByteBuffer bytes : list) {
         hash = 31 * hash + hashCodeByteBuffer(bytes);
      }

      return hash;
   }

   public static int hashCodeByteBuffer(ByteBuffer bytes) {
      if (bytes.hasArray()) {
         int h = partialHash(bytes.capacity(), bytes.array(), bytes.arrayOffset(), bytes.capacity());
         return h == 0 ? 1 : h;
      } else {
         int bufferSize = bytes.capacity() > 4096 ? 4096 : bytes.capacity();
         byte[] buffer = new byte[bufferSize];
         ByteBuffer duplicated = bytes.duplicate();
         Java8Compatibility.clear(duplicated);

         int h;
         int length;
         for(h = bytes.capacity(); duplicated.remaining() > 0; h = partialHash(h, buffer, 0, length)) {
            length = duplicated.remaining() <= bufferSize ? duplicated.remaining() : bufferSize;
            duplicated.get(buffer, 0, length);
         }

         return h == 0 ? 1 : h;
      }
   }

   public static MessageLite getDefaultInstance(Class clazz) {
      try {
         java.lang.reflect.Method method = clazz.getMethod("getDefaultInstance");
         return (MessageLite)method.invoke(method);
      } catch (Exception e) {
         throw new RuntimeException("Failed to get default instance for " + clazz, e);
      }
   }

   static Object mergeMessage(Object destination, Object source) {
      return ((MessageLite)destination).toBuilder().mergeFrom((MessageLite)source).buildPartial();
   }

   static {
      EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);
      EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(EMPTY_BYTE_ARRAY);
   }

   public static class ListAdapter extends AbstractList {
      private final List fromList;
      private final Converter converter;

      public ListAdapter(List fromList, Converter converter) {
         this.fromList = fromList;
         this.converter = converter;
      }

      public Object get(int index) {
         return this.converter.convert(this.fromList.get(index));
      }

      public int size() {
         return this.fromList.size();
      }

      public interface Converter {
         Object convert(Object from);
      }
   }

   public static class MapAdapter extends AbstractMap {
      private final Map realMap;
      private final Converter valueConverter;

      public static Converter newEnumConverter(final EnumLiteMap enumMap, final EnumLite unrecognizedValue) {
         return new Converter() {
            public EnumLite doForward(Integer value) {
               T result = (T)enumMap.findValueByNumber(value);
               return result == null ? unrecognizedValue : result;
            }

            public Integer doBackward(EnumLite value) {
               return value.getNumber();
            }
         };
      }

      public MapAdapter(Map realMap, Converter valueConverter) {
         this.realMap = realMap;
         this.valueConverter = valueConverter;
      }

      public Object get(Object key) {
         RealValue result = (RealValue)this.realMap.get(key);
         return result == null ? null : this.valueConverter.doForward(result);
      }

      public Object put(Object key, Object value) {
         RealValue oldValue = (RealValue)this.realMap.put(key, this.valueConverter.doBackward(value));
         return oldValue == null ? null : this.valueConverter.doForward(oldValue);
      }

      public Set entrySet() {
         return new SetAdapter(this.realMap.entrySet());
      }

      private class SetAdapter extends AbstractSet {
         private final Set realSet;

         public SetAdapter(Set realSet) {
            this.realSet = realSet;
         }

         public Iterator iterator() {
            return MapAdapter.this.new IteratorAdapter(this.realSet.iterator());
         }

         public int size() {
            return this.realSet.size();
         }
      }

      private class IteratorAdapter implements Iterator {
         private final Iterator realIterator;

         public IteratorAdapter(Iterator realIterator) {
            this.realIterator = realIterator;
         }

         public boolean hasNext() {
            return this.realIterator.hasNext();
         }

         public Map.Entry next() {
            return MapAdapter.this.new EntryAdapter((Map.Entry)this.realIterator.next());
         }

         public void remove() {
            this.realIterator.remove();
         }
      }

      private class EntryAdapter implements Map.Entry {
         private final Map.Entry realEntry;

         public EntryAdapter(Map.Entry realEntry) {
            this.realEntry = realEntry;
         }

         public Object getKey() {
            return this.realEntry.getKey();
         }

         public Object getValue() {
            return MapAdapter.this.valueConverter.doForward(this.realEntry.getValue());
         }

         public Object setValue(Object value) {
            RealValue oldValue = (RealValue)this.realEntry.setValue(MapAdapter.this.valueConverter.doBackward(value));
            return oldValue == null ? null : MapAdapter.this.valueConverter.doForward(oldValue);
         }

         public boolean equals(Object o) {
            if (o == this) {
               return true;
            } else if (!(o instanceof Map.Entry)) {
               return false;
            } else {
               Map.Entry<?, ?> other = (Map.Entry)o;
               return this.getKey().equals(other.getKey()) && this.getValue().equals(this.getValue());
            }
         }

         public int hashCode() {
            return this.realEntry.hashCode();
         }
      }

      public interface Converter {
         Object doForward(Object object);

         Object doBackward(Object object);
      }
   }

   public interface IntList extends ProtobufList {
      int getInt(int index);

      void addInt(int element);

      @CanIgnoreReturnValue
      int setInt(int index, int element);

      IntList mutableCopyWithCapacity(int capacity);
   }

   public interface BooleanList extends ProtobufList {
      boolean getBoolean(int index);

      void addBoolean(boolean element);

      @CanIgnoreReturnValue
      boolean setBoolean(int index, boolean element);

      BooleanList mutableCopyWithCapacity(int capacity);
   }

   public interface LongList extends ProtobufList {
      long getLong(int index);

      void addLong(long element);

      @CanIgnoreReturnValue
      long setLong(int index, long element);

      LongList mutableCopyWithCapacity(int capacity);
   }

   public interface DoubleList extends ProtobufList {
      double getDouble(int index);

      void addDouble(double element);

      @CanIgnoreReturnValue
      double setDouble(int index, double element);

      DoubleList mutableCopyWithCapacity(int capacity);
   }

   public interface FloatList extends ProtobufList {
      float getFloat(int index);

      void addFloat(float element);

      @CanIgnoreReturnValue
      float setFloat(int index, float element);

      FloatList mutableCopyWithCapacity(int capacity);
   }

   public interface EnumLite {
      int getNumber();
   }

   public interface EnumLiteMap {
      EnumLite findValueByNumber(int number);
   }

   public interface EnumVerifier {
      boolean isInRange(int number);
   }

   public interface ProtobufList extends List, RandomAccess {
      void makeImmutable();

      boolean isModifiable();

      ProtobufList mutableCopyWithCapacity(int capacity);
   }
}
