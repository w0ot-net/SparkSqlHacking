package org.apache.logging.log4j.util;

import aQute.bnd.annotation.spi.ServiceConsumer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

@ServiceConsumer(
   value = PropertySource.class,
   resolution = "optional",
   cardinality = "multiple"
)
public final class PropertiesUtil {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String LOG4J_PROPERTIES_FILE_NAME = "log4j2.component.properties";
   private static final String LOG4J_SYSTEM_PROPERTIES_FILE_NAME = "log4j2.system.properties";
   private static final Lazy COMPONENT_PROPERTIES = Lazy.lazy(() -> new PropertiesUtil("log4j2.component.properties", false));
   private static final Pattern DURATION_PATTERN = Pattern.compile("([+-]?\\d+)\\s*(\\w+)?", 2);
   private final Environment environment;

   public PropertiesUtil(final Properties props) {
      this((PropertySource)(new PropertiesPropertySource(props)));
   }

   public PropertiesUtil(final String propertiesFileName) {
      this(propertiesFileName, true);
   }

   private PropertiesUtil(final String propertiesFileName, final boolean useTccl) {
      this((PropertySource)(new PropertyFilePropertySource(propertiesFileName, useTccl)));
   }

   PropertiesUtil(final PropertySource source) {
      this.environment = new Environment(source);
   }

   static Properties loadClose(final InputStream in, final Object source) {
      Properties props = new Properties();
      if (null != in) {
         try {
            props.load(in);
         } catch (IOException error) {
            LOGGER.error((String)"Unable to read source `{}`", (Object)source, (Object)error);
         } finally {
            try {
               in.close();
            } catch (IOException error) {
               LOGGER.error((String)"Unable to close source `{}`", (Object)source, (Object)error);
            }

         }
      }

      return props;
   }

   public static PropertiesUtil getProperties() {
      return (PropertiesUtil)COMPONENT_PROPERTIES.get();
   }

   public void addPropertySource(final PropertySource propertySource) {
      this.environment.addPropertySource((PropertySource)Objects.requireNonNull(propertySource));
   }

   public void removePropertySource(final PropertySource propertySource) {
      this.environment.removePropertySource((PropertySource)Objects.requireNonNull(propertySource));
   }

   public boolean hasProperty(final String name) {
      return this.environment.containsKey(name);
   }

   public boolean getBooleanProperty(final String name) {
      return this.getBooleanProperty(name, false);
   }

   public boolean getBooleanProperty(final String name, final boolean defaultValue) {
      String prop = this.getStringProperty(name);
      return prop == null ? defaultValue : "true".equalsIgnoreCase(prop);
   }

   public boolean getBooleanProperty(final String name, final boolean defaultValueIfAbsent, final boolean defaultValueIfPresent) {
      String prop = this.getStringProperty(name);
      return prop == null ? defaultValueIfAbsent : (prop.isEmpty() ? defaultValueIfPresent : "true".equalsIgnoreCase(prop));
   }

   public Boolean getBooleanProperty(final String[] prefixes, final String key, final Supplier supplier) {
      for(String prefix : prefixes) {
         if (this.hasProperty(prefix + key)) {
            return this.getBooleanProperty(prefix + key);
         }
      }

      return supplier != null ? (Boolean)supplier.get() : null;
   }

   public Charset getCharsetProperty(final String name) {
      return this.getCharsetProperty(name, Charset.defaultCharset());
   }

   public Charset getCharsetProperty(final String name, final Charset defaultValue) {
      String charsetName = this.getStringProperty(name);
      if (charsetName == null) {
         return defaultValue;
      } else if (Charset.isSupported(charsetName)) {
         return Charset.forName(charsetName);
      } else {
         ResourceBundle bundle = getCharsetsResourceBundle();
         if (bundle.containsKey(name)) {
            String mapped = bundle.getString(name);
            if (Charset.isSupported(mapped)) {
               return Charset.forName(mapped);
            }
         }

         LOGGER.warn((String)"Unable to read charset `{}` from property `{}`. Falling back to the default: `{}`", (Object)charsetName, name, defaultValue);
         return defaultValue;
      }
   }

