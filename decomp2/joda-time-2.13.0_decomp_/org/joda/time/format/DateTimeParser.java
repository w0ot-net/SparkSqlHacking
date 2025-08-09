package org.joda.time.format;

public interface DateTimeParser {
   int estimateParsedLength();

   int parseInto(DateTimeParserBucket var1, String var2, int var3);
}
