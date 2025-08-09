package jodd.typeconverter;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import jodd.Jodd;
import jodd.datetime.JDateTime;
import jodd.mutable.MutableByte;
import jodd.mutable.MutableDouble;
import jodd.mutable.MutableFloat;
import jodd.mutable.MutableInteger;
import jodd.mutable.MutableLong;
import jodd.mutable.MutableShort;
import jodd.typeconverter.impl.ArrayConverter;
import jodd.typeconverter.impl.BigDecimalConverter;
import jodd.typeconverter.impl.BigIntegerConverter;
import jodd.typeconverter.impl.BooleanArrayConverter;
import jodd.typeconverter.impl.BooleanConverter;
import jodd.typeconverter.impl.ByteArrayConverter;
import jodd.typeconverter.impl.ByteConverter;
import jodd.typeconverter.impl.CalendarConverter;
import jodd.typeconverter.impl.CharacterArrayConverter;
import jodd.typeconverter.impl.CharacterConverter;
import jodd.typeconverter.impl.ClassArrayConverter;
import jodd.typeconverter.impl.ClassConverter;
import jodd.typeconverter.impl.CollectionConverter;
import jodd.typeconverter.impl.DateConverter;
import jodd.typeconverter.impl.DoubleArrayConverter;
import jodd.typeconverter.impl.DoubleConverter;
import jodd.typeconverter.impl.FileConverter;
import jodd.typeconverter.impl.FloatArrayConverter;
import jodd.typeconverter.impl.FloatConverter;
import jodd.typeconverter.impl.IntegerArrayConverter;
import jodd.typeconverter.impl.IntegerConverter;
import jodd.typeconverter.impl.JDateTimeConverter;
import jodd.typeconverter.impl.LocaleConverter;
import jodd.typeconverter.impl.LongArrayConverter;
import jodd.typeconverter.impl.LongConverter;
import jodd.typeconverter.impl.MutableByteConverter;
import jodd.typeconverter.impl.MutableDoubleConverter;
import jodd.typeconverter.impl.MutableFloatConverter;
import jodd.typeconverter.impl.MutableIntegerConverter;
import jodd.typeconverter.impl.MutableLongConverter;
import jodd.typeconverter.impl.MutableShortConverter;
import jodd.typeconverter.impl.ShortArrayConverter;
import jodd.typeconverter.impl.ShortConverter;
import jodd.typeconverter.impl.SqlDateConverter;
import jodd.typeconverter.impl.SqlTimeConverter;
import jodd.typeconverter.impl.SqlTimestampConverter;
import jodd.typeconverter.impl.StringArrayConverter;
import jodd.typeconverter.impl.StringConverter;
import jodd.typeconverter.impl.TimeZoneConverter;
import jodd.typeconverter.impl.URIConverter;
import jodd.typeconverter.impl.URLConverter;
import jodd.util.ReflectUtil;

public class TypeConverterManagerBean {
   private final HashMap converters = new HashMap(70);
   protected ConvertBean convertBean = new ConvertBean();

   public ConvertBean getConvertBean() {
      return this.convertBean;
   }

   public TypeConverterManagerBean() {
      this.registerDefaults();
   }