   public double getDoubleProperty(final String name, final double defaultValue) {
      String prop = this.getStringProperty(name);
      if (prop != null) {
         try {
            return Double.parseDouble(prop);
         } catch (NumberFormatException e) {
            LOGGER.warn((String)"Unable to read double `{}` from property `{}`. Falling back to the default: `{}`", (Object)prop, name, defaultValue, e);
         }
      }

      return defaultValue;
   }

   public int getIntegerProperty(final String name, final int defaultValue) {
      String prop = this.getStringProperty(name);
      if (prop != null) {
         try {
            return Integer.parseInt(prop.trim());
         } catch (NumberFormatException e) {
            LOGGER.warn((String)"Unable to read int `{}` from property `{}`. Falling back to the default: `{}`", (Object)prop, name, defaultValue, e);
         }
      }

      return defaultValue;
   }

   public Integer getIntegerProperty(final String[] prefixes, final String key, final Supplier supplier) {
      for(String prefix : prefixes) {
         if (this.hasProperty(prefix + key)) {
            return this.getIntegerProperty(prefix + key, 0);
         }
      }

      return supplier != null ? (Integer)supplier.get() : null;
   }

   public long getLongProperty(final String name, final long defaultValue) {
      String prop = this.getStringProperty(name);
      if (prop != null) {
         try {
            return Long.parseLong(prop);
         } catch (NumberFormatException e) {
            LOGGER.warn((String)"Unable to read long `{}` from property `{}`. Falling back to the default: `{}`", (Object)prop, name, defaultValue, e);
         }
      }

      return defaultValue;
   }

   public Long getLongProperty(final String[] prefixes, final String key, final Supplier supplier) {
      for(String prefix : prefixes) {
         if (this.hasProperty(prefix + key)) {
            return this.getLongProperty(prefix + key, 0L);
         }
      }

      return supplier != null ? (Long)supplier.get() : null;
   }

   public Duration getDurationProperty(final String name, final Duration defaultValue) {
      String prop = this.getStringProperty(name);

      try {
         return parseDuration(prop);
      } catch (IllegalArgumentException e) {
         LOGGER.warn((String)"Unable to read duration `{}` from property `{}`.\nExpected format 'n unit', where 'n' is an integer and 'unit' is one of: {}.", (Object)prop, name, PropertiesUtil.TimeUnit.getValidUnits().collect(Collectors.joining(", ")), e);
         return defaultValue;
      }
   }

   public Duration getDurationProperty(final String[] prefixes, final String key, final Supplier supplier) {
      for(String prefix : prefixes) {
         if (this.hasProperty(prefix + key)) {
            return this.getDurationProperty(prefix + key, (Duration)null);
         }
      }

      return supplier != null ? (Duration)supplier.get() : null;
   }

   public String getStringProperty(final String[] prefixes, final String key, final Supplier supplier) {
      for(String prefix : prefixes) {
         String result = this.getStringProperty(prefix + key);
         if (result != null) {
            return result;
         }
      }

      return supplier != null ? (String)supplier.get() : null;
   }

   public String getStringProperty(final String name) {
      return this.environment.get(name);
   }

   public String getStringProperty(final String name, final String defaultValue) {
      String prop = this.getStringProperty(name);
      return prop == null ? defaultValue : prop;
   }

   public static Properties getSystemProperties() {
      try {
         return new Properties(System.getProperties());
      } catch (SecurityException error) {
         LOGGER.error((String)"Unable to access system properties.", (Throwable)error);
         return new Properties();
      }
   }

   /** @deprecated */
   @Deprecated
   public void reload() {
   }

