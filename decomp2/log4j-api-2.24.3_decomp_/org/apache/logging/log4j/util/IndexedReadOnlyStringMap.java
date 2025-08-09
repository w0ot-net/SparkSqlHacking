package org.apache.logging.log4j.util;

public interface IndexedReadOnlyStringMap extends ReadOnlyStringMap {
   String getKeyAt(final int index);

   Object getValueAt(final int index);

   int indexOfKey(final String key);
}
