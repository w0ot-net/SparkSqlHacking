package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public interface DateTimePrinter {
   int estimatePrintedLength();

   void printTo(StringBuffer var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7);

   void printTo(Writer var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException;

   void printTo(StringBuffer var1, ReadablePartial var2, Locale var3);

   void printTo(Writer var1, ReadablePartial var2, Locale var3) throws IOException;
}