   public void registerDefaults() {
      this.register(String.class, new StringConverter());
      this.register(String[].class, new StringArrayConverter(this));
      IntegerConverter integerConverter = new IntegerConverter();
      this.register(Integer.class, integerConverter);
      this.register(Integer.TYPE, integerConverter);
      this.register(MutableInteger.class, new MutableIntegerConverter(this));
      ShortConverter shortConverter = new ShortConverter();
      this.register(Short.class, shortConverter);
      this.register(Short.TYPE, shortConverter);
      this.register(MutableShort.class, new MutableShortConverter(this));
      LongConverter longConverter = new LongConverter();
      this.register(Long.class, longConverter);
      this.register(Long.TYPE, longConverter);
      this.register(MutableLong.class, new MutableLongConverter(this));
      ByteConverter byteConverter = new ByteConverter();
      this.register(Byte.class, byteConverter);
      this.register(Byte.TYPE, byteConverter);
      this.register(MutableByte.class, new MutableByteConverter(this));
      FloatConverter floatConverter = new FloatConverter();
      this.register(Float.class, floatConverter);
      this.register(Float.TYPE, floatConverter);
      this.register(MutableFloat.class, new MutableFloatConverter(this));
      DoubleConverter doubleConverter = new DoubleConverter();
      this.register(Double.class, doubleConverter);
      this.register(Double.TYPE, doubleConverter);
      this.register(MutableDouble.class, new MutableDoubleConverter(this));
      BooleanConverter booleanConverter = new BooleanConverter();
      this.register(Boolean.class, booleanConverter);
      this.register(Boolean.TYPE, booleanConverter);
      CharacterConverter characterConverter = new CharacterConverter();
      this.register(Character.class, characterConverter);
      this.register(Character.TYPE, characterConverter);
      this.register(byte[].class, new ByteArrayConverter(this));
      this.register(short[].class, new ShortArrayConverter(this));
      this.register(int[].class, new IntegerArrayConverter(this));
      this.register(long[].class, new LongArrayConverter(this));
      this.register(float[].class, new FloatArrayConverter(this));
      this.register(double[].class, new DoubleArrayConverter(this));
      this.register(boolean[].class, new BooleanArrayConverter(this));
      this.register(char[].class, new CharacterArrayConverter(this));
      this.register(Integer[].class, new ArrayConverter(this, Integer.class));
      this.register(Long[].class, new ArrayConverter(this, Long.class));
      this.register(Byte[].class, new ArrayConverter(this, Byte.class));
      this.register(Short[].class, new ArrayConverter(this, Short.class));
      this.register(Float[].class, new ArrayConverter(this, Float.class));
      this.register(Double[].class, new ArrayConverter(this, Double.class));
      this.register(Boolean[].class, new ArrayConverter(this, Boolean.class));
      this.register(Character[].class, new ArrayConverter(this, Character.class));
      this.register(MutableInteger[].class, new ArrayConverter(this, MutableInteger.class));
      this.register(MutableLong[].class, new ArrayConverter(this, MutableLong.class));
      this.register(MutableByte[].class, new ArrayConverter(this, MutableByte.class));
      this.register(MutableShort[].class, new ArrayConverter(this, MutableShort.class));
      this.register(MutableFloat[].class, new ArrayConverter(this, MutableFloat.class));
      this.register(MutableDouble[].class, new ArrayConverter(this, MutableDouble.class));
      this.register(BigDecimal.class, new BigDecimalConverter());
      this.register(BigInteger.class, new BigIntegerConverter());
      this.register(BigDecimal[].class, new ArrayConverter(this, BigDecimal.class));
      this.register(BigInteger[].class, new ArrayConverter(this, BigInteger.class));
      this.register(Date.class, new DateConverter());
      this.register(java.sql.Date.class, new SqlDateConverter());
      this.register(Time.class, new SqlTimeConverter());
      this.register(Timestamp.class, new SqlTimestampConverter());
      this.register(Calendar.class, new CalendarConverter());
      this.register(GregorianCalendar.class, new CalendarConverter());
      this.register(JDateTime.class, new JDateTimeConverter());
      this.register(File.class, new FileConverter());
      this.register(Class.class, new ClassConverter());
      this.register(Class[].class, new ClassArrayConverter(this));
      this.register(URI.class, new URIConverter());
      this.register(URL.class, new URLConverter());
      this.register(Locale.class, new LocaleConverter());
      this.register(TimeZone.class, new TimeZoneConverter());
      if (Jodd.isModuleLoaded(13)) {
         Jodd.bind(13, this);
      }

   }

   public void register(Class type, TypeConverter typeConverter) {
      this.convertBean.register(type, typeConverter);
      this.converters.put(type, typeConverter);
   }

   public void unregister(Class type) {
      this.convertBean.register(type, (TypeConverter)null);
      this.converters.remove(type);
   }

   public TypeConverter lookup(Class type) {
      return (TypeConverter)this.converters.get(type);
   }

   public Object convertType(Object value, Class destinationType) {
      if (destinationType == Object.class) {
         return value;
      } else {
         TypeConverter converter = this.lookup(destinationType);
         if (converter != null) {
            return converter.convert(value);
         } else if (value == null) {
            return null;
         } else if (destinationType.isArray()) {
            ArrayConverter<T> arrayConverter = new ArrayConverter(this, destinationType.getComponentType());
            return arrayConverter.convert(value);
         } else {
            if (destinationType.isEnum()) {
               Object[] enums = destinationType.getEnumConstants();
               String valStr = value.toString();

               for(Object e : enums) {
                  if (e.toString().equals(valStr)) {
                     return e;
                  }
               }
            }

            if (ReflectUtil.isInstanceOf(value, destinationType)) {
               return value;
            } else if (ReflectUtil.isInterfaceImpl(destinationType, Collection.class)) {
               CollectionConverter<T> collectionConverter = new CollectionConverter(this, destinationType, Object.class);
               return collectionConverter.convert(value);
            } else {
               throw new TypeConversionException("Conversion failed: " + destinationType.getName());
            }
         }
      }
   }

   public Collection convertToCollection(Object value, Class destinationType, Class componentType) {
      if (value == null) {
         return null;
      } else if (ReflectUtil.isInstanceOf(value, destinationType)) {
         return (Collection)value;
      } else {
         if (componentType == null) {
            componentType = Object.class;
         }

         CollectionConverter collectionConverter = new CollectionConverter(destinationType, componentType);
         return collectionConverter.convert(value);
      }
   }
}
