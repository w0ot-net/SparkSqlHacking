package org.joda.time.tz;

import java.util.Locale;

public interface NameProvider {
   String getShortName(Locale var1, String var2, String var3);

   String getName(Locale var1, String var2, String var3);
}
