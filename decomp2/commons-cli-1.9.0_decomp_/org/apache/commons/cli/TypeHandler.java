package org.apache.commons.cli;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TypeHandler {
   private static final TypeHandler DEFAULT = new TypeHandler();
   private static final int HEX_RADIX = 16;
   private final Map converterMap;

   public static Class createClass(String className) throws ParseException {
      return (Class)createValue(className, Class.class);
   }

   public static Date createDate(String string) {
      return (Date)createValueUnchecked(string, Date.class);
   }

   public static Map createDefaultMap() {
      return putDefaultMap(new HashMap());
   }

   public static File createFile(String string) {
      return (File)createValueUnchecked(string, File.class);
   }

   /** @deprecated */
   @Deprecated
   public static File[] createFiles(String string) {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   /** @deprecated */
   @Deprecated
   public static Number createNumber(String string) throws ParseException {
      return (Number)createValue(string, Number.class);
   }

   /** @deprecated */
   @Deprecated
   public static Object createObject(String className) throws ParseException {
      return createValue(className, Object.class);
   }

   public static URL createURL(String string) throws ParseException {
      return (URL)createValue(string, URL.class);
   }

   public static Object createValue(String string, Class clazz) throws ParseException {
      try {
         return getDefault().getConverter(clazz).apply(string);
      } catch (Throwable e) {
         throw ParseException.wrap(e);
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object createValue(String string, Object obj) throws ParseException {
      return createValue(string, (Class)obj);
   }

   private static Object createValueUnchecked(String string, Class clazz) {
      try {
         return createValue(string, clazz);
      } catch (ParseException e) {
         throw new IllegalArgumentException(e);
      }
   }

   public static TypeHandler getDefault() {
      return DEFAULT;
   }

   /** @deprecated */
   @Deprecated
   public static FileInputStream openFile(String string) throws ParseException {
      return (FileInputStream)createValue(string, FileInputStream.class);
   }

   private static Map putDefaultMap(Map map) {
      map.put(Object.class, Converter.OBJECT);
      map.put(Class.class, Converter.CLASS);
      map.put(Date.class, Converter.DATE);
      map.put(File.class, Converter.FILE);
      map.put(Path.class, Converter.PATH);
      map.put(Number.class, Converter.NUMBER);
      map.put(URL.class, Converter.URL);
      map.put(FileInputStream.class, FileInputStream::new);
      map.put(Long.class, Long::parseLong);
      map.put(Integer.class, Integer::parseInt);
      map.put(Short.class, Short::parseShort);
      map.put(Byte.class, Byte::parseByte);
      map.put(Character.class, (Converter)(s) -> s.startsWith("\\u") ? Character.toChars(Integer.parseInt(s.substring(2), 16))[0] : s.charAt(0));
      map.put(Double.class, Double::parseDouble);
      map.put(Float.class, Float::parseFloat);
      map.put(BigInteger.class, BigInteger::new);
      map.put(BigDecimal.class, BigDecimal::new);
      return map;
   }

   public TypeHandler() {
      this(createDefaultMap());
   }

   public TypeHandler(Map converterMap) {
      this.converterMap = (Map)Objects.requireNonNull(converterMap, "converterMap");
   }

   public Converter getConverter(Class clazz) {
      return (Converter)this.converterMap.getOrDefault(clazz, Converter.DEFAULT);
   }
}
