package org.apache.logging.log4j.core.lookup;

import org.apache.logging.log4j.core.LogEvent;

public abstract class AbstractLookup implements StrLookup {
   public String lookup(final String key) {
      return this.lookup((LogEvent)null, key);
   }

   public LookupResult evaluate(final String key) {
      return this.evaluate((LogEvent)null, key);
   }
}
