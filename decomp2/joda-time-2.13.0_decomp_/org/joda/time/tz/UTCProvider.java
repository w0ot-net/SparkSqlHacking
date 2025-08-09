package org.joda.time.tz;

import java.util.Collections;
import java.util.Set;
import org.joda.time.DateTimeZone;

public final class UTCProvider implements Provider {
   private static final Set AVAILABLE_IDS = Collections.singleton("UTC");

   public DateTimeZone getZone(String var1) {
      return "UTC".equalsIgnoreCase(var1) ? DateTimeZone.UTC : null;
   }

   public Set getAvailableIDs() {
      return AVAILABLE_IDS;
   }
}
