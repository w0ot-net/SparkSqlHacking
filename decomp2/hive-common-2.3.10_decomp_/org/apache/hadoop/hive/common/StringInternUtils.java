package org.apache.hadoop.hive.common;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.hadoop.fs.Path;

public class StringInternUtils {
   private static Class uriClass = URI.class;
   private static Field stringField;
   private static Field schemeField;
   private static Field authorityField;
   private static Field hostField;
   private static Field pathField;
   private static Field fragmentField;
   private static Field schemeSpecificPartField;

   public static URI internStringsInUri(URI uri) {
      if (uri == null) {
         return null;
      } else {
         try {
            String string = (String)stringField.get(uri);
            if (string != null) {
               stringField.set(uri, string.intern());
            }

            String scheme = (String)schemeField.get(uri);
            if (scheme != null) {
               schemeField.set(uri, scheme.intern());
            }

            String authority = (String)authorityField.get(uri);
            if (authority != null) {
               authorityField.set(uri, authority.intern());
            }

            String host = (String)hostField.get(uri);
            if (host != null) {
               hostField.set(uri, host.intern());
            }

            String path = (String)pathField.get(uri);
            if (path != null) {
               pathField.set(uri, path.intern());
            }

            String fragment = (String)fragmentField.get(uri);
            if (fragment != null) {
               fragmentField.set(uri, fragment.intern());
            }

            String schemeSpecificPart = (String)schemeSpecificPartField.get(uri);
            if (schemeSpecificPart != null) {
               schemeSpecificPartField.set(uri, schemeSpecificPart.intern());
            }

            return uri;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public static Path internUriStringsInPath(Path path) {
      if (path != null) {
         internStringsInUri(path.toUri());
      }

      return path;
   }

   public static Path[] internUriStringsInPathArray(Path[] paths) {
      if (paths != null) {
         for(Path path : paths) {
            internUriStringsInPath(path);
         }
      }

      return paths;
   }

   public static List internStringsInList(List list) {
      if (list != null) {
         try {
            ListIterator<String> it = list.listIterator();

            while(it.hasNext()) {
               it.set(((String)it.next()).intern());
            }
         } catch (UnsupportedOperationException var2) {
         }
      }

      return list;
   }

   public static String[] internStringsInArray(String[] strings) {
      for(int i = 0; i < strings.length; ++i) {
         if (strings[i] != null) {
            strings[i] = strings[i].intern();
         }
      }

      return strings;
   }

   public static Map internValuesInMap(Map map) {
      if (map != null) {
         for(Object key : map.keySet()) {
            String value = (String)map.get(key);
            if (value != null) {
               map.put(key, value.intern());
            }
         }
      }

      return map;
   }

   public static String internIfNotNull(String s) {
      if (s != null) {
         s = s.intern();
      }

      return s;
   }

   static {
      try {
         stringField = uriClass.getDeclaredField("string");
         schemeField = uriClass.getDeclaredField("scheme");
         authorityField = uriClass.getDeclaredField("authority");
         hostField = uriClass.getDeclaredField("host");
         pathField = uriClass.getDeclaredField("path");
         fragmentField = uriClass.getDeclaredField("fragment");
         schemeSpecificPartField = uriClass.getDeclaredField("schemeSpecificPart");
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      }

      stringField.setAccessible(true);
      schemeField.setAccessible(true);
      authorityField.setAccessible(true);
      hostField.setAccessible(true);
      pathField.setAccessible(true);
      fragmentField.setAccessible(true);
      schemeSpecificPartField.setAccessible(true);
   }
}
