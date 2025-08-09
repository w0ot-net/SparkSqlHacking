package org.apache.log4j.helpers;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;

public class OptionConverter {
   static String DELIM_START = "${";
   static char DELIM_STOP = '}';
   static int DELIM_START_LEN = 2;
   static int DELIM_STOP_LEN = 1;
   private static final Logger LOGGER = StatusLogger.getLogger();
   static final int MAX_CUTOFF_LEVEL;
   static final int MIN_CUTOFF_LEVEL;
   static final ConcurrentMap LEVELS;
   private static final String LOG4J2_LEVEL_CLASS;
   private static final CharMap[] charMap;

   public static String[] concatanateArrays(final String[] l, final String[] r) {
      int len = l.length + r.length;
      String[] a = new String[len];
      System.arraycopy(l, 0, a, 0, l.length);
      System.arraycopy(r, 0, a, l.length, r.length);
      return a;
   }

   static int toLog4j2Level(final int v1Level) {
      if (v1Level >= MAX_CUTOFF_LEVEL) {
         return StandardLevel.OFF.intLevel();
      } else if (v1Level > 10000) {
         int offset = Math.round((float)(v1Level - 10000) / 100.0F);
         return StandardLevel.DEBUG.intLevel() - offset;
      } else if (v1Level > 5000) {
         int offset = Math.round((float)(v1Level - 5000) / 50.0F);
         return StandardLevel.TRACE.intLevel() - offset;
      } else if (v1Level > MIN_CUTOFF_LEVEL) {
         int offset = 5000 - v1Level;
         return StandardLevel.TRACE.intLevel() + offset;
      } else {
         return StandardLevel.ALL.intLevel();
      }
   }

   static int toLog4j1Level(final int v2Level) {
      if (v2Level == StandardLevel.ALL.intLevel()) {
         return Integer.MIN_VALUE;
      } else if (v2Level > StandardLevel.TRACE.intLevel()) {
         return MIN_CUTOFF_LEVEL + (StandardLevel.ALL.intLevel() - v2Level);
      } else if (v2Level > StandardLevel.DEBUG.intLevel()) {
         return 5000 + 50 * (StandardLevel.TRACE.intLevel() - v2Level);
      } else {
         return v2Level > StandardLevel.OFF.intLevel() ? 10000 + 100 * (StandardLevel.DEBUG.intLevel() - v2Level) : Integer.MAX_VALUE;
      }
   }

   static int toSyslogLevel(final int v2Level) {
      if (v2Level <= StandardLevel.FATAL.intLevel()) {
         return 0;
      } else if (v2Level <= StandardLevel.ERROR.intLevel()) {
         return 3 - 3 * (StandardLevel.ERROR.intLevel() - v2Level) / (StandardLevel.ERROR.intLevel() - StandardLevel.FATAL.intLevel());
      } else if (v2Level <= StandardLevel.WARN.intLevel()) {
         return 4;
      } else {
         return v2Level <= StandardLevel.INFO.intLevel() ? 6 - 2 * (StandardLevel.INFO.intLevel() - v2Level) / (StandardLevel.INFO.intLevel() - StandardLevel.WARN.intLevel()) : 7;
      }
   }

   public static Level createLevel(final Priority level) {
      String name = Strings.toRootUpperCase(level.toString()) + "#" + level.getClass().getName();
      return Level.forName(name, toLog4j2Level(level.toInt()));
   }

   public static Level convertLevel(final Priority level) {
      return level != null ? level.getVersion2Level() : Level.ERROR;
   }

   public static org.apache.log4j.Level convertLevel(final Level level) {
      org.apache.log4j.Level actualLevel = toLevel(level.name(), (org.apache.log4j.Level)null);
      if (actualLevel == null) {
         actualLevel = toLevel(LOG4J2_LEVEL_CLASS, level.name(), (org.apache.log4j.Level)null);
      }

      return actualLevel != null ? actualLevel : org.apache.log4j.Level.ERROR;
   }