   public static Properties extractSubset(final Properties properties, final String prefix) {
      Properties subset = new Properties();
      if (prefix != null && !prefix.isEmpty()) {
         String prefixToMatch = prefix.charAt(prefix.length() - 1) != '.' ? prefix + '.' : prefix;
         Collection<String> keys = new ArrayList();

         for(String key : properties.stringPropertyNames()) {
            if (key.startsWith(prefixToMatch)) {
               subset.setProperty(key.substring(prefixToMatch.length()), properties.getProperty(key));
               keys.add(key);
            }
         }

         for(String key : keys) {
            properties.remove(key);
         }

         return subset;
      } else {
         return subset;
      }
   }

   static ResourceBundle getCharsetsResourceBundle() {
      return ResourceBundle.getBundle("Log4j-charsets");
   }

   public static Map partitionOnCommonPrefixes(final Properties properties) {
      return partitionOnCommonPrefixes(properties, false);
   }

   public static Map partitionOnCommonPrefixes(final Properties properties, final boolean includeBaseKey) {
      Map<String, Properties> parts = new ConcurrentHashMap();

      for(String key : properties.stringPropertyNames()) {
         int idx = key.indexOf(46);
         if (idx < 0) {
            if (includeBaseKey) {
               if (!parts.containsKey(key)) {
                  parts.put(key, new Properties());
               }

               ((Properties)parts.get(key)).setProperty("", properties.getProperty(key));
            }
         } else {
            String prefix = key.substring(0, idx);
            if (!parts.containsKey(prefix)) {
               parts.put(prefix, new Properties());
            }

            ((Properties)parts.get(prefix)).setProperty(key.substring(idx + 1), properties.getProperty(key));
         }
      }

      return parts;
   }

   public boolean isOsWindows() {
      return SystemPropertiesPropertySource.getSystemProperty("os.name", "").startsWith("Windows");
   }

   static Duration parseDuration(final CharSequence value) {
      Matcher matcher = DURATION_PATTERN.matcher(value);
      if (matcher.matches()) {
         return Duration.of(parseDurationAmount(matcher.group(1)), PropertiesUtil.TimeUnit.parseUnit(matcher.group(2)));
      } else {
         throw new IllegalArgumentException("Invalid duration value '" + value + "'.");
      }
   }

   private static long parseDurationAmount(final String amount) {
      try {
         return Long.parseLong(amount);
      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("Invalid duration amount '" + amount + "'", e);
      }
   }

   private static final class Environment {
      private final Set sources;
      private final ThreadLocal CURRENT_PROPERTY_SOURCE;

      private Environment(final PropertySource propertySource) {
         this.sources = ConcurrentHashMap.newKeySet();
         this.CURRENT_PROPERTY_SOURCE = new ThreadLocal();
         PropertySource sysProps = new PropertyFilePropertySource("log4j2.system.properties", false);

         try {
            sysProps.forEach((key, value) -> {
               if (System.getProperty(key) == null) {
                  System.setProperty(key, value);
               }

            });
         } catch (SecurityException e) {
            PropertiesUtil.LOGGER.warn((String)"Unable to set Java system properties from {} file, due to security restrictions.", (Object)"log4j2.system.properties", (Object)e);
         }

         this.sources.add(propertySource);
         Stream var10000 = ServiceLoaderUtil.safeStream(PropertySource.class, ServiceLoader.load(PropertySource.class, PropertiesUtil.class.getClassLoader()), PropertiesUtil.LOGGER);
         Set var10001 = this.sources;
         Objects.requireNonNull(var10001);
         var10000.forEach(var10001::add);
      }

      private void addPropertySource(final PropertySource propertySource) {
         this.sources.add(propertySource);
      }

      private void removePropertySource(final PropertySource propertySource) {
         this.sources.remove(propertySource);
      }

      private String get(final String key) {
         List<CharSequence> tokens = PropertySource.Util.tokenize(key);
         return (String)this.sources.stream().sorted(PropertySource.Comparator.INSTANCE).map((source) -> {
            if (!tokens.isEmpty()) {
               String normalKey = Objects.toString(source.getNormalForm(tokens), (String)null);
               if (normalKey != null && this.sourceContainsProperty(source, normalKey)) {
                  return this.sourceGetProperty(source, normalKey);
               }
            }

            return this.sourceGetProperty(source, key);
         }).filter(Objects::nonNull).findFirst().orElse((Object)null);
      }

