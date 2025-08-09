package org.joda.time.format;

import java.util.Locale;
import org.joda.time.ReadWritablePeriod;

public interface PeriodParser {
   int parseInto(ReadWritablePeriod var1, String var2, int var3, Locale var4);
}
