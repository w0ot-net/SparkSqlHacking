package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.ReadablePeriod;

public interface PeriodPrinter {
   int calculatePrintedLength(ReadablePeriod var1, Locale var2);

   int countFieldsToPrint(ReadablePeriod var1, int var2, Locale var3);

   void printTo(StringBuffer var1, ReadablePeriod var2, Locale var3);

   void printTo(Writer var1, ReadablePeriod var2, Locale var3) throws IOException;
}