      private boolean sourceContainsProperty(final PropertySource source, final String key) {
         PropertySource recursiveSource = (PropertySource)this.CURRENT_PROPERTY_SOURCE.get();
         if (recursiveSource == null) {
            label43: {
               this.CURRENT_PROPERTY_SOURCE.set(source);

               boolean var4;
               try {
                  var4 = source.containsProperty(key);
               } catch (Exception e) {
                  PropertiesUtil.LOGGER.warn((String)"Failed to retrieve Log4j property {} from property source {}.", (Object)key, source, e);
                  break label43;
               } finally {
                  this.CURRENT_PROPERTY_SOURCE.remove();
               }

               return var4;
            }
         }

         PropertiesUtil.LOGGER.warn((String)"Recursive call to `containsProperty()` from property source {}.", (Object)recursiveSource);
         return false;
      }

      private String sourceGetProperty(final PropertySource source, final String key) {
         PropertySource recursiveSource = (PropertySource)this.CURRENT_PROPERTY_SOURCE.get();
         if (recursiveSource == null) {
            label43: {
               this.CURRENT_PROPERTY_SOURCE.set(source);

               String var4;
               try {
                  var4 = source.getProperty(key);
               } catch (Exception e) {
                  PropertiesUtil.LOGGER.warn((String)"Failed to retrieve Log4j property {} from property source {}.", (Object)key, source, e);
                  break label43;
               } finally {
                  this.CURRENT_PROPERTY_SOURCE.remove();
               }

               return var4;
            }
         }

         PropertiesUtil.LOGGER.warn((String)"Recursive call to `getProperty()` from property source {}.", (Object)recursiveSource);
         return null;
      }

      private boolean containsKey(final String key) {
         List<CharSequence> tokens = PropertySource.Util.tokenize(key);
         return this.sources.stream().anyMatch((s) -> {
            CharSequence normalizedKey = tokens.isEmpty() ? null : s.getNormalForm(tokens);
            return this.sourceContainsProperty(s, key) || normalizedKey != null && this.sourceContainsProperty(s, normalizedKey.toString());
         });
      }
   }

   private static enum TimeUnit {
      NANOS(new String[]{"ns", "nano", "nanos", "nanosecond", "nanoseconds"}, ChronoUnit.NANOS),
      MICROS(new String[]{"us", "micro", "micros", "microsecond", "microseconds"}, ChronoUnit.MICROS),
      MILLIS(new String[]{"ms", "milli", "millis", "millisecond", "milliseconds"}, ChronoUnit.MILLIS),
      SECONDS(new String[]{"s", "second", "seconds"}, ChronoUnit.SECONDS),
      MINUTES(new String[]{"m", "minute", "minutes"}, ChronoUnit.MINUTES),
      HOURS(new String[]{"h", "hour", "hours"}, ChronoUnit.HOURS),
      DAYS(new String[]{"d", "day", "days"}, ChronoUnit.DAYS);

      private final String[] descriptions;
      private final TemporalUnit timeUnit;

      private TimeUnit(final String[] descriptions, final TemporalUnit timeUnit) {
         this.descriptions = descriptions;
         this.timeUnit = timeUnit;
      }

      private static Stream getValidUnits() {
         return Arrays.stream(values()).flatMap((unit) -> Arrays.stream(unit.descriptions));
      }

      private static TemporalUnit parseUnit(final String unit) {
         if (unit == null) {
            return ChronoUnit.MILLIS;
         } else {
            for(TimeUnit value : values()) {
               for(String description : value.descriptions) {
                  if (unit.equals(description)) {
                     return value.timeUnit;
                  }
               }
            }

            throw new IllegalArgumentException("Invalid duration unit '" + unit + "'");
         }
      }

      // $FF: synthetic method
      private static TimeUnit[] $values() {
         return new TimeUnit[]{NANOS, MICROS, MILLIS, SECONDS, MINUTES, HOURS, DAYS};
      }
   }
}
