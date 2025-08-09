package org.apache.logging.log4j.core.appender.nosql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultNoSqlObject implements NoSqlObject {
   private final Map map = new HashMap();

   public void set(final String field, final Object value) {
      this.map.put(field, value);
   }

   public void set(final String field, final NoSqlObject value) {
      this.map.put(field, value != null ? value.unwrap() : null);
   }

   public void set(final String field, final Object[] values) {
      this.map.put(field, values != null ? Arrays.asList(values) : null);
   }

   public void set(final String field, final NoSqlObject[] values) {
      if (values == null) {
         this.map.put(field, (Object)null);
      } else {
         List<Map<String, Object>> list = new ArrayList(values.length);

         for(NoSqlObject value : values) {
            list.add((Map)value.unwrap());
         }

         this.map.put(field, list);
      }

   }

   public Map unwrap() {
      return this.map;
   }
}