   public static Level convertLevel(final String level, final Level defaultLevel) {
      org.apache.log4j.Level actualLevel = toLevel(level, (org.apache.log4j.Level)null);
      return actualLevel != null ? actualLevel.getVersion2Level() : defaultLevel;
   }

   public static String convertSpecialChars(final String s) {
      int len = s.length();
      StringBuilder sbuf = new StringBuilder(len);

      char c;
      for(int i = 0; i < len; sbuf.append(c)) {
         c = s.charAt(i++);
         if (c == '\\') {
            c = s.charAt(i++);

            for(CharMap entry : charMap) {
               if (entry.key == c) {
                  c = entry.replacement;
               }
            }
         }
      }

      return sbuf.toString();
   }

   public static String findAndSubst(final String key, final Properties props) {
      String value = props.getProperty(key);
      if (value == null) {
         return null;
      } else {
         try {
            return substVars(value, props);
         } catch (IllegalArgumentException e) {
            LOGGER.error("Bad option value [{}].", value, e);
            return value;
         }
      }
   }

   public static String getSystemProperty(final String key, final String def) {
      try {
         return System.getProperty(key, def);
      } catch (Throwable var3) {
         LOGGER.debug("Was not allowed to read system property \"{}\".", key);
         return def;
      }
   }

   public static Object instantiateByClassName(final String className, final Class superClass, final Object defaultValue) {
      if (className != null) {
         try {
            Object obj = LoaderUtil.newInstanceOf(className);
            if (!superClass.isAssignableFrom(obj.getClass())) {
               LOGGER.error("A \"{}\" object is not assignable to a \"{}\" variable", className, superClass.getName());
               return defaultValue;
            }

            return obj;
         } catch (ReflectiveOperationException e) {
            LOGGER.error("Could not instantiate class [" + className + "].", e);
         }
      }

      return defaultValue;
   }

   public static Object instantiateByKey(final Properties props, final String key, final Class superClass, final Object defaultValue) {
      String className = findAndSubst(key, props);
      if (className == null) {
         LogLog.error("Could not find value for key " + key);
         return defaultValue;
      } else {
         return instantiateByClassName(className.trim(), superClass, defaultValue);
      }
   }

   public static void selectAndConfigure(final InputStream inputStream, final String clazz, final LoggerRepository hierarchy) {
      Configurator configurator = null;
      if (clazz != null) {
         LOGGER.debug("Preferred configurator class: " + clazz);
         configurator = (Configurator)instantiateByClassName(clazz, Configurator.class, (Object)null);
         if (configurator == null) {
            LOGGER.error("Could not instantiate configurator [" + clazz + "].");
            return;
         }
      } else {
         configurator = new PropertyConfigurator();
      }

      configurator.doConfigure(inputStream, hierarchy);
   }

   public static void selectAndConfigure(final URL url, String clazz, final LoggerRepository hierarchy) {
      Configurator configurator = null;
      String filename = url.getFile();
      if (clazz == null && filename != null && filename.endsWith(".xml")) {
         clazz = "org.apache.log4j.xml.DOMConfigurator";
      }

      if (clazz != null) {
         LOGGER.debug("Preferred configurator class: " + clazz);
         configurator = (Configurator)instantiateByClassName(clazz, Configurator.class, (Object)null);
         if (configurator == null) {
            LOGGER.error("Could not instantiate configurator [" + clazz + "].");
            return;
         }
      } else {
         configurator = new PropertyConfigurator();
      }

      configurator.doConfigure(url, hierarchy);
   }

   public static String substVars(final String val, final Properties props) throws IllegalArgumentException {
      return substVars(val, props, new ArrayList());
   }

