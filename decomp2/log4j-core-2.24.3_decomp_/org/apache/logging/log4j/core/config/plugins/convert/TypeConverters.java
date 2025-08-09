package org.apache.logging.log4j.core.config.plugins.convert;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Provider;
import java.security.Security;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rolling.action.Duration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.util.CronExpression;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.Strings;

public final class TypeConverters {
   public static final String CATEGORY = "TypeConverter";
   private static final Logger LOGGER = StatusLogger.getLogger();

   public static Object convert(final String s, final Class clazz, final Object defaultValue) {
      TypeConverter<T> converter = TypeConverterRegistry.getInstance().findCompatibleConverter(clazz);
      if (s == null) {
         return parseDefaultValue(converter, defaultValue);
      } else {
         try {
            return converter.convert(s);
         } catch (Exception e) {
            LOGGER.warn("Error while converting string [{}] to type [{}]. Using default value [{}].", s, clazz, defaultValue, e);
            return parseDefaultValue(converter, defaultValue);
         }
      }
   }

   private static Object parseDefaultValue(final TypeConverter converter, final Object defaultValue) {
      if (defaultValue == null) {
         return null;
      } else if (!(defaultValue instanceof String)) {
         return defaultValue;
      } else {
         try {
            return converter.convert((String)defaultValue);
         } catch (Exception e) {
            LOGGER.debug("Can't parse default value [{}] for type [{}].", defaultValue, converter.getClass(), e);
            return null;
         }
      }
   }

   @Plugin(
      name = "BigDecimal",
      category = "TypeConverter"
   )
   public static class BigDecimalConverter implements TypeConverter {
      public BigDecimal convert(final String s) {
         return new BigDecimal(s);
      }
   }

   @Plugin(
      name = "BigInteger",
      category = "TypeConverter"
   )
   public static class BigIntegerConverter implements TypeConverter {
      public BigInteger convert(final String s) {
         return new BigInteger(s);
      }
   }

   @Plugin(
      name = "Boolean",
      category = "TypeConverter"
   )
   public static class BooleanConverter implements TypeConverter {
      public Boolean convert(final String s) {
         return Boolean.valueOf(s);
      }
   }

   @Plugin(
      name = "ByteArray",
      category = "TypeConverter"
   )
   public static class ByteArrayConverter implements TypeConverter {
      private static final String PREFIX_0x = "0x";
      private static final String PREFIX_BASE64 = "Base64:";

      public byte[] convert(final String value) {
         byte[] bytes;
         if (value != null && !value.isEmpty()) {
            if (value.startsWith("Base64:")) {
               String lexicalXSDBase64Binary = value.substring("Base64:".length());
               bytes = Base64Converter.parseBase64Binary(lexicalXSDBase64Binary);
            } else if (value.startsWith("0x")) {
               String lexicalXSDHexBinary = value.substring("0x".length());
               bytes = HexConverter.parseHexBinary(lexicalXSDHexBinary);
            } else {
               bytes = value.getBytes(Charset.defaultCharset());
            }
         } else {
            bytes = Constants.EMPTY_BYTE_ARRAY;
         }

         return bytes;
      }
   }

   @Plugin(
      name = "Byte",
      category = "TypeConverter"
   )
   public static class ByteConverter implements TypeConverter {
      public Byte convert(final String s) {
         return Byte.valueOf(s);
      }
   }

   @Plugin(
      name = "Character",
      category = "TypeConverter"
   )
   public static class CharacterConverter implements TypeConverter {
      public Character convert(final String s) {
         if (s.length() != 1) {
            throw new IllegalArgumentException("Character string must be of length 1: " + s);
         } else {
            return s.toCharArray()[0];
         }
      }
   }

   @Plugin(
      name = "CharacterArray",
      category = "TypeConverter"
   )
   public static class CharArrayConverter implements TypeConverter {
      public char[] convert(final String s) {
         return s.toCharArray();
      }
   }

   @Plugin(
      name = "Charset",
      category = "TypeConverter"
   )
   public static class CharsetConverter implements TypeConverter {
      public Charset convert(final String s) {
         return Charset.forName(s);
      }
   }

