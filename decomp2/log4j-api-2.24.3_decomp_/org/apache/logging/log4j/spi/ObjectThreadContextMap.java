package org.apache.logging.log4j.spi;

import java.util.Map;

public interface ObjectThreadContextMap extends CleanableThreadContextMap {
   Object getValue(String key);

   void putValue(String key, Object value);

   void putAllValues(Map values);
}
