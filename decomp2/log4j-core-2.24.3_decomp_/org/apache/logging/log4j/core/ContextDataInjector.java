package org.apache.logging.log4j.core;

import java.util.List;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.StringMap;

public interface ContextDataInjector {
   StringMap injectContextData(final List properties, final StringMap reusable);

   /** @deprecated */
   @Deprecated
   ReadOnlyStringMap rawContextData();

   default Object getValue(final String key) {
      return this.rawContextData().getValue(key);
   }
}