   private static String substVars(final String val, final Properties props, final List keys) throws IllegalArgumentException {
      if (val == null) {
         return null;
      } else {
         StringBuilder sbuf = new StringBuilder();
         int i = 0;

         while(true) {
            int j = val.indexOf(DELIM_START, i);
            if (j == -1) {
               if (i == 0) {
                  return val;
               } else {
                  sbuf.append(val.substring(i));
                  return sbuf.toString();
               }
            }

            sbuf.append(val.substring(i, j));
            int k = val.indexOf(DELIM_STOP, j);
            if (k == -1) {
               throw new IllegalArgumentException(Strings.dquote(val) + " has no closing brace. Opening brace at position " + j + '.');
            }

            j += DELIM_START_LEN;
            String key = val.substring(j, k);
            String replacement = PropertiesUtil.getProperties().getStringProperty(key, (String)null);
            if (replacement == null && props != null) {
               replacement = props.getProperty(key);
            }

            if (replacement != null) {
               if (!keys.contains(key)) {
                  List<String> usedKeys = new ArrayList(keys);
                  usedKeys.add(key);
                  String recursiveReplacement = substVars(replacement, props, usedKeys);
                  sbuf.append(recursiveReplacement);
               } else {
                  sbuf.append(replacement);
               }
            }

            i = k + DELIM_STOP_LEN;
         }
      }
   }

   public static boolean toBoolean(final String value, final boolean dEfault) {
      if (value == null) {
         return dEfault;
      } else {
         String trimmedVal = value.trim();
         if ("true".equalsIgnoreCase(trimmedVal)) {
            return true;
         } else {
            return "false".equalsIgnoreCase(trimmedVal) ? false : dEfault;
         }
      }
   }

   public static long toFileSize(final String value, final long defaultValue) {
      if (value == null) {
         return defaultValue;
      } else {
         String s = Strings.toRootUpperCase(value.trim());
         long multiplier = 1L;
         int index;
         if ((index = s.indexOf("KB")) != -1) {
            multiplier = 1024L;
            s = s.substring(0, index);
         } else if ((index = s.indexOf("MB")) != -1) {
            multiplier = 1048576L;
            s = s.substring(0, index);
         } else if ((index = s.indexOf("GB")) != -1) {
            multiplier = 1073741824L;
            s = s.substring(0, index);
         }

         if (s != null) {
            try {
               return Long.valueOf(s) * multiplier;
            } catch (NumberFormatException e) {
               LogLog.error("[" + s + "] is not in proper int form.");
               LogLog.error("[" + value + "] not in expected format.", e);
            }
         }

         return defaultValue;
      }
   }

   public static int toInt(final String value, final int dEfault) {
      if (value != null) {
         String s = value.trim();

         try {
            return Integer.valueOf(s);
         } catch (NumberFormatException e) {
            LogLog.error("[" + s + "] is not in proper int form.");
            e.printStackTrace();
         }
      }

      return dEfault;
   }

   public static org.apache.log4j.Level toLevel(String value, final org.apache.log4j.Level defaultValue) {
      if (value == null) {
         return defaultValue;
      } else {
         value = value.trim();
         org.apache.log4j.Level cached = (org.apache.log4j.Level)LEVELS.get(value);
         if (cached != null) {
            return cached;
         } else {
            int hashIndex = value.indexOf(35);
            if (hashIndex == -1) {
               if ("NULL".equalsIgnoreCase(value)) {
                  return null;
               } else {
                  org.apache.log4j.Level standardLevel = org.apache.log4j.Level.toLevel(value, defaultValue);
                  if (standardLevel != null && value.equals(standardLevel.toString())) {
                     LEVELS.putIfAbsent(value, standardLevel);
                  }

                  return standardLevel;
               }
            } else {
               String clazz = value.substring(hashIndex + 1);
               String levelName = value.substring(0, hashIndex);
               org.apache.log4j.Level customLevel = toLevel(clazz, levelName, defaultValue);
               if (customLevel != null && levelName.equals(customLevel.toString()) && clazz.equals(customLevel.getClass().getName())) {
                  LEVELS.putIfAbsent(value, customLevel);
               }

               return customLevel;
            }
         }
      }
   }

