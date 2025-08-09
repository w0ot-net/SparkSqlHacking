package org.apache.hadoop.hive.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StringableMap extends HashMap {
   public StringableMap(String s) {
      String[] parts = s.split(":", 2);
      int numElements = Integer.parseInt(parts[0]);
      s = parts[1];

      for(int i = 0; i < numElements; ++i) {
         parts = s.split(":", 2);
         int len = Integer.parseInt(parts[0]);
         String key = null;
         if (len > 0) {
            key = parts[1].substring(0, len);
         }

         parts = parts[1].substring(len).split(":", 2);
         len = Integer.parseInt(parts[0]);
         String value = null;
         if (len > 0) {
            value = parts[1].substring(0, len);
         }

         s = parts[1].substring(len);
         this.put(key, value);
      }

   }

   public StringableMap(Map m) {
      super(m);
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append(this.size());
      buf.append(':');
      if (this.size() > 0) {
         for(Map.Entry entry : this.entrySet()) {
            int length = entry.getKey() == null ? 0 : ((String)entry.getKey()).length();
            buf.append(entry.getKey() == null ? 0 : length);
            buf.append(':');
            if (length > 0) {
               buf.append((String)entry.getKey());
            }

            length = entry.getValue() == null ? 0 : ((String)entry.getValue()).length();
            buf.append(length);
            buf.append(':');
            if (length > 0) {
               buf.append((String)entry.getValue());
            }
         }
      }

      return buf.toString();
   }

   public Properties toProperties() {
      Properties props = new Properties();
      props.putAll(this);
      return props;
   }
}
