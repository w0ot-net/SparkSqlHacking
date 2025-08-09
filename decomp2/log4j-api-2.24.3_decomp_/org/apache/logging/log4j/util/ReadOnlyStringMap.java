package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.util.Map;

public interface ReadOnlyStringMap extends Serializable {
   Map toMap();

   boolean containsKey(String key);

   void forEach(final BiConsumer action);

   void forEach(final TriConsumer action, final Object state);

   Object getValue(final String key);

   boolean isEmpty();

   int size();
}