   @Plugin(
      name = "Class",
      category = "TypeConverter"
   )
   public static class ClassConverter implements TypeConverter {
      public Class convert(final String s) throws ClassNotFoundException {
         switch (Strings.toRootLowerCase(s)) {
            case "boolean":
               return Boolean.TYPE;
            case "byte":
               return Byte.TYPE;
            case "char":
               return Character.TYPE;
            case "double":
               return Double.TYPE;
            case "float":
               return Float.TYPE;
            case "int":
               return Integer.TYPE;
            case "long":
               return Long.TYPE;
            case "short":
               return Short.TYPE;
            case "void":
               return Void.TYPE;
            default:
               return LoaderUtil.loadClass(s);
         }
      }
   }

   @Plugin(
      name = "CronExpression",
      category = "TypeConverter"
   )
   public static class CronExpressionConverter implements TypeConverter {
      public CronExpression convert(final String s) throws Exception {
         return new CronExpression(s);
      }
   }

   @Plugin(
      name = "Double",
      category = "TypeConverter"
   )
   public static class DoubleConverter implements TypeConverter {
      public Double convert(final String s) {
         return Double.valueOf(s);
      }
   }

   /** @deprecated */
   @Plugin(
      name = "Duration",
      category = "TypeConverter"
   )
   @Deprecated
   public static class DurationConverter implements TypeConverter {
      public Duration convert(final String s) {
         return Duration.parse(s);
      }
   }

   @Plugin(
      name = "File",
      category = "TypeConverter"
   )
   public static class FileConverter implements TypeConverter {
      @SuppressFBWarnings(
         value = {"PATH_TRAVERSAL_IN"},
         justification = "The name of the accessed file is based on a configuration value."
      )
      public File convert(final String s) {
         return new File(s);
      }
   }

   @Plugin(
      name = "Float",
      category = "TypeConverter"
   )
   public static class FloatConverter implements TypeConverter {
      public Float convert(final String s) {
         return Float.valueOf(s);
      }
   }

   @Plugin(
      name = "InetAddress",
      category = "TypeConverter"
   )
   public static class InetAddressConverter implements TypeConverter {
      public InetAddress convert(final String s) throws Exception {
         return InetAddress.getByName(s);
      }
   }

   @Plugin(
      name = "Integer",
      category = "TypeConverter"
   )
   public static class IntegerConverter implements TypeConverter {
      public Integer convert(final String s) {
         return Integer.valueOf(s);
      }
   }

   @Plugin(
      name = "Level",
      category = "TypeConverter"
   )
   public static class LevelConverter implements TypeConverter {
      public Level convert(final String s) {
         return Level.valueOf(s);
      }
   }

   @Plugin(
      name = "Long",
      category = "TypeConverter"
   )
   public static class LongConverter implements TypeConverter {
      public Long convert(final String s) {
         return Long.valueOf(s);
      }
   }

   @Plugin(
      name = "Path",
      category = "TypeConverter"
   )
   public static class PathConverter implements TypeConverter {
      @SuppressFBWarnings(
         value = {"PATH_TRAVERSAL_IN"},
         justification = "The name of the accessed file is based on a configuration value."
      )
      public Path convert(final String s) throws Exception {
         return Paths.get(s);
      }
   }

   @Plugin(
      name = "Pattern",
      category = "TypeConverter"
   )
   public static class PatternConverter implements TypeConverter {
      public Pattern convert(final String s) {
         return Pattern.compile(s);
      }
   }

   @Plugin(
      name = "SecurityProvider",
      category = "TypeConverter"
   )
   public static class SecurityProviderConverter implements TypeConverter {
      public Provider convert(final String s) {
         return Security.getProvider(s);
      }
   }

   @Plugin(
      name = "Short",
      category = "TypeConverter"
   )
   public static class ShortConverter implements TypeConverter {
      public Short convert(final String s) {
         return Short.valueOf(s);
      }
   }

   @Plugin(
      name = "String",
      category = "TypeConverter"
   )
   public static class StringConverter implements TypeConverter {
      public String convert(final String s) {
         return s;
      }
   }

   @Plugin(
      name = "URI",
      category = "TypeConverter"
   )
   public static class UriConverter implements TypeConverter {
      public URI convert(final String s) throws URISyntaxException {
         return new URI(s);
      }
   }

   @Plugin(
      name = "URL",
      category = "TypeConverter"
   )
   public static class UrlConverter implements TypeConverter {
      public URL convert(final String s) throws MalformedURLException {
         return new URL(s);
      }
   }

   @Plugin(
      name = "UUID",
      category = "TypeConverter"
   )
   public static class UuidConverter implements TypeConverter {
      public UUID convert(final String s) throws Exception {
         return UUID.fromString(s);
      }
   }
}
