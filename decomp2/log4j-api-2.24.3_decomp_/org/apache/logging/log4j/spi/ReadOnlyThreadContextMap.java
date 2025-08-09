package org.apache.logging.log4j.spi;

import java.util.Map;
import org.apache.logging.log4j.util.StringMap;

public interface ReadOnlyThreadContextMap {
   void clear();

   boolean containsKey(final String key);

   String get(final String key);

   Map getCopy();

   Map getImmutableMapOrNull();

   StringMap getReadOnlyContextData();

   boolean isEmpty();
}