   public static org.apache.log4j.Level toLevel(final String clazz, final String levelName, final org.apache.log4j.Level defaultValue) {
      if ("NULL".equalsIgnoreCase(levelName)) {
         return null;
      } else {
         LOGGER.debug("toLevel:class=[{}]:pri=[{}]", clazz, levelName);
         if (LOG4J2_LEVEL_CLASS.equals(clazz)) {
            Level v2Level = Level.getLevel(Strings.toRootUpperCase(levelName));
            if (v2Level != null) {
               switch (v2Level.name()) {
                  case "ALL":
                     return org.apache.log4j.Level.ALL;
                  case "DEBUG":
                     return org.apache.log4j.Level.DEBUG;
                  case "ERROR":
                     return org.apache.log4j.Level.ERROR;
                  case "FATAL":
                     return org.apache.log4j.Level.FATAL;
                  case "INFO":
                     return org.apache.log4j.Level.INFO;
                  case "OFF":
                     return org.apache.log4j.Level.OFF;
                  case "WARN":
                     return org.apache.log4j.Level.WARN;
                  case "TRACE":
                     return org.apache.log4j.Level.TRACE;
                  default:
                     return new LevelWrapper(v2Level);
               }
            } else {
               return defaultValue;
            }
         } else {
            try {
               Class<?> customLevel = LoaderUtil.loadClass(clazz);
               Class<?>[] paramTypes = new Class[]{String.class, org.apache.log4j.Level.class};
               Method toLevelMethod = customLevel.getMethod("toLevel", paramTypes);
               Object[] params = new Object[]{levelName, defaultValue};
               Object o = toLevelMethod.invoke((Object)null, params);
               return (org.apache.log4j.Level)o;
            } catch (ClassNotFoundException var8) {
               LOGGER.warn("custom level class [" + clazz + "] not found.");
            } catch (NoSuchMethodException e) {
               LOGGER.warn("custom level class [" + clazz + "] does not have a class function toLevel(String, Level)", e);
            } catch (InvocationTargetException var10) {
               if (var10.getTargetException() instanceof InterruptedException || var10.getTargetException() instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               LOGGER.warn("custom level class [" + clazz + "] could not be instantiated", var10);
            } catch (ClassCastException e) {
               LOGGER.warn("class [" + clazz + "] is not a subclass of org.apache.log4j.Level", e);
            } catch (IllegalAccessException e) {
               LOGGER.warn("class [" + clazz + "] cannot be instantiated due to access restrictions", e);
            } catch (RuntimeException e) {
               LOGGER.warn("class [" + clazz + "], level [" + levelName + "] conversion failed.", e);
            }

            return defaultValue;
         }
      }
   }

   private OptionConverter() {
   }

   static {
      MAX_CUTOFF_LEVEL = '썐' + 100 * (StandardLevel.FATAL.intLevel() - StandardLevel.OFF.intLevel() - 1) + 1;
      MIN_CUTOFF_LEVEL = -2147478648 - (Integer.MIN_VALUE + StandardLevel.ALL.intLevel()) + StandardLevel.TRACE.intLevel();
      LEVELS = new ConcurrentHashMap();
      LOG4J2_LEVEL_CLASS = Level.class.getName();
      charMap = new CharMap[]{new CharMap('n', '\n'), new CharMap('r', '\r'), new CharMap('t', '\t'), new CharMap('f', '\f'), new CharMap('\b', '\b'), new CharMap('"', '"'), new CharMap('\'', '\''), new CharMap('\\', '\\')};
   }

   private static class CharMap {
      final char key;
      final char replacement;

      public CharMap(final char key, final char replacement) {
         this.key = key;
         this.replacement = replacement;
      }
   }

   private static class LevelWrapper extends org.apache.log4j.Level {
      private static final long serialVersionUID = -7693936267612508528L;

      protected LevelWrapper(final Level v2Level) {
         super(OptionConverter.toLog4j1Level(v2Level.intLevel()), v2Level.name(), OptionConverter.toSyslogLevel(v2Level.intLevel()), v2Level);
      }
   }
}
