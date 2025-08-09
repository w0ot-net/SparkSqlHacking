package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

public class DefaultSerializers {
   public static class VoidSerializer extends Serializer {
      public VoidSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Object object) {
      }

      public Object read(Kryo kryo, Input input, Class type) {
         return null;
      }
   }

   public static class BooleanSerializer extends Serializer {
      public BooleanSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Boolean object) {
         output.writeBoolean(object);
      }

      public Boolean read(Kryo kryo, Input input, Class type) {
         return input.readBoolean();
      }
   }

   public static class ByteSerializer extends Serializer {
      public ByteSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Byte object) {
         output.writeByte(object);
      }

      public Byte read(Kryo kryo, Input input, Class type) {
         return input.readByte();
      }
   }

   public static class CharSerializer extends Serializer {
      public CharSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Character object) {
         output.writeChar(object);
      }

      public Character read(Kryo kryo, Input input, Class type) {
         return input.readChar();
      }
   }

   public static class ShortSerializer extends Serializer {
      public ShortSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Short object) {
         output.writeShort(object);
      }

      public Short read(Kryo kryo, Input input, Class type) {
         return input.readShort();
      }
   }

   public static class IntSerializer extends Serializer {
      public IntSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Integer object) {
         output.writeInt(object, false);
      }

      public Integer read(Kryo kryo, Input input, Class type) {
         return input.readInt(false);
      }
   }

   public static class LongSerializer extends Serializer {
      public LongSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Long object) {
         output.writeLong(object, false);
      }

      public Long read(Kryo kryo, Input input, Class type) {
         return input.readLong(false);
      }
   }

   public static class FloatSerializer extends Serializer {
      public FloatSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Float object) {
         output.writeFloat(object);
      }

      public Float read(Kryo kryo, Input input, Class type) {
         return input.readFloat();
      }
   }

   public static class DoubleSerializer extends Serializer {
      public DoubleSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Double object) {
         output.writeDouble(object);
      }

      public Double read(Kryo kryo, Input input, Class type) {
         return input.readDouble();
      }
   }

   public static class StringSerializer extends Serializer {
      public StringSerializer() {
         this.setImmutable(true);
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, String object) {
         output.writeString(object);
      }

      public String read(Kryo kryo, Input input, Class type) {
         return input.readString();
      }
   }

   public static class BigIntegerSerializer extends Serializer {
      public BigIntegerSerializer() {
         this.setImmutable(true);
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, BigInteger object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else if (object == BigInteger.ZERO) {
            output.writeVarInt(2, true);
            output.writeByte((int)0);
         } else {
            byte[] bytes = object.toByteArray();
            output.writeVarInt(bytes.length + 1, true);
            output.writeBytes(bytes);
         }
      }

      public BigInteger read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         if (length == 0) {
            return null;
         } else {
            byte[] bytes = input.readBytes(length - 1);
            if (type != BigInteger.class && type != null) {
               try {
                  Constructor<BigInteger> constructor = type.getConstructor(byte[].class);
                  if (!constructor.isAccessible()) {
                     try {
                        constructor.setAccessible(true);
                     } catch (SecurityException var8) {
                     }
                  }

                  return (BigInteger)constructor.newInstance(bytes);
               } catch (Exception ex) {
                  throw new KryoException(ex);
               }
            } else {
               if (length == 2) {
                  switch (bytes[0]) {
                     case 0:
                        return BigInteger.ZERO;
                     case 1:
                        return BigInteger.ONE;
                     case 10:
                        return BigInteger.TEN;
                  }
               }

               return new BigInteger(bytes);
            }
         }
      }
   }

   public static class BigDecimalSerializer extends Serializer {
      private final BigIntegerSerializer bigIntegerSerializer = new BigIntegerSerializer();

      public BigDecimalSerializer() {
         this.setAcceptsNull(true);
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, BigDecimal object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else if (object == BigDecimal.ZERO) {
            this.bigIntegerSerializer.write(kryo, output, BigInteger.ZERO);
            output.writeInt(0, false);
         } else {
            this.bigIntegerSerializer.write(kryo, output, object.unscaledValue());
            output.writeInt(object.scale(), false);
         }
      }

      public BigDecimal read(Kryo kryo, Input input, Class type) {
         BigInteger unscaledValue = this.bigIntegerSerializer.read(kryo, input, BigInteger.class);
         if (unscaledValue == null) {
            return null;
         } else {
            int scale = input.readInt(false);
            if (type != BigDecimal.class && type != null) {
               try {
                  Constructor<BigDecimal> constructor = type.getConstructor(BigInteger.class, Integer.TYPE);
                  if (!constructor.isAccessible()) {
                     try {
                        constructor.setAccessible(true);
                     } catch (SecurityException var8) {
                     }
                  }

                  return (BigDecimal)constructor.newInstance(unscaledValue, scale);
               } catch (Exception ex) {
                  throw new KryoException(ex);
               }
            } else {
               return unscaledValue == BigInteger.ZERO && scale == 0 ? BigDecimal.ZERO : new BigDecimal(unscaledValue, scale);
            }
         }
      }
   }

   public static class ClassSerializer extends Serializer {
      public ClassSerializer() {
         this.setImmutable(true);
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, Class object) {
         kryo.writeClass(output, object);
         output.writeByte(object != null && object.isPrimitive() ? 1 : 0);
      }

      public Class read(Kryo kryo, Input input, Class type) {
         Registration registration = kryo.readClass(input);
         int isPrimitive = input.read();
         Class typ = registration != null ? registration.getType() : null;
         if (typ != null && typ.isPrimitive()) {
            return isPrimitive == 1 ? typ : Util.getWrapperClass(typ);
         } else {
            return typ;
         }
      }
   }

   public static class DateSerializer extends Serializer {
      private Date create(Kryo kryo, Class type, long time) throws KryoException {
         if (type != Date.class && type != null) {
            if (type == Timestamp.class) {
               return new Timestamp(time);
            } else if (type == java.sql.Date.class) {
               return new java.sql.Date(time);
            } else if (type == Time.class) {
               return new Time(time);
            } else {
               try {
                  Constructor<? extends Date> constructor = type.getConstructor(Long.TYPE);
                  if (!constructor.isAccessible()) {
                     try {
                        constructor.setAccessible(true);
                     } catch (SecurityException var7) {
                     }
                  }

                  return (Date)constructor.newInstance(time);
               } catch (Exception var8) {
                  Date d = (Date)kryo.newInstance(type);
                  d.setTime(time);
                  return d;
               }
            }
         } else {
            return new Date(time);
         }
      }

      public void write(Kryo kryo, Output output, Date object) {
         output.writeLong(object.getTime(), true);
      }

      public Date read(Kryo kryo, Input input, Class type) {
         return this.create(kryo, type, input.readLong(true));
      }

      public Date copy(Kryo kryo, Date original) {
         return this.create(kryo, original.getClass(), original.getTime());
      }
   }

   public static class EnumSerializer extends Serializer {
      private Object[] enumConstants;

      public EnumSerializer(Class type) {
         this.setImmutable(true);
         this.setAcceptsNull(true);
         this.enumConstants = type.getEnumConstants();
         if (this.enumConstants == null && !Enum.class.equals(type)) {
            throw new IllegalArgumentException("The type must be an enum: " + type);
         }
      }

      public void write(Kryo kryo, Output output, Enum object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.ordinal() + 1, true);
         }
      }

      public Enum read(Kryo kryo, Input input, Class type) {
         int ordinal = input.readVarInt(true);
         if (ordinal == 0) {
            return null;
         } else {
            --ordinal;
            if (ordinal >= 0 && ordinal <= this.enumConstants.length - 1) {
               Object constant = this.enumConstants[ordinal];
               return (Enum)constant;
            } else {
               throw new KryoException("Invalid ordinal for enum \"" + type.getName() + "\": " + ordinal);
            }
         }
      }
   }

   public static class EnumSetSerializer extends Serializer {
      public void write(Kryo kryo, Output output, EnumSet object) {
         Serializer serializer;
         if (object.isEmpty()) {
            EnumSet tmp = EnumSet.complementOf(object);
            if (tmp.isEmpty()) {
               throw new KryoException("An EnumSet must have a defined Enum to be serialized.");
            }

            serializer = kryo.writeClass(output, tmp.iterator().next().getClass()).getSerializer();
         } else {
            serializer = kryo.writeClass(output, object.iterator().next().getClass()).getSerializer();
         }

         output.writeInt(object.size(), true);

         for(Object element : object) {
            serializer.write(kryo, output, element);
         }

      }

      public EnumSet read(Kryo kryo, Input input, Class type) {
         Registration registration = kryo.readClass(input);
         EnumSet object = EnumSet.noneOf(registration.getType());
         Serializer serializer = registration.getSerializer();
         int length = input.readInt(true);

         for(int i = 0; i < length; ++i) {
            object.add(serializer.read(kryo, input, (Class)null));
         }

         return object;
      }

      public EnumSet copy(Kryo kryo, EnumSet original) {
         return EnumSet.copyOf(original);
      }
   }

   public static class CurrencySerializer extends Serializer {
      public CurrencySerializer() {
         this.setImmutable(true);
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, Currency object) {
         output.writeString(object == null ? null : object.getCurrencyCode());
      }

      public Currency read(Kryo kryo, Input input, Class type) {
         String currencyCode = input.readString();
         return currencyCode == null ? null : Currency.getInstance(currencyCode);
      }
   }

   public static class StringBufferSerializer extends Serializer {
      public StringBufferSerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, StringBuffer object) {
         output.writeString((CharSequence)object);
      }

      public StringBuffer read(Kryo kryo, Input input, Class type) {
         String value = input.readString();
         return value == null ? null : new StringBuffer(value);
      }

      public StringBuffer copy(Kryo kryo, StringBuffer original) {
         return new StringBuffer(original);
      }
   }

   public static class StringBuilderSerializer extends Serializer {
      public StringBuilderSerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, StringBuilder object) {
         output.writeString((CharSequence)object);
      }

      public StringBuilder read(Kryo kryo, Input input, Class type) {
         return input.readStringBuilder();
      }

      public StringBuilder copy(Kryo kryo, StringBuilder original) {
         return new StringBuilder(original);
      }
   }

   public static class KryoSerializableSerializer extends Serializer {
      public void write(Kryo kryo, Output output, KryoSerializable object) {
         object.write(kryo, output);
      }

      public KryoSerializable read(Kryo kryo, Input input, Class type) {
         KryoSerializable object = (KryoSerializable)kryo.newInstance(type);
         kryo.reference(object);
         object.read(kryo, input);
         return object;
      }
   }

   public static class CollectionsEmptyListSerializer extends Serializer {
      public CollectionsEmptyListSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Object object) {
      }

      public Object read(Kryo kryo, Input input, Class type) {
         return Collections.EMPTY_LIST;
      }
   }

   public static class CollectionsEmptyMapSerializer extends Serializer {
      public CollectionsEmptyMapSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Object object) {
      }

      public Object read(Kryo kryo, Input input, Class type) {
         return Collections.EMPTY_MAP;
      }
   }

   public static class CollectionsEmptySetSerializer extends Serializer {
      public CollectionsEmptySetSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Object object) {
      }

      public Object read(Kryo kryo, Input input, Class type) {
         return Collections.EMPTY_SET;
      }
   }

   public static class CollectionsSingletonListSerializer extends Serializer {
      public CollectionsSingletonListSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, List object) {
         kryo.writeClassAndObject(output, object.get(0));
      }

      public List read(Kryo kryo, Input input, Class type) {
         return Collections.singletonList(kryo.readClassAndObject(input));
      }
   }

   public static class CollectionsSingletonMapSerializer extends Serializer {
      public CollectionsSingletonMapSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Map object) {
         Map.Entry entry = (Map.Entry)object.entrySet().iterator().next();
         kryo.writeClassAndObject(output, entry.getKey());
         kryo.writeClassAndObject(output, entry.getValue());
      }

      public Map read(Kryo kryo, Input input, Class type) {
         Object key = kryo.readClassAndObject(input);
         Object value = kryo.readClassAndObject(input);
         return Collections.singletonMap(key, value);
      }
   }

   public static class CollectionsSingletonSetSerializer extends Serializer {
      public CollectionsSingletonSetSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Set object) {
         kryo.writeClassAndObject(output, object.iterator().next());
      }

      public Set read(Kryo kryo, Input input, Class type) {
         return Collections.singleton(kryo.readClassAndObject(input));
      }
   }

   public static class TimeZoneSerializer extends Serializer {
      public TimeZoneSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, TimeZone object) {
         output.writeString(object.getID());
      }

      public TimeZone read(Kryo kryo, Input input, Class type) {
         return TimeZone.getTimeZone(input.readString());
      }
   }

   public static class CalendarSerializer extends Serializer {
      private static final long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;
      TimeZoneSerializer timeZoneSerializer = new TimeZoneSerializer();

      public void write(Kryo kryo, Output output, Calendar object) {
         this.timeZoneSerializer.write(kryo, output, object.getTimeZone());
         output.writeLong(object.getTimeInMillis(), true);
         output.writeBoolean(object.isLenient());
         output.writeInt(object.getFirstDayOfWeek(), true);
         output.writeInt(object.getMinimalDaysInFirstWeek(), true);
         if (object instanceof GregorianCalendar) {
            output.writeLong(((GregorianCalendar)object).getGregorianChange().getTime(), false);
         } else {
            output.writeLong(-12219292800000L, false);
         }

      }

      public Calendar read(Kryo kryo, Input input, Class type) {
         Calendar result = Calendar.getInstance(this.timeZoneSerializer.read(kryo, input, TimeZone.class));
         result.setTimeInMillis(input.readLong(true));
         result.setLenient(input.readBoolean());
         result.setFirstDayOfWeek(input.readInt(true));
         result.setMinimalDaysInFirstWeek(input.readInt(true));
         long gregorianChange = input.readLong(false);
         if (gregorianChange != -12219292800000L && result instanceof GregorianCalendar) {
            ((GregorianCalendar)result).setGregorianChange(new Date(gregorianChange));
         }

         return result;
      }

      public Calendar copy(Kryo kryo, Calendar original) {
         return (Calendar)original.clone();
      }
   }

   public static class TreeMapSerializer extends MapSerializer {
      public void write(Kryo kryo, Output output, Map map) {
         TreeMap treeMap = (TreeMap)map;
         kryo.writeClassAndObject(output, treeMap.comparator());
         super.write(kryo, output, map);
      }

      protected Map create(Kryo kryo, Input input, Class type) {
         return this.createTreeMap(type, (Comparator)kryo.readClassAndObject(input));
      }

      protected Map createCopy(Kryo kryo, Map original) {
         return this.createTreeMap(original.getClass(), ((TreeMap)original).comparator());
      }

      private TreeMap createTreeMap(Class type, Comparator comparator) {
         if (type != TreeMap.class && type != null) {
            try {
               Constructor constructor = type.getConstructor(Comparator.class);
               if (!constructor.isAccessible()) {
                  try {
                     constructor.setAccessible(true);
                  } catch (SecurityException var5) {
                  }
               }

               return (TreeMap)constructor.newInstance(comparator);
            } catch (Exception ex) {
               throw new KryoException(ex);
            }
         } else {
            return new TreeMap(comparator);
         }
      }
   }

   public static class TreeSetSerializer extends CollectionSerializer {
      public void write(Kryo kryo, Output output, Collection collection) {
         TreeSet treeSet = (TreeSet)collection;
         kryo.writeClassAndObject(output, treeSet.comparator());
         super.write(kryo, output, collection);
      }

      protected TreeSet create(Kryo kryo, Input input, Class type) {
         return this.createTreeSet(type, (Comparator)kryo.readClassAndObject(input));
      }

      protected TreeSet createCopy(Kryo kryo, Collection original) {
         return this.createTreeSet(original.getClass(), ((TreeSet)original).comparator());
      }

      private TreeSet createTreeSet(Class type, Comparator comparator) {
         if (type != TreeSet.class && type != null) {
            try {
               Constructor constructor = type.getConstructor(Comparator.class);
               if (!constructor.isAccessible()) {
                  try {
                     constructor.setAccessible(true);
                  } catch (SecurityException var5) {
                  }
               }

               return (TreeSet)constructor.newInstance(comparator);
            } catch (Exception ex) {
               throw new KryoException(ex);
            }
         } else {
            return new TreeSet(comparator);
         }
      }
   }

   public static class LocaleSerializer extends Serializer {
      public static final Locale SPANISH = new Locale("es", "", "");
      public static final Locale SPAIN = new Locale("es", "ES", "");

      public LocaleSerializer() {
         this.setImmutable(true);
      }

      protected Locale create(String language, String country, String variant) {
         Locale defaultLocale = Locale.getDefault();
         if (isSameLocale(defaultLocale, language, country, variant)) {
            return defaultLocale;
         } else if (defaultLocale != Locale.US && isSameLocale(Locale.US, language, country, variant)) {
            return Locale.US;
         } else if (isSameLocale(Locale.ENGLISH, language, country, variant)) {
            return Locale.ENGLISH;
         } else if (isSameLocale(Locale.GERMAN, language, country, variant)) {
            return Locale.GERMAN;
         } else if (isSameLocale(SPANISH, language, country, variant)) {
            return SPANISH;
         } else if (isSameLocale(Locale.FRENCH, language, country, variant)) {
            return Locale.FRENCH;
         } else if (isSameLocale(Locale.ITALIAN, language, country, variant)) {
            return Locale.ITALIAN;
         } else if (isSameLocale(Locale.JAPANESE, language, country, variant)) {
            return Locale.JAPANESE;
         } else if (isSameLocale(Locale.KOREAN, language, country, variant)) {
            return Locale.KOREAN;
         } else if (isSameLocale(Locale.SIMPLIFIED_CHINESE, language, country, variant)) {
            return Locale.SIMPLIFIED_CHINESE;
         } else if (isSameLocale(Locale.CHINESE, language, country, variant)) {
            return Locale.CHINESE;
         } else if (isSameLocale(Locale.TRADITIONAL_CHINESE, language, country, variant)) {
            return Locale.TRADITIONAL_CHINESE;
         } else if (isSameLocale(Locale.UK, language, country, variant)) {
            return Locale.UK;
         } else if (isSameLocale(Locale.GERMANY, language, country, variant)) {
            return Locale.GERMANY;
         } else if (isSameLocale(SPAIN, language, country, variant)) {
            return SPAIN;
         } else if (isSameLocale(Locale.FRANCE, language, country, variant)) {
            return Locale.FRANCE;
         } else if (isSameLocale(Locale.ITALY, language, country, variant)) {
            return Locale.ITALY;
         } else if (isSameLocale(Locale.JAPAN, language, country, variant)) {
            return Locale.JAPAN;
         } else if (isSameLocale(Locale.KOREA, language, country, variant)) {
            return Locale.KOREA;
         } else if (isSameLocale(Locale.CANADA, language, country, variant)) {
            return Locale.CANADA;
         } else {
            return isSameLocale(Locale.CANADA_FRENCH, language, country, variant) ? Locale.CANADA_FRENCH : new Locale(language, country, variant);
         }
      }

      public void write(Kryo kryo, Output output, Locale l) {
         output.writeAscii(l.getLanguage());
         output.writeAscii(l.getCountry());
         output.writeString(l.getVariant());
      }

      public Locale read(Kryo kryo, Input input, Class type) {
         String language = input.readString();
         String country = input.readString();
         String variant = input.readString();
         return this.create(language, country, variant);
      }

      protected static boolean isSameLocale(Locale locale, String language, String country, String variant) {
         try {
            return locale.getLanguage().equals(language) && locale.getCountry().equals(country) && locale.getVariant().equals(variant);
         } catch (NullPointerException var5) {
            return false;
         }
      }
   }

   public static class CharsetSerializer extends Serializer {
      public CharsetSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, Charset object) {
         output.writeString(object.name());
      }

      public Charset read(Kryo kryo, Input input, Class type) {
         return Charset.forName(input.readString());
      }
   }

   public static class URLSerializer extends Serializer {
      public URLSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, URL object) {
         output.writeString(object.toExternalForm());
      }

      public URL read(Kryo kryo, Input input, Class type) {
         try {
            return new URL(input.readString());
         } catch (MalformedURLException e) {
            throw new KryoException(e);
         }
      }
   }
}
