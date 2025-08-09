package org.apache.logging.log4j.layout.template.json;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.logging.log4j.layout.template.json.util.RecyclerFactories;
import org.apache.logging.log4j.layout.template.json.util.RecyclerFactory;
import org.apache.logging.log4j.util.PropertiesUtil;

public final class JsonTemplateLayoutDefaults {
   private static final PropertiesUtil PROPERTIES = PropertiesUtil.getProperties();

   private JsonTemplateLayoutDefaults() {
   }

   public static Charset getCharset() {
      String charsetName = PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.charset");
      return charsetName != null ? Charset.forName(charsetName) : StandardCharsets.UTF_8;
   }

   public static boolean isLocationInfoEnabled() {
      return PROPERTIES.getBooleanProperty("log4j.layout.jsonTemplate.locationInfoEnabled", false);
   }

   public static boolean isStackTraceEnabled() {
      return PROPERTIES.getBooleanProperty("log4j.layout.jsonTemplate.stackTraceEnabled", true);
   }

   public static String getTimestampFormatPattern() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.timestampFormatPattern", "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ");
   }

   public static TimeZone getTimeZone() {
      String timeZoneId = PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.timeZone");
      return timeZoneId != null ? TimeZone.getTimeZone(timeZoneId) : TimeZone.getDefault();
   }

   public static Locale getLocale() {
      String locale = PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.locale");
      if (locale == null) {
         return Locale.getDefault();
      } else {
         String[] localeFields = locale.split("_", 3);
         switch (localeFields.length) {
            case 1:
               return new Locale(localeFields[0]);
            case 2:
               return new Locale(localeFields[0], localeFields[1]);
            case 3:
               return new Locale(localeFields[0], localeFields[1], localeFields[2]);
            default:
               throw new IllegalArgumentException("invalid locale: " + locale);
         }
      }
   }

   public static String getEventTemplate() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.eventTemplate");
   }

   public static String getEventTemplateUri() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.eventTemplateUri", "classpath:EcsLayout.json");
   }

   public static String getEventTemplateRootObjectKey() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.eventTemplateRootObjectKey");
   }

   public static String getStackTraceElementTemplate() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.stackTraceElementTemplate");
   }

   public static String getStackTraceElementTemplateUri() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.stackTraceElementTemplateUri", "classpath:StackTraceElementLayout.json");
   }

   public static String getEventDelimiter() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.eventDelimiter", System.lineSeparator());
   }

   public static boolean isNullEventDelimiterEnabled() {
      return PROPERTIES.getBooleanProperty("log4j.layout.jsonTemplate.nullEventDelimiterEnabled", false);
   }

   public static int getMaxStringLength() {
      int maxStringLength = PROPERTIES.getIntegerProperty("log4j.layout.jsonTemplate.maxStringLength", 16384);
      if (maxStringLength <= 0) {
         throw new IllegalArgumentException("was expecting a non-zero positive maxStringLength: " + maxStringLength);
      } else {
         return maxStringLength;
      }
   }

   public static String getTruncatedStringSuffix() {
      return PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.truncatedStringSuffix", "…");
   }

   public static RecyclerFactory getRecyclerFactory() {
      String recyclerFactorySpec = PROPERTIES.getStringProperty("log4j.layout.jsonTemplate.recyclerFactory");
      return RecyclerFactories.ofSpec(recyclerFactorySpec);
   }
}
