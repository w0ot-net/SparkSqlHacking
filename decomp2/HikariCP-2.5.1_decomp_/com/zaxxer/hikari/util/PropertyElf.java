package com.zaxxer.hikari.util;

import com.zaxxer.hikari.HikariConfig;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertyElf {
   private static final Logger LOGGER = LoggerFactory.getLogger(PropertyElf.class);
   private static final Pattern GETTER_PATTERN = Pattern.compile("(get|is)[A-Z].+");

   public static void setTargetFromProperties(Object target, Properties properties) {
      if (target != null && properties != null) {
         List<Method> methods = Arrays.asList(target.getClass().getMethods());
         properties.forEach((key, value) -> {
            if (target instanceof HikariConfig && key.toString().startsWith("dataSource.")) {
               ((HikariConfig)target).addDataSourceProperty(key.toString().substring("dataSource.".length()), value);
            } else {
               setProperty(target, key.toString(), value, methods);
            }

         });
      }
   }

   public static Set getPropertyNames(Class targetClass) {
      HashSet<String> set = new HashSet();
      Matcher matcher = GETTER_PATTERN.matcher("");

      for(Method method : targetClass.getMethods()) {
         String name = method.getName();
         if (method.getParameterTypes().length == 0 && matcher.reset(name).matches()) {
            name = name.replaceFirst("(get|is)", "");

            try {
               if (targetClass.getMethod("set" + name, method.getReturnType()) != null) {
                  name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                  set.add(name);
               }
            } catch (Exception var9) {
            }
         }
      }

      return set;
   }

   public static Object getProperty(String propName, Object target) {
      try {
         String capitalized = "get" + propName.substring(0, 1).toUpperCase(Locale.ENGLISH) + propName.substring(1);
         Method method = target.getClass().getMethod(capitalized);
         return method.invoke(target);
      } catch (Exception var6) {
         try {
            String capitalized = "is" + propName.substring(0, 1).toUpperCase(Locale.ENGLISH) + propName.substring(1);
            Method method = target.getClass().getMethod(capitalized);
            return method.invoke(target);
         } catch (Exception var5) {
            return null;
         }
      }
   }

   public static Properties copyProperties(Properties props) {
      Properties copy = new Properties();
      props.forEach((key, value) -> copy.setProperty(key.toString(), value.toString()));
      return copy;
   }

   private static void setProperty(Object target, String propName, Object propValue, List methods) {
      String methodName = "set" + propName.substring(0, 1).toUpperCase(Locale.ENGLISH) + propName.substring(1);
      Method writeMethod = (Method)methods.stream().filter((m) -> m.getName().equals(methodName) && m.getParameterCount() == 1).findFirst().orElse((Object)null);
      if (writeMethod == null) {
         String methodName2 = "set" + propName.toUpperCase(Locale.ENGLISH);
         writeMethod = (Method)methods.stream().filter((m) -> m.getName().equals(methodName2) && m.getParameterCount() == 1).findFirst().orElse((Object)null);
      }

      if (writeMethod == null) {
         LOGGER.error("Property {} does not exist on target {}", propName, target.getClass());
         throw new RuntimeException(String.format("Property %s does not exist on target %s", propName, target.getClass()));
      } else {
         try {
            Class<?> paramClass = writeMethod.getParameterTypes()[0];
            if (paramClass == Integer.TYPE) {
               writeMethod.invoke(target, Integer.parseInt(propValue.toString()));
            } else if (paramClass == Long.TYPE) {
               writeMethod.invoke(target, Long.parseLong(propValue.toString()));
            } else if (paramClass != Boolean.TYPE && paramClass != Boolean.class) {
               if (paramClass == String.class) {
                  writeMethod.invoke(target, propValue.toString());
               } else {
                  writeMethod.invoke(target, propValue);
               }
            } else {
               writeMethod.invoke(target, Boolean.parseBoolean(propValue.toString()));
            }

         } catch (Exception e) {
            LOGGER.error("Failed to set property {} on target {}", new Object[]{propName, target.getClass(), e});
            throw new RuntimeException(e);
         }
      }
   }
}
